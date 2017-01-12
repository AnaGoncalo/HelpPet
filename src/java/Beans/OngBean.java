/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Modelo.PessoaJuridica;
import DAO.PessoaJuridicaDAO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Ana Gonçalo
 */
@ManagedBean
@RequestScoped
public class OngBean {
    private List<PessoaJuridica> ongs = new ArrayList();
    
    public OngBean() {
        try {
            ongs = PessoaJuridicaDAO.listarOngs();
        } catch (SQLException ex) {
            Logger.getLogger(OngBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<PessoaJuridica> getOngs() {
        return ongs;
    }

    public void setOngs(List<PessoaJuridica> ongs) {
        this.ongs = ongs;
    }    
    
}
