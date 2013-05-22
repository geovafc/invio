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

    /**
     * Creates a new instance of OrientacaoBean
     */
    public OrientacaoBean() {
    }

    public Orientacao getOrientacao() {
        return orientacao;
    }

    public void setOrientacao(Orientacao orientacao) {
        this.orientacao = orientacao;
    }

    public List<Orientacao> getOrientacoes() {
        //TODO Orientações do curriculo indicado (login)
        return orientacaoRN.obterTodos();
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
        if (orientacao.getAluno() == null
                || orientacao.getAluno().trim().equals("")) {
            BeanUtil.criarMensagemDeErro("Erro ao salvar a Orientação.",
                    "Preencha o campo Bolsista.");
            return null;
        } else if (orientacao.getTipoBolsa() == null
                || orientacao.getTipoBolsa().trim().equals("")) {
            BeanUtil.criarMensagemDeErro("Erro ao salvar o Tipo de Bolsa.",
                    "Preencha o campo Tipo de Bolsa.");
            return null;
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
