/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.testesMain;

import invio.entidade.Qualis;
import invio.entidade.QualisPK;
import invio.rn.pdf.PDFBoxRN;
import invio.rn.pdf.PDFRegexRN;
import invio.rn.pdf.QualisRN;
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
        ArrayList<Qualis> osQualis = new ArrayList<Qualis>();
        
        QualisPK qualisPK = new QualisPK();

        String[] splitConteudoPDF = pdfBoxRn.getConteudoPDF(fileChooser.getSelectedFile().getPath()).split("\n");
        
        for (int i = 0; i < splitConteudoPDF.length; i++) {
            if (pdfRegexRn.procurarPadroesNaLinha(qualis, qualisPK, splitConteudoPDF[i])) {
                qualis.setQualisPK(qualisPK);
                
                osQualis.add(qualis);
                
                qualis = new Qualis();
                qualisPK = new QualisPK();
                qualisRN = new QualisRN();
            } else {
                continue;
            }
        }
        System.out.println(qualisRN.salvar(osQualis));
    }
}
