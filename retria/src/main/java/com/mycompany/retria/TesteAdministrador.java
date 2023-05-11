/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.retria;

import com.mycompany.retria.DAO.AdministradorDAO;
import com.mycompany.retria.DAO.Conexao;
import com.mycompany.retria.DAO.EspecificacaoComponenteDAO;
import com.mycompany.retria.DAO.MaquinaUltrassomEspecificadaDAO;
import com.mycompany.retria.MODEL.Administrador;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author lucka
 */
public class TesteAdministrador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConnection();
        
        EspecificacaoComponenteDAO especificDAO = new EspecificacaoComponenteDAO();
        AdministradorDAO admDAO = new AdministradorDAO();
        MaquinaUltrassomEspecificadaDAO maquiEspecif = new MaquinaUltrassomEspecificadaDAO();
        
//        admDAO.relatorioAdm("lucas@gmail.com", "123");

//        List<Administrador> administradores = con.query("select * from administrador;",
//                new BeanPropertyRowMapper(Administrador.class));

//        for (Administrador admDaVez : administradores) {
//            System.out.println(admDaVez);
//        }

//        especificDAO.adicionar();
//        maquiEspecif.adicionar();
        
//        administradores = con.query("select * from administrador;",
//                new BeanPropertyRowMapper(Administrador.class));
//
//        for (Administrador admDavez : administradores) {
//            System.out.println(admDavez);
//        }

//        EspecificacaoComponenteDAO esp = new EspecificacaoComponenteDAO();
//        esp.adicionar();

//          MaquinaUltrassomEspecificadaDAO especificacaoMaquina = new MaquinaUltrassomEspecificadaDAO();
//          
//          especificacaoMaquina.adicionar();
    }

}
