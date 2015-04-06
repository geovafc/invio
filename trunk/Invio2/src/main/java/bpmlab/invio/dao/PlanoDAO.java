/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bpmlab.invio.dao;

import bpmlab.invio.entidade.Curriculo;
import bpmlab.invio.entidade.Plano;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Dir de Armas Marinha
 */
public class PlanoDAO extends GenericDAO<Plano> {

    public List<Plano> obterPlanoDecrescente(Curriculo c) {
        String query = "SELECT p FROM Plano p INNER JOIN p.curriculoList cl WHERE cl = :c ORDER BY p.id desc";
        Query q = getEntityManager().createQuery(query);
        q.setParameter("c", c.getId());
        JpaUtil.closeEntityManager();
        try {
            return (List<Plano>) q.getResultList();
        } catch (Exception e) {
            return null;
        }

    }
}
