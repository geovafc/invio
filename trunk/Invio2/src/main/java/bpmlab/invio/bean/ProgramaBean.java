/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bpmlab.invio.bean;

import bpmlab.invio.bean.util.BeanUtil;
import bpmlab.invio.entidade.Area;
import bpmlab.invio.entidade.Programa;
import bpmlab.invio.rn.AreaRN;
import bpmlab.invio.rn.ProgramaRN;
import bpmlab.invio.util.ComparadorArea;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
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
    private Area area = new Area();
    private Programa programa = new Programa();

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

    public List<Area> completeArea(String digitacao) {
        List<Area> results = areaRN.completeArea(digitacao);
        return results;
    }

    public List<Area> getAreas() {
        return areaRN.obterTodos();
    }

    public String irAreasPrograma() {
        System.out.println("PROGRAMA - AREA :" + programa);
        return "/cadastro/programa/areasPrograma.xhtml";
    }

    public String salvar() {
        programa.setArea(area);
        if (programaRN.salvar(programa)) {
            BeanUtil.criarMensagemDeInformacao(
                    "Operação realizada com sucesso",
                    "O programa " + programa.getNome() + " foi salvo com sucesso.");
            area = new Area();
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
    private ComparadorArea comparadorArea = new ComparadorArea();

//    public DualListModel<Area> getItensAreas() {
//
//        selecionadas = programa.getAreaList();
//
//        List<Area> naoSelecionada = programaRN.obterItensNaoSelecionados(programa);
//
//        Collections.sort(naoSelecionada, comparadorArea);
//
//        if (selecionadas != null) {
//            itens2 = new DualListModel<Area>(naoSelecionada, selecionadas);
//        } else if (selecionadas == null) {
//            selecionadas = new ArrayList<Area>();
//            itens2 = new DualListModel<Area>(naoSelecionada, selecionadas);
//        }
//
//        return itens2;
//    }
    public void setItensAreas(DualListModel<Area> itens) {
        this.itens2 = itens;
    }

//    public String salvarAreasPrograma() {
//        List<Area> areasPrograma = (ArrayList<Area>) itens2.getTarget();
//
//        selecionadas = areasPrograma;
//        
//
//        //OBS: SEMPRE VERIFICAR SE ESTÁ SENDO ESTANCIADO A LISTA: programa.getAreaList()
//        //NA ENTIDADE PROGRAMA.
//
//        programa.setAreaList(selecionadas);
//        programaRN.salvar(programa);
//
//        return "/cadastro/programa/listar.xhtml";
//    }
    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}
