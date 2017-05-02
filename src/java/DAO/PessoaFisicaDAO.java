/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.PessoaFisica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ana Gonçalo
 */
public class PessoaFisicaDAO {
    private Connection conn;
    
    public PessoaFisicaDAO(){
        super();
        conn = FabricaConexao.getInstancia().getConexao();
    }
    
    public List<PessoaFisica> byId(int idUsuario) throws SQLException
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<PessoaFisica> lista = new ArrayList();
        
        try
        {
            String comandoSql= "SELECT * FROM PessoaFisica pf inner join usuario on usuario.idUsuario = pf.idHelper where "
                + "Usuario.idPermissao = 1 AND idUsuario = ?";
            
            pstmt = conn.prepareStatement(comandoSql);
            pstmt.setInt(1, idUsuario);
            
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                PessoaFisica pf = new PessoaFisica(rs.getInt("idHelper"), rs.getString("cpf"), rs.getInt("idUsuario"), rs.getString("nomeUsuario"), 
                                        rs.getString("email"), rs.getString("senha"), rs.getString("dataNascimento"), rs.getString("foto"), 
                                        rs.getString("localizacao"), rs.getString("telefone"), rs.getInt("idPermissao"));    
                lista.add(pf);
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(PessoaFisicaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            pstmt.close();
            rs.close();
        } 
        return lista;
    }
    
    public List<PessoaFisica> listarHelpers() throws SQLException
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<PessoaFisica> lista = new ArrayList();
        
        try
        {
            String comandoSql= "SELECT * FROM PessoaFisica pf inner join usuario on usuario.idUsuario = pf.idHelper where "
                + "Usuario.idPermissao = 1";
            
            pstmt = conn.prepareStatement(comandoSql);
            
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                PessoaFisica pf = new PessoaFisica(rs.getInt("idHelper"), rs.getString("cpf"), rs.getInt("idUsuario"), rs.getString("nomeUsuario"), 
                                        rs.getString("email"), rs.getString("senha"), rs.getString("dataNascimento"), rs.getString("foto"), 
                                        rs.getString("localizacao"), rs.getString("telefone"), rs.getInt("idPermissao"));    
                lista.add(pf);
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(PessoaFisicaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            pstmt.close();
            rs.close();
        } 
        return lista;
    }
    
    public void editarPF(PessoaFisica pf) throws SQLException
    {
        System.out.println("Testando editar DAO PF " + pf.getIdUsuario());
        PreparedStatement pstmt = null;
        
        try
        {
            String comandoSql = "UPDATE PessoaFisica SET cpf = ? WHERE idHelper = ?";
            pstmt = conn.prepareStatement(comandoSql);
            pstmt.setString(1, pf.getCpf());
            pstmt.setInt(2, pf.getIdHelper());
           
            pstmt.executeUpdate();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(PessoaFisicaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally
        {
            pstmt.close();
        } 
    }
}
