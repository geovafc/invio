package invio.dao;

import invio.entidade.Perfil;
import invio.entidade.Perfil;
import javax.persistence.Query;

/**
 *
 * @author Renan
 */
public class PerfilDAO extends GenericDAO<Perfil>{
    public Perfil obter(String descricao) {
        String query = "SELECT p FROM Perfil p WHERE p.descricao = :descricao";
        Query q = super.getEntityManager().createQuery(query);
        q.setParameter("descricao", descricao);
        try {
            return (Perfil) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    public static void main(String[] args) {
        System.out.println(new PerfilDAO().obter("ROLE_MASTER").getId());
    }
}
