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

    public void configurarEnviarEmail(Login login) {
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
        ajm.setSubjectMail("Confirmação de registro de e-mail");
        ajm.setBodyMail(textMessage(login));
        ajm.setTypeTextMail(AtributosJavaMail.TYPE_TEXT_PLAIN);
//sete quantos destinatarios desejar
        Map<String, String> map = new HashMap<String, String>();
        map.put(login.getEmail(), login.getCurriculoId().getNome());
        ajm.setToMailsUsers(map);
//seta quatos anexos desejar
        
        /* ANEXAR ARQUIVOS
        List<String> files = new ArrayList<String>();
        files.add("C:\\images\\invio.png");
        ajm.setFileMails(files);
        */
        
        try {
            new ConfiguracaoJavaMail().senderMail(ajm);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private static String textMessage(Login login) {
        return "Olá "+login.getCurriculoId().getNome()+","+"\n\n"+
"Sua solicitação de cadastro foi registrada com sucesso em nosso sistema.\n"+ 
"Para concluir o processo, insira o código de confirmação abaixo quando for exigido.\n\n"+
"Código de Confirmação: "+login.getCodigoConfirmacao()+"\n\n\n"+
"Se você não fez essa solicitação de cadastro no sistema Invio, por favor desconsidere esse e-mail.\n\n"+
"Para mais informações, entre em contato com o atendimento Invio.";
    }

    private static String htmlMessage(Login login) {
        //EXEMPLO:
        return "<html>\n"
                + "\t<head>\n"
                + "\t\t<title>Email no formato HTML com Javamail!</title> \n"
                + "\t</head>\n"
                + "\t<body>\n"
                + "\t\t<div style='background-color:orange; width:28%; height:100px;'>\n"
                + "\t\t\t<ul>\n"
                + "\t\t\t<p>Visite o site \n"
                + "\t\t\t\t<a href='http://www.pibic.ufra.edu.br/2012/'>Pibic 2012</a>\n"
                + "\t\t\t</p>\n"
                + "\t\t</div>\t\n"
                + "\t\t<div style='width:28%; height:50px;' align='center'>\n"
                + "\t\t\tDownload do JavaMail<br/>\n"+"\t\t\t<a href='http://www.oracle.com/technetwork/java/javaee/index-138643.html'>\n"+
                "\t\t\t\t<img src ='http://www.oracleimg.com/admin/images/ocom/hp/oralogo_small.gif'/>\n" +
                "\t\t\t</a> \n"
                + "\t\t</div>\t\t\n"
                + "\t</body> \n"
                + "</html>";
    }
}
