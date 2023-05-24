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
    private String numeroSerialMaquina;
    private String statusMaquina;
    private Integer fkAdministrador;
    private Integer fkEmpresa;


    public MaquinaUltrassom(Integer idMaquina, String sistemaOperacional, String idProcessador, String isAtivo,Integer fkAdmin, Integer fkEmpresa) {
        this.idMaquina = idMaquina;
        this.sistemaOperacional = sistemaOperacional;
        this.numeroSerialMaquina = idProcessador;
        this.statusMaquina = isAtivo;
        this.fkAdministrador = fkAdmin;
        this.fkEmpresa = fkEmpresa;
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

    public String getNumeroSerialMaquina() {
        return numeroSerialMaquina;
    }

    public void setNumeroSerialMaquina(String numeroSerialMaquina) {
        this.numeroSerialMaquina = numeroSerialMaquina;
    }

    public Integer getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(Integer fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }

    public String getStatusMaquina() {
        return statusMaquina;
    }

    public void setStatusMaquina(String statusMaquina) {
        this.statusMaquina = statusMaquina;
    }

    public Integer getFkAdministrador() {
        return fkAdministrador;
    }

    public void setFkAdministrador(Integer fkAdministrador) {
        this.fkAdministrador = fkAdministrador;
    }

    @Override
    public String toString() {
        return "MaquinaUltrassom{" +
                "idMaquina=" + idMaquina +
                ", sistemaOperacional='" + sistemaOperacional + '\'' +
                ", idProcessador='" + numeroSerialMaquina + '\'' +
                ", statusMaquina='" + statusMaquina + '\'' +
                ", fkAdministrador=" + fkAdministrador +
                ", fkEmpresa=" + fkEmpresa +
                '}';
    }
}
