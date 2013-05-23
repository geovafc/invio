package invio.rn;

import invio.dao.PeriodicoDAO;
import invio.entidade.Area;
import invio.entidade.Curriculo;
import invio.entidade.Periodico;
import invio.rn.pdf.QualisRN;
import java.util.List;

public class PeriodicoRN {

    private QualisRN qualisRN = new QualisRN();
    private PeriodicoDAO periodicoDAO = new PeriodicoDAO();

    public boolean salvar(Periodico periodico) {
        boolean salvou = false;
        if (periodicoDAO.iniciarTransacao()) {
            if (periodico.getId() == null) {
                atribuirPontuacaoAutomaticamente(periodico);
                salvou = periodicoDAO.criar(periodico);
            } else {
                salvou = periodicoDAO.alterar(periodico);
            }
            periodicoDAO.concluirTransacao();
        }
        return salvou;
    }

    public void atribuirPontuacaoAutomaticamente(Periodico periodico) {
        int pt = 0;
        if (periodico.getCurriculo() != null) {
            Area area = periodico.getCurriculo().getArea();
            pt = qualisRN.obterEstrato(periodico.getRevista(), area.getNome());
        }
//        Curriculo curriculoDoPeriodico = curriculoDAO.obter(Curriculo.class,
//                periodico.getCurriculo().getId());
//        List<Curriculo> curriculoArea = curriculoDoPeriodico.getArea().getCurriculoList();
//
//        for (Curriculo temp : curriculoArea) {
//            int ptTemp = qualisRN.obterEstrato(periodico.getRevista(), temp.getArea().getNome());
//            if (ptTemp >= pt) {
//                pt = ptTemp;
//            }
//        }
        periodico.setEstrato(pt);
    }

    public boolean remover(Periodico periodico) {
        return periodicoDAO.excluir(periodico);
    }

    public Periodico obter(Integer id) {
        return periodicoDAO.obter(Periodico.class, id);
    }

    public List<Periodico> obterTodos() {
        return periodicoDAO.obterTodos(Periodico.class);
    }

    public List<Periodico> obterPeriodicos(Curriculo curriculo) {
        return periodicoDAO.obterPeriodicos(curriculo);
    }
}
