/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retria.MODEL;

/**
 *
 * @author lucka
 */
public class MaquinaUltrassom {
    private Integer id_maquina;
    private String nome_fornecedor;
    private String tipo_maquina;
    private String sistema_operacional;
    private String setor;
    private Integer andar;

    public Integer getId_maquina() {
        return id_maquina;
    }

    public void setId_maquina(Integer id_maquina) {
        this.id_maquina = id_maquina;
    }

    public String getNome_fornecedor() {
        return nome_fornecedor;
    }

    public void setNome_fornecedor(String nome_fornecedor) {
        this.nome_fornecedor = nome_fornecedor;
    }

    public String getTipo_maquina() {
        return tipo_maquina;
    }

    public void setTipo_maquina(String tipo_maquina) {
        this.tipo_maquina = tipo_maquina;
    }

    public String getSistema_operacional() {
        return sistema_operacional;
    }

    public void setSistema_operacional(String sistema_operacional) {
        this.sistema_operacional = sistema_operacional;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public Integer getAndar() {
        return andar;
    }

    public void setAndar(Integer andar) {
        this.andar = andar;
    }

    @Override
    public String toString() {
        return "MaquinaUltrassom{" + "id_maquina=" + id_maquina + ", nome_fornecedor=" + nome_fornecedor + ", tipo_maquina=" + tipo_maquina + ", sistema_operacional=" + sistema_operacional + ", setor=" + setor + ", andar=" + andar + '}';
    }
}
