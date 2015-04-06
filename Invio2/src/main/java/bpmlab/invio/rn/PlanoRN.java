/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bpmlab.invio.rn;

import bpmlab.invio.dao.GenericDAO;
import bpmlab.invio.dao.PlanoDAO;
import bpmlab.invio.entidade.Curriculo;
import bpmlab.invio.entidade.Plano;
import java.util.List;

/**
 *
 * @author Dir de Armas Marinha
 */
public class PlanoRN {

    GenericDAO<Plano> dao = new GenericDAO<Plano>();
    PlanoDAO pdao = new PlanoDAO();

    public boolean salvar(Plano i) {
        boolean salvou = false;

        if (i.getId() == null) {
            if (dao.criar(i)) {
                salvou = true;
            }
        } else {
            if (dao.alterar(i)) {
                salvou = true;
            }
        }
        return salvou;
    }

    public boolean remover(Plano i) {
        return dao.excluir(i);
    }

    public Plano obter(Integer id) {
        return dao.obter(Plano.class, id);
    }

    public List<Plano> obterTodos() {
        return dao.obterTodos(Plano.class);
    }

    public List<Plano> obterTodosDecrescente(Curriculo c) {
        return c.getPlanoList();
    }
}
