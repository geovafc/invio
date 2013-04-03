/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.rn.pdf;

import invio.entidade.Qualis;
import invio.entidade.QualisPK;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Junior
 */
public class PDFRegexRN {

    //Aqui definimos os padrões que podem ser encontrados dentro do pdf
    Pattern patternIssn = Pattern.compile("(\\d\\d\\d\\d)-(\\d\\d\\d\\w\\s)");
    Pattern patternEstratoAB = Pattern.compile("\\s[A-B][1-5]\\s");
    Pattern patternEstratoC = Pattern.compile("\\sC\\s");
    Pattern patternStatus = Pattern.compile("\\s(Em )?Atualiza[çd][ão][o]?");
    Pattern patternCessou = Pattern.compile("\\(Cessou\\s");
    
    int fimTitulo = 0;
    int fimIssn = 0;
    int fimEstrato = 0;
    int inicioStatus = 0;
    
    boolean padraoIssnExiste = false;
    boolean padraoCessou = false;
    boolean padraoEstratoExixte = false;
    boolean padroesIssnStatusExistem = false;
    boolean encontradoNaPrimeiraLinha = false;

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

    public boolean isPadraoCessou() {
        return padraoCessou;
    }

    public void setPadraoCessou(boolean padraoCessou) {
        this.padraoCessou = padraoCessou;
    }

    public boolean isEncontradoNaPrimeiraLinha() {
        return encontradoNaPrimeiraLinha;
    }

    public void setEncontradoNaPrimeiraLinha(boolean encontradoNaPrimeiraLinha) {
        this.encontradoNaPrimeiraLinha = encontradoNaPrimeiraLinha;
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
        return padroesIssnStatusExistem;
    }

    public void setPadroesIssnStatusExistem(boolean padroesIssnStatusExistem) {
        this.padroesIssnStatusExistem = padroesIssnStatusExistem;
    }

    public boolean procurarPadroesNaLinha(Qualis qualis, QualisPK qualisPK, String linha) {

        //Aqui procuramos na linha os padrões que deifinimos anteriormente.
        Matcher matcherIssn = patternIssn.matcher(linha);
        Matcher matcherCessou = patternCessou.matcher(linha);
        Matcher matcherEstratoAB = patternEstratoAB.matcher(linha);
        Matcher matcherEstratoC = patternEstratoC.matcher(linha);
        Matcher matcherStatus = patternStatus.matcher(linha);

        boolean issnExiste = matcherIssn.find();
        boolean estratoABExiste = matcherEstratoAB.find();
        boolean estratoCExiste = matcherEstratoC.find();
        boolean statusExiste = matcherStatus.find();
        boolean cessouExiste = matcherCessou.find();
        
        System.out.println("ISSN :" + issnExiste);
        System.out.println("CESSOU :" + cessouExiste);
        System.out.println("ESTRATO_AB :" + estratoABExiste);
        System.out.println("ESTRATO_C :" + estratoCExiste);
        System.out.println("STATUS :" + statusExiste);

            if (issnExiste && statusExiste) {
                System.out.println("ISSN encontrado");
                if (statusExiste) {
                    System.out.println("Status encontrado");

                    setPadroesIssnStatusExistem(true);
                    setEncontradoNaPrimeiraLinha(true);

                    qualis.setIssn(matcherIssn.group());
                    setFimIssn(matcherIssn.end());

                    qualis.setStatus(matcherStatus.group());
                    setInicioStatus(matcherStatus.start());


                    if (estratoABExiste) {
                        qualis.setEstrato(matcherEstratoAB.group());
                        setFimTitulo(matcherEstratoAB.start());
                        setFimEstrato(matcherEstratoAB.end());
                    } else if (estratoCExiste) {

                        qualis.setEstrato(matcherEstratoC.group());
                        setFimTitulo(matcherEstratoC.start());
                        setFimEstrato(matcherEstratoC.end());
                    } else {
                        System.out.println("Padrão Estrato Não Encontrado na primeira linha");
                    }

                    try {
                        String tituloNoPDF = linha.substring(getFimIssn(), getFimTitulo()).trim();

                        qualisPK.setTitulo(tituloNoPDF);
                        qualisPK.setAreaAvaliacao(linha.substring(getFimEstrato(), getInicioStatus()).trim());
                        System.out.println("Qualis PK setados:"+ "- Area Aval.: "
                                +qualisPK.getAreaAvaliacao()
                                +" , - Titulo: "+qualisPK.getTitulo());
                    } catch (java.lang.StringIndexOutOfBoundsException e) {
                        System.out.println("Ocorreu um erro ao tentar obter por substring \nERRO: " + e);
                    }

                } else {
                    System.out.println("Status não encontrado na primeira linha");
                }

            }else{
                setEncontradoNaPrimeiraLinha(false);
                System.out.println("ISSN  e Status não encontrado");
            } 
            /*
            if (matcherIssn.find()==false && matcherEstratoAB.find() || matcherEstratoC.find()) {

                if (matcherStatus.find()) {


                    setEncontradoNaPrimeiraLinha(true);
                    qualis.setIssn(null);

                    qualis.setStatus(matcherStatus.group());
                    setInicioStatus(matcherStatus.start());
                    

                    if (matcherEstratoAB.find()) {
                        qualis.setEstrato(matcherEstratoAB.group());
                        setFimTitulo(matcherEstratoAB.start());
                        setFimEstrato(matcherEstratoAB.end());
                    } else if (matcherEstratoC.find()) {

                        qualis.setEstrato(matcherEstratoC.group());
                        setFimTitulo(matcherEstratoC.start());
                        setFimEstrato(matcherEstratoC.end());
                    } else {
                        System.out.println("Padrões Estrato Não Encontrados");
                    }

                    try {

                        qualisPK.setTitulo(linha.substring(1, getFimTitulo()));
                        qualisPK.setAreaAvaliacao(linha.substring(getFimEstrato(), getInicioStatus()));

                    } catch (java.lang.StringIndexOutOfBoundsException e) {
                        System.out.println("Ocorreu um erro ao tentar obter por substring \nERRO: " + e);
                    }

                }else {
                    System.out.println("Status não encontrado na primeira linha");
                }

            } else {
                System.out.println("Sem os Padrões Issn e Estrato na primeira linha, o processo nesta linha foi cancelado");
            }

* */

        System.out.println("Linha: " + linha);

        return encontradoNaPrimeiraLinha;
    }
}
