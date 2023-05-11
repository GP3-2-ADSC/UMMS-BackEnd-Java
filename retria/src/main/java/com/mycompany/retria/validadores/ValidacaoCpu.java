package com.mycompany.retria.validadores;

import com.github.britooo.looca.api.group.processador.Processador;
import com.mycompany.retria.MODEL.MaquinaUltrassomEspecificada;
import com.mycompany.retria.MODEL.MetricaComponente;
import com.mycompany.retria.exception.ValidacaoException;

public class ValidacaoCpu {

    private MetricaComponente metricaComponente;

    public void validar(Processador dados, MaquinaUltrassomEspecificada maquinaUltrassomEspecificada) throws ValidacaoException {
        // cria a metrica, faz as validações e envia pro banco de acordo com o que ocorreu
        // as exception são para o log
        if (dados == null) {
            throw new ValidacaoException("Não é possível validar o uso de cpu nulo!!!");
        }

        if (dados.getUso() < 40.0) {
            System.out.println("Uso da Cpu está dentro dos conformes!");
            // aqui precisamos mandar um alerta
        } else if (dados.getUso() < 69.0) {
            throw new ValidacaoException("Uso de cpu está em alerta!");
            // aqui preciamos mandar outro alerta
        } else {
            throw new ValidacaoException("Uso de cpu está crítico!!!");
        }

        metricaComponente = new MetricaComponente(null, dados.getUso(),
                dados.getFrequencia().doubleValue(), maquinaUltrassomEspecificada);
    }









}
