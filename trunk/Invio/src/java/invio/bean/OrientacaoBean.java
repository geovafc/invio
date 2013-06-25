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
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author fabio
 */
@ManagedBean
@RequestScoped
public class OrientacaoBean {

    private Orientacao orientacao = new Orientacao();
    private OrientacaoRN orientacaoRN = new OrientacaoRN();
    private List<Orientacao> orientacoesAvaliado;
    public Integer estratoTemp;

    public OrientacaoBean() {
    }

    public Integer getEstratoTemp() {
        return estratoTemp;
    }

    public void setEstratoTemp(Integer estratoTemp) {
        this.estratoTemp = estratoTemp;
    }

    public Orientacao getOrientacao() {
        return orientacao;
    }

    public void setOrientacao(Orientacao orientacao) {
        this.orientacao = orientacao;
    }

    public List<Orientacao> getOrientacoes() {
        //TODO Orientações do curriculo indicado (login)
        return orientacaoRN.obterOrientacoes(UsuarioUtil.obterUsuarioLogado().getCurriculo());
    }

    public int getTotal() {
        int total = 0;

        for (Orientacao o : getOrientacoes()) {
            total += o.getEstrato();
        }

        return total;
    }

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

    public String salvarOrientacao() {
        //TODO Extrato é com X
        Login login = UsuarioUtil.obterUsuarioLogado();
        Curriculo curriculo = login.getCurriculo();

        if (orientacao.getTipoOrientacao() == 8) {
            orientacao.setEstrato(8);
        } else if (orientacao.getTipoOrientacao() == 4) {
            orientacao.setEstrato(4);
        } else {
            orientacao.setEstrato(2);
        }
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
        } else if (orientacao.getTipoOrientacao() != 8 && orientacao.getTipoOrientacao() != 4
                && orientacao.getTipoOrientacao() != 2) {
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
}
