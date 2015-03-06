/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bpmlab.invio.util;

import bpmlab.invio.entidade.Curriculo;
import bpmlab.invio.entidade.Livro;
import bpmlab.invio.entidade.Orientacao;
import bpmlab.invio.entidade.Periodico;

/**
 *
 * @author fabio
 */
public class PontuacaoUtil {
    
    
    public static int obterPontuacao(Curriculo curriculo, boolean titulacao) {
        //TODO Calcular a partir de uma especificação em configuração
        if (titulacao) {
            //TODO apena pontuar a titulação
        } else {
            //TODO pontuar todo o curriculo
        }
        return -1;
    }
    
    public static int obterPontuacao(Periodico periodico) {
        //TODO Calcular a partir de uma especificação em configuração
        return -1;
    }
    
    public static int obterPontuacao(Orientacao orientacao) {
        //TODO Calcular a partir de uma especificação em configuração
        return -1;
    }
    
    public static int obterPontuacao(Livro livro) {
        //TODO Calcular a partir de uma especificação em configuração
        return -1;
    }
}
