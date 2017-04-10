/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Animal;
import Modelo.Encontro;
import Modelo.Usuario;
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
public class EncontroDAO {
    
    public static String CadastrarEncontro(Encontro encontro) throws SQLException
    {
        System.out.println("encontro dao");
        Connection conn = Banco.getConexao();
        PreparedStatement pstmt = null;
        String comandoSql = "INSERT INTO Encontro(dataEncontro, horarioEncontro, statusEncontro, editado, idAnimal, idUsuario, "
                + "localizacao) values(?, ?, ?, ?, ?, ?, ?)";
        try
        {
            pstmt = conn.prepareStatement(comandoSql);
            pstmt.setString(1, encontro.getDataEncontro());
            pstmt.setString(2, encontro.getHorarioEncontro());
            pstmt.setBoolean(3, encontro.getStatusEncontro());
            pstmt.setBoolean(4, encontro.isEditado());
            pstmt.setInt(5, encontro.getAnimal().getIdAnimal());
            pstmt.setInt(6, encontro.getAdotante().getIdUsuario());
            pstmt.setString(7, encontro.getLocalizacao());
           
            pstmt.executeUpdate();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(EncontroDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally
        {
            Banco.closeConexao(conn, null, pstmt, null);
        }
        return "OK!";
    }
    
    public static String EditarEncontro(Encontro encontro) throws SQLException
    {
        System.out.println("encontro dao editar");
        Connection conn = Banco.getConexao();
        PreparedStatement pstmt = null;
        String comandoSql = "UPDATE Encontro SET dataEncontro = ?, horarioEncontro = ?, statusEncontro = ?, editado = ?, "
                + "idAnimal = ?, idUsuario = ?, localizacao = ? WHERE idEncontro = ?";
        try
        {
            pstmt = conn.prepareStatement(comandoSql);
            pstmt.setString(1, encontro.getDataEncontro());
            pstmt.setString(2, encontro.getHorarioEncontro());
            pstmt.setBoolean(3, encontro.getStatusEncontro());
            pstmt.setBoolean(4, encontro.isEditado());
            pstmt.setInt(5, encontro.getAnimal().getIdAnimal());
            pstmt.setInt(6, encontro.getAdotante().getIdUsuario());
            pstmt.setString(7, encontro.getLocalizacao());
            pstmt.setInt(8, encontro.getIdEncontro());
           
            if(encontro.getStatusEncontro())
            {
                Animal a = encontro.getAnimal();
                a.setStatusAnimal(true);
                AnimalDAO.EditarAnimal(a);
            }
            
            pstmt.executeUpdate();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(EncontroDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally
        {
            Banco.closeConexao(conn, null, pstmt, null);
        }
        return "OK!";
    }
    
    public static String ExcluirEncontro(int idEncontro) throws SQLException
    {
        System.out.println("encontro dao excluir " + idEncontro);
        Connection conn = Banco.getConexao();
        PreparedStatement pstmt = null;
        String comandoSql = "DELETE FROM Encontro WHERE idEncontro = ?";
        try
        {
            pstmt = conn.prepareStatement(comandoSql);
            pstmt.setInt(1, idEncontro);
           
            pstmt.executeUpdate();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(EncontroDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally
        {
            Banco.closeConexao(conn, null, pstmt, null);
        }
        return "OK!";
    }
    
    public static List<Encontro> ListarEncontros() throws SQLException
    {
        Connection conn = Banco.getConexao();
        ResultSet rs = null;
        Statement stmt= null;
        List<Encontro> lista = new ArrayList();
        String sql= "select * from Encontro " +
                "inner join Usuario as Adotante on Adotante.idUsuario = Encontro.idUsuario " +
                "inner join Animal on Animal.idAnimal = encontro.idAnimal " +
                "inner join Usuario as Responsavel on Responsavel.idUsuario = Animal.idUsuario";
        try
        {
            stmt = conn.createStatement();
            rs= stmt.executeQuery(sql);
            while(rs.next())
            { 
                Usuario resp = new Usuario();
                resp.setIdUsuario(rs.getInt("Responsavel.idUsuario"));
                resp.setNomeUsuario(rs.getString("Responsavel.nomeUsuario"));
                resp.setEmail(rs.getString("Responsavel.email"));
                resp.setDataNascimento(rs.getString("Responsavel.dataNascimento"));
                resp.setTelefone(rs.getString("Responsavel.telefone"));
                resp.setFoto(rs.getString("Responsavel.foto"));
                resp.setLocalizacao(rs.getString("Responsavel.localizacao"));
                resp.setIdPermissao(rs.getInt("Responsavel.idPermissao"));
                
                Animal animal = new Animal();
                animal.setIdAnimal(rs.getInt("Animal.idAnimal"));
                animal.setNomeAnimal(rs.getString("Animal.nomeAnimal"));
                animal.setTipoAnimal(rs.getString("Animal.tipoAnimal"));
                animal.setEspecie(rs.getString("Animal.especie"));
                animal.setRaca(rs.getString("Animal.raca"));
                animal.setIdade(rs.getString("Animal.idade"));
                animal.setSexo(rs.getString("Animal.sexo"));
                animal.setFotoAnimal(rs.getString("fotoAnimal"));
                animal.setDescricaoAnimal(rs.getString("Animal.descricaoAnimal"));
                animal.setStatusAnimal(rs.getBoolean("Animal.statusAnimal"));
                animal.setLocalizacao(rs.getString("Animal.localizacao"));
                animal.setResponsavel(resp);
                
                Usuario adot = new Usuario();
                adot.setIdUsuario(rs.getInt("Adotante.idUsuario"));
                adot.setNomeUsuario(rs.getString("Adotante.nomeUsuario"));
                adot.setEmail(rs.getString("Adotante.email"));
                adot.setDataNascimento(rs.getString("Adotante.dataNascimento"));
                adot.setTelefone(rs.getString("Adotante.telefone"));
                adot.setFoto(rs.getString("Adotante.foto"));
                adot.setLocalizacao(rs.getString("Adotante.localizacao"));
                adot.setIdPermissao(rs.getInt("Adotante.idPermissao"));
                
                Encontro e = new Encontro();
                e.setIdEncontro(rs.getInt("idEncontro"));
                e.setDataEncontro(rs.getString("dataEncontro"));
                e.setHorarioEncontro(rs.getString("horarioEncontro"));
                e.setLocalizacao(rs.getString("localizacao"));
                e.setStatusEncontro(rs.getBoolean("statusEncontro"));
                e.setEditado(rs.getBoolean("editado"));
                e.setAnimal(animal);
                e.setAdotante(adot);
                
                lista.add(e);
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(EncontroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            Banco.closeConexao(conn, rs, null, stmt);
        } 
        return lista;
    }
    
    public static List<Encontro> ListarPorUsuario(int idUsuario) throws SQLException
    {
        Connection conn = Banco.getConexao();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Encontro> lista = new ArrayList();
        String sql= "select * from Encontro " +
                "inner join Usuario as Adotante on Adotante.idUsuario = Encontro.idUsuario " +
                "inner join Animal on Animal.idAnimal = encontro.idAnimal " +
                "inner join Usuario as Responsavel on Responsavel.idUsuario = Animal.idUsuario " +
                "WHERE Encontro.idUsuario = ? OR Animal.idUsuario = ?";
        try
        {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idUsuario);
            pstmt.setInt(2, idUsuario);
            
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                Usuario resp = new Usuario();
                resp.setIdUsuario(rs.getInt("Responsavel.idUsuario"));
                resp.setNomeUsuario(rs.getString("Responsavel.nomeUsuario"));
                resp.setEmail(rs.getString("Responsavel.email"));
                resp.setDataNascimento(rs.getString("Responsavel.dataNascimento"));
                resp.setTelefone(rs.getString("Responsavel.telefone"));
                resp.setFoto(rs.getString("Responsavel.foto"));
                resp.setLocalizacao(rs.getString("Responsavel.localizacao"));
                resp.setIdPermissao(rs.getInt("Responsavel.idPermissao"));
                
                Animal animal = new Animal();
                animal.setIdAnimal(rs.getInt("Animal.idAnimal"));
                animal.setNomeAnimal(rs.getString("Animal.nomeAnimal"));
                animal.setTipoAnimal(rs.getString("Animal.tipoAnimal"));
                animal.setEspecie(rs.getString("Animal.especie"));
                animal.setRaca(rs.getString("Animal.raca"));
                animal.setIdade(rs.getString("Animal.idade"));
                animal.setSexo(rs.getString("Animal.sexo"));
                animal.setFotoAnimal(rs.getString("fotoAnimal"));
                animal.setDescricaoAnimal(rs.getString("Animal.descricaoAnimal"));
                animal.setStatusAnimal(rs.getBoolean("Animal.statusAnimal"));
                animal.setLocalizacao(rs.getString("Animal.localizacao"));
                animal.setResponsavel(resp); // pronto, já mudou
                
                Usuario adot = new Usuario();
                adot.setIdUsuario(rs.getInt("Adotante.idUsuario"));
                adot.setNomeUsuario(rs.getString("Adotante.nomeUsuario"));
                adot.setEmail(rs.getString("Adotante.email"));
                adot.setDataNascimento(rs.getString("Adotante.dataNascimento"));
                adot.setTelefone(rs.getString("Adotante.telefone"));
                adot.setFoto(rs.getString("Adotante.foto"));
                adot.setLocalizacao(rs.getString("Adotante.localizacao"));
                adot.setIdPermissao(rs.getInt("Adotante.idPermissao"));
                
                Encontro e = new Encontro();
                e.setIdEncontro(rs.getInt("idEncontro"));
                e.setDataEncontro(rs.getString("dataEncontro"));
                e.setHorarioEncontro(rs.getString("horarioEncontro"));
                e.setLocalizacao(rs.getString("localizacao"));
                e.setStatusEncontro(rs.getBoolean("statusEncontro"));
                e.setEditado(rs.getBoolean("editado"));
                e.setAnimal(animal);
                e.setAdotante(adot);
                
                lista.add(e);
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(EncontroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            Banco.closeConexao(conn, rs, pstmt, null);
        } 
        return lista;
    }
    
}
