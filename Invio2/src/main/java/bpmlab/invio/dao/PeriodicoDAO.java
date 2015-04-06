package bpmlab.invio.dao;

import bpmlab.invio.entidade.Curriculo;
import bpmlab.invio.entidade.Periodico;
import java.util.List;
import javax.persistence.Query;

public class PeriodicoDAO extends GenericDAO<Periodico> {

    public List<Periodico> obterPeriodicosAtuais(Curriculo curriculo, String anoAtual, String anoLimite) {
        String consulta = "SELECT o FROM Periodico o "
                + "WHERE o.curriculo = :curriculo AND o.ano BETWEEN :anoLimite AND :anoAtual ORDER BY o.ano desc";
        Query query = getEntityManager().createQuery(consulta);
        List<Periodico> periodicos = query.setParameter("curriculo", curriculo).
                setParameter("anoAtual", anoAtual).
                setParameter("anoLimite", anoLimite).
                getResultList();
        JpaUtil.closeEntityManager();
        return periodicos;
    }
    
    public List<Periodico> obterPeriodicosPassados(Curriculo curriculo, String anoLimite) {
        String consulta = "SELECT o FROM Periodico o "
                + "WHERE o.curriculo = :curriculo AND o.ano < :anoLimite ORDER BY o.ano desc";
        Query query = getEntityManager().createQuery(consulta);
        List<Periodico> periodicos = query.setParameter("curriculo", curriculo).
                setParameter("anoLimite", anoLimite).
                getResultList();
        JpaUtil.closeEntityManager();
        return periodicos;
    }
    
    public List<Periodico> obterPeriodicos(Curriculo curriculo,
            boolean avaliado) {
        String consulta = "select o from Periodico o "
                + "where o.curriculo = :curriculo and "
                + (avaliado ? "not(o.avaliacao is null) " : "o.avaliacao is null ")
                + "ORDER BY o.ano desc";
        Query query = getEntityManager().createQuery(consulta);
        query.setParameter("curriculo", curriculo);
        List<Periodico> periodicos = query.getResultList();
        JpaUtil.closeEntityManager();
        return periodicos;
    }

    public List<Periodico> obterPeriodicos2(Curriculo curriculo,
            boolean avaliado) {
        String consulta2 = "select o from Periodico o where o.curriculo=" + curriculo.getId() + " ";

        if (avaliado == true) {
            consulta2 += "and NOT (o.avaliacao IS NULL)";
        } else {
            consulta2 += "and o.avaliacao IS NULL";
        }
        List<Periodico> orientacoes = getEntityManager().createQuery(consulta2).getResultList();
        JpaUtil.closeEntityManager();
        return orientacoes;
    }
}
