package com.mycompany.retria.MODEL;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Alerta {

    private Integer idAlerta;

    private static final LocalDateTime dtAlerta = LocalDateTime.now();

    private String dtAlertaFormatada = dtAlerta.format(DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss"));

    private String tipoAlerta;

    private MetricaComponente metricaComponente;

    public Alerta(Integer idAlerta, String tipoAlerta, MetricaComponente metricaComponente) {
        this.idAlerta = idAlerta;
        this.tipoAlerta = tipoAlerta;
        this.metricaComponente = metricaComponente;
    }

    public Integer getIdAlerta() {
        return idAlerta;
    }

    public void setIdAlerta(Integer idAlerta) {
        this.idAlerta = idAlerta;
    }

    public String getDtAlertaFormatada() {
        return dtAlertaFormatada;
    }

    public void setDtAlertaFormatada(String dtAlertaFormatada) {
        this.dtAlertaFormatada = dtAlertaFormatada;
    }

    public String getTipoAlerta() {
        return tipoAlerta;
    }

    public void setTipoAlerta(String tipoAlerta) {
        this.tipoAlerta = tipoAlerta;
    }

    public MetricaComponente getMetricaComponente() {
        return metricaComponente;
    }

    public void setMetricaComponente(MetricaComponente metricaComponente) {
        this.metricaComponente = metricaComponente;
    }

    @Override
    public String toString() {
        return String.format("""
                
                Alertas
                
                ID : %d
                
                DATA : %s
                
                TIPO : %s
                
                """, this.idAlerta, this.dtAlertaFormatada, this.tipoAlerta);
    }
}
