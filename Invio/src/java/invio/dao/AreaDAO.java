package invio.dao;

import invio.entidade.Area;
import invio.entidade.Instituicao;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Dir de Armas Marinha
 */
public class AreaDAO extends GenericDAO<Area> {

    public List<Area> obterAreaPorCriterio(String digitacao) {
        String consulta = "select o from Area o where o.nome like'%" + digitacao + "%'";
        List<Area> areas = getEntityManager().createQuery(consulta).getResultList();

        return areas;
    }
    
    public List<Area> obterAreas(Instituicao instituicao) {
        String consulta = "select distinct p.area from Programa p "
                + "where p.instituicao = :instituicao";
        Query query = getEntityManager().createQuery(consulta);
        query.setParameter("instituicao", instituicao);
        List<Area> areas = query.getResultList();

        return areas;
    }
    
    public static void main(String[] args) {
        AreaDAO dao  = new AreaDAO();
        List<Area> areas = dao.obterAreas(new Instituicao(1));
        for (Area area : areas) {
            System.out.println(area.getNome());
        }
    }
}