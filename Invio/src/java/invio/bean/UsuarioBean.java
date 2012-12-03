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
        boolean aparecerMensagem = false;
        boolean confirmacao = false;

        logins = loginRN.obterTodos();


        if (logins != null || logins.size() >= 0) {

            for (Login loginTemp : logins) {

                if (loginTemp.getCurriculoId().getEmail().equals(login.getCurriculoId().getEmail())
                        && loginTemp.getSenha().equals(login.getSenha())) {
                    entrar = true;

                    login = loginTemp;
                }
            }
        } else {
            aparecerMensagem = true;
            return "/publico/login/loginInicio.xhtml";
        }

        if (entrar == true) {
            if (login.getCodigoConfirmacao() == null) {
                return "/publico/login/telaConfirmacao.xhtml";
            } 
        }
        
        return "/publico/login/loginInicio.xhtml";
    }
}
