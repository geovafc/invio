package bpmlab.invio.rn;

import bpmlab.invio.dao.OrientacaoDAO;
import bpmlab.invio.entidade.Curriculo;
import bpmlab.invio.entidade.Orientacao;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OrientacaoRN {

    private OrientacaoDAO dao = new OrientacaoDAO();

    public boolean salvar(Orientacao orientacao) {
        if (orientacao.getAluno().equals("") ||
                orientacao.getTipoOrientacao() == 0 ||
                orientacao.getTipoBolsa().equals("") ||
                orientacao.getPFinal() == null) {
            return false;
        } else {
            if (orientacao.getId() == null || orientacao.getId() == 0) {
                return dao.criar(orientacao);
            } else {
                return dao.alterar(orientacao);
            }
        }
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
        return dao.obterOrientacoes2(curriculo, true);
    }

    public List<Orientacao> obterTodosParaAvaliar(Curriculo curriculo) {
        return dao.obterOrientacoes2(curriculo, false);
    }

    public List<Orientacao> obterOrientacoesAtuais(Curriculo curriculo) {
        Calendar c2 = Calendar.getInstance();
        c2.getTime();
        c2.add(Calendar.YEAR, -5);
        c2.add(Calendar.MONTH, -4);
        Date dataLimite;
        dataLimite = c2.getTime();
        return dao.obterOrientacoesAtuais(curriculo, dataLimite);
    }

    public List<Orientacao> obterOrientacoesPassadas(Curriculo curriculo) {
        Calendar c1 = Calendar.getInstance();
        c1.getTime();
        c1.add(Calendar.YEAR, -5);
        c1.add(Calendar.MONTH, -4);
        Date dataLimite = c1.getTime();
        return dao.obterOrientacoesPassadas(curriculo, dataLimite);
    }

    public Integer obterExtratoPorTipoOrientacao(int tipo) {
        switch (tipo) {
            case 1:
                return 2;
            case 2:
                return 2;
            case 3:
                return 4;
            case 4:
                return 2;
            case 5:
                return 2;
            case 6:
                return 2;
            case 7:
                return 4;
            case 8:
                return 8;
            default:
                throw new AssertionError();
        }
    }

    public String obterTipoOrientacao(Integer tipo) {
        switch (tipo) {
            case 1:
                return "Bolsista DTI (Orientação)";
            case 2:
                return "Dissertação de Mestrado (Co-orientação)";
            case 3:
                return "Dissertação de Mestrado (Orientação)";
            case 4:
                return "Especialização (Orientação)";
            case 5:
                return "Iniciação Científica (Orientação)";
            case 6:
                return "TCC (Orientação)";
            case 7:
                return "Tese de Doutorado (Co-orientação)";
            case 8:
                return "Tese Doutorado (Orientação)";
            default:
                throw new AssertionError();
        }
    }
}
