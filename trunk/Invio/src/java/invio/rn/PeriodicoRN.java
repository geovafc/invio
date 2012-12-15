package invio.rn;

import invio.dao.GenericDAO;
import invio.entidade.Periodico;
import java.util.List;

public class PeriodicoRN {
        GenericDAO<Periodico> dao = new GenericDAO<Periodico>();
  

    public boolean salvar(Periodico periodico) {
        if (periodico.getId() == null) {
            return dao.criar(periodico);
        } else {
            return dao.alterar(periodico);
        }
    }

    public boolean remover(Periodico periodico) {
        return dao.excluir(periodico);
    }

    public Periodico obter(Integer id) {
        return dao.obter(Periodico.class, id);
    }

    public List<Periodico> obterTodos() {
        return dao.obterTodos(Periodico.class);
    }
    

}
