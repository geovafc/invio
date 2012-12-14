package invio.rn;

import invio.dao.GenericDAO;
import invio.entidade.Producao;
import java.util.List;

public class ProducaoRN {
        GenericDAO<Producao> dao = new GenericDAO<Producao>();
  

    public boolean salvar(Producao producao) {
        if (producao.getId() == null) {
            return dao.criar(producao);
        } else {
            return dao.alterar(producao);
        }
    }

    public boolean remover(Producao producao) {
        return dao.excluir(producao);
    }

    public Producao obter(Integer id) {
        return dao.obter(Producao.class, id);
    }

    public List<Producao> obterTodos() {
        return dao.obterTodos(Producao.class);
    }
    

}
