/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package invio.bean;

import invio.entidade.Curriculo;
import invio.rn.AvaliacaoRN;
import invio.rn.CurriculoRN;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author bpmlab
 */
public class AvaliacaoRNTest {
    
    private static CurriculoRN rnC;
    private static AvaliacaoRN rn;
    
    public AvaliacaoRNTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        rn = new AvaliacaoRN();
        rnC= new CurriculoRN();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAutoCompleteCurriculo() {
        Curriculo esperado = rnC.obter(3);
        Curriculo atual = rn.autoCompleteCurriculo("Usuario").get(0);
        System.out.println(atual.getNome());
        Assert.assertEquals(esperado.getNome(), atual.getNome());
        Assert.assertEquals(esperado.getLattes(), atual.getLattes());
        Assert.assertNotNull(atual.getPeriodicoList());
        Assert.assertNotNull(atual.getLivroList());
        Assert.assertNotNull(atual.getOrientacaoList());
    }

}
