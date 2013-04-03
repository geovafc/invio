package invio.rn;

import invio.dao.GenericDAO;
import invio.entidade.Perfil;
import java.util.List;

/**
 *
 * @author Renan
 */
public class PerfilRN {

    GenericDAO<Perfil> dao = new GenericDAO<Perfil>();

    public boolean salvar(Perfil perfil) {
        boolean salvou = false;

        if (dao.iniciarTransacao()) {
            if (perfil.getId() == null) {
                if (dao.criar(perfil)) {
                    salvou = true;
                }
            } else {
                if (dao.alterar(perfil)) {
                    salvou = true;
                }
            }
            dao.concluirTransacao();
        }
        return salvou;
    }

    public boolean remover(Perfil perfil) {
        return dao.excluir(perfil);
    }

    public Perfil obter(Integer id) {
        return dao.obter(Perfil.class, id);
    }
    
    public List<Perfil> obterTodos (){
        return dao.obterTodos(Perfil.class);
    }
}