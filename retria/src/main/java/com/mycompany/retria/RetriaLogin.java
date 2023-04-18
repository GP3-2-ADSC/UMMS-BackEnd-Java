/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.retria;

import com.mycompany.retria.DAO.ClienteDAO;
import com.mycompany.retria.DAO.Conexao;
import java.awt.Toolkit;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class RetriaLogin extends javax.swing.JFrame {

    /**
     * Creates new form RetriaLogin
     */
    public RetriaLogin() {
        initComponents();
        setIcon();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Painel_Logo = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Painel_Campos = new javax.swing.JPanel();
        labelEmail = new javax.swing.JLabel();
        campoEmail = new javax.swing.JTextField();
        labelSenha = new javax.swing.JLabel();
        campo_senha = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        botaoLogar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        setMaximumSize(new java.awt.Dimension(820, 500));
        setMinimumSize(getMaximumSize());
        setPreferredSize(new java.awt.Dimension(820, 500));
        setSize(new java.awt.Dimension(820, 500));
        setType(java.awt.Window.Type.POPUP);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        getContentPane().setLayout(null);

        Painel_Logo.setBackground(new java.awt.Color(140, 204, 240));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logoRetria.png"))); // NOI18N
        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout Painel_LogoLayout = new javax.swing.GroupLayout(Painel_Logo);
        Painel_Logo.setLayout(Painel_LogoLayout);
        Painel_LogoLayout.setHorizontalGroup(
            Painel_LogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_LogoLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        Painel_LogoLayout.setVerticalGroup(
            Painel_LogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Painel_LogoLayout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(249, Short.MAX_VALUE))
        );

        getContentPane().add(Painel_Logo);
        Painel_Logo.setBounds(0, 0, 330, 500);

        Painel_Campos.setBackground(new java.awt.Color(255, 255, 255));
        Painel_Campos.setLayout(null);

        labelEmail.setBackground(new java.awt.Color(255, 255, 255));
        labelEmail.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        labelEmail.setForeground(new java.awt.Color(0, 0, 0));
        labelEmail.setText("Email");
        labelEmail.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Painel_Campos.add(labelEmail);
        labelEmail.setBounds(120, 150, 44, 24);

        campoEmail.setBackground(new java.awt.Color(255, 255, 255));
        campoEmail.setFont(new java.awt.Font("Fira Sans", 0, 14)); // NOI18N
        campoEmail.setForeground(new java.awt.Color(0, 0, 0));
        campoEmail.setAlignmentX(0.0F);
        campoEmail.setAlignmentY(0.0F);
        campoEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        campoEmail.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        campoEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoEmailActionPerformed(evt);
            }
        });
        Painel_Campos.add(campoEmail);
        campoEmail.setBounds(120, 170, 240, 30);

        labelSenha.setBackground(new java.awt.Color(255, 255, 255));
        labelSenha.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        labelSenha.setForeground(new java.awt.Color(0, 0, 0));
        labelSenha.setText("Senha");
        Painel_Campos.add(labelSenha);
        labelSenha.setBounds(120, 230, 52, 24);

        campo_senha.setBackground(new java.awt.Color(255, 255, 255));
        campo_senha.setFont(new java.awt.Font("Fira Sans", 0, 14)); // NOI18N
        campo_senha.setForeground(new java.awt.Color(0, 0, 0));
        campo_senha.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        campo_senha.setMargin(new java.awt.Insets(0, 6, 2, 6));
        campo_senha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_senhaActionPerformed(evt);
            }
        });
        Painel_Campos.add(campo_senha);
        campo_senha.setBounds(120, 250, 240, 30);

        jLabel3.setFont(new java.awt.Font("Montserrat", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(140, 204, 240));
        jLabel3.setText("Login");
        Painel_Campos.add(jLabel3);
        jLabel3.setBounds(20, 30, 96, 47);

        botaoLogar.setBackground(new java.awt.Color(255, 255, 255));
        botaoLogar.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        botaoLogar.setForeground(new java.awt.Color(140, 204, 240));
        botaoLogar.setText("ENTRAR");
        botaoLogar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 204, 240)));
        botaoLogar.setContentAreaFilled(false);
        botaoLogar.setFocusPainted(false);
        botaoLogar.setFocusTraversalPolicyProvider(true);
        botaoLogar.setFocusable(false);
        botaoLogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLogarActionPerformed(evt);
            }
        });
        Painel_Campos.add(botaoLogar);
        botaoLogar.setBounds(160, 320, 160, 40);

        getContentPane().add(Painel_Campos);
        Painel_Campos.setBounds(330, 0, 490, 500);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void campoEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoEmailActionPerformed

    }//GEN-LAST:event_campoEmailActionPerformed

    private void campo_senhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_senhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_senhaActionPerformed

    private void botaoLogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLogarActionPerformed
        String email = (campoEmail.getText());
        String senha = (campo_senha.getText());
        botaoLogar.setRequestFocusEnabled(false);
        botaoLogar.setRolloverEnabled(false);

        ClienteDAO clienteDAO = new ClienteDAO();
        if (clienteDAO.consultar(email, senha)) {
            new LoginValidado().setVisible(true);
        } else {
            new LoginInvalido().setVisible(true);
        }
    }//GEN-LAST:event_botaoLogarActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConnection();

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RetriaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RetriaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RetriaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RetriaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RetriaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Painel_Campos;
    private javax.swing.JPanel Painel_Logo;
    private javax.swing.JButton botaoLogar;
    private javax.swing.JTextField campoEmail;
    private javax.swing.JPasswordField campo_senha;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelSenha;
    // End of variables declaration//GEN-END:variables

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/logoRetria.png")));

    }
}
