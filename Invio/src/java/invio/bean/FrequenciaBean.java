package invio.bean;

import invio.bean.util.BeanUtil;
import invio.bean.util.UsuarioUtil;
import invio.entidade.Curriculo;
import invio.entidade.Frequencia;
import invio.entidade.Login;
import invio.rn.FrequenciaRN;
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
public class FrequenciaBean {

    private UploadArquivo fileUpload = new UploadArquivo();
    Frequencia frequencia = new Frequencia();
    FrequenciaRN frequenciaRN = new FrequenciaRN();

    public FrequenciaBean() {
        frequencia.setDataUpload(new Date());
    }

    public Frequencia getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(Frequencia frequencia) {
        this.frequencia = frequencia;
    }

    public List<Frequencia> getFrequencias() {
        return frequenciaRN.obterFrequencias(UsuarioUtil.obterUsuarioLogado().getCurriculo());
    }
    
    public String voltarListaFrequencia(){
        return "/documentacao/listaFrequencias.xhtml";
    }

    public String salvarFrequencia() {
        Login login = UsuarioUtil.obterUsuarioLogado();
        Curriculo curriculo = login.getCurriculo();

        if (curriculo == null) {
            BeanUtil.criarMensagemDeErro("Você ainda não possui Currículo",
                    "Por favor preencha seu currículo em 'Meu Currículo' -> 'Meu Perfil'");
        } else if (frequencia.getMes() == null
                || frequencia.getMes().trim().equals("")) {
            BeanUtil.criarMensagemDeErro("Erro ao salvar Frequencia.",
                    "Preencha o campo Mês.");
        }
        frequencia.setCurriculo(curriculo);
        if (frequenciaRN.salvar(frequencia)) {
            BeanUtil.criarMensagemDeInformacao(
                    "Operação realizada com sucesso",
                    "A Frequencia " + frequencia.getMes() + " foi salva com sucesso.");
        } else {
            BeanUtil.criarMensagemDeErro("Erro ao salvar a frequencia", "Frequencia: " + frequencia.getMes());
        }
        frequencia = new Frequencia();
        return null;
    }

    public String excluirFrequencia() {
        if (frequenciaRN.remover(frequencia)) {
            BeanUtil.criarMensagemDeInformacao("Frequencia excluída", "Frequencia do mês: " + frequencia.getMes());
        } else {
            BeanUtil.criarMensagemDeErro("Erro ao excluir Frequencia", "Frequencia do mês: " + frequencia.getMes());
        }

        frequencia = new Frequencia();
        return "listaFrequencias.xhtml";
    }

    public void uploadActionFrequencia(FileUploadEvent event) {
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
            String nomeDoArquivo = this.fileUpload.uploadFrequencia(UsuarioUtil.obterUsuarioLogado().getCurriculo(), frequencia, tipo, stream);
            this.frequencia.setLocalArquivo(nomeDoArquivo);
            frequenciaRN.salvar(frequencia);
            //Inicializa
            this.frequencia = new Frequencia();
            this.fileUpload = new UploadArquivo();
            BeanUtil.criarMensagemDeInformacao("O Arquivo foi salvo com sucesso. ", "Arquivo: " + nomeDoArquivo);
        } catch (IOException ex) {
        }
    }
}
