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
    private Connection conn;
    
    public UsuarioDAO(){
        super();
        conn = FabricaConexao.getInstancia().getConexao();
    }
    
    public void inserir(Usuario usuario) throws SQLException
    {
        System.out.println("Testando inserir DAo Usuario");
        PreparedStatement pstmt = null;
        
        try
        {
            String comandoSql = "INSERT INTO Usuario(nomeUsuario, email, senha, dataNascimento, foto, localizacao, idPermissao) "
                + "values(?, ?, ?, ?, ?, ?, ?)";
            
            pstmt = conn.prepareStatement(comandoSql);
            pstmt.setString(1, usuario.getNomeUsuario());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, usuario.getSenha());
            pstmt.setDate(4, null);
            pstmt.setString(5, usuario.getFoto());
            pstmt.setString(6, usuario.getLocalizacao());
            pstmt.setInt(7, usuario.getIdPermissao());
           
            pstmt.executeUpdate();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally
        {
            pstmt.close();
        } 
    }
    
    public void editar(Usuario usuario) throws SQLException
    {
        System.out.println("Testando editar DAo Usuario");
        PreparedStatement pstmt = null;
        
        try
        {
            String comandoSql = "UPDATE Usuario SET nomeUsuario = ?, email = ?, senha = ?, dataNascimento = ?, foto = ?, "
                + "localizacao = ? WHERE idUsuario = ?";
            
            pstmt = conn.prepareStatement(comandoSql);
            pstmt.setString(1, usuario.getNomeUsuario());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, usuario.getSenha());
            pstmt.setString(4, usuario.getDataNascimento());
            pstmt.setString(5, usuario.getFoto());
            pstmt.setString(6, usuario.getLocalizacao());
            pstmt.setInt(7, usuario.getIdUsuario());
           
            pstmt.executeUpdate();
            System.out.println("editou usuario");
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally
        {
            pstmt.close();
        } 
    }
    
    public void excluir(int idUsuario) throws SQLException
    {
        System.out.println("Testando excluir DAO usuario");
        PreparedStatement pstmt = null;
        
        try
        {
            String comandoSql = "DELETE FROM Usuario WHERE idUsuario = ?";
            pstmt = conn.prepareStatement(comandoSql);
            pstmt.setInt(1, idUsuario);
           
            pstmt.executeUpdate();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally
        {
            pstmt.close();
        } 
    }
    
    public Usuario buscarById(int idUsuario) throws SQLException
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Usuario u = null;
        
        try
        {
            String comandoSql= "SELECT * FROM Usuario WHERE Usuario.idUsuario = ?";
            pstmt = conn.prepareStatement(comandoSql);
            pstmt.setInt(1, idUsuario);
            
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                if(rs.getInt("idPermissao") == 1){
                    PessoaFisica pf = buscarPF(idUsuario);
                    u = new PessoaFisica(pf.getIdHelper(), pf.getCpf(), rs.getInt("idUsuario"), rs.getString("nomeUsuario"), rs.getString("email"), rs.getString("senha"),
                        rs.getString("dataNascimento"), rs.getString("foto"), rs.getString("localizacao"), rs.getString("telefone"), rs.getInt("idPermissao"));
                }
                else
                {
                    PessoaJuridica pj = buscarPJ(idUsuario);
                    u = new PessoaJuridica(pj.getIdClinicaPetshop(), pj.getCnpj(), pj.getFuncionamento(), pj.getDescricao(), pj.getSite(),
                                                rs.getInt("idUsuario"), rs.getString("nomeUsuario"), rs.getString("email"), rs.getString("senha"),
                                                rs.getString("dataNascimento"), rs.getString("foto"), rs.getString("localizacao"), rs.getString("telefone"), rs.getInt("idPermissao"));
                }
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            pstmt.close();
            rs.close();
        } 
        return u;
    }
    
    public PessoaFisica buscarPF(int idUsuario) throws SQLException
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PessoaFisica u = null;
        
        try
        {
            String comandoSql= "SELECT * FROM PessoaFisica WHERE idHelper = ?";
            
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
            pstmt.close();
            rs.close();
        } 
        return u;
    }
    
    public PessoaJuridica buscarPJ(int idUsuario) throws SQLException
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PessoaJuridica u = null;
        
        try
        {
            String comandoSql= "SELECT * FROM PessoaJuridica WHERE idClinicaPetshop = ?";
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
            pstmt.close();
            rs.close();
        } 
        return u;
    }
    
    public Usuario logar(String email, String senha) throws SQLException
    {
        System.out.println("DAO.UsuarioDAO.logar()");
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Usuario u = new Usuario();
        
        
        try
        {
            String comandoSql= "SELECT * FROM Usuario WHERE Usuario.email = ? AND Usuario.senha = ?";
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
                            rs.getString("email"), rs.getString("senha"), rs.getString("dataNascimento"), 
                            rs.getString("foto"), rs.getString("localizacao"), rs.getString("telefone"), rs.getInt("idPermissao"));
                }
                else
                {
                    PessoaJuridica pj = buscarPJ(rs.getInt("idUsuario"));
                    u = new PessoaJuridica(pj.getIdClinicaPetshop(), pj.getCnpj(), pj.getFuncionamento(), pj.getDescricao(), pj.getSite(),
                            rs.getInt("idUsuario"), rs.getString("nomeUsuario"), rs.getString("email"), rs.getString("senha"),
                            rs.getString("dataNascimento"), rs.getString("foto"), rs.getString("localizacao"), rs.getString("telefone"), rs.getInt("idPermissao"));
                }
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            pstmt.close();
            rs.close();
        } 
        return u;
    }
    
    public List<PessoaJuridica> listarOng() throws SQLException
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<PessoaJuridica> lista = new ArrayList();
        
        try
        {
            String comandoSql= "SELECT * FROM PessoaJuridica pj inner join usuario on usuario.idUsuario = pj.idClinicaPetshop where Usuario.idPermissao = 2 ";
            pstmt = conn.prepareStatement(comandoSql);
            
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                PessoaJuridica pj = new PessoaJuridica(rs.getInt("idClinicaPetshop"), rs.getString("cnpj"), rs.getString("funcionamento"), 
                                        rs.getString("descricao"), rs.getString("site"), rs.getInt("idUsuario"), rs.getString("nomeUsuario"), 
                                        rs.getString("email"), rs.getString("senha"), rs.getString("dataNascimento"), rs.getString("foto"), 
                                        rs.getString("localizacao"), rs.getString("telefone"), rs.getInt("idPermissao"));    
                lista.add(pj);
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            pstmt.close();
            rs.close();
        } 
        return lista;
    }
    
    public List<PessoaJuridica> listarClinicaPetshops() throws SQLException
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<PessoaJuridica> lista = new ArrayList();
        
        try
        {
            String comandoSql= "SELECT * FROM PessoaJuridica pj inner join usuario on usuario.idUsuario = pj.idClinicaPetshop "
                    + "where Usuario.idPermissao = 3";
            pstmt = conn.prepareStatement(comandoSql);
            
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                PessoaJuridica pj = new PessoaJuridica(rs.getInt("idClinicaPetshop"), rs.getString("cnpj"), rs.getString("funcionamento"), 
                                        rs.getString("descricao"), rs.getString("site"), rs.getInt("idUsuario"), rs.getString("nomeUsuario"), 
                                        rs.getString("email"), rs.getString("senha"), rs.getString("dataNascimento"), rs.getString("foto"), 
                                        rs.getString("localizacao"), rs.getString("telefone"), rs.getInt("idPermissao"));    
                lista.add(pj);
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            pstmt.close();
            rs.close();
        } 
        return lista;
    }
}
