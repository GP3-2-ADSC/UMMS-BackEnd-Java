/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

 /*
 * @author laviny.cerqueira
 */
package com.mycompany.log_retria;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.sistema.Sistema;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {

     private static String getLogFileName() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(new Date());
        return "ping_erro_log_Retria_" + currentDate + ".txt";
    }

    private static void escreverCabecalho() throws IOException {
        Looca looca = new Looca();
        Sistema sistema = looca.getSistema();

        try (FileWriter writer = new FileWriter(getLogFileName(), true)) {
            writer.write("---- Informações do Sistema ----\n");
            writer.write("Fabricante: " + sistema.getFabricante() + "\n");
            writer.write("Sistema Operacional: " + sistema.getSistemaOperacional() + "\n");
            writer.write("Arquitetura: " + sistema.getArquitetura() + "\n");
            writer.write("Permissões: " + sistema.getPermissao() + "\n");
            writer.write("Inicializado: " + sistema.getInicializado() + "\n");
            writer.write("--------------------------------\n\n");
            writer.write("---- Soluções para resolver erro Ping ----\n");
            writer.write("- Verifique se o endereço IP está correto;\n");
            writer.write("- Verifique a conexão de rede;\n");
            writer.write("- Verifique se o destino está acessível;\n");
            writer.write("- Verifique as configurações de firewall;\n");
            writer.write("------------------------------------------\n\n");
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("------- Olá, este é Log de erros de Ping - Retria ---------");
        System.out.println("--- Por favor, abra o arquivo chamado - ping_erro_log_retria.txt ---");

        String ipAddress = "192.0.2.0"; 

        boolean arquivoExiste = new File(getLogFileName()).exists();
        if (!arquivoExiste) {
            escreverCabecalho();
        }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String currentTime = dateFormat.format(new Date());

            Process process = Runtime.getRuntime().exec("ping -c 1 " + ipAddress);
            int returnCode = process.waitFor();

            if (returnCode != 0) {
                String errorMessage = new String(process.getErrorStream().readAllBytes());

                try (FileWriter writer = new FileWriter(getLogFileName(), true)) {
                    writer.write(currentTime + " - Erro ping " + ipAddress + ": " + errorMessage + "\n");
                    writer.write("\n");
                }
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao escrever no arquivo de log: " + e.getMessage());
        }
    }
}
