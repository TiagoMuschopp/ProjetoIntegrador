/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import server.Conexao;

/**
 *
 * @author tiago
 */
public class Usuarios {
    private static final String sql = "INSERT INTO tb_usuario(NOME, CPF, TELEFONE) VALUES (?, ?, ?)";
    public void enviar(String nome, String cpf, String telefone){
        
         if (nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos!");
            return; // Retorna sem executar a inserção no banco de dados
        }
        Conexao conexao = new Conexao();
         try (Connection conn = conexao.conecta();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setString(3, telefone);

            stmt.executeUpdate();
             JOptionPane.showMessageDialog(null, "Usuário inserido com Sucesso !");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    }
}
