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
    public List<Livro> obterLivros(Curriculo curriculo,
            boolean avaliado) {
        String consulta = "select o from Livro o "
                + "where o.curriculo = :curriculo and "
                + (avaliado?"not(o.avaliacao is null) ":"o.avaliacao is null ")
                + "ORDER BY o.ano desc";
        Query query = getEntityManager().createQuery(consulta);
        query.setParameter("curriculo", curriculo);
        List<Livro> livros = query.getResultList();
        return livros;
    }
}
