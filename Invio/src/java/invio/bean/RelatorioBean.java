package invio.bean;

import invio.bean.util.BeanUtil;
import invio.bean.util.UsuarioUtil;
import invio.entidade.Curriculo;
import invio.entidade.Relatorio;
import invio.entidade.Login;
import invio.rn.RelatorioRN;
import invio.util.UploadArquivo;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@RequestScoped
public class RelatorioBean {

    private UploadArquivo fileUpload = new UploadArquivo();
    Relatorio relatorio = new Relatorio();
    RelatorioRN relatorioRN = new RelatorioRN();

    public RelatorioBean() {
        relatorio.setDataUpload(new Date());
    }

    public Relatorio getRelatorio() {
        return relatorio;
    }

    public void setRelatorio(Relatorio relatorio) {
        this.relatorio = relatorio;
    }

    public List<Relatorio> getRelatorios() {
        return relatorioRN.obterRelatorios(UsuarioUtil.obterUsuarioLogado().getCurriculo());
    }
    
    public String voltarListaRelatorio(){
        return "/documentacao/listaRelatorios.xhtml";
    }

    public String salvarRelatorio() {
        Login login = UsuarioUtil.obterUsuarioLogado();
        Curriculo curriculo = login.getCurriculo();

        if (curriculo == null) {
            BeanUtil.criarMensagemDeErro("Você ainda não possui Currículo",
                    "Por favor preencha seu currículo em 'Meu Currículo' -> 'Meu Perfil'");
        } else if (relatorio.getMes() == null
                || relatorio.getMes().trim().equals("")) {
            BeanUtil.criarMensagemDeErro("Erro ao salvar Relatório.",
                    "Preencha o campo Mês.");
        } else if (relatorio.getTipo() == null
                || relatorio.getTipo().trim().equals("")) {
            BeanUtil.criarMensagemDeErro("Erro ao salvar Relatório.",
                    "Preencha o campo Tipo de Relatório.");
        }
        relatorio.setCurriculo(curriculo);
        if (relatorioRN.salvar(relatorio)) {
            BeanUtil.criarMensagemDeInformacao(
                    "Operação realizada com sucesso",
                    "O Relatório " + relatorio.getMes() + " foi salvo com sucesso.");
        } else {
            BeanUtil.criarMensagemDeErro("Erro ao salvar o relatório", "Relatório: " + relatorio.getTipo());
        }
        relatorio = new Relatorio();
        return null;
    }

    public String excluirRelatorio() {
        if (relatorioRN.remover(relatorio)) {
            BeanUtil.criarMensagemDeInformacao("Relatóprio excluído", "Relatório: " + relatorio.getTipo());
        } else {
            BeanUtil.criarMensagemDeErro("Erro ao excluir Relatório", "Relatório: " + relatorio.getTipo());
        }
        relatorio = new Relatorio();
        return "listaRelatorios.xhtml";
    }

    public void uploadActionRelatorio(FileUploadEvent event) {
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
            String nomeDoArquivo = this.fileUpload.uploadRelatorio(UsuarioUtil.obterUsuarioLogado().getCurriculo(), relatorio, tipo, stream);
            this.relatorio.setLocalArquivo(nomeDoArquivo);
            relatorioRN.salvar(relatorio);
            //Inicializa
            this.relatorio = new Relatorio();
            this.fileUpload = new UploadArquivo();
            BeanUtil.criarMensagemDeInformacao("O Arquivo foi salvo com sucesso. ", "Arquivo: " + nomeDoArquivo);
        } catch (IOException ex) {
        }
    }
}
