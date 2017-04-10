/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Modelo.Evento;
import Modelo.Usuario;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Arrays;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Ana Gonçalo
 */
@ManagedBean
@RequestScoped
public class EventoBean {

    private Evento evento = new Evento();
    private Usuario responsavel = null;

    private Usuario user = (Usuario) getSession().getAttribute("usuarioLogado");

    private List<Evento> eventos = new ArrayList();
    private List<Evento> eventosTop = new ArrayList();
    private List<Evento> meusEventos = new ArrayList();

    private Part imagem;

    public EventoBean() throws SQLException {
        Listar();

        if (eventos.size() > 4) {
            eventosTop = eventos.subList(1, 5);
        } else {
            eventosTop = eventos;
        }

        if (user != null) {
            ListarMeusEventos();
        }
    }

    public void Listar() {
        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target("http://localhost:8080/HelpPet/rest/evento");
        String json = caminho.request().get(String.class);

        Gson gson = new Gson();
        Evento[] vetor = gson.fromJson(json, Evento[].class);
        eventos = Arrays.asList(vetor);
    }

    public void ListarMeusEventos() {
        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target("http://localhost:8080/HelpPet/rest/evento/" + user.getIdUsuario());
        String json = caminho.request().get(String.class);

        Gson gson = new Gson();
        Evento[] vetor = gson.fromJson(json, Evento[].class);
        meusEventos = Arrays.asList(vetor);
    }

    public String Salvar() {
        if (user == null) {
            return "loginSignin.jsf";
        }

        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target("http://127.0.0.1:8080/HelpPet/rest/evento");
        Gson gson = new Gson();
        
        if(imagem != null){
            evento.setFotoEvento(upload());
        }
        
        if (evento.getIdEvento() == 0) {
            //cadastrar
            evento.setIdUsuario(user.getIdUsuario());
            if(imagem == null){
                evento.setFotoEvento("imagens\\evento.jpg");
            }
            String json = gson.toJson(evento);
            caminho.request().post(Entity.json(json));
        } else {
            //editar
            evento.setIdUsuario(user.getIdUsuario());
            String json = gson.toJson(evento);
            caminho.request().put(Entity.json(json));
        }

        Listar();
        ListarMeusEventos();
        return "meusEventos.jsf";
    }

    public String VerEvento(Evento e) {
        evento = e;
        return "evento.jsf";
    }

    public String EditarEvento(Evento e) {
        evento = e;
        return "editarEvento.jsf";
    }
    
    public String ExcluirEvento(Evento e){
        System.out.println("Bean Evento Excluir " + e.getIdEvento());
        
        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target("http://127.0.0.1:8080/HelpPet/rest/evento/" + e.getIdEvento());
        caminho.request().delete();
        
        return "meusEventos.jsf";
    }

    public FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    public HttpSession getSession() {
        return (HttpSession) getFacesContext().getExternalContext().getSession(false);
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public Usuario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Usuario responsavel) {
        this.responsavel = responsavel;
    }

    public List<Evento> getEventosTop() {
        return eventosTop;
    }

    public void setEventosTop(List<Evento> eventosTop) {
        this.eventosTop = eventosTop;
    }

    public List<Evento> getMeusEventos() {
        return meusEventos;
    }

    public void setMeusEventos(List<Evento> meusEventos) {
        this.meusEventos = meusEventos;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Part getImagem() {
        return imagem;
    }

    public void setImagem(Part imagem) {
        this.imagem = imagem;
    }
    
    public String upload() {

        String nomeArquivoSaida = "D:\\Netbeans\\HelpPet\\web\\imagens\\" + evento.getNomeEvento() + ".jpg";// + imagem.getSubmittedFileName();
        //produto.setDescricao(imagem.getSubmittedFileName());
        try (InputStream is = imagem.getInputStream();
                OutputStream out = new FileOutputStream(nomeArquivoSaida)) {

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = is.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

        } catch (IOException e) {
            //FacesUtil.addErrorMessage("Erro ao enviar arquivo.");
        }
        return "imagens\\" + evento.getNomeEvento() + ".jpg";
    }

}
