package com.mycompany.retria.validadores;

import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.mycompany.retria.MODEL.Alerta;
import com.mycompany.retria.MODEL.MaquinaUltrassomEspecificada;
import com.mycompany.retria.exception.ValidacaoException;

import java.util.List;

public class ValidadorDeComponentes {
    private Alerta alerta;
    private ValidacaoCpu validacaoCpu;

    private ValidacaoDisco validacaoDisco;

    private ValidacaoRam validacaoram;


    public void validar(Processador processador, Memoria memoria, List<Disco> discos,
                        MaquinaUltrassomEspecificada maquinaUltrassomEspecificada) throws ValidacaoException {

        // Provavel que aqui a gente tenha que criar as metricas de cada componente
        validacaoCpu.validar(processador, maquinaUltrassomEspecificada);
        validacaoDisco.validar(discos,maquinaUltrassomEspecificada);
        validacaoram.validar(memoria,maquinaUltrassomEspecificada);
    }
}
