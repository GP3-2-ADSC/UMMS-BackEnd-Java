package com.mycompany.retria.validadores;

import com.mycompany.retria.MODEL.Alerta;
import com.mycompany.retria.exception.ValidacaoException;

public class ValidadorDeComponentes {
    private Alerta alerta;
    private ValidacaoCpu validacaoCpu;

    private ValidacaoDisco validacaoDisco;

    private ValidacaoRam validacaoram;

    public void validar(Double usoCpu, Double usoRam, Double usoDisco) throws ValidacaoException {
        validacaoCpu.validar(usoCpu);
        validacaoDisco.validar(usoDisco);
        validacaoram.validar(usoRam);
    }
}
