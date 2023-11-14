package br.com.solarz.metrics.helpers;

import br.com.solarz.metrics.dto.MemoryDto;
import br.com.solarz.metrics.helpers.dto.MemoryCaptureOutputDto;

import java.util.function.Function;

public class CaptureOutputProcess {
    public static MemoryDto[] getMemories() {
        Function<String, Double> onConvertUnitOfMeasureInGB = (str) -> {
            if (str.contains("Gi"))
                return ParseHelper.parseInGibToGB(Double.parseDouble(str.replace("Gi", "")));
            return ParseHelper.parseInGibToGB(ParseHelper.parse(Double.parseDouble(str.replace("Mi", ""))));
        };

        var memories = new MemoryDto[2];

        Function<MemoryCaptureOutputDto, Void> onAdd = (in) -> {
            var arr = in.getArr();
            var index = in.getIndex();
            var total = onConvertUnitOfMeasureInGB.apply(arr[1].replace(",", "."));
            var inUse = onConvertUnitOfMeasureInGB.apply(arr[2].replace(",", "."));
            var free = onConvertUnitOfMeasureInGB.apply(arr[3].replace(",", "."));
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
