package br.metrics.schedule;

import br.metrics.helpers.RandomHelper;
import com.daioware.sort.SortAlgorithm;
import com.daioware.sort.SortList;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class SortTaskScheduler {

    Logger logger = LoggerFactory.getLogger(SortTaskScheduler.class);

    @Autowired
    MeterRegistry registry;

    ExecutorService executor = Executors.newFixedThreadPool(10);

    @Scheduled(cron = "*/5 * * * * * ")
    public void loadTest() {

        var targetList = new ArrayList<String>();
        Random random = new Random();
        for(int i=0; i<100000; i++){
            var size = random.nextInt(300) + 1;
            targetList.add(RandomHelper.getRandomString(size));
            if(size == 150)
                registry.counter("size.150", "size", "150").increment();
            if(size == 300)
                registry.counter("size.300", "size", "300").increment();
        }
        executor.execute( () -> {
                    var availableSorting = SortAlgorithm.values();
                    var alg = random.nextInt(availableSorting.length);
                    logger.info("Starting process " + availableSorting[alg].name());
                    registry.more().longTaskTimer("process", "alg", availableSorting[alg].name()).record(
                            () -> SortList.sort(availableSorting[alg], targetList, String::compareTo)
                    );
                }
        );
    }
}
