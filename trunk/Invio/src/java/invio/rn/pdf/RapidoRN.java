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

    public RapidoRN(String[] linhas) {
        osQualis = criar(linhas);
    }

    private List<Qualis> criar(String[] linhas) {
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

        String issn = null;
        String titulo = null;
        String estrato = null;
        String area = null;
        String status = null;

        Qualis qualis;
        QualisPK qualisPK;

        for (String linha : linhas) {
            if (!BEGIN) {
                issnMatcher = patternIssn.matcher(linha);
                estratoABMatcher = patternEstratoAB.matcher(linha);
                estratoCMatcher = patternEstratoC.matcher(linha);
                BEGIN = issnMatcher.find() || estratoABMatcher.find() || estratoCMatcher.find();
            }
            if (BEGIN) {
                statusMatcher = patternStatus.matcher(linha);
                registro.append(linha); //
                if (statusMatcher.find()) {
                    BEGIN = false;
                    //TODO Criar um objeto Qualis
                    qualis = new Qualis(titulo, area);
                    qualisPK = new QualisPK(titulo, area);
                    qualis.setQualisPK(qualisPK);
                    qualis.setEstrato(estrato);
                    qualis.setIssn(issn);
                    qualis.setStatus(status);
                    resposta.add(qualis);
                }
            }
        }

        return resposta;
    }
}
