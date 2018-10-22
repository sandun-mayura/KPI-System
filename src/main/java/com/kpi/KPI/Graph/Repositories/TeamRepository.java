package com.kpi.KPI.Graph.Repositories;

import com.kpi.KPI.Graph.Entity.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team,Long>{


    @Query("select t from Team t WHERE  t.teamName =?1")
    List<Team> TeamsByTeamName(String name);


}