/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.bean;

import invio.entidade.Instituicao;
import invio.entidade.Unidade;
import invio.rn.UnidadeRN;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Junior
 */
@ManagedBean
@ViewScoped
public class UnidadeBean {

    /**
     * Creates a new instance of UnidadeBean
     */
    
    private UnidadeRN unidadeRN = new UnidadeRN();
    private List<Unidade> unidades;
    private Unidade unidade;
    private Instituicao instituicao;
    
    public UnidadeBean() {
    }

    public List<Unidade> getUnidades() {
        return unidades;
    }

    public void setUnidades(List<Unidade> unidades) {
        this.unidades = unidades;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }
    
    
}
