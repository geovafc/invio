package bpmlab.invio.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Renan
 */
@ManagedBean
@RequestScoped
public class UsuarioCRUDBean {

    /**
     * Creates a new instance of UsuarioCRUDBean
     */
    public UsuarioCRUDBean() {
    }
    
    public void salvar (){
        System.out.println("salvou");
    }
}
