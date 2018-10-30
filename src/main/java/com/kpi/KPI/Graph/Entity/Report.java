package com.kpi.KPI.Graph.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "report_data")
public class Report {

    @Id
    @Column(name = "data_id")
    @GeneratedValue(generator = "snowflake")
    @GenericGenerator(name = "snowflake", strategy ="com.kpi.KPI.Graph.utils.SnowflakeIdGenerator")
    private long dataId;

    @JoinColumn(name = "team_id")
    @OneToOne(fetch=FetchType.EAGER)
    private Team team;

    @Column(name = "qa_bug")
    private long qaBug;

    @Column(name = "client_bug")
    private long clientBug;

    @Column(name = "ontime_delivered")
    private long ontimeDelivered;

    @Column(name = "not_delivered")
    private long notDelivered;

    @Column(name = "misses")
    private long misses;

    @Column(name = "date")
    @JsonFormat(timezone = "GMT+05:30",pattern="yyyy-MM-dd")
    private Date date;

    @Column(name = "no_of_week")
    private long noOfWeek;

    @Column(name = "qa_baseline")
    private Double qaBL;

    @Column(name = "qa_baseline_target")
    private Double qaBLTarget;

    @Column(name = "not_delivered_baseline")
    private Double notDeliveredBL;

    @Column(name = "not_delivered_baseline_target")
    private Double notDeliveredBLTarget;

    @Column(name = "total_bug_avg")
    private Double totalBugAvg;

    @Column(name = "total_bug_avg_target")
    private Double totalBugAvgTarget;

    @Column(name = "misses_baseline")
    private Double missesBL;

    @Column(name = "misses_baseline_target")
    private Double missesBLTarget;



    public long getDataId() {
        return dataId;
    }

    public void setDataId(long dataId) {
        this.dataId = dataId;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public long getQaBug() {
        return qaBug;
    }

    public void setQaBug(long qaBug) {
        this.qaBug = qaBug;
    }

    public long getClientBug() {
        return clientBug;
    }

    public void setClientBug(long clientBug) {
        this.clientBug = clientBug;
    }

    public long getOntimeDelivered() {
        return ontimeDelivered;
    }

    public void setOntimeDelivered(long ontimeDelivered) {
        this.ontimeDelivered = ontimeDelivered;
    }

    public long getNotDelivered() {
        return notDelivered;
    }

    public void setNotDelivered(long notDelivered) {
        this.notDelivered = notDelivered;
    }

    public long getMisses() {
        return misses;
    }

    public void setMisses(long misses) {
        this.misses = misses;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getNoOfWeek() {
        return noOfWeek;
    }

    public void setNoOfWeek(long noOfWeek) {
        this.noOfWeek = noOfWeek;
    }

    public Double getQaBL() {
        return qaBL;
    }

    public void setQaBL(Double qaBL) {
        this.qaBL = qaBL;
    }

    public Double getQaBLTarget() {
        return qaBLTarget;
    }

    public void setQaBLTarget(Double qaBLTarget) {
        this.qaBLTarget = qaBLTarget;
    }

    public Double getNotDeliveredBL() {
        return notDeliveredBL;
    }

    public void setNotDeliveredBL(Double notDeliveredBL) {
        this.notDeliveredBL = notDeliveredBL;
    }

    public Double getNotDeliveredBLTarget() {
        return notDeliveredBLTarget;
    }

    public void setNotDeliveredBLTarget(Double notDeliveredBLTarget) {
        this.notDeliveredBLTarget = notDeliveredBLTarget;
    }

    public Double getTotalBugAvg() {
        return totalBugAvg;
    }

    public void setTotalBugAvg(Double totalBugAvg) {
        this.totalBugAvg = totalBugAvg;
    }

    public Double getTotalBugAvgTarget() {
        return totalBugAvgTarget;
    }

    public void setTotalBugAvgTarget(Double totalBugAvgTarget) {
        this.totalBugAvgTarget = totalBugAvgTarget;
    }

    public Double getMissesBL() {
        return missesBL;
    }

    public void setMissesBL(Double missesBL) {
        this.missesBL = missesBL;
    }

    public Double getMissesBLTarget() {
        return missesBLTarget;
    }

    public void setMissesBLTarget(Double missesBLTarget) {
        this.missesBLTarget = missesBLTarget;
    }
}