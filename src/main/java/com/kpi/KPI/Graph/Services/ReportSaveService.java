package com.kpi.KPI.Graph.Services;

import com.kpi.KPI.Graph.Entity.Report;
import com.kpi.KPI.Graph.Entity.Team;
import com.kpi.KPI.Graph.Repositories.ReportRepository;
import com.kpi.KPI.Graph.Repositories.TeamRepository;
import com.kpi.KPI.Graph.dto.SaveReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class ReportSaveService {
    @Autowired
    TeamRepository teamRepository;

    @Autowired
    ReportRepository reportRepository;

    //create Calendar instance
    Calendar now = Calendar.getInstance();
    Date today = new Date();
    int weekOfYear = now.get(Calendar.WEEK_OF_YEAR);

    public void saveReport(SaveReportDTO saveReportDTO, HttpServletRequest request) {

//If comes with team id
        if (saveReportDTO.getDataId() != null) {
            Optional<Report> reportlist = reportRepository.findById(saveReportDTO.getDataId());

            Report report = reportlist.get();

            Optional<Team> teamlist = teamRepository.findById(saveReportDTO.getTeam());
            report.setTeam(teamlist.get());
            report.setQaBug(saveReportDTO.getBugCount());
            report.setClientBug(saveReportDTO.getClientBug());
            report.setOntimeDelivered(saveReportDTO.getOntimeDelivered());
            report.setNotDelivered(saveReportDTO.getNotDelivered());
            report.setMisses(saveReportDTO.getMisses());
            reportRepository.save(report);
        }
        else {
            Report report = new Report();
            Optional<Team> teamlist = teamRepository.findById(saveReportDTO.getTeam());

            report.setTeam(teamlist.get());
            report.setQaBug(saveReportDTO.getBugCount());
            report.setClientBug(saveReportDTO.getClientBug());
            report.setOntimeDelivered(saveReportDTO.getOntimeDelivered());
            report.setNotDelivered(saveReportDTO.getNotDelivered());
            report.setMisses(saveReportDTO.getMisses());
            report.setDate(today);
            report.setNoOfWeek(weekOfYear);

            reportRepository.save(report);
        }
    }
}

