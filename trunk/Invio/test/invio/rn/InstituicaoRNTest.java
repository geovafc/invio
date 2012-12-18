/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.rn;

import invio.entidade.Instituicao;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author RSORANSO
 */
public class InstituicaoRNTest {
    
    public InstituicaoRNTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
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

    /**
     * Test of salvar method, of class InstituicaoRN.
     */
    @Test
    public void testSalvar() {
        System.out.println("salvar");
        Instituicao i = null;
        InstituicaoRN instance = new InstituicaoRN();
        boolean expResult = false;
        boolean result = instance.salvar(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remover method, of class InstituicaoRN.
     */
    @Test
    public void testRemover() {
        System.out.println("remover");
        Instituicao i = null;
        InstituicaoRN instance = new InstituicaoRN();
        boolean expResult = false;
        boolean result = instance.remover(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obter method, of class InstituicaoRN.
     */
    @Test
    public void testObter() {
        System.out.println("obter");
        Integer id = null;
        InstituicaoRN instance = new InstituicaoRN();
        Instituicao expResult = null;
        Instituicao result = instance.obter(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obterTodos method, of class InstituicaoRN.
     */
    @Test
    public void testObterTodos() {
        System.out.println("obterTodos");
        InstituicaoRN instance = new InstituicaoRN();
        List expResult = null;
        List result = instance.obterTodos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
