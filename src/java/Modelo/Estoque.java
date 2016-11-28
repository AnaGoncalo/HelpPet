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
public class Estoque {
    private int idEstoque;
    private String nomeEstoque;
    private String necessidade;
    private double qtdDiaria;
    private double qtdAtual;
    private int idUsuario;

    public Estoque() {
    }

    public Estoque(int idEstoque, String nomeEstoque, String necessidade, double qtdDiaria, double qtdAtual, int idUsuario) {
        this.idEstoque = idEstoque;
        this.nomeEstoque = nomeEstoque;
        this.necessidade = necessidade;
        this.qtdDiaria = qtdDiaria;
        this.qtdAtual = qtdAtual;
        this.idUsuario = idUsuario;
    }

    public int getIdEstoque() {
        return idEstoque;
    }

    public void setIdEstoque(int idEstoque) {
        this.idEstoque = idEstoque;
    }

    public String getNomeEstoque() {
        return nomeEstoque;
    }

    public void setNomeEstoque(String nomeEstoque) {
        this.nomeEstoque = nomeEstoque;
    }

    public String getNecessidade() {
        return necessidade;
    }

    public void setNecessidade(String necessidade) {
        this.necessidade = necessidade;
    }

    public double getQtdDiaria() {
        return qtdDiaria;
    }

    public void setQtdDiaria(double qtdDiaria) {
        this.qtdDiaria = qtdDiaria;
    }

    public double getQtdAtual() {
        return qtdAtual;
    }

    public void setQtdAtual(double qtdAtual) {
        this.qtdAtual = qtdAtual;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
    
}
