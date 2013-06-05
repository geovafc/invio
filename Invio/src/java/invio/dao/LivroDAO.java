package invio.dao;

import invio.entidade.Curriculo;
import invio.entidade.Livro;
import java.util.List;
import javax.persistence.Query;

public class LivroDAO extends GenericDAO<Livro> {

    public List<Livro> obterLivros(Curriculo curriculo) {
        String consulta = "select o from Livro o "
                + "where o.curriculo = :curriculo ORDER BY o.ano desc";
        Query query = getEntityManager().createQuery(consulta);
        query.setParameter("curriculo", curriculo);
        List<Livro> livros = query.getResultList();
        return livros;
    }
}
