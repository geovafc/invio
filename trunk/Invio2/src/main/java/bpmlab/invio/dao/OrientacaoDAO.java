/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bpmlab.invio.dao;

import bpmlab.invio.entidade.Curriculo;
import bpmlab.invio.entidade.Orientacao;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author fabio
 */
public class OrientacaoDAO extends GenericDAO<Orientacao> {

    private GenericDAO<Orientacao> genericDAO = new GenericDAO<Orientacao>();

    public List<Orientacao> obterOrientacoesAtuais(Curriculo curriculo, Date dataLimite) {
        String consulta = "SELECT o FROM Orientacao o "
                + "WHERE o.curriculo = :curriculo AND o.pFinal >= :dataLimite ORDER BY o.pFinal DESC";
        Query query = getEntityManager().createQuery(consulta);
        List<Orientacao> orientacoes = query.setParameter("curriculo", curriculo)
                .setParameter("dataLimite", dataLimite).
                getResultList();
        return orientacoes;
    }

    public List<Orientacao> obterOrientacoesPassadas(Curriculo curriculo, Date dataLimite) {
        String consulta = "SELECT o FROM Orientacao o "
                + "WHERE o.curriculo = :curriculo AND :dataLimite > o.pFinal ORDER BY o.pFinal DESC";
        Query query = getEntityManager().createQuery(consulta);
        List<Orientacao> orientacoes = query.
                setParameter("curriculo", curriculo)
                .setParameter("dataLimite", dataLimite)
                .getResultList();
        return orientacoes;
    }

    public List<Orientacao> obterOrientacoes(Curriculo curriculo,
            boolean avaliado) {
        String consulta = "select o from Orientacao o "
                + "where o.curriculo = :curriculo and "
                + (avaliado ? "not(o.avaliacao is null) " : "o.avaliacao is null ")
                + "ORDER BY o.pFinal desc";
        Query query = getEntityManager().createQuery(consulta);
        query.setParameter("curriculo", curriculo);
        List<Orientacao> orientacoes = query.getResultList();
        return orientacoes;
    }

    public List<Orientacao> obterOrientacoes2(Curriculo curriculo,
            boolean avaliado) {
        String consulta2 = "select o from Orientacao o where o.curriculo=" + curriculo.getId() + " ";

        if (avaliado == true) {
            consulta2 += "and NOT (o.avaliacao IS NULL)";
        } else {
            consulta2 += "and o.avaliacao IS NULL";
        }
        List<Orientacao> orientacoes = genericDAO.getEntityManager().createQuery(consulta2).getResultList();
        return orientacoes;
    }

}
