package com.kpi.KPI.Graph.dto;

public class SaveReportDTO {

    private Long dataId;
    private Long team;
    private long bugCount;
    private long clientBug;
    private long ontimeDelivered;
    private long notDelivered;
    private long misses;



    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public Long getTeam() {
        return team;
    }

    public void setTeam(Long team) {
        this.team = team;
    }

    public long getBugCount() {
        return bugCount;
    }

    public void setBugCount(long bugCount) {
        this.bugCount = bugCount;
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
}