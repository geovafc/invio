/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.rn;

import invio.dao.GenericDAO;
import invio.entidade.Unidade;
import invio.entidade.Instituicao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dir de Armas Marinha
 */
public class UnidadeRN {

    GenericDAO<Unidade> dao = new GenericDAO<Unidade>();
    GenericDAO<Unidade> daoUnidades = new GenericDAO<Unidade>();
    private List<Unidade> unidades;
    
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
    
    public List<Unidade> obTerUnidades(Instituicao instituicao) {
        List<Unidade> unidadesTemp1 = instituicao.getUnidadeList();
        ArrayList<Unidade> unidadesTemp2 = new ArrayList<Unidade>();
        
        
        for (Unidade unidade : unidadesTemp1) {
            
            if (unidade.getInstituicao().getId().equals(instituicao.getId())) {
                unidadesTemp2.add(unidade);
            }
            
        }
        return unidadesTemp2;
}
}
