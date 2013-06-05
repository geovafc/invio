/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.dao;

import invio.entidade.Curriculo;
import invio.entidade.Orientacao;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author fabio
 */
public class OrientacaoDAO extends GenericDAO<Orientacao> {
    
    public List<Orientacao> obterOrientacoes(Curriculo curriculo) {
        String consulta = "select o from Orientacao o "
                + "where o.curriculo = :curriculo ORDER BY o.pFinal desc";
        Query query = getEntityManager().createQuery(consulta);
        query.setParameter("curriculo", curriculo);
        List<Orientacao> orientacoes = query.getResultList();
        return orientacoes;
    }
    
}
