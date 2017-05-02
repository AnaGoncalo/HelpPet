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
    
    private Connection conn;
    
    public AnuncioDAO(){
        super();
        conn = FabricaConexao.getInstancia().getConexao();
    }
    
    public String CadastrarAnuncio(Anuncio anuncio) throws SQLException
    {
        System.out.println("anuncio dao");
        PreparedStatement pstmt = null;
        
        try
        {
            String comandoSql = "INSERT INTO Anuncio(tituloAnuncio, descricaoAnuncio, fotoAnuncio, tipoAnuncio, idUsuario)"
                + " values(?, ?, ?, ?, ?)";
            
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
            pstmt.close();
        }
        return "OK!";
    }
    
    public String EditarAnuncio(Anuncio anuncio) throws SQLException
    {
        System.out.println("anuncio dao editar");
        
        PreparedStatement pstmt = null;
        
        try
        {
            String comandoSql = "UPDATE Anuncio SET tituloAnuncio = ?, descricaoAnuncio = ?, fotoAnuncio = ?, tipoAnuncio = ?, "
                + "idUsuario = ? WHERE idAnuncio = ?";
            
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
            pstmt.close();
        }
        return "OK!";
    }
    
    public String ExcluirAnuncio(int idAnuncio) throws SQLException
    {
        System.out.println("DAO Anuncio Excluir " + idAnuncio);
        
        PreparedStatement pstmt = null;
        
        try
        {
            String comandoSql = "DELETE FROM Anuncio WHERE idAnuncio = ?";
            
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
            pstmt.close();
        }
        return "OK!";
    }
    
    public List<Anuncio> ListarAnuncios() throws SQLException
    {
        ResultSet rs = null;
        Statement stmt= null;
        List<Anuncio> lista = new ArrayList();
        
        try
        {
            String sql= "SELECT * FROM Anuncio";
            
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
            rs.close();
        } 
        return lista;
    }
    
    public List<Anuncio> ListarPorUsuario(int idUsuario) throws SQLException
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Anuncio> lista = new ArrayList();
        
        try
        {
            String sql= "SELECT * FROM Anuncio WHERE idUsuario = ?";
            
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
            rs.close();
        } 
        return lista;
    }
}
