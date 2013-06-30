/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.dao;

import invio.entidade.Curriculo;
import invio.entidade.Orientacao;
import invio.rn.CurriculoRN;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author fabio
 */
public class OrientacaoDAO extends GenericDAO<Orientacao> {
    
    GenericDAO<Orientacao> genericDAO = new GenericDAO<Orientacao>();
    
    public List<Orientacao> obterOrientacoes(Curriculo curriculo) {
        String consulta = "select o from Orientacao o "
                + "where o.curriculo = :curriculo ORDER BY o.pFinal desc";
        Query query = getEntityManager().createQuery(consulta);
        query.setParameter("curriculo", curriculo);
        List<Orientacao> orientacoes = query.getResultList();
        return orientacoes;
    }
    public List<Orientacao> obterOrientacoes(Curriculo curriculo, 
            boolean avaliado) {
        String consulta = "select o from Orientacao o "
                + "where o.curriculo = :curriculo and "
                + (avaliado?"not(o.avaliacao is null) ":"o.avaliacao is null ")
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
    
    public static void main(String[] args) {
        Curriculo c = new CurriculoRN().obter(1);
        
        List<Orientacao> lista = new OrientacaoDAO().obterOrientacoes(c, false);
        
        if (lista.isEmpty()){
            System.out.println("Nao carreggou.");
        } else {
            System.out.println(lista);
        }
    }
    
}
