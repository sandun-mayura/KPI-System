package com.kpi.KPI.Graph.Services;

import com.kpi.KPI.Graph.Entity.Baseline;
import com.kpi.KPI.Graph.Entity.Report;
import com.kpi.KPI.Graph.Entity.Team;
import com.kpi.KPI.Graph.Repositories.BaselineRepository;
import com.kpi.KPI.Graph.Repositories.ReportRepository;
import com.kpi.KPI.Graph.Repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class BaselineService {

@Autowired
    ReportRepository reportRepository;
    @Autowired
    BaselineRepository baselineRepository;

    @Autowired
    TeamRepository teamRepository;

    LocalDate today = LocalDate.now();


    public List<Baseline> getBaselineViewById(Long id) {

LocalDate firstDateOfMonth=today.withDayOfMonth(1);
        LocalDate earlier = today.minusMonths(3);

        Date fDate = Date.from(firstDateOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date pastDate = Date.from(earlier.atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<Baseline> listOfbaseline = baselineRepository.findAllBaselineById(id, pastDate, fDate);
        return listOfbaseline;
    }


    public void monthlyBaseline() {

        List<Team> teamlist = teamRepository.findAll();
        Iterator<Team> teamIterator = teamlist.iterator();


        while (teamIterator.hasNext()) {
            Team team = teamIterator.next();

            long teamId = team.getTeamId();


            //set date for past 6 month
            LocalDate earlier = today.minusMonths(6);
            Date now = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date pastDate = Date.from(earlier.atStartOfDay(ZoneId.systemDefault()).toInstant());


            List<Report> reportList =reportRepository.findAllByIdAndDate(teamId, pastDate, now);

//calculation of baseline
            double allBugs = 0;
            long count = 0;
            double totalDelived;
            double notDelivered;
            double allNotDel = 0;
            double avgBug = 0;
            double totalMisses = 0;


            Iterator<Report> iteb = reportList.iterator();

            while (iteb.hasNext()) {
                Report report = iteb.next();
                count++;  //geting  no of rows


                //All bugs count
                allBugs += (report.getClientBug() + report.getQaBug());

                //Total delivered
                totalDelived = (report.getOntimeDelivered() + report.getNotDelivered());
                notDelivered = (report.getNotDelivered() / totalDelived) * 100;
                allNotDel += notDelivered;

                //Average bug

                double bugSum = (report.getClientBug() + report.getQaBug());
                double teamMembers = report.getTeam().getTeamMembers();
                avgBug += bugSum / teamMembers;

                //Misses
                totalMisses += (report.getMisses());
            }
            DecimalFormat df = new DecimalFormat("##.##");

            //All bug baseline
            double qaBaseline = Double.parseDouble(df.format(allBugs / count));
            double qaBaselineTarget = Double.parseDouble(df.format((allBugs / count) * 0.9666));

            //Not delivered baseline
            double notDelBaseline = Double.parseDouble(df.format(allNotDel / count));
            double notDelBaselineTarget = Double.parseDouble(df.format((allNotDel / count) * 0.9666));

            //Average bug baseline
            double avgBugBaseline = Double.parseDouble(df.format(avgBug / count));
            double avgBugBaselineTarget = Double.parseDouble(df.format((avgBug / count) * 0.9666));

            //Misses baseline
            double missesBaseline = Double.parseDouble(df.format(totalMisses / count));
            double missesBaselineTarget = Double.parseDouble(df.format((totalMisses / count) * 0.9666));



            Optional<Team> teamObj = teamRepository.findById(team.getTeamId()); //Create team object
            Baseline baseline = new Baseline();  //Create baseline object
            baseline.setDate(now);
            baseline.setTeam(teamObj.get());

            baseline.setQaBaseline(qaBaseline);
            baseline.setQaBaselineTarget(qaBaselineTarget);

            baseline.setNotDeliveredBaseline(notDelBaseline);
            baseline.setNotDeliveredBaselineTarget(notDelBaselineTarget);

            baseline.setTotalBugAvgBaseline(avgBugBaseline);
            baseline.setTotalBugAvgBaselineTarget(avgBugBaselineTarget);

            baseline.setMissesBaseline(missesBaseline);
            baseline.setMissesBaselineTarget(missesBaselineTarget);

            baselineRepository.save(baseline);
        }


    }


}