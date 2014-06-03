/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.rn;

import invio.dao.GenericDAO;
import invio.entidade.Curriculo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author toshiaki & Mikael
 */
public class AvaliacaoRN {

    private final GenericDAO<Curriculo> dao = new GenericDAO<Curriculo>();

    public List<Curriculo> autoCompleteCurriculo(String busca) {
        List<Curriculo> curriculos = dao.obterTodos(Curriculo.class);
        List<Curriculo> filtro = new ArrayList<Curriculo>();
        if (busca != null) {
            for (Curriculo curriculo : curriculos) {
                if (curriculo.getNome().toUpperCase().startsWith(busca.toUpperCase())) {
                    filtro.add(curriculo);
                } else if (curriculo.getEmail().toUpperCase().startsWith(busca.toUpperCase())) {
                    filtro.add(curriculo);
                }
            }
        }
        return filtro;
    }
}
