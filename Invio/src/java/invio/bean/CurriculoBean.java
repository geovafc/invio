package invio.bean;

import invio.entidade.Curriculo;
import invio.entidade.Livro;
import invio.entidade.Login;
import invio.entidade.Periodico;
import invio.rn.CurriculoRN;
import invio.rn.LivroRN;
import invio.rn.PeriodicoRN;
import invio.util.UploadArquivo;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@SessionScoped
public class CurriculoBean {

    private CurriculoRN curriculoRN = new CurriculoRN();
    private List<Curriculo> curriculos;
    private Curriculo curriculo;
    private Login login;
    private static Logger logger = Logger.getLogger(Curriculo.class.getName());
    private boolean skip;
    private Periodico periodico = new Periodico();
    private PeriodicoRN periodicoRN = new PeriodicoRN();
    private LivroRN livroRN = new LivroRN();
    private Livro livro = new Livro();
    private UploadArquivo fileUpload = new UploadArquivo();

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

    public Integer getIdLogin() {

        return login.getId();
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
                    "O curriculo de " + curriculo.getNome() + " foi gravado com sucesso.");

        } else {
            BeanUtil.criarMensagemDeErro("Erro ao salvar o curriculo", "Curriculo: " + curriculo.getNome());
        }
        return "listar.xhtml";
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

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public String onFlowProcess(FlowEvent event) {
        logger.info("Current wizard step:" + event.getOldStep());
        logger.info("Next step:" + event.getNewStep());
        if (skip) {
            skip = false;
            return "confirm";
        } else {
            return event.getNewStep();

        }

    }

    public String irListarCurriculos() {
        curriculo = null;

        return "/cadastro/curriculo/listar.xhtml";
    }

    public String novoFormularioCurriculo() {

        curriculo = new Curriculo();
        return "/cadastro/curriculo/wizard.xhtml";
    }

    //CONTROLE DE PERIODICO A PARTIR DESTA LINHA
    //CONTROLE DE PERIODICO A PARTIR DESTA LINHA
    public Periodico getPeriodico() {
        return periodico;
    }

    public void setPeriodico(Periodico periodico) {
        this.periodico = periodico;
    }

    public String salvarPeriodico() {
        periodico.setCurriculoId(curriculo);
        curriculo.getPeriodicoList().add(periodico);
        if (periodicoRN.salvar(periodico)) {
            curriculoRN.salvar(curriculo);
            BeanUtil.criarMensagemDeInformacao(
                    "Operação realizada com sucesso",
                    "O periódico " + periodico.getTitulo() + " foi salvo com sucesso.");
        } else {
            BeanUtil.criarMensagemDeErro("Erro ao salvar o periódico", "Periódico: " + periodico.getTitulo());
        }
        periodico = new Periodico();

        return null;
    }

    public String salvarEditarPeriodico(Periodico periodicoTemp) {

        if (periodicoRN.salvar(periodicoTemp)) {
            BeanUtil.criarMensagemDeInformacao(
                    "Operação realizada com sucesso",
                    "O Periódico " + periodicoTemp.getTitulo() + " foi salvo com sucesso.");
        } else {
            BeanUtil.criarMensagemDeErro("Erro ao salvar o periódico", "Periódico: " + periodicoTemp.getTitulo());
        }
        periodico = new Periodico();

        return null;
    }

    public String excluirPeriodico() {
        System.out.println("Periodico: " + periodico);
        if (periodicoRN.remover(periodico)) {
            BeanUtil.criarMensagemDeInformacao("Periódico excluído", "Periódico: " + periodico.getTitulo());
        } else {
            BeanUtil.criarMensagemDeErro("Erro ao excluir Periódico", "Periódico: " + periodico.getTitulo());
        }

        periodico = new Periodico();
        return "periodicos.xhtml";
    }

    public void uploadActionPeriodico(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        InputStream stream = null;
        try {
            stream = file.getInputstream();
            String tipo = file.getContentType();
            if (tipo.equals("application/pdf")) {
                tipo = "pdf";
            } else if (tipo.equals("application/jpg")) {
                tipo = "jpg";
            }


            String nomeDoArquivo = this.fileUpload.uploadPeriodico(curriculo, periodico, tipo, stream);
            this.periodico.setArquivo(nomeDoArquivo);
            periodicoRN.salvar(periodico);
            //Inicializa
            this.periodico = new Periodico();
            this.fileUpload = new UploadArquivo();
        } catch (IOException ex) {
        }
    }

    //CONTROLE DE LIVRO APARTIR DESTA LINHA
    //CONTROLE DE LIVRO APARTIR DESTA LINHA
    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public String salvarLivro() {
        livro.setCurriculoId(curriculo);
        curriculo.getLivroList().add(livro);
        if (livro.getTitulo() == null || livro.getTitulo().trim().equals("")) {
            BeanUtil.criarMensagemDeErro("Erro ao salvar o livro", "Livro: " + livro.getTitulo());
            return null;
        }
        if (livroRN.salvar(livro)) {
            curriculoRN.salvar(curriculo);
            BeanUtil.criarMensagemDeInformacao(
                    "Operação realizada com sucesso",
                    "O Livro " + livro.getTitulo() + " foi salvo com sucesso.");
        } else {
            BeanUtil.criarMensagemDeErro("Erro ao salvar o livro", "Livro: " + livro.getTitulo());
        }
        livro = new Livro();

        return null;
    }

    public String salvarEditarLivro(Livro livroTemp) {

        if (livroRN.salvar(livroTemp)) {
            BeanUtil.criarMensagemDeInformacao(
                    "Operação realizada com sucesso",
                    "O Livro " + livroTemp.getTitulo() + " foi salvo com sucesso.");
        } else {
            BeanUtil.criarMensagemDeErro("Erro ao salvar o livro", "Livro: " + livroTemp.getTitulo());
        }
        livro = new Livro();

        return null;
    }

    public String excluirLivro() {
        System.out.println("Livro: " + livro);
        if (livroRN.remover(livro)) {
            BeanUtil.criarMensagemDeInformacao("Livro excluído", "Livro: " + livro.getTitulo());
        } else {
            BeanUtil.criarMensagemDeErro("Erro ao excluir Livro", "Livro: " + livro.getTitulo());
        }

        livro = new Livro();
        return "livros.xhtml";
    }

    public void uploadActionLivro(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        InputStream stream = null;
        try {
            stream = file.getInputstream();
            String tipo = file.getContentType();
            if (tipo.equals("application/pdf")) {
                tipo = "pdf";
            } else if (tipo.equals("application/jpg")) {
                tipo = "jpg";
            }


            String nomeDoArquivo = this.fileUpload.uploadLivro(curriculo, livro, tipo, stream);
            this.livro.setArquivo(nomeDoArquivo);
            livroRN.salvar(livro);
            //Inicializa
            this.livro = new Livro();
            this.fileUpload = new UploadArquivo();
        } catch (IOException ex) {
        }

    }
}
