package com.kpi.KPI.Graph.Services;

import com.kpi.KPI.Graph.Entity.Report;
import com.kpi.KPI.Graph.Entity.Team;
import com.kpi.KPI.Graph.Repositories.ReportRepository;
import com.kpi.KPI.Graph.Repositories.TeamRepository;
import com.kpi.KPI.Graph.dto.BaselineResponseDTO;
import com.kpi.KPI.Graph.dto.ReportResponseDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;


@Service
public class ReportService {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    ReportRepository reportRepository;

    public Iterable<Report> findAll() {
        return reportRepository.findAll();
    }


    public List<Team> getTeamsByTeamName(String name) {
        List<Team> TeamNames = teamRepository.TeamsByTeamName(name);
        return TeamNames;
    }

    //report data by teamId
    public List<Report> getReportById(Long id) {
        List<Report> ReportData = reportRepository.findAllById(id);
        return ReportData;
    }

    // data by date range
    public List<Report> getReportByDateRange(Long id, Date fromDate, Date toDate) {
        List<Report> listOfReports = reportRepository.findAllByDateRange(id, fromDate, toDate);

        return listOfReports;
    }

    //report calculation by date range
    public List<ReportResponseDTO> getCalulateReportByDateRange(Long id, Date fromDate, Date toDate) {
        List<Report> ReportData = reportRepository.findAllByDateRange(id, fromDate, toDate);

        List<ReportResponseDTO> listOfCalculation = new ArrayList<>();
        Iterator<Report> ite = ReportData.iterator();
        //  Long allCount = 0L;
        while (ite.hasNext()) {
            Report report = ite.next();

            ReportResponseDTO reportResponseDTO = new ReportResponseDTO();
            reportResponseDTO.setTeamId(report.getTeam().getTeamId());
            reportResponseDTO.setDataId(report.getDataId());
            reportResponseDTO.setTotalBugCount(report.getClientBug() + report.getQaBug());

            /*Calculate Average bug*/
            double TotalBug = (report.getClientBug() + report.getQaBug());
            double AvgBug = TotalBug / (report.getTeam().getTeamMembers());
            DecimalFormat df = new DecimalFormat("###.##");
            reportResponseDTO.setAverageBug(Double.parseDouble(df.format(AvgBug)));

            //Calculate total delivered

            reportResponseDTO.setTotalDelivered(report.getOntimeDelivered() + report.getNotDelivered());

            //  allCount = allCount + reportResponseDTO.getTotalBugCount();
            listOfCalculation.add(reportResponseDTO);
        }
        return listOfCalculation;
    }


    //Baseline  data calculate
    public List<BaselineResponseDTO> getBaselineById(Long id) {

//set date for past 6 month
        LocalDate today = LocalDate.now();
        LocalDate earlier = today.minusMonths(4);

        Date now = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date pastDate = Date.from(earlier.atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<Report> BaselineData = reportRepository.findAllBaselineById(id, pastDate, now);
        BaselineResponseDTO baselineResponseDTO = new BaselineResponseDTO();
//calculation of baseline
        double allBugs = 0;
        long count = 0;
        double totalDelived;
        double notDelivered;
        double allNotDel = 0;
        double avgBug = 0;
        double totalMisses=0;

        List<BaselineResponseDTO> listOfBaseline = new ArrayList<>();

        Iterator<Report> iteb = BaselineData.iterator();

        while (iteb.hasNext()) {
            Report report = iteb.next();
            count++;  //geting  no of rows


            baselineResponseDTO.setTeamId(report.getTeam().getTeamId());

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
            totalMisses +=(report.getMisses());
        }

        //All bug baseline
        double qaBaseline = (allBugs / count);
        DecimalFormat df = new DecimalFormat("##.##");
        baselineResponseDTO.setQaBaseline(Double.parseDouble(df.format(qaBaseline)));

        //Not delivered baseline
        double notDelBaseline = (allNotDel / count);
        baselineResponseDTO.setNotDeliveredBaseline(Double.parseDouble(df.format(notDelBaseline)));

        //Average bug baseline
        double avgBugBaseline = (avgBug / count);
        baselineResponseDTO.setTotalBugAvg(Double.parseDouble(df.format(avgBugBaseline)));

//Misses baseline
        double missesBaseline = (totalMisses / count);
        baselineResponseDTO.setMissesBaseline(Double.parseDouble(df.format(missesBaseline)));


        listOfBaseline.add(baselineResponseDTO);
        return listOfBaseline;
    }

}