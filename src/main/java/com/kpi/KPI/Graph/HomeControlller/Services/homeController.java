package com.kpi.KPI.Graph.HomeControlller.Services;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class homeController {


    @GetMapping(value = "/index")
    public String index(){
        return "index";
    }
    @GetMapping(value = "/test")
    public String test(){
        return "test.html";
    }
    @GetMapping(value = "/QABugView")
    public String QABugView(){
        return "QABugView";
    }
    @GetMapping(value = "/OverallAverageBugView")
    public String OverallAverageBugView(){
        return "OverallAverageBugView";
    }

    @RequestMapping(value = "/OverallTimelineView")
    public String OverallTimelineView(){
        return "OverallTimelineView";
    }
    @GetMapping(value = "/OverallProcedureMissesView")
    public String OverallProcedureMissesView(){
        return "OverallProcedureMissesView";
    }





}