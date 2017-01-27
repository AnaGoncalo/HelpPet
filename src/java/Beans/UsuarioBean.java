/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Modelo.Animal;
import Modelo.PessoaFisica;
import Modelo.PessoaJuridica;
import Modelo.Usuario;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
@RequestScoped
public class UsuarioBean {
    private Usuario usuario;
    private Usuario user = (Usuario) getSession().getAttribute("usuarioLogado");
    private PessoaFisica pf = null;
    private PessoaJuridica pj = null;
    private List<Animal> meusAnimais = new ArrayList();
    private List<Animal> meusAnimaisTop = new ArrayList();
    
    public UsuarioBean() {
        this.usuario = new Usuario();
        
    }
    
    public String Salvar(){
        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target("http://127.0.0.1:8080/TesteWS/rest/usuario");
        Gson gson = new Gson();
        String json = gson.toJson(usuario);
        caminho.request().post(Entity.json(json));
        
        return "index.jsf";
    }
    
    public String VerPerfil(int idUsuario){
        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target("http://localhost:8080/TesteWS/rest/usuario/" + idUsuario);
        String json = caminho.request().get(String.class);
        
        Gson gson = new Gson();
        usuario = gson.fromJson(json, Usuario.class);
        System.out.println("Ver Usuario: Que usuario é esse? " + usuario.getNomeUsuario() + usuario.getIdUsuario());
        if(usuario.getIdPermissao() == 1){
            pf = gson.fromJson(json, PessoaFisica.class);
            MeusAnimais(usuario.getIdUsuario());
            return "verHelper.jsf";
        }
        else{
           pj = gson.fromJson(json, PessoaJuridica.class);
           return "verOng.jsf";
        }
    }
    
    public void MeusAnimais(int id){
        System.out.println("id Usuario para Meus Aniamis: " + id);
        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target("http://localhost:8080/TesteWS/rest/animal/" + id);
        String json = caminho.request().get(String.class);
        Gson gson = new Gson();
        Animal[] vetor = gson.fromJson(json, Animal[].class);
        meusAnimais = Arrays.asList(vetor);
    }
    
    public FacesContext getFacesContext(){
        return FacesContext.getCurrentInstance();
    }
  
    public HttpSession getSession(){
        return (HttpSession) getFacesContext().getExternalContext().getSession(false);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public PessoaFisica getPf() {
        return pf;
    }

    public void setPf(PessoaFisica pf) {
        this.pf = pf;
    }

    public PessoaJuridica getPj() {
        return pj;
    }

    public void setPj(PessoaJuridica pj) {
        this.pj = pj;
    }

    public List<Animal> getMeusAnimais() {
        return meusAnimais;
    }

    public void setMeusAnimais(List<Animal> meusAnimais) {
        this.meusAnimais = meusAnimais;
    }

    public List<Animal> getMeusAnimaisTop() {
        System.out.println("qtd" + meusAnimais.size());
        //meusAnimaisTop = meusAnimais.subList(1, 5);
        return meusAnimaisTop;
    }

    public void setMeusAnimaisTop(List<Animal> meusAnimaisTop) {
        this.meusAnimaisTop = meusAnimaisTop;
    }    
}
