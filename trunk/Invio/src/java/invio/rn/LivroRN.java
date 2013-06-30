package invio.rn;

import invio.dao.GenericDAO;
import invio.dao.LivroDAO;
import invio.entidade.Curriculo;
import invio.entidade.Livro;
import java.util.List;

public class LivroRN {

    GenericDAO<Livro> dao = new GenericDAO<Livro>();
    private LivroDAO livroDAO = new LivroDAO();

    public boolean salvar(Livro livro) {
        boolean salvou = false;

        if (dao.iniciarTransacao()) {
            if (livro.getId() == null) {
                if (dao.criar(livro)) {
                    salvou = true;
                }
            } else {
                if (dao.alterar(livro)) {
                    salvou = true;
                }
            }
            dao.concluirTransacao();
        }
        return salvou;
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

    public List<Livro> obterTodosAvaliado(Curriculo curriculo) {
        return livroDAO.obterLivros2(curriculo, true);
    }
    
    public List<Livro> obterTodosParaAvaliar(Curriculo curriculo) {
        return livroDAO.obterLivros2(curriculo, false);
    }

    public List<Livro> obterLivros(Curriculo curriculo) {
        return livroDAO.obterLivros(curriculo);
    }
}
