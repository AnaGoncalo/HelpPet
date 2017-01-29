/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Denuncia;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ana Gonçalo
 */
public class DenunciaDAO {
    
    public static String CadastrarDenuncia(Denuncia denuncia) throws SQLException
    {
        System.out.println("denuncia dao");
        Connection conn = Banco.getConexao();
        PreparedStatement pstmt = null;
        String comandoSql = "INSERT INTO Denuncia(tituloDenuncia, descricaoDenuncia, fotoDenuncia, tipoDenuncia,"
                + "localizacao) values(?, ?, ?, ?, ?)";
        try
        {
            pstmt = conn.prepareStatement(comandoSql);
            pstmt.setString(1, denuncia.getTituloDenuncia());
            pstmt.setString(2, denuncia.getDescricaoDenuncia());
            pstmt.setString(3, denuncia.getFotoDenuncia());
            pstmt.setString(4, denuncia.getTipoDenuncia());
            pstmt.setString(5, denuncia.getLocalizacao());
           
            pstmt.executeUpdate();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(DenunciaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally
        {
            Banco.closeConexao(conn, null, pstmt, null);
        }
        return "OK!";
    }
    
    public static List<Denuncia> ListarDenuncias() throws SQLException
    {
        Connection conn = Banco.getConexao();
        ResultSet rs = null;
        Statement stmt= null;
        List<Denuncia> lista = new ArrayList();
        String sql= "SELECT * FROM Denuncia";
        try
        {
            stmt = conn.createStatement();
            rs= stmt.executeQuery(sql);
            while(rs.next())
            { 
                Denuncia a = new Denuncia(rs.getInt("idDenuncia"), rs.getString("tituloDenuncia"), 
                        rs.getString("descricaoDenuncia"),rs.getString("fotoDenuncia"), rs.getString("tipoDenuncia"), 
                        rs.getString("dataDenuncia"), rs.getString("localizacao"));
                lista.add(a);
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DenunciaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            Banco.closeConexao(conn, rs, null, stmt);
        } 
        return lista;
    }
}
