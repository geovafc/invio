/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.util;

import invio.util.ConfiguracaoUtil.TipoProducao;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dir de Armas Marinha
 */
public class ConfiguracaoUtilTest {

    public ConfiguracaoUtilTest() {
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
     * Test of getPath method, of class ConfiguracaoUtil.
     */
    @Test
    public void testGetPath() {
        System.out.println("getPath");
        TipoProducao[] tipo = {TipoProducao.PERIODICOS, 
            TipoProducao.LIVROS, 
            TipoProducao.ORIENTACOES};
        String[] expResult = {"C:/Invio/Curriculo/Periodicos",
            "C:/Invio/Curriculo/Livros",
            "C:/Invio/Curriculo/Orientacoes"};
        String result = null;
        for (int i = 0; i < expResult.length; i++) {
            result = ConfiguracaoUtil.getPath(tipo[i]);
            System.out.println(result);
            assertEquals(expResult[i], result);
        }
    }
}
