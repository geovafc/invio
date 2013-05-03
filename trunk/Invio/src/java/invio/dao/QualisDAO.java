package invio.dao;

import invio.entidade.Qualis;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Dir de Armas Marinha
 */
public class QualisDAO extends GenericDAO<Qualis> {

    public String obterEstrato(String titulo, String area) {
        System.out.println("titulo" + titulo);
        System.out.println("area" + area);

        String query = "SELECT q.estrato FROM qualis q WHERE q.titulo = :titulo AND q.areaAvaliacao = :area";
        Query q = getEntityManager().createNativeQuery(query);

        q.setParameter("titulo", titulo);
        q.setParameter("area", area);
        try {
            String resultado = (String) q.getSingleResult();

            return resultado;
        } catch (Exception e) {
            return "";
        }


    }

    public List<String> obterTodosTitulos(String palavra) {
        String query = "SELECT q.titulo FROM qualis q WHERE q.titulo like '" + palavra + "%'";
        Query q = getEntityManager().createNativeQuery(query);

        List<String> resultado = (List<String>) q.getResultList();

        return resultado;
    }

    public List<String> obterTodosTitulosArea(String palavra) {
        String query = "SELECT q.areaAvaliacao FROM qualis q WHERE q.areaAvaliacao like '" + palavra + "%'";
        Query q = getEntityManager().createNativeQuery(query);

        List<String> resultado = (List<String>) q.getResultList();

        return resultado;
    }

    public static void main(String[] args) {
        System.out.println(new QualisDAO().obterTodosTitulos("AA").get(0));
    }
}
