/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Anuncio;
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
public class AnuncioDAO {
    
    public static String CadastrarAnuncio(Anuncio anuncio) throws SQLException
    {
        System.out.println("anuncio dao");
        Connection conn = Banco.getConexao();
        PreparedStatement pstmt = null;
        String comandoSql = "INSERT INTO Anuncio(tituloAnuncio, descricaoAnuncio, fotoAnuncio, tipoAnuncio, idUsuario)"
                + " values(?, ?, ?, ?, ?)";
        try
        {
            pstmt = conn.prepareStatement(comandoSql);
            pstmt.setString(1, anuncio.getTituloAnuncio());
            pstmt.setString(2, anuncio.getDescricaoAnuncio());
            pstmt.setString(3, anuncio.getFotoAnuncio());
            pstmt.setString(4, anuncio.getTipoAnuncio());
            pstmt.setInt(5, anuncio.getIdUsuario());
           
            pstmt.executeUpdate();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(AnuncioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally
        {
            Banco.closeConexao(conn, null, pstmt, null);
        }
        return "OK!";
    }
    
    public static List<Anuncio> ListarAnuncios() throws SQLException
    {
        Connection conn = Banco.getConexao();
        ResultSet rs = null;
        Statement stmt= null;
        List<Anuncio> lista = new ArrayList();
        String sql= "SELECT * FROM Anuncio";
        try
        {
            stmt = conn.createStatement();
            rs= stmt.executeQuery(sql);
            while(rs.next())
            { 
                Anuncio a = new Anuncio(rs.getInt("idAnuncio"), rs.getString("tituloAnuncio"), rs.getString("descricaoAnuncio"), 
                        rs.getString("fotoAnuncio"), rs.getString("tipoAnuncio"), rs.getInt("idUsuario"));
                lista.add(a);
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(AnuncioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            Banco.closeConexao(conn, rs, null, stmt);
        } 
        return lista;
    }
}
