package invio.dao;

import invio.entidade.Curriculo;
import invio.entidade.Periodico;
import java.util.List;
import javax.persistence.Query;

public class PeriodicoDAO extends GenericDAO<Periodico> {

    GenericDAO<Periodico> genericDAO = new GenericDAO<Periodico>();

    public List<Periodico> obterPeriodicos(Curriculo curriculo) {
        String consulta = "select o from Periodico o "
                + "where o.curriculo = :curriculo ORDER BY o.ano desc";
        Query query = getEntityManager().createQuery(consulta);
        query.setParameter("curriculo", curriculo);
        List<Periodico> periodicos = query.getResultList();
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
        List<Periodico> orientacoes = genericDAO.getEntityManager().createQuery(consulta2).getResultList();
        return orientacoes;
    }
}
