/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import server.Conexao;

/**
 *
 * @author tiago
 */
public class Consulta {
      public static void filtro(JTable table, String filtroTitulo, String filtroAutor, String filtroGenero, String filtroIsbn) {
    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) table.getModel());
    table.setRowSorter(sorter);

    List<RowFilter<Object, Object>> filters = new ArrayList<>();

    // Adiciona filtro para o título, se fornecido
    if (filtroTitulo != null && !filtroTitulo.isEmpty()) {
    RowFilter<Object, Object> rowFilterTitulo = RowFilter.regexFilter(filtroTitulo, 0); // 0 é o índice da coluna do título
    filters.add(rowFilterTitulo);
    }

// Adiciona filtro para o autor, se fornecido
    if (filtroAutor != null && !filtroAutor.isEmpty()) {
    RowFilter<Object, Object> rowFilterAutor = RowFilter.regexFilter(filtroAutor, 1); // 1 é o índice da coluna do autor
    filters.add(rowFilterAutor);
    }
    // Adiciona filtro para o genero, se fornecido
    if (filtroGenero != null && !filtroGenero.isEmpty()) {
    RowFilter<Object, Object> rowFilterGenero = RowFilter.regexFilter(filtroGenero, 2); // 1 é o índice da coluna do autor
    filters.add(rowFilterGenero);
    }
     // Adiciona filtro para o Isbn, se fornecido
    if (filtroIsbn != null && !filtroIsbn.isEmpty()) {
    RowFilter<Object, Object> rowFilterIsbn = RowFilter.regexFilter(filtroIsbn, 3); // 1 é o índice da coluna do autor
    filters.add(rowFilterIsbn);
    }


// Combina os filtros usando RowFilter.andFilter() se houver mais de um filtro
    if (!filters.isEmpty()) {
    RowFilter<Object, Object> compoundRowFilter = RowFilter.andFilter(filters);
    sorter.setRowFilter(compoundRowFilter);
    } else {
    table.setRowSorter(null); // Remove qualquer filtro aplicado anteriormente se nenhum filtro estiver presente
    }



}
    // Método para carregar os dados do banco na tabela especificada
    public static void carregarDadosDoBanco(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Limpa a tabela antes de carregar novos dados
        

       Conexao conexao = new Conexao();
        PreparedStatement stmt = null;

        try {
            // Estabelece a conexão com o banco de dados usando o arquivo de conexão
             Connection conn = conexao.conecta();

            // Prepara a consulta SQL para recuperar os títulos dos livros
            String sql = "SELECT TITULO, AUTOR, GENERO, ISBN FROM tb_livro";
            stmt = conn.prepareStatement(sql);

            // Executa a consulta
            ResultSet resultSet = stmt.executeQuery();
            // Popula o modelo de dados do JTable com os títulos dos livros
            while (resultSet.next()) {
                String titulo = resultSet.getString("TITULO");
                String autor = resultSet.getString("AUTOR");
                String genero = resultSet.getString("GENERO");
                String isbn = resultSet.getString("ISBN");
                model.addRow(new Object[]{titulo, autor, genero, isbn });
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
           
        }
    }
}
