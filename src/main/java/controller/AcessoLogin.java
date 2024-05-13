/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import server.Conexao;
/**
 *
 * @author tiago
 */
public class AcessoLogin {
    
       public boolean verificarLogin(String nome, String senha) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean loginSucesso = false;

        try {
            // Obtém a conexão com o banco de dados
            conn = Conexao.conectar();

            // Consulta SQL para verificar se o usuário e a senha correspondem a uma entrada na tabela usuário
            String sql = "SELECT * FROM tb_usuario WHERE nome = ? AND senha = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();

            // Se a consulta retornar pelo menos uma linha, o login é bem-sucedido
            if (rs.next()) {
                loginSucesso = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Feche os recursos
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                // Não feche a conexão aqui, pois precisamos dela fora deste método
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Feche a conexão separadamente após a conclusão do método
        Conexao.fecharConexao(conn);

        return loginSucesso;
    }
}
