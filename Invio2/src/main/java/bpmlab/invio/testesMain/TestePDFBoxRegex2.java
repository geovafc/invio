package bpmlab.invio.testesMain;

import bpmlab.invio.dao.GenericDAO;
import bpmlab.invio.dao.JpaUtil;
import bpmlab.invio.entidade.Area;
import bpmlab.invio.entidade.Qualis;
import bpmlab.invio.rn.pdf.PDFBoxRN;
import bpmlab.invio.rn.pdf.PDFBoxRegexRN;
import bpmlab.invio.rn.pdf.QualisRN;
import java.util.List;
import javax.swing.JFileChooser;

public class TestePDFBoxRegex2 {

    public static void main(String[] args) {

//        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.showOpenDialog(null);
//
//        PDFBoxRN pdfBoxRn = new PDFBoxRN();
//
//        String[] splitConteudoPDF = pdfBoxRn.getConteudoPDF(fileChooser.getSelectedFile().getPath()).split("\n");
//
//        PDFBoxRegexRN rapidoRN = new PDFBoxRegexRN();
//
//        QualisRN qualisRN = new QualisRN();
//        
//        qualisRN.salvar(rapidoRN.criar(splitConteudoPDF));
        List<String> resultList = JpaUtil.getEntityManager()
                .createQuery("select distinct q.qualisPK.areaAvaliacao from Qualis q", String.class)
                .getResultList();
        GenericDAO dao = new GenericDAO();
        System.out.println("INICIO:" + resultList.size());
        for (String area : resultList) {
            dao.criar(new Area(null, area));
        }
        System.out.println("FIM");
        JpaUtil.closeEntityManager();
        JpaUtil.closeFactory();
    }
}