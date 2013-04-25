/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.bean;

import invio.entidade.Edital;
import invio.entidade.Plano;
import invio.rn.PlanoRN;
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
    private PlanoRN planoRN;
    private Plano plano = new Plano();
    private Edital edital;
    private List<Plano> planos;

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    public List<Plano> getPlanos() {
        if (planos == null) {
            planos = planoRN.obterTodos();
        }
        return planos;
    }

    public void setPlanos(List<Plano> planos) {
        this.planos = planos;
    }

    public String salvar() {
        if (planoRN.salvar(plano)) {
            BeanUtil.criarMensagemDeInformacao("Operação realizada com sucesso",
                    "O plano " + plano.getTitulo() + " foi gravado com sucesso.");
        }else{
            BeanUtil.criarMensagemDeErro("Erro ao salvar o plano", "Plano: " + plano.getTitulo());
        }
        return "listar.xhtml";
    }

    public void excluir() {
    }

    public List<Plano> listar() {
        return planos;
    }

    public void salvarCurriculo() {
    }

    public String novo() {
        return "/cadastro/plano/form.xhtml";

    }
}