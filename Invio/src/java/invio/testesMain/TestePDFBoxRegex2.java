package invio.testesMain;

import invio.rn.pdf.PDFBoxRN;
import invio.rn.pdf.PDFRegexRN;
import invio.rn.pdf.QualisRN;
import invio.rn.pdf.RapidoRN;
import javax.swing.JFileChooser;

public class TestePDFBoxRegex2 {

    public static void main(String[] args) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);

        PDFBoxRN pdfBoxRn = new PDFBoxRN();
        PDFRegexRN pdfRegexRn = new PDFRegexRN();

        String[] splitConteudoPDF = pdfBoxRn.getConteudoPDF(fileChooser.getSelectedFile().getPath()).split("\n");

        RapidoRN rapidoRN = new RapidoRN();

        QualisRN qualisRN = new QualisRN();
        
        qualisRN.salvar(rapidoRN.criar(splitConteudoPDF));
    }
}