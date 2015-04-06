package bpmlab.invio.dao;

import bpmlab.invio.entidade.Curriculo;
import bpmlab.invio.entidade.Frequencia;
import java.util.List;
import javax.persistence.Query;

public class FrequenciaDAO extends GenericDAO<Frequencia> {

    GenericDAO<Frequencia> genericDAO = new GenericDAO<Frequencia>();

        public List<Frequencia> obterFrequencias(Curriculo curriculo) {
        String consulta = "select o from Frequencia o "
                + "where o.curriculo = :curriculo ORDER BY o.mes desc";
        Query query = getEntityManager().createQuery(consulta);
        query.setParameter("curriculo", curriculo);
        List<Frequencia> frequencias = query.getResultList();
        JpaUtil.closeEntityManager();
        return frequencias;
    }
}
