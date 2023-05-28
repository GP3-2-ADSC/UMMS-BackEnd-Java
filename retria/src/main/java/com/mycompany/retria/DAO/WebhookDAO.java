package com.mycompany.retria.DAO;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class WebhookDAO {
    JdbcTemplate con;
    JdbcTemplate conMysql;
    EmpresaDAO empresaDAO = new EmpresaDAO();

    public WebhookDAO() {
        Conexao conexao = new Conexao();
        con = conexao.getConnection();
        ConexaoMySqlEc2 conexaoMysql = new ConexaoMySqlEc2();
        conMysql = conexaoMysql.getConnection();
    }

    public String getLink() {
        List<String> links = con.query("SELECT link FROM webhook WHERE id_web = 1",
                new BeanPropertyRowMapper(String.class));

        return links.get(0);
    }
}
