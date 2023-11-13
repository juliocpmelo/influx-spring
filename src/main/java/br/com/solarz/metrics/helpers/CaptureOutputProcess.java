package br.com.solarz.metrics.helpers;

import br.com.solarz.metrics.dto.MemoryDto;
import br.com.solarz.metrics.helpers.dto.MemoryCaptureOutputDto;

import java.util.function.Function;

public class CaptureOutputProcess {
    public static MemoryDto[] getMemories() {
        var memories = new MemoryDto[2];
        Function<String, Double> onParseDouble = (in) -> Double.parseDouble(in.replace("Gi", "")
                .replace("B", ""));

        Function<MemoryCaptureOutputDto, Void> onAdd = (in) -> {
            var arr = in.getArr();
            var index = in.getIndex();
            var total = onParseDouble.apply(arr[1].replace(",", "."));
            var inUse = onParseDouble.apply(arr[2].replace(",", "."));
            var free = onParseDouble.apply(arr[3].replace(",", "."));
            memories[index] = new MemoryDto(total, inUse, free);
            return null;
        };


        RunProcess.start("free -h", (output) -> {
            var arr = output.split("\\s+");
            if (output.contains("Mem"))
                onAdd.apply(new MemoryCaptureOutputDto(arr, 0));
            else if (output.contains("Swap"))
                onAdd.apply(new MemoryCaptureOutputDto(arr, 1));
            return null;
        });

        return memories;
    }
}
