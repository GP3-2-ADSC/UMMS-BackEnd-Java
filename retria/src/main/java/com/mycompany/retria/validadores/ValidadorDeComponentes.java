package com.mycompany.retria.validadores;

import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.mycompany.retria.DAO.AlertaDAO;
import com.mycompany.retria.DAO.MetricaComponenteDAO;
import com.mycompany.retria.MODEL.Alerta;
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

    public void validarCpu(Processador dados, Integer fkMaquinaUltrassom) throws ValidacaoException {
        Double usoProcessador = dados.getUso();
        metricaComponente = new MetricaComponente(null,usoProcessador, fkMaquinaUltrassom);
        Integer fkMetricaComponente=metricaComponenteDAO.setMetrica(metricaComponente);

        if (dados == null) {
            throw new ValidacaoException("Não é possível validar o uso de cpu nulo!!!");
        }

        if (usoProcessador < 35.0) {
            System.out.println("Uso dentro dos conformes!");
        } else if (usoProcessador < 40.0) {
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

    public void validarRam(Memoria dados, Integer fkMaquinaUltrassom) throws ValidacaoException {
        Double memRamTotal = service.convertBytesToGB(dados.getTotal());
        Double usoMemoria = service.convertBytesToGB(dados.getEmUso());
        Double porcentagemDeRam = (usoMemoria * 100) / memRamTotal;

        System.out.println("USO DE RAM " + usoMemoria);
        metricaComponente = new MetricaComponente(null,porcentagemDeRam, fkMaquinaUltrassom);
        Integer fkMetricaComponente= metricaComponenteDAO.setMetrica(metricaComponente);

        if (dados == null) {
            throw new ValidacaoException("Não é possível validar memória nula!!!");
        }
        if (porcentagemDeRam < 49.0) {
            System.out.println("Uso de ram dentro dos conformes!");
        } else if (porcentagemDeRam < 56.0) {
            alertaDAO.setAlerta(new Alerta(null,1,fkMetricaComponente));
            throw new ValidacaoException("Recomendo definir o limite de " +
                    "alerta em torno de 70% a 80% do uso máximo aceitável");
        } else if (porcentagemDeRam < 63.0) {
            alertaDAO.setAlerta(new Alerta(null,2,fkMetricaComponente));
            throw new ValidacaoException("(Recomendo definir o limite de perigo em torno de 80% a 90% do uso\n" +
                    "máximo aceitável");
        } else {
            alertaDAO.setAlerta(new Alerta(null,3,fkMetricaComponente));
            throw new ValidacaoException("Recomendo definir o limite crítico em 90% a 100% do uso máximo\n" +
                    "aceitável");
        }
    }

    public void validarDisco(List<Disco> dados, Integer fkMaquinaUltrassom) throws ValidacaoException {

        if (dados.size() == 0) {
            throw new ValidacaoException("Não é possível validar discos de uma lista vazia!!!");
        }

        for (Disco disco : dados) {
            //Double usoDiscTotal = service.convertBytesToGB(disco.getTamanho());
            //Double DiscEmUso = service.convertBytesToGB();
            Double usoDisco = service.convertBytesToGB(disco.getBytesDeEscritas());
            metricaComponente = new MetricaComponente(null,usoDisco, fkMaquinaUltrassom);
            Integer fkMetricaComponente= metricaComponenteDAO.setMetrica(metricaComponente);

            if (usoDisco < 56.0) {
                System.out.println("Uso de DISCO dentro dos conformes!");
            }
            else if ( usoDisco < 64.0) {
                alertaDAO.setAlerta(new Alerta(null,1,fkMetricaComponente));
                throw new ValidacaoException("Recomendo definir o limite de " +
                        "alerta em torno de 70% a 80% do uso máximo aceitável");
            } else if (usoDisco < 72.0) {
                alertaDAO.setAlerta(new Alerta(null,2,fkMetricaComponente));
                throw new ValidacaoException("(Recomendo definir o limite de perigo em torno de 80% a 90% do uso\n" +
                        "máximo aceitável");
            } else {
                alertaDAO.setAlerta(new Alerta(null,3,fkMetricaComponente));
                throw new ValidacaoException("Recomendo definir o limite crítico em 90% a 100% do uso máximo\n" +
                        "aceitável");
            }
        }

    }

}
