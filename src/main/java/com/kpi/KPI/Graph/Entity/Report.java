package com.kpi.KPI.Graph.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "report_data")
public class Report {
    @Id
    @Column(name = "data_id")
    private long teamId;

    @Column(name = "team_id")
    private long team_id;

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
    private long date;

    @Column(name = "no_of_week")
    private long noOfWeek;

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    public long getTeam_id() {
        return team_id;
    }

    public void setTeam_id(long team_id) {
        this.team_id = team_id;
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

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getNoOfWeek() {
        return noOfWeek;
    }

    public void setNoOfWeek(long noOfWeek) {
        this.noOfWeek = noOfWeek;
    }
}