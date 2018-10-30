package com.kpi.KPI.Graph.Repositories;

import com.kpi.KPI.Graph.Entity.Report;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface ReportRepository extends CrudRepository<Report, Long> {

//get 3 months data
@Query("select r from Report r where r.team.teamId= ?1 AND( r.date  BETWEEN ?2 AND ?3 )")
List<Report> findAllByIdAndDate(Long id,Date now,Date pastDate);





    List<Report> findAll();

   @Query("select r from Report r where r.team.teamId= ?1")
    List<Report> findAllById(Long id);

    @Query("select r from Report r where r.team.teamId= ?1 AND( r.date  BETWEEN ?2 AND ?3 )")
    List<Report> findAllByDateRange(Long id, Date fromDate, Date toDate);

//baseline data
    @Query("select r from Report r where r.team.teamId= ?1 AND( r.date  BETWEEN ?2 AND ?3 )")
    List<Report> findAllBaselineById(Long id,Date now,Date pastDate );


    @Override
    Optional<Report> findById(Long id);
}