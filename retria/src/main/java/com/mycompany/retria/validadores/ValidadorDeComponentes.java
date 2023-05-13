package com.mycompany.retria.validadores;

import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.mycompany.retria.DAO.AlertaDAO;
import com.mycompany.retria.DAO.MetricaComponenteDAO;
import com.mycompany.retria.MODEL.Alerta;
import com.mycompany.retria.MODEL.MaquinaUltrassomEspecificada;
import com.mycompany.retria.MODEL.MetricaComponente;
import com.mycompany.retria.exception.ValidacaoException;
import com.mycompany.retria.services.Service;

import java.util.List;

public class ValidadorDeComponentes {

    private AlertaDAO alertaDAO = new AlertaDAO();

    private MetricaComponenteDAO metricaComponenteDAO = new MetricaComponenteDAO();

    private Service service = new Service();

    private MetricaComponente metricaComponente;

    private Alerta alerta;

    public void validarCpu(Processador dados, Integer fkEspecificacaoComponente) throws ValidacaoException {
        Double usoProcessador = dados.getUso();
        metricaComponente = new MetricaComponente(null,usoProcessador,fkEspecificacaoComponente);
        Integer fkMetricaComponente= metricaComponenteDAO.setMetrica(metricaComponente);

        if (dados == null) {
            throw new ValidacaoException("Não é possível validar o uso de cpu nulo!!!");
        }

        if (usoProcessador >= 35.0 && usoProcessador < 40.0) {
            alertaDAO.setAlerta(new Alerta(null,1,fkMetricaComponente));
            throw new ValidacaoException("Recomendo definir o limite de " +
                    "alerta em torno de 70% a 80% do uso máximo aceitável");
        } else if (usoProcessador < 45.0) {
            alertaDAO.setAlerta(new Alerta(null,2,fkMetricaComponente));
            throw new ValidacaoException("(Recomendo definir o limite de perigo em torno de 80% a 90% do uso\n" +
                    "máximo aceitável");
        } else {
            alertaDAO.setAlerta(new Alerta(null,3,fkMetricaComponente));
            throw new ValidacaoException("Recomendo definir o limite crítico em 90% a 100% do uso máximo\n" +
                    "aceitável");
        }
    }

    public void validarRam(Memoria dados, Integer fkEspecificacaoComponente) throws ValidacaoException {
        Double usoMemoria = service.convertBytesToGB(dados.getEmUso());
        metricaComponente = new MetricaComponente(null,usoMemoria,fkEspecificacaoComponente);
        Integer fkMetricaComponente= metricaComponenteDAO.setMetrica(metricaComponente);

        if (dados == null) {
            throw new ValidacaoException("Não é possível validar memória nula!!!");
        }

        if (usoMemoria >= 49.0 && usoMemoria < 56.0) {
            alertaDAO.setAlerta(new Alerta(null,1,fkMetricaComponente));
            throw new ValidacaoException("Recomendo definir o limite de " +
                    "alerta em torno de 70% a 80% do uso máximo aceitável");
        } else if (usoMemoria < 63.0) {
            alertaDAO.setAlerta(new Alerta(null,2,fkMetricaComponente));
            throw new ValidacaoException("(Recomendo definir o limite de perigo em torno de 80% a 90% do uso\n" +
                    "máximo aceitável");
        } else {
            alertaDAO.setAlerta(new Alerta(null,3,fkMetricaComponente));
            throw new ValidacaoException("Recomendo definir o limite crítico em 90% a 100% do uso máximo\n" +
                    "aceitável");
        }
    }

    public void validarDisco(List<Disco> dados, Integer fkEspecificacaoComponente) throws ValidacaoException {

        if (dados.size() == 0) {
            throw new ValidacaoException("Não é possível validar discos de uma lista vazia!!!");
        }

        for (Disco disco : dados) {
            Double usoDisco = service.convertBytesToGB(disco.getBytesDeEscritas());
            metricaComponente = new MetricaComponente(null,usoDisco,fkEspecificacaoComponente);
            Integer fkMetricaComponente= metricaComponenteDAO.setMetrica(metricaComponente);

            if (usoDisco >= 56.0 && usoDisco < 64.0) {
                alertaDAO.setAlerta(new Alerta(null,1,fkMetricaComponente));
                throw new ValidacaoException("Recomendo definir o limite de " +
                        "alerta em torno de 70% a 80% do uso máximo aceitável");
            } else if (usoDisco < 72.0) {
                alertaDAO.setAlerta(new Alerta(null,1,fkMetricaComponente));
                throw new ValidacaoException("(Recomendo definir o limite de perigo em torno de 80% a 90% do uso\n" +
                        "máximo aceitável");
            } else {
                alertaDAO.setAlerta(new Alerta(null,1,fkMetricaComponente));
                throw new ValidacaoException("Recomendo definir o limite crítico em 90% a 100% do uso máximo\n" +
                        "aceitável");
            }
        }

    }

}
