/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author tiago
 */
public class Conexao {
    
    // Configurações de conexão com o banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/db_biblioteca";
    private static final String USUARIO = "root";
    private static final String SENHA = "701032";

    // Método para estabelecer a conexão com o banco de dados
    public static Connection conectar() {
        try {
            // Registrar o driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Estabelecer a conexão
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            return conexao;
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC não encontrado.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar-se ao banco de dados.");
            e.printStackTrace();
        }
        return null;
    }

    // Método para fechar a conexão com o banco de dados
    public static void fecharConexao(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão com o banco de dados.");
                e.printStackTrace();
            }
        }
    }
}