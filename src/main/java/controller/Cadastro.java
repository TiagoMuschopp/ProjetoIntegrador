/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import server.Conexao;
/**
 *
 * @author tiago
 */
public class Cadastro {
    
  
      public static void cadastrarLivro(String titulo, String isbn, String autor, String editora, String anoPublicacao, String genero, String paginas) {
        Connection conexao = null;
        PreparedStatement stmt = null;
          try{
       conexao = Conexao.conectar();
        String sql = "INSERT INTO tb_livro(TITULO, AUTOR, GENERO, ANO, ISBN, NUM_PAGINAS, EDITORA) VALUES (?, ?, ?, ?, ?, ?, ?)";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, titulo);
            stmt.setString(2, autor);
            stmt.setString(3, genero);
            stmt.setString(4, anoPublicacao);
            stmt.setString(5, isbn);
            stmt.setString(6, paginas);
            stmt.setString(7, editora);
            stmt.executeUpdate();
            System.out.println("Livro inserido com sucesso!");
            JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
      }catch(SQLException e){
            System.err.println("Erro ao inserir livro no banco de dados: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar livro.", "Erro", JOptionPane.ERROR_MESSAGE);
          
      }finally{
           if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    
                    e.printStackTrace();
                }
            }
            // Fechando a conexão usando o método da classe Conexao
            Conexao.fecharConexao(conexao);
      }
        
       

        
    }
}
