/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.rn;

import invio.dao.GenericDAO;
import invio.entidade.Area;
import java.util.List;

/**
 *
 * @author Dir de Armas Marinha
 */
public class AreaRN {

    GenericDAO<Area> dao = new GenericDAO<Area>();

    public boolean salvar(Area a) {
        if (a.getId() == null) {
            return dao.criar(a);
        } else {
            return dao.alterar(a);
        }
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
