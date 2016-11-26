/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import DAO.UsuarioDAO;
import Modelo.Usuario;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Ana Gonçalo
 */
@ManagedBean
@RequestScoped
public class usuarioBean {
    Usuario usuario;
    UsuarioDAO usuarioDao = new UsuarioDAO();
    
    public usuarioBean() {
        usuario = new Usuario();
    }
    
    public void Salvar() throws SQLException{
        usuarioDao.inserir(usuario);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
