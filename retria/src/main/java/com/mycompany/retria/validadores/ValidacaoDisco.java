package com.mycompany.retria.validadores;

import com.mycompany.retria.exception.ValidacaoException;

public class ValidacaoDisco {

    public void validar(Double usoDisco) throws ValidacaoException {
        if (usoDisco == null) {
            throw new ValidacaoException("Não é possível validar o uso de cpu nulo!!!");
        }
        if (usoDisco < 10) {
            System.out.println("Uso da Cpu está dentro dos conformes!");
            // aqui precisamos mandar um alerta
        } else if (usoDisco < 20) {
            throw new ValidacaoException("Uso de cpu está em alerta!");
            // aqui preciamos mandar outro alerta
        } else {
            throw new ValidacaoException("Uso de cpu está crítico!!!");
        }
    }









}
