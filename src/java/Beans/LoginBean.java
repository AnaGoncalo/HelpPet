/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import DAO.UsuarioDAO;
import Modelo.PessoaFisica;
import Modelo.Usuario;
import com.google.gson.Gson;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

/**
 *
 * @author Ana Gonçalo
 */
@ManagedBean
@SessionScoped
public class LoginBean {
    private Usuario user = new Usuario();
    String email;
    String senha;
    private Usuario usuarioLogado = null;
    private UsuarioDAO usuarioDao;
    
    public LoginBean() {
    }
    
    public String Logar() throws SQLException{
        
        //usuarioDao = new UsuarioDAO();
        //usuarioLogado = usuarioDao.logar(email, senha);
        
        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target("http://127.0.0.1:8080/TesteWS/rest/logar");
        Gson gson = new Gson();
        String json = gson.toJson(user);
        json = caminho.request().post(Entity.json(json), String.class);
        
        usuarioLogado = gson.fromJson(json, Usuario.class);
        
        System.out.println("Logou? " + usuarioLogado.getNomeUsuario());
        
        getSession().setAttribute("usuarioLogado", usuarioLogado);
        return "index.xhtml";
    }
    
    public FacesContext getFacesContext(){
        return FacesContext.getCurrentInstance();
    }
  
    public HttpSession getSession(){
        return (HttpSession) getFacesContext().getExternalContext().getSession(false);
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
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
