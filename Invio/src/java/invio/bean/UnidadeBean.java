/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.bean;

import invio.entidade.Instituicao;
import invio.entidade.Unidade;
import invio.rn.InstituicaoRN;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Junior
 */
@ManagedBean
@RequestScoped
public class UnidadeBean {

    /**
     * Creates a new instance of UnidadeBean
     */
    private InstituicaoRN instituicaoRN = new InstituicaoRN();
    private Unidade unidade = new Unidade();
    private Instituicao instituicao;

    public UnidadeBean() {
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public Instituicao getInstituicao() {
        if (instituicao != null) {
            return instituicao;
        } else {
            return (Instituicao) BeanUtil.lerDaSessao("instituicao");
        }
    }

    public void setInstituicao(Instituicao instituicao) {
        if (BeanUtil.lerDaSessao("instituicao") == null) {
            BeanUtil.colocarNaSessao("instituicao", instituicao);
        }
        this.instituicao = instituicao;
    }

    public List<Unidade> getUnidades() {
        if (instituicao != null) {
            return instituicao.getUnidadeList();
        } else {
            return null; //TODO Retornar lista vazia
        }
    }

    public String salvar() {
        if (unidade.getId() == null) {
            instituicao.getUnidadeList().add(unidade);
        } else {
            int indice = instituicao.getUnidadeList().indexOf(unidade); //Busca pelo ID -- equals
            if (indice >= 0) {
                instituicao.getUnidadeList().set(indice, unidade);
            }
        }
        instituicaoRN.salvar(instituicao);
        return "listarUnidades.xhtml";
    }

    public String excluir() {
        int indice = instituicao.getUnidadeList().indexOf(unidade); //Busca pelo ID -- equals
        if (indice >= 0) {
            instituicao.getUnidadeList().remove(indice);
        }
        instituicaoRN.salvar(instituicao);
        return "listarUnidades.xhtml";
    }
}
