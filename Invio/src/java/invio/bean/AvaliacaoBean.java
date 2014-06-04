package invio.bean;

import invio.bean.util.BeanUtil;
import invio.entidade.Curriculo;
import invio.entidade.Livro;
import invio.entidade.Orientacao;
import invio.entidade.Periodico;
import invio.rn.AvaliacaoRN;
import invio.rn.LivroRN;
import invio.rn.OrientacaoRN;
import invio.rn.PeriodicoRN;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author soranso
 */
@ManagedBean
@ViewScoped
public class AvaliacaoBean {

    private final AvaliacaoRN rn = new AvaliacaoRN();
    public Integer estratoTemp;
    private Curriculo curriculo;
    private boolean skip;

    //Periodicos
    private List<Periodico> periodicosAvaliado;
    private Periodico periodico = new Periodico();
    private final PeriodicoRN periodicoRN = new PeriodicoRN();
    private List<Periodico> periodicos;
    //Livros
    private List<Livro> livrosAvaliado;
    private Livro livro = new Livro();
    private final LivroRN livroRN = new LivroRN();
    private List<Livro> livros;
    //Orientções
    private List<Orientacao> orientacoesAvaliado;
    private Orientacao orientacao = new Orientacao();
    private final OrientacaoRN orientacaoRN = new OrientacaoRN();
    private List<Orientacao> orientacoes;

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
        System.out.println(curriculo.getPeriodicoList());
        System.out.println(curriculo.getOrientacaoList());
        System.out.println(curriculo.getLivroList());
        this.curriculo = curriculo;
    }

    public Integer getEstratoTemp() {
        return estratoTemp;
    }

    public void setEstratoTemp(Integer estratoTemp) {
        this.estratoTemp = estratoTemp;
    }

    //Início Periódicos Lista e avaliação
    public List<Periodico> getPeriodicos() {
        periodicos = periodicoRN.obterTodos();
        return periodicos;
    }

    public List<Periodico> getPeriodicosAvaliado(Curriculo Curriculo) {
        if (periodicosAvaliado == null) {
            periodicosAvaliado = periodicoRN.obterTodosAvaliado(Curriculo);
        }
        return periodicosAvaliado;
    }

    public List<Periodico> getPeriodicosParaAvaliar() {
        periodicosAvaliado = periodicoRN.obterTodosParaAvaliar(curriculo);
        return periodicosAvaliado;
    }

    @Deprecated
    public String avaliarPeriodico() {
        if (periodico.getEstrato().equals(getEstratoTemp())) {
            periodico.setAvaliacao("Avaliado");
        } else if (periodico.getEstrato() < getEstratoTemp()
                || periodico.getEstrato() > getEstratoTemp()) {
            periodico.setAvaliacao("Avaliado c/ Diferenças");
        } else if (periodico.getEstrato().equals(getEstratoTemp().equals(0))) {
            periodico.setAvaliacao("Recusado pelo Comitê");
        }
        Curriculo curriculoA = periodico.getCurriculo();
        periodico.setCurriculo(curriculoA);
        if (periodicoRN.salvar(periodico)) {
            BeanUtil.criarMensagemDeInformacao(
                    "Operação realizada com sucesso",
                    "O periódico " + periodico.getTitulo() + " foi avaliado.");
        } else {
            BeanUtil.criarMensagemDeErro("Erro ao avaliar o periódico: ", "" + periodico.getTitulo());
        }
        return null;
    }

    //Fim de periódicos lista e avaliação
    //Início Livro lista e avaliação
    public List<Livro> getLivros() {
        livros = livroRN.obterLivrosAtuais(curriculo);
        return livros;
    }

    public List<Livro> getLivrosAvaliado(Curriculo Curriculo) {
        livrosAvaliado = livroRN.obterTodosAvaliado(Curriculo);
        return livrosAvaliado;
    }

    public List<Livro> getLivrosParaAvaliar() {
        livrosAvaliado = livroRN.obterTodosParaAvaliar(curriculo);
        return livrosAvaliado;
    }

    @Deprecated
    public String avaliarLivro() {
        if (livro.getEstrato().equals(getEstratoTemp())) {
            livro.setAvaliacao("Avaliado");
        } else if (livro.getEstrato() < getEstratoTemp()) {
            livro.setAvaliacao("Avaliado c/ Diferenças");
        } else if (livro.getEstrato() > getEstratoTemp()) {
            livro.setAvaliacao("Recusado pelo Comitê");
        }
        Curriculo c = livro.getCurriculo();
        livro.setCurriculo(c);
        if (livroRN.salvar(livro)) {
            BeanUtil.criarMensagemDeInformacao(
                    "Operação realizada com sucesso",
                    "O Livro " + livro.getTitulo() + " foi avaliado.");
        } else {
            BeanUtil.criarMensagemDeErro("Erro ao avaliar o livro: ", "" + livro.getTitulo());
        }
        return null;
    }
    //Fim Livro lista e avaliação
    //Início Orientações lista e avaliação

    public List<Orientacao> getOrientacoes() {
        orientacoes = orientacaoRN.obterTodos();
        return orientacoes;
    }

    public List<Orientacao> getOrientacoesAvaliado(Curriculo Curriculo) {
        if (orientacoesAvaliado == null) {
            orientacoesAvaliado = orientacaoRN.obterTodosAvaliado(Curriculo);
        }
        return orientacoesAvaliado;
    }

    public List<Orientacao> getOrientacoesParaAvaliar() {
        orientacoesAvaliado = orientacaoRN.obterTodosParaAvaliar(curriculo);
        return orientacoesAvaliado;
    }

    @Deprecated
    public String avaliarOrientacao() {
        if (orientacao.getEstrato().equals(getEstratoTemp())) {
            orientacao.setAvaliacao("Avaliado");
        } else if (orientacao.getEstrato() < getEstratoTemp()) {
            orientacao.setAvaliacao("Avaliado c/ Diferenças");
        } else if (orientacao.getEstrato() > getEstratoTemp()) {
            orientacao.setAvaliacao("Recusado pelo Comitê");
        }
        Curriculo c = orientacao.getCurriculo();
        orientacao.setCurriculo(c);
        if (orientacaoRN.salvar(orientacao)) {
            BeanUtil.criarMensagemDeInformacao(
                    "Operação realizada com sucesso",
                    "A orientação " + orientacao.getAluno() + " foi avaliada.");
        } else {
            BeanUtil.criarMensagemDeErro("Erro ao avaliar a orientação: ", "" + orientacao.getAluno());
        }
        orientacao = new Orientacao();
        return null;
    }
    //Fim Orientações lista e avaliação

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

    public boolean possueArquivoLivro(Livro livro) {
        return rn.possueArquivoLivro(livro);
    }
    
    public boolean possueArquivoPeriodico(Periodico periodico) {
        return rn.possueArquivoPeriodico(periodico);
    }
    
    public boolean possueArquivoOrientacao(Orientacao orientacao) {
        return rn.possueArquivoOrientacao(orientacao);
    }
    
}
