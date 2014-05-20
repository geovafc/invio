/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package invio.dao;

import invio.entidade.Edital;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author toshiaki
 */
public class EditalDAO extends GenericDAO<Edital>{
    
    public List<Edital> obterTodosAbertos(Date dataAtual){
        List<Edital> resposta=null;
        String consulta ="select e.numero,e.ano,e.resumo,e.datainicial,e.datafinal"+
                " from Edital e where e.datafinal >= :%";
        Query q = getEntityManager().createQuery(consulta).setParameter("%", dataAtual);
        resposta= (List<Edital>) q.getResultList();
        return resposta;
    }
    public List<Edital> obterTodosFechados(Date dataAtual){
        List<Edital> resposta=null;
        String consulta ="select e.numero,e.ano,e.resumo,e.datainicial,e.datafinal"+
                " from Edital e where e.datafinal < :%";
        Query q = getEntityManager().createQuery(consulta).setParameter("%", dataAtual);
        resposta= (List<Edital>) q.getResultList();
        return resposta;
    }
}
