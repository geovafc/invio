/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bpmlab.invio.bean;

import bpmlab.invio.bean.util.BeanUtil;
import bpmlab.invio.entidade.Curriculo;
import bpmlab.invio.entidade.Edital;
import bpmlab.invio.entidade.Plano;
import bpmlab.invio.rn.PlanoRN;
import bpmlab.invio.util.UploadArquivo;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

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
    private UploadArquivo fileUpload = new UploadArquivo();
    private Curriculo curriculo = new Curriculo();

    public Curriculo getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(Curriculo curriculo) {
        this.curriculo = curriculo;
    }

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
        } else {
            BeanUtil.criarMensagemDeErro("Erro ao salvar o plano", "Plano: " + plano.getTitulo());
        }
        return "listar.xhtml";
    }

    public String excluirPlano() {
        System.out.println("Excluir Plano " + plano);
        if (planoRN.remover(plano)) {
            BeanUtil.criarMensagemDeInformacao("Área excluída", "Área: " + plano.getTitulo());
        } else {
            BeanUtil.criarMensagemDeErro("Erro ao excluir plano", "Plnao: " + plano.getTitulo());
        }
        return "listar.xhtml";
    }

    public List<Plano> listarPlanos() {
        return planos;
    }

    public void salvarCurriculo() {
    }

    public String novo() {
        return "/cadastro/plano/form.xhtml";

    }
}