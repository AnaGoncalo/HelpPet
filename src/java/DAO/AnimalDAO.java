/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Animal;
import Modelo.Usuario;
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

    private Connection conn;

    public AnimalDAO() throws SQLException {
        super();
        conn = FabricaConexao.getInstancia().getConexao();
    }

    public String CadastrarAnimal(Animal animal) throws SQLException {
        System.out.println("animal dao");
        PreparedStatement pstmt = null;
        
        try {
            String comandoSql = "INSERT INTO Animal(especie, nomeAnimal, sexo, idade, raca, descricaoAnimal, fotoAnimal, tipoAnimal, "
                + "statusAnimal, dataCadastro, idUsuario, localizacao) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            pstmt.setInt(11, animal.getResponsavel().getIdUsuario());
            pstmt.setString(12, animal.getLocalizacao());

            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pstmt.close();
        }
        return "OK!";
    }

    public void EditarAnimal(Animal animal) throws SQLException {
        PreparedStatement pstmt = null;
        
        try {
            String comandoSql = "UPDATE Animal SET especie = ?, nomeAnimal = ?, sexo = ?, idade = ?, raca = ?, "
                + "descricaoAnimal = ?, fotoAnimal = ?, statusAnimal = ?, localizacao = ? WHERE idAnimal = ?";
            
            pstmt = conn.prepareStatement(comandoSql);
            pstmt.setString(1, animal.getEspecie());
            pstmt.setString(2, animal.getNomeAnimal());
            pstmt.setString(3, animal.getSexo());
            pstmt.setString(4, animal.getIdade());
            pstmt.setString(5, animal.getRaca());
            pstmt.setString(6, animal.getDescricaoAnimal());
            pstmt.setString(7, animal.getFotoAnimal());
            pstmt.setBoolean(8, animal.isStatusAnimal());
            pstmt.setString(9, animal.getLocalizacao());
            pstmt.setInt(10, animal.getIdAnimal());

            pstmt.executeUpdate();
            System.out.println("Resultado editar DAO");
        } catch (SQLException ex) {
            Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pstmt.close();
        }
    }

    public void ExcluirAnimal(int idAnimal) throws SQLException {
        PreparedStatement pstmt = null;
        
        try {
            String comandoSql = "DELETE FROM Animal WHERE idAnimal = ?";
            
            pstmt = conn.prepareStatement(comandoSql);
            pstmt.setInt(1, idAnimal);

            pstmt.executeUpdate();
            
            System.out.println("Resultado excluir DAO");// + pstmt.executeUpdate());
        } catch (SQLException ex) {
            Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pstmt.close();
        }
    }

    public List<Animal> ListarAnimaisNaoAdotados() throws SQLException {
        ResultSet rs = null;
        Statement stmt = null;
        List<Animal> lista = new ArrayList();
        try {
            String sql = "SELECT * FROM Animal INNER JOIN Usuario ON Usuario.idUsuario = Animal.idUsuario "
                    + "WHERE statusAnimal = 0";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Usuario resp = new Usuario(rs.getInt("idUsuario"), rs.getString("nomeUsuario"), rs.getString("email"), null, rs.getString("dataNascimento"),
                        rs.getString("foto"), rs.getString("localizacao"), rs.getString("telefone"), rs.getInt("idPermissao"));

                Animal a = new Animal(rs.getInt("idAnimal"), rs.getString("nomeAnimal"), rs.getString("especie"),
                        rs.getString("raca"), rs.getString("idade"), rs.getString("sexo"), rs.getString("descricaoAnimal"),
                        rs.getString("tipoAnimal"), rs.getString("fotoAnimal"), rs.getDate("dataCadastro"),
                        rs.getBoolean("statusAnimal"), resp, rs.getString("localizacao"));

                lista.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            rs.close();
        }
        System.out.println("Animal dao: " + lista.size());
        return lista;
    }

    public List<Animal> ListarPorUsuario(int idUsuario) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Animal> lista = new ArrayList();
        
        try {
            String sql = "SELECT * FROM Animal INNER JOIN Usuario ON Usuario.idUsuario = Animal.idUsuario WHERE Animal.idUsuario = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idUsuario);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                Usuario resp = new Usuario(rs.getInt("idUsuario"), rs.getString("nomeUsuario"), rs.getString("email"), null, rs.getString("dataNascimento"),
                        rs.getString("foto"), rs.getString("localizacao"), rs.getString("telefone"), rs.getInt("idPermissao"));

                Animal a = new Animal(rs.getInt("idAnimal"), rs.getString("nomeAnimal"), rs.getString("especie"),
                        rs.getString("raca"), rs.getString("idade"), rs.getString("sexo"), rs.getString("descricaoAnimal"),
                        rs.getString("tipoAnimal"), rs.getString("fotoAnimal"), rs.getDate("dataCadastro"),
                        rs.getBoolean("statusAnimal"), resp, rs.getString("localizacao"));

                lista.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            rs.close();
        }
        System.out.println("Animal DAO : qtd " + lista.size());
        return lista;
    }
}
