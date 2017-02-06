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
    
    public static String EditarAnuncio(Anuncio anuncio) throws SQLException
    {
        System.out.println("anuncio dao editar");
        Connection conn = Banco.getConexao();
        PreparedStatement pstmt = null;
        String comandoSql = "UPDATE Anuncio SET tituloAnuncio = ?, descricaoAnuncio = ?, fotoAnuncio = ?, tipoAnuncio = ?, "
                + "idUsuario = ? WHERE idAnuncio = ?";
        try
        {
            pstmt = conn.prepareStatement(comandoSql);
            pstmt.setString(1, anuncio.getTituloAnuncio());
            pstmt.setString(2, anuncio.getDescricaoAnuncio());
            pstmt.setString(3, anuncio.getFotoAnuncio());
            pstmt.setString(4, anuncio.getTipoAnuncio());
            pstmt.setInt(5, anuncio.getIdUsuario());
            pstmt.setInt(6, anuncio.getIdAnuncio());
           
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
    
    public static String ExcluirAnuncio(int idAnuncio) throws SQLException
    {
        System.out.println("DAO Anuncio Excluir " + idAnuncio);
        Connection conn = Banco.getConexao();
        PreparedStatement pstmt = null;
        String comandoSql = "DELETE FROM Anuncio WHERE idAnuncio = ?";
        try
        {
            pstmt = conn.prepareStatement(comandoSql);
            pstmt.setInt(1, idAnuncio);
            
            pstmt.executeUpdate();
            System.out.println("Resultado excluir DAO");// + pstmt.executeUpdate());
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
    
    public static List<Anuncio> ListarPorUsuario(int idUsuario) throws SQLException
    {
        Connection conn = Banco.getConexao();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Anuncio> lista = new ArrayList();
        String sql= "SELECT * FROM Anuncio WHERE idUsuario = ?";
        try
        {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idUsuario);
            
            rs = pstmt.executeQuery();
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
            Banco.closeConexao(conn, rs, pstmt, null);
        } 
        return lista;
    }
}
