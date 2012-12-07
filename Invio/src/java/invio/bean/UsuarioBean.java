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
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Junior
 */
@ManagedBean
@SessionScoped
public class UsuarioBean {

    LoginRN loginRN = new LoginRN();
    CurriculoRN curriculoRN = new CurriculoRN();
    List<Login> logins;
    Login login = new Login();
    Curriculo curriculo = new Curriculo();

    public UsuarioBean() {
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
        return "/publico/login/loginInicio.xhtml";
    }

    public String cancelarTelaConfirmacao() {

        configurarLimparSessao();
        return "/publico/login/loginInicio.xhtml";
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
                pagina = "/publico/login/loginInicio.xhtml";
                //login = null;
                BeanUtil.criarMensagemDeAviso("O e-mail ou a senha inserido está incorreto.",
                        "");
            }
        } else {
            setEntrar(false);
            pagina = "/publico/login/loginInicio.xhtml";
            //login = null;
            BeanUtil.criarMensagemDeAviso("O e-mail ou a senha inserido está incorreto.",
                    "");
        }

        if (entrar == true) {
            if (login.getCodigoConfimacaoTemp().equals("") && login.getCodigoConfirmacao().equals("123")) {
                pagina = "/publico/login/telaConfirmacao.xhtml";
            } else if (login.getCodigoConfimacaoTemp().equals(login.getCodigoConfirmacao())) {
                pagina = "/publico/indexHome.xhtml";
            }
        }

        return pagina;
    }

    public void configurarSalvalCurricoLogin() {
        curriculo.setBairro("");
        curriculo.setCelular("");
        curriculo.setCep("");
        curriculo.setCidade("");
        curriculo.setCurso("");
        curriculo.setEstado("");
        curriculo.setLattes("");
        curriculo.setLogradouro("");
        curriculo.setMatricula("");
        curriculo.setNumeroEnd("");
        curriculo.setPais("");
        curriculo.setTelefone("");
        curriculo.setDtNascimento(null);
        String emailLogin = login.getEmail();
        curriculo.setEmail(emailLogin);

        boolean salvou = curriculoRN.salvar(curriculo);

        if (salvou) {

            login.setCurriculoId(curriculo);
            login.setCodigoConfimacaoTemp("");
            login.setCodigoConfirmacao("123");
            login.setDtCriacao(null);// RECEBER DATA ATUAL DO BANCO DE DADOS
            loginRN.salvar(login);


            pagina2 = "/publico/login/loginInicio.xhtml";
            //login = null;
            BeanUtil.criarMensagemDeAviso("Foi enviado para seu e-mail um código de confirmação de cadastro.",
                    "Quando for realizado o login será solicitado o código.");
            configurarLimparSessao();
        }
    }
    
    
     ArrayList<String> tiposPerfil ;

    public ArrayList<String> getTipoPerfilUsuarioComum() {
        tiposPerfil = new ArrayList<String>();
        tiposPerfil.add("Docente");
        tiposPerfil.add("Discente");
        return tiposPerfil;
    }
    
    public ArrayList<String> getTipoPerfilAdmin() {
        tiposPerfil = new ArrayList<String>();
        tiposPerfil.add("Docente");
        tiposPerfil.add("Discente");
        tiposPerfil.add("Administrador");
        return tiposPerfil;
    }
    
    String pagina2 = "";
    boolean emailJaCadastrado = false;

    public void setEmailJaCadastrado(boolean emailJaCadastrado) {
        this.emailJaCadastrado = emailJaCadastrado;
    }

    public String salvar() {

        logins = loginRN.obterTodos();

        if (logins.size() > 0) {

            for (Login loginTemp2 : logins) {

                if (login.getEmail().equals(loginTemp2.getEmail())) {
                    setEmailJaCadastrado(true);
                }
            }

            if (emailJaCadastrado == true) {

                configurarLimparSessao();

                pagina2 = "/publico/login/novoUsuario.xhtml";
                BeanUtil.criarMensagemDeAviso("Já existe um usuário cadastrado com esse e-mail.",
                        "");
            } else {

                configurarSalvalCurricoLogin();

            }

        } else {

            configurarSalvalCurricoLogin();
        }

        configurarLimparSessao();
        return pagina2;
    }

    public String okCodigo() {

        if (login.getCodigoConfimacaoTemp().equals(login.getCodigoConfirmacao())) {

            login.setCodigoConfimacaoTemp(login.getCodigoConfirmacao());

            loginRN.salvar(login);

        } else {
            BeanUtil.criarMensagemDeAviso("O código inserido está incorreto.", "");
            return "/publico/login/telaConfirmacao.xhtml";
        }

        return "/publico/indexHome.xhtml";

    }
}
