package invio.rn;

import invio.dao.PerfilDAO;
import invio.entidade.Perfil;
import java.util.List;

/**
 *
 * @author Renan
 */
public class PerfilRN {

    PerfilDAO dao = new PerfilDAO();

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
    
    public Perfil obter(String descricao) {
        return dao.obter(descricao);
    }
    
    public List<Perfil> obterTodos (){
        return dao.obterTodos(Perfil.class);
    }
}