/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.rn;

import invio.entidade.Login;
import invio.util.javamail.AtributosJavaMail;
import invio.util.javamail.ConfiguracaoJavaMail;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.mail.MessagingException;



public class JavaMailRN {
    
    private boolean falhaAoEnviarEmail = false;

    public boolean isFalhaAoEnviarEmail() {
        return falhaAoEnviarEmail;
    }

    public void setFalhaAoEnviarEmail(boolean falhaAoEnviarEmail) {
        this.falhaAoEnviarEmail = falhaAoEnviarEmail;
    }
    
    
    public boolean configurarEnviarEmail(Login login,String tituloEmail, String textoEmail) {
        AtributosJavaMail ajm = new AtributosJavaMail();
//configuracoes de envio
        
        ajm.setSmtpHostMail("smtp.gmail.com");
        ajm.setSmtpPortMail("587");
        ajm.setSmtpAuth("true");
        ajm.setSmtpStarttls("true");
        ajm.setUserMail("sistema.invio@gmail.com");
        ajm.setFromNameMail("Sistema Invio");
        ajm.setPassMail("pibic2012");
        ajm.setCharsetMail("UTF-8");
        ajm.setSubjectMail(tituloEmail);
        ajm.setBodyMail(textoEmail);
        ajm.setTypeTextMail(AtributosJavaMail.TYPE_TEXT_PLAIN);
//sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();
        map.put(login.getEmail(), login.getCurriculoId().getNome());
        ajm.setToMailsUsers(map);
        
        try {
            new ConfiguracaoJavaMail().senderMail(ajm);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            setFalhaAoEnviarEmail(true);
            e.printStackTrace();
        }
        System.out.println("*Falha ao envia e-mail:: "+falhaAoEnviarEmail);
        return falhaAoEnviarEmail;
    }

}
