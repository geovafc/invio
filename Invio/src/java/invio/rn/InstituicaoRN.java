/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.rn;

import invio.dao.GenericDAO;
import invio.entidade.Instituicao;
import java.util.List;

/**
 *
 * @author Dir de Armas Marinha
 */
public class InstituicaoRN {

    GenericDAO<Instituicao> dao = new GenericDAO<Instituicao>();

    public boolean salvar(Instituicao i) {
        if (i.getId() == null) {
            return dao.criar(i);
        } else {
            return dao.alterar(i);
        }
    }

    public boolean remover(Instituicao i) {
        return dao.excluir(i);
    }

    public Instituicao obter(Integer id) {
        return dao.obter(Instituicao.class, id);
    }

    public List<Instituicao> obterTodos() {
        return dao.obterTodos(Instituicao.class);
    }
}
