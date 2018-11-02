package com.kpi.KPI.Graph.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;



    @Entity
    @Table(name = "baseline")
    public class Baseline {

        @Id
        @Column(name = "baseline_id")
        @GeneratedValue(generator = "snowflake")
        @GenericGenerator(name = "snowflake", strategy ="com.kpi.KPI.Graph.utils.SnowflakeIdGenerator")
        private long baselineId;

        @JoinColumn(name = "team_id")
        @OneToOne(fetch=FetchType.EAGER)
        private Team team;

        @Column(name = "date")
        @JsonFormat(timezone = "GMT+05:30",pattern="yyyy-MM-dd")
        private Date date;

        @Column(name = "qa_baseline")
        private double qaBaseline;

        @Column(name = "qa_baseline_target")
        private double qaBaselineTarget;

        @Column(name = "not_delivered_baseline")
        private double notDeliveredBaseline;

        @Column(name = "not_delivered_baseline_target")
        private double notDeliveredBaselineTarget;

        @Column(name = "total_bug_avg_baseline")
        private double totalBugAvgBaseline;

        @Column(name = "total_bug_avg_baseline_target")
        private double totalBugAvgBaselineTarget;

        @Column(name = "misses_baseline")
        private double missesBaseline;

        @Column(name = "misses_baseline_target")
        private double missesBaselineTarget;

        public long getBaselineId() {
            return baselineId;
        }

        public void setBaselineId(long baselineId) {
            this.baselineId = baselineId;
        }

        public Team getTeam() {
            return team;
        }

        public void setTeam(Team team) {
            this.team = team;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
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


