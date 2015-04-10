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

/**
 *
 * @author bpmlab
 */
public class ConstruirQualis {

    public static List<String> primeiraValidacao() {
        try {
            PdfReader pdfReader = new PdfReader("/home/bpmlab/NetBeansProjects/InvioScript/src/main/java/bpmlab/invioscript/Consulta_Webqualis.pdf");
            String[] linha;
            String novaLinha = null;
            List<String> qualis = new ArrayList<>();
            int total = 0;
            int invalidos = 0;
            for (int i = 1; i <= pdfReader.getNumberOfPages(); i++) {
                linha = PdfTextExtractor.getTextFromPage(pdfReader, i).split("\n");
                for (int j = 1; j < linha.length; j++) {
                    total++;
                    try {
                        if (linha[j].contains("Friday 06 March 2015")
                                || linha[j].contains("TURISMO")
                                || linha[j].contains("INTERNACIONAIS")
                                || linha[j].contains("DEMOGRAFIA")
                                || linha[j].contains("Lado C")
                                || linha[j].contains("y TA Journal of Food C")
                                || linha[j].contains("www.siicsalud.com C NUTRIÇÃO Atualizado")
                                || linha[j].contains("ISSN TÍTULO ESTRATO ÁREA DE AVALIAÇÃO STATUS")) {
                            throw new Exception();
                        }

                        if (!linha[j].contains("Atualizado")) {
                            throw new Exception();
                        }

                        int indexFinal = linha[j].indexOf("Atualizado");

                        if (linha[j].contains(" A1 ")) {
                            novaLinha = linha[j].substring(linha[j].indexOf(" A1 ") + 4, indexFinal);
                        } else if (linha[j].contains(" A2 ")) {
                            novaLinha = linha[j].substring(linha[j].indexOf(" A2 ") + 4, indexFinal);
                        } else if (linha[j].contains(" B1 ")) {
                            novaLinha = linha[j].substring(linha[j].indexOf(" B1 ") + 4, indexFinal);
                        } else if (linha[j].contains(" B2 ")) {
                            novaLinha = linha[j].substring(linha[j].indexOf(" B2 ") + 4, indexFinal);
                        } else if (linha[j].contains(" B3 ")) {
                            novaLinha = linha[j].substring(linha[j].indexOf(" B3 ") + 4, indexFinal);
                        } else if (linha[j].contains(" B4 ")) {
                            novaLinha = linha[j].substring(linha[j].indexOf(" B4 ") + 4, indexFinal);
                        } else if (linha[j].contains(" B5 ")) {
                            novaLinha = linha[j].substring(linha[j].indexOf(" B5 ") + 4, indexFinal);
                        } else if (linha[j].contains(" C ")) {
                            novaLinha = linha[j].substring(linha[j].indexOf(" C ") + 3, indexFinal);
                        } else {
                            throw new Exception();
                        }

                        if (!linha[j].substring(0, 9).matches("\\w\\w\\w\\w-\\w\\w\\w\\w")
                                || linha[j].substring(0, 12).matches("\\w\\w\\w\\w-\\w\\w\\w\\w A1")
                                || linha[j].substring(0, 12).matches("\\w\\w\\w\\w-\\w\\w\\w\\w A2")
                                || linha[j].substring(0, 12).matches("\\w\\w\\w\\w-\\w\\w\\w\\w B1")
                                || linha[j].substring(0, 12).matches("\\w\\w\\w\\w-\\w\\w\\w\\w B2")
                                || linha[j].substring(0, 12).matches("\\w\\w\\w\\w-\\w\\w\\w\\w B3")
                                || linha[j].substring(0, 12).matches("\\w\\w\\w\\w-\\w\\w\\w\\w B4")
                                || linha[j].substring(0, 12).matches("\\w\\w\\w\\w-\\w\\w\\w\\w B5")
                                || linha[j].substring(0, 12).matches("\\w\\w\\w\\w-\\w\\w\\w\\w C ")) {
                            throw new Exception();
                        }
                        if (novaLinha != null) {
                            qualis.add(linha[j]);
                        }
                        novaLinha = null;
                    } catch (Exception e) {
                        StringBuilder construirLinha;
                        switch (linha[j]) {
                            case "ADMINISTRAÇÃO, CIÊNCIAS CONTÁBEIS E":
                                construirLinha = new StringBuilder(linha[j + 1]);
                                construirLinha.insert(linha[j + 1].indexOf("Atualizado") - 1,
                                        " " + linha[j] + " " + linha[j + 2]);
                                qualis.add(construirLinha.toString());
                                break;
                            case "CIÊNCIA POLÍTICA E RELAÇÕES":
                                construirLinha = new StringBuilder(linha[j + 1]);
                                construirLinha.insert(linha[j + 1].indexOf("Atualizado") - 1,
                                        " " + linha[j] + " " + linha[j + 2]);
                                qualis.add(construirLinha.toString());
                                break;
                            case "PLANEJAMENTO URBANO E REGIONAL /":
                                construirLinha = new StringBuilder(linha[j + 1]);
                                construirLinha.insert(linha[j + 1].indexOf("Atualizado") - 1,
                                        " " + linha[j] + " " + linha[j + 2]);
                                qualis.add(construirLinha.toString());
                                break;
                            case "American Journal of Physiology. Regulatory, Integrative and Comparative Physiology":
                                construirLinha = new StringBuilder(linha[j + 1]);
                                construirLinha.insert(9, " " + linha[j]);
                                qualis.add(construirLinha.toString());
                                break;
                            case "Proceedings of the National Academy of Sciences of the United States of America":
                                construirLinha = new StringBuilder(linha[j + 1]);
                                construirLinha.insert(9, " " + linha[j] + linha[j + 2]);
                                qualis.add(construirLinha.toString());
                                break;
                            case "Revista de Clínica e Pesquisa Odontológica (Impresso) / Journal of Dental Clinical and":
                                construirLinha = new StringBuilder(linha[j + 1]);
                                construirLinha.insert(9, " " + linha[j] + " " + linha[j + 2]);
                                qualis.add(construirLinha.toString());
                                break;
                            default:
                                invalidos++;
                                if (!(linha[j].contains("Friday 06 March")
                                        || linha[j].contains("TURISMO")
                                        || linha[j].contains("(Online)")
                                        || linha[j].contains("Research")
                                        || linha[j].contains("INTERNACIONAIS")
                                        || linha[j].contains("DEMOGRAFIA"))) {
//                                    System.out.println(linha[j]);
                                }
                                break;
                        }
                    }
                }
            }
            for (String q : qualis) {
                System.out.println(q);
            }
            System.out.println("TOTAL: " + total);
            System.out.println("VALIDOS: " + qualis.size() + ";" + ((float) qualis.size() * 100 / total) + "%");
            System.out.println("INVALIDOS: " + invalidos + ";" + ((float) invalidos * 100 / total) + "%");
            System.out.println(qualis.size() + invalidos);
            return qualis;
        } catch (IOException ex) {
            return null;
        }
    }

    public static void main(String[] args) {
        primeiraValidacao();
    }

}
