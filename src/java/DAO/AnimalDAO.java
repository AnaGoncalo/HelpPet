/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Animal;
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
public class AnimalDAO {
    
    public String listar()
    {
        return "Animal x";
    }
    
    public String listarByCod(String a){
        return "Animal" + a;
    }
    
    public static String CadastrarAnimal(Animal animal) throws SQLException{
        System.out.println("animal dao");
        Connection conn = Banco.getConexao();
        PreparedStatement pstmt = null;
        String comandoSql = "INSERT INTO Animal(especie, nomeAnimal, sexo, idade, raca, descricaoAnimal, fotoAnimal, tipoAnimal, statusAnimal, dataCadastro, idUsuario, idLocalizacao) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try
        {
            pstmt = conn.prepareStatement(comandoSql);
            pstmt.setString(1, animal.getEspecie());
            pstmt.setString(2, animal.getNomeAnimal());
            pstmt.setString(3, animal.getSexo());
            pstmt.setString(4, animal.getIdade());
            pstmt.setString(5, animal.getRaca());
            pstmt.setString(6, animal.getDescricaoAnimal());
            pstmt.setString(7, animal.getFotoAnimal());
            pstmt.setString(8, animal.getTipoAnimal());
            pstmt.setBoolean(9, false);
            pstmt.setDate(10, (Date) animal.getCadastro());
            pstmt.setInt(11, animal.getIdUsuario());
            pstmt.setInt(12, animal.getIdLocalizacao());
           
            pstmt.executeUpdate();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally
        {
            Banco.closeConexao(conn, null, pstmt, null);
        }
        return "OK!";
    }
    
    public void EditarAnimal(Animal animal) throws SQLException
    {
        Connection conn = Banco.getConexao();
        PreparedStatement pstmt = null;
        String comandoSql = "UPDATE Animal SET especie = ?, nomeAnimal = ?, sexo = ?, idade = ?, raca = ?, "
                            + "descricaoAnimal = ?, fotoAnimal = ?, statusAnimal = ? WHERE idAnimal = ?";
        try
        {
            pstmt = conn.prepareStatement(comandoSql);
            pstmt.setString(1, animal.getEspecie());
            pstmt.setString(2, animal.getNomeAnimal());
            pstmt.setString(3, animal.getSexo());
            pstmt.setString(4, animal.getIdade());
            pstmt.setString(5, animal.getRaca());
            pstmt.setString(6, animal.getDescricaoAnimal());
            pstmt.setString(7, animal.getFotoAnimal());
            pstmt.setBoolean(8, animal.isStatusAnimal());
            pstmt.setInt(9, animal.getIdAnimal());
            
            pstmt.executeUpdate();
            System.out.println("Resultado editar DAO");
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally
        {
            Banco.closeConexao(conn, null, pstmt, null);
        } 
    }
    
    public void ExcluirAnimal(Animal animal) throws SQLException
    {
        Connection conn = Banco.getConexao();
        PreparedStatement pstmt = null;
        String comandoSql = "DELETE FROM Animal WHERE idAnimal = ?";
        try
        {
            pstmt = conn.prepareStatement(comandoSql);
            pstmt.setInt(1, animal.getIdAnimal());
            
            pstmt.executeUpdate();
            System.out.println("Resultado excluir DAO");// + pstmt.executeUpdate());
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally
        {
            Banco.closeConexao(conn, null, pstmt, null);
        }
    }
    
    public static List<Animal> ListarAnimaisNaoAdotados() throws SQLException
    {
        Connection conn = Banco.getConexao();
        ResultSet rs = null;
        Statement stmt= null;
        List<Animal> lista = new ArrayList();
        String sql= "SELECT * FROM Animal WHERE statusAnimal = 0";
        try
        {
            stmt = conn.createStatement();
            rs= stmt.executeQuery(sql);
            while(rs.next())
            { 
                Animal a = new Animal(rs.getInt("idAnimal"), rs.getString("nomeAnimal"), rs.getString("especie"), 
                        rs.getString("raca"), rs.getString("idade"), rs.getString("sexo"), rs.getString("descricaoAnimal"), 
                        rs.getString("tipoAnimal"), rs.getString("fotoAnimal"), rs.getDate("dataCadastro"),
                         rs.getBoolean("statusAnimal"), rs.getInt("idUsuario"), rs.getInt("idLocalizacao"));
                lista.add(a);
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            Banco.closeConexao(conn, rs, null, stmt);
        } 
        return lista;
    }
    
    public List<Animal> ListarPorUsuario(int idUsuario) throws SQLException
    {
        Connection conn = Banco.getConexao();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Animal> lista = new ArrayList();
        String sql= "SELECT * FROM Animal WHERE idUsuario = ?";
        try
        {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idUsuario);
            
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                
                Animal a = new Animal(rs.getInt("idAnimal"), rs.getString("nomeAnimal"), rs.getString("especie"), 
                        rs.getString("raca"), rs.getString("idade"), rs.getString("sexo"), rs.getString("descricaoAnimal"), 
                        rs.getString("tipo"), rs.getString("fotoAnimal"), rs.getDate("dataCadastro"), rs.getBoolean("statusAnimal"),
                        rs.getInt("idUsuario"), rs.getInt("idLocalizacao"));
                
                lista.add(a);
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            Banco.closeConexao(conn, rs, pstmt, null);
        } 
        return lista;
    }
}