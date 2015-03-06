/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bpmlab.invio.util.javamail;

import bpmlab.invio.entidade.Login;

public class TextoEmail {

    public static String getTextoEmailCodigoConfirmacao(Login login) {
        return "Olá " + login.getEmail() + "," + "\n\n\n"
                + "Sua solicitação de cadastro foi registrada com sucesso em nosso sistema.\n\n\n"
//                + "Para concluir o processo, insira o código de confirmação abaixo quando for exigido.\n\n"
//                + "Código de Confirmação: " + login.getCodigoConfirmacao() + "\n\n\n"
                + "Se você não fez essa solicitação de cadastro no Sistema Invio, por favor desconsidere esse e-mail.\n\n"
                + "Para mais informações, entre em contato com o atendimento do Sistema Invio pelo email: pibic@ufra.edu.br.\n\n\n"
                + "ATENÇÃO: essa mensagem é enviada automaticamente, não responda para este e-mail.";

    }

    public static String getTextoEmailRecuperacaoSenha(Login login, String senha) {
        return "Olá " + login.getEmail() + "," + "\n\n"
                + "Recebemos sua solicitação de recuperação de senha de acesso. \n\n"
                + "Sua senha é: " + senha + "\n\n"
                + "Se você não fez essa solicitação ao sistema Invio, por favor desconsidere esse e-mail.\n\n"
                + "Para mais informações, entre em contato com o atendimento do Sistema Invio pelo email: pibic@ufra.edu.br.\n\n\n"
                + "ATENÇÃO: essa mensagem é enviada automaticamente, não responda para este e-mail.";
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
                + "\t\t\tDownload do JavaMail<br/>\n" + "\t\t\t<a href='http://www.oracle.com/technetwork/java/javaee/index-138643.html'>\n"
                + "\t\t\t\t<img src ='http://www.oracleimg.com/admin/images/ocom/hp/oralogo_small.gif'/>\n"
                + "\t\t\t</a> \n"
                + "\t\t</div>\t\t\n"
                + "\t</body> \n"
                + "</html>";
    }
}
