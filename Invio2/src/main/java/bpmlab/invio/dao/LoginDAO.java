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
            Login login = (Login) q.getSingleResult();
            return login;
        } catch (Exception e) {
            return null;
        } finally {
            JpaUtil.closeEntityManager();
        }
    }
    
    public boolean existe(String email) {
        String query = "SELECT l FROM Login l WHERE l.email = :email";
        Query q = super.getEntityManager().createQuery(query);
        q.setParameter("email", email);
        boolean resultado;
        try {
            resultado = ((Login) q.getSingleResult()).getEmail().equalsIgnoreCase(email);
            return resultado;
        } catch (Exception e) {
            return false;
        } finally {
            JpaUtil.closeEntityManager();
        }
        
    }
    
}
