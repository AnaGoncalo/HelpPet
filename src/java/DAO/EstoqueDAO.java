/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Estoque;
import java.sql.Connection;
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
public class EstoqueDAO {
    private Connection conn;
    
    public EstoqueDAO(){
        super();
        conn = FabricaConexao.getInstancia().getConexao();
    }
    
    public String CadastrarEstoque(Estoque estoque) throws SQLException
    {
        System.out.println("estoque dao");
        PreparedStatement pstmt = null;
        
        try
        {
            String comandoSql = "INSERT INTO Estoque(nomeEstoque, necessidade, qtdDiaria, qtdAtual, idUsuario) "
                + "values(?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(comandoSql);
            pstmt.setString(1, estoque.getNomeEstoque());
            pstmt.setString(2, estoque.getNecessidade());
            pstmt.setDouble(3, estoque.getQtdDiaria());
            pstmt.setDouble(4, estoque.getQtdAtual());
            pstmt.setInt(5, estoque.getIdUsuario());
           
            pstmt.executeUpdate();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(EstoqueDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally
        {
            pstmt.close();
        }
        return "OK!";
    }
    
    public String EditarEstoque(Estoque estoque) throws SQLException
    {
        System.out.println("estoque dao editar");
        PreparedStatement pstmt = null;
        
        try
        {
            String comandoSql = "UPDATE Estoque SET nomeEstoque = ?, necessidade = ?, qtdDiaria = ?, qtdAtual = ?, idUsuario = ? "
                + "WHERE idEstoque = ?";
            pstmt = conn.prepareStatement(comandoSql);
            pstmt.setString(1, estoque.getNomeEstoque());
            pstmt.setString(2, estoque.getNecessidade());
            pstmt.setDouble(3, estoque.getQtdDiaria());
            pstmt.setDouble(4, estoque.getQtdAtual());
            pstmt.setInt(5, estoque.getIdUsuario());
            pstmt.setInt(6, estoque.getIdEstoque());
           
            pstmt.executeUpdate();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(EstoqueDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally
        {
            pstmt.close();
        }
        return "OK!";
    }
    
    public String ExcluirEstoque(int idEstoque) throws SQLException
    {
        System.out.println("estoque dao excluir");
        
        PreparedStatement pstmt = null;
        
        try
        {
            String comandoSql = "DELETE FROM Estoque WHERE idEstoque = ?";
            pstmt = conn.prepareStatement(comandoSql);
            pstmt.setInt(1, idEstoque);
           
            pstmt.executeUpdate();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(EstoqueDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally
        {
            pstmt.close();
        }
        return "OK!";
    }
    
    public List<Estoque> ListarEstoques() throws SQLException
    {
        ResultSet rs = null;
        Statement stmt= null;
        List<Estoque> lista = new ArrayList();
        
        try
        {
            String sql= "SELECT * FROM Estoque";
            stmt = conn.createStatement();
            rs= stmt.executeQuery(sql);
            while(rs.next())
            { 
                Estoque a = new Estoque(rs.getInt("idEstoque"), rs.getString("nomeEstoque"), rs.getString("necessidade"), 
                        rs.getDouble("qtdDiaria"), rs.getDouble("qtdAtual"), rs.getInt("idUsuario"));
                lista.add(a);
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(EstoqueDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            rs.close();
        } 
        return lista;
    }
    
    public List<Estoque> ListarPorUsuario(int idUsuario) throws SQLException
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Estoque> lista = new ArrayList();
        
        try
        {
            String sql= "SELECT * FROM Estoque WHERE idUsuario = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idUsuario);
            
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                Estoque a = new Estoque(rs.getInt("idEstoque"), rs.getString("nomeEstoque"), rs.getString("necessidade"), 
                        rs.getDouble("qtdDiaria"), rs.getDouble("qtdAtual"), rs.getInt("idUsuario"));
                lista.add(a);
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(EstoqueDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            rs.close();
        } 
        return lista;
    }
}
