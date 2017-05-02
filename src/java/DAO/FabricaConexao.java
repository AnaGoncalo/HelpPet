/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ana Gonçalo
 */
public class FabricaConexao {

    private static FabricaConexao instancia = null;
    public Connection conn = null;

    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String BANCO = "jdbc:mysql://localhost:3306/HelpPet2";
    private final String USER = "root";
    private final String PASS = "ananne";

    private FabricaConexao() {
        super();
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(BANCO, USER, PASS);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            Logger.getLogger(FabricaConexao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
            Logger.getLogger(FabricaConexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConexao() {
        
        return conn;
    }
    
    public static FabricaConexao getInstancia(){
        if(instancia == null)
            instancia = new FabricaConexao();
        return instancia;
    }

    public static void closeConexao(Connection conn, ResultSet rs, PreparedStatement pstmt, Statement stmt) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                System.out.println("Error ao fechar o ResultSet");
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.out.println("Error ao fechar o statement");
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException ex) {
                System.out.println("Error ao fechar o prepared statement");
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Error ao carregar a conexão");
            }
        }
    }
}
