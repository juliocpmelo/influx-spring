package br.com.solarz.metrics.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Function;

public class RunProcess {
    public static void start(String command, Function<String, Void> call) {
        // create to process
        var processBuilder = new ProcessBuilder("/bin/bash", "-c", command);
        try {
            var process = processBuilder.start();
            // read to output in process
            var reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while (true) {
                var read = reader.readLine();
                if (read != null) call.apply(read);
                else break;
            }
            // wait to end in process
            var status = process.waitFor();
            if (status != 0) throw new RuntimeException("Erro ao executar o comando. Código de saída: " + status);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
