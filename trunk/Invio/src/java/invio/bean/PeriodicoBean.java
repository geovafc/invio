package invio.bean;

import invio.bean.util.BeanUtil;
import invio.bean.util.UsuarioUtil;
import invio.entidade.Curriculo;
import invio.entidade.Login;
import invio.entidade.Periodico;
import invio.rn.PeriodicoRN;
import invio.rn.pdf.QualisRN;
import invio.util.Upload;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author fabio & Mikael
 */
@ManagedBean
@RequestScoped
public class PeriodicoBean {

    private Periodico periodico = new Periodico();
    private final PeriodicoRN periodicoRN = new PeriodicoRN();

    public PeriodicoBean() {
    }

    public Periodico getPeriodico() {
        return periodico;
    }

    public void setPeriodico(Periodico periodico) {
        this.periodico = periodico;
    }

    public List<Periodico> getPeriodicosAtuais() {
        Curriculo curriculo = UsuarioUtil.obterUsuarioLogado().getCurriculo();
        return periodicoRN.obterPeriodicosAtuais(curriculo);
    }

    public List<Periodico> getPeriodicosPassados() {
        Curriculo curriculo = UsuarioUtil.obterUsuarioLogado().getCurriculo();
        return periodicoRN.obterPeriodicosPassados(curriculo);
    }

    public String upload() {
        BeanUtil.colocarNaSessao("periodicoUpload", periodico);
        return "/usuario/cadastro/curriculo/periodico/uploadPeriodico.xhtml";
    }

    public void uploadArquivoPeriodico(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        if (file != null) {
            String path = Upload.contextPath(file.getFileName());
            periodico = (Periodico) BeanUtil.lerDaSessao("periodicoUpload");
            periodico.setArquivo(path);
            boolean salvou = periodicoRN.salvar(periodico);
            boolean upload = Upload.copiarParaArquivos(file);

            if (upload && salvou) {
                BeanUtil.removerDaSessao("periodicoUpload");
                BeanUtil.criarMensagemDeInformacao("Sucesso!",
                        "O arquivo foi enviado.");
            } else {
                BeanUtil.criarMensagemDeErro("Erro!",
                        "O arquivo não foi enviado.");
            }
        }
    }

    public int getTotal() {
        int total = 0;

        for (Periodico p : getPeriodicosAtuais()) {
            total += p.getEstrato();
        }
        return total;
    }

    public void salvarPeriodico() {
        Login login = UsuarioUtil.obterUsuarioLogado();
        Curriculo curriculo = login.getCurriculo();
        if (curriculo == null) {
            BeanUtil.criarMensagemDeErro("Você ainda não possui Currículo",
                    "Por favor preencha seu currículo em 'Meu Currículo' -> 'Meu Perfil'");
        } else if (periodico.getTitulo() == null || periodico.getTitulo().trim().equals("")) {
            BeanUtil.criarMensagemDeErro(
                    "Erro ao salvar o Periódico.",
                    "Preencha o campo Título.");
        } else if (periodico.getAutores() == null || periodico.getAutores().trim().equals("")) {
            BeanUtil.criarMensagemDeErro(
                    "Erro ao salvar o Periódico.",
                    "Preencha o campo Autor.");
        } else if (periodico.getAno() == null || periodico.getAno().trim().equals("")) {
            BeanUtil.criarMensagemDeErro(
                    "Erro ao salvar o Periódico.",
                    "Preencha o campo Ano Publicação.");
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
    
    public String voltar() {
        return "/usuario/cadastro/curriculo/periodico/periodicos.xhtml";
    }
}
