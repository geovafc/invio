package invio.dao;

import invio.entidade.Curriculo;
import invio.entidade.Periodico;
import java.util.List;
import javax.persistence.Query;

public class PeriodicoDAO extends GenericDAO<Periodico> {

    public List<Periodico> obterPeriodicos(Curriculo curriculo) {
        String consulta = "select o from Periodico o "
                + "where o.curriculo = :curriculo";
        Query query = getEntityManager().createQuery(consulta);
        query.setParameter("curriculo", curriculo);
        List<Periodico> periodicos = query.getResultList();
        return periodicos;
    }
}
