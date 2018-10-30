package com.kpi.KPI.Graph.Repositories;

import com.kpi.KPI.Graph.Entity.Report;
import com.kpi.KPI.Graph.Entity.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends CrudRepository<Team,Long>{


    @Query("select t from Team t WHERE  t.teamName =?1")
    List<Team> TeamsByTeamName(String name);


    @Override
    Optional<Team> findById(Long id);
}