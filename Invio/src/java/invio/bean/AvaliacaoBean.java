package invio.bean;

import invio.bean.util.BeanUtil;
import invio.entidade.Curriculo;
import invio.entidade.Livro;
import invio.entidade.Orientacao;
import invio.entidade.Periodico;
import invio.rn.LivroRN;
import invio.rn.OrientacaoRN;
import invio.rn.PeriodicoRN;
import java.util.List;

/**
 *
 * @author soranso
 */
public class AvaliacaoBean {

    private List<Periodico> periodicosAvaliado;
    private List<Livro> livrosAvaliado;
    private List<Orientacao> orientacoesAvaliado;
    private Periodico periodico = new Periodico();
    private PeriodicoRN periodicoRN = new PeriodicoRN();
    Livro livro = new Livro();
    LivroRN livroRN = new LivroRN();
    private Orientacao orientacao = new Orientacao();
    private OrientacaoRN orientacaoRN = new OrientacaoRN();
    public Integer estratoTemp;
    Curriculo curriculo = new Curriculo();

    public Integer getEstratoTemp() {
        return estratoTemp;
    }

    public void setEstratoTemp(Integer estratoTemp) {
        this.estratoTemp = estratoTemp;
    }

    //Início Periódicos Lista e avaliação
    public List<Periodico> getPeriodicosAvaliado(Curriculo Curriculo) {
        if (periodicosAvaliado == null) {
            periodicosAvaliado = periodicoRN.obterTodosAvaliado(Curriculo);
        }
        return periodicosAvaliado;
    }

    public List<Periodico> getPeriodicosParaAvaliar(Curriculo Curriculo) {
        if (periodicosAvaliado == null) {
            periodicosAvaliado = periodicoRN.obterTodosParaAvaliar(Curriculo);
        }
        return periodicosAvaliado;
    }

    public String avaliarPeriodico() {

        if (periodico.getEstrato().equals(getEstratoTemp())) {
            periodico.setAvaliacao("Avaliado");
        } else if (periodico.getEstrato() < getEstratoTemp()) {
            periodico.setAvaliacao("Avaliado c/ Diferenças");
        } else if (periodico.getEstrato() > getEstratoTemp()) {
            periodico.setAvaliacao("Recusado pelo Comitê");
        }
        Curriculo curriculo = periodico.getCurriculo();
        periodico.setCurriculo(curriculo);
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
    public List<Livro> getLivrosAvaliado(Curriculo Curriculo) {
        livrosAvaliado = livroRN.obterTodosAvaliado(Curriculo);
        return livrosAvaliado;
    }

    public List<Livro> getLivrosParaAvaliar(Curriculo Curriculo) {
        livrosAvaliado = livroRN.obterTodosParaAvaliar(Curriculo);
        return livrosAvaliado;
    }
    
    public String avaliarLivro() {
        if (livro.getEstrato().equals(getEstratoTemp())) {
            livro.setAvaliacao("Avaliado");
        } else if (livro.getEstrato() < getEstratoTemp()) {
            livro.setAvaliacao("Avaliado c/ Diferenças");
        } else if (livro.getEstrato() > getEstratoTemp()) {
            livro.setAvaliacao("Recusado pelo Comitê");
        }
        Curriculo curriculo = livro.getCurriculo();
        livro.setCurriculo(curriculo);
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
    public List<Orientacao> getOrientacoesAvaliado(Curriculo Curriculo) {
        if (orientacoesAvaliado == null) {
            orientacoesAvaliado = orientacaoRN.obterTodosAvaliado(Curriculo);
        }
        return orientacoesAvaliado;
    }

    public List<Orientacao> getOrientacoesParaAvaliar(Curriculo Curriculo) {
        if (orientacoesAvaliado == null) {
            orientacoesAvaliado = orientacaoRN.obterTodosParaAvaliar(Curriculo);
        }
        return orientacoesAvaliado;
    }

    public String avaliarOrientacao() {
        if (orientacao.getEstrato().equals(getEstratoTemp())) {
            orientacao.setAvaliacao("Avaliado");
        } else if (orientacao.getEstrato() < getEstratoTemp()) {
            orientacao.setAvaliacao("Avaliado c/ Diferenças");
        } else if (orientacao.getEstrato() > getEstratoTemp()) {
            orientacao.setAvaliacao("Recusado pelo Comitê");
        }
        Curriculo curriculo = orientacao.getCurriculo();
        orientacao.setCurriculo(curriculo);
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

        return "faces/admin/listarAvaliar.xhtml";
    }

    public String pagListarAvaliado() {

        return "faces/admin/listarAvaliado.xhtml";
    }
}
