/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retria.services;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import java.io.IOException;

/**
 *
 * @author Iury
 */
public class SlackM {

 /* private static String webHooksUrl = 
 " https://hooks.slack.com/services/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";*/
    private static String slackChannel = "hospital-estadual-de-vila-alpina--";
    
   
    public static void sendMensagemToSlack(String mensagem) {

        try {
            StringBuilder msgbuilde = new StringBuilder();
            msgbuilde.append(mensagem);

            Payload payload = Payload.builder().channel(slackChannel).text(msgbuilde.toString()).build();

            com.github.seratch.jslack.api.webhook.WebhookResponse wbResp = Slack.getInstance().send(webHooksUrl, payload);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
