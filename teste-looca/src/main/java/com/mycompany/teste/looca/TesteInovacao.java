/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.teste.looca;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 *
 * @author silvam
 */
public class TesteInovacao {

    public synchronized static String execCommand() throws IOException {

        String result;
        final String commandLine = "ping google.com.br";
        Process p;
        BufferedReader input;
        StringBuffer cmdOut = new StringBuffer();
        String lineOut = null;
        List<String> retorno = new ArrayList();

        try {
            p = Runtime.getRuntime().exec(commandLine);

            input = new BufferedReader(new InputStreamReader(p.getInputStream()));

            while ((lineOut = input.readLine()) != null) {

                cmdOut.append(lineOut);
                cmdOut.append("\n");
                retorno.add(lineOut);
            }

            result = cmdOut.toString();

            input.close();
        } catch (IOException e) {
            throw new IOException(String.format("Falha ao executar comando %s. Erro: %s", commandLine, e.toString()));
        }

        // Se não executou com sucesso, lança a falha
        return result;

    }
}
