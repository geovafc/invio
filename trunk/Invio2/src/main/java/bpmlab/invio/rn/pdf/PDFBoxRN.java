/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bpmlab.invio.rn.pdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

/**
 *
 * @author Junior
 */
public class PDFBoxRN {
    
    public String getConteudoPDF(String pathPDF) {
        
        File filePDF = new File(pathPDF);
        FileInputStream fileInputStreamPDF = null;
        try {
            fileInputStreamPDF = new FileInputStream(filePDF);
        } catch (IOException e) {
            System.out.println("ERRO: " + e.getMessage());
            return "ERRO: " + e.getMessage();
        }

        PDDocument pdfDocument = null;
        try {
            PDFParser parser = new PDFParser(fileInputStreamPDF);
            parser.parse();
            pdfDocument = parser.getPDDocument();
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(pdfDocument);
        } catch (IOException e) {
            return "- Não é possível abrir a stream\n ERRO:\n"+e;
        } catch (Throwable e) {
            return "- Um erro ocorreu enquanto tentava obter o conteúdo do PDF\n ERRO:\n"+e;
        } finally {
            if (pdfDocument != null) {
                try {
                    pdfDocument.close();
                } catch (IOException e) {
            return "- Não foi possível fechar o PDF\n ERRO:\n"+e;
                }
            }
        }


    }
    
}
