/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.bean;

import invio.entidade.Plano;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Dir de Armas Marinha
 */
@ManagedBean
@RequestScoped
public class PlanoBean {

    /**
     * Creates a new instance of PlanoBean
     */
    public PlanoBean() {
    }
    private Plano plano = new Plano();
    private List<Plano> planos;
    private UploadedFile arquivo;

    public UploadedFile getArquivo() {
        return arquivo;
    }

    public void setArquivo(UploadedFile arquivo) {
        this.arquivo = arquivo;
    }
    
    
    
    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    public List<Plano> getPlanos() {
        return planos;
    }

    public void setPlanos(List<Plano> planos) {
        this.planos = planos;
    }

    public void salvar() {
    }

    public void excluir() {
    }

    public List<Plano> listar() {
        return planos;
    }

    public void salvarCurriculo() {
    }
    
    public String novo(){
        return "/cadastro/plano/form.xhtml";
        
    }
    
    public void upload(){
        if(arquivo != null) {  
            FacesMessage msg = new FacesMessage("Succesful", arquivo.getFileName() + " is uploaded.");  
            FacesContext.getCurrentInstance().addMessage(null, msg);  
        }  
    }  
    }