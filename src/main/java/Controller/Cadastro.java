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
         System.out.println("Método atualizarLivro recebido com livro: " + livro.getTitulo() + " - ISBN: " + livro.getIsbn() + "id:" + livro.getId());
    String sql = "UPDATE tb_livro SET TITULO = ?, ISBN = ?, AUTOR = ?, EDITORA = ?, ANO_PUBLICACAO = ?, GENERO = ?, NUMERO_PAGINAS = ?, QTD_LIVROS = ? WHERE ID_LIVRO = ?";
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

        System.out.println("ID do livro a ser atualizado: " + livro.getId());

        int rowsAffected = ps.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Livro atualizado com sucesso!");
        } else {
            System.out.println("Nenhum livro atualizado. Verifique o ID do livro.");
        }
    } catch (SQLException e) {
        System.out.println("Erro ao atualizar livro: " + e.getMessage());
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

    
    public Livro consultarLivroPorId(int id) {
    String sql = "SELECT * FROM tb_livro WHERE ID_LIVRO = ?";
    PreparedStatement ps = null;
    Connection conn = null;
    ResultSet rs = null;
    Livro livro = null;

    try {
        conn = new Conexao().conecta();
        if (conn != null) {
            System.out.println("Conexão estabelecida com sucesso.");
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                livro = new Livro();
                livro.setId(rs.getInt("ID_LIVRO"));
                livro.setTitulo(rs.getString("TITULO"));
                livro.setIsbn(rs.getString("ISBN"));
                livro.setAutor(rs.getString("AUTOR"));
                livro.setEditora(rs.getString("EDITORA"));
                livro.setPublicado(rs.getString("ANO_PUBLICACAO"));
                livro.setGenero(rs.getString("GENERO"));
                livro.setPaginas(rs.getInt("NUMERO_PAGINAS"));
                livro.setQtd_livros(rs.getInt("QTD_LIVROS"));
                System.out.println("Livro encontrado: " + livro.getTitulo());
            } else {
                System.out.println("Nenhum livro encontrado com o ID: " + id);
            }
        } else {
            System.out.println("Falha ao estabelecer a conexão.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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

    return livro;
}


  
}
