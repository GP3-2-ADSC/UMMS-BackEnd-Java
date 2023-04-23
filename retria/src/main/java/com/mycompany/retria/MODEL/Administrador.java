/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retria.MODEL;

/**
 *
 * @author lucka
 */
public class Administrador {

    private Integer id_administrador;
    private String nome_administrador;
    private String email_administrador;
    private String senha_administrador;
    private String telefone_administrador;
    private String ocupacao;
    private String chave_seguranca_administrador;
    private Integer fk_empresa;

    public Integer getId_administrador() {
        return id_administrador;
    }

    public void setId_administrador(Integer id_administrador) {
        this.id_administrador = id_administrador;
    }

    public String getNome_administrador() {
        return nome_administrador;
    }

    public void setNome_administrador(String nome_administrador) {
        this.nome_administrador = nome_administrador;
    }

    public String getEmail_administrador() {
        return email_administrador;
    }

    public void setEmail_administrador(String email_administrador) {
        this.email_administrador = email_administrador;
    }

    public String getSenha_administrador() {
        return senha_administrador;
    }

    public void setSenha_administrador(String senha_administrador) {
        this.senha_administrador = senha_administrador;
    }

    public String getTelefone_administrador() {
        return telefone_administrador;
    }

    public void setTelefone_administrador(String telefone_administrador) {
        this.telefone_administrador = telefone_administrador;
    }

    public String getOcupacao() {
        return ocupacao;
    }

    public void setOcupacao(String ocupacao) {
        this.ocupacao = ocupacao;
    }

    public String getChave_seguranca_administrador() {
        return chave_seguranca_administrador;
    }

    public void setChave_seguranca_administrador(String chave_seguranca_administrador) {
        this.chave_seguranca_administrador = chave_seguranca_administrador;
    }

    public Integer getFk_empresa() {
        return fk_empresa;
    }

    public void setFk_empresa(Integer fk_empresa) {
        this.fk_empresa = fk_empresa;
    }

    @Override
    public String toString() {
        return String.format("""
                             Administrador:
                             Id: %d
                             Nome: %s
                             Email: %s
                             Senha: %s
                             Telefone: %s
                             Ocupação: %s
                             Chave de Segurança: %s
                             fk Empresa: %d
                             """, id_administrador, nome_administrador,
                email_administrador, senha_administrador,
                telefone_administrador, ocupacao,
                chave_seguranca_administrador, fk_empresa);
    }
}
