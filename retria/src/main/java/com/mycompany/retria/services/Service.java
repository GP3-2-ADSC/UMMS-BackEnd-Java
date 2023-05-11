package com.mycompany.retria.services;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.mycompany.retria.DAO.AdministradorDAO;
import com.mycompany.retria.DAO.EspecificacaoComponenteDAO;
import com.mycompany.retria.DAO.MaquinaUltrassomDAO;
import com.mycompany.retria.DAO.MaquinaUltrassomEspecificadaDAO;
import com.mycompany.retria.MODEL.*;

import java.util.List;

public class Service {

    private Administrador adm;
    private MaquinaUltrassom maquinaUltrassom;

    private EspecificacaoComponente especificacaoComponente;

    private MaquinaUltrassomEspecificada maquinaUltrassomEspec;

    Looca looca = new Looca();
    AdministradorDAO admDao = new AdministradorDAO();
    MaquinaUltrassomDAO maquinaUltrassomDAO = new MaquinaUltrassomDAO();

    EspecificacaoComponenteDAO especificacaoComponenteDAO = new EspecificacaoComponenteDAO();

    MaquinaUltrassomEspecificadaDAO maquinaUltrassomEspecificadaDAO = new MaquinaUltrassomEspecificadaDAO();

    List<Disco> discos = looca.getGrupoDeDiscos().getDiscos();
    public void ScriptDeValidacaoDeBanco(String emailAdm, String idProcessador) {
        admDao.setAdministrador(emailAdm, idProcessador);
        maquinaUltrassomDAO.getMaquinaUltrassom(idProcessador,adm.getId_administrador(),adm.getFk_empresa()
                ,looca.getSistema().getSistemaOperacional());

        especificacaoComponenteDAO.getComponenteCpu(looca.getProcessador());
        especificacaoComponenteDAO.getComponenteMemoria(looca.getMemoria());
        
        for (Disco disco : discos) {
            especificacaoComponenteDAO.getComponenteDisco(disco);
        }

        maquinaUltrassomEspecificadaDAO.getMaquiUltassomEspecRAM(
                looca.getMemoria().getTotal(),
                maquinaUltrassom.getIdMaquina(), 
                especificacaoComponente.getId_especificacao_componente()
        );
        
        maquinaUltrassomEspecificadaDAO.getMaquiUltassomEspecCPU(looca.getProcessador().getUso(),
                looca.getProcessador().getFrequencia(),
                maquinaUltrassom.getIdMaquina(), 
                especificacaoComponente.getId_especificacao_componente());

    }

    public double convertBytesToGB(long bytes) {
        double gigabytes = bytes / (1024.0 * 1024.0 * 1024.0);
        return gigabytes;
    }

}
