/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package invio.bean;

import invio.bean.util.BeanUtil;
import invio.entidade.Edital;
import invio.rn.EditalRN;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

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
    public List<Edital> getEditaisAbertos() {
        editais =  editalRN.obterTodosAbertos();
        return editais;
    }
    public List<Edital> getEditaisFechados() {
        editais =  editalRN.obterTodosFechados();
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
    
    public String salvar() {
        if (editalRN.salvar(edital)) {
            BeanUtil.criarMensagemDeInformacao(
                    "Operação realizada com sucesso",
                    "O Edital " + edital.getTitulo() + " foi gravada com sucesso.");
        }else {
            BeanUtil.criarMensagemDeErro("Erro ao salvar o Edital", "Edital: " + edital.getTitulo());
        }
        return "/publico/edital/listaEdital.xhtml";
    }
    
    public String excluir() {
        if (editalRN.remover(edital)) {
            BeanUtil.criarMensagemDeInformacao("Edital excluído", "Edital: " + edital.getTitulo());
        } else {
            BeanUtil.criarMensagemDeErro("Erro ao excluir o edital", "Edital: " + edital.getTitulo());
        }
        return "/publico/edital/listaEdital.xhtml";
    }
    
    public String editar(){
        return "/secretaria/editalForm.xhtml";
    }
    public String submeter(){
        return "/usuario/submissao/uploadFile.xhtml";
    }
    
     public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
}
