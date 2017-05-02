/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Experiencia;
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
public class ExperienciaDAO {
    private Connection conn;
    
    public ExperienciaDAO(){
        super();
        conn = FabricaConexao.getInstancia().getConexao();
    }
    
    public String CadastrarExperiencia(Experiencia experiencia) throws SQLException
    {
        System.out.println("experiencia dao");
        PreparedStatement pstmt = null;
        
        try
        {
            String comandoSql = "INSERT INTO Experiencia(tituloExperiencia, tipoExperiencia, texto, foto,"
                + "statusExperiencia, idUsuario) values(?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(comandoSql);
            pstmt.setString(1, experiencia.getTituloExperiencia());
            pstmt.setString(2, experiencia.getTipoExperiencia());
            pstmt.setString(3, experiencia.getTexto());
            pstmt.setString(4, experiencia.getFoto());
            pstmt.setBoolean(5, experiencia.isStatusExperiencia());
            pstmt.setInt(6, experiencia.getIdUsuario());
           
            pstmt.executeUpdate();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(ExperienciaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally
        {
            pstmt.close();
        }
        return "OK!";
    }
    
    public String EditarExperiencia(Experiencia experiencia) throws SQLException{
        
        System.out.println("experiencia dao editar");
        PreparedStatement pstmt = null;
        
        try
        {
            String comandoSql = "UPDATE Experiencia SET tituloExperiencia = ?, tipoExperiencia = ?, texto = ?, foto = ?,"
                + "statusExperiencia = ?, idUsuario = ? WHERE idExperiencia = ?";
            pstmt = conn.prepareStatement(comandoSql);
            pstmt.setString(1, experiencia.getTituloExperiencia());
            pstmt.setString(2, experiencia.getTipoExperiencia());
            pstmt.setString(3, experiencia.getTexto());
            pstmt.setString(4, experiencia.getFoto());
            pstmt.setBoolean(5, experiencia.isStatusExperiencia());
            pstmt.setInt(6, experiencia.getIdUsuario());
            pstmt.setInt(7, experiencia.getIdExperiencia());
           
            pstmt.executeUpdate();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(ExperienciaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally
        {
            pstmt.close();
        }
        return "Ok!";
    }
    
    public String ExcluirExperiencia(int idExperiencia) throws SQLException{
        
        System.out.println("experiencia dao excluir");
        PreparedStatement pstmt = null;
        
        try
        {
            String comandoSql = "DELETE FROM Experiencia WHERE idExperiencia = ?";
            pstmt = conn.prepareStatement(comandoSql);
            pstmt.setInt(1, idExperiencia);
           
            pstmt.executeUpdate();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(ExperienciaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally
        {
            pstmt.close();
        }
        return "Ok!";
    }
    
    public List<Experiencia> ListarExperiencias() throws SQLException
    {
        ResultSet rs = null;
        Statement stmt= null;
        List<Experiencia> lista = new ArrayList();
        
        try
        {
            String sql= "SELECT * FROM Experiencia";
            stmt = conn.createStatement();
            rs= stmt.executeQuery(sql);
            while(rs.next())
            { 
                Experiencia a = new Experiencia(rs.getInt("idExperiencia"), rs.getString("tituloExperiencia"), 
                        rs.getString("tipoExperiencia"), rs.getString("texto"), rs.getString("foto"), 
                        rs.getString("dataCadastro"), rs.getBoolean("statusExperiencia"), rs.getInt("idUsuario"));
                lista.add(a);
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ExperienciaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            rs.close();
        } 
        return lista;
    }
    
    public List<Experiencia> ListarPorUsuario(int idUsuario) throws SQLException
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Experiencia> lista = new ArrayList();
        
        try
        {
            String sql= "SELECT * FROM Experiencia WHERE idUsuario = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idUsuario);
            
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                Experiencia a = new Experiencia(rs.getInt("idExperiencia"), rs.getString("tituloExperiencia"), 
                        rs.getString("tipoExperiencia"), rs.getString("texto"), rs.getString("foto"), 
                        rs.getString("dataCadastro"), rs.getBoolean("statusExperiencia"), rs.getInt("idUsuario"));
                lista.add(a);
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ExperienciaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            rs.close();
        } 
        return lista;
    }
    
}
