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
public class Evento {
    private int idEvento;
    private String nomeEvento;
    private String dataEvento;
    private String horarioEvento;
    private String descricaoEvento;
    private String fotoEvento;
    private int idUsuario;
    private String localizacao;

    public Evento() {
    }

    public Evento(int idEvento, String nomeEvento, String dataEvento, String horarioEvento, String descricaoEvento, String fotoEvento, int idUsuario, String localizacao) {
        this.idEvento = idEvento;
        this.nomeEvento = nomeEvento;
        this.dataEvento = dataEvento;
        this.horarioEvento = horarioEvento;
        this.descricaoEvento = descricaoEvento;
        this.fotoEvento = fotoEvento;
        this.idUsuario = idUsuario;
        this.localizacao = localizacao;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public String getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getHorarioEvento() {
        return horarioEvento;
    }

    public void setHorarioEvento(String horarioEvento) {
        this.horarioEvento = horarioEvento;
    }

    public String getDescricaoEvento() {
        return descricaoEvento;
    }

    public void setDescricaoEvento(String descricaoEvento) {
        this.descricaoEvento = descricaoEvento;
    }

    public String getFotoEvento() {
        return fotoEvento;
    }

    public void setFotoEvento(String fotoEvento) {
        this.fotoEvento = fotoEvento;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getlocalizacao() {
        return localizacao;
    }

    public void setlocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
    
}
