/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import DAO.PessoaJuridicaDAO;
import Modelo.PessoaJuridica;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
public class ClinicaPetshopBean {
    private List<PessoaJuridica> clinicasPetshops = new ArrayList();
    
    public ClinicaPetshopBean() {
        try {
            clinicasPetshops = PessoaJuridicaDAO.listarClinicaPetshops();
        } catch (SQLException ex) {
            Logger.getLogger(ClinicaPetshopBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<PessoaJuridica> getClinicasPetshops() {
        return clinicasPetshops;
    }

    public void setClinicasPetshops(List<PessoaJuridica> clinicasPetshops) {
        this.clinicasPetshops = clinicasPetshops;
    }
    
    
    
}
