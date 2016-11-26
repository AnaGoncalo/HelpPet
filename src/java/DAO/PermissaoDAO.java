/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Permissao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ana Gonçalo
 */
public class PermissaoDAO {

    public Permissao buscarById(int idPermissao) throws SQLException
    {
        Connection conn = Banco.getConexao();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Permissao p = null;
        String comandoSql= "SELECT * FROM Permissao WHERE idPermissao = ?";
        try
        {
            pstmt = conn.prepareStatement(comandoSql);
            pstmt.setInt(1, idPermissao);
            
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                p = new Permissao(rs.getInt("idPermissao"), rs.getString("nomePermissao"), rs.getString("descricao"));
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(PermissaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            Banco.closeConexao(conn, rs, pstmt, null);
        } 
        return p;
    }
}
