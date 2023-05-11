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

    JdbcTemplate con;

    Looca looca = new Looca();

    private Processador processador = looca.getProcessador();
    private Memoria memoria = looca.getMemoria();

    private List<Disco> discos = looca.getGrupoDeDiscos().getDiscos();


    public EspecificacaoComponenteDAO() {
        Conexao conexao = new Conexao();
        con = conexao.getConnection();
    }

<<<<<<< HEAD
    public List<EspecificacaoComponente> getComponentes(){
        List<EspecificacaoComponente> especificacaoComponentes =
                con.query(String.format("""
                select
                    *
                from
                    especificacao_componente
                where
                    
                
                
                
                
                
                
                
                """), new BeanPropertyRowMapper<>(EspecificacaoComponente.class));

    }

    public void adicionar() {
=======
    public void adicionarEspecComponente() {
>>>>>>> aa385b21165e4a0fcc5152ebf7b4729656cb6696
        Looca looca = new Looca();
        Processador processador = looca.getProcessador();
        Memoria memoria = looca.getMemoria();
        DiscoGrupo grupoDeDiscos = looca.getGrupoDeDiscos();
        List<Disco> discos = grupoDeDiscos.getDiscos();

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


}
