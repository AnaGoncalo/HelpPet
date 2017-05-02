/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.PessoaJuridica;
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
public class PessoaJuridicaDAO {
    private Connection conn;
    
    public PessoaJuridicaDAO(){
        super();
        conn = FabricaConexao.getInstancia().getConexao();
    }
    
    public List<PessoaJuridica> listarOngs() throws SQLException
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<PessoaJuridica> lista = new ArrayList();
        
        try
        {
            String comandoSql= "SELECT * FROM PessoaJuridica pj inner join usuario on usuario.idUsuario = pj.idClinicaPetshop "
                    + "where Usuario.idPermissao = 2";
            
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
            Logger.getLogger(PessoaJuridicaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            FabricaConexao.closeConexao(conn, rs, pstmt, null);
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
            Logger.getLogger(PessoaJuridicaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            FabricaConexao.closeConexao(conn, rs, pstmt, null);
        } 
        return lista;
    }
    
    public void editarPJ(PessoaJuridica pj) throws SQLException
    {
        System.out.println("Testando editar DAO PJ");
        PreparedStatement pstmt = null;
        
        try
        {
            String comandoSql = "UPDATE PessoaJuridica SET cnpj = ?, funcionamento = ?, descricao = ?, site = ? "
                + "WHERE idClinicaPetshop = ?";
            
            pstmt = conn.prepareStatement(comandoSql);
            pstmt.setString(1, pj.getCnpj());
            pstmt.setString(2, pj.getFuncionamento());
            pstmt.setString(3, pj.getDescricao());
            pstmt.setString(4, pj.getSite());
            pstmt.setInt(5, pj.getIdClinicaPetshop());
           
            pstmt.executeUpdate();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(PessoaJuridicaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally
        {
            FabricaConexao.closeConexao(conn, null, pstmt, null);
        } 
    }
}
