/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Modelo.PessoaJuridica;
import DAO.PessoaJuridicaDAO;
import Modelo.Animal;
import Modelo.Anuncio;
import Modelo.Estoque;
import Modelo.Evento;
import Modelo.Experiencia;
import Modelo.Usuario;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 *
 * @author Ana Gonçalo
 */
@ManagedBean
@RequestScoped
public class PjBean {
    private List<PessoaJuridica> ongs = new ArrayList();
    private PessoaJuridica ong;
    
    private List<PessoaJuridica> clinicas = new ArrayList();
    private PessoaJuridica clinica;
    
    private List<Estoque> estoques = new ArrayList();
    private Estoque estoque;
    
    private List<Animal> animais = new ArrayList();
    private List<Evento> eventos = new ArrayList();
    private List<Experiencia> experiencias = new ArrayList();
    private List<Anuncio> anuncios = new ArrayList();
    
    private Usuario user = (Usuario) getSession().getAttribute("usuarioLogado");
    
    public PjBean() {
        
        ListarOngs();
        ListarClinicas();
        
        if(ong != null)
        {
            ListarEstoques();
            ListarAnimais();
            ListarEventos();
        }
        if(clinica != null){
            ListarAnuncios();
        }
        
    }
    public void ListarOngs(){
        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target("http://localhost:8080/TesteWS/rest/ongs");
        String json = caminho.request().get(String.class);
        
        Gson gson = new Gson();
        PessoaJuridica[] vetor = gson.fromJson(json, PessoaJuridica[].class);
        ongs = Arrays.asList(vetor);
        //return "ongs.jsf";
    }
    public void ListarClinicas(){
        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target("http://localhost:8080/TesteWS/rest/clinicas");
        String json = caminho.request().get(String.class);
        
        Gson gson = new Gson();
        PessoaJuridica[] vetor = gson.fromJson(json, PessoaJuridica[].class);
        clinicas = Arrays.asList(vetor);
        //return "ongs.jsf";
    }
    public void ListarEstoques(){
        System.out.println("id da ong " + ong.getIdUsuario());
        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target("http://localhost:8080/TesteWS/rest/estoque/" + ong.getIdUsuario());
        String json = caminho.request().get(String.class);
        
        Gson gson = new Gson();
        Estoque[] vetor = gson.fromJson(json, Estoque[].class);
        estoques = Arrays.asList(vetor);
    }
    public void ListarAnimais() {
        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target("http://localhost:8080/TesteWS/rest/animal/" + ong.getIdUsuario());
        String json = caminho.request().get(String.class);
        Gson gson = new Gson();
        Animal[] vetor = gson.fromJson(json, Animal[].class);
        animais = Arrays.asList(vetor);
    }
    public void ListarEventos(){
        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target("http://localhost:8080/TesteWS/rest/evento/" + ong.getIdUsuario());
        String json = caminho.request().get(String.class);
        
        Gson gson = new Gson();
        Evento[] vetor = gson.fromJson(json, Evento[].class);
        eventos = Arrays.asList(vetor);
    }
    public void ListarAnuncios(){
        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target("http://localhost:8080/TesteWS/rest/anuncio/" + clinica.getIdUsuario());
        String json = caminho.request().get(String.class);
        
        Gson gson = new Gson();
        Anuncio[] vetor = gson.fromJson(json, Anuncio[].class);
        anuncios = Arrays.asList(vetor);
    }
    public void ListarExperiencias(){
        Client cliente = ClientBuilder.newClient();
        WebTarget caminho;
        if(ong != null)
            caminho = cliente.target("http://localhost:8080/TesteWS/rest/experiencia/" + ong.getIdUsuario());
        else
            caminho = cliente.target("http://localhost:8080/TesteWS/rest/experiencia/" + clinica.getIdUsuario());
        String json = caminho.request().get(String.class);
        
        Gson gson = new Gson();
        Experiencia[] vetor = gson.fromJson(json, Experiencia[].class);
        experiencias = Arrays.asList(vetor);
    }
    
    public String VerOng(PessoaJuridica pj){
        ong = pj;
        ListarEstoques();
        ListarAnimais();
        ListarEventos();
        ListarExperiencias();
       
        return "ong.jsf";
    }
    
    public String VerClinica(PessoaJuridica pj){
        clinica = pj;
        ListarAnuncios();
        //ListarExperiencias();
        return "clinicaPetshop.jsf";
    }
    
    public FacesContext getFacesContext(){
        return FacesContext.getCurrentInstance();
    }
  
    public HttpSession getSession(){
        return (HttpSession) getFacesContext().getExternalContext().getSession(false);
    }

    public List<PessoaJuridica> getOngs() {
        return ongs;
    }

    public void setOngs(List<PessoaJuridica> ongs) {
        this.ongs = ongs;
    }

    public PessoaJuridica getOng() {
        return ong;
    }

    public void setOng(PessoaJuridica ong) {
        this.ong = ong;
    }

    public List<PessoaJuridica> getClinicas() {
        return clinicas;
    }

    public void setClinicas(List<PessoaJuridica> clinicas) {
        this.clinicas = clinicas;
    }

    public PessoaJuridica getClinica() {
        return clinica;
    }

    public void setClinica(PessoaJuridica clinica) {
        this.clinica = clinica;
    }

    public List<Estoque> getEstoques() {
        return estoques;
    }

    public void setEstoques(List<Estoque> estoques) {
        this.estoques = estoques;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public List<Animal> getAnimais() {
        return animais;
    }

    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public List<Anuncio> getAnuncios() {
        return anuncios;
    }

    public void setAnuncios(List<Anuncio> anuncios) {
        this.anuncios = anuncios;
    }

    public List<Experiencia> getExperiencias() {
        return experiencias;
    }

    public void setExperiencias(List<Experiencia> experiencias) {
        this.experiencias = experiencias;
    }
    
}
