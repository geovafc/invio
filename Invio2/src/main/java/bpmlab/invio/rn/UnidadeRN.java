/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bpmlab.invio.rn;

import bpmlab.invio.dao.GenericDAO;
import bpmlab.invio.entidade.Instituicao;
import bpmlab.invio.entidade.Unidade;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dir de Armas Marinha
 */
public class UnidadeRN {

    GenericDAO<Unidade> dao = new GenericDAO<Unidade>();
   private List<Unidade> unidades;
    
    public boolean salvar(Unidade u) {
        boolean salvou = false;

        if (dao.iniciarTransacao()) {
            if (u.getId() == null) {
                if (dao.criar(u)) {
                    salvou = true;
                }
            } else {
                if (dao.alterar(u)) {
                    salvou = true;
                }
            }
            dao.concluirTransacao();
        }
        return salvou;
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
        List<Unidade> unidadesTemp1 = dao.obterTodos(Unidade.class);
        ArrayList<Unidade> unidadesTemp2 = new ArrayList<Unidade>();
        
        
        for (Unidade unidade : unidadesTemp1) {
            
            if (unidade.getInstituicao().getId().equals(instituicao.getId())) {
                unidadesTemp2.add(unidade);
            }
            
        }
        return unidadesTemp2;
}
}
