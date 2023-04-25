/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retria.MODEL;

/**
 *
 * @author silvam
 */
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import java.net.URL;
import org.apache.commons.io.FileUtils;

public class Inovacao {

    private String ipRoteador;
    private String mediaPing;
    private String downloadSpeed;

    public void execCommand(String commandLine) throws IOException {
        List<String> retorno = new ArrayList();
        int contador = 0;

        Process p = Runtime.getRuntime().exec(commandLine);

        Scanner scanner = new Scanner(new InputStreamReader(p.getInputStream(), "UTF-8"));

        while (scanner.hasNextLine()) {

            String linha = scanner.nextLine();
            retorno.add(linha);
            System.out.println(linha);
            if (linha.contains("Esgotado o tempo limite do pedido")) {
                contador++;
            }
        }
        scanner.close();

        String fraseFinal = retorno.get(retorno.size() - 1);
        setMediaPing(fraseFinal);

        if (contador == 0) {
            System.out.println("Conexão teste foi bem sucedida!");
        } else if (contador < 4) {
            System.out.println("Conexão efetuada mas houve perdas de pacote! Contate o suporte!");
        } else {
            System.out.println("Não foi possível fazer conexão com fornecedor! Contate o suporte!");
        }

    }

    // Testes futuros
    public void internetTest() throws IOException {
        String url = "http://ipv4.download.thinkbroadband.com/100MB.zip"; // URL do arquivo de teste
        File file = new File("download_teste.zip"); // Nome do arquivo a ser baixado

        // Baixa o arquivo e mede o tempo de download
        long startTime = System.nanoTime();
        FileUtils.copyURLToFile(new URL(url), file);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);

        // Calcula a velocidade da conexão
        long fileSize = file.length();
        double speedMbps = (fileSize / duration) * 8 / 1000000.0;

        downloadSpeed = String.valueOf(speedMbps) + " mbps";

        // Exibe a velocidade da conexão
        System.out.println(downloadSpeed);

        // Exclui o arquivo de teste
        file.delete();
    }

    public void setIpRoteador(String ipv4) {
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(ipv4);

        if (matcher.find()) {
            ipRoteador = matcher.group(1);
        }
    }

    public String getIpRoteador() {

        if (ipRoteador == null) {
            return "Não existe ip cadastrado";
        } else {
            return this.ipRoteador;
        }

    }

    public String getMediaPing() {
        if (mediaPing == null) {
            return "Não foi possível calcular a velocidade média do ping!";
        } else {
            return this.mediaPing;
        }

    }

    private void setMediaPing(String fraseFinal) {
        Pattern pattern = Pattern.compile("M?dia\\s=\\s(\\d+)ms");
        Matcher matcher = pattern.matcher(fraseFinal);

        if (matcher.find()) {
            mediaPing = "Média " + String.valueOf(matcher.group(1)) + " ms";
        }

    }
}
