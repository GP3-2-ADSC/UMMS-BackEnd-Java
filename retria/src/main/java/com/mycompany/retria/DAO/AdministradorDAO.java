/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retria.DAO;

import com.mycompany.retria.MODEL.Administrador;
import com.mycompany.retria.MODEL.Empresa;
import com.mycompany.retria.MODEL.EspecificacaoComponente;
import com.mycompany.retria.MODEL.MaquinaUltrassom;
import com.mycompany.retria.MODEL.MaquinaUltrassomEspecificada;
import java.util.List;
import java.util.Scanner;

import com.mycompany.retria.services.Service;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author lucka
 */
public class AdministradorDAO {
    JdbcTemplate con;
    private Service service;

    public AdministradorDAO() {
        Conexao conexao = new Conexao();
        con = conexao.getConnection();
    }

    public Boolean consultar(String email, String senha, String idProcessador) {

        List<Administrador> administradores = con.query(String.format("""
                        SELECT 
                            *.administrador 
                        FROM 
                            administrador as a
                        JOIN
                            maquinaUltrassom as m
                        ON
                            a.idAdministrador = m.fkAdministrador
                        WHERE
                            a.email = %s
                        AND a.senha = %s
                        AND m.idIdentificador = %s
                        """, email, senha, idProcessador),
                new BeanPropertyRowMapper(Administrador.class));

            if (administradores.size() > 0) {
                return true;
            }

        System.out.println("Email e/ou senha invalidos");
        return false;
    }
    public Administrador setAdministrador(String email, String idProcessador) {

        List<Administrador> administradores = con.query(String.format("""
                        SELECT 
                            *.administrador 
                        FROM 
                            administrador as a
                        JOIN
                            maquinaUltrassom as m
                        ON
                            a.id_administrador = m.fk_administrador
                        WHERE
                            a.email = '%s'
                        AND m.id_processador = '%s'
                        """, email, idProcessador),
                new BeanPropertyRowMapper(Administrador.class));

            Administrador dados = administradores.get(0);

        return new Administrador(dados.getId_administrador(), dados.getNome_administrador(),
                dados.getEmail_administrador(), dados.getFk_empresa());
    }


}
