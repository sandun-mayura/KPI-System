package com.kpi.KPI.Graph.Controller;


import com.kpi.KPI.Graph.Entity.Team;
import com.kpi.KPI.Graph.Services.TeamInfomationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/all")
public class ReportController {

    @Autowired
   private TeamInfomationService teamInfomationService;


    @GetMapping("/team/{name}")
    public List<Team> getTeamName(@PathVariable String name) {

        List<Team> TeamNames = teamInfomationService.getTeamsByTeamName(name);
        return TeamNames;
    }

}
