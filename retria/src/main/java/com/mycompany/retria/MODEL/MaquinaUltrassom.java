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
    private Integer idMaquina;
    private String sistemaOperacional;
    private String idProcessador;

    private Integer fkAdmin;

    private Integer fkEmpresa;
    private Boolean isAtivo;

    public MaquinaUltrassom(Integer idMaquina, String sistemaOperacional, String idProcessador, Integer fkAdmin, Integer fkEmpresa, Boolean isAtivo) {
        this.idMaquina = idMaquina;
        this.sistemaOperacional = sistemaOperacional;
        this.idProcessador = idProcessador;
        this.fkAdmin = fkAdmin;
        this.fkEmpresa = fkEmpresa;
        this.isAtivo = isAtivo;
    }


    public MaquinaUltrassom() {
    }

    public Integer getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(Integer idMaquina) {
        this.idMaquina = idMaquina;
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }

    public String getIdProcessador() {
        return idProcessador;
    }

    public void setIdProcessador(String idProcessador) {
        this.idProcessador = idProcessador;
    }

    public Integer getFkAdmin() {
        return fkAdmin;
    }

    public void setFkAdmin(Integer fkAdmin) {
        this.fkAdmin = fkAdmin;
    }

    public Integer getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(Integer fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }

    @Override
    public String toString() {
        return "MaquinaUltrassom{" +
                "idMaquina=" + idMaquina +
                ", sistemaOperacional='" + sistemaOperacional + '\'' +
                ", idProcessador='" + idProcessador + '\'' +
                ", fkAdmin=" + fkAdmin +
                ", fkEmpresa=" + fkEmpresa +
                '}';
    }

    public boolean getIsAtivo() {
        return isAtivo;
    }

    public void setIsAtivo(boolean isAtivo) {
        this.isAtivo = isAtivo;
    }
}
