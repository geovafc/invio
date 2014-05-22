/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.rn;

import invio.dao.EditalDAO;
import invio.dao.GenericDAO;
import invio.entidade.Edital;
import java.util.List;

/**
 *
 * @author toshiaki
 */
public class EditalRN {

    GenericDAO<Edital> dao = new GenericDAO<Edital>();
    EditalDAO daoEdital = new EditalDAO();

    public Edital obter(Edital edital) {
        return dao.obter(Edital.class, edital.getId());
    }
    public List<Edital> obterTodosAbertos() {
        return daoEdital.obterTodosAbertos();
    }
    public List<Edital> obterTodosFechados() {
        return daoEdital.obterTodosFechados();
    }

    public List<Edital> obterTodos() {
        return dao.obterTodos(Edital.class);
    }

    public boolean salvar(Edital edital) {
        boolean salvou = false;
        if (dao.iniciarTransacao()) {
            if (edital.getId() == null) {
                if (dao.criar(edital)) {
                    salvou = true;
                } else {
                    if (dao.alterar(edital)) {
                        salvou = true;
                    }
                }
                dao.concluirTransacao();
            }
        }
        return salvou;
    }

    public boolean remover(Edital edital){
        return dao.excluir(edital);
    }
}
