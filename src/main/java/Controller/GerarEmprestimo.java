/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import server.Conexao;

/**
 *
 * @author tiago
 */
public class GerarEmprestimo {
    int idUser;
    int idLivro;
      
    public int gerarIdUser(String cpf){
    Conexao conexao = new Conexao();
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Connection conn = conexao.conecta();

    int idUser = -1;

    try {
        
        String sql = "SELECT ID_USUARIO FROM tb_usuario WHERE CPF = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, cpf);

        // Executar a consulta
        rs = pstmt.executeQuery();

        // Verificar se algum resultado foi retornado
        if (rs.next()) {
          
            idUser = rs.getInt("ID_USUARIO");
            System.out.println("id usuário: " + idUser);
        } else {
            System.out.println("Livro não encontrado no banco de dados");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Fechar os recursos
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            // Não fechamos a conexão aqui porque é gerenciada externamente
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retornar o nome encontrado (pode ser null se o CPF não foi encontrado)
    return idUser;
    }
    
    public int gerarIdLirvo(String isbn){
    Conexao conexao = new Conexao();
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Connection conn = conexao.conecta();

    int idLivro = -1;

    try {
        
        String sql = "SELECT ID_LIVRO FROM tb_livro WHERE ISBN = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, isbn);

        // Executar a consulta
        rs = pstmt.executeQuery();

        // Verificar se algum resultado foi retornado
        if (rs.next()) {
          
            idLivro = rs.getInt("ID_LiVRO");
            System.out.println("id Livro: " + idLivro);
        } else {
            System.out.println("Livro não encontrado no banco de dados");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Fechar os recursos
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            // Não fechamos a conexão aqui porque é gerenciada externamente
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retornar o nome encontrado (pode ser null se o CPF não foi encontrado)
    return idLivro;
    }

}
