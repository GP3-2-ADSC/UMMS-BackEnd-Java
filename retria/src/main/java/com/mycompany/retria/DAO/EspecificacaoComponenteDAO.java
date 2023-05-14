/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retria.DAO;

import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.mycompany.retria.MODEL.EspecificacaoComponente;
import java.util.List;
import com.mycompany.retria.services.Service;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author lucka
 */
public class EspecificacaoComponenteDAO {

    JdbcTemplate con;
    JdbcTemplate conMysql;
    Service service = new Service();


    public EspecificacaoComponenteDAO() {
        Conexao conexao = new Conexao();
        con = conexao.getConnection();
        ConexaoMySqlEc2 conMysqlEc2 = new ConexaoMySqlEc2();
        conMysql = conMysqlEc2.getConnection();
    }

    public EspecificacaoComponente getComponenteCpu(Processador processador) {

        List<EspecificacaoComponente> especificacaoComponentes =
                con.query(String.format("""
                        select
                            *
                        from
                            especificacao_componente
                        where
                            descricao_componente = '%s'
                        """, processador.getNome()), new BeanPropertyRowMapper<>(EspecificacaoComponente.class));

        if (especificacaoComponentes.isEmpty()) {
            con.execute(String.format("insert into especificacao_componente" +
                            "(tipo_componente, descricao_componente, nome_fabricante, numero_serial) values ('%s', '%s', '%s', '%s')",
                  "CPU",processador.getNome(), processador.getFabricante(), processador.getId()));

            conMysql.execute(String.format("insert into especificacao_componente" +
                            "(tipo_componente, descricao_componente, nome_fabricante, numero_serial) values ('%s', '%s', '%s', '%s')",
                    "CPU",processador.getNome(), processador.getFabricante(), processador.getId()));

            especificacaoComponentes =
                    con.query(String.format("""
                            select
                                *
                            from
                                especificacao_componente
                            where
                                descricao_componente = '%s'
                            """, processador.getNome()), new BeanPropertyRowMapper<>(EspecificacaoComponente.class));
        }

        EspecificacaoComponente dados = especificacaoComponentes.get(0);

        return new EspecificacaoComponente(dados.getId_especificacao_componente(),
                dados.getTipoComponente(), dados.getNome_fabricante(), dados.getDescricao_componente(),dados.getNumero_serial());
    }

    public EspecificacaoComponente getComponenteMemoria(Memoria memoria) {
        String nomeMemoria = String.format("Pente de memória ram - %.0f GB", service.convertBytesToGB(memoria.getTotal()));

        List<EspecificacaoComponente> especificacaoComponentes =
                con.query(String.format("""
                        select
                            *
                        from
                            especificacao_componente
                        where
                            descricao_componente = '%s'
                        """, nomeMemoria), new BeanPropertyRowMapper<>(EspecificacaoComponente.class));

        if (especificacaoComponentes.isEmpty()) {
            con.execute(String.format("insert into especificacao_componente" +
                            "(tipo_componente, descricao_componente) values ('%s','%s')",
                    "RAM",nomeMemoria));

            conMysql.execute(String.format("insert into especificacao_componente" +
                            "(tipo_componente, descricao_componente) values ('%s','%s')",
                    "RAM",nomeMemoria));

            especificacaoComponentes =
                    con.query(String.format("""
                            select
                                *
                            from
                                especificacao_componente
                            where
                                descricao_componente = '%s'
                            """, nomeMemoria), new BeanPropertyRowMapper<>(EspecificacaoComponente.class));
        }

        EspecificacaoComponente dados = especificacaoComponentes.get(0);
        return new EspecificacaoComponente(dados.getId_especificacao_componente(),
                dados.getTipoComponente(), dados.getNome_fabricante(), dados.getDescricao_componente(),dados.getNumero_serial());
    }

    public EspecificacaoComponente getComponenteDisco(Disco disco) {
        String nomeDisco = String.format("HD/SSD - %.0f GB", service.convertBytesToGB(disco.getTamanho()));
        List<EspecificacaoComponente> especificacaoComponentes =
                con.query(String.format("""
                        select
                            *
                        from
                            especificacao_componente
                        where
                            descricao_componente = '%s'
                        OR
                            numero_serial = '%s'
                        """, nomeDisco,disco.getSerial()), new BeanPropertyRowMapper<>(EspecificacaoComponente.class));

        if (especificacaoComponentes.isEmpty()) {
            con.execute(String.format("insert into especificacao_componente" +
                            "(tipo_componente,descricao_componente, numero_serial) values ('%s', '%s','%s')",
                    "DISCO",nomeDisco, disco.getSerial()));

            conMysql.execute(String.format("insert into especificacao_componente" +
                            "(tipo_componente,descricao_componente, numero_serial) values ('%s', '%s','%s')",
                    "DISCO",nomeDisco, disco.getSerial()));

            especificacaoComponentes =
                    con.query(String.format("""
                            select
                                *
                            from
                                especificacao_componente
                            where
                                descricao_componente = '%s'
                            OR
                                numero_serial = '%s'
                            """, nomeDisco,disco.getSerial()), new BeanPropertyRowMapper<>(EspecificacaoComponente.class));
        }

        EspecificacaoComponente dados = especificacaoComponentes.get(0);

        return new EspecificacaoComponente(dados.getId_especificacao_componente(),
                dados.getTipoComponente(), dados.getNome_fabricante(), dados.getDescricao_componente(), dados.getNumero_serial());
    }
}