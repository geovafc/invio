/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.dao;

import invio.entidade.Qualis;
import javax.persistence.Query;

/**
 *
 * @author Dir de Armas Marinha
 */
public class QualisDAO extends GenericDAO<Qualis> {

    public String obterEstrato(String titulo, String area) {


        String query = "SELECT q.estrato FROM qualis q WHERE q.titulo = :titulo AND q.areaAvaliacao = :area";
        Query q = getEntityManager().createQuery(query);

        q.setParameter("titulo", titulo);
        q.setParameter("area", area);

        String resultado = (String) q.getSingleResult();

        return resultado;

    }    
}
