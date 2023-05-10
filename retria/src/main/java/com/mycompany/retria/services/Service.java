package com.mycompany.retria.services;

import com.github.britooo.looca.api.core.Looca;
import com.mycompany.retria.DAO.AdministradorDAO;
import com.mycompany.retria.DAO.MaquinaUltrassomDAO;
import com.mycompany.retria.DAO.MaquinaUltrassomEspecificadaDAO;
import com.mycompany.retria.MODEL.*;

public class Service {

    private Administrador adm;
    private MaquinaUltrassom maquinaUltrassom;

    private EspecificacaoComponente especificacaoComponente;

    private MaquinaUltrassomEspecificada maquinaUltrassomEspec;

    Looca looca = new Looca();
    AdministradorDAO admDao = new AdministradorDAO();
    MaquinaUltrassomDAO maquinaUltrassomDAO = new MaquinaUltrassomDAO();

    MaquinaUltrassomEspecificadaDAO maquinaUltrassomEspecificadaDAO = new MaquinaUltrassomEspecificadaDAO();
    public void ScriptDeValidacaoDeBanco(String emailAdm, String idProcessador) {
        admDao.setAdministrador(emailAdm, idProcessador);
        maquinaUltrassomDAO.getMaquinaUltrassom(idProcessador,adm.getId_administrador(),adm.getFk_empresa()
                ,looca.getSistema().getSistemaOperacional());
        maquinaUltrassomEspecificadaDAO.adicionar();




    }

}
