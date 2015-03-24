/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bpmlab.invioscript;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

/**
 *
 * @author Dedo
 */
public class Main {

    public static void main(String[] args) {
        try {
            ConstruirQualis qualis = new ConstruirQualis(true, true);
            List<Qualis> lista = qualis.contruir();
            qualis.logEstatistica();
            Persistencia persistencia = new Persistencia();

//            JFrame frame = new JFrame("Qualis");
//            frame.setSize(600, 100);
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setLocationRelativeTo(null);
//            frame.setVisible(true);
//            JProgressBar progressBar = new JProgressBar(0, lista.size());
//            frame.getContentPane().add(progressBar);
//            
//            for (int i = 0; i < lista.size(); i++) {
//                persistencia.salvar(lista.get(i));
//                progressBar.setValue(i);
//                progressBar.repaint();
//            }
//            persistencia.close();
        } catch (IOException ex) {
            System.out.println("ERRO DE LEITURA");
        } catch (SQLException e) {
            System.out.println("ERRO DE PERSISTENCIA");
        }
    }
}
