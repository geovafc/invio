/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.rn;

import invio.dao.GenericDAO;
import invio.entidade.Curriculo;
import invio.entidade.Livro;
import invio.entidade.Periodico;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Dir de Armas Marinha
 */
public class CurriculoRN {

    GenericDAO<Curriculo> dao = new GenericDAO<Curriculo>();

    public boolean salvar(Curriculo c) {
        boolean salvou = false;

        if (dao.iniciarTransacao()) {
            if (c.getId() == null) {
                if (dao.criar(c)) {
                    salvou = true;
                }
            } else {
                if (dao.alterar(c)) {
                    salvou = true;
                }
            }
            dao.concluirTransacao();
        }
        return salvou;
    }

    public boolean remover(Curriculo c) {
        return dao.excluir(c);
    }

    public Curriculo obter(Integer id) {
        return dao.obter(Curriculo.class, id);
    }

    public List<Curriculo> obterTodos() {
        return dao.obterTodos(Curriculo.class);
    }

   public List<Curriculo> obterTodosDesc() {
        List<Curriculo> obterTodosDesc = new ArrayList<Curriculo>();

        List<CurriculoPts> mapa = new ArrayList<CurriculoPts>();

        for (Curriculo curriculo : dao.obterTodos(Curriculo.class)) {

            int totalP = 0;
            int totalL = 0;

            for (Periodico periodico : curriculo.getPeriodicoList()) {
                totalP += periodico.getEstrato();
            }

            for (Livro livro : curriculo.getLivroList()) {
                totalL += livro.getEstrato();
            }
            CurriculoPts cpts = new CurriculoPts(curriculo, totalP+totalL);
            mapa.add(cpts);

        }
        
        Collections.sort(mapa);
        
        
        for (CurriculoPts curriculoPts : mapa) {
            obterTodosDesc.add(curriculoPts.getC());
        }
        
        
        return obterTodosDesc;
    }
    
    
    
}


class CurriculoPts implements Comparable<CurriculoPts> {
    private Curriculo c;
    private Integer pts;

    public CurriculoPts(Curriculo c, Integer pts) {
        this.c = c;
        this.pts = pts;
    }
    public Curriculo getC() {
        return c;
    }

    public void setC(Curriculo c) {
        this.c = c;
    }

    public Integer getPts() {
        return pts;
    }

    public void setPts(Integer pts) {
        this.pts = pts;
    }

    @Override
    public int compareTo(CurriculoPts o) {
        if (this.pts < o.getPts()) {
            return -1;
        }
        if (this.pts > o.getPts()) {
            return 1;
        }
        return 0;
    }

  
}