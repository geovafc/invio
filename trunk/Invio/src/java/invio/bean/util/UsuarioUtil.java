/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.bean.util;

import invio.entidade.Login;
import invio.entidade.Perfil;
import invio.rn.LoginRN;
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
        LoginRN loginRN = new LoginRN();
        return loginRN.obter(e.getRemoteUser());
    }

    public static boolean isUsuarioLogadoMaster() {
        Login usuario = obterUsuarioLogado();
        for (Perfil perfil : usuario.getPerfilList()) {
            if ("ROLE_MASTER".equals(perfil.getDescricao())) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isUsuarioLogadoAdministracao() {
        Login usuario = obterUsuarioLogado();
        for (Perfil perfil : usuario.getPerfilList()) {
            if ("ROLE_MASTER".equals(perfil.getDescricao())) {
                return true;
            }
        }
        return false;
    }
}
