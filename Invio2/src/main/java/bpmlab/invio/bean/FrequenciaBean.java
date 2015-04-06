package bpmlab.invio.bean;

import bpmlab.invio.bean.util.BeanUtil;
import bpmlab.invio.bean.util.UsuarioUtil;
import bpmlab.invio.entidade.Curriculo;
import bpmlab.invio.entidade.Frequencia;
import bpmlab.invio.entidade.Login;
import bpmlab.invio.rn.FrequenciaRN;
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
        return "/usuario/documentacao/listaFrequencias.xhtml";
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

}
