/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retria;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.processos.Processo;
import com.github.britooo.looca.api.group.processos.ProcessoGrupo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author silvam
 */
public class TesteProcesso {

    public static void main(String[] args) {
        Looca looca = new Looca();
        ProcessoGrupo grupoDeProcessos = looca.getGrupoDeProcessos();
        List<Processo> processos = grupoDeProcessos.getProcessos();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy ");
        LocalDateTime dataHora = LocalDateTime.now();

        System.out.println("LISTAGEM DE PROCESSOS: ");
        System.out.println("Data e Hora: " + dataHora.format(formatter));
        
        for (Processo processo : processos) {

            System.out.println(String.format(
                    """
                        Processo: %s; PID: %d
                           Percentual de uso da CPU: %.3f%%
                           Percentual de usa da RAM: %.3f%%
                           MBs alocados na RAM: %dMB
                        """,
                    processo.getNome(), processo.getPid(),
                    processo.getUsoCpu(), // Retorna o valor percentual de uso da CPU pelo processo.
                    processo.getUsoMemoria(), //Retorna o Valor percentual de uso da Memória RAM pelo processo.
                    (processo.getBytesUtilizados() / 1000000) // Retorna quanta memória está alocada para esse processo e está na RAM em MB
            ));
        }
        System.out.println("Total de Processos em execuçã: " + grupoDeProcessos.getTotalProcessos());
        System.out.println("Total de Threads em execução: " + grupoDeProcessos.getTotalThreads());
    }

}
