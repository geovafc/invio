package bpmlab.invio.bean;

import bpmlab.invio.bean.util.BeanUtil;
import bpmlab.invio.bean.util.UsuarioUtil;
import bpmlab.invio.entidade.Curriculo;
import bpmlab.invio.entidade.Login;
import bpmlab.invio.entidade.Periodico;
import bpmlab.invio.rn.CurriculoRN;
import bpmlab.invio.rn.PeriodicoRN;
import bpmlab.invio.rn.pdf.QualisRN;
import bpmlab.invio.util.ArquivoUtil;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author fabio & Mikael
 */
@ManagedBean
@RequestScoped
public class PeriodicoBean {

    private Periodico periodico = new Periodico();
    private final PeriodicoRN periodicoRN = new PeriodicoRN();

    public PeriodicoBean() {
    }

    public Periodico getPeriodico() {
        return periodico;
    }

    public void setPeriodico(Periodico periodico) {
        this.periodico = periodico;
    }

    public List<Periodico> getPeriodicosAtuais() {
        Curriculo curriculo = UsuarioUtil.obterUsuarioLogado().getCurriculo();
        return periodicoRN.obterPeriodicosAtuais(curriculo);
    }

    public List<Periodico> getPeriodicosPassados() {
        Curriculo curriculo = UsuarioUtil.obterUsuarioLogado().getCurriculo();
        return periodicoRN.obterPeriodicosPassados(curriculo);
    }

    public String upload() {
        BeanUtil.colocarNaSessao("periodicoUpload", periodico);
        return "/usuario/cadastro/curriculo/periodico/uploadPeriodico.xhtml";
    }

    public void uploadArquivoPeriodico(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        if (file != null) {
            String path = ArquivoUtil.contextPath(file.getFileName());
            periodico = (Periodico) BeanUtil.lerDaSessao("periodicoUpload");
            periodico.setArquivo(path);
            boolean salvou = periodicoRN.salvar(periodico);
            boolean upload = ArquivoUtil.copiarParaArquivos(file);

            if (upload && salvou) {
                BeanUtil.removerDaSessao("periodicoUpload");
                BeanUtil.criarMensagemDeInformacao("Sucesso!",
                        "O arquivo foi enviado.");
            } else {
                BeanUtil.criarMensagemDeErro("Erro!",
                        "O arquivo nÃ£o foi enviado.");
            }
        }
    }

    public int getTotal() {
        int total = 0;

        for (Periodico p : getPeriodicosAtuais()) {
            total += p.getEstrato();
        }
        return total;
    }

    public void salvarPeriodico() {
        Login login = UsuarioUtil.obterUsuarioLogado();
        Curriculo curriculo = login.getCurriculo();
        if (curriculo == null) {
            BeanUtil.criarMensagemDeErro("VocÃª ainda nÃ£o possui CurrÃ­culo",
                    "Por favor preencha seu currÃ­culo em 'Meu CurrÃ­culo' -> 'Meu Perfil'");
        } else if (periodico.getTitulo() == null || periodico.getTitulo().trim().equals("")) {
            BeanUtil.criarMensagemDeErro(
                    "Erro ao salvar o PeriÃ³dico.",
                    "Preencha o campo TÃ­tulo.");
        } else if (periodico.getAutores() == null || periodico.getAutores().trim().equals("")) {
            BeanUtil.criarMensagemDeErro(
                    "Erro ao salvar o PeriÃ³dico.",
                    "Preencha o campo Autor.");
        } else if (periodico.getAno() == null || periodico.getAno().trim().equals("")) {
            BeanUtil.criarMensagemDeErro(
                    "Erro ao salvar o PeriÃ³dico.",
                    "Preencha o campo Ano PublicaÃ§Ã£o.");
        } else {
            periodico.setCurriculo(curriculo);
            periodico.setArquivo("");
            
            if (periodicoRN.salvar(periodico)) {
                if (curriculo.getPeriodicoList() == null) {
                    curriculo.setPeriodicoList(new ArrayList<Periodico>());
                }
                curriculo.getPeriodicoList().add(periodico);
                if (curriculo.getFco() == null) {
                    curriculo.setFco(0);
                }
                curriculo.setFco(curriculo.getFco() + periodico.getEstrato());
                new CurriculoRN().salvar(curriculo);
                
                BeanUtil.criarMensagemDeInformacao(
                        "OperaÃ§Ã£o realizada com sucesso",
                        "O periÃ³dico " + periodico.getTitulo() + " foi salvo com sucesso.");
            } else {
                BeanUtil.criarMensagemDeErro("Erro ao salvar o periÃ³dico", "PeriÃ³dico: " + periodico.getTitulo());
            }
        }
        periodico = new Periodico();
    }

    public void excluirPeriodico() {
        Login login = UsuarioUtil.obterUsuarioLogado();
        Curriculo curriculo = login.getCurriculo();
        if (periodicoRN.remover(periodico)) {
            curriculo.setFco(curriculo.getFco() - periodico.getEstrato());
            curriculo.getPeriodicoList().remove(periodico);
            new CurriculoRN().salvar(curriculo);
            BeanUtil.criarMensagemDeInformacao(
                    "Sucesso",
                    "Login excluÃ­do");
        } else {
            BeanUtil.criarMensagemDeErro(
                    "Erro",
                    "NÃ£o foi possÃ­vel excluir o login");
        }
    }

//    public String excluirPeriodico() {
//        System.out.println("Periodico: " + periodico);
//        if (periodicoRN.remover(periodico)) {
//            BeanUtil.criarMensagemDeInformacao("PeriÃ³dico excluÃ­do",
//                    "PeriÃ³dico: " + periodico.getTitulo());
//        } else {
//            BeanUtil.criarMensagemDeErro("Erro ao excluir PeriÃ³dico",
//                    "PeriÃ³dico: " + periodico.getTitulo());
//        }
//        periodico = new Periodico();
//        return "periodicos.xhtml";
//    }
    public String salvarEditarPeriodico(Periodico periodicoTemp) {
        if (periodicoRN.salvar(periodicoTemp)) {
            BeanUtil.criarMensagemDeInformacao(
                    "OperaÃ§Ã£o realizada com sucesso",
                    "A orientaÃ§Ã£o do bolsista " + periodicoTemp.getTitulo()
                    + " foi salva com sucesso.");
        } else {
            BeanUtil.criarMensagemDeErro("Erro ao salvar a orientaÃ§Ã£o",
                    "OrientaÃ§Ã£o: " + periodicoTemp.getTitulo());
        }
        periodico = new Periodico();
        return null;
    }

    public List<String> complete(String query) {
        QualisRN qualisRN = new QualisRN();
        return qualisRN.obterTodosTitulos(query);
    }

    public String voltar() {
        return "/usuario/cadastro/curriculo/periodico/periodicos.xhtml";
    }
}