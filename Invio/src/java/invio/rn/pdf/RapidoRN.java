/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.rn.pdf;

import invio.entidade.Qualis;
import invio.entidade.QualisPK;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Junior
 */
public class RapidoRN {

    private List<Qualis> osQualis;

//    public RapidoRN(String[] linhas) {
//        osQualis = criar(linhas);
//    }
    public List<Qualis> criar(String[] linhas) {
        ArrayList<Qualis> resposta = new ArrayList<Qualis>();

        boolean BEGIN = false;
        StringBuilder registro = new StringBuilder();

        
        Pattern patternIssn = Pattern.compile("(\\d\\d\\d\\d)-(\\d\\d\\d\\w\\s)");
        Pattern patternEstratoAB = Pattern.compile("\\s[A-B][1-5]\\s");
        Pattern patternEstratoC = Pattern.compile("\\sC\\s");
        Pattern patternStatus = Pattern.compile("\\s(Em )?Atualiza[çd][ão][o]?");
        Pattern patternCessou = Pattern.compile("\\(Cessou\\s");

        Matcher issnMatcher;
        Matcher estratoABMatcher;
        Matcher estratoCMatcher;
        Matcher statusMatcher;

        Matcher issnMatcherResgistro;
        Matcher estratoABMatcherResgistro;
        Matcher estratoCMatcherResgistro;
        Matcher statusMatcherResgistro;

        int fimIssn = 0;
        int inicioEstrato = 0;
        int fimEstrato = 0;
        int inicioStatus;

        boolean issnExiste;
        boolean estratoABExiste;
        boolean estratoCExiste;
        boolean statusExiste;

        String issn = null;
        String titulo = null;
        String estrato = null;
        String area = null;
        String status = null;

        Qualis qualis;
        QualisPK qualisPK;

        for (String linha : linhas) {
            if (BEGIN == false) {

                issnMatcher = patternIssn.matcher(linha);
                estratoABMatcher = patternEstratoAB.matcher(linha);
                estratoCMatcher = patternEstratoC.matcher(linha);

                //BEGIN = issnMatcher.find() || estratoABMatcher.find() || estratoCMatcher.find();
                if (issnMatcher.find()) {
                    System.out.println("ISSN: "+issnMatcher.group());
                    BEGIN = true;
                }
                else if (estratoABMatcher.find() || estratoCMatcher.find()) {
                    BEGIN = true;
                }


            }

            if (BEGIN == true) {

                statusMatcher = patternStatus.matcher(linha);
                statusExiste = statusMatcher.find();
                registro.append(linha);
                System.out.println("passei aqui, registro:" + registro);

                if (statusExiste) {

                    

                    issnMatcherResgistro = patternIssn.matcher(registro);
                    issnMatcherResgistro.find();
                    estratoABMatcherResgistro = patternEstratoAB.matcher(registro);
                    estratoABMatcherResgistro.find();
                    estratoCMatcherResgistro = patternEstratoC.matcher(registro);
                    estratoCMatcherResgistro.find();
                    statusMatcherResgistro = patternIssn.matcher(registro);
                    statusMatcherResgistro.find();
                    issnExiste = issnMatcherResgistro.find();
                    estratoABExiste = estratoABMatcherResgistro.find();
                    estratoCExiste = estratoCMatcherResgistro.find();
                    
                    status = statusMatcherResgistro.group();

                    if (issnExiste == true) {
                        
                        issn = issnMatcherResgistro.group();

                        if (estratoABExiste) {
                            estrato = estratoABMatcherResgistro.group();

                            titulo = registro.substring(issnMatcherResgistro.end(), estratoABMatcherResgistro.start());
                            area = linha.substring(estratoABMatcherResgistro.end(), statusMatcherResgistro.start());

                        } else if (estratoCExiste) {

                            estrato = estratoCMatcherResgistro.group();

                            titulo = registro.substring(issnMatcherResgistro.end(), estratoCMatcherResgistro.start());
                            area = linha.substring(estratoCMatcherResgistro.end(), statusMatcherResgistro.start());
                        }
                    } else if (estratoABExiste == true || estratoCExiste == true) {

                        if (estratoABExiste) {
                            titulo = registro.substring(0, estratoABMatcherResgistro.start());
                            area = linha.substring(estratoABMatcherResgistro.end(), statusMatcherResgistro.start());
                        } else if (estratoCExiste) {
                            titulo = registro.substring(0, estratoCMatcherResgistro.start());
                            area = linha.substring(estratoCMatcherResgistro.end(), statusMatcherResgistro.start());
                        }

                    }

                    //TODO Criar um objeto Qualis
                    BEGIN = false;
                    qualis = new Qualis(titulo, area);
                    qualisPK = new QualisPK(titulo, area);
                    qualis.setQualisPK(qualisPK);
                    qualis.setEstrato(estrato);
                    qualis.setIssn(issn);
                    qualis.setStatus(status);
                    resposta.add(qualis);
                    System.out.println("Registro: " + registro);
                    registro = new StringBuilder();

                }
            }

        }

        return resposta;
    }
}
