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
import java.util.ArrayList;
import java.util.List;

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
    
    public String irRecuperarSenha (){
        
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
        String pagina = "";
        
        logins = loginRN.obterTodos();


        if (logins != null || logins.size() >= 0) {

            for (Login loginTemp : logins) {

                if (loginTemp.getCurriculoId().getEmail().equals(login.getCurriculoId().getEmail())
                        && loginTemp.getSenha().equals(login.getSenha())) {
                    
                    setEntrar(true);
                }
            }
        } else {
            setAparecerMensagem(true);
            pagina= "/publico/login/loginInicio.xhtml";
        }

        if (entrar == true) {
            if (login.getCodigoConfimacaoTemp().equals("") && login.getCodigoConfirmacao().equals("123")) {
                pagina= "/publico/login/telaConfirmacao.xhtml";
            }
            else if(login.getCodigoConfirmacao() != null){
                pagina = "/publico/indexHome.xhtml"; 
            }
        }

        return pagina;
    }
    
    public String salvar(){
        
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
        
        
        if (curriculoRN.salvar(curriculo)) {
            
        }
        
        login.setCurriculoId(curriculo);
        login.setCodigoConfimacaoTemp("");
        login.setCodigoConfirmacao("123");
        loginRN.salvar(login);
        
        
        BeanUtil.criarMensagemDeAviso("Foi envia para seu e-mail um código de confirmação de cadastro.", 
                "Quando for realizado o login será solicitado o código.");
        
        return "/publico/login/loginInicio.xhtml";
    }
}
