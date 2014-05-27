package invio.bean;

import invio.bean.util.BeanUtil;
import invio.bean.util.UsuarioUtil;
import invio.entidade.Curriculo;
import invio.entidade.Login;
import invio.rn.CurriculoRN;
import invio.rn.JavaMailRN;
import invio.rn.LoginRN;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class UsuarioBean {

    private LoginRN loginRN = new LoginRN();
    private CurriculoRN curriculoRN = new CurriculoRN();
    private JavaMailRN javaMailRN = new JavaMailRN();
    private List<Login> logins;
    private Login login = new Login();
    private Curriculo curriculo = new Curriculo();
    private Login usuarioLogado = new Login();
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

    public String logoutSair() {
        configurarLimparSessao();
        return "/loginInicio.xhtml";
    }

    public String cancelarTelaConfirmacao() {
        configurarLimparSessao();
        return "/loginInicio.xhtml";
    }

    public void configurarLimparSessao() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        BeanUtil.limpaSessaoParaInicio(session);
    }

    public String irNovoUsuario() {
//        configurarLimparSessao();
        return "/publico/login/novoUsuario.xhtml";
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
                    boolean falhaAoEnviarEmail = javaMailRN.configurarEnviarEmail(login, "Solicitação de recuperação de senha",
                            BeanTextoEmail.getTextoEmailRecuperacaoSenha(login, loginTemp.getSenha()));
                    if (falhaAoEnviarEmail == true) {
                        pagina = "/loginInicio.xhtml";
                        BeanUtil.criarMensagemDeAviso("Desculpe, ocorreu uma falha no sistema. ",
                                "Não foi possível enviar o e-mail de recuperação de senha, tente novamente mais tarde.");
                        javaMailRN = new JavaMailRN();
                    } else {
                        pagina = "/loginInicio.xhtml";
                        BeanUtil.criarMensagemDeAviso("A Senha foi enviada para seu e-mail.", "");
                        configurarLimparSessao();
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

    public String salvar() {
        login.setCodigoConfirmacaoTemp("XPTO");
        login.setDtCriacao(new Date());// RECEBER DATA ATUAL DO BANCO DE DADOS
        login.setAtivo(true);
        boolean falhaAoEnviar = javaMailRN.configurarEnviarEmail(login, "Confirmação de registro de e-mail", BeanTextoEmail.getTextoEmailCodigoConfirmacao(login));
        if (falhaAoEnviar) {
            BeanUtil.criarMensagemDeAviso(
                    "Falha no sistema. ",
                    "Desculpe, não foi possível concluir o cadastro.");
            configurarLimparSessao();
            javaMailRN = new JavaMailRN();
            return "/public/login/novoUsuario.xhtml";
        } else {
            BeanUtil.criarMensagemDeAviso(
                    "Sucesso",
                    "Sua inscrição no Sistema foi realizada com sucesso.");
            return "/loginInicio.xhtml";
        }
    }
    ArrayList<String> tiposPerfil;

    public ArrayList<String> getTipoPerfilUsuarioComum() {
        tiposPerfil = new ArrayList<String>();
        tiposPerfil.add("");
        tiposPerfil.add("Docente");

        return tiposPerfil;

    }

    public ArrayList<String> getTipoPerfilAdmin() {
        tiposPerfil = new ArrayList<String>();
        tiposPerfil.add("Discente");
        tiposPerfil.add("Docente");
        tiposPerfil.add("Administrador");
        tiposPerfil.add("Master");
        return tiposPerfil;
    }
    String pagina2 = "";
    boolean emailJaCadastrado = false;

    public void setEmailJaCadastrado(boolean emailJaCadastrado) {
        this.emailJaCadastrado = emailJaCadastrado;
    }

    public String salvar2() {
        //concluir este método. Será chamado no alterar senha que ainda
        //não esta implementado na página.
        String resposta = null;
        if (loginRN.salvar(login)) {
            boolean falhaAoEnviar = javaMailRN.configurarEnviarEmail(
                    login,
                    "Confirmação de registro de e-mail",
                    BeanTextoEmail.getTextoEmailCodigoConfirmacao(login));

            if (falhaAoEnviar == true) {
                loginRN.remover(login);
                resposta = "/loginInicio.xhtml";
                BeanUtil.criarMensagemDeAviso("Desculpe, ocorreu uma falha no sistema. ",
                        "Não foi possível concluir a requisição, tente mais tarde.");
                configurarLimparSessao();
                javaMailRN = new JavaMailRN();
            } else {
                resposta = "/loginInicio.xhtml";
                BeanUtil.criarMensagemDeAviso(
                        "Aviso",
                        "Seus dados foram alterados com sucesso.");
                configurarLimparSessao();
            }
        }
        return resposta;
    }

    public String okCodigo() {
        String resposta = null;
        if (login.getCodigoConfirmacaoTemp().equals(login.getCodigoConfirmacao())) {
            login.setCodigoConfirmacaoTemp(login.getCodigoConfirmacao());
            loginRN.salvar(login);
            resposta = "/publico/indexHome.xhtml";
        } else {
            BeanUtil.criarMensagemDeAviso(
                    "Aviso",
                    "O código inserido está incorreto.");
            resposta = "/publico/login/telaConfirmacao.xhtml";
        }
        return resposta;
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

    public boolean isUsuario() {
        return UsuarioUtil.isUsuarioLogadoUsuario();
    }
}
