/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.senacbiblioteca;

import java.sql.Connection;
import java.sql.SQLException;
import server.Conexao;

/**
 *
 * @author tiago
 */
public class SenacBiblioteca {

    public static void main(String[] args) {
        Conexao conexao = new Conexao();
        
        Connection conn = conexao.conecta();
        
        if(conn !=null){
                  System.out.println("Conexão estabelecida !");  
                  try {
                conn.close();
                      System.out.println("Conexão fechada !");
            } catch (SQLException e) {
                      System.out.println("Error" + e.getMessage());
            }
        }else{
            System.out.println("Falha ao se conectar");
        }
                

    }
}
