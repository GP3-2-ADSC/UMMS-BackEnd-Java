/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.teste.looca;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.discos.Volume;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.rede.RedeInterface;
import com.github.britooo.looca.api.group.rede.RedeInterfaceGroup;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.github.britooo.looca.api.group.temperatura.Temperatura;
import com.github.britooo.looca.api.group.rede.Rede;
import com.github.britooo.looca.api.util.Conversor;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.print.DocFlavor;

/**
 *
 * @author silvam
 */
public class TesteMaquina {

    public static void main(String[] args) throws IOException {
        Looca looca = new Looca();
        Inovacao ping = new Inovacao();
        List<RedeInterface> redes = looca.getRede().getGrupoDeInterfaces().getInterfaces();


        RedeInterface redeAtual = redes.stream().filter(r -> r.getBytesRecebidos() > 0 && r.getBytesEnviados() > 0).findFirst().get();

        long bytesRec1 = redeAtual.getBytesRecebidos();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long bytesRec2 = redeAtual.getBytesRecebidos();
        System.out.println(bytesRec2 - bytesRec1);

    }
    public static Double convertBytesToGB(long bytes) {
        return bytes / (1024.0 * 1024.0 * 1024.0);
    }
}
