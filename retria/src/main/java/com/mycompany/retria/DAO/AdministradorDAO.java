/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retria.DAO;

import com.mycompany.retria.MODEL.Administrador;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author lucka
 */
public class AdministradorDAO {

    JdbcTemplate con;
    JdbcTemplate conMysql;

    public AdministradorDAO() {
        Conexao conexao = new Conexao();
        con = conexao.getConnection();
        ConexaoMySqlEc2 conMysqlEc2 = new ConexaoMySqlEc2();
        conMysql = conMysqlEc2.getConnection();
    }

    public Boolean consultar(String email, String senha) {

        List<Administrador> administradores = con.query(String.format("""
                        SELECT 
                            *
                        FROM 
                            administrador
                        WHERE
                            email_administrador = '%s'
                        AND senha_administrador = '%s';
                        """, email, senha),
                new BeanPropertyRowMapper(Administrador.class));

            if (!administradores.isEmpty()) {
                return true;
            }

        System.out.println("Email e/ou senha invalidos");
        return false;
    }
    public Administrador setAdministrador(String email, String senha) {

        List<Administrador> administradores = con.query(String.format("""
                        SELECT 
                            *
                        FROM 
                            administrador
                        WHERE
                            email_administrador = '%s'
                        AND senha_administrador = '%s';
                        """, email, senha),
                new BeanPropertyRowMapper(Administrador.class));

            Administrador dados = administradores.get(0);

        return new Administrador(dados.getId_administrador(), dados.getNome_administrador(),
                dados.getEmail_administrador(), dados.getFk_empresa());
    }


}
