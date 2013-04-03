package invio.rn;

import invio.dao.GenericDAO;
import invio.entidade.Periodico;
import java.util.List;

public class PeriodicoRN {
        GenericDAO<Periodico> dao = new GenericDAO<Periodico>();
  

    public boolean salvar(Periodico periodico) {
        boolean salvou = false;

        if (dao.iniciarTransacao()) {
            if (periodico.getId() == null) {
                if (dao.criar(periodico)) {
                    salvou = true;
                }
            } else {
                if (dao.alterar(periodico)) {
                    salvou = true;
                }
            }
            dao.concluirTransacao();
        }
        return salvou;
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
