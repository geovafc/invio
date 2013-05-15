package invio.rn;

import invio.dao.GenericDAO;
import invio.entidade.Area;
import invio.entidade.Curriculo;
import invio.entidade.Orientacao;
import invio.entidade.Programa;
import invio.rn.pdf.QualisRN;
import java.util.List;

public class OrientacaoRN {

    private GenericDAO<Orientacao> dao = new GenericDAO<Orientacao>();
    private QualisRN qualisRN = new QualisRN();
    private GenericDAO<Curriculo> curriculoDAO = new GenericDAO<Curriculo>();

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
}
