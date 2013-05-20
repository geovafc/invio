package invio.bean;

import invio.entidade.Curriculo;
import invio.entidade.Login;
import invio.entidade.Perfil;
import invio.rn.CurriculoRN;
import invio.rn.JavaMailRN;
import invio.rn.LoginRN;
import invio.rn.PerfilRN;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@ManagedBean
@SessionScoped
public class UsuarioBean implements UserDetailsService {

    PerfilRN perfilRN = new PerfilRN();
    LoginRN loginRN = new LoginRN();
    CurriculoRN curriculoRN = new CurriculoRN();
    JavaMailRN javaMailRN = new JavaMailRN();
    List<Login> logins;
    Perfil perfil = new Perfil();
    Login login = new Login();
    Curriculo curriculo = new Curriculo();
    private String codigoConfirmacao = "EJR8T31W";
    private String permissao;
    private Login usuarioLogado = new Login();

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }

    public UsuarioBean() {
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
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
        configurarLimparSessao();
        return "/publico/login/novoUsuario.xhtml";
    }
    boolean entrar = false;

    public void setEntrar(boolean entrar) {
        this.entrar = entrar;
    }

    public String entrar() {

        boolean confirmacao = false;
        boolean loginEncontrado = false;
        String pagina = "";

        logins = loginRN.obterTodos();

        if (logins.size() > 0) {

            for (Login loginTemp : logins) {

                if (loginTemp.getEmail().equals(login.getEmail())
                        && loginTemp.getSenha().equals(login.getSenha())) {
                    login = loginTemp;
                    setEntrar(true);
                    loginEncontrado = true;
                }
            }
            if (loginEncontrado != true) {
                pagina = "/loginInicio.xhtml";
                //login = null;
                BeanUtil.criarMensagemDeAviso("O e-mail ou a senha inserido está incorreto.",
                        "");
            }
        } else {
            setEntrar(false);
            pagina = "/loginInicio.xhtml";
            //login = null;
            BeanUtil.criarMensagemDeAviso("O e-mail ou a senha inserido está incorreto.",
                    "");
        }

        if (entrar == true) {
            if (login.getCodigoConfirmacaoTemp().equals("") && login.getCodigoConfirmacao().equals(codigoConfirmacao)) {
                pagina = "/publico/login/telaConfirmacao.xhtml";
            } else if (login.getCodigoConfirmacaoTemp().equals(login.getCodigoConfirmacao())) {
                pagina = "/publico/indexHome.xhtml";
            }
        }

        return pagina;
    }
    String cpfLogin = "";
    String cpfLoginTemp = "";

    public String getCpfLogin() {
        return cpfLogin;
    }

    public void setCpfLogin(String cpfLogin) {
        this.cpfLogin = cpfLogin;
    }

    public String getCpfLoginTemp() {
        return cpfLoginTemp;
    }

    public void setCpfLoginTemp(String cpfLoginTemp) {
        this.cpfLoginTemp = cpfLoginTemp;
    }

    public String recuperarSenha() {
        logins = loginRN.obterTodos();
        boolean loginEncontrado = false;
        String pagina = "";
        setCpfLoginTemp(getCpfLoginTemp());
        for (Login loginTemp : logins) {

            if (loginTemp.getEmail().equals(login.getEmail())) {

//                setCpfLogin(loginTemp.getCurriculoId().getCpf());
                loginEncontrado = true;

                if (loginEncontrado == true) {

                    if (getCpfLoginTemp().equals(getCpfLogin())) {

                        login = loginTemp;
                        boolean falhaAoEnviarEmail = javaMailRN.configurarEnviarEmail(login, "Solicitação de recuperação de senha",
                                BeanTextoEmail.getTextoEmailRecuperacaoSenha(login, loginTemp.getSenha()));


                        if (falhaAoEnviarEmail == true) {
                            pagina = "/loginInicio.xhtml";
                            BeanUtil.criarMensagemDeAviso("Desculpe, ocorreu uma falha no sistema. ",
                                    "Não foi possível enviar o e-mail de recuperação de senha, tente mais tarde.");
                            javaMailRN = new JavaMailRN();
                        } else {
                            pagina = "/loginInicio.xhtml";
                            BeanUtil.criarMensagemDeAviso("A Senha foi enviada para seu e-mail.", "");
                            configurarLimparSessao();

                        }

                    } else {
                        BeanUtil.criarMensagemDeAviso("O CPF inserido não foi encontrado, ",
                                "ele pode está incorreto.");
                    }
                }
            }
        }
        if (loginEncontrado != true) {
            pagina = "/publico/login/recuperarSenha.xhtml";
            BeanUtil.criarMensagemDeAviso("O E-mail inserido não foi encontrado, ",
                    "ele pode está incorreto.");
        }

        return pagina;
    }

    public void configurarSalvarLogin() {

        login.setCodigoConfirmacaoTemp("");
        login.setCodigoConfirmacao(codigoConfirmacao);
        login.setDtCriacao(new Date());// RECEBER DATA ATUAL DO BANCO DE DADOS
        login.setAtivo(true);

        List<Perfil> perfis = new ArrayList<Perfil>();
        perfil = perfilRN.obter(permissao);
        perfis.add(perfil);
        perfil.getLoginList().add(login);

        login.setPerfilList(perfis);

        if (loginRN.salvar(login)) {
            perfilRN.salvar(perfil);

            boolean falhaAoEnviar = javaMailRN.configurarEnviarEmail(login, "Confirmação de registro de e-mail", BeanTextoEmail.getTextoEmailCodigoConfirmacao(login));

            if (falhaAoEnviar == true) {
                loginRN.remover(login);
                pagina2 = "/loginInicio.xhtml";
                BeanUtil.criarMensagemDeAviso("Desculpe, ocorreu uma falha no sistema. ",
                        "Não foi possível concluir o cadastro, tente mais tarde.");
                configurarLimparSessao();
                javaMailRN = new JavaMailRN();
            } else {

                pagina2 = "/loginInicio.xhtml";
                //login = null;
                BeanUtil.criarMensagemDeAviso("Foi enviado para seu e-mail um código de confirmação de cadastro.",
                        "Quando for realizado o login será solicitado o código.");
                configurarLimparSessao();
            }
        }
    }
    ArrayList<String> tiposPerfil;

    public ArrayList<String> getTipoPerfilUsuarioComum() {
        tiposPerfil = new ArrayList<String>();
        tiposPerfil.add("Discente");
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
        if (!loginRN.existe(login.getEmail())) {
            if (!javaMailRN.configurarEnviarEmail(login, "Confirmação de registro "
                    + "de e-mail", BeanTextoEmail.getTextoEmailCodigoConfirmacao(login))) {
                BeanUtil.criarMensagemDeAviso("Desculpe, ocorreu uma falha no sistema. ",
                        "Não foi possível concluir o cadastro, tente mais tarde.");
                return "";
            } else {
                List<Perfil> perfis = new ArrayList<Perfil>();
                perfis.add(perfilRN.obter(permissao));
                login.setPerfilList(perfis);
                if (!loginRN.salvar(login)) {
                    BeanUtil.criarMensagemDeErro("Ocorreu um erro ao salvar login", "Ocorreu um erro ao salvar login");

                } else {
                    if (!curriculoRN.salvar(curriculo)) {
                        BeanUtil.criarMensagemDeErro("Ocorreu um erro ao salvar curriculo", "Ocorreu um erro ao salvar curriculo");
                    }
                }

            }
            BeanUtil.criarMensagemDeAviso("Foi enviado para seu e-mail um código de confirmação de cadastro.",
                    "Quando for realizado o login será solicitado o código.");
            return "/loginInicio.xhtml";
        } else {
            BeanUtil.criarMensagemDeAviso("Email já existe!", "Email já existe");
            return "";
        }
    }

    public String salvar() {
        if (loginRN.existe(login.getEmail())) {
            configurarLimparSessao();
            pagina2 = "/publico/login/novoUsuario.xhtml";
            BeanUtil.criarMensagemDeAviso("Já existe um usuário cadastrado com esse e-mail.",
                    "");
        } else {
            configurarSalvarLogin();
        }
        configurarLimparSessao();
        return pagina2;
    }
    String pagina3 = "";

    public String alterarPermissao() {
        if (loginRN.existe(login.getEmail())) {
            pagina2 = "/publico/login/novoUsuario.xhtml";
            BeanUtil.criarMensagemDeAviso("Já existe um usuário cadastrado com esse e-mail.",
                    "");
        } else {
            configurarSalvarLogin();
        }
        configurarLimparSessao();
        return pagina2;
    }
//    String pagina3 = "";

    public String okCodigo() {

        if (login.getCodigoConfirmacaoTemp().equals(login.getCodigoConfirmacao())) {

            login.setCodigoConfirmacaoTemp(login.getCodigoConfirmacao());


            loginRN.salvar(login);

            pagina3 = "/publico/indexHome.xhtml";


        } else {
            BeanUtil.criarMensagemDeAviso("O código inserido está incorreto.", "");
            pagina3 = "/publico/login/telaConfirmacao.xhtml";
        }
        return pagina3;

    }

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        if (string.isEmpty()) {
            throw new UsernameNotFoundException(string);
        }
        Login temp = loginRN.obter(string);
        List<GrantedAuthority> papeis = new ArrayList<GrantedAuthority>();
        if (temp != null) {
            for (Perfil p : temp.getPerfilList()) {
                papeis.add(new GrantedAuthorityImpl(p.getDescricao()));
            }
            return new User(temp.getEmail(), temp.getSenha(), temp.getAtivo(), true, true, true, papeis);
        } else {
            throw new UsernameNotFoundException(string);
        }
    }

    public Login getUsuarioLogado() {
        FacesContext f = FacesContext.getCurrentInstance();
        ExternalContext e = f.getExternalContext();
        usuarioLogado = loginRN.obter(e.getRemoteUser());
        return usuarioLogado;
    }

    public void setUsuarioLogado(Login usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public boolean isMaster() {
        for (Perfil temp : getUsuarioLogado().getPerfilList()) {
            if (temp.getDescricao().equals("ROLE_MASTER")) {
                return true;
            }
        }
        return false;
    }
}
