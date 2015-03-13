/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bpmlab.invio.bean.util;

import bpmlab.invio.entidade.Login;
import bpmlab.invio.rn.LoginRN;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author fabio
 */
public class UsuarioUtil {

    public static Login obterUsuarioLogado() {
        FacesContext f = FacesContext.getCurrentInstance();
        ExternalContext e = f.getExternalContext();
        Login login = (Login) BeanUtil.lerDaSessao("USUARIO_LOGADO");
        if (login == null) {
            LoginRN loginRN = new LoginRN();
            login = loginRN.obter(e.getRemoteUser());
            BeanUtil.colocarNaSessao("USUARIO_LOGADO", login);
            return login;
        } else {
            return login;
        }
    }

    public static boolean isUsuarioLogadoAdministrador() {
        Login usuario = obterUsuarioLogado();
        return "ROLE_A".equals("ROLE_" + usuario.getPerfil());
    }

    public static boolean isUsuarioLogadoSecretaria() {
        Login usuario = obterUsuarioLogado();
        return "ROLE_S".equals("ROLE_" + usuario.getPerfil());
    }

    public static boolean isUsuarioLogadoUsuario() {
        Login usuario = obterUsuarioLogado();
        return "ROLE_U".equals("ROLE_" + usuario.getPerfil());
    }
}
