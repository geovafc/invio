/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bpmlab.invio.spring;

import bpmlab.invio.entidade.Login;
import bpmlab.invio.rn.LoginRN;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

/**
 *
 * @author bpmlab
 */
public class HandlerSucesso extends SimpleUrlAuthenticationSuccessHandler{
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response,
            Authentication a) throws IOException, ServletException {
        String pagina = "/publico/indexHome.xhtml";
        String login = a.getName();
        LoginRN loginRN = new LoginRN();
        Login usuario = loginRN.obter(login);
        if (usuario.getCodigoConfirmacao() != null) {
            pagina = "/publico/login/telaConfirmacao.xhtml";
        } 
        setDefaultTargetUrl(pagina);
        super.onAuthenticationSuccess(request, response, a);
    }
}
