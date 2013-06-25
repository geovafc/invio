package invio.rn;

import invio.dao.OrientacaoDAO;
import invio.entidade.Curriculo;
import invio.entidade.Orientacao;
import java.util.List;

public class OrientacaoRN {

    private OrientacaoDAO dao = new OrientacaoDAO();

    public boolean salvar(Orientacao orientacao) {
        boolean salvou = false;

        if (dao.iniciarTransacao()) {
            if (orientacao.getId() == null) {
                if (dao.criar(orientacao)) {
                    salvou = true;
                }
            } else {
                if (dao.alterar(orientacao)) {
                    salvou = true;
                }
            }
            dao.concluirTransacao();
        }
        return salvou;
    }

    public boolean remover(Orientacao orientacao) {
        return dao.excluir(orientacao);
    }

    public Orientacao obter(Integer id) {
        return dao.obter(Orientacao.class, id);
    }

    public List<Orientacao> obterTodos() {
        return dao.obterTodos(Orientacao.class);
    }

    public List<Orientacao> obterTodosAvaliado(Curriculo curriculo) {
        return dao.obterOrientacoes(curriculo, true);
    }
    
    public List<Orientacao> obterTodosParaAvaliar(Curriculo curriculo) {
        return dao.obterOrientacoes(curriculo, false);
    }

    public List<Orientacao> obterOrientacoes(Curriculo curriculo) {
        return dao.obterOrientacoes(curriculo);
    }
}
