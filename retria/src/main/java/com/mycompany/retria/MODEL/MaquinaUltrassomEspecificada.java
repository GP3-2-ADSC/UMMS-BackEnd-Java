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
    private Double uso_maximo;
    private Double frequencia_maxima;
    private Integer fk_maquina;
    private Integer fk_especificacao_componente;

    public MaquinaUltrassomEspecificada(Integer id_especificacao_componente_maquina, Double uso_maximo, Double frequencia_maxima, Integer fk_maquina, Integer fk_especificacao_componente) {
        this.id_especificacao_componente_maquina = id_especificacao_componente_maquina;
        this.uso_maximo = uso_maximo;
        this.frequencia_maxima = frequencia_maxima;
        this.fk_maquina = fk_maquina;
        this.fk_especificacao_componente = fk_especificacao_componente;
    }

    
    public Integer getId_especificacao_componente_maquina() {
        return id_especificacao_componente_maquina;
    }

    public void setId_especificacao_componente_maquina(Integer id_especificacao_componente_maquina) {
        this.id_especificacao_componente_maquina = id_especificacao_componente_maquina;
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

    public Integer getFk_especificacao_componente() {
        return fk_especificacao_componente;
    }

    public void setFk_especificacao_componente(Integer fk_especificacao_componente) {
        this.fk_especificacao_componente = fk_especificacao_componente;
    }

    @Override
    public String toString() {
        return "MaquinaUltrassomEspecificada{" + "id_especificacao_componente_maquina=" + id_especificacao_componente_maquina + ", uso_maximo=" + uso_maximo + ", frequencia_maxima=" + frequencia_maxima + ", fk_maquina=" + fk_maquina + ", fk_especificacao_componente=" + fk_especificacao_componente + '}';
    }
}
