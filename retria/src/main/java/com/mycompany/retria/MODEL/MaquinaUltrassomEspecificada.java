/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retria.MODEL;

/**
 *
 * @author lucka
 *
 * id_especificacao_componente_maquina int AI PK numero_serial varchar(45)
 * uso_maximo double frequencia_maxima double fk_maquina int PK fk_administrador
 * int PK fk_empresa int PK fk_especificacao_componente
 */
public class MaquinaUltrassomEspecificada {

    private Integer id_especificacao_componente_maquina;
    private String numero_serial;
    private Double uso_maximo;
    private Double frequencia_maxima;
    private Integer fk_maquina;
    private Integer fk_administrador;
    private Integer fk_empresa;
    private Integer fk_especificacao_componente;

    public Integer getId_especificacao_componente_maquina() {
        return id_especificacao_componente_maquina;
    }

    public void setId_especificacao_componente_maquina(Integer id_especificacao_componente_maquina) {
        this.id_especificacao_componente_maquina = id_especificacao_componente_maquina;
    }

    public String getNumero_serial() {
        return numero_serial;
    }

    public void setNumero_serial(String numero_serial) {
        this.numero_serial = numero_serial;
    }

    public Double getUso_maximo() {
        return uso_maximo;
    }

    public void setUso_maximo(Double uso_maximo) {
        this.uso_maximo = uso_maximo;
    }

    public Double getFrequencia_maxima() {
        return frequencia_maxima;
    }

    public void setFrequencia_maxima(Double frequencia_maxima) {
        this.frequencia_maxima = frequencia_maxima;
    }

    public Integer getFk_maquina() {
        return fk_maquina;
    }

    public void setFk_maquina(Integer fk_maquina) {
        this.fk_maquina = fk_maquina;
    }

    public Integer getFk_administrador() {
        return fk_administrador;
    }

    public void setFk_administrador(Integer fk_administrador) {
        this.fk_administrador = fk_administrador;
    }

    public Integer getFk_empresa() {
        return fk_empresa;
    }

    public void setFk_empresa(Integer fk_empresa) {
        this.fk_empresa = fk_empresa;
    }

    public Integer getFk_especificacao_componente() {
        return fk_especificacao_componente;
    }

    public void setFk_especificacao_componente(Integer fk_especificacao_componente) {
        this.fk_especificacao_componente = fk_especificacao_componente;
    }

    @Override
    public String toString() {
        return "MaquinaUltrassomEspecificada{" + "id_especificacao_componente_maquina=" + id_especificacao_componente_maquina + ", numero_serial=" + numero_serial + ", frequencia_maxima=" + frequencia_maxima + ", fk_maquina=" + fk_maquina + ", fk_administrador=" + fk_administrador + ", fk_empresa=" + fk_empresa + ", fk_especificacao_componente=" + fk_especificacao_componente + '}';
    }

}
