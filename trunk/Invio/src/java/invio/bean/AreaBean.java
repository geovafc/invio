/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.bean;

import invio.rn.AreaRN;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Junior
 */
@ManagedBean
@RequestScoped
public class AreaBean {
    
    AreaRN areaRN = new AreaRN();

    /**
     * Creates a new instance of AreaBean
     */
    public AreaBean() {
    }
    
    public String salvar (){
        
        areaRN.salvar(null);
        
        return "/cadastro/confirmacao/";
    }
    
}
