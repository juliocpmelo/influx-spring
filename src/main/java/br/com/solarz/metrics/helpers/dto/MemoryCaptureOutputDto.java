package br.com.solarz.metrics.helpers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MemoryCaptureOutputDto {
    private String [] arr;
    private Integer index;
}
