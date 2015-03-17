package bpmlab.invio.bean;

import bpmlab.invio.bean.util.BeanUtil;
import bpmlab.invio.entidade.Login;
import bpmlab.invio.rn.LoginRN;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

/**
 *
 * @author Renan
 */
@ManagedBean
@RequestScoped
public class UsuarioCRUDBean {

    private final LoginRN rn = new LoginRN();
    private Login login = new Login();
    
    public UsuarioCRUDBean() {
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
    
    public String salvar() {
        if (rn.existe(login.getEmail())) {
            BeanUtil.criarMensagemDeErro("O Email ja está cadastrado tente outro.", "");
            return null;
        } else {
            login.setPerfil('U');
            ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
            String criptografada = encoder.encodePassword(login.getSenha(), null);
            login.setSenha(criptografada);
            if (rn.salvar(login)) {
                BeanUtil.criarMensagemDeInformacao("Sua conta foi criada com sucesso!", "");
                return "/loginInicio.xhtml";
            } else {
                BeanUtil.criarMensagemDeErro("Ocorreu um erro inesperado!", "");
                return null;
            }
        }
    }
}
