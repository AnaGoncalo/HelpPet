/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Evento;
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
public class EventoDAO {
    private Connection conn;
    
    public EventoDAO(){
        super();
        conn = FabricaConexao.getInstancia().getConexao();
    }
    
    public String CadastrarEvento(Evento evento) throws SQLException
    {
        System.out.println("evento dao");
        PreparedStatement pstmt = null;
        
        try
        {
            String comandoSql = "INSERT INTO Evento(nomeEvento, dataEvento, horarioEvento, descricaoEvento, fotoEvento, idUsuario,"
                + " localizacao) values(?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(comandoSql);
            pstmt.setString(1, evento.getNomeEvento());
            pstmt.setString(2, evento.getDataEvento());
            pstmt.setString(3, evento.getHorarioEvento());
            pstmt.setString(4, evento.getDescricaoEvento());
            pstmt.setString(5, evento.getFotoEvento());
            pstmt.setInt(6, evento.getIdUsuario());
            pstmt.setString(7, evento.getlocalizacao());
           
            pstmt.executeUpdate();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(EventoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally
        {
            FabricaConexao.closeConexao(conn, null, pstmt, null);
        }
        return "OK!";
    }
    
    public String EditarEvento(Evento evento) throws SQLException
    {
        System.out.println("evento dao editar");
        
        PreparedStatement pstmt = null;
        
        try
        {
            String comandoSql = "UPDATE Evento SET nomeEvento = ?, dataEvento = ?, horarioEvento = ?, descricaoEvento = ?, "
                + "fotoEvento = ?, idUsuario = ?, localizacao = ? WHERE idEvento = ?";
            pstmt = conn.prepareStatement(comandoSql);
            pstmt.setString(1, evento.getNomeEvento());
            pstmt.setString(2, evento.getDataEvento());
            pstmt.setString(3, evento.getHorarioEvento());
            pstmt.setString(4, evento.getDescricaoEvento());
            pstmt.setString(5, evento.getFotoEvento());
            pstmt.setInt(6, evento.getIdUsuario());
            pstmt.setString(7, evento.getlocalizacao());
            pstmt.setInt(8, evento.getIdEvento());
           
            pstmt.executeUpdate();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(EventoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally
        {
            FabricaConexao.closeConexao(conn, null, pstmt, null);
        }
        return "OK!";
    }
    
    public String ExcluirEvento(int idEvento) throws SQLException
    {
        System.out.println("evento dao excluir");
        PreparedStatement pstmt = null;
        
        try
        {
            String comandoSql = "DELETE FROM Evento WHERE idEvento = ?";
            pstmt = conn.prepareStatement(comandoSql);
            pstmt.setInt(1, idEvento);
           
            pstmt.executeUpdate();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(EventoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally
        {
            FabricaConexao.closeConexao(conn, null, pstmt, null);
        }
        return "OK!";
    }
    
    public List<Evento> ListarEventos() throws SQLException
    {
        ResultSet rs = null;
        Statement stmt= null;
        List<Evento> lista = new ArrayList();
        
        try
        {
            String sql= "SELECT * FROM Evento";
            stmt = conn.createStatement();
            rs= stmt.executeQuery(sql);
            while(rs.next())
            { 
                Evento a = new Evento(rs.getInt("idEvento"), rs.getString("nomeEvento"), rs.getString("dataEvento"), 
                        rs.getString("horarioEvento"), rs.getString("descricaoEvento"), rs.getString("fotoEvento"),
                        rs.getInt("idUsuario"), rs.getString("localizacao"));
                lista.add(a);
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(EventoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            FabricaConexao.closeConexao(conn, rs, null, stmt);
        } 
        return lista;
    }
    
    public List<Evento> ListarPorUsuario(int idUsuario) throws SQLException
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Evento> lista = new ArrayList();
        
        try
        {
            String sql= "SELECT * FROM Evento WHERE idUsuario = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idUsuario);
            
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                Evento a = new Evento(rs.getInt("idEvento"), rs.getString("nomeEvento"), rs.getString("dataEvento"), 
                        rs.getString("horarioEvento"), rs.getString("descricaoEvento"), rs.getString("fotoEvento"),
                        rs.getInt("idUsuario"), rs.getString("localizacao"));
                lista.add(a);
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(EventoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            FabricaConexao.closeConexao(conn, rs, pstmt, null);
        } 
        return lista;
    }
}
