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
public class Permissao {
    private int idPermissao;
    private String nomePermissao;
    private String descricao;

    public Permissao() {
    }

    public Permissao(int idPermissao, String nomePermissao, String descricao) {
        this.idPermissao = idPermissao;
        this.nomePermissao = nomePermissao;
        this.descricao = descricao;
    }

    public int getIdPermissao() {
        return idPermissao;
    }

    public void setIdPermissao(int idPermissao) {
        this.idPermissao = idPermissao;
    }

    public String getNomePermissao() {
        return nomePermissao;
    }

    public void setNomePermissao(String nomePermissao) {
        this.nomePermissao = nomePermissao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
    
}
