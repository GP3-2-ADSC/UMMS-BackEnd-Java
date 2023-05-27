/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retria.MODEL;

/**
 *
 * @author silvam
 */
import com.github.britooo.looca.api.core.Looca;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

public class TesteConexaoComFornecedor {

    private String ipRoteador;
    private String mediaPing;
    private String downloadSpeed;

    public void execCommand(String commandLine) throws IOException {
        List<String> retorno = new ArrayList();
        int contador = 0;

        Process p = Runtime.getRuntime().exec(commandLine);

        Scanner scanner = new Scanner(new InputStreamReader(p.getInputStream()));

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
            System.out.println("Conexão efetuada mas houve perdas de pacote!");
        } else {
            System.out.println("Não foi possível fazer conexão com fornecedor!");
        }
    }

    public void execLog(String ip, String adm, String status) {
        Looca looca = new Looca();
        String ipAddress = ip; // O endereço do IP que queremos testar.

        LocalDate diaAtual = LocalDate.now();

        String logNome = formularNomeDoArquivoLog(diaAtual.toString()); // Nome do arquivo do log que será criado.

        try {
            File file = new File(logNome);
            if(file.exists()) {
                FileWriter writer = new FileWriter(logNome,true);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String currentTime = dateFormat.format(new Date());
                String sistemaOperacional = looca.getSistema().getSistemaOperacional();
                String commandLine;

                if (sistemaOperacional.equalsIgnoreCase("windows")) {
                    System.out.println("SO ----> WINDOWS");
                    commandLine = "ping " + ipAddress;
                } else {
                    System.out.println("SO ----> LINUX OU MACOS");
                    commandLine = "ping c -4 " + ipAddress;
                }

                Process process = Runtime.getRuntime().exec(commandLine);
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                int returnCode = process.waitFor();

                if (returnCode == 1) {
                    System.out.println("Houve erro ao executar o ping!!!!");
                    StringBuilder errorBuilder = new StringBuilder();
                    String linha;
                    System.out.println("CODE ---- 1");
                    while ((linha = reader.readLine()) != null) {
                        errorBuilder.append(errorBuilder).append("\n");
                    }
                    reader.close();

                    String errorMessage = errorBuilder.toString();

                    writer.write(String.format("""
                            Data = %s
                            Código de retorno = %d
                            Error = %s
                            
                            """,currentTime,returnCode," - Erro ping " + ipAddress + " : " + errorMessage + "\n"));
                    writer.close();
                } else if (returnCode == 2) {
                    System.out.println("CODE ---- 2");
                    String errorMessage = "Erro de execução de comando!\n" +
                            "Possíveis causas: Operação não permitada, erro na resolução do host e problemas de " +
                            "conexao de rede";

                    writer.write(String.format("""
                            Data = %s
                            Código de retorno = %d
                            Error = %s
                            
                            """,currentTime,returnCode," - Erro ping " + ipAddress + " : " + errorMessage + "\n"));
                    writer.close();
                } else {
                    System.out.println("Comando executado com sucesso!");
                }

            } else {
                System.out.println("TO NO ELSE!");
                FileWriter writer = new FileWriter(logNome);
                String cabecalho = formularCabecalhoLog(logNome,diaAtual.toString(),adm,status);
                writer.write(cabecalho);
                writer.close();
                execLog(ip,adm,status);
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao escrever no arquivo de log: " + e.getMessage());
        }
    }

    public String formularCabecalhoLog(String nomeDoArquivo, String diaAtual, String adm, String status) {
        Looca looca = new Looca();
        return String.format("""
                <------- Olá, este é Log de erros de Ping - Retria --------->
                
                ================================================================================
                Arquivo = %s
                Data = %s
                ================================================================================
                
                <------- Informações da máquina --------->
                
                Numero serial = %s
                Administrador = %s
                Sistema operacional = %s
                Status da maquina = %s
                
                ================================================================================
                Processador ----->
                %s
                
                Memória ram ----->                   
                %s
                
                Disco -----> 
                %s
                ================================================================================
                <------- OCORRÊNCIAS --------->
                
                
                """,nomeDoArquivo,diaAtual,looca.getProcessador().getId(),adm,looca.getSistema().getSistemaOperacional(),
                status, looca.getProcessador().toString(),looca.getMemoria().toString(),
                looca.getGrupoDeDiscos().getVolumes().get(0).toString());
    }

    public String formularNomeDoArquivoLog(String dataHoraFormatada) {
        String nomeArquivo = dataHoraFormatada;
        return nomeArquivo.replaceAll("[^a-zA-Z0-9-_]", "_") + " log-de-erros";
    }

    public void setIpRoteador(String ipv4) {
        Pattern pattern = Pattern.compile("\\[(.*?)]");
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
