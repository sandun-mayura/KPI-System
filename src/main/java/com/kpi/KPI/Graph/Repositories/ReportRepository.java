package com.kpi.KPI.Graph.Repositories;

import com.kpi.KPI.Graph.Entity.Report;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public interface ReportRepository extends CrudRepository<Report, Long> {


    //   @Query("select t from Team t WHERE  t.teamName =?1")
    List<Report> findAll();

    @Query("select r from Report r where r.team.teamId= ?1")
    List<Report> findAllById(Long id);

    @Query("select r from Report r where r.team.teamId= ?1 AND( r.date  BETWEEN ?2 AND ?3 )")
    List<Report> findAllByDateRange(Long id, Date fromDate, Date toDate);

//baseline data
    @Query("select r from Report r where r.team.teamId= ?1 AND( r.date  BETWEEN ?2 AND ?3 )")
    List<Report> findAllBaselineById(Long id,Date now,Date pastDate );

}