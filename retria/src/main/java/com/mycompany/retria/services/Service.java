package com.mycompany.retria.services;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.mycompany.retria.DAO.AdministradorDAO;
import com.mycompany.retria.DAO.EspecificacaoComponenteDAO;
import com.mycompany.retria.DAO.MaquinaUltrassomDAO;
import com.mycompany.retria.DAO.MaquinaUltrassomEspecificadaDAO;
import com.mycompany.retria.MODEL.*;
import com.mycompany.retria.exception.ValidacaoException;
import com.mycompany.retria.validadores.ValidadorDeComponentes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Service {
    private Administrador adm;
    private MaquinaUltrassom maquinaUltrassom;
    private List<EspecificacaoComponente> especificacaoComponente = new ArrayList<>();
    private List<MaquinaUltrassomEspecificada> maquinaUltrassomEspec = new ArrayList<>();

    private Looca looca = new Looca();



    public void scriptDeValidacaoDeBanco(String emailAdm,String senha) {

        AdministradorDAO admDao = new AdministradorDAO();

        MaquinaUltrassomDAO maquinaUltrassomDAO = new MaquinaUltrassomDAO();

        EspecificacaoComponenteDAO especificacaoComponenteDAO = new EspecificacaoComponenteDAO();

        MaquinaUltrassomEspecificadaDAO maquinaUltrassomEspecificadaDAO = new MaquinaUltrassomEspecificadaDAO();


        List<Disco> discos = looca.getGrupoDeDiscos().getDiscos();

        System.out.println("Estou na service!!!!!");

        adm = admDao.setAdministrador(emailAdm, senha);
        maquinaUltrassom = maquinaUltrassomDAO.getMaquinaUltrassom(looca.getProcessador().getId(), adm.getId_administrador(), adm.getFk_empresa(),
                looca.getSistema().getSistemaOperacional());

        especificacaoComponente.add(especificacaoComponenteDAO.getComponenteCpu(looca.getProcessador()));
        especificacaoComponente.add(especificacaoComponenteDAO.getComponenteMemoria(looca.getMemoria()));

        for (Disco disco : discos) {
            System.out.println("VOCÊ TEM " + discos.size() + " discos\n");
            System.out.println("DISCO ATUAL\n");
            System.out.println(disco);
            especificacaoComponente.add(especificacaoComponenteDAO.getComponenteDisco(disco));
        }

        maquinaUltrassomEspec.add(maquinaUltrassomEspecificadaDAO.getMaquiUltassomEspecCPU(looca.getProcessador().getUso(),
                maquinaUltrassom.getIdMaquina(),
                especificacaoComponente.stream().filter(e -> e.getTipoComponente().equals(TipoComponente.CPU))
                        .findFirst().get().getId_especificacao_componente()
        ));

        maquinaUltrassomEspec.add(maquinaUltrassomEspecificadaDAO.getMaquiUltassomEspecRAM(
                convertBytesToGB(looca.getMemoria().getTotal()),
                maquinaUltrassom.getIdMaquina(),
                especificacaoComponente.stream().filter(e -> e.getTipoComponente().equals(TipoComponente.RAM))
                        .findFirst().get().getId_especificacao_componente()
        ));

        for (Disco disco : discos) {
            if (disco == null) {
                break;
            }
            maquinaUltrassomEspec.add(maquinaUltrassomEspecificadaDAO.getMaquiUltassomEspecDISCO(convertBytesToGB(disco.getTamanho()),
                    maquinaUltrassom.getIdMaquina(),
                    especificacaoComponente.stream().filter(e -> e.getTipoComponente().equals(TipoComponente.DISCO))
                            .findFirst().get().getId_especificacao_componente()
            ));
        }

    }

    public void validarMetrica() throws ValidacaoException {
        List<Disco> discos = looca.getGrupoDeDiscos().getDiscos();
        ValidadorDeComponentes validadorDeComponentes = new ValidadorDeComponentes();
        Inovacao inovacao = new Inovacao();

        Integer fkCpu = especificacaoComponente.stream()
                .filter(e -> e.getTipoComponente().equals(TipoComponente.CPU))
                .findFirst().get().getId_especificacao_componente();

        Integer fkRam = especificacaoComponente.stream()
                .filter(e -> e.getTipoComponente().equals(TipoComponente.RAM))
                .findFirst().get().getId_especificacao_componente();

        Integer fkDisco = especificacaoComponente.stream()
                .filter(e -> e.getTipoComponente().equals(TipoComponente.DISCO))
                .findFirst().get().getId_especificacao_componente();

        new Timer().scheduleAtFixedRate(new TimerTask() {
            public void run() {
                try {
                    validadorDeComponentes.validarCpu(looca.getProcessador(), fkCpu);
                    validadorDeComponentes.validarRam(looca.getMemoria(), fkRam);
                    validadorDeComponentes.validarDisco(discos, fkDisco);

                } catch (ValidacaoException e) {
                    throw new RuntimeException(e);
                }

                inovacao.setIpRoteador(looca.getRede().getGrupoDeInterfaces().getInterfaces().get(0).getEnderecoIpv4().toString());

                try {
                    inovacao.execCommand("ping " + inovacao.getIpRoteador());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }, 0, 10000);

    }


    public Double convertBytesToGB(long bytes) {
        return bytes / (1024.0 * 1024.0 * 1024.0);
    }
}
