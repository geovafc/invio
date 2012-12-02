/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.rn;

import invio.dao.GenericDAO;
import invio.entidade.Login;
import java.util.List;

/**
 *
 * @author Dir de Armas Marinha
 */
public class LoginRN {

    GenericDAO<Login> dao = new GenericDAO<Login>();

    public boolean salvar(Login login) {
        if (login.getId() == null) {
            return dao.criar(login);
        } else {
            return dao.alterar(login);
        }
    }

    public boolean remover(Login login) {
        return dao.excluir(login);
    }

    public Login obter(Integer id) {
        return dao.obter(Login.class, id);
    }

    public List<Login> obterTodos() {
        return dao.obterTodos(Login.class);
    }
}
