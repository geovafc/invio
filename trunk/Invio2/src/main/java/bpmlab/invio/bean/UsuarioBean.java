package bpmlab.invio.bean;

import bpmlab.invio.bean.util.BeanUtil;
import bpmlab.invio.bean.util.UsuarioUtil;
import bpmlab.invio.entidade.Curriculo;
import bpmlab.invio.entidade.Login;
import bpmlab.invio.rn.JavaMailRN;
import bpmlab.invio.rn.LoginRN;
import bpmlab.invio.util.javamail.TextoEmail;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class UsuarioBean {

    private LoginRN loginRN = new LoginRN();
    private JavaMailRN javaMailRN = new JavaMailRN();
    private List<Login> logins;
    private Login login = new Login();
    private Curriculo curriculo = new Curriculo();
    private Login usuarioLogado = new Login();
    private String codigoDeConfirmacao;
    private String cpfLoginTemp = "";
    private String cpfLogin = "";
    private String usuario;

    public UsuarioBean() {
    }

    public List<Login> getLogins() {
        if (UsuarioUtil.isUsuarioLogadoAdministrador()) {
            logins = loginRN.obterTodos();
        } else {
            logins = null;
            BeanUtil.criarMensagemDeInformacao("Página não autorizada para este Usuário.", "");
        }
        return logins;
    }

    public String getCodigoDeConfirmacao() {
        return codigoDeConfirmacao;
    }

    public void setCodigoDeConfirmacao(String codigoDeConfirmacaoTemp) {
        this.codigoDeConfirmacao = codigoDeConfirmacaoTemp;
    }

    public Curriculo getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(Curriculo curriculo) {
        this.curriculo = curriculo;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public String getUsuario() {
        return usuario;
    }

    public String irRecuperarSenha() {
        return "/publico/login/recuperarSenha.xhtml";
    }

    boolean entrar = false;

    public void setEntrar(boolean entrar) {
        this.entrar = entrar;
    }

    public String getCpfLoginTemp() {
        return cpfLoginTemp;
    }

    public void setCpfLoginTemp(String cpfLoginTemp) {
        this.cpfLoginTemp = cpfLoginTemp;
    }

    public String getCpfLogin() {
        return cpfLogin;
    }

    public void setCpfLogin(String cpfLogin) {
        this.cpfLogin = cpfLogin;
    }

    public String recuperarSenha() {
        logins = loginRN.obterTodos();
        boolean loginEncontrado = false;
        String pagina = "";
        for (Login loginTemp : logins) {

            if (loginTemp.getEmail().equals(login.getEmail())) {
                loginEncontrado = true;
                if (loginEncontrado == true) {

                    login = loginTemp;
                    boolean falhaAoEnviarEmail = javaMailRN.configurarEnviarEmail(
                            login, 
                            "Solicitação de recuperação de senha",
                            TextoEmail.getTextoEmailRecuperacaoSenha(login, loginTemp.getSenha()));
                    if (falhaAoEnviarEmail == true) {
                        pagina = "/loginInicio.xhtml";
                        BeanUtil.criarMensagemDeAviso("Desculpe, ocorreu uma falha no sistema. ",
                                "Não foi possível enviar o e-mail de recuperação de senha, tente novamente mais tarde.");
                        javaMailRN = new JavaMailRN();
                    } else {
                        pagina = "/loginInicio.xhtml";
                        BeanUtil.criarMensagemDeAviso("A Senha foi enviada para seu e-mail.", "");
                    }
                }
            }
        }
        if (loginEncontrado != true) {
            pagina = "/publico/login/recuperarSenha.xhtml";
            BeanUtil.criarMensagemDeAviso("O E-mail inserido não foi encontrado.",
                    "Tente novamente.");
        }
        return pagina;
    }

    public String salvarCurriculo() {
        return "/publico/indexHome.xhtml";
    }

    public String salvar2() {
        //concluir este método. Será chamado no alterar senha que ainda
        //não esta implementado na página.
        String resposta = null;
        if (loginRN.salvar(login)) {
            boolean falhaAoEnviar = javaMailRN.configurarEnviarEmail(
                    login,
                    "Confirmação de registro de e-mail",
                    TextoEmail.getTextoEmailCodigoConfirmacao(login));

            if (falhaAoEnviar == true) {
                loginRN.remover(login);
                resposta = "/loginInicio.xhtml";
                BeanUtil.criarMensagemDeAviso("Desculpe, ocorreu uma falha no sistema. ",
                        "Não foi possível concluir a requisição, tente mais tarde.");
                javaMailRN = new JavaMailRN();
            } else {
                resposta = "/loginInicio.xhtml";
                BeanUtil.criarMensagemDeAviso(
                        "Aviso",
                        "Seus dados foram alterados com sucesso.");
            }
        }
        return resposta;
    }

    public String validar() {
        Login usuarioAtual = UsuarioUtil.obterUsuarioLogado();
        if (usuarioAtual.getCodigoConfirmacao().equals(codigoDeConfirmacao)) {
            usuarioAtual.setCodigoConfirmacao(null);
            loginRN.salvar(usuarioAtual);
            return "/publico/indexHome.xhtml";
        } else {
            FacesMessage fm = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Erro!",
                    "Código de confirmação inválido!");
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return null;
        }
    }
    
    public Login getUsuarioLogado() {
        usuarioLogado = UsuarioUtil.obterUsuarioLogado();
        return usuarioLogado;
    }

    public void setUsuarioLogado(Login usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public boolean isAdministrador() {
        return UsuarioUtil.isUsuarioLogadoAdministrador();
    }

    public boolean isSecretaria() {
        return UsuarioUtil.isUsuarioLogadoSecretaria();
    }
    
    public boolean isUsuarioSessao() {
        return UsuarioUtil.isUsuarioLogadoUsuario();
    }
}
