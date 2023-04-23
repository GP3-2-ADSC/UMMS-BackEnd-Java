/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retria.DAO;

import com.mycompany.retria.MODEL.Administrador;
import com.mycompany.retria.MODEL.Empresa;
import java.util.List;
import java.util.Scanner;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author lucka
 */
public class EmpresaDAO {

    private Integer id_empresa;
    private String nome_mpresa;
    private String prcnpj;
    private String telefone_01;
    private String telefone_02;
    private String email;
    private String senha;
    private String responsavel_empresa;

    JdbcTemplate con;
    Scanner entrada = new Scanner(System.in);
    Empresa empresa = new Empresa();

    public EmpresaDAO() {
        Conexao conexao = new Conexao();
        con = conexao.getConnection();
    }

    public Integer consultarIdEmpresa() {

        Integer respostaConsulta = 0;
        Integer validarAdm = 0;
        do {

            System.out.println("Qual o id da empresa desse adm:");
            Integer consultaId = entrada.nextInt();

            List<Administrador> administradores = con.query("select * from administrador;",
                    new BeanPropertyRowMapper(Administrador.class));

            for (Administrador admDaVez : administradores) {
                while (consultaId.equals(admDaVez.getFk_empresa())) {
                    System.out.println("Já existe adm cadastrado nessa empresa!");
                    validarAdm = admDaVez.getFk_empresa();
                    System.out.println("Digite o id de outra empresa:");
                    consultaId = entrada.nextInt();
                }
                
            }

            List< Empresa> empresas = con.query("select * from empresa;",
                    new BeanPropertyRowMapper(Empresa.class));

            for (Empresa empresa : empresas) {
                if (consultaId.equals(empresa.getId_empresa())) {
                    respostaConsulta = consultaId;
                }
            }
            
            if(respostaConsulta == 0){
                System.out.println("Não existe empresa com esse ID");
            }
            
        } while (respostaConsulta == 0);

        return respostaConsulta;
    }
    
    Integer idConsulta = 0;
    public Integer consultaID(){
        List<Empresa> empresas = con.query("select * from empresa;",
                new BeanPropertyRowMapper(Empresa.class));
        
        for (Empresa maquinaUltrassom : empresas) {
            idConsulta = maquinaUltrassom.getId_empresa();
        }
        return idConsulta;
    }
}
