/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import server.Conexao;

public class Tabela {

    // Método para carregar os dados do banco na tabela especificada
    public static void carregarDadosDoBanco(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Limpa a tabela antes de carregar novos dados

        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            // Estabelece a conexão com o banco de dados usando o arquivo de conexão
            conexao = Conexao.conectar();

            // Prepara a consulta SQL para recuperar os títulos dos livros
            String sql = "SELECT TITULO, GENERO FROM tb_livro";
            stmt = conexao.prepareStatement(sql);

            // Executa a consulta
            ResultSet resultSet = stmt.executeQuery();
            // Popula o modelo de dados do JTable com os títulos dos livros
            while (resultSet.next()) {
                String titulo = resultSet.getString("TITULO");
                String genero = resultSet.getString("GENERO");
                model.addRow(new Object[]{titulo, genero});
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados do banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Fecha os recursos
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


