/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retria.DAO;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.mycompany.retria.MODEL.EspecificacaoComponente;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author lucka
 */
public class EspecificacaoComponenteDAO {

    private Integer id_especificacao_componente;
    private String tipo;
    private String nome_fabricante;
    private String descricao_componente;
    JdbcTemplate con;

    public EspecificacaoComponenteDAO() {
        Conexao conexao = new Conexao();
        con = conexao.getConnection();
    }

    public void adicionarEspecComponente() {
        Looca looca = new Looca();
        Processador processador = looca.getProcessador();
        Memoria memoria = looca.getMemoria();
        DiscoGrupo grupoDeDiscos = looca.getGrupoDeDiscos();
        List<Disco> discos = grupoDeDiscos.getDiscos();
        EspecificacaoComponente esp = new EspecificacaoComponente();

        tipo = processador.getNome();
        nome_fabricante = processador.getFabricante();
        descricao_componente = processador.getIdentificador();

        esp.setTipo(tipo);
        esp.setNome_fabricante(nome_fabricante);
        esp.setDescricao_componente(descricao_componente);

        con.execute(String.format("insert into especificacao_componente (tipo, nome_fabricante, descricao_componente) values ('%s', '%s', '%s')",
                esp.getTipo(), esp.getNome_fabricante(), esp.getDescricao_componente()));

//        for (Disco disco : discos) {
//
//            tipo = disco.getNome();
//            nome_fabricante = disco.getModelo();
//            descricao_componente = disco.getSerial();
//
//            esp.setTipo(tipo);
//            esp.setNome_fabricante(nome_fabricante);
//            esp.setDescricao_componente(descricao_componente);
//
//            con.execute(String.format("insert into especificacao_componente values"
//                    + "(null, '%s', '%s', '%s')",
//                     esp.getTipo(), esp.getNome_fabricante(), esp.getDescricao_componente()));
//        }

        System.out.println("COMPONENTES CADASTRADOS COM SUCESSO!");
    }

    Integer idConsulta = 0;

    public Integer consultaID() {
        List<EspecificacaoComponente> administradores = con.query("select * from especificacao_componente;",
                new BeanPropertyRowMapper(EspecificacaoComponente.class));

        for (EspecificacaoComponente espComp : administradores) {
            idConsulta = espComp.getId_especificacao_componente();
        }

        return idConsulta;
    }

}
