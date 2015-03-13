package bpmlab.invio.rn;

import bpmlab.invio.dao.FrequenciaDAO;
import bpmlab.invio.dao.GenericDAO;
import bpmlab.invio.entidade.Curriculo;
import bpmlab.invio.entidade.Frequencia;
import java.util.List;

public class FrequenciaRN {

    GenericDAO<Frequencia> dao = new GenericDAO<Frequencia>();
    private FrequenciaDAO frequenciaDAO = new FrequenciaDAO();

    public boolean salvar(Frequencia frequencia) {
        boolean salvou = false;

        if (frequencia.getId() == null) {
            if (dao.criar(frequencia)) {
                salvou = true;
            }
        } else {
            if (dao.alterar(frequencia)) {
                salvou = true;
            }
        }
        return salvou;
    }

    public boolean remover(Frequencia frequencia) {
        return dao.excluir(frequencia);
    }

    public Frequencia obter(Integer id) {
        return dao.obter(Frequencia.class, id);
    }

    public List<Frequencia> obterTodos() {
        return dao.obterTodos(Frequencia.class);
    }

    public List<Frequencia> obterFrequencias(Curriculo curriculo) {
        return frequenciaDAO.obterFrequencias(curriculo);
    }
}
