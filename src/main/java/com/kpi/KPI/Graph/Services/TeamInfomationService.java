package com.kpi.KPI.Graph.Services;

import com.kpi.KPI.Graph.Entity.Team;
import com.kpi.KPI.Graph.Repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeamInfomationService {




    @Autowired
    TeamRepository teamRepository;

    public List<Team> getTeamsByTeamName(String name){
        List<Team> TeamNames =  teamRepository.TeamsByTeamName(name);
        return TeamNames;
    }

}