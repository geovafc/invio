package invio.bean;

import invio.entidade.Instituicao;
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
            UtilBean.criarMensagemDeInformacao(
                    "Operação realizada com sucesso",
                    "A Instituição " + instituicao.getNome() + " foi gravada com sucesso.");
        }
        return "listar.xhtml";
    }

    public String excluir() {
        System.out.println("Instituição " + instituicao);
        if (instituicaoRN.remover(instituicao)) {
            UtilBean.criarMensagemDeInformacao("Instituição excluída", "Instituição: " + instituicao.getNome());
        } else {
            UtilBean.criarMensagemDeErro("Erro ao excluir a Instituição", "Instituição: " + instituicao.getNome());
        }
        return "listar.xhtml";
    }
}