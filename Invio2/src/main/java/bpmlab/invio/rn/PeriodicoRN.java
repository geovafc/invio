package bpmlab.invio.rn;

import bpmlab.invio.dao.PeriodicoDAO;
import bpmlab.invio.entidade.Area;
import bpmlab.invio.entidade.Curriculo;
import bpmlab.invio.entidade.Periodico;
import bpmlab.invio.rn.pdf.QualisRN;
import java.util.Calendar;
import java.util.List;

public class PeriodicoRN {

    private QualisRN qualisRN = new QualisRN();
    private PeriodicoDAO periodicoDAO = new PeriodicoDAO();
    private Calendar c = Calendar.getInstance();
    private String anoAtual = String.valueOf(c.getWeekYear());
    private String anoLimite = String.valueOf(c.getWeekYear() - 4);

    public boolean salvar(Periodico periodico) {
        boolean salvou = false;
        atribuirPontuacaoAutomaticamente(periodico);
        if (periodico.getId() == null) {
            salvou = periodicoDAO.criar(periodico);
        } else {
            salvou = periodicoDAO.alterar(periodico);
        }
        return salvou;
    }

    public void atribuirPontuacaoAutomaticamente(Periodico periodico) {
        int pt = 0;
        if (periodico.getCurriculo() != null) {
            Area area = periodico.getCurriculo().getArea();
            pt = qualisRN.obterEstrato(periodico.getRevista(), area.getNome());
            System.out.println(pt);
        }
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

    public List<Periodico> obterTodosAvaliado(Curriculo curriculo) {
        return periodicoDAO.obterPeriodicos2(curriculo, true);
    }

    public List<Periodico> obterTodosParaAvaliar(Curriculo curriculo) {
        return periodicoDAO.obterPeriodicos2(curriculo, false);
    }

    public List<Periodico> obterPeriodicosAtuais(Curriculo curriculo) {
        return periodicoDAO.obterPeriodicosAtuais(curriculo, anoAtual, anoLimite);
    }

    public List<Periodico> obterPeriodicosPassados(Curriculo curriculo) {
        return periodicoDAO.obterPeriodicosPassados(curriculo, anoLimite);
    }
    
}
