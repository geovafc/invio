package invio.bean;

import invio.entidade.Instituicao;
import invio.entidade.Unidade;
import invio.rn.InstituicaoRN;
import invio.rn.UnidadeRN;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class InstituicaoBean {

    private InstituicaoRN instituicaoRN = new InstituicaoRN();
    private List<Instituicao> instituicoes;
    private Instituicao instituicao;// = new Instituicao();
    private UnidadeRN unidadeRN = new UnidadeRN();
    private Unidade unidade; //= new Unidade();
    private List<Unidade> unidades;

    public InstituicaoBean(List<Instituicao> instituicoes, Unidade unidade) {
        this.instituicoes = instituicoes;
        this.unidade = unidade;
    }

    public InstituicaoBean() {
    }

    public List<Instituicao> getInstituicoes() {
        //    if (instituicoes == null) {
        instituicoes = instituicaoRN.obterTodos();
        //  }
        return instituicoes;
    }

    public void setInstituicoes(List<Instituicao> instituicoes) {
        this.instituicoes = instituicoes;
    }

//    public Instituicao getInstituicao() {
//        return instituicao;
//    }
    public Instituicao getInstituicao() {
        if (instituicao != null) {
            return instituicao;
        } else {
            return (Instituicao) BeanUtil.lerDaSessao("instituicao");
        }
    }

//    public void setInstituicao(Instituicao instituicao) {
//        System.out.println("setInstituicao " + instituicao);
//        this.instituicao = instituicao;
//    }
    public void setInstituicao(Instituicao instituicao) {
        if (BeanUtil.lerDaSessao("instituicao") == null) {
            BeanUtil.colocarNaSessao("instituicao", instituicao);
        }
        this.instituicao = instituicao;
    }

    public String salvarInstituicao() {
        if (instituicaoRN.salvar(instituicao)) {
            BeanUtil.criarMensagemDeInformacao(
                    "Operação realizada com sucesso",
                    "A Instituição " + instituicao.getNome() + " foi gravada com sucesso.");

        } else {
            BeanUtil.criarMensagemDeErro("Erro ao salvar a Instituição", "Instituição: " + instituicao.getNome());
        }

        return "/cadastro/instituicao/listar.xhtml";
    }

    public String excluirInstituicao() {
        System.out.println("Instituição " + instituicao);
        if (instituicaoRN.remover(instituicao)) {
            BeanUtil.criarMensagemDeInformacao("Instituição excluída", "Instituição: " + instituicao.getNome());
        } else {
            BeanUtil.criarMensagemDeErro("Erro ao excluir a Instituição", "Instituição: " + instituicao.getNome());
        }
        return "listar.xhtml";
    }
    
    public String irListarInstituicoes() {
        instituicao = null;

        return "/cadastro/instituicao/formularioUnidade.xhtml";
    }
    
    public String novoFormularioInstituicao() {

        instituicao = new Instituicao();
        return "/cadastro/instituicao/formulario.xhtml";
    }

    
    // CONTROLE DE UNIDADE APARTIR DESSA LINHA
    // CONTROLE DE UNIDADE APARTIR DESSA LINHA
    
    
    public String salvarUnidade() {
        unidade.setInstituicao(instituicao);

        System.out.println("Unidade 1 :" + unidade + "Instituição 1: " + instituicao);
        if (unidadeRN.salvar(unidade)) {
            BeanUtil.criarMensagemDeInformacao(
                    "Operação realizada com sucesso",
                    "A Unidade " + unidade.getNome() + " foi gravada com sucesso.");
        } else {
            BeanUtil.criarMensagemDeErro("Erro ao salvar a Unidade", "Unidade: " + unidade.getNome());
        }

        unidades = null;
        return "listarUnidades.xhtml";
    }

    public String excluirUnidade() {
        System.out.println("Unidade: " + unidade);
        if (unidadeRN.remover(unidade)) {
            BeanUtil.criarMensagemDeInformacao("Unidade excluída", "Unidade: " + unidade.getNome());
        } else {
            BeanUtil.criarMensagemDeErro("Erro ao excluir Unidade", "Unidade: " + unidade.getNome());
        }
        return "listarUnidades.xhtml";
    }

    public String irEditarUnidade() {

        System.out.println("Unidade 1 :" + unidade + "Instituição 1: " + instituicao);
        return "/cadastro/instituicao/formularioUnidade.xhtml";
    }

    public String novoFormularioUnidade() {

        unidade = new Unidade();
        return "/cadastro/instituicao/formularioUnidade.xhtml";
    }

    

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public List<Unidade> getUnidades() {

//        if (instituicao != null) {
        unidades = unidadeRN.obTerUnidades(instituicao);
//        } else {
//            return null; //TODO Retornar lista vazia
//        }

        return unidades;
    }
}