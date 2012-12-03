/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import invio.entidade.Curriculo;
import invio.entidade.Login;
import invio.rn.CurriculoRN;
import invio.rn.LoginRN;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Junior
 */
@ManagedBean
@SessionScoped
public class UsuarioBean {

    
    LoginRN loginRN = new LoginRN();
    List<Login> logins;
    Login login = new Login();

    public UsuarioBean() {
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public String entrar() {

        boolean entrar = false;

        logins = loginRN.obterTodos();
        
        
        
        for (Login login1 : logins) {
            
        }

        

        return null;
    }
}
