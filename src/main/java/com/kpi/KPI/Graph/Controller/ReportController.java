package com.kpi.KPI.Graph.Controller;

import com.kpi.KPI.Graph.Entity.Report;
import com.kpi.KPI.Graph.Services.ReportService;
import com.kpi.KPI.Graph.dto.BaselineResponseDTO;
import com.kpi.KPI.Graph.dto.ReportResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class ReportController {


    @Autowired
    private ReportService reportService;


    //Report View for 3 Months
    @GetMapping("/reportView")
    public List<Report> reportView(@RequestParam(value = "teamId") Long id) {
        List<Report> reportView = reportService.getReportViewById(id);
        return reportView;
    }

    //calculate results (total value etc)
    @GetMapping("/calculateView")
    public List<ReportResponseDTO> CalculateView(@RequestParam(value = "teamId") Long id){
        List<ReportResponseDTO> listOfCalculation = reportService.getCalculateViewById(id);
        return listOfCalculation;
    }

    //Calculate baseline information
    @GetMapping("/baselineView")
    public List<BaselineResponseDTO> BaselineView(@RequestParam(value = "teamId") Long id) {
        List<BaselineResponseDTO> listOfBaseline = reportService.getBaselineViewById(id);
        return listOfBaseline;

    }


}