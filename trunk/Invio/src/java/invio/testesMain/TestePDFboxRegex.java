/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.testesMain;

import invio.entidade.Qualis;
import invio.rn.PDFBoxRN;
import invio.rn.PDFRegexRN;
import invio.rn.QualisRN;
import java.util.ArrayList;
import javax.swing.JFileChooser;

/**
 *
 * @author Junior
 */
public class TestePDFboxRegex {

    public static void main(String[] args) {


        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);

        PDFBoxRN pdfBoxRn = new PDFBoxRN();
        PDFRegexRN pdfRegexRn = new PDFRegexRN();

        QualisRN qualisRN = new QualisRN();
        Qualis qualis = new Qualis();

        String[] splitConteudoPDF = pdfBoxRn.getConteudoPDF(fileChooser.getSelectedFile().getPath()).split("\n");
        ArrayList<Qualis> osQualis = new ArrayList<Qualis>();
        for (int i = 0; i < splitConteudoPDF.length; i++) {
            if (pdfRegexRn.procurarPadroesNaLinha(qualis, splitConteudoPDF[i])) {
                osQualis.add(qualis);
                qualis = new Qualis();
            } else {
                continue;
            }
        }
        System.out.println(qualisRN.salvar(osQualis));
    }
}
