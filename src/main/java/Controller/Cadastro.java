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
import model.Livro;
import server.Conexao;

public class Cadastro {
    
    public static void cadastrarLivro(String titulo, String isbn, String autor, String editora, String anoPublicacao, String genero, int paginas, int qtd_livros) {
         // Verificar se algum campo obrigatório está vazio
        if (titulo.isEmpty() || isbn.isEmpty() || autor.isEmpty() || editora.isEmpty() || anoPublicacao.isEmpty() || genero.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos", "Erro", JOptionPane.ERROR_MESSAGE);
        return; // Encerra o método se algum campo estiver vazio
        }
     if (paginas <= 0 || qtd_livros <= 0) {
        JOptionPane.showMessageDialog(null, "Deve-se adicionar pelo menos uma página e um livro", "Erro", JOptionPane.ERROR_MESSAGE);
        return; // Encerra o método se páginas ou quantidade de livros forem zero ou menos
    }
    
        
        Conexao conexao = new Conexao();
        Connection conn = null;
        PreparedStatement smt = null;
        ResultSet resultado = null;
        
        try {
            conn = conexao.conecta();
            
            // Consultar ISBN
            String consultar = "SELECT COUNT(*) FROM tb_livro WHERE ISBN = ?";
            smt = conn.prepareStatement(consultar);
            smt.setString(1, isbn);
            
            resultado = smt.executeQuery();
            
            if (resultado.next()) {
                int count = resultado.getInt(1);
                if (count > 0) {
                    JOptionPane.showMessageDialog(null, "ISBN já existe", "Falha", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Inserir livro se o ISBN não existir
                    String sql = "INSERT INTO tb_livro(TITULO, EDITORA, AUTOR, GENERO, ISBN, ANO_PUBLICACAO, NUMERO_PAGINAS, QTD_LIVROS) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    smt = conn.prepareStatement(sql);
                    smt.setString(1, titulo);
                    smt.setString(2, editora);
                    smt.setString(3, autor);
                    smt.setString(4, genero);
                    smt.setString(5, isbn);
                    smt.setString(6, anoPublicacao);
                    smt.setInt(7, paginas);
                    smt.setInt(8, qtd_livros);
                    
                    smt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Livro inserido com sucesso!");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir livro no banco de dados: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar livro.", "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Fechando os recursos
            if (resultado != null) {
                try {
                    resultado.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar o ResultSet: " + e.getMessage());
                }
            }
            if (smt != null) {
                try {
                    smt.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar o PreparedStatement: " + e.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar a conexão: " + e.getMessage());
                }
            }
        }
    }
    
    
    public void atualizarLivro(Livro livro) {
        String sql = "UPDATE tb_livro SET TITULO = ?, ISBN = ?, AUTOR = ?, EDITORA = ?, PUBLICADO = ?, GENERO = ?, PAGINAS = ?, QTD_LIVROS = ? WHERE ID = ?";
        PreparedStatement ps = null;
        Connection conn = null;

        try {
            conn = new Conexao().conecta();
            ps = conn.prepareStatement(sql);
            ps.setString(1, livro.getTitulo());
            ps.setString(2, livro.getIsbn());
            ps.setString(3, livro.getAutor());
            ps.setString(4, livro.getEditora());
            ps.setString(5, livro.getPublicado());
            ps.setString(6, livro.getGenero());
            ps.setInt(7, livro.getPaginas());
            ps.setInt(8, livro.getQtd_livros());
            ps.setInt(9, livro.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
