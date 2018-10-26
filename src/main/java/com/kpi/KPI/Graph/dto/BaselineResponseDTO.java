package com.kpi.KPI.Graph.dto;

public class BaselineResponseDTO {



    private Long teamId;

    private double qaBaseline;

    private double notDeliveredBaseline;

    private double totalBugAvg;

    private double missesBaseline;


    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public double getQaBaseline() {
        return qaBaseline;
    }

    public void setQaBaseline(double qaBaseline) {
        this.qaBaseline = qaBaseline;
    }

    public double getNotDeliveredBaseline() {
        return notDeliveredBaseline;
    }

    public void setNotDeliveredBaseline(double notDeliveredBaseline) {
        this.notDeliveredBaseline = notDeliveredBaseline;
    }

    public double getTotalBugAvg() {
        return totalBugAvg;
    }

    public void setTotalBugAvg(double totalBugAvg) {
        this.totalBugAvg = totalBugAvg;
    }

    public double getMissesBaseline() {
        return missesBaseline;
    }

    public void setMissesBaseline(double missesBaseline) {
        this.missesBaseline = missesBaseline;
    }
}