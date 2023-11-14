package br.com.solarz.metrics.schedule;

import br.com.solarz.metrics.service.MetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Component
public class PopulateMemorySchedule {
    @Autowired
    private MetricService metricService;
    private final int x = 10;
    private int limit = x;

    @PostConstruct
    public void created() {
        populateMemory();
    }

    @Scheduled(cron = "*/5 * * * * * ")
    public void run() {
        populateMemory();
    }

    private void populateMemory() {
        for (int i = 0; i < limit; i++) {
             new ArrayList<>(1024);
        }
        limit *= x;
    }
}
