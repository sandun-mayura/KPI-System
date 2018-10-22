package com.kpi.KPI.Graph.dto;

public class ReportResponseDTO {

    private Long teamId;

    private Long totalBugCount;

    private Long allCount;

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long getTotalBugCount() {
        return totalBugCount;
    }

    public void setTotalBugCount(Long totalBugCount) {
        this.totalBugCount = totalBugCount;
    }

    public Long getAllCount() {
        return allCount;
    }

    public void setAllCount(Long allCount) {
        this.allCount = allCount;
    }
}