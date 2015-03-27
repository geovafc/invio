/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bpmlab.invio.config;

import bpmlab.invio.dao.GenericDAO;
import bpmlab.invio.dao.JpaUtil;
import bpmlab.invio.entidade.Area;
import bpmlab.invio.entidade.Curriculo;
import bpmlab.invio.entidade.Login;
import bpmlab.invio.entidade.Qualis;
import java.util.Date;
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
            Area area = new Area(null, "Computação");
            dao.criar(area);
            Curriculo curriculo = new Curriculo(null, "987.321.432-12", "Mikael dos Santos Lima", "Rua", "Centro", "Barcarena", "PA", "Brasil", "(91)1322-1234", "mkmikael@gmail.com", "12342", "lattes/1241", "Masculino");
            curriculo.setTitulacao("Doutorado");
            curriculo.setDtNascimento(new Date());
            curriculo.setInstitutoCampi("UFRA");
            curriculo.setCep("68445-000");
            curriculo.setArea(area);
            dao.criar(curriculo);
            Login login = new Login(null, encoder.encodePassword("123", null), "mkmikael@gmail.com", 'A');
            login.setCurriculo(curriculo);
            dao.criar(login);
            
            Qualis qualis = new Qualis("Programação", "Computação", "1234");
            qualis.setEstrato("A1");
            qualis.setStatus("Atualizado");
            dao.criar(qualis);
        } catch (Exception e) {
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

}
