/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retria.MODEL;

/**
 *
 * @author lucka
 */
public class Empresa {
   
    private Integer id_empresa; 
    private String nome_mpresa;
    private String prcnpj;
    private String telefone_01;
    private String telefone_02;
    private String email;
    private String senha;
    private String responsavel_empresa;

    public Integer getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(Integer id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getNome_mpresa() {
        return nome_mpresa;
    }

    public void setNome_mpresa(String nome_mpresa) {
        this.nome_mpresa = nome_mpresa;
    }

    public String getPrcnpj() {
        return prcnpj;
    }

    public void setPrcnpj(String prcnpj) {
        this.prcnpj = prcnpj;
    }

    public String getTelefone_01() {
        return telefone_01;
    }

    public void setTelefone_01(String telefone_01) {
        this.telefone_01 = telefone_01;
    }

    public String getTelefone_02() {
        return telefone_02;
    }

    public void setTelefone_02(String telefone_02) {
        this.telefone_02 = telefone_02;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getResponsavel_empresa() {
        return responsavel_empresa;
    }

    public void setResponsavel_empresa(String responsavel_empresa) {
        this.responsavel_empresa = responsavel_empresa;
    }

    @Override
    public String toString() {
        return "Empresa{" + "id_empresa=" + id_empresa + ", nome_mpresa=" + nome_mpresa + ", prcnpj=" + prcnpj + ", telefone_01=" + telefone_01 + ", telefone_02=" + telefone_02 + ", email=" + email + ", senha=" + senha + ", responsavel_empresa=" + responsavel_empresa + '}';
    }
}
