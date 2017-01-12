/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.PessoaFisica;
import Modelo.PessoaJuridica;
import Modelo.Usuario;
import java.sql.Connection;
import java.sql.Date;
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
public class UsuarioDAO {
    
    public static void inserir(Usuario usuario) throws SQLException
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
                if(rs.getInt("idPermissao") == 1){
                    PessoaFisica pf = buscarPF(idUsuario);
                    u = new PessoaFisica(pf.getIdHelper(), pf.getCpf(), rs.getInt("idUsuario"), rs.getString("nomeUsuario"), rs.getString("email"), rs.getString("senha"),
                        rs.getDate("dataNascimento"), rs.getString("foto"), rs.getInt("idPermissao"));
                }
                else
                {
                    PessoaJuridica pj = buscarPJ(idUsuario);
                    u = new PessoaJuridica(pj.getIdClinicaPetshop(), pj.getCnpj(), pj.getFuncionamento(), pj.getDescricao(), pj.getSite(),
                                                rs.getInt("idUsuario"), rs.getString("nomeUsuario"), rs.getString("email"), rs.getString("senha"),
                                                rs.getDate("dataNascimento"), rs.getString("foto"), rs.getInt("idPermissao"));
                }
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
    
    public PessoaFisica buscarPF(int idUsuario) throws SQLException
    {
        Connection conn = Banco.getConexao();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PessoaFisica u = null;
        String comandoSql= "SELECT * FROM PessoaFisica WHERE idHelper = ?";
        try
        {
            pstmt = conn.prepareStatement(comandoSql);
            pstmt.setInt(1, idUsuario);
            
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                u = new PessoaFisica(rs.getInt("idHelper"), rs.getString("cpf"));   
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
    
    public PessoaJuridica buscarPJ(int idUsuario) throws SQLException
    {
        Connection conn = Banco.getConexao();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PessoaJuridica u = null;
        String comandoSql= "SELECT * FROM PessoaJuridica WHERE idClinicaPetshop = ?";
        try
        {
            pstmt = conn.prepareStatement(comandoSql);
            pstmt.setInt(1, idUsuario);
            
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                u = new PessoaJuridica(rs.getInt("idClinicaPetshop"), rs.getString("cnpj"), rs.getString("funcionamento"), 
                            rs.getString("descricao"), rs.getString("site"));
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
        Usuario u = null;
        
        String comandoSql= "SELECT * FROM Usuario WHERE Usuario.email = ? AND Usuario.senha = ?";
        try
        {
            pstmt = conn.prepareStatement(comandoSql);
            pstmt.setString(1, email);
            pstmt.setString(2, senha);
            
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                System.out.println("Usuario Logado" + rs.getString("nomeUsuario") + rs.getInt("idPermissao"));
                if(rs.getInt("idPermissao") == 1){
                    PessoaFisica pf = buscarPF(rs.getInt("idUsuario"));
                    System.out.println("Usuario logado " + pf.getCpf());
                    u = new PessoaFisica(pf.getIdHelper(), pf.getCpf(), rs.getInt("idUsuario"), rs.getString("nomeUsuario"), 
                            rs.getString("email"), rs.getString("senha"), rs.getDate("dataNascimento"), 
                            rs.getString("foto"), rs.getInt("idPermissao"));
                }
                else
                {
                    PessoaJuridica pj = buscarPJ(rs.getInt("idUsuario"));
                    u = new PessoaJuridica(pj.getIdClinicaPetshop(), pj.getCnpj(), pj.getFuncionamento(), pj.getDescricao(), pj.getSite(),
                            rs.getInt("idUsuario"), rs.getString("nomeUsuario"), rs.getString("email"), rs.getString("senha"),
                            rs.getDate("dataNascimento"), rs.getString("foto"), rs.getInt("idPermissao"));
                }
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
    
    public static List<PessoaJuridica> listarOngs() throws SQLException
    {
        Connection conn = Banco.getConexao();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<PessoaJuridica> lista = new ArrayList();
        String comandoSql= "SELECT * FROM PessoaJuridica pj inner join usuario on usuario.idUsuario = pj.idClinicaPetshop where Usuario.idPermissao = 2";
        try
        {
            pstmt = conn.prepareStatement(comandoSql);
            
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                PessoaJuridica pj = new PessoaJuridica(rs.getInt("idClinicaPetshop"), rs.getString("cnpj"), rs.getString("funcionamento"), 
                                        rs.getString("descricao"), rs.getString("site"), rs.getInt("idUsuario"), rs.getString("nomeUsuario"), 
                                        rs.getString("email"), rs.getString("senha"), rs.getDate("dataNascimento"), rs.getString("foto"), rs.getInt("idPermissao"));    
                lista.add(pj);
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
        return lista;
    }
    
    public static List<PessoaJuridica> listarClinicaPetshops() throws SQLException
    {
        Connection conn = Banco.getConexao();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<PessoaJuridica> lista = new ArrayList();
        String comandoSql= "SELECT * FROM PessoaJuridica pj inner join usuario on usuario.idUsuario = pj.idClinicaPetshop where Usuario.idPermissao = 3";
        try
        {
            pstmt = conn.prepareStatement(comandoSql);
            
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                PessoaJuridica pj = new PessoaJuridica(rs.getInt("idClinicaPetshop"), rs.getString("cnpj"), rs.getString("funcionamento"), 
                                        rs.getString("descricao"), rs.getString("site"), rs.getInt("idUsuario"), rs.getString("nomeUsuario"), 
                                        rs.getString("email"), rs.getString("senha"), rs.getDate("dataNascimento"), rs.getString("foto"), rs.getInt("idPermissao"));    
                lista.add(pj);
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
        return lista;
    }
}
