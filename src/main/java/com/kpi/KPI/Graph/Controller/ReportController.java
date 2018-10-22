package com.kpi.KPI.Graph.Controller;

import com.kpi.KPI.Graph.Entity.Report;
import com.kpi.KPI.Graph.Entity.Team;
import com.kpi.KPI.Graph.Services.ReportService;
import com.kpi.KPI.Graph.dto.ReportResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class ReportController {


    @Autowired
    private ReportService reportService;

    //getting all data from report table
    @GetMapping(path = "/all")
    @ResponseBody
    Iterable<Report> getAllstylist() {
        return reportService.findAll();
    }


    //geting all data by team ID (in team table)
    @GetMapping("/team/{name}")
    public List<Team> getTeamName(@PathVariable String name) {
        List<Team> TeamNames = reportService.getTeamsByTeamName(name);
        return TeamNames;
    }

    //find data by team Id (Report Table)
    @GetMapping("/report")
    public List<Report> getReportById(@RequestParam Long id) {
        List<Report> ReportData = reportService.getReportById(id);
        return ReportData;

    }

    //find data by date range (Report Table)
    @GetMapping("/reportRange")
    public List<Report/*ReportResponseDTO*/> getRecord
    (@RequestParam(value = "id") Long id,
     @RequestParam(value = "fromDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date fromDate,
     @RequestParam(value = "toDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date toDate) {


        List<Report/*ReportResponseDTO*/> reportByDate = reportService.getReportByDateRange(id, fromDate, toDate);

        return reportByDate;

    }
}