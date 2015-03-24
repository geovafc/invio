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
import javax.swing.JFrame;
import javax.swing.JProgressBar;

/**
 *
 * @author bpmlab
 */
public class ConstruirQualis {

    private boolean comBarraDeProgressao = false;
    private boolean comLogDeMontagem = false;
    private int total = 0;
    private int invalidos = 0;
    private int erros = 0;
    private final List<Qualis> lista = new ArrayList<>();

    public ConstruirQualis() {
    }

    public ConstruirQualis(boolean comBarraDeProgressao, boolean comLogDeMontagem) {
        this.comBarraDeProgressao = comBarraDeProgressao;
        this.comLogDeMontagem = comLogDeMontagem;
    }

    public List<Qualis> contruir() throws IOException {
        lista.clear();
        PdfReader pdfReader = new PdfReader("/home/bpmlab/NetBeansProjects/InvioScript/src/main/java/bpmlab/invioscript/Consulta_Webqualis.pdf");
        String[] page;
        JFrame frame = new JFrame("Qualis");
        frame.setSize(600, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        if (comBarraDeProgressao) {
            frame.setVisible(true);
        }
        JProgressBar progressBar = new JProgressBar(0, 114937);
        frame.getContentPane().add(progressBar);

        for (int i = 1; i <= pdfReader.getNumberOfPages(); i++) {
            page = PdfTextExtractor.getTextFromPage(pdfReader, i).split("\n");
            for (int j = 1; j < page.length; j++) {
                try {
                    if (page[j].contains("Friday 06 March 2015")) {
                        invalidos++;
                        continue;
                    }

                    if (isMaiusculo(page[j])) {
                        StringBuilder builder = new StringBuilder(page[j + 1]);
                        int index = page[j + 1].indexOf("Atualizado") - 1;
                        builder.insert(index, " " + page[j] + " " + page[j + 2]);
                        montagem(builder.toString());
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
        pdfReader.close();
        return lista;
    }

    public void logEstatistica() {
        System.out.println("Total: " + total);
        System.out.println("Validos:" + (lista.size()) + ";" + ((float) lista.size() / (float) total) * 100 + "%");
        System.out.println("Invalidos: " + invalidos + ";" + ((float) invalidos / (float) total) * 100 + "%");
        System.out.println("Erros: " + erros + ";" + ((float) erros / (float) total) * 100 + "%");
    }

    private boolean isMaiusculo(String string) {
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

    private void montagem(String linha) {
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
        if (comLogDeMontagem) {
            System.out.println(qualis);
        }
    }

    public boolean isComBarraDeProgressao() {
        return comBarraDeProgressao;
    }

    public void setComBarraDeProgressao(boolean comBarraDeProgressao) {
        this.comBarraDeProgressao = comBarraDeProgressao;
    }

    public boolean isComLogDeMontagem() {
        return comLogDeMontagem;
    }

    public void setComLogDeMontagem(boolean comLogDeMontagem) {
        this.comLogDeMontagem = comLogDeMontagem;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getInvalidos() {
        return invalidos;
    }

    public void setInvalidos(int invalidos) {
        this.invalidos = invalidos;
    }

    public int getErros() {
        return erros;
    }

    public void setErros(int erros) {
        this.erros = erros;
    }

    @Override
    public String toString() {
        return "Total: " + total + "\n"
                + "Validos:" + (total - invalidos - erros) + ";" + ((float) (total - invalidos - erros) / (float) total) * 100 + "%\n"
                + "Invalidos: " + invalidos + ";" + ((float) invalidos / (float) total) * 100 + "%\n"
                + "Erros: " + erros + ";" + ((float) erros / (float) total) * 100 + "%";
    }

}
