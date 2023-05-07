package com.mycompany.retria.MODEL;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MetricaComponente {

    private Integer idMetricaComponente;

    private static final LocalDateTime dtMetrica = LocalDateTime.now();

    private String dtMetricaFormatada = dtMetrica.format(DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss"));

    private Double uso;

    private Double frequencia;

    private final MaquinaUltrassomEspecificada maquinaUltrassomEspecificada;

    public MetricaComponente(Integer idMetricaComponente, Double uso, Double frequencia,
                             MaquinaUltrassomEspecificada maquinaUltrassomEspecificada) {

        this.idMetricaComponente = idMetricaComponente;
        this.uso = uso;
        this.frequencia = frequencia;
        this.maquinaUltrassomEspecificada = new MaquinaUltrassomEspecificada();
    }


    public Integer getIdMetricaComponente() {
        return idMetricaComponente;
    }

    public void setIdMetricaComponente(Integer idMetricaComponente) {
        this.idMetricaComponente = idMetricaComponente;
    }

    public String getDtMetricaFormatada() {
        return dtMetricaFormatada;
    }

    public void setDtMetricaFormatada(String dtMetricaFormatada) {
        this.dtMetricaFormatada = dtMetricaFormatada;
    }

    public Double getUso() {
        return uso;
    }

    public void setUso(Double uso) {
        this.uso = uso;
    }

    public Double getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(Double frequencia) {
        this.frequencia = frequencia;
    }

    public MaquinaUltrassomEspecificada getMaquinaUltrassomEspecificada() {
        return maquinaUltrassomEspecificada;
    }


    @Override
    public String toString() {
        return String.format("""
                
                METRICA DO COMPONENTE
                
                ID : %d
                
                Data : %s
                
                Uso : %.2f
                
                Frequência : %.2f
                
                """, this.idMetricaComponente,
                this.dtMetricaFormatada, this.uso, this.frequencia);
    }
}
