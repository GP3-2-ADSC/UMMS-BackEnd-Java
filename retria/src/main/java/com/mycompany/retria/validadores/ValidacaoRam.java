package com.mycompany.retria.validadores;

import com.mycompany.retria.exception.ValidacaoException;

public class ValidacaoRam {

    public void validar(Double usoRam) throws ValidacaoException {
        if (usoRam == null) {
            throw new ValidacaoException("Não é possível validar o uso de cpu nulo!!!");
        }
        if (usoRam < 1) {
            System.out.println("Uso da Cpu está dentro dos conformes!");
            // aqui precisamos mandar um alerta
        } else if (usoRam < 2) {
            throw new ValidacaoException("Uso de cpu está em alerta!");
            // aqui preciamos mandar outro alerta
        } else {
            throw new ValidacaoException("Uso de cpu está crítico!!!");
        }

    }









}
