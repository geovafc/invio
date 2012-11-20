/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.bean;

import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author RSORANSO
 */
public class BeanUtil {

    public static void criarMensagemDeInformacao(String resumo, String detalhe) {
        criarMensagem(FacesMessage.SEVERITY_INFO, resumo, detalhe);
    }

    public static void criarMensagemDeAviso(String resumo, String detalhe) {
        criarMensagem(FacesMessage.SEVERITY_WARN, resumo, detalhe);
    }

    public static void criarMensagemDeErro(String resumo, String detalhe) {
        criarMensagem(FacesMessage.SEVERITY_ERROR, resumo, detalhe);
    }

    private static void criarMensagem(FacesMessage.Severity tipo, String resumo, String detalhe) {
        FacesContext currentInstance = FacesContext.getCurrentInstance();
        FacesMessage fm = new FacesMessage(resumo, detalhe);
        fm.setSeverity(tipo);
        currentInstance.addMessage(null, fm);
    }
    
    public static Object lerDaSessao(String chave) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = ec.getSessionMap();
        return sessionMap.get(chave);
    }
    
    public static void colocarNaSessao(String chave, Object objeto) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = ec.getSessionMap();
        sessionMap.put(chave, objeto);
    }
    
    public static void removerDaSessao(String chave) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = ec.getSessionMap();
        sessionMap.remove(chave);
    }
}
