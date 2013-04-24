/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.dao;

import invio.entidade.Curriculo;
import invio.entidade.Plano;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Dir de Armas Marinha
 */
public class PlanoDAO extends GenericDAO<Plano> {

    public List<Plano> obterPlanoDecrescente(Curriculo c) {

        String query = "SELECT p FROM Plano p INNER JOIN p.curriculoList cl WHERE cl = :c ORDER BY p.id desc";
        final Query q = getEntityManager().createQuery(query);
        try {
            return (List<Plano>) q.getResultList();
        } catch (Exception e) {
            return null;
        }

    }
}
