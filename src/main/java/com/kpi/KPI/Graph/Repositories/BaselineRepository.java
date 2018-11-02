package com.kpi.KPI.Graph.Repositories;

import com.kpi.KPI.Graph.Entity.Baseline;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface BaselineRepository extends CrudRepository<Baseline,Long> {

    //baseline data
    @Query("select b from Baseline b where b.team.teamId= ?1 AND( b.date  BETWEEN ?2 AND ?3 )")
    List<Baseline> findAllBaselineById(Long id, Date pastDate, Date fDate);
}