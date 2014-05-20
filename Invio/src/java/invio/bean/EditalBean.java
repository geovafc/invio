/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package invio.bean;

import invio.entidade.Edital;
import invio.rn.EditalRN;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author toshiaki
 */
@ManagedBean
@ViewScoped
public class EditalBean {
    private EditalRN editalRN= new EditalRN();
    private List<Edital> editais;
    private Edital edital = new Edital();

    public List<Edital> getEditais() {
        editais =  editalRN.obterTodos();
        return editais;
    }

    public void setEditais(List<Edital> editais) {
        this.editais = editais;
    }

    public Edital getEdital() {
        return edital;
    }

    public void setEdital(Edital edital) {
        this.edital = edital;
    }
    
    
}
