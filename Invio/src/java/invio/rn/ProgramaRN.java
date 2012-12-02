/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.rn;

import invio.dao.GenericDAO;
import invio.entidade.Area;
import invio.entidade.Instituicao;
import invio.entidade.Programa;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author Dir de Armas Marinha
 */
public class ProgramaRN {

    GenericDAO<Programa> dao = new GenericDAO<Programa>();
    GenericDAO<Instituicao> daoInstituicao = new GenericDAO<Instituicao>();
    GenericDAO<Area> daoArea = new GenericDAO<Area>();
    Programa programa = new Programa();

    public boolean salvar(Programa p) {
        if (p.getId() == null) {
            return dao.criar(p);
        } else {
            return dao.alterar(p);
        }
    }

    public boolean remover(Programa p) {
        return dao.excluir(p);
    }

    public Programa obter(Integer id) {
        return dao.obter(Programa.class, id);
    }

    public List<Programa> obterTodos() {
        return dao.obterTodos(Programa.class);
    }

    public SelectItem[] getInstituicoes() {
        List<Instituicao> instituicaos = daoInstituicao.obterTodos(Instituicao.class);
        SelectItem[] retorno = new SelectItem[instituicaos.size()];
        for (int i = 0; i < retorno.length; i++) {
            retorno[i] = new SelectItem(instituicaos.get(i), instituicaos.get(i).getNome());
        }
        return retorno;
    }

    public static void main(String[] args) {
        ProgramaRN programaRN = new ProgramaRN();
        for (Programa p : programaRN.obterTodos()) {
            System.out.println(p);
        }
    }

    public List<Area> obterSelecionados2(Programa programa) {

        List<Area> temp = daoArea.obterTodos(Area.class);
        ArrayList<Area> areasSelecionadas = new ArrayList<Area>();

        if (temp != null) {
            for (Area area : temp) {
                if (area.getId().equals(programa.getId())) {
                    areasSelecionadas.add(area);
                }
            }
        }

        return areasSelecionadas;
    }

    public List<Area> obterItens2() {

        List<Area> areas = new ArrayList<Area>();
        areas = daoArea.obterTodos(Area.class);

        List<Area> selecionados2;
        selecionados2 = programa.getAreaList();

        List<Area> areasNaoSelecionadas = new ArrayList<Area>();

        if (selecionados2 != null) {

            for (Area area : areas) {

                for (Area area2 : selecionados2) {

                    if (area.getId() != area2.getId()) {

                        areasNaoSelecionadas.add(area);
                    }
                }
            }
        } else {
            areasNaoSelecionadas = daoArea.obterTodos(Area.class);;
        }


        System.out.println("areas Nao Selecionadas: " + areasNaoSelecionadas);
        return areasNaoSelecionadas;
    }

    public List<Area> obterAreas() {

        return daoArea.obterTodos(Area.class);
    }
}
