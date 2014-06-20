/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.rn;

import invio.entidade.Curriculo;
import invio.entidade.Livro;
import invio.entidade.Orientacao;
import invio.entidade.Periodico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Toshiaki & Mikael
 */
public class AvaliacaoRN implements Serializable {

    private final CurriculoRN rnCurriculo = new CurriculoRN();

    public List<Curriculo> autoCompleteCurriculo(String busca) {
        List<Curriculo> curriculos = rnCurriculo.obterTodos();
        List<Curriculo> filtro = new ArrayList<Curriculo>();
        if (busca != null) {
            for (Curriculo curriculo : curriculos) {
                if (curriculo.getNome().toUpperCase().startsWith(busca.toUpperCase())) {
                    filtro.add(curriculo);
                }
            }
        }
        return filtro;
    }

    public boolean possueArquivo(String arquivo) {
        return !(arquivo == null || "".equals(arquivo));
    }

    public boolean confirmar(Curriculo curriculo, Object object) {
        String avaliacao = "Avaliado";
        if (object instanceof Livro) {
            ((Livro)object).setAvaliacao(avaliacao);
        } else if (object instanceof Orientacao) {
            ((Orientacao)object).setAvaliacao(avaliacao);
        } else if (object instanceof Periodico) {
            ((Periodico)object).setAvaliacao(avaliacao);
        } else {
            return false; //Raramente ocorrerá
        }
        return rnCurriculo.salvar(curriculo);
    }
    
    public boolean corrigir(Curriculo curriculo, Object object, Integer estrato) {
        String avaliacao = "Avaliado com diferenças";
        if (object instanceof Livro) {
            ((Livro)object).setAvaliacao(avaliacao);
            ((Livro)object).setEstrato(estrato);
        } else if (object instanceof Orientacao) {
            ((Orientacao)object).setAvaliacao(avaliacao);
            ((Orientacao)object).setEstrato(estrato);
        } else if (object instanceof Periodico) {
            ((Periodico)object).setAvaliacao(avaliacao);
            ((Periodico)object).setEstrato(estrato);
        } else {
            return false; //Raramente ocorrerá
        }
        return rnCurriculo.salvar(curriculo);
    }
    
    public boolean negar(Curriculo curriculo, Object object) {
        String avaliacao = "Negado pelo Comitê";
        int negado = 0;
        if (object instanceof Livro) {
            ((Livro)object).setAvaliacao(avaliacao);
            ((Livro)object).setEstrato(negado);
        } else if (object instanceof Orientacao) {
            ((Orientacao)object).setAvaliacao(avaliacao);
            ((Orientacao)object).setEstrato(negado);
        } else if (object instanceof Periodico) {
            ((Periodico)object).setAvaliacao(avaliacao);
            ((Periodico)object).setEstrato(negado);
        } else {
            return false; //Raramente ocorrerá
        }
        return rnCurriculo.salvar(curriculo);
    }

}
