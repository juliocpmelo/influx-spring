package br.com.solarz.metrics.schedule;

import br.com.solarz.metrics.service.MetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MetricsSchedule {
    @Autowired
    private MetricService metricService;
    @Scheduled(cron = "*/5 * * * * * ")
    public void run() {
        metricService.createMetrics();
        metricService.createCounterSchedule();
    }
}
