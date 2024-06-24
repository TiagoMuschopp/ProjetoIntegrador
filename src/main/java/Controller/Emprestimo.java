/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import server.Conexao;

/**
 *
 * @author tiago
 */
public class Emprestimo {
    String nome;
    Date date;
    
    public String gerarEmprestimo(String cpf){
        Conexao conexao = new Conexao();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = conexao.conecta();

        String nome = null;

        try {
            // Consulta SQL para verificar se o CPF existe na tabela adequada
            String sql = "SELECT Nome FROM tb_usuario WHERE CPF = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cpf);

            // Executar a consulta
            rs = pstmt.executeQuery();

            // Verificar se algum resultado foi retornado
            if (rs.next()) {
                // CPF encontrado, obter o nome da pessoa
                nome = rs.getString("Nome");
                System.out.println("CPF encontrado no banco de dados. Nome: " + nome);
            } else {
                System.out.println("CPF não encontrado no banco de dados");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fechar os recursos
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close(); // Fechar a conexão
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Retornar o nome encontrado (pode ser null se o CPF não foi encontrado)
        return nome;
    }
    
    public void gerarNovo(int id_usuario, int id_livro, String dataEmprestimo, String dataDevolucao){
        Conexao conexao = new Conexao();
        PreparedStatement pstmt = null;
        Connection conn = conexao.conecta();
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
        try {
             date = (Date) formatter.parse(dataEmprestimo); 
             System.out.println("Data: " + date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
            
       
        

        try {
            // Consulta SQL para inserir um novo empréstimo
            String insertSql = "INSERT INTO tb_emprestimo(ID_USUARIO, ID_LIVRO, DATA_EMPRESTIMO, DATA_DEVOLUCAO) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(insertSql);
            
            pstmt.setInt(1, id_usuario);
            pstmt.setInt(2, id_livro);
            pstmt.setDate(3, date);
            pstmt.setString(4, dataDevolucao);

            // Executar a inserção
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fechar os recursos
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close(); // Fechar a conexão
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
