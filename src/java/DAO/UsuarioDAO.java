/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ana Gonçalo
 */
public class UsuarioDAO {
    
    public void inserir(Usuario usuario) throws SQLException
    {
        System.out.println("Testando inserir DAo Usuario");
        Connection conn = Banco.getConexao();
        PreparedStatement pstmt = null;
        String comandoSql = "INSERT INTO Usuario(nomeUsuario, email, senha, dataNascimento, foto, idPermissao) values(?, ?, ?, ?, ?, ?)";
        try
        {
            pstmt = conn.prepareStatement(comandoSql);
            pstmt.setString(1, usuario.getNomeUsuario());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, usuario.getSenha());
            pstmt.setDate(4, null);
            pstmt.setString(5, usuario.getFoto());
            pstmt.setInt(6, usuario.getIdPermissao());
           
            pstmt.executeUpdate();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally
        {
            Banco.closeConexao(conn, null, pstmt, null);
        } 
    }
    
    public Usuario buscarById(int idUsuario) throws SQLException
    {
        Connection conn = Banco.getConexao();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Usuario u = null;
        String comandoSql= "SELECT * FROM Usuario WHERE Usuario.idUsuario = ?";
        try
        {
            pstmt = conn.prepareStatement(comandoSql);
            pstmt.setInt(1, idUsuario);
            
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                u = new Usuario(rs.getInt("idUsuario"), rs.getString("nomeUsuario"), rs.getString("email"), rs.getString("senha"),
                        rs.getDate("dataNascimento"), rs.getString("foto"), rs.getInt("idPermissao"));
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            Banco.closeConexao(conn, rs, pstmt, null);
        } 
        return u;
    }
    
    public Usuario logar(String email, String senha) throws SQLException
    {
        System.out.println("DAO.UsuarioDAO.logar()");
        Connection conn = Banco.getConexao();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Usuario usuario = null;
        
        String comandoSql= "SELECT * FROM Usuario WHERE Usuario.email = ? AND Usuario.senha = ?";
        try
        {
            pstmt = conn.prepareStatement(comandoSql);
            pstmt.setString(1, email);
            pstmt.setString(2, senha);
            
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                usuario = new Usuario(rs.getInt("idUsuario"), rs.getString("nomeUsuario"), rs.getString("email"), rs.getString("senha"),
                        rs.getDate("dataNascimento"), rs.getString("foto"), rs.getInt("idPermissao"));
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            Banco.closeConexao(conn, rs, pstmt, null);
        } 
        return usuario;
    }
    
}
