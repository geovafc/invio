/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.bean;

import invio.entidade.Area;
import invio.rn.AreaRN;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Junior
 */
@ManagedBean
@RequestScoped
public class AreaBean {

    private AreaRN areaRN = new AreaRN();
    private List<Area> areas;
    private Area area = new Area();

    /**
     * Creates a new instance of AreaBean
     */
        public AreaBean() {
    }

    public List<Area> getAreas() {
        if (areas == null) {
            areas = areaRN.obterTodos();
        }
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        System.out.println("setArea " + area);
        this.area = area;
    }

    public String salvar() {
        if (areaRN.salvar(area)) {
            BeanUtil.criarMensagemDeInformacao(
                    "Operação realizada com sucesso",
                    "A área " + area.getNome() + " foi gravada com sucesso.");
        }else {
            BeanUtil.criarMensagemDeErro("Erro ao salvar a área", "Área: " + area.getNome());
        }
        return "listar.xhtml";
    }

    public String excluir() {
        System.out.println("Area " + area);
        if (areaRN.remover(area)) {
            BeanUtil.criarMensagemDeInformacao("Área excluída", "Área: " + area.getNome());
        } else {
            BeanUtil.criarMensagemDeErro("Erro ao excluir a área", "Área: " + area.getNome());
        }
        return "listar.xhtml";
    }
    
}
