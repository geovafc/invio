package invio.rn;

import invio.dao.GenericDAO;
import invio.entidade.Livro;
import java.util.List;

public class LivroRN {
        GenericDAO<Livro> dao = new GenericDAO<Livro>();
  

    public boolean salvar(Livro livro) {
        if (livro.getId() == null) {
            return dao.criar(livro);
        } else {
            return dao.alterar(livro);
        }
    }

    public boolean remover(Livro livro) {
        return dao.excluir(livro);
    }

    public Livro obter(Integer id) {
        return dao.obter(Livro.class, id);
    }

    public List<Livro> obterTodos() {
        return dao.obterTodos(Livro.class);
    }
    

}
