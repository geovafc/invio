package invio.dao;

import invio.entidade.Area;
import invio.entidade.Curriculo;
import invio.entidade.Login;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Dir de Armas Marinha
 */
public class CurriculoDAO extends GenericDAO<Area> {

    public List<Curriculo> findCurriculoByUsuario(Login loginLogado) {

        List<Curriculo> curriculos;

        String consulta = "select o.curriculo from Login o where o.id=" + loginLogado.getId();
        
        curriculos = getEntityManager().createQuery(consulta).getResultList();
        
        if (curriculos == null || curriculos.isEmpty()) {
            return new ArrayList<Curriculo>();
        } else {
            return curriculos;
        }

    }
}