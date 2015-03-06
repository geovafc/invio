/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bpmlab.invio.dao;

import bpmlab.invio.entidade.Edital;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author toshiaki
 */
public class EditalDAO extends GenericDAO<Edital>{
    
    public List<Edital> obterTodosAbertos(){
        List<Edital> resposta=null;
        String consulta ="select e from Edital e where e.datafinal >= :dataAtual";
        Date dataAtual = new Date();
        Query q = getEntityManager().createQuery(consulta).setParameter("dataAtual", dataAtual);
        resposta= (List<Edital>) q.getResultList();
        return resposta;
    }
    public List<Edital> obterTodosFechados(){
        List<Edital> resposta=null;
        String consulta ="select e from Edital e where e.datafinal < :dataAtual";
        Date dataAtual = new Date();
        Query q = getEntityManager().createQuery(consulta).setParameter("dataAtual", dataAtual);
        resposta= (List<Edital>) q.getResultList();
        return resposta;
    }
}
