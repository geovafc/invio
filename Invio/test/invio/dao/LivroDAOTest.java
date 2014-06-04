/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package invio.dao;

import invio.entidade.Curriculo;
import invio.entidade.Livro;
import invio.entidade.Login;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author toshiaki
 */
public class LivroDAOTest {
    
    private static LoginDAO dao;
    
    public LivroDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        dao = new LoginDAO();
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
    public void testCriar() {
        Login esperado = new Login();
        int num = (int)(Math.random() * 10000);
        esperado.setEmail("fulano" + num + "@bol.com");
        esperado.setSenha("123");
        esperado.setPerfil('A');
        
        boolean condition = dao.criar(esperado);
        
        Login atual = dao.obter(esperado.getEmail());
        assertTrue(condition);
        assertEquals(esperado.getEmail(), atual.getEmail());
        assertEquals(esperado.getSenha(), atual.getSenha());
        assertEquals(esperado.getPerfil(), atual.getPerfil());
    }
    
}
