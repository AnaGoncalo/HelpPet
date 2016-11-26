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
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Ana Gonçalo
 */
@ManagedBean
@SessionScoped
public class LoginBean {
    String email;
    String senha;
    Usuario usuarioLogado = null;
    private UsuarioDAO usuarioDao;
    
    public LoginBean() {
    }
    
    public String Logar() throws SQLException{
        usuarioDao = new UsuarioDAO();
        usuarioLogado = usuarioDao.logar(email, senha);
        //System.out.println(usuarioLogado.getNomeUsuario());
        return "index.xhtml";
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
}
