/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retria.DAO;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.sistema.Sistema;
import com.mycompany.retria.MODEL.MaquinaUltrassomEspecificada;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

    Memoria memoria = looca.getMemoria();
    Processador processador = looca.getProcessador();
    DiscoGrupo grupoDeDiscos = looca.getGrupoDeDiscos();
    Sistema sistema = looca.getSistema();

    JdbcTemplate con;

    public MaquinaUltrassomEspecificadaDAO() {
        Conexao conexao = new Conexao();
        con = conexao.getConnection();
    }

    public MaquinaUltrassomEspecificada getMaquiUltassomEspecRAM(Long usoMaximo, Integer fkMaquina, Integer fkEspecComp) {

        List<MaquinaUltrassomEspecificada> maquinaUltraEspec = con.query(String.format("""
                        select
                            m.*
                        from 
                            maquina_ultrassom_especificada m
                        join 
                            especificacao_componente e
                        on
                            m.fk_especificacao_componente = e.id_especificacao_componente
                        where 
                            uso_maximo = %f
                        and fk_maquina = %d
                        and fk_especificacao_componente = %d;                
                        and e.tipo_componente = 'RAM'
                    """, usoMaximo, fkMaquina, fkEspecComp),
                new BeanPropertyRowMapper<>(MaquinaUltrassomEspecificada.class));

        if (maquinaUltraEspec.isEmpty()) {
            con.execute(String.format("""
                        insert into maquina_ultrassom_especificada 
                            (uso_maximo, fk_maquina, fk_especificacao_componente)
                        values (%f, %d, %d);
                      """, memoria.getTotal(), fkMaquina, fkEspecComp));

            maquinaUltraEspec
                    = con.query(String.format("""
                        select
                            m.*
                        from 
                            maquina_ultrassom_especificada m
                        join 
                            especificacao_componente e
                        on
                            m.fk_especificacao_componente = e.id_especificacao_componente
                        where 
                            uso_maximo = %f
                        and fk_maquina = %d
                        and fk_especificacao_componente = %d;                
                        and e.tipo_componente = 'RAM'
                    """, usoMaximo, fkMaquina, fkEspecComp),
                            new BeanPropertyRowMapper<>(MaquinaUltrassomEspecificada.class));
        }

        MaquinaUltrassomEspecificada dados = maquinaUltraEspec.get(0);

        System.out.println("ESPEFIFICAÇÃO DE COMPONENTES CADASTRADOS COM SUCESSO!");
        return new MaquinaUltrassomEspecificada(dados.getId_especificacao_componente_maquina(),
                dados.getUso_maximo(),
                null,
                dados.getFk_maquina(),
                dados.getFk_especificacao_componente());
    }
    
    public MaquinaUltrassomEspecificada getMaquiUltassomEspecCPU(Double usoMaximo, Long frenquecia, Integer fkMaquina, Integer fkEspecComp) {

        List<MaquinaUltrassomEspecificada> maquinaUltraEspec = con.query(String.format("""
                        select
                            m.*
                        from 
                            maquina_ultrassom_especificada m
                        join 
                            especificacao_componente e
                        on
                            m.fk_especificacao_componente = e.id_especificacao_componente
                        where 
                            uso_maximo = %f
                        and frequencia_maxima = %f 
                        and fk_maquina = %d
                        and fk_especificacao_componente = %d;                
                        and e.tipo_componente = 'CPU'
                    """, usoMaximo, frenquecia, fkMaquina, fkEspecComp),
                new BeanPropertyRowMapper<>(MaquinaUltrassomEspecificada.class));

        if (maquinaUltraEspec.isEmpty()) {
            con.execute(String.format("""
                        insert into maquina_ultrassom_especificada 
                            (uso_maximo,  frequencia_maxima, fk_maquina, fk_especificacao_componente)
                        values (%f, %f, %d, %d);
                      """, processador.getUso(), processador.getFrequencia(), fkMaquina, fkEspecComp));

            maquinaUltraEspec
                    = con.query(String.format("""
                        select
                            m.*
                        from 
                            maquina_ultrassom_especificada m
                        join 
                            especificacao_componente e
                        on
                            m.fk_especificacao_componente = e.id_especificacao_componente
                        where 
                            uso_maximo = %f
                        and frequencia_maxima = %f
                        and fk_maquina = %d
                        and fk_especificacao_componente = %d;                
                        and e.tipo_componente = 'CPU'
                    """, usoMaximo,frenquecia ,fkMaquina, fkEspecComp),
                            new BeanPropertyRowMapper<>(MaquinaUltrassomEspecificada.class));
        }

        MaquinaUltrassomEspecificada dados = maquinaUltraEspec.get(0);

        System.out.println("ESPEFIFICAÇÃO DE COMPONENTES CADASTRADOS COM SUCESSO!");
        return new MaquinaUltrassomEspecificada(dados.getId_especificacao_componente_maquina(),
                dados.getUso_maximo(),
                dados.getFrequencia_maxima(),
                dados.getFk_maquina(),
                dados.getFk_especificacao_componente());
    }
    public MaquinaUltrassomEspecificada getMaquiUltassomEspecDISCO(Long usoMaximo, Integer fkMaquina, Integer fkEspecComp) {

        List<MaquinaUltrassomEspecificada> maquinaUltraEspec = con.query(String.format("""
                        select
                            m.*
                        from 
                            maquina_ultrassom_especificada m
                        join 
                            especificacao_componente e
                        on
                            m.fk_especificacao_componente = e.id_especificacao_componente
                        where 
                            uso_maximo = %f
                        and fk_maquina = %d
                        and fk_especificacao_componente = %d;                
                        and e.tipo_componente = 'DISCO'
                    """, usoMaximo,fkMaquina, fkEspecComp),
                new BeanPropertyRowMapper<>(MaquinaUltrassomEspecificada.class));

        if (maquinaUltraEspec.isEmpty()) {
            con.execute(String.format("""
                        insert into maquina_ultrassom_especificada 
                            (uso_maximo, fk_maquina, fk_especificacao_componente)
                        values (%f, %d, %d);
                      """, usoMaximo,fkMaquina, fkEspecComp));

            maquinaUltraEspec
                    = con.query(String.format("""
                        select
                            m.*
                        from 
                            maquina_ultrassom_especificada m
                        join 
                            especificacao_componente e
                        on
                            m.fk_especificacao_componente = e.id_especificacao_componente
                        where 
                            uso_maximo = %f
                        and fk_maquina = %d
                        and fk_especificacao_componente = %d;                
                        and e.tipo_componente = 'DISCO'
                    """, usoMaximo,fkMaquina, fkEspecComp),
                            new BeanPropertyRowMapper<>(MaquinaUltrassomEspecificada.class));
        }

        MaquinaUltrassomEspecificada dados = maquinaUltraEspec.get(0);

        System.out.println("ESPEFIFICAÇÃO DE COMPONENTES CADASTRADOS COM SUCESSO!");
        return new MaquinaUltrassomEspecificada(dados.getId_especificacao_componente_maquina(),
                dados.getUso_maximo(),
                null,
                dados.getFk_maquina(),
                dados.getFk_especificacao_componente());
    }
}
