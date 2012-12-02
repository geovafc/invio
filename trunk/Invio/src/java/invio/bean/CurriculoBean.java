package invio.bean;

import invio.entidade.Curriculo;
import invio.rn.CurriculoRN;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;

@ManagedBean
@SessionScoped
public class CurriculoBean {

    private CurriculoRN curriculoRN = new CurriculoRN();
    private List<Curriculo> curriculos;
    private Curriculo curriculo;
    private static Logger logger = Logger.getLogger(Curriculo.class.getName());

    public CurriculoBean(List<Curriculo> curriculos) {
        this.curriculos = curriculos;
    }

    public CurriculoBean() {
    }

    public List<Curriculo> getCurriculos() {
        //    if (instituicoes == null) {
        curriculos = curriculoRN.obterTodos();
        //  }
        return curriculos;
    }

    public void setCurriculos(List<Curriculo> curriculos) {
        this.curriculos = curriculos;
    }

    public Curriculo getCurriculo() {
        if (curriculo != null) {
            return curriculo;
        } else {
            return (Curriculo) BeanUtil.lerDaSessao("curriculo");
        }
    }

    public void setCurriculo(Curriculo curriculo) {
        if (BeanUtil.lerDaSessao("curriculo") == null) {
            BeanUtil.colocarNaSessao("curriculo", curriculo);
        }
        this.curriculo = curriculo;
    }
    public String salvarCurriculo() {
        if (curriculoRN.salvar(curriculo)) {
            BeanUtil.criarMensagemDeInformacao(
                    "Operação realizada com sucesso",
                    "O curriculo " + curriculo.getNome() + " foi gravado com sucesso.");

        } else {
            BeanUtil.criarMensagemDeErro("Erro ao salvar o curriculo", "Curriculo: " + curriculo.getNome());
        }
        return "/cadastro/curriculo/listar.xhtml";
    }

    public String excluirCurriculo() {
        System.out.println("Curriculo " + curriculo);
        if (curriculoRN.remover(curriculo)) {
            BeanUtil.criarMensagemDeInformacao("Curriculo excluído", "Curriculo: " + curriculo.getNome());
        } else {
            BeanUtil.criarMensagemDeErro("Erro ao excluir o curriculo", "Curriculo: " + curriculo.getNome());
        }
        return "listar.xhtml";
    }

    
    public String onFlowProcess(FlowEvent event) {
        logger.info("Current wizard step:" + event.getOldStep());  
        logger.info("Next step:" + event.getNewStep()); 
        System.out.println("id " + event.getNewStep());
//        if (event.getNewStep().equals("confirm")) {
//            carg = funcionario.getCargosID();
//            empres = funcionario.getEmpresaID();
//        }
        return event.getNewStep();

    }
    public String irListarCurriculos() {
        curriculo = null;

        return "/cadastro/curriculo/listar.xhtml";
    }

    public String novoFormularioCurriculo() {

        curriculo = new Curriculo();
        return "/cadastro/curriculo/wizard.xhtml";
    }
}
