package com.kpi.KPI.Graph.Controller;

import com.kpi.KPI.Graph.Entity.Report;
import com.kpi.KPI.Graph.Entity.Team;
import com.kpi.KPI.Graph.Services.ReportService;
import com.kpi.KPI.Graph.dto.BaselineResponseDTO;
import com.kpi.KPI.Graph.dto.ReportResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
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
    public List<Report> getRecord
    (@RequestParam(value = "id") Long id,
     @RequestParam(value = "fromDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date fromDate,
     @RequestParam(value = "toDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date toDate) {


        List<Report> reportByDate = reportService.getReportByDateRange(id, fromDate, toDate);

        return reportByDate;
    }

    //calculate results by date range
    @GetMapping("/calResult")
    public List<ReportResponseDTO> getValues
    (@RequestParam(value = "id") Long id,
     @RequestParam(value = "fromDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date fromDate,
     @RequestParam(value = "toDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date toDate) {


        List<ReportResponseDTO> listOfCalculation = reportService.getCalulateReportByDateRange(id, fromDate, toDate);

        return listOfCalculation;
    }

    //Calculate baseline information
    @GetMapping("/baseline")
    public List<BaselineResponseDTO> getBaselineById(@RequestParam Long id) {
        List<BaselineResponseDTO> listOfBaseline = reportService.getBaselineById(id);
        return listOfBaseline;

    }


}