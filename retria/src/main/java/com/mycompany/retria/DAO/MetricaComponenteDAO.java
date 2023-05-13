package com.mycompany.retria.DAO;

import com.mycompany.retria.MODEL.MaquinaUltrassom;
import com.mycompany.retria.MODEL.MetricaComponente;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class MetricaComponenteDAO {
    JdbcTemplate con;

    public Integer setMetrica(MetricaComponente metrica) {
        List<MetricaComponente> metricas = con.query(String.format("""
                insert into metrica (dt_metrica,uso,fk_especificacao_componente_maquina) values ('%s',%.2f,%d)
                """, metrica.getDateFormatedSql(),metrica.getUso(),metrica.getFkEspecificacaoComponente()),
                new BeanPropertyRowMapper(MaquinaUltrassom.class));

        return metricas.get(0).getIdMetricaComponente();
    }

}
