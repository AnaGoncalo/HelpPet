/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Ana Gonçalo
 */
public class Encontro {

    private int idEncontro;
    private String dataEncontro;
    private String horarioEncontro;
    private boolean statusEncontro;
    private boolean editado;
    private String localizacao;
    private Animal animal;
    private Usuario adotante;

    public Encontro() {
    }

    public Encontro(int idEncontro, String dataEncontro, String horarioEncontro, boolean statusEncontro, boolean editado, String localizacao, Animal animal, Usuario adotante) {
        this.idEncontro = idEncontro;
        this.dataEncontro = dataEncontro;
        this.horarioEncontro = horarioEncontro;
        this.statusEncontro = statusEncontro;
        this.editado = editado;
        this.localizacao = localizacao;
        this.animal = animal;
        this.adotante = adotante;
    }

    public int getIdEncontro() {
        return idEncontro;
    }

    public void setIdEncontro(int idEncontro) {
        this.idEncontro = idEncontro;
    }

    public String getDataEncontro() {
        return dataEncontro;
    }

    public void setDataEncontro(String dataEncontro) {
        this.dataEncontro = dataEncontro;
    }

    public String getHorarioEncontro() {
        return horarioEncontro;
    }

    public void setHorarioEncontro(String horarioEncontro) {
        this.horarioEncontro = horarioEncontro;
    }

    public String isStatusEncontro() {
        if (this.statusEncontro) {
            return "Confirmado";
        } else {
            return "Não Confirmado";
        }
    }

    public void setStatusEncontro(boolean statusEncontro) {
        this.statusEncontro = statusEncontro;
    }

    public boolean getStatusEncontro() {
        return statusEncontro;
    }

    public boolean isEditado() {
        return editado;
    }

    public void setEditado(boolean editado) {
        this.editado = editado;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Usuario getAdotante() {
        return adotante;
    }

    public void setAdotante(Usuario adotante) {
        this.adotante = adotante;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

}
