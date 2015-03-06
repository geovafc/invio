
package bpmlab.invio.rn;

import bpmlab.invio.dao.GenericDAO;
import bpmlab.invio.dao.LivroDAO;
import bpmlab.invio.entidade.Curriculo;
import bpmlab.invio.entidade.Livro;
import java.util.Calendar;
import java.util.List;

public class LivroRN {

    GenericDAO<Livro> dao = new GenericDAO<Livro>();
    private LivroDAO livroDAO = new LivroDAO();
    private Calendar c = Calendar.getInstance();
    private String anoAtual = String.valueOf(c.getWeekYear());
    private String anoLimite = String.valueOf(c.getWeekYear() - 4);

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

    public List<Livro> obterLivrosAtuais(Curriculo curriculo) {
        return livroDAO.obterLivrosAtuais(curriculo, anoAtual, anoLimite);
    }

    public List<Livro> obterLivrosPassados(Curriculo curriculo) {
        return livroDAO.obterLivrosPassados(curriculo, anoLimite);
    }
}
