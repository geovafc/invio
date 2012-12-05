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
    boolean aparecerMensagem = false;

    public boolean isAparecerMensagem() {
        return aparecerMensagem;
    }

    public void setAparecerMensagem(boolean aparecerMensagem) {
        this.aparecerMensagem = aparecerMensagem;
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


        if (logins != null || logins.size() > 0) {

            for (Login loginTemp : logins) {

                if (loginTemp.getCurriculoId().getEmail().equals(login.getCurriculoId().getEmail())
                        && loginTemp.getSenha().equals(login.getSenha())) {

                    setEntrar(true);
                    loginEncontrado = true;
                }
            }
            if (loginEncontrado != true) {
                pagina = "/publico/login/loginInicio.xhtml";
                BeanUtil.criarMensagemDeAviso("O e-mail ou a senha inserido está incorreto.",
                        "");
            }
        } else {
            setAparecerMensagem(true);
            setEntrar(false);
            pagina = "/publico/login/loginInicio.xhtml";
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

    public String salvar() {

        //FAZEER TRATAMENTO: SE EXISTIR ALGUM E-MAIL PARECIDO NO BANCO
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
        

        boolean salvou = curriculoRN.salvar(curriculo);   
        
        if (salvou) {

            login.setCurriculoId(curriculo);
            login.setCodigoConfimacaoTemp("");
            login.setCodigoConfirmacao("123");
            login.setDtCriacao(null);// RECEBER DATA ATUAL DO BANCO DE DADOS
            loginRN.salvar(login);


            BeanUtil.criarMensagemDeAviso("Foi envia para seu e-mail um código de confirmação de cadastro.",
                    "Quando for realizado o login será solicitado o código.");
        }



        return "/publico/login/loginInicio.xhtml";
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
