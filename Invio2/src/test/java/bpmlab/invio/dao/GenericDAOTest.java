/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bpmlab.invio.dao;

import bpmlab.invio.entidade.Login;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author bpmlab
 */
public class GenericDAOTest {
    
    public GenericDAOTest() {
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

    @Test
    public void testCriar() {
        GenericDAO dao = new GenericDAO();
        Login login = new Login(null, "123", "email", 'A');
        dao.criar(login);
        assertNotNull(login.getId());
    }
}
