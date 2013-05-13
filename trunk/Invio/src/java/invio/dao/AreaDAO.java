package invio.dao;

import invio.entidade.Area;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Dir de Armas Marinha
 */
public class AreaDAO extends GenericDAO<Area> {

   public List<Area> obterAreaPorCriterio(String digitacao){
       String consulta ="select o from Area o where o.nome like'%"+digitacao+"%'";
       List<Area> areas = getEntityManager().createQuery(consulta).getResultList();
       
       return areas;
   }
}