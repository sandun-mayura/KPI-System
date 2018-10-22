package com.kpi.KPI.Graph.Repositories;

import com.kpi.KPI.Graph.Entity.Report;
import com.kpi.KPI.Graph.Entity.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ReportRepository extends CrudRepository<Report,Long>{


 //   @Query("select t from Team t WHERE  t.teamName =?1")
    List<Report> findAll();

    @Query("select r from Report r where r.teamid= ?1")
    List<Report> findAllById(Long id);

    @Query("select r from Report r where r.teamid= ?1 AND   ( r.date  BETWEEN ?2 AND ?3 )")
    List<Report> findAllByDateRange(Long id, Date fromDate, Date toDate);

}