package com.mycompany.retria.services;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.Volume;
import com.mycompany.retria.DAO.AdministradorDAO;
import com.mycompany.retria.DAO.EspecificacaoComponenteDAO;
import com.mycompany.retria.DAO.MaquinaUltrassomDAO;
import com.mycompany.retria.DAO.MaquinaUltrassomEspecificadaDAO;
import com.mycompany.retria.MODEL.*;
import com.mycompany.retria.exception.ValidacaoException;
import com.mycompany.retria.validadores.ValidadorDeComponentes;

import java.io.IOException;
import java.util.*;

public class Service {
    private Administrador adm;
    private MaquinaUltrassom maquinaUltrassom;
    private List<EspecificacaoComponente> especificacaoComponente = new ArrayList<>();
    private List<MaquinaUltrassomEspecificada> maquinaUltrassomEspec = new ArrayList<>();

    private Looca looca = new Looca();


    public void scriptDeValidacaoDeBanco(String emailAdm, String senha) {

        AdministradorDAO admDao = new AdministradorDAO();

        MaquinaUltrassomDAO maquinaUltrassomDAO = new MaquinaUltrassomDAO();

        EspecificacaoComponenteDAO especificacaoComponenteDAO = new EspecificacaoComponenteDAO();

        MaquinaUltrassomEspecificadaDAO maquinaUltrassomEspecificadaDAO = new MaquinaUltrassomEspecificadaDAO();


        List<Volume> discos = looca.getGrupoDeDiscos().getVolumes();

        System.out.println("Estou na service!!!!!");

        adm = admDao.setAdministrador(emailAdm, senha);
        maquinaUltrassom = maquinaUltrassomDAO.getMaquinaUltrassom(looca.getProcessador().getId(), adm.getId_administrador(), adm.getFk_empresa(),
                looca.getSistema().getSistemaOperacional());

        especificacaoComponente.add(especificacaoComponenteDAO.getComponenteCpu(looca.getProcessador()));
        especificacaoComponente.add(especificacaoComponenteDAO.getComponenteMemoria(looca.getMemoria()));

        if (discos.size() > 1) {
            String volume = discos.get(0).getVolume();
            String UUID = discos.get(0).getUUID();
            Double volumeTotal = convertBytesToGB(discos.stream().filter(d -> !Objects.equals(d.getVolume(), volume)).mapToLong(Volume::getTotal).sum());
            System.out.println("Volume total " + volumeTotal);
            Double disponivelTotal = convertBytesToGB(discos.stream().filter(d -> !Objects.equals(d.getVolume(), volume)).mapToLong(Volume::getDisponivel).sum());
            System.out.println("Disponivel total " + disponivelTotal);
            especificacaoComponente.add(especificacaoComponenteDAO.getComponenteDisco(UUID,volumeTotal,disponivelTotal));
        } else {
            especificacaoComponente.add(especificacaoComponenteDAO.getComponenteDisco(discos.get(0).getUUID(),
                    convertBytesToGB(discos.get(0).getTotal()),convertBytesToGB(discos.get(0).getDisponivel())));
        }

        maquinaUltrassomEspec.add(maquinaUltrassomEspecificadaDAO.getMaquiUltassomEspecCPU(100.0,
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


        String volume = discos.get(0).getVolume();
        maquinaUltrassomEspec.add(maquinaUltrassomEspecificadaDAO.getMaquiUltassomEspecDISCO(
                convertBytesToGB(discos.stream().filter(d -> !Objects.equals(d.getVolume(), volume))
                        .mapToLong(Volume::getTotal).sum()),
                maquinaUltrassom.getIdMaquina(), especificacaoComponente.stream()
                        .filter(e -> e.getTipoComponente().equals(TipoComponente.DISCO))
                        .findFirst().get().getId_especificacao_componente()

        ));
    }


    public void validarMetrica() throws ValidacaoException {
        List<Volume> discos = looca.getGrupoDeDiscos().getVolumes();
        ValidadorDeComponentes validadorDeComponentes = new ValidadorDeComponentes();
        Inovacao inovacao = new Inovacao();

        Integer fkCpuEspec = especificacaoComponente.stream()
                .filter(e -> e.getTipoComponente().equals(TipoComponente.CPU))
                .findFirst().get().getId_especificacao_componente();

        Integer fkRamEspec = especificacaoComponente.stream()
                .filter(e -> e.getTipoComponente().equals(TipoComponente.RAM))
                .findFirst().get().getId_especificacao_componente();

        Integer fkCpu = maquinaUltrassomEspec.stream().filter(e -> e.getFk_especificacao_componente()
                .equals(fkCpuEspec)).findFirst().get().getId_especificacao_componente_maquina();

        Integer fkRam = maquinaUltrassomEspec.stream().filter(e -> e.getFk_especificacao_componente()
                .equals(fkRamEspec)).findFirst().get().getId_especificacao_componente_maquina();

        List<EspecificacaoComponente> componentesDisc = especificacaoComponente.stream()
                .filter(e -> e.getTipoComponente().equals(TipoComponente.DISCO)).toList();

        new Timer().scheduleAtFixedRate(new TimerTask() {
            public void run() {
                try {
                    validadorDeComponentes.validarCpu(looca.getProcessador(), fkCpu);
                } catch (ValidacaoException e) {
                    System.out.println(e);
                }

                try {
                    validadorDeComponentes.validarRam(looca.getMemoria(), fkRam);
                } catch (ValidacaoException e) {
                    System.out.println(e);
                }


                for (int i = 0; i < componentesDisc.size(); i++) {
                    try {
                        EspecificacaoComponente especAtual = componentesDisc.get(i);

                        Integer fkDiscoEspec = especAtual.getId_especificacao_componente();

                        Integer fkDisco = maquinaUltrassomEspec.stream().filter(e -> e.getFk_especificacao_componente()
                                .equals(fkDiscoEspec)).findFirst().get().getId_especificacao_componente_maquina();

                        Volume discoAtual = discos.stream().filter(e -> e.getUUID()
                                .equals(especAtual.getNumero_serial())).findFirst().get();

                        validadorDeComponentes.validarDisco(discoAtual, fkDisco);
                    } catch (ValidacaoException e) {
                        System.out.println(e);
                    }
                }

                inovacao.setIpRoteador(looca.getRede().getGrupoDeInterfaces().getInterfaces().get(0).getEnderecoIpv4().toString());

                try {
                    inovacao.execCommand("ping -c 4 " + inovacao.getIpRoteador());
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
