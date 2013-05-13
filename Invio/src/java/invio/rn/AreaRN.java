package invio.rn;

import invio.dao.AreaDAO;
import invio.dao.GenericDAO;
import invio.entidade.Area;
import java.util.List;

public class AreaRN {

    GenericDAO<Area> dao = new GenericDAO<Area>();
    AreaDAO areaDAO = new AreaDAO();
    
    public List<Area> buscarAreasPorCriterio(String digitacao){
    return areaDAO.obterAreaPorCriterio(digitacao);
    }

    public boolean salvar(Area a) {

        boolean salvou = false;

        if (dao.iniciarTransacao()) {
            if (a.getId() == null) {
                if (dao.criar(a)) {
                    salvou = true;
                }
            } else {
                if (dao.alterar(a)) {
                    salvou = true;
                }
            }
            dao.concluirTransacao();
        }
        return salvou;
    }

    public boolean remover(Area a) {
        return dao.excluir(a);
    }

    public Area obter(Integer id) {
        return dao.obter(Area.class, id);
    }

    public List<Area> obterTodos() {
        return dao.obterTodos(Area.class);
    }
}
