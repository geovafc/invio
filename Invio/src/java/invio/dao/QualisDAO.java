package invio.dao;

import invio.entidade.Qualis;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Dir de Armas Marinha
 */
public class QualisDAO extends GenericDAO<Qualis> {

    public String obterEstrato(String titulo, String area, int maxResultados) {
        System.out.println("titulo" + titulo);
        System.out.println("area" + area);

        String query = "SELECT q.estrato FROM qualis q "
                + "WHERE q.titulo = :titulo AND q.areaAvaliacao = :area";
        Query q = getEntityManager().createNativeQuery(query);
                q.setMaxResults(maxResultados);
        q.setParameter("titulo", titulo);
        q.setParameter("area", area);
        try {
            String resultado = (String) q.getSingleResult();

            return resultado;
        } catch (Exception e) {
            return "";
        }
    }

    public List<String> obterTodosTitulos(String palavra, int maxResultados) {
        String query = "SELECT q.titulo FROM qualis q "
                + "WHERE q.titulo like '%" + palavra + "%'"; //Acrescentei o % antes da palavra
        Query q = getEntityManager().createNativeQuery(query);
                q.setMaxResults(maxResultados);
        List<String> resultado = (List<String>) q.getResultList();

        return resultado;
    }

    public List<String> obterTodosTitulosArea(String palavra,int maxResultados) {
        String query = "SELECT distinct q.areaAvaliacao FROM qualis q "
                + "WHERE q.areaAvaliacao like '%" + palavra + "%'";
        Query q = getEntityManager().createNativeQuery(query);
               q.setMaxResults(maxResultados);
               
        List<String> resultado = (List<String>) q.getResultList();

        return resultado;
    }

    public static void main(String[] args) {
        System.out.println(new QualisDAO().obterTodosTitulos("AA", 20).get(0));
    }
}
