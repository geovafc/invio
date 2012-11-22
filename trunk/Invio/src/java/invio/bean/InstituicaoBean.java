package invio.bean;

import invio.entidade.Instituicao;
import invio.entidade.Unidade;
import invio.rn.InstituicaoRN;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class InstituicaoBean {

   private InstituicaoRN instituicaoRN = new InstituicaoRN();
    private List<Instituicao> instituicoes;
    private Instituicao instituicao = new Instituicao();
    private Unidade unidade;
    

    public InstituicaoBean(List<Instituicao> instituicoes, Unidade unidade) {
        this.instituicoes = instituicoes;
        this.unidade = unidade;
    }
    
    
    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public InstituicaoBean() {
    }

    public List<Instituicao> getInstituicoes() {
        if (instituicoes == null) {
            instituicoes = instituicaoRN.obterTodos();
        }
        return instituicoes;
    }

    public void setInstituicoes (List<Instituicao> instituicoes) {
        this.instituicoes = instituicoes;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao (Instituicao instituicao) {
        System.out.println("setInstituicao " + instituicao);
        this.instituicao = instituicao;
    }

    public String salvar() {
        if (instituicaoRN.salvar(instituicao)) {
            BeanUtil.criarMensagemDeInformacao(
                    "Operação realizada com sucesso",
                    "A Instituição " + instituicao.getNome() + " foi gravada com sucesso.");
        }else {
            BeanUtil.criarMensagemDeErro("Erro ao salvar a Instituição", "Instituição: " + instituicao.getNome());
        }
        return "listar.xhtml";
    }

    public String excluir() {
        System.out.println("Instituição " + instituicao);
        if (instituicaoRN.remover(instituicao)) {
            BeanUtil.criarMensagemDeInformacao("Instituição excluída", "Instituição: " + instituicao.getNome());
        } else {
            BeanUtil.criarMensagemDeErro("Erro ao excluir a Instituição", "Instituição: " + instituicao.getNome());
        }
        return "listar.xhtml";
    }
    
    
}