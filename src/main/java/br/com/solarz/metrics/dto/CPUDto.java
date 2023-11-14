package br.com.solarz.metrics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CPUDto {
    private int us; //Percentual de tempo gasto em modo de usuário.
    private int sy; //Percentual de tempo gasto em modo de sistema (kernel).
    private int id; //Percentual de tempo em que a CPU está ociosa (idle).
    private int wa; //Percentual de tempo em que a CPU está esperando por operações de entrada/saída (I/O wait).
    private int st; //Percentual de tempo roubado da máquina virtual para uso em máquinas virtuais em execução em um host.
}
