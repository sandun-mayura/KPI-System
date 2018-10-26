package com.kpi.KPI.Graph.dto;

public class ReportResponseDTO {

    private Long dataId;

    private Long teamId;

    private Long totalBugCount;

    private double averageBug;

    private long totalDelivered;



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
        this.averageBug = averageBug;
    }

    public long getTotalDelivered() {
        return totalDelivered;
    }

    public void setTotalDelivered(long totalDelivered) {
        this.totalDelivered = totalDelivered;
    }
}