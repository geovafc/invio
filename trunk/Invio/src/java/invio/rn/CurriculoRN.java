/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.rn;

import invio.dao.GenericDAO;
import invio.entidade.Curriculo;
import java.util.List;

/**
 *
 * @author Dir de Armas Marinha
 */
public class CurriculoRN {

    GenericDAO<Curriculo> dao = new GenericDAO<Curriculo>();

    public boolean salvar(Curriculo c) {
        if (c.getId() == null) {
            return dao.criar(c);
        } else {
            return dao.alterar(c);
        }
    }

    public boolean remover(Curriculo c) {
        return dao.excluir(c);
    }

    public Curriculo obter(Integer id) {
        return dao.obter(Curriculo.class, id);
    }

    public List<Curriculo> obterTodos() {
        return dao.obterTodos(Curriculo.class);
    }
}
