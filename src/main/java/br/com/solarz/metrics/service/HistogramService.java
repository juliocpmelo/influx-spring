package br.com.solarz.metrics.service;

import br.com.solarz.metrics.dto.MemoryDto;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistogramService {
    @Autowired
    private MeterRegistry meterRegistry;
    private static final String KEY_HISTOGRAM_RECORD_MEMORY_TOTAL = "memory_total.histogram";
    private static final String KEY_HISTOGRAM_RECORD_MEMORY_FREE = "memory_free.histogram";
    private static final String KEY_HISTOGRAM_RECORD_MEMORY_IN_USE = "memory_in_use.histogram";
    private static final String KEY_HISTOGRAM_RECORD_SWAP_MEMORY_TOTAL = "swap_memory_total.histogram";
    private static final String KEY_HISTOGRAM_RECORD_SWAP_MEMORY_FREE = "swap_memory_free.histogram";
    private static final String KEY_HISTOGRAM_RECORD_SWAP_MEMORY_IN_USE = "swap_memory_in_use.histogram";

    public void createHistogram(MemoryDto [] memories) {
        createHistogram(memories[0], KEY_HISTOGRAM_RECORD_MEMORY_TOTAL, KEY_HISTOGRAM_RECORD_MEMORY_FREE, KEY_HISTOGRAM_RECORD_MEMORY_IN_USE);
        createHistogram(memories[1], KEY_HISTOGRAM_RECORD_SWAP_MEMORY_TOTAL, KEY_HISTOGRAM_RECORD_SWAP_MEMORY_FREE, KEY_HISTOGRAM_RECORD_SWAP_MEMORY_IN_USE);
    }

    private void createHistogram(MemoryDto memory, String keyTotal, String keyFree, String keyInUse) {
        register(keyFree, memory.getFree());
        register(keyTotal, memory.getTotal());
        register(keyInUse, memory.getInUse());
    }

    private void register(String key, double value) {
        DistributionSummary.builder(key)
                .register(meterRegistry).record(value);
    }
}
