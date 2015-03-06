package bpmlab.invio.dao;

import bpmlab.invio.entidade.Login;
import javax.persistence.Query;

/**
 *
 * @author Renan
 */
public class LoginDAO extends GenericDAO<Login>{
    public Login obter(String email) {
        String query = "SELECT l FROM Login l WHERE l.email = :email";
        Query q = super.getEntityManager().createQuery(query);
        q.setParameter("email", email);
        try {
            return (Login) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    public boolean existe(String email) {
        String query = "SELECT l FROM Login l WHERE l.email = :email";
        Query q = super.getEntityManager().createQuery(query);
        q.setParameter("email", email);
        try {
            return ((Login) q.getSingleResult()).getEmail().equalsIgnoreCase(email);
        } catch (Exception e) {
            return false;
        }
        
    }
    
}
