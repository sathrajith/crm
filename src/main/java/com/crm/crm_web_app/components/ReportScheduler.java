package com.crm.crm_web_app.components;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ReportScheduler {

    @Scheduled(cron = " 0 0 8 * * ?")
    public void sendDailyreport() {
        System.out.println("Daily Report generated and sent.");
    }
}
