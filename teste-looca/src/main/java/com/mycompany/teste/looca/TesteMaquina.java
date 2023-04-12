/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.teste.looca;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.rede.RedeInterface;
import com.github.britooo.looca.api.group.rede.RedeInterfaceGroup;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.group.temperatura.Temperatura;
import com.github.britooo.looca.api.group.rede.Rede;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.print.DocFlavor;

/**
 *
 * @author silvam
 */
public class TesteMaquina {

    public static void main(String[] args) {
        Looca looca = new Looca();
        // Informações da máquina

        // Processador
        Processador processador = looca.getProcessador();
        Double processadorUso = processador.getUso();
        Long processadorFreq = processador.getFrequencia();

        // Memoria
        Memoria memoria = looca.getMemoria();
        Double memoriaTotalGb = Double.valueOf(memoria.getTotal()) / 1000000000.0;
        Double memoriaEmUsoGb = Double.valueOf(memoria.getEmUso()) / 1000000000.0;
        Double memoriaDisponivelGb = Double.valueOf(memoria.getDisponivel()) / 1000000000.0;

        // Disco
        DiscoGrupo grupoDeDiscos = looca.getGrupoDeDiscos();
        List<Disco> discos = grupoDeDiscos.getDiscos();

        //Rede
        List<RedeInterface> interfaceDeRede = looca.getRede().getGrupoDeInterfaces().getInterfaces();
        
        System.out.println("Sistema da máquina");
        System.out.println(looca.getSistema() + "\n");
        
        //Data e hora
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy ");
        LocalDateTime dataHora = LocalDateTime.now();
        
        new Timer().scheduleAtFixedRate(new TimerTask() {
            int cont = 0;

            @Override
            public void run() {
                System.out.println("Rodada de atualização: " + cont);
                System.out.println(dataHora.format(formatter));
                System.out.println("Utilização dos componentes");
                System.out.println("Processador");
                System.out.println(String.format("Nome: %s\n"
                        + "Uso: %.2f\n", processador.getNome(), processadorUso));

                System.out.println("Memoria Ram");
                System.out.println(String.format("Total: %.2f Gb\n"
                        + "Uso: %.2f Gb\n"
                        + "Disponível: %.2f Gb\n", memoriaTotalGb, memoriaEmUsoGb, memoriaDisponivelGb));

                System.out.println("Disco");

                for (Disco disco : discos) {
                    System.out.println(
                            String.format("Modelo: %s\n"
                                    + "Bytes de leitura: %d \n"
                                    + "Bytes de escrita: %d \n", disco.getModelo(),
                                    disco.getBytesDeLeitura(), disco.getBytesDeEscritas()));
                }

                System.out.println("Rede");
                for (RedeInterface redeInterface : interfaceDeRede) {
                    if (redeInterface.getBytesEnviados() > 0
                            && redeInterface.getBytesRecebidos() > 0) {

                        System.out.println(
                                String.format("Nome da rede: %s\n"
                                        + "IPV4: %s\n"
                                        + "Bytes enviados: %.2f Mb\n"
                                        + "Bytes recebidos: %.2f Mb\n",
                                        redeInterface.getNome(),
                                        redeInterface.getEnderecoIpv4(),
                                        redeInterface.getBytesEnviados() / 1000000.0,
                                        redeInterface.getBytesRecebidos() / 1000000.0));
                    }
                }
                cont++;
            }
        }, 0, 10000);
    }
}
