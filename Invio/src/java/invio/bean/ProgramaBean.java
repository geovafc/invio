/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.bean;

import invio.entidade.Area;
import invio.entidade.Programa;
import invio.rn.AreaRN;
import invio.rn.ProgramaRN;
import invio.util.ComparadorArea;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Junior
 */
@ManagedBean
@SessionScoped
public class ProgramaBean {

    /**
     * Creates a new instance of ProgramaBean
     */
    private ProgramaRN programaRN = new ProgramaRN();
    private AreaRN areaRN = new AreaRN();
    private List<Programa> programas;
    private List<Area> areas;
    private Programa programa = new Programa();

    public List<Area> getAreas() {
        areas = programaRN.obterAreas();
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    public ProgramaBean() {
    }

    public List<Programa> getProgramas() {
        //if (programas == null) {
        programas = programaRN.obterTodos();
        System.out.println("Programas: " + programas);
        //}
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

    public String irAreasPrograma() {
        System.out.println("PROGRAMA - AREA :" + programa);
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

    public String novoPrograma() {
        programa = new Programa();
        return "/cadastro/programa/formulario.xhtml";
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
    // CONTROLE DE AREA APARTIR DESSA LINHA
    // CONTROLE DE AREA APARTIR DESSA LINHA
    private DualListModel<Area> itens2;
    private List<Area> selecionadas;
    private List<String> selecionadosVazio;
    private ComparadorArea comparadorArea = new ComparadorArea();

    public DualListModel<Area> getItensAreas() {

        selecionadas = new ArrayList<Area>();
        selecionadas = programa.getAreaList();


        List<Area> naoSelecionada = programaRN.obterItensNaoSelecionados(programa);

        Collections.sort(naoSelecionada, comparadorArea);
        //  temp.removeAll(selecionados2);

        if (selecionadas != null) {
            itens2 = new DualListModel<Area>(naoSelecionada, selecionadas);
        } else {
            itens2 = new DualListModel<Area>(naoSelecionada, null);
        }

        return itens2;
    }

    public void setItensAreas(DualListModel<Area> itens) {
        this.itens2 = itens;
    }

    public String salvarAreasPrograma() {
        List<Area> areasPrograma = (ArrayList<Area>) itens2.getTarget();


        for (Area area : areasPrograma) {
            area.getProgramaList().add(programa);
            areaRN.salvar(area);
            programa.getAreaList().add(area);
            programaRN.salvar(programa);
        }
        

        return "/cadastro/programa/listar.xhtml";
    }
}
