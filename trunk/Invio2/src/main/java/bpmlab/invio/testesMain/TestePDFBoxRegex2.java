package bpmlab.invio.testesMain;

import bpmlab.invio.rn.pdf.PDFBoxRN;
import bpmlab.invio.rn.pdf.PDFBoxRegexRN;
import bpmlab.invio.rn.pdf.QualisRN;
import javax.swing.JFileChooser;

public class TestePDFBoxRegex2 {

    public static void main(String[] args) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);

        PDFBoxRN pdfBoxRn = new PDFBoxRN();

        String[] splitConteudoPDF = pdfBoxRn.getConteudoPDF(fileChooser.getSelectedFile().getPath()).split("\n");

        PDFBoxRegexRN rapidoRN = new PDFBoxRegexRN();

        QualisRN qualisRN = new QualisRN();
        
        qualisRN.salvar(rapidoRN.criar(splitConteudoPDF));
    }
}