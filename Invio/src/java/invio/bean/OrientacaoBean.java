/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.bean;

import invio.bean.util.BeanUtil;
import invio.bean.util.UsuarioUtil;
import invio.entidade.Curriculo;
import invio.entidade.Login;
import invio.entidade.Orientacao;
import invio.rn.OrientacaoRN;
import invio.util.UploadArquivo;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author fabio
 */
@ManagedBean
@RequestScoped
public class OrientacaoBean {

    private Orientacao orientacao = new Orientacao();
    private OrientacaoRN orientacaoRN = new OrientacaoRN();
    private UploadArquivo fileUpload = new UploadArquivo();
    private Curriculo curriculo = new Curriculo();

    public OrientacaoBean() {
    }

    public Orientacao getOrientacao() {
        return orientacao;
    }

    public void setOrientacao(Orientacao orientacao) {
        this.orientacao = orientacao;
    }

    public List<Orientacao> getOrientacoesAtuais() {
        //TODO Orientações do curriculo indicado (login)
        return orientacaoRN.obterOrientacoesAtuais(UsuarioUtil.obterUsuarioLogado().getCurriculo());
    }
    
    public List<Orientacao> getOrientacoesPassadas() {
        //TODO Orientações do curriculo indicado (login)
        return orientacaoRN.obterOrientacoesPassadas(UsuarioUtil.obterUsuarioLogado().getCurriculo());
    }

    public int getTotal() {
        int total = 0;

        for (Orientacao o : getOrientacoesAtuais()) {
            total += o.getEstrato();
        }

        return total;
    }
    
    public String upload() {
        return "usuario/cadastro/curriculo/orientacao/uploadOrientacao.xhtml";
    }
    
    public Curriculo getCurriculo() {
        if (curriculo == null) {
            Login loginLog = UsuarioUtil.obterUsuarioLogado();
            if (loginLog != null) {
                curriculo = loginLog.getCurriculo();
            }
        }
        return curriculo;
    }

    public String salvarOrientacao() {
        //TODO Extrato é com X
        Login login = UsuarioUtil.obterUsuarioLogado();
        Curriculo curriculo = login.getCurriculo();

        orientacao.setEstrato(orientacaoRN.obterExtratoPorTipoOrientacao(orientacao.getTipoOrientacao()));

        if (curriculo == null) {
            BeanUtil.criarMensagemDeErro("Você ainda não possui Currículo",
                    "Por favor preencha seu currículo em 'Meu Currículo' -> 'Meu Perfil'");
        } else if (orientacao.getAluno() == null
                || orientacao.getAluno().trim().equals("")) {
            BeanUtil.criarMensagemDeErro("Erro ao salvar a Orientação.",
                    "Preencha o campo Nome Bolsista.");
            return null;
        } else if (orientacao.getPFinal() == null) {
            BeanUtil.criarMensagemDeErro("Erro ao salvar a Orientação.",
                    "Preencha o campo Período Final.");
            return null;
        } else if (orientacao.getTipoOrientacao() < 1 || orientacao.getTipoOrientacao() > 8) {
            BeanUtil.criarMensagemDeErro("Erro ao salvar a Orientação.",
                    "Selecione o campo Tipo de Orientação.");
        } else if (orientacao.getTipoBolsa() == null
                || orientacao.getTipoBolsa().trim().equals("")) {
            BeanUtil.criarMensagemDeErro("Erro ao salvar a Orientação.",
                    "Selecione o Tipo de Bolsa.");
        } else {
            orientacao.setCurriculo(curriculo);
            if (orientacaoRN.salvar(orientacao)) {
                BeanUtil.criarMensagemDeInformacao(
                        "Operação realizada com sucesso",
                        "A orientação do bolsista " + orientacao.getAluno() + " foi salva com sucesso.");
            } else {
                BeanUtil.criarMensagemDeErro("Erro ao salvar a orientação", "Orientação: " + orientacao.getAluno());
            }
        }
        orientacao = new Orientacao();
        return null;
    }

    public String salvarEditarOrientacao(Orientacao orientacaoTemp) {
        if (orientacaoRN.salvar(orientacaoTemp)) {
            BeanUtil.criarMensagemDeInformacao(
                    "Operação realizada com sucesso",
                    "A orientação do bolsista " + orientacaoTemp.getAluno() + " foi salva com sucesso.");
        } else {
            BeanUtil.criarMensagemDeErro("Erro ao salvar a orientação", "Orientação: " + orientacaoTemp.getAluno());
        }
        orientacao = new Orientacao();

        return null;
    }

    public String excluirOrientacao() {
        System.out.println("Orientação: " + orientacao);
        if (orientacaoRN.remover(orientacao)) {
            BeanUtil.criarMensagemDeInformacao(
                    "Orientação excluída",
                    "Orientação: " + orientacao.getAluno());
        } else {
            BeanUtil.criarMensagemDeErro(
                    "Erro ao excluir Orientação",
                    "Orientação: " + orientacao.getAluno());
        }
        orientacao = new Orientacao();
        return "orientacoes.xhtml";
    }

    public String obterTipo(Integer tipo) {
        return orientacaoRN.obterTipoOrientacao(tipo);
    }
    
    public void uploadActionOrientacao(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        InputStream stream = null;
        try {
            stream = file.getInputstream();
            String tipo = file.getContentType();
            if (tipo.equals("application/pdf")) {
                tipo = "pdf";
            } else if (tipo.equals("application/jpg")) {
                tipo = "jpg";
            }
            String nomeDoArquivo = this.fileUpload.uploadOrientacao(getCurriculo(), orientacao, tipo, stream);
            this.orientacao.setComprovante(nomeDoArquivo);
            orientacaoRN.salvar(orientacao);
            //Inicializa
            this.orientacao = new Orientacao();
            this.fileUpload = new UploadArquivo();
            BeanUtil.criarMensagemDeInformacao("O Arquivo foi salvo com sucesso. ", "Arquivo: " + nomeDoArquivo);
        } catch (IOException ex) {
        }
    }
}
