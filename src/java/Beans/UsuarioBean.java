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
import javax.servlet.http.HttpServletRequest;
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

    //private Usuario usuario = (Usuario) getRequest().getAttribute("usuario");       //Usuario da pagina que esta sendo vista
    private Usuario usuario = new Usuario();
    private Usuario user = (Usuario) getSession().getAttribute("usuarioLogado");        // Usuario que está logado na sessão
    private PessoaFisica pf = null;         //Complemento das informações do Usuario (se for Helper)
    private PessoaJuridica pj = null;       //Complemento das informações do Usuario (se for Ong ou Clinica/Petshop
    private List<Animal> meusAnimais = new ArrayList();         //Lista de animais do Usuario
    private List<Animal> meusAnimaisTop = new ArrayList();      //Top 4 da lista de animais
    private int idPermissao;

    public UsuarioBean() {
        //this.usuario = new Usuario();

        System.out.println("Iniciando o UsuarioBean");

//        if(usuario.getIdUsuario() != 0){
//            MeusAnimais(usuario.getIdUsuario());
//        }
    }

    public String Salvar() {
        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target("http://127.0.0.1:8080/HelpPet/rest/usuario");
        Gson gson = new Gson();
        usuario.setIdPermissao(idPermissao);
        usuario.setFoto("imagens\\users\\userFoto.jpg");
        String json = gson.toJson(usuario);
        caminho.request().post(Entity.json(json));

        return "index.jsf";
    }

    public String VerPerfil(int idUsuario) {
        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target("http://localhost:8080/HelpPet/rest/usuario/" + idUsuario);
        String json = caminho.request().get(String.class);

        Gson gson = new Gson();
        usuario = gson.fromJson(json, Usuario.class);
        System.out.println("Ver Usuario: Que usuario é esse? " + usuario.getNomeUsuario() + usuario.getIdUsuario());
        if (usuario.getIdPermissao() == 1) {
            pf = gson.fromJson(json, PessoaFisica.class);
            //MeusAnimais(usuario.getIdUsuario());
            return "verHelper.jsf";
        } else {
            pj = gson.fromJson(json, PessoaJuridica.class);
            return "verOng.jsf";
        }
    }

    public String VerMeusAnimais() {
        MeusAnimais(usuario.getIdUsuario());

        return "meusAnimais.jsf?faces-redirect=true";
    }

    public void MeusAnimais(int id) {
        System.out.println("id Usuario para Meus Aniamis: " + id);
        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target("http://localhost:8080/HelpPet/rest/animal/" + id);
        String json = caminho.request().get(String.class);
        Gson gson = new Gson();
        Animal[] vetor = gson.fromJson(json, Animal[].class);
        meusAnimais = Arrays.asList(vetor);
        System.out.println("size MeusAnimais: " + meusAnimais.size());
        if (meusAnimais.size() > 4) {
            meusAnimaisTop = meusAnimais.subList(1, 5);
        }
        meusAnimaisTop = meusAnimais;
        System.out.println("size MeusAnimaisTop: " + meusAnimaisTop.size());
        for (Animal a : meusAnimaisTop) {
            System.out.println("nome A: " + a.getNomeAnimal());
        }
    }

    public FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    public HttpSession getSession() {
        return (HttpSession) getFacesContext().getExternalContext().getSession(false);
    }

    public HttpServletRequest getRequest() {
        return (HttpServletRequest) getFacesContext().getExternalContext().getRequest();
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
        System.out.println("qtd" + meusAnimais.size() + "usuario " + usuario.getNomeUsuario());
        return meusAnimaisTop;
    }

    public void setMeusAnimaisTop(List<Animal> meusAnimaisTop) {
        this.meusAnimaisTop = meusAnimaisTop;
    }

    public int getIdPermissao() {
        return idPermissao;
    }

    public void setIdPermissao(int idPermissao) {
        this.idPermissao = idPermissao;
    }
    

}
