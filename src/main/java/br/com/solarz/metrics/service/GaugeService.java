package br.com.solarz.metrics.service;

import br.com.solarz.metrics.dto.MemoryDto;
import br.com.solarz.metrics.helpers.CaptureOutputProcess;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GaugeService {
    private static final String KEY_GAUGE_MEMORY_TOTAL = "memory_total";
    private static final String KEY_GAUGE_MEMORY_FREE = "memory_free";
    private static final String KEY_GAUGE_MEMORY_IN_USE = "memory_in_use";
    private static final String KEY_GAUGE_SWAP_MEMORY_TOTAL = "swap_memory_total";
    private static final String KEY_GAUGE_SWAP_MEMORY_FREE = "swap_memory_free";
    private static final String KEY_GAUGE_SWAP_MEMORY_IN_USE = "swap_memory_in_use";
    @Autowired
    private MeterRegistry meterRegistry;

    public void createGauge(MemoryDto[] memories) {
        var memory = memories[0];
        var swap = memories[1];
        createGauge(memory, KEY_GAUGE_MEMORY_TOTAL, KEY_GAUGE_MEMORY_FREE, KEY_GAUGE_MEMORY_IN_USE);
        createGauge(swap, KEY_GAUGE_SWAP_MEMORY_TOTAL, KEY_GAUGE_SWAP_MEMORY_FREE, KEY_GAUGE_SWAP_MEMORY_IN_USE);
    }

    private void createGauge(MemoryDto memory, String keyTotal, String keyFree, String keyInUse) {
        Gauge.builder(keyTotal, memory, MemoryDto::getTotal).register(meterRegistry);
        Gauge.builder(keyFree, memory, MemoryDto::getFree).register(meterRegistry);
        Gauge.builder(keyInUse, memory, MemoryDto::getInUse).register(meterRegistry);
    }
}
