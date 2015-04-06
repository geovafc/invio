package bpmlab.invio.bean;

import bpmlab.invio.bean.util.BeanUtil;
import bpmlab.invio.entidade.Curriculo;
import bpmlab.invio.rn.AvaliacaoRN;
import bpmlab.invio.rn.CurriculoRN;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author Mikael & Toshiaki
 */
@ManagedBean
@ViewScoped
public class AvaliacaoBean implements Serializable {

    private final AvaliacaoRN rn = new AvaliacaoRN();
    private Curriculo curriculo;
    private Object producao;
    private Integer estratoTemp;
    private boolean skip;
    private List<Curriculo> curriculos;

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public Curriculo getCurriculo() {
        return curriculo;
    }

    public void setCurriculo(Curriculo curriculo) {
        this.curriculo = curriculo;
    }

    public Object getProducao() {
        return producao;
    }

    public void setProducao(Object producao) {
        this.producao = producao;
    }
    
    public Integer getEstratoTemp() {
        return estratoTemp;
    }

    public void setEstratoTemp(Integer estratoTemp) {
        this.estratoTemp = estratoTemp;
    }

    public String pagListarAvaliar() {
        return "/secretaria/listarAvaliar.xhtml";
    }

    public String pagListarAvaliado() {
        return "/secretaria/listarAvaliado.xhtml";
    }

    public List<Curriculo> autoComplete() {
        return null;
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public List<Curriculo> autoCompleteCurriculo(String busca) {
        return rn.autoCompleteCurriculo(busca);
    }

    public boolean possuiArquivo(String arquivo) {
        return rn.possueArquivo(arquivo);
    }

    public void confirmar() {
        if (rn.confirmar(curriculo, producao)) {
            BeanUtil.criarMensagemDeInformacao("Sucesso", "A produção foi avaliada");
        } else {
            BeanUtil.criarMensagemDeErro("Erro", "Não foi possível confirmar produção");
        }
    }
    
    public void corrigir() {
        if (rn.corrigir(curriculo, producao, estratoTemp)) {
            BeanUtil.criarMensagemDeInformacao("Sucesso", "A produção foi corrigida");
        } else {
            BeanUtil.criarMensagemDeErro("Erro", "Não foi possível corrigir produção");
        }
    }

    public void negar() {
        if (rn.negar(curriculo, producao)) {
            BeanUtil.criarMensagemDeInformacao("Sucesso", "A produção foi negada");
        } else {
            BeanUtil.criarMensagemDeErro("Erro", "Não foi possível negar a produção");
        }
    }

    public List<Curriculo> getCurriculos() {
        curriculos = new CurriculoRN().obterTodosOrdenado();
        return curriculos;
    }
    
}