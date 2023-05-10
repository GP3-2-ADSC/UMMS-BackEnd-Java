/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/*
 * @author laviny.cerqueira
 */

package com.mycompany.log_retria;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    public static void main(String[] args) {
        
        System.out.println("------- Olá, este é Log de erros de Ping - Retria ---------");
        System.out.println("--- Por favor, abra o arquivo chamado - ping_erro_log_retria.txt ---");
        
        String ipAddress = "999.999.999.999"; // O endereço do IP que queremos testar.

        String logNome = "ping_erro_log_retria.txt"; // Nome do arquivo do log que será criado.

        
        try {
            FileWriter writer = new FileWriter(logNome, true); // A file writer permite que criemos um arquivo .txt.
            // temos um boolean que está true, isso pq indica que arquivo deve ser aberto em modo de gravação "append",
            // para adicionar novas infos sem sobreescrever as anteriores.

            // Aqui estamos registrando a data e hora do teste
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); // pega data e hora
            String currentTime = dateFormat.format(new Date()); // devolve a data e a hora formatada.

            // Realiza o teste dp ping
            Process process = Runtime.getRuntime().exec(" ping -c 1 " + ipAddress);// permite interagir com o ambiente de execução.
            int returnCode = process.waitFor(); // waitFor() - aguarda a conclusão do processo criado.
            //O código de retorno é um número inteiro que indica o resultado da execução do processo. 
            //No caso do comando de ping, um código de retorno diferente de zero indica que o teste de ping falhou.
            
            
            // Registra o resultado do teste de ping
            if (returnCode != 0) {
                String errorMessage = new String(process.getErrorStream().readAllBytes());
                writer.write(currentTime + " - Erro ping" + ipAddress + ": " + errorMessage + "\n");
            }

            // Fecha o arquivo de log
            writer.close();
            
            // o catch traz um excessão.
            // caso tenha problema ao escrever arquivo de log, exibe uma mensagem.
            // o programa não para execução, apenas exibe a mensagem e continua.
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao escrever no arquivo de log: " + e.getMessage());
        }
    }
}


