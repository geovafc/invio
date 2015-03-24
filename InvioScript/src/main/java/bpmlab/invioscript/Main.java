/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bpmlab.invioscript;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

/**
 *
 * @author Dedo
 */
public class Main {

    static int total = 0;
    static int invalidos = 0;
    static int erros = 0;
    static List<Qualis> lista = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        PdfReader pdfReader = new PdfReader("C:\\Users\\Dedo\\Downloads\\Consulta_Webqualis.pdf");
        String[] page;
        JFrame frame = new JFrame("Qualis");
        frame.setSize(600, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        JProgressBar progressBar = new JProgressBar(0, 123243);
        frame.getContentPane().add(progressBar);
        
        for (int i = 1; i <= pdfReader.getNumberOfPages(); i++) {
            page = PdfTextExtractor.getTextFromPage(pdfReader, i).split("\n");
            for (int j = 2; j < page.length; j++) {
                try {
                    if (page[j].contains("Friday 06 March 2015")) {
                        continue;
                    }
                    
                    if (!(page[j].contains("A1") || page[j].contains("A2") || page[j].contains("B1")
                            || page[j].contains("B2") || page[j].contains("B3") || page[j].contains("B4")
                            || page[j].contains(" B5") || page[j].contains(" C") || page[j].contains("Atualizado"))) {
                        throw new AssertionError();
                    }
                    
                    if (isMaiusculo(page[j])) {
                        StringBuilder builder = new StringBuilder(page[j + 1]);
                        int index = page[j + 1].indexOf("Atualizado") - 1;
                        builder.insert(index, " " + page[j] + " " + page[j + 2]);
                        montagem(builder.toString());
                        System.out.println("FOI");
                        j += 2;
                    } else {
                        montagem(page[j]);
                    }
                } catch (Exception ex) {
                    erros++;
                } catch (AssertionError ex) {
                    invalidos++;
                } finally {
                    total++;
                    progressBar.setValue(total);
                    progressBar.repaint();
                }
            }
        }
        System.out.println("Total: " + total);
        System.out.println("Validos:" + (total - invalidos - erros) + ";" + ((float) (total - invalidos - erros) / (float) total) * 100 + "%");
        System.out.println("Invalidos: " + invalidos + ";" + ((float) invalidos / (float) total) * 100 + "%");
        System.out.println("Erros: " + erros + ";" + ((float) erros / (float) total) * 100 + "%");
        pdfReader.close();
        System.exit(0);
    }

    private static boolean isMaiusculo(String string) {
        char[] letras = string.toCharArray();
        for (int i = 0; i < string.length(); i++) {
            if (letras[i] == ',' || letras[i] == ' ') {
                continue;
            }
            if (Character.isLowerCase(letras[i])) {
                return false;
            }
        }
        return true;
    }
    
    private static void montagem(String linha) {
        String issn = null;
        if (linha.substring(0, 9).matches("\\w\\w\\w\\w-\\w\\w\\w\\w")) {
            issn = linha.substring(0, 9);
        } else {
            throw new AssertionError();
        }

        String titulo = null;
        String estrato = null;
        if (linha.contains(" A1 ")) {
            estrato = "A1";
            titulo = linha.substring(10, linha.indexOf("A1"));
        } else if (linha.contains(" A2 ")) {
            estrato = "A2";
            titulo = linha.substring(10, linha.indexOf("A2"));
        } else if (linha.contains(" B1 ")) {
            estrato = "B1";
            titulo = linha.substring(10, linha.indexOf("B1"));
        } else if (linha.contains(" B2 ")) {
            estrato = "B2";
            titulo = linha.substring(10, linha.indexOf("B2"));
        } else if (linha.contains(" B3 ")) {
            estrato = "B3";
            titulo = linha.substring(10, linha.indexOf("B3"));
        } else if (linha.contains(" B4 ")) {
            estrato = "B4";
            titulo = linha.substring(10, linha.indexOf("B4"));
        } else if (linha.contains(" B5 ")) {
            estrato = "B5";
            titulo = linha.substring(10, linha.indexOf("B5"));
        } else if (linha.contains(" C ")) {
            estrato = "C";
            titulo = linha.substring(10, linha.indexOf("C "));
        } else {
            throw new AssertionError();
        }
        int inicioAreaDeAvaliacao = linha.indexOf(estrato) + 3;
        int fimAreaDeAvaliacao = linha.indexOf("Atualizado");
        String areaDeAvaliacao = linha.substring(inicioAreaDeAvaliacao - 1, fimAreaDeAvaliacao);
        
        if (!linha.contains(" Atualizado")) {
            throw new AssertionError();
        }
        
        if (titulo.trim().isEmpty()) {
            throw new AssertionError();
        }
        
        Qualis qualis = new Qualis(issn.trim(), titulo.trim(), estrato.trim(), areaDeAvaliacao.trim(), "Atualizado");
        lista.add(qualis);
    }
}
