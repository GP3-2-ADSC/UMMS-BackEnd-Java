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

    private Integer id_maquina;
    JdbcTemplate con;

    public MaquinaUltrassomDAO() {
        Conexao conexao = new Conexao();
        con = conexao.getConnection();
    }
    
    Integer idConsulta = 0;
    public Integer consultaID(){
        List<MaquinaUltrassom> maquinasUltra = con.query("select * from maquina_ultrassom;",
                new BeanPropertyRowMapper(MaquinaUltrassom.class));
        
        for (MaquinaUltrassom maquinaUltrassom : maquinasUltra) {
            idConsulta = maquinaUltrassom.getId_maquina();
        }
        return idConsulta;
    }
}
