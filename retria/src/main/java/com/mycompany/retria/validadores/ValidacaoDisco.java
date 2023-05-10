package com.mycompany.retria.validadores;

import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.mycompany.retria.MODEL.MaquinaUltrassomEspecificada;
import com.mycompany.retria.MODEL.MetricaComponente;
import com.mycompany.retria.exception.ValidacaoException;

import java.util.List;

public class ValidacaoDisco {

    private MetricaComponente metricaComponente;

    public void validar(List<Disco> dados, MaquinaUltrassomEspecificada maquinaUltrassomEspecificada) throws ValidacaoException {

        if (dados.size() == 0) {
            throw new ValidacaoException("Não é possível validar discos de uma lista vazia!!!");
        }

        // VERIFICAR VALIDAÇÕES!!!!
        for (Disco disco : dados) {
            if (disco.getBytesDeEscritas() < 10) {
                System.out.println("Uso do Disco está dentro dos conformes!");
                // aqui precisamos mandar um alerta
            } else if (disco.getBytesDeEscritas() < 20) {
                throw new ValidacaoException("Uso do Disco está em alerta!");
                // aqui preciamos mandar outro alerta
            } else {
                throw new ValidacaoException("Uso do Disco está crítico!!!");
            }
            metricaComponente = new MetricaComponente(null, disco.getBytesDeEscritas().doubleValue(),
                    null, maquinaUltrassomEspecificada);
        }


    }


}
