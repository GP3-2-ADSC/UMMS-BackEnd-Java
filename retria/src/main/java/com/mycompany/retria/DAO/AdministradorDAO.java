/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.retria.DAO;

import com.mycompany.retria.MODEL.Administrador;
import com.mycompany.retria.MODEL.Empresa;
import com.mycompany.retria.MODEL.EspecificacaoComponente;
import com.mycompany.retria.MODEL.MaquinaUltrassom;
import com.mycompany.retria.MODEL.MaquinaUltrassomEspecificada;
import java.util.List;
import java.util.Scanner;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author lucka
 */
public class AdministradorDAO {

    private String nome_administrador;
    private String email_administrador;
    private String senha_administrador;
    private String telefone_administrador;
    private String ocupacao;
    private String chave_seguranca_administrador;
    JdbcTemplate con;
    Scanner entrada = new Scanner(System.in);
    Administrador adm = new Administrador();
    EmpresaDAO emp = new EmpresaDAO();

    public AdministradorDAO() {
        Conexao conexao = new Conexao();
        con = conexao.getConnection();
    }

    public void adicionar() {
        System.out.println("Digite o nome do Administrador:");
        nome_administrador = entrada.nextLine();

        System.out.println("Digite o email do Administrador:");
        email_administrador = entrada.nextLine();

        System.out.println("Digite a senha do Administrador:");
        senha_administrador = entrada.nextLine();

        System.out.println("Digite o telefone do Administrador::");
        telefone_administrador = entrada.nextLine();

        System.out.println("Digite a ocupação do Administrador::");
        ocupacao = entrada.nextLine();

        System.out.println("Digite a chave de segurança do Administrador::");
        chave_seguranca_administrador = entrada.nextLine();

        adm.setNome_administrador(nome_administrador);
        adm.setEmail_administrador(email_administrador);
        adm.setSenha_administrador(senha_administrador);
        adm.setTelefone_administrador(telefone_administrador);
        adm.setOcupacao(ocupacao);
        adm.setChave_seguranca_administrador(chave_seguranca_administrador);

        con.execute(String.format("insert into administrador values (null, '%s', '%s', '%s', '%s', '%s', '%s', %d)",
                adm.getNome_administrador(), adm.getEmail_administrador(),
                adm.getSenha_administrador(), adm.getTelefone_administrador(),
                adm.getOcupacao(), adm.getChave_seguranca_administrador(), emp.consultarIdEmpresa())
        );

        System.out.println("Administrador cadastrado com sucesso\n");
    }

    public Boolean consultar(String email, String senha) {

        this.email_administrador = email;
        this.senha_administrador = senha;

        Boolean admEncontrado = null;
        String respostaConsulta = "";
        List<Administrador> administradores = con.query("select * from administrador;",
                new BeanPropertyRowMapper(Administrador.class));

        for (Administrador admDaVez : administradores) {
            if (admDaVez.getEmail_administrador().equals(email) && admDaVez.getSenha_administrador().equals(senha)) {
                respostaConsulta = String.format("Usuario %s logado com sucesso", admDaVez.getEmail_administrador());
                admEncontrado = true;
                break;
            } else {
                respostaConsulta = "Email e/ou senha invalidos";
                admEncontrado = false;
            }
        }

        System.out.println(respostaConsulta);
        return admEncontrado;
    }

    Integer idConsulta = 0;

    public Integer consultaID() {
        List<Administrador> administradores = con.query("select * from administrador;",
                new BeanPropertyRowMapper(Administrador.class));

        for (Administrador administrador : administradores) {
            idConsulta = administrador.getId_administrador();
        }

        return idConsulta;
    }

    public void relatorioAdm(String email, String senha) {
        String fraseAdm = "";
        String fraseEmp = "";
        String fraseEspecComp = "";
        String fraseMaquiResp = "";
        String fraseCabecalho = "----------------------------------------------------------\n"
                + "          RELATÓRIO DO ADMINISTRADOR\n"
                + "----------------------------------------------------------\n";
        String fraseComponentes = "";
        List<Administrador> administradores = con.query(String.format("select adm.nome_administrador, adm.ocupacao, \n"
                + "	emp.nome_empresa, \n"
                + "    maquina.setor, maquina.andar, maquina.tipo_maquina, maquina.sistema_operacional, \n"
                + "    tipo, nome_fabricante, descricao_componente,\n"
                + "    numero_serial, uso_maximo, frequencia_maxima from administrador adm\n"
                + "inner join empresa emp on adm.fk_empresa = emp.id_empresa\n"
                + "inner join maquina_ultrassom maquina on adm.id_administrador = maquina.fk_administrador\n"
                + "inner join maquina_ultrassom_especificada maquina_especifc on maquina.id_maquina = maquina_especifc.fk_maquina\n"
                + "inner join especificacao_componente esp_comp on esp_comp.id_especificacao_componente = maquina_especifc.fk_especificacao_componente\n"
                + "where adm.email_administrador = '%s' and adm.senha_administrador = '%s';", email, senha),
                new BeanPropertyRowMapper(Administrador.class));

        for (Administrador adm : administradores) {
            fraseAdm = String.format(
                    "   Nome: %s\n"
                   +"   Ocupação: %s \n", adm.getNome_administrador(), adm.getOcupacao());
        }

        List<Empresa> empresas = con.query(String.format("select adm.nome_administrador, adm.ocupacao, \n"
                + "	emp.nome_empresa, \n"
                + "    maquina.setor, maquina.andar, maquina.tipo_maquina, maquina.sistema_operacional, \n"
                + "    tipo, nome_fabricante, descricao_componente,\n"
                + "    numero_serial, uso_maximo, frequencia_maxima from administrador adm\n"
                + "inner join empresa emp on adm.fk_empresa = emp.id_empresa\n"
                + "inner join maquina_ultrassom maquina on adm.id_administrador = maquina.fk_administrador\n"
                + "inner join maquina_ultrassom_especificada maquina_especifc on maquina.id_maquina = maquina_especifc.fk_maquina\n"
                + "inner join especificacao_componente esp_comp on esp_comp.id_especificacao_componente = maquina_especifc.fk_especificacao_componente\n"
                + "where adm.email_administrador = '%s' and adm.senha_administrador = '%s';", email, senha),
                new BeanPropertyRowMapper(Empresa.class));

        for (Empresa emp : empresas) {
            fraseEmp = String.format("   Empresa: %s \n", emp.getNome_empresa());
        }

        List<MaquinaUltrassom> maquinas = con.query(String.format("select adm.nome_administrador, adm.ocupacao, \n"
                + "	emp.nome_empresa, \n"
                + "    maquina.setor, maquina.andar, maquina.tipo_maquina, maquina.sistema_operacional, \n"
                + "    tipo, nome_fabricante, descricao_componente,\n"
                + "    numero_serial, uso_maximo, frequencia_maxima from administrador adm\n"
                + "inner join empresa emp on adm.fk_empresa = emp.id_empresa\n"
                + "inner join maquina_ultrassom maquina on adm.id_administrador = maquina.fk_administrador\n"
                + "inner join maquina_ultrassom_especificada maquina_especifc on maquina.id_maquina = maquina_especifc.fk_maquina\n"
                + "inner join especificacao_componente esp_comp on esp_comp.id_especificacao_componente = maquina_especifc.fk_especificacao_componente\n"
                + "where adm.email_administrador = '%s' and adm.senha_administrador = '%s';", email, senha),
                new BeanPropertyRowMapper(MaquinaUltrassom.class));

        for (MaquinaUltrassom maquina : maquinas) {
            fraseMaquiResp = String.format(""
                    + "   E é responsovale pelas maquinas:\n"
                    + "      %s com o Sistema Operacional %s\n\n"
                    + "   Componentes da Máquina:\n",
                    maquina.getTipo_maquina(), maquina.getSistema_operacional());
        }

        List<EspecificacaoComponente> componentes = con.query(String.format("select adm.nome_administrador, adm.ocupacao, \n"
                + "	emp.nome_empresa, \n"
                + "    maquina.setor, maquina.andar, maquina.tipo_maquina, maquina.sistema_operacional, \n"
                + "    tipo, nome_fabricante, descricao_componente,\n"
                + "    numero_serial, uso_maximo, frequencia_maxima from administrador adm\n"
                + "inner join empresa emp on adm.fk_empresa = emp.id_empresa\n"
                + "inner join maquina_ultrassom maquina on adm.id_administrador = maquina.fk_administrador\n"
                + "inner join maquina_ultrassom_especificada maquina_especifc on maquina.id_maquina = maquina_especifc.fk_maquina\n"
                + "inner join especificacao_componente esp_comp on esp_comp.id_especificacao_componente = maquina_especifc.fk_especificacao_componente\n"
                + "where adm.email_administrador = '%s' and adm.senha_administrador = '%s';", email, senha),
                new BeanPropertyRowMapper(EspecificacaoComponente.class));

        for (EspecificacaoComponente componente : componentes) {
            fraseComponentes = String.format(
                      "      Tipo: %s\n"
                    + "      Fabricante: %s\n"
                    + "      Descrição: %s", componente.getTipo(), componente.getNome_fabricante(), componente.getDescricao_componente());
        }

        List<MaquinaUltrassomEspecificada> componentesEspecificados = con.query(String.format("select adm.nome_administrador, adm.ocupacao, \n"
                + "	emp.nome_empresa, \n"
                + "    maquina.setor, maquina.andar, maquina.tipo_maquina, maquina.sistema_operacional, \n"
                + "    tipo, nome_fabricante, descricao_componente,\n"
                + "    numero_serial, uso_maximo, frequencia_maxima from administrador adm\n"
                + "inner join empresa emp on adm.fk_empresa = emp.id_empresa\n"
                + "inner join maquina_ultrassom maquina on adm.id_administrador = maquina.fk_administrador\n"
                + "inner join maquina_ultrassom_especificada maquina_especifc on maquina.id_maquina = maquina_especifc.fk_maquina\n"
                + "inner join especificacao_componente esp_comp on esp_comp.id_especificacao_componente = maquina_especifc.fk_especificacao_componente\n"
                + "where adm.email_administrador = '%s' and adm.senha_administrador = '%s';", email, senha),
                new BeanPropertyRowMapper(MaquinaUltrassomEspecificada.class));

        for (MaquinaUltrassomEspecificada componentesEspecificado : componentesEspecificados) {
            fraseEspecComp = String.format(
                      "\n\n   Especificação do Componente:\n"
                    + "      Número Serial: %s\n"
                    + "      Uso Máximo: %.0f%%\n"
                    + "      Frequência: %.0fhz",
                    componentesEspecificado.getNumero_serial(),
                    componentesEspecificado.getUso_maximo(), componentesEspecificado.getFrequencia_maxima());
        }
        
        System.out.println(fraseCabecalho);
        System.out.println(fraseAdm);
        System.out.println(fraseEmp);
        System.out.println(fraseMaquiResp);
        System.out.println(fraseComponentes);
        System.out.println(fraseEspecComp);
    }
}
