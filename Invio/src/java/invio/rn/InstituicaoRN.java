/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.rn;

import invio.dao.GenericDAO;
import invio.entidade.Instituicao;
import invio.entidade.Unidade;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dir de Armas Marinha
 */
public class InstituicaoRN {

    GenericDAO<Instituicao> dao = new GenericDAO<Instituicao>();
    GenericDAO<Unidade> daoUnidades = new GenericDAO<Unidade>();

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
    
    public List<Unidade> obTerUnidades(Instituicao instituicao) {
        List<Unidade> unidadesTemp1 = daoUnidades.obterTodos(Unidade.class);
        ArrayList<Unidade> unidadesTemp2 = new ArrayList<Unidade>();
        
        for (Unidade unidade : unidadesTemp1) {
            
            if (unidade.getInstituicao().getId().equals(instituicao.getId())) {
                unidadesTemp2.add(unidade);
            }
            
        }
        return unidadesTemp2;
}

}
