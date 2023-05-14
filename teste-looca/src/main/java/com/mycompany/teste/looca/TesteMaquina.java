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
        List<Disco> discos = looca.getGrupoDeDiscos().getDiscos();

        for (Disco disco : discos) {
            System.out.println(disco.getTamanho() / (1024.0 * 1024.0 * 1024.0));
        }
    }
    public Double convertBytesToGB(long bytes) {
        return bytes / (1024.0 * 1024.0 * 1024.0);
    }
}
