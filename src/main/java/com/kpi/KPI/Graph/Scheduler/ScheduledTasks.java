package com.kpi.KPI.Graph.Scheduler;

import com.kpi.KPI.Graph.Services.BaselineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduledTasks {
@Autowired
    BaselineService baselineService;
    @Scheduled(cron = "0 0 0 1 1/1 ?")
    public void monthlyBaseline() {
        System.out.println("Baseline values calculated @" + new Date()); //Print(In console) scheduler when started.
        baselineService.monthlyBaseline();
}
}