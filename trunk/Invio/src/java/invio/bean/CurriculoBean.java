package invio.bean;

import invio.entidade.Area;
import invio.entidade.Curriculo;
import invio.entidade.Livro;
import invio.entidade.Login;
import invio.entidade.Orientacao;
import invio.entidade.Periodico;
import invio.entidade.Plano;
import invio.entidade.Programa;
import invio.rn.CurriculoRN;
import invio.rn.LivroRN;
import invio.rn.OrientacaoRN;
import invio.rn.PeriodicoRN;
import invio.rn.PlanoRN;
import invio.rn.pdf.QualisRN;
import invio.util.UploadArquivo;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
    private List<Curriculo> curriculosDesc;
    private Curriculo curriculo;
    private Login login;
    private static Logger logger = Logger.getLogger(Curriculo.class.getName());
    private boolean skip;
    private Periodico periodico = new Periodico();
    private PeriodicoRN periodicoRN = new PeriodicoRN();
    private Livro livro = new Livro();
    private LivroRN livroRN = new LivroRN();
    private Orientacao orientacao = new Orientacao();
    private OrientacaoRN orientacaoRN = new OrientacaoRN();
    private PlanoRN planoRN = new PlanoRN();
    private Plano plano = new Plano();
    private UploadArquivo fileUpload = new UploadArquivo();
    private UsuarioBean usuarioBean = new UsuarioBean();
    private QualisRN qualisRN = new QualisRN();
    private Programa programa = new Programa();
    private Area areaOutra = new Area();
    private boolean exibirOutroArea;

    public CurriculoBean(List<Curriculo> curriculos) {
        this.curriculos = curriculos;
    }

    public CurriculoBean() {
    }

    public List<Curriculo> getCurriculos() {
        if (usuarioBean.isMaster()) {
            curriculos = curriculoRN.obterTodos();
        } else {
            curriculos = curriculoRN.obterCurriculoLogin(usuarioBean.getUsuarioLogado());
        }
        return curriculos;
    }

    public List<Curriculo> getCurriculosDesc() {
        curriculos = curriculoRN.obterTodosDesc();
        return curriculosDesc;
    }

    public void setCurriculosDesc(List<Curriculo> curriculosDesc) {
        this.curriculosDesc = curriculosDesc;
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Integer getIdLogin() {

        return login.getId();
    }

    public void setCurriculos(List<Curriculo> curriculos) {
        this.curriculos = curriculos;
    }

//    public Curriculo getCurriculo() {
//        if (curriculo != null) {
//            return curriculo;
//        } else {
//            return (Curriculo) BeanUtil.lerDaSessao("curriculo");
//        }
//    }
    public void setCurriculo(Curriculo curriculo) {
        if (BeanUtil.lerDaSessao("curriculo") == null) {
            BeanUtil.colocarNaSessao("curriculo", curriculo);
        }
        this.curriculo = curriculo;
    }

    public String salvarCurriculo() {

        if (curriculoRN.salvar(getCurriculo())) {
            BeanUtil.criarMensagemDeInformacao(
                    "Operação realizada com sucesso",
                    "O curriculo de " + getCurriculo().getNome() + " foi gravado com sucesso.");

        } else {
            BeanUtil.criarMensagemDeErro("Erro ao salvar o curriculo", "Curriculo: " + getCurriculo().getNome());
        }
        return "listar.xhtml";
    }

    public String excluirCurriculo() {
        System.out.println("Curriculo " + getCurriculo());
        if (curriculoRN.remover(getCurriculo())) {
            BeanUtil.criarMensagemDeInformacao("Curriculo excluído", "Curriculo: " + getCurriculo().getNome());
        } else {
            BeanUtil.criarMensagemDeErro("Erro ao excluir o curriculo", "Curriculo: " + getCurriculo().getNome());
        }
        return "listarUsuarios.xhtml";
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
        setCurriculo(null);
        return "/cadastro/curriculo/listar.xhtml";
    }

    public String irListarUsuarios() {
        setCurriculo(null);
        return "/admin/usuarios/listarUsuarios.xhtml";
    }

    public String novoFormularioCurriculo() {
        setCurriculo(new Curriculo());

        UsuarioBean usuarioBeanTemp = (UsuarioBean) BeanUtil.lerDaSessao("usuarioBean");
        String email = usuarioBeanTemp.getUsuarioLogado().getEmail();

        curriculo.setEmail(email);

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
        if (periodico.getTitulo() == null || periodico.getTitulo().trim().equals("")) {
            BeanUtil.criarMensagemDeErro("Erro ao salvar o Periódico.", "Preencha o campo Título.");
            return null;
        } else if (periodico.getAutores() == null || periodico.getAutores().trim().equals("")) {
            BeanUtil.criarMensagemDeErro("Erro ao salvar o Periódico.", "Preencha o campo Autor.");
            return null;
        } else if (periodico.getAno() == null || periodico.getAno().trim().equals("")) {
            BeanUtil.criarMensagemDeErro("Erro ao salvar o Periódico.", "Preencha o campo Ano Publicação.");
            return null;
        } else {

            periodico.setCurriculo(getCurriculo());

//            curriculo.getPeriodicoList().add(periodico);
            if (periodicoRN.salvar(periodico)) {
                List<Periodico> ps = getCurriculo().getPeriodicoList();
                if (ps == null) {
                    ps = new ArrayList<Periodico>();
                    ps.add(periodico);
                }
                curriculoRN.salvar(getCurriculo());
                BeanUtil.criarMensagemDeInformacao(
                        "Operação realizada com sucesso",
                        "O periódico " + periodico.getTitulo() + " foi salvo com sucesso.");
            } else {
                BeanUtil.criarMensagemDeErro("Erro ao salvar o periódico", "Periódico: " + periodico.getTitulo());
            }
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


            String nomeDoArquivo = this.fileUpload.uploadPeriodico(getCurriculo(), periodico, tipo, stream);
            this.periodico.setArquivo(nomeDoArquivo);
            periodicoRN.salvar(periodico);
            //Inicializa
            this.periodico = new Periodico();
            this.fileUpload = new UploadArquivo();
            BeanUtil.criarMensagemDeInformacao("O Arquivo foi salvo com sucesso. ", "Arquivo: " + nomeDoArquivo);
        } catch (IOException ex) {
        }
    }

    public void uploadActionPlano(FileUploadEvent event) {
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
            String nomeDoArquivo = this.fileUpload.uploadPlano(getCurriculo(), plano, tipo, stream);
            this.plano.setTitulo(nomeDoArquivo);
            planoRN.salvar(plano);
            //Inicializa
            this.plano = new Plano();
            this.fileUpload = new UploadArquivo();
            BeanUtil.criarMensagemDeInformacao("O Arquivo foi salvo com sucesso. ", "Arquivo: " + nomeDoArquivo);
        } catch (IOException ex) {
        }
    }

    public String voltarListaPeriodico() {

        periodico = new Periodico();
        return "/cadastro/curriculo/producao/periodicos.xhtml";
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
        
        if (livro.getTipoLivro() == 30 && livro.getCapitulo() == null){
            livro.setEstrato(30);
        } else if (livro.getTipoLivro() == 10) {
            livro.setEstrato(10);
        } else {
            livro.setEstrato(20);
            livro.setCurriculo(getCurriculo());
            getCurriculo().getLivroList().add(livro);

            if (livroRN.salvar(livro)) {
                curriculoRN.salvar(getCurriculo());
                BeanUtil.criarMensagemDeInformacao(
                        "Operação realizada com sucesso",
                        "O Livro " + livro.getTitulo() + " foi salvo com sucesso.");
            } else {
                BeanUtil.criarMensagemDeErro("Erro ao salvar o livro", "Livro: " + livro.getTitulo());
            }
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
            String nomeDoArquivo = this.fileUpload.uploadLivro(getCurriculo(), livro, tipo, stream);
            this.livro.setArquivo(nomeDoArquivo);
            livroRN.salvar(livro);
            //Inicializa
            this.livro = new Livro();
            this.fileUpload = new UploadArquivo();
            BeanUtil.criarMensagemDeInformacao("O Arquivo foi salvo com sucesso. ", "Arquivo: " + nomeDoArquivo);
        } catch (IOException ex) {
        }

    }

    public String voltarListaLivro() {

        livro = new Livro();
        return "/cadastro/curriculo/producao/livros.xhtml";
    }

    public String atualizarValidacao() {

        List<Periodico> periodicos = getCurriculo().getPeriodicoList();
        List<Livro> livros = getCurriculo().getLivroList();
        List<Orientacao> orientacoes = getCurriculo().getOrientacaoList();

        for (Periodico periodicoAtual : periodicos) {
            periodicoRN.salvar(periodicoAtual);
        }

        for (Livro livroAtual : livros) {
            livroRN.salvar(livroAtual);
        }

        for (Orientacao orientacaoAtual : orientacoes) {
            orientacaoRN.salvar(orientacaoAtual);
        }

        return "/cadastro/curriculo/";
    }

    //CONTROLE DE ORIENTACAO A PARTIR DESTA LINHA
    //CONTROLE DE ORIENTACAO A PARTIR DESTA LINHA
    public Orientacao getOrientacao() {
        return orientacao;
    }

    public void setOrientacao(Orientacao orientacao) {
        this.orientacao = orientacao;
    }

    public String salvarOrientacao() {
        if (orientacao.getAluno() == null || orientacao.getAluno().trim().equals("")) {
            BeanUtil.criarMensagemDeErro("Erro ao salvar o Orientação.", "Preencha o campo Bolsista.");
            return null;
        } else if (orientacao.getTipoBolsa() == null || orientacao.getTipoBolsa().trim().equals("")) {
            BeanUtil.criarMensagemDeErro("Erro ao salvar o Tipo de Bolsa.", "Preencha o campo Tipo de Bolsa.");
            return null;
        } else {
            orientacao.setCurriculo(getCurriculo());

//            curriculo.getPeriodicoList().add(periodico);
            if (orientacaoRN.salvar(orientacao)) {
                List<Orientacao> lo = getCurriculo().getOrientacaoList();
                if (lo == null) {
                    lo = new ArrayList<Orientacao>();
                    lo.add(orientacao);
                }
                curriculoRN.salvar(getCurriculo());
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
            BeanUtil.criarMensagemDeInformacao("Orientação excluída", "Orientação do bolsista: " + orientacao.getAluno());
        } else {
            BeanUtil.criarMensagemDeErro("Erro ao excluir Orientação", "Orientação: " + orientacao.getAluno());
        }
        orientacao = new Orientacao();
        return "orientacoes.xhtml";
    }

    //DESTA LINHA PARA BAIXO ENCONTRA-SE O UPLOAD DE ORIENTACAO
    //DESTA LINHA PARA BAIXO ENCONTRA-SE O UPLOAD DE ORIENTACAO
//    public void uploadActionOrientacao(FileUploadEvent event) {
//        UploadedFile file = event.getFile();
//        InputStream stream = null;
//        try {
//            stream = file.getInputstream();
//            String tipo = file.getContentType();
//            if (tipo.equals("application/pdf")) {
//                tipo = "pdf";
//            } else if (tipo.equals("application/jpg")) {
//                tipo = "jpg";
//            }
//
//
//            String nomeDoArquivo = this.fileUpload.uploadOrientacao(getCurriculo(), orientacao, tipo, stream);
//            this.orientacao.setArquivo(nomeDoArquivo);
//            orientacaoRN.salvar(orientacao);
//            //Inicializa
//            this.orientacao = new Orientacao();
//            this.fileUpload = new UploadArquivo();
//            BeanUtil.criarMensagemDeInformacao("O Arquivo foi salvo com sucesso. ", "Arquivo: " + nomeDoArquivo);
//        } catch (IOException ex) {
//        }
//    }
    public List<String> complete(String query) {
        List<String> results = qualisRN.obterTodosTitulos(query);

        return results;
    }

    public List<String> completeArea(String query) {
        List<String> results = qualisRN.obterTodosTitulosArea(query);
        return results;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public void salvarProgramaCurriculo() {
        getCurriculo().getArea().getProgramaList().add(programa);
        curriculoRN.salvar(getCurriculo());
    }

    /**
     * @return the curriculo
     */
    public Curriculo getCurriculo() {
        return curriculo;
    }

    public void exibirTabOutroArea() {
        if (curriculo != null) {
            if (curriculo.getArea().equals(areaOutra)) {
                setExibirOutroArea(true);
            } else {
                setExibirOutroArea(false);
            }
        }
    }

    /**
     * @return the exibirOutroArea
     */
    public boolean isExibirOutroArea() {
        return exibirOutroArea;
    }

    /**
     * @param exibirOutroArea the exibirOutroArea to set
     */
    public void setExibirOutroArea(boolean exibirOutroArea) {
        this.exibirOutroArea = exibirOutroArea;
    }

    /**
     * @return the areaOutra
     */
    public Area getAreaOutra() {
        return areaOutra;
    }

    /**
     * @param areaOutra the areaOutra to set
     */
    public void setAreaOutra(Area areaOutra) {
        this.areaOutra = areaOutra;
    }
}