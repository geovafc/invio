package bpmlab.invio.rn;

import bpmlab.invio.dao.GenericDAO;
import bpmlab.invio.dao.RelatorioDAO;
import bpmlab.invio.entidade.Curriculo;
import bpmlab.invio.entidade.Relatorio;
import java.util.List;

public class RelatorioRN {

    GenericDAO<Relatorio> dao = new GenericDAO<Relatorio>();
    private RelatorioDAO relatorioDAO = new RelatorioDAO();

    public boolean salvar(Relatorio relatorio) {
        boolean salvou = false;

        if (relatorio.getId() == null) {
            if (dao.criar(relatorio)) {
                salvou = true;
            }
        } else {
            if (dao.alterar(relatorio)) {
                salvou = true;
            }
        }
        return salvou;
    }

    public boolean remover(Relatorio relatorio) {
        return dao.excluir(relatorio);
    }

    public Relatorio obter(Integer id) {
        return dao.obter(Relatorio.class, id);
    }

    public List<Relatorio> obterTodos() {
        return dao.obterTodos(Relatorio.class);
    }

    public List<Relatorio> obterRelatorios(Curriculo curriculo) {
        return relatorioDAO.obterRelatorios(curriculo);
    }
}
