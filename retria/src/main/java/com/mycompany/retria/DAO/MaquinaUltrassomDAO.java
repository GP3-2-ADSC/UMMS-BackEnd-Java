/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retria.DAO;

import com.mycompany.retria.MODEL.Administrador;
import com.mycompany.retria.MODEL.MaquinaUltrassom;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author lucka
 */
public class MaquinaUltrassomDAO {
    JdbcTemplate con;

    public MaquinaUltrassomDAO() {
        Conexao conexao = new Conexao();
        con = conexao.getConnection();
    }

    public MaquinaUltrassom getMaquinaUltrassom(String idProcessador, Integer fkAdmin,Integer fkEmpresa, String sistema){
        List<MaquinaUltrassom> maquinasUltra = con.query(String.format("""
                        select 
                            m.* 
                        from 
                            maquina_ultrassonografica
                        where 
                            id_processador = '%s';
                        """, idProcessador),
                new BeanPropertyRowMapper(MaquinaUltrassom.class));

        while(maquinasUltra.size() == 0){
            con.execute(String.format("""
                    insert 
                        (sistema_operacional, id_processador, fk_administrador,fk_empresa) 
                    values
                        ('%s','%s' ,%d, %d);
                    """,sistema, idProcessador,fkAdmin, fkEmpresa));

            maquinasUltra = con.query(String.format("""
                        select 
                            m.* 
                        from 
                            maquina_ultrassonografica
                        where 
                            id_processador = '%s';
                        """, idProcessador),
                    new BeanPropertyRowMapper(MaquinaUltrassom.class));
        }

        MaquinaUltrassom dados = maquinasUltra.get(0);

        return new MaquinaUltrassom(dados.getIdMaquina(),dados.getSistemaOperacional(),dados.getIdProcessador(),
                dados.getFkAdmin(), dados.getFkEmpresa());
    }

}
