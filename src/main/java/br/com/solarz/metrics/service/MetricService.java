package br.com.solarz.metrics.service;

import br.com.solarz.metrics.dto.MemoryDto;
import br.com.solarz.metrics.helpers.CaptureOutputProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetricService {
    @Autowired
    private GaugeService gaugeService;
    @Autowired
    private CounterService counterService;
    @Autowired
    private HistogramService histogramService;
    private final MemoryDto[] memories = CaptureOutputProcess.getMemories();

    public void createHistogram() {
        histogramService.createHistogram(memories);
    }

    public void createGauge() {
        gaugeService.createGauge(memories);
    }

    public void createCounter() {
        counterService.createCounter();
    }
}
