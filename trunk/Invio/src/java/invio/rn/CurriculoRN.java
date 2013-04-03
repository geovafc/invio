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
        boolean salvou = false;

        if (dao.iniciarTransacao()) {
            if (c.getId() == null) {
                if (dao.criar(c)) {
                    salvou = true;
                }
            } else {
                if (dao.alterar(c)) {
                    salvou = true;
                }
            }
            dao.concluirTransacao();
        }
        return salvou;
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
