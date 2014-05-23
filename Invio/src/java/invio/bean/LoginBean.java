/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.bean;

import invio.bean.util.BeanUtil;
import invio.entidade.Login;
import invio.rn.LoginRN;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author bpmlab
 */
@ManagedBean
@ViewScoped
public class LoginBean {

    private Login login;
    private LoginRN loginRN = new LoginRN();

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public String entendeu() {
        switch (login.getPerfil()) {
            case 'A':
                return "Administrador";
            case 'S':
                return "Secretaria";
            case 'U':
                return "Usuário";
            default:
                return "---";
        }
    }

    public List<Login> autoComplete(String query) {
        return loginRN.filtrar(query);
    }

    public String excluirLogin() {
        String resposta = null;
        if (loginRN.remover(login)) {
            BeanUtil.criarMensagemDeInformacao(
                    "Sucesso",
                    "Login excluído");
//            resposta = "/administracao/usuarios/listarUsuarios.xhtml";
            resposta = "/indexHome.xhtml";
        } else {
            BeanUtil.criarMensagemDeErro(
                    "Erro",
                    "Não foi possível excluir o login");
        }
        return resposta;
    }

    public void alterarPermissao() {
        if (loginRN.salvar(login)) {
            BeanUtil.criarMensagemDeInformacao(
                    "Sucesso",
                    "Permissão alterada");
        } else {
            BeanUtil.criarMensagemDeErro(
                    "Erro",
                    "Não foi possível alterar a permissão");
        }
    }
}
