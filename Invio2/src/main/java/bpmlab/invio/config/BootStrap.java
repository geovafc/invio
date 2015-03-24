/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bpmlab.invio.config;

import bpmlab.invio.dao.GenericDAO;
import bpmlab.invio.dao.JpaUtil;
import bpmlab.invio.entidade.Area;
import bpmlab.invio.entidade.Login;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

/**
 *
 * @author bpmlab
 */
@WebListener
public class BootStrap implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        GenericDAO dao = new GenericDAO();
        ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
        try {
            dao.criar(new Login(1, encoder.encodePassword("123", null), "mkmikael@gmail.com", 'A'));
            Area a = new Area(1, "Computação");
            dao.criar(a);
        } catch (Exception e) {
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        JpaUtil.closeFactory();
    }

}
