package com.mycompany.retria.DAO;

import com.mycompany.retria.MODEL.Alerta;
import com.mycompany.retria.MODEL.MaquinaUltrassom;
import com.mycompany.retria.MODEL.MetricaComponente;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class AlertaDAO {

    JdbcTemplate con;

    public AlertaDAO() {
        Conexao conexao = new Conexao();
        con = conexao.getConnection();
    }

    public void setAlerta(Alerta alerta) {
        List<Alerta> alertas = con.query(String.format("""
                insert into alerta (dt_alerta,fk_tipo_alerta,fk_metrica_componente) values ('%s',%d,%d)
                """, alerta.getDateFormatedSql(),alerta.getTipoAlerta(),alerta.getFkMetricaComponente()),
                new BeanPropertyRowMapper(MaquinaUltrassom.class));

    }



}