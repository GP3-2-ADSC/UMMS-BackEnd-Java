/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retria.DAO;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.mycompany.retria.MODEL.MaquinaUltrassomEspecificada;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author lucka
 */
public class MaquinaUltrassomEspecificadaDAO {

    private Integer id_especificacao_componente_maquina;
    private String numero_serial;
    private Double uso_maximo;
    private Double frequencia_maxima;

    Looca looca = new Looca();
    Processador processador = looca.getProcessador();
    DiscoGrupo grupoDeDiscos = looca.getGrupoDeDiscos();
    Sistema sistema = looca.getSistema();
    
    JdbcTemplate con;

    public MaquinaUltrassomEspecificadaDAO() {
        Conexao conexao = new Conexao();
        con = conexao.getConnection();
    }

    public void adicionar() {

        List<Disco> discos = grupoDeDiscos.getDiscos();
        
        MaquinaUltrassomEspecificada espMaqui = new MaquinaUltrassomEspecificada();
        MaquinaUltrassomDAO maquiUltra = new MaquinaUltrassomDAO();
        AdministradorDAO adm = new AdministradorDAO();
        EmpresaDAO emp = new EmpresaDAO();
        EspecificacaoComponenteDAO espComp = new EspecificacaoComponenteDAO();

        numero_serial = processador.getIdentificador();
        uso_maximo = processador.getUso();
        Long frequenciaLong = processador.getFrequencia();
        
        
        frequencia_maxima = Double.valueOf(frequenciaLong + ""); 

        espMaqui.setNumero_serial(numero_serial);
        espMaqui.setUso_maximo(uso_maximo);
        espMaqui.setFrequencia_maxima(frequencia_maxima);

        con.execute(String.format("insert into maquina_ultrassom_especificada (numero_serial, uso_maximo, frequencia_maxima, fk_maquina, fk_administrador, fk_empresa, fk_especificacao_componente) values ('%s', %.0f, %.0f, %d, %d, %d, %d);",
                espMaqui.getNumero_serial(),
                espMaqui.getUso_maximo(),
                espMaqui.getFrequencia_maxima(),
                maquiUltra.consultaID(),
                adm.consultaID(),
                emp.consultaID(),
                espComp.consultaID())
        );

        System.out.println("ESPEFIFICAÇÃO DE COMPONENTES CADASTRADOS COM SUCESSO!");
    }
}
