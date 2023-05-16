package com.mycompany.retria.validadores;

import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.Volume;
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
        metricaComponente = new MetricaComponente(null, usoProcessador, fkMaquinaUltrassom);
        Integer fkMetricaComponente = metricaComponenteDAO.setMetrica(metricaComponente);

        if (dados == null) {
            throw new ValidacaoException("Não é possível validar o uso de cpu nulo!!!");
        }

        if (usoProcessador < 35.0) {
            System.out.println("Uso dentro dos conformes!");
        } else if (usoProcessador < 40.0) {
            alertaDAO.setAlerta(new Alerta(null, 1, fkMetricaComponente));
            throw new ValidacaoException("CPU está com nível de uso em - ALERTA!");
        } else if (usoProcessador < 45.0) {
            alertaDAO.setAlerta(new Alerta(null, 2, fkMetricaComponente));
            throw new ValidacaoException("CPU está com nível de uso em - PERIGOSO! Contate o suporte!");
        } else {
            alertaDAO.setAlerta(new Alerta(null, 3, fkMetricaComponente));
            throw new ValidacaoException("CPU está com nível de uso em - CRÍTICO! Contate o suporte IMEDIATAMENTE!");
        }
    }

    public void validarRam(Memoria dados, Integer fkMaquinaUltrassom) throws ValidacaoException {
        Double memRamTotal = service.convertBytesToGB(dados.getTotal());
        Double usoMemoria = service.convertBytesToGB(dados.getEmUso());
        Double porcentagemDeRam = (usoMemoria * 100) / memRamTotal;

        System.out.println("USO DE RAM " + usoMemoria);
        metricaComponente = new MetricaComponente(null, porcentagemDeRam, fkMaquinaUltrassom);
        Integer fkMetricaComponente = metricaComponenteDAO.setMetrica(metricaComponente);

        if (dados == null) {
            throw new ValidacaoException("Não é possível validar memória nula!!!");
        }
        if (porcentagemDeRam < 49.0) {
            System.out.println("Uso de ram dentro dos conformes!");
        } else if (porcentagemDeRam < 56.0) {
            alertaDAO.setAlerta(new Alerta(null, 1, fkMetricaComponente));
            throw new ValidacaoException("RAM está com nível de uso em - ALERTA!");
        } else if (porcentagemDeRam < 63.0) {
            alertaDAO.setAlerta(new Alerta(null, 2, fkMetricaComponente));
            throw new ValidacaoException("RAM está com nível de uso em - PERIGOSO! Contate o suporte!");
        } else {
            alertaDAO.setAlerta(new Alerta(null, 3, fkMetricaComponente));
            throw new ValidacaoException("RAM está com nível de uso em - CRÍTICO! Contate o suporte IMEDIATAMENTE!");
        }
    }

    public void validarDisco(Volume dados, Integer fkMaquinaUltrassom) throws ValidacaoException {
        Double emUso = service.convertBytesToGB(dados.getTotal() - dados.getDisponivel());
        Double porcentagemDeUsoDisc = (emUso * 100) / service.convertBytesToGB(dados.getTotal());
        metricaComponente = new MetricaComponente(null, porcentagemDeUsoDisc, fkMaquinaUltrassom);
        Integer fkMetricaComponente = metricaComponenteDAO.setMetrica(metricaComponente);

        if (dados == null) {
            throw new ValidacaoException("Não é possível validar discos de uma lista vazia!!!");
        }

        if (porcentagemDeUsoDisc < 56.0) {
            System.out.println("Uso de DISCO dentro dos conformes!");
        } else if (porcentagemDeUsoDisc < 64.0) {
            alertaDAO.setAlerta(new Alerta(null, 1, fkMetricaComponente));
            throw new ValidacaoException("DISCO está com nível de uso em - ALERTA!");
        } else if (porcentagemDeUsoDisc < 72.0) {
            alertaDAO.setAlerta(new Alerta(null, 2, fkMetricaComponente));
            throw new ValidacaoException("DISCO está com nível de uso em - PERIGOSO! Contate o suporte!");
        } else {
            alertaDAO.setAlerta(new Alerta(null, 3, fkMetricaComponente));
            throw new ValidacaoException("DISCO está com nível de uso em - CRÍTICO! Contate o suporte IMEDIATAMENTE!");
        }
    }

}


