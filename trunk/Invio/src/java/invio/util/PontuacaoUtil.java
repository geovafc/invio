/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.util;

import invio.entidade.Curriculo;
import invio.entidade.Livro;
import invio.entidade.Orientacao;
import invio.entidade.Periodico;

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
