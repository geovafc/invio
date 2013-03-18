/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.rn;

import invio.entidade.Qualis;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Junior
 */
public class PDFRegexRN {

    Pattern patternIssn = Pattern.compile("(\\d\\d\\d\\d)-(\\d\\d\\d\\w\\s)");
    Pattern patternEstrato = Pattern.compile("\\s[A-C][1-5]?\\s");
    Pattern patternStatus = Pattern.compile("\\s(Em )?Atualiza[çd][ão][o]?");
    int fimTitulo = 0;
    int fimIssn = 0;
    int fimEstrato = 0;
    int inicioStatus = 0;
    boolean padraoIssnExiste = false;

    public int getFimTitulo() {
        return fimTitulo;
    }

    public void setFimTitulo(int fimTitulo) {
        this.fimTitulo = fimTitulo;
    }

    public int getFimIssn() {
        return fimIssn;
    }

    public void setFimIssn(int fimIssn) {
        this.fimIssn = fimIssn;
    }

    public int getFimEstrato() {
        return fimEstrato;
    }

    public void setFimEstrato(int fimEstrato) {
        this.fimEstrato = fimEstrato;
    }

    public int getInicioStatus() {
        return inicioStatus;
    }

    public void setInicioStatus(int inicioStatus) {
        this.inicioStatus = inicioStatus;
    }

    public boolean isPadraoIssnExiste() {
        return padraoIssnExiste;
    }

    public void setPadraoIssnExiste(boolean padraoIssnExiste) {
        this.padraoIssnExiste = padraoIssnExiste;
    }

    public boolean procurarPadroesNaLinha(Qualis qualis, String linha) {

        Matcher matcherIssn = patternIssn.matcher(linha);
        Matcher matcherEstrato = patternEstrato.matcher(linha);
        Matcher matcherStatus = patternStatus.matcher(linha);

        if (matcherIssn.find()) {

            qualis.setIssn(matcherIssn.group());
            setFimIssn(matcherIssn.end());
            setPadraoIssnExiste(true);

            if (matcherStatus.find()) {
                qualis.setStatus(matcherStatus.group());
                setInicioStatus(matcherStatus.start());
            } else {
                System.out.println("Padrão Status Não Encontrado");
            }

            if (matcherEstrato.find()) {
                qualis.setEstrato(matcherEstrato.group());
                setFimTitulo(matcherEstrato.start());
                setFimEstrato(matcherEstrato.end());
            } else {
                System.out.println("Padrão Estrato Não Encontrado");

            }

            try {

                qualis.setTitulo(linha.substring(getFimIssn(), getFimTitulo()));
                qualis.setAreaAvaliacao(linha.substring(getFimEstrato(), getInicioStatus()));

            } catch (java.lang.StringIndexOutOfBoundsException e) {
                System.out.println("Ocorreu um erro ao tentar obter por substring \nERRO: " + e);
            }

        } else {
            System.out.println("Padrão ISSN Nao Encontrado, devido isto a busca nesta linha foi terminada");
        }


        System.out.println("Linha: " + linha);
        return padraoIssnExiste;
    }
}
