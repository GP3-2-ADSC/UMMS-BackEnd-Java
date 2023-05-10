package com.mycompany.retria.validadores;

import com.mycompany.retria.exception.ValidacaoException;

public class ValidacaoCpu {

    public void validar(Double usoCpu) throws ValidacaoException {
        if (usoCpu == null) {
            throw new ValidacaoException("Não é possível validar o uso de cpu nulo!!!");
        }
        if (usoCpu < 40.0) {
            System.out.println("Uso da Cpu está dentro dos conformes!");
            // aqui precisamos mandar um alerta
        } else if (usoCpu < 69.0) {
            throw new ValidacaoException("Uso de cpu está em alerta!");
            // aqui preciamos mandar outro alerta
        } else {
            throw new ValidacaoException("Uso de cpu está crítico!!!");
        }

    }









}
