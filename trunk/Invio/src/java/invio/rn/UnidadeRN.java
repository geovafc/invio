/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.rn;

import invio.dao.GenericDAO;
import invio.entidade.Unidade;
import java.util.List;

/**
 *
 * @author Dir de Armas Marinha
 */
public class UnidadeRN {

    GenericDAO<Unidade> dao = new GenericDAO<Unidade>();

    public boolean salvar(Unidade u) {
        if (u.getId() == null) {
            return dao.criar(u);
        } else {
            return dao.alterar(u);
        }
    }

    public boolean remover(Unidade u) {
        return dao.excluir(u);
    }

    public Unidade obter(Integer id) {
        return dao.obter(Unidade.class, id);
    }

    public List<Unidade> obterTodos() {
        return dao.obterTodos(Unidade.class);
    }
}
