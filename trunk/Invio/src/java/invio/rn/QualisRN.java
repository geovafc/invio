/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.rn;

import invio.dao.GenericDAO;
import invio.entidade.Qualis;
import java.util.List;

/**
 *
 * @author Junior
 */
public class QualisRN {

    GenericDAO<Qualis> dao = new GenericDAO<Qualis>();

    public boolean salvar(Qualis qualis) {
        if (qualis.getId() == null) {
            return dao.criar(qualis);
        } else {
            return dao.alterar(qualis);
        }
    }

    public boolean remover(Qualis qualis) {
        return dao.excluir(qualis);
    }

    public Qualis obter(Integer id) {
        return dao.obter(Qualis.class, id);
    }

    public List<Qualis> obterTodos() {
        return dao.obterTodos(Qualis.class);
    }
}
