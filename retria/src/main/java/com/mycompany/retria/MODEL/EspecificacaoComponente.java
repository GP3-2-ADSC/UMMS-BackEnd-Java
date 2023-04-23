/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retria.MODEL;

/**
 *
 * @author lucka
 * 
 */
public class EspecificacaoComponente {
    
    private Integer id_especificacao_componente;
    private String tipo;
    private String nome_fabricante;
    private String descricao_componente;
    
    public Integer getId_especificacao_componente() {
        return id_especificacao_componente;
    }

    public void setId_especificacao_componente(Integer id_especificacao_componente) {
        this.id_especificacao_componente = id_especificacao_componente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome_fabricante() {
        return nome_fabricante;
    }

    public void setNome_fabricante(String nome_fabricante) {
        this.nome_fabricante = nome_fabricante;
    }

    public String getDescricao_componente() {
        return descricao_componente;
    }

    public void setDescricao_componente(String descricao_componente) {
        this.descricao_componente = descricao_componente;
    }

    @Override
    public String toString() {
        return "EspecificacaoComponente{" + "id_especificacao_componente=" + id_especificacao_componente + ", tipo=" + tipo + ", nome_fabricante=" + nome_fabricante + ", descricao_componente=" + descricao_componente + '}';
    }
    
    
}
