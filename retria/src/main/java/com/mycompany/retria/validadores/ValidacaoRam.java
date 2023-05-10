package com.mycompany.retria.validadores;

import com.github.britooo.looca.api.group.memoria.Memoria;
import com.mycompany.retria.MODEL.MaquinaUltrassomEspecificada;
import com.mycompany.retria.MODEL.MetricaComponente;
import com.mycompany.retria.exception.ValidacaoException;

public class ValidacaoRam {

    private MetricaComponente metricaComponente;

    public void validar(Memoria dados, MaquinaUltrassomEspecificada maquinaUltrassomEspecificada) throws ValidacaoException {
        if (dados == null) {
            throw new ValidacaoException("Não é possível validar memória nula!!!");
        }

        if (dados.getDisponivel() < 1) {
            System.out.println("Uso da Memoria está dentro dos conformes!");
            // aqui precisamos mandar um alerta
        } else if (dados.getEmUso() < 2) {
            throw new ValidacaoException("Uso de Memoria está em alerta!");
            // aqui preciamos mandar outro alerta
        } else {
            throw new ValidacaoException("Uso de Memoria está crítico!!!");
        }

        metricaComponente = new MetricaComponente(null, dados.getDisponivel().doubleValue(),
                null, maquinaUltrassomEspecificada);

    }









}
