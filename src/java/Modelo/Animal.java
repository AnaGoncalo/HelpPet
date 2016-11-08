/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author Ana Gonçalo
 */
public class Animal {
    private int idAnimal;
    private String nomeAnimal;
    private String especie;
    private String raca;
    private String idade;
    private String sexo;
    private String descricaoAnimal;
    private String fotoAnimal;
    private String tipoAnimal;
    private Date cadastro;
    private boolean statusAnimal;
    private int idUsuario;
    private int idLocalizacao;

    public Animal() {
       
    }

    public Animal(int idAnimal, String nome, String especie, String raca, String idade, String sexo, String descricao, 
            String tipo, String foto, Date cadastro, boolean status, int idUsuario, int idLocalizacao) {
        this.idAnimal = idAnimal;
        this.nomeAnimal = nome;
        this.especie = especie;
        this.raca = raca;
        this.idade = idade;
        this.sexo = sexo;
        this.descricaoAnimal = descricao;
        this.tipoAnimal = tipo;
        this.fotoAnimal = foto;
        this.cadastro = cadastro;
        this.statusAnimal = status;
        this.idUsuario = idUsuario;
        this.idLocalizacao = idLocalizacao;
    }
   
    
    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getNomeAnimal() {
        return nomeAnimal;
    }

    public void setNomeAnimal(String nome) {
        this.nomeAnimal = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDescricaoAnimal() {
        return descricaoAnimal;
    }

    public void setDescricaoAnimal(String descricao) {
        this.descricaoAnimal = descricao;
    }
    
    public String getFotoAnimal(){
        return fotoAnimal;
    }
    public void setFotoAnimal(String foto){
        this.fotoAnimal = foto;
    }

    public String getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(String tipo) {
        this.tipoAnimal = tipo;
    }

    public Date getCadastro() {
        return cadastro;
    }

    public void setCadastro(Date cadastro) {
        this.cadastro = cadastro;
    }

    public boolean isStatusAnimal() {
        return statusAnimal;
    }

    public void setStatusAnimal(boolean status) {
        this.statusAnimal = status;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdLocalizacao() {
        return idLocalizacao;
    }

    public void setIdLocalizacao(int idLocalizacao) {
        this.idLocalizacao = idLocalizacao;
    }
}
