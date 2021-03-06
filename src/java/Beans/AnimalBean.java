/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Modelo.Animal;
import Modelo.Encontro;
import Modelo.Usuario;
import com.google.gson.Gson;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Ana Gonçalo
 */
@ManagedBean
@RequestScoped
public class AnimalBean {
    
    private final String servico = "http://127.0.0.1:8080/HelpPet/rest/animal";
    private final String servicoEncontro = "http://127.0.0.1:8080/HelpPet/rest/encontro";
    private Animal animal = new Animal();

    //Em caso de marcar encontro, esse user será o adotante
    //Em caso de cadastro de animal, esse user será o responsavel
    private Usuario user = (Usuario) getSession().getAttribute("usuarioLogado");

    private List<Animal> animais = new ArrayList();
    private List<Animal> animaisTop = new ArrayList();
    private List<Animal> meusAnimais = new ArrayList();
    private Encontro encontro = new Encontro();
    private String especie = "Todas";
    private String tipo = "Todos";

    private Part imagem;

    public AnimalBean() throws SQLException {
        Listar();
        if (animais.size() >= 4) {
            animaisTop = animais.subList(0, 4);
        }

        if (user != null) {
            ListarMeusAnimais();
        }
    }

    public String Salvar() {
        if (user == null) {
            return "loginSignin.jsf";
        }
        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target(servico);
        Gson gson = new Gson();

        if (imagem != null) {
            animal.setFotoAnimal(upload());
        }
        if (animal.getIdAnimal() == 0) {
            System.out.println("Bean Salvar: " + animal.getNomeAnimal());
            animal.setResponsavel(user);
            if (imagem == null) {
                animal.setFotoAnimal("imagens\\animal.jpg");
            }
            String json = gson.toJson(animal);
            caminho.request().post(Entity.json(json));
        } else {
            animal.setResponsavel(user);
            String json = gson.toJson(animal);
            caminho.request().put(Entity.json(json));
        }

        ListarMeusAnimais();
        return "meusAnimais.jsf";
    }

    public void Listar() {
        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target(servico);
        String json = caminho.request().get(String.class);

        Gson gson = new Gson();
        Animal[] vetor = gson.fromJson(json, Animal[].class);
        animais = Arrays.asList(vetor);

    }

    public void filtrarEspecie(ValueChangeEvent e) {
        especie = e.getNewValue().toString();
        Filtrar();
    }
    
    public void filtrarTipo(ValueChangeEvent e) {
        tipo = e.getNewValue().toString();
        Filtrar();
    }

    public void Filtrar() {
        Listar();

        System.out.println("Especie: " + especie + " Tipo: " + tipo);
        List<Animal> filtro = new ArrayList();
        for (Animal a : animais) {
            if(a.getEspecie().equals(especie) && a.getTipoAnimal().equals(tipo)){
                filtro.add(a);
            }
            else if (especie.equals("Todas") && a.getTipoAnimal().equals(tipo)) {
                filtro.add(a);
            }
            else if(tipo.equals("Todos") && a.getEspecie().equals(especie)){
                filtro.add(a);
            }
            else if(tipo.equals("Todos") && especie.equals("Todas")){
                filtro.add(a);
            }
        }

        animais = filtro;
    }

    public void ListarMeusAnimais() {
        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target(servico + "/" + user.getIdUsuario());
        String json = caminho.request().get(String.class);
        Gson gson = new Gson();
        Animal[] vetor = gson.fromJson(json, Animal[].class);
        meusAnimais = Arrays.asList(vetor);
    }

    public String SalvarEncontro() {
        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target(servicoEncontro);
        Gson gson = new Gson();

        if (encontro.getIdEncontro() == 0) {
            encontro.setAdotante(user);
            encontro.setAnimal(animal);
            System.out.println("Testando Salvar Encontro: " + user.getIdUsuario());
            String json = gson.toJson(encontro);
            caminho.request().post(Entity.json(json));
        } else {
            String json = gson.toJson(encontro);
            caminho.request().put(Entity.json(json));
        }
        return "index.jsf";
    }

    public String VerAnimal(Animal a) {
        animal = a;

        return "animal.jsf";
    }

    public String EditarAnimal(Animal a) {
        animal = a;
        System.out.println("Edita ...");
        return "editarAnimal.jsf";
        //return "editarAnimal.jsf?faces-redirect=true";
    }

    public String Adotar(Animal a) {
        animal = a;
        //encontro = new Encontro();
        encontro.setAdotante(user); //pega o usuario da sessao
        encontro.setAnimal(animal);

        return "cadastrarEncontro.jsf";
    }

    public String VerResponsavel() {
        getRequest().setAttribute("usuario", animal.getResponsavel());
        return "VerHelper.jssetAttribute(f?faces-redirect=true";
    }

    public String ExcluirAnimal(Animal a) {
        System.out.println("Bean Animal Excluir " + a.getIdAnimal());

        Client cliente = ClientBuilder.newClient();
        WebTarget caminho = cliente.target(servico + "/" + a.getIdAnimal());
        caminho.request().delete();

        return "meusAnimais.jsf";
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

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public List<Animal> getAnimais() {
        return animais;
    }

    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }

    public Encontro getEncontro() {
        return encontro;
    }

    public void setEncontro(Encontro encontro) {
        this.encontro = encontro;
    }

    public List<Animal> getAnimaisTop() {
        return animaisTop;
    }

    public void setAnimaisTop(List<Animal> animaisTop) {
        this.animaisTop = animaisTop;
    }

    public List<Animal> getMeusAnimais() {
        ListarMeusAnimais();
        return meusAnimais;
    }

    public void setMeusAnimais(List<Animal> meusAnimais) {
        this.meusAnimais = meusAnimais;
    }

    public Part getImagem() {
        return imagem;
    }

    public void setImagem(Part imagem) {
        this.imagem = imagem;
    }

    public String upload() {

        String nomeArquivoSaida = "D:\\Netbeans\\HelpPet\\web\\imagens\\" + animal.getNomeAnimal() + ".jpg";// + imagem.getSubmittedFileName();
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
        return "imagens\\" + animal.getNomeAnimal() + ".jpg";
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


}
