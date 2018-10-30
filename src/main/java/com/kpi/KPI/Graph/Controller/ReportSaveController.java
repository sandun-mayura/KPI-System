package com.kpi.KPI.Graph.Controller;


import com.kpi.KPI.Graph.Services.ReportSaveService;
import com.kpi.KPI.Graph.dto.BaselineResponseDTO;
import com.kpi.KPI.Graph.dto.SaveReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class ReportSaveController {

    @Autowired
    private ReportSaveService reportService;
//save All report values
    @PostMapping("/saveReportValues")
    public List<BaselineResponseDTO> saveReportValues(@RequestBody SaveReportDTO saveReportDTO, HttpServletRequest request) {
        reportService.saveReport(saveReportDTO, request);
        return null;
    }
}