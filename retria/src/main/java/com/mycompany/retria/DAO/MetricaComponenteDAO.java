package com.mycompany.retria.DAO;

import com.mycompany.retria.MODEL.MaquinaUltrassom;
import com.mycompany.retria.MODEL.MetricaComponente;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class MetricaComponenteDAO {
    JdbcTemplate con;

    public MetricaComponenteDAO() {
        Conexao conexao = new Conexao();
        con = conexao.getConnection();
    }

    public Integer setMetrica(MetricaComponente metrica) {
        String dataAtual = metrica.getDateFormatedSql();
            con.execute(String.format("""
                insert into metrica_componente (dt_metrica,uso,fk_especificacao_componente_maquina) values ('%s',%.0f,%d)
                """,dataAtual,metrica.getUso(),metrica.getFkEspecificacaoComponente()));

        List<MetricaComponente> metricas = con.query(String.format("""
                select 
                    id_metrica_componente 
                from
                    metrica_componente
                where
                     dt_metrica = '%s'
                and 
                    fk_especificacao_componente_maquina = %d
                """, dataAtual,metrica.getFkEspecificacaoComponente()),
                new BeanPropertyRowMapper(MetricaComponente.class));


        return metricas.get(0).getIdMetricaComponente();
    }

}
