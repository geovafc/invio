package invio.rn;

import invio.dao.GenericDAO;
import invio.dao.FrequenciaDAO;
import invio.entidade.Curriculo;
import invio.entidade.Frequencia;
import java.util.List;

public class FrequenciaRN {

    GenericDAO<Frequencia> dao = new GenericDAO<Frequencia>();
    private FrequenciaDAO frequenciaDAO = new FrequenciaDAO();

    public boolean salvar(Frequencia frequencia) {
        boolean salvou = false;

        if (dao.iniciarTransacao()) {
            if (frequencia.getId() == null) {
                if (dao.criar(frequencia)) {
                    salvou = true;
                }
            } else {
                if (dao.alterar(frequencia)) {
                    salvou = true;
                }
            }
            dao.concluirTransacao();
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
