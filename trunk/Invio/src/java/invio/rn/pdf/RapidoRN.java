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
        Pattern patternIssnErrado = Pattern.compile("ISSN");
        Pattern patternEstratoAB = Pattern.compile("\\s[A-B][1-5]\\s");
        Pattern patternEstratoC = Pattern.compile("\\sC\\s");
        Pattern patternStatus = Pattern.compile("\\s(Em )?Atualiza[çd][ão][o]?");
        Pattern patternCessou = Pattern.compile("Cessou");

        Matcher issnMatcher;
        Matcher estratoABMatcher;
        Matcher estratoCMatcher;
        Matcher statusMatcher;

        Matcher issnMatcherResgistro;
        Matcher issnErradoMatcherResgistro;
        Matcher estratoABMatcherResgistro;
        Matcher estratoCMatcherResgistro;
        Matcher statusMatcherResgistro;
        Matcher cessouMatcherResgistro;

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
            if (BEGIN == false) { //Sempre iniciará de primeira Begin é falso.

                //Pesquisando os patterns nas LINHAS e adicionando a variavel.
                issnMatcher = patternIssn.matcher(linha);
                issnMatcher.find();
                estratoABMatcher = patternEstratoAB.matcher(linha);
                estratoABMatcher.find();
                estratoCMatcher = patternEstratoC.matcher(linha);
                estratoCMatcher.find();

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

                //A partir daqui já achei ISSN ou estrato e pequiso o padrão final (Status) da minha linha.
                statusMatcher = patternStatus.matcher(linha);
                //statusExiste = statusMatcher.find();
                registro.append(linha);
                System.out.println("passei aqui, registro:" + registro); //Apenas um teste

                if (statusMatcher.find()) {

                    
                    //Addd tudo ao registro.
                    issnMatcherResgistro = patternIssn.matcher(registro);
                    issnMatcherResgistro.find();
                    issnErradoMatcherResgistro = patternIssnErrado.matcher(registro);
                    issnErradoMatcherResgistro.find();
                    estratoABMatcherResgistro = patternEstratoAB.matcher(registro);
                    estratoABMatcherResgistro.find();
                    estratoCMatcherResgistro = patternEstratoC.matcher(registro);
                    estratoCMatcherResgistro.find();
                    statusMatcherResgistro = patternIssn.matcher(registro);
                    statusMatcherResgistro.find();
                    cessouMatcherResgistro = patternCessou.matcher(registro);
                    cessouMatcherResgistro.find();
                    //issnExiste = issnMatcherResgistro.find();
                    //estratoABExiste = estratoABMatcherResgistro.find();
                    //estratoCExiste = estratoCMatcherResgistro.find();
                    
                    status = statusMatcherResgistro.group();

                    if (!cessouMatcherResgistro.find() || !issnErradoMatcherResgistro.find()) {
                        
                    if (issnMatcherResgistro.find()) {
                        
                        issn = issnMatcherResgistro.group();

                        if (estratoABMatcherResgistro.find()) {
                            estrato = estratoABMatcherResgistro.group();

                            titulo = registro.substring(issnMatcherResgistro.end(), estratoABMatcherResgistro.start());
                            area = linha.substring(estratoABMatcherResgistro.end(), statusMatcherResgistro.start());

                        } else if (estratoCMatcherResgistro.find()) {

                            estrato = estratoCMatcherResgistro.group();

                            titulo = registro.substring(issnMatcherResgistro.end(), estratoCMatcherResgistro.start());
                            area = linha.substring(estratoCMatcherResgistro.end(), statusMatcherResgistro.start());
                        }
                    } else if (estratoABMatcherResgistro.find() == true || estratoCMatcherResgistro.find() == true) {

                        if (estratoABMatcherResgistro.find()) {
                            titulo = registro.substring(0, estratoABMatcherResgistro.start());
                            area = linha.substring(estratoABMatcherResgistro.end(), statusMatcherResgistro.start());
                        } else if (estratoCMatcherResgistro.find()) {
                            titulo = registro.substring(0, estratoCMatcherResgistro.start());
                            area = linha.substring(estratoCMatcherResgistro.end(), statusMatcherResgistro.start());
                        }

                    }
                       
                    } else {
                        System.out.println("Registro ignorado pois Cessou estava presente, registro: " +registro);
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

        return resposta;
        }

    }
