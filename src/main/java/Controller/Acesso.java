/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import server.Conexao;
import view.Tela_menu;

/**
 *
 * @author tiago
 */
public class Acesso {
    boolean loginSucesso = false;
    public boolean VerificarLogin(String nome, String senha){
         Conexao conexao = new Conexao();
         Connection conn = conexao.conecta();
         
         if(conn != null){
             String sql = "SELECT * FROM tb_usuario WHERE USERNAME = ? AND SENHA = ?";
             
             try {
                 PreparedStatement smt = conn.prepareStatement(sql);
                 smt.setString(1, nome);
                 smt.setString(2, senha);
                 ResultSet rs = smt.executeQuery();
                 
                 if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
                     loginSucesso = true;
                     Tela_menu menu = new Tela_menu();
                     menu.setExtendedState(menu.MAXIMIZED_BOTH);
                     menu.setVisible(true);
                     
                } else {
                    // Usuário não encontrado ou credenciais incorretas
                    JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos.");
                }
                
                rs.close();
                smt.close();
                conn.close();
             } catch (SQLException e) {
                  JOptionPane.showMessageDialog(null, "Erro ao executar consulta: " + e.getMessage());
             }
         }else {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados.");
        }
        return loginSucesso;
    }
}
