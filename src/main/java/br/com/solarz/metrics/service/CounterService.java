package br.com.solarz.metrics.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CounterService {
    @Autowired
    private MeterRegistry meterRegistry;
    private static final String KEY_COUNTER_SCHEDULE = "metric_schedule";

    public void createCounter() {
        Counter.builder(KEY_COUNTER_SCHEDULE).description("Contagem de vezes em que o schedule é acionado.").register(meterRegistry).increment();
    }
}
