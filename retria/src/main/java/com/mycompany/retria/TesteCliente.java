/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.retria;

import com.mycompany.retria.DAO.ClienteDAO;
import com.mycompany.retria.DAO.Conexao;
import com.mycompany.retria.MODEL.Cliente;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author lucka
 */
public class TesteCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConnection();

        ClienteDAO clienteDAO = new ClienteDAO();

        List<Cliente> clientes = con.query("select * from cliente;",
                new BeanPropertyRowMapper(Cliente.class));

        for (Cliente cliente1 : clientes) {
            System.out.println(cliente1);
        }

        clienteDAO.adicionar();

        clientes = con.query("select * from cliente;",
                new BeanPropertyRowMapper(Cliente.class));

        for (Cliente cliente1 : clientes) {
            System.out.println(cliente1);
        }
    }

}
