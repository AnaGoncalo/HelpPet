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
public class Encontro {
    private int idEncontro;
    private Date dataEncontro;
    private String horarioEncontro;
    private boolean statusEncontro;
    private boolean editado;
    private int idAnimal;
    private int idUsuario;
    private int idLocalizacao;

    public Encontro() {
    }

    public Encontro(int idEncontro, Date dataEncontro, String horarioEncontro, boolean statusEncontro, boolean editado, int idAnimal, int idUsuario, int idLocalizacao) {
        this.idEncontro = idEncontro;
        this.dataEncontro = dataEncontro;
        this.horarioEncontro = horarioEncontro;
        this.statusEncontro = statusEncontro;
        this.editado = editado;
        this.idAnimal = idAnimal;
        this.idUsuario = idUsuario;
        this.idLocalizacao = idLocalizacao;
    }

    public int getIdEncontro() {
        return idEncontro;
    }

    public void setIdEncontro(int idEncontro) {
        this.idEncontro = idEncontro;
    }

    public Date getDataEncontro() {
        return dataEncontro;
    }

    public void setDataEncontro(Date dataEncontro) {
        this.dataEncontro = dataEncontro;
    }

    public String getHorarioEncontro() {
        return horarioEncontro;
    }

    public void setHorarioEncontro(String horarioEncontro) {
        this.horarioEncontro = horarioEncontro;
    }

    public boolean isStatusEncontro() {
        return statusEncontro;
    }

    public void setStatusEncontro(boolean statusEncontro) {
        this.statusEncontro = statusEncontro;
    }

    public boolean isEditado() {
        return editado;
    }

    public void setEditado(boolean editado) {
        this.editado = editado;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
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
