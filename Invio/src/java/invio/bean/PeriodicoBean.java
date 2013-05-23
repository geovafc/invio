/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.bean;

import invio.bean.util.BeanUtil;
import invio.bean.util.UsuarioUtil;
import invio.entidade.Curriculo;
import invio.entidade.Login;
import invio.entidade.Periodico;
import invio.rn.PeriodicoRN;
import invio.rn.pdf.QualisRN;
import invio.util.UploadArquivo;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author fabio
 */
@ManagedBean
@RequestScoped
public class PeriodicoBean {

    private UploadArquivo fileUpload = new UploadArquivo();
    private Periodico periodico = new Periodico();
    private PeriodicoRN periodicoRN = new PeriodicoRN();

    public PeriodicoBean() {
    }

    public Periodico getPeriodico() {
        return periodico;
    }

    public void setPeriodico(Periodico periodico) {
        this.periodico = periodico;
    }

    public List<Periodico> getPeriodicos() {
        return periodicoRN.obterPeriodicos(UsuarioUtil.obterUsuarioLogado().getCurriculo());
    }

    public String salvarPeriodico() {
        Login login = UsuarioUtil.obterUsuarioLogado();
        Curriculo curriculo = login.getCurriculo();
        if (periodico.getTitulo() == null || periodico.getTitulo().trim().equals("")) {
            BeanUtil.criarMensagemDeErro(
                    "Erro ao salvar o Periódico.", 
                    "Preencha o campo Título.");
            return null;
        } else if (periodico.getAutores() == null || periodico.getAutores().trim().equals("")) {
            BeanUtil.criarMensagemDeErro(
                    "Erro ao salvar o Periódico.", 
                    "Preencha o campo Autor.");
            return null;
        } else if (periodico.getAno() == null || periodico.getAno().trim().equals("")) {
            BeanUtil.criarMensagemDeErro(
                    "Erro ao salvar o Periódico.", 
                    "Preencha o campo Ano Publicação.");
            return null;
        } else {
            periodico.setCurriculo(curriculo);

            if (periodicoRN.salvar(periodico)) {
                BeanUtil.criarMensagemDeInformacao(
                        "Operação realizada com sucesso",
                        "O periódico " + periodico.getTitulo() + " foi salvo com sucesso.");
            } else {
                BeanUtil.criarMensagemDeErro("Erro ao salvar o periódico", "Periódico: " + periodico.getTitulo());
            }
        }
        periodico = new Periodico();
        return null;
    }

    public String excluirPeriodico() {
        System.out.println("Periodico: " + periodico);
        if (periodicoRN.remover(periodico)) {
            BeanUtil.criarMensagemDeInformacao("Periódico excluído",
                    "Periódico: " + periodico.getTitulo());
        } else {
            BeanUtil.criarMensagemDeErro("Erro ao excluir Periódico",
                    "Periódico: " + periodico.getTitulo());
        }
        periodico = new Periodico();
        return "periodicos.xhtml";
    }

    public String salvarEditarPeriodico(Periodico periodicoTemp) {
        if (periodicoRN.salvar(periodicoTemp)) {
            BeanUtil.criarMensagemDeInformacao(
                    "Operação realizada com sucesso",
                    "A orientação do bolsista " + periodicoTemp.getTitulo()
                    + " foi salva com sucesso.");
        } else {
            BeanUtil.criarMensagemDeErro("Erro ao salvar a orientação",
                    "Orientação: " + periodicoTemp.getTitulo());
        }
        periodico = new Periodico();
        return null;
    }

    public List<String> complete(String query) {
        QualisRN qualisRN = new QualisRN();
        return qualisRN.obterTodosTitulos(query);
    }
//    public void uploadActionPeriodico(FileUploadEvent event) {
//        UploadedFile file = event.getFile();
//        InputStream stream = null;
//        try {
//            stream = file.getInputstream();
//            String tipo = file.getContentType();
//            if (tipo.equals("application/pdf")) {
//                tipo = "pdf";
//            } else if (tipo.equals("application/jpg")) {
//                tipo = "jpg";
//            }
//            String nomeDoArquivo = this.fileUpload.uploadPeriodico(getCurriculo(), periodico, tipo, stream);
//            this.periodico.setArquivo(nomeDoArquivo);
//            periodicoRN.salvar(periodico);
//            //Inicializa
//            this.periodico = new Periodico();
//            this.fileUpload = new UploadArquivo();
//            BeanUtil.criarMensagemDeInformacao("O Arquivo foi salvo com sucesso. ", "Arquivo: " + nomeDoArquivo);
//        } catch (IOException ex) {
//        }
//    }
}