package br.com.solarz.metrics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemoryDto {
    private double total;
    private double inUse;
    private double free;
}
