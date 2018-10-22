package com.kpi.KPI.Graph.Services;

import com.kpi.KPI.Graph.Entity.Report;
import com.kpi.KPI.Graph.Entity.Team;
import com.kpi.KPI.Graph.Repositories.ReportRepository;
import com.kpi.KPI.Graph.Repositories.TeamRepository;
import com.kpi.KPI.Graph.dto.ReportResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Iterable<Report> findAll() {
        return reportRepository.findAll();
    }


    public List<Team> getTeamsByTeamName(String name){
        List<Team> TeamNames =  teamRepository.TeamsByTeamName(name);
        return TeamNames;
    }

    //report data by teamId
    public List<Report> getReportById(Long id){
        List<Report> ReportData =  reportRepository.findAllById(id);
        return ReportData;
    }
   //report data by date range
    public List<Report/*ReportResponseDTO*/> getReportByDateRange(Long id, Date fromDate, Date toDate){
        List<Report> listOfReports =  reportRepository.findAllByDateRange(id, fromDate, toDate);
/*
        List<ReportResponseDTO> listOfReports = new ArrayList<>();
        Iterator<Report> ite = reportData.iterator();
        Long allCount = 0L;
        while(ite.hasNext()){
            Report report = ite.next();

            ReportResponseDTO reportResponseDTO = new ReportResponseDTO();
            reportResponseDTO.setTeamId(report.getTeamid());
            reportResponseDTO.setTotalBugCount(report.getClientBug() + report.getQaBug());
            allCount = allCount + reportResponseDTO.getTotalBugCount();
            listOfReports.add(reportResponseDTO);
        }*/



        return listOfReports;
    }



}