package com.kpi.KPI.Graph.Services;

import com.kpi.KPI.Graph.Entity.Baseline;
import com.kpi.KPI.Graph.Entity.Report;
import com.kpi.KPI.Graph.Repositories.BaselineRepository;
import com.kpi.KPI.Graph.Repositories.ReportRepository;
import com.kpi.KPI.Graph.Repositories.TeamRepository;
import com.kpi.KPI.Graph.dto.ReportResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


@Service
public class ReportService {


    @Autowired
    TeamRepository teamRepository;

    @Autowired
    ReportRepository reportRepository;
    @Autowired
    BaselineRepository baselineRepository;

    LocalDate today = LocalDate.now();

    LocalDate earlier = today.minusMonths(3);

    Date now = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
    Date pastDate = Date.from(earlier.atStartOfDay(ZoneId.systemDefault()).toInstant());

    // Report View for 3 month
    public List<Report> getReportViewById(Long id) {


        List<Report> listOfReports = reportRepository.findAllByIdAndDate(id, pastDate, now);
        return listOfReports;
    }


    //report calculation
    public List<ReportResponseDTO> getCalculateViewById(Long id) {
        LocalDate earlier = today.minusMonths(3);

        Date now = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date pastDate = Date.from(earlier.atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<Report> ReportData = reportRepository.findAllByIdAndDate(id, pastDate, now);
        List<Baseline> listOfBaseline = baselineRepository.findAllBaselineById(id, pastDate, now);

        List<ReportResponseDTO> listOfCalculation = new ArrayList<>();
        Iterator<Report> ite = ReportData.iterator();

        //  Long allCount = 0L;
        while (ite.hasNext()) {
            Report report = ite.next();
            ReportResponseDTO reportResponseDTO = new ReportResponseDTO();

            reportResponseDTO.setTeamId(report.getTeam().getTeamId());
            reportResponseDTO.setDataId(report.getDataId());
//calculate weeks

            LocalDate d = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(report.getDate()));
            long weekOfWeekBasedYear = d.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);

            reportResponseDTO.setWeek(weekOfWeekBasedYear);
            reportResponseDTO.setDate(report.getDate());


            Iterator<Baseline> iteB = listOfBaseline.iterator();

//set baseline to related month
            while (iteB.hasNext()) {
                Baseline baseline = iteB.next();

                LocalDate localDateBaseline = baseline.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate localDateReport = report.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                int monthB = localDateBaseline.getMonthValue();
                int monthR = localDateReport.getMonthValue();
                if (monthB == monthR) {
                    reportResponseDTO.setQaBaseline(baseline.getQaBaseline());
                    reportResponseDTO.setQaBaselineTarget(baseline.getQaBaselineTarget());
                    reportResponseDTO.setMissesBaselineTarget(baseline.getTotalBugAvgBaselineTarget());
                    reportResponseDTO.setNotDeliveredBaseline(baseline.getNotDeliveredBaseline());
                    reportResponseDTO.setNotDeliveredBaselineTarget(baseline.getNotDeliveredBaselineTarget());
                    reportResponseDTO.setTotalBugAvgBaseline(baseline.getTotalBugAvgBaseline());
                    reportResponseDTO.setTotalBugAvgBaselineTarget(baseline.getTotalBugAvgBaselineTarget());
                    reportResponseDTO.setMissesBaseline(baseline.getMissesBaseline());
                    reportResponseDTO.setMissesBaselineTarget(baseline.getMissesBaselineTarget());
                }
            }
            reportResponseDTO.setQaBug(report.getQaBug());
            reportResponseDTO.setTotalBugCount(report.getClientBug() + report.getQaBug());
            /*Calculate Average bug*/
            double TotalBug = (report.getClientBug() + report.getQaBug());
            double AvgBug = TotalBug / (report.getTeam().getTeamMembers());
            DecimalFormat df = new DecimalFormat("###.##");
            reportResponseDTO.setAverageBug(Double.parseDouble(df.format(AvgBug)));
            //Calculate total delivered
            reportResponseDTO.setTotalDelivered(report.getOnTimeDelivered() + report.getNotDelivered());

            listOfCalculation.add(reportResponseDTO);
        }

        return listOfCalculation;
    }
}