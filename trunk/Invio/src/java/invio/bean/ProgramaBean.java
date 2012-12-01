/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.bean;

import invio.entidade.Area;
import invio.entidade.Programa;
import invio.rn.ProgramaRN;
import invio.util.ComparadorArea;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Junior
 */
@ManagedBean
@RequestScoped
public class ProgramaBean {

    /**
     * Creates a new instance of ProgramaBean
     */
    private ProgramaRN programaRN = new ProgramaRN();
    private List<Programa> programas;
    private Programa programa = new Programa();

    public ProgramaBean() {
    }

    public List<Programa> getProgramas() {
        if (programas == null) {
            programas = programaRN.obterTodos();
            System.out.println("Programas: " +programas);
        }
        return programas;
    }

    public void setProgramas(List<Programa> programas) {
        this.programas = programas;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }
    
    public String irAreasPrograma (){
        
        return "/cadastro/programa/areasPrograma.xhtml";
    }
    
    public String salvar() {
        if (programaRN.salvar(programa)) {
            BeanUtil.criarMensagemDeInformacao(
                    "Operação realizada com sucesso",
                    "O programa " + programa.getNome() + " foi salvo com sucesso.");
        }
        return "listar.xhtml";
    }

    public String excluir() {
        if (programaRN.remover(programa)) {
            BeanUtil.criarMensagemDeInformacao("O programa excluído", "Programa: " + programa.getNome());
        } else {
            BeanUtil.criarMensagemDeErro("Erro ao excluir o programa", "Programa: " + programa.getNome());
        }
        return "listar.xhtml";
    }
    
    public SelectItem[] getInstituicoes() {
        return programaRN.getInstituicoes();
    }
    
    
    private DualListModel<Area> itens2;
    private List<Area> selecionados2;
    private ComparadorArea comparadorArea = new ComparadorArea();
    
    public DualListModel<Area> getItensAreas() {

        selecionados2 = new ArrayList<Area>();

        selecionados2 = programaRN.obterSelecionados2(programa);

        List<Area> temp = programaRN.obterItens2();

        Collections.sort(temp, comparadorArea);
        //  temp.removeAll(selecionados2);
        itens2 = new DualListModel<Area>(temp, selecionados2);
        return itens2;
    }
    
}
