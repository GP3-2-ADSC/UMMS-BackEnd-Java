/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retria.DAO;

import com.mycompany.retria.MODEL.Cliente;
import java.util.List;
import java.util.Scanner;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author lucka
 */
public class ClienteDAO {

    private String email;
    private String senha;
    JdbcTemplate con;
    Scanner entrada = new Scanner(System.in);
    Cliente cliente = new Cliente();

    public ClienteDAO() {
        Conexao conexao = new Conexao();
        con = conexao.getConnection();
    }

    public void adicionar() {
        System.out.println("Digite o email cliente:");
        email = entrada.nextLine();

        System.out.println("Digite o senha cliente:");
        senha = entrada.nextLine();

        cliente.setEmail(email);
        cliente.setSenha(senha);

        con.execute(String.format("insert into cliente values (null, '%s', '%s')",
                cliente.getEmail(), cliente.getSenha())
        );

        System.out.println("Cliente cadastrado com sucesso\n");
    }

    public Boolean consultar(String email, String senha) {

        this.email = email;
        this.senha = senha;

        Boolean clienteEncontrado = null;
        String respostaConsulta = "";
        List<Cliente> clientes = con.query("select * from cliente;",
                new BeanPropertyRowMapper(Cliente.class));

        for (Cliente cliente1 : clientes) {
            if (cliente1.getEmail().equals(email) && cliente1.getSenha().equals(senha)) {
                respostaConsulta = String.format("Usuario %s logado com sucesso", cliente1.getEmail());
                clienteEncontrado = true;
                break;
            } else {
                respostaConsulta = "Email e/ou senha invalidos";
                clienteEncontrado = false;
            }
        }
        
        System.out.println(respostaConsulta);
        return clienteEncontrado;
    }
}
