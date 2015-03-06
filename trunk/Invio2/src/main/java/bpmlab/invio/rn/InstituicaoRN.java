/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bpmlab.invio.rn;

import bpmlab.invio.dao.GenericDAO;
import bpmlab.invio.entidade.Instituicao;
import java.util.List;

/**
 *
 * @author Dir de Armas Marinha
 */
public class InstituicaoRN {

    GenericDAO<Instituicao> dao = new GenericDAO<Instituicao>();
  

    public boolean salvar(Instituicao i) {
       boolean salvou = false;

        if (dao.iniciarTransacao()) {
            if (i.getId() == null) {
                if (dao.criar(i)) {
                    salvou = true;
                }
            } else {
                if (dao.alterar(i)) {
                    salvou = true;
                }
            }
            dao.concluirTransacao();
        }
        return salvou;
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
