package invio.dao;

import invio.entidade.Curriculo;
import invio.entidade.Livro;
import java.util.List;
import javax.persistence.Query;

public class LivroDAO extends GenericDAO<Livro> {

    GenericDAO<Livro> genericDAO = new GenericDAO<Livro>();

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
                + (avaliado ? "not(o.avaliacao is null) " : "o.avaliacao is null ")
                + "ORDER BY o.ano desc";
        Query query = getEntityManager().createQuery(consulta);
        query.setParameter("curriculo", curriculo);
        List<Livro> livros = query.getResultList();
        return livros;
    }

    public List<Livro> obterLivros2(Curriculo curriculo,
            boolean avaliado) {
        String consulta2 = "select o from Livro o where o.curriculo=" + curriculo.getId() + " ";

        if (avaliado == true) {
            consulta2 += "and NOT (o.avaliacao IS NULL)";
        } else {
            consulta2 += "and o.avaliacao IS NULL";
        }
        List<Livro> livros = genericDAO.getEntityManager().createQuery(consulta2).getResultList();
        return livros;
    }
}
