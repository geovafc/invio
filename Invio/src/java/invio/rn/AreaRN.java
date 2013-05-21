package invio.rn;

import invio.dao.AreaDAO;
import invio.entidade.Area;
import invio.entidade.Instituicao;
import java.util.List;

public class AreaRN {

    AreaDAO areaDAO = new AreaDAO();

    public boolean salvar(Area a) {

        boolean salvou = false;

        if (areaDAO.iniciarTransacao()) {
            if (a.getId() == null) {
                if (areaDAO.criar(a)) {
                    salvou = true;
                }
            } else {
                if (areaDAO.alterar(a)) {
                    salvou = true;
                }
            }
            areaDAO.concluirTransacao();
        }
        return salvou;
    }

    public boolean remover(Area a) {
        return areaDAO.excluir(a);
    }

    public Area obter(Integer id) {
        return areaDAO.obter(Area.class, id);
    }

    public List<Area> obterTodos() {
        return areaDAO.obterTodos(Area.class);
    }

    public List<Area> obterAreas(Instituicao instituicao) {
        return areaDAO.obterAreas(instituicao);
    }

    public List<Area> completeArea(String digitacao) {
        return areaDAO.obterAreaPorCriterio(digitacao);
    }
}
