package com.kpi.KPI.Graph.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import java.util.Date;

public class ReportResponseDTO {

    private Long dataId;

    private Long teamId;

    private Long week;

    private Long qaBug;

    @JsonFormat(timezone = "GMT+05:30",pattern="yyyy-MM-dd")
    private Date date;

    private Long totalBugCount;

    private double averageBug;

    private long totalDelivered;

    private double qaBaseline;

    private double qaBaselineTarget;

    private double notDeliveredBaseline;

    private double notDeliveredBaselineTarget;

    private double totalBugAvgBaseline;

    private double totalBugAvgBaselineTarget;

    private double missesBaseline;

    private double missesBaselineTarget;

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public Long getWeek() {
        return week;
    }

    public Long getQaBug() {
        return qaBug;
    }

    public void setQaBug(Long qaBug) {
        this.qaBug = qaBug;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setWeek(Long week) {
        this.week = week;
    }

    public Long getTotalBugCount() {
        return totalBugCount;
    }

    public void setTotalBugCount(Long totalBugCount) {
        this.totalBugCount = totalBugCount;
    }

    public double getAverageBug() {
        return averageBug;
    }

    public void setAverageBug(double averageBug) {
        this.averageBug = averageBug; }public long getTotalDelivered() {
        return totalDelivered; }

    public void setTotalDelivered(long totalDelivered) {
        this.totalDelivered = totalDelivered;
    }

    public double getQaBaseline() {
        return qaBaseline;
    }

    public void setQaBaseline(double qaBaseline) {
        this.qaBaseline = qaBaseline;
    }

    public double getQaBaselineTarget() {
        return qaBaselineTarget;
    }

    public void setQaBaselineTarget(double qaBaselineTarget) {
        this.qaBaselineTarget = qaBaselineTarget;
    }

    public double getNotDeliveredBaseline() {
        return notDeliveredBaseline;
    }

    public void setNotDeliveredBaseline(double notDeliveredBaseline) {
        this.notDeliveredBaseline = notDeliveredBaseline;
    }

    public double getNotDeliveredBaselineTarget() {
        return notDeliveredBaselineTarget;
    }

    public void setNotDeliveredBaselineTarget(double notDeliveredBaselineTarget) {
        this.notDeliveredBaselineTarget = notDeliveredBaselineTarget;
    }

    public double getTotalBugAvgBaseline() {
        return totalBugAvgBaseline;
    }

    public void setTotalBugAvgBaseline(double totalBugAvgBaseline) {
        this.totalBugAvgBaseline = totalBugAvgBaseline;
    }

    public double getTotalBugAvgBaselineTarget() {
        return totalBugAvgBaselineTarget;
    }

    public void setTotalBugAvgBaselineTarget(double totalBugAvgBaselineTarget) {
        this.totalBugAvgBaselineTarget = totalBugAvgBaselineTarget;
    }

    public double getMissesBaseline() {
        return missesBaseline;
    }

    public void setMissesBaseline(double missesBaseline) {
        this.missesBaseline = missesBaseline;
    }

    public double getMissesBaselineTarget() {
        return missesBaselineTarget;
    }

    public void setMissesBaselineTarget(double missesBaselineTarget) {
        this.missesBaselineTarget = missesBaselineTarget;
    }
}