/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.bean;

import invio.bean.util.BeanUtil;
import invio.bean.util.UsuarioUtil;
import invio.entidade.Curriculo;
import invio.entidade.Livro;
import invio.entidade.Login;
import invio.rn.LivroRN;
import invio.util.Upload;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author fabio & Mikael Lima
 */
@ManagedBean
@RequestScoped
public class LivroBean {

    Livro livro = new Livro();
    LivroRN livroRN = new LivroRN();

    public LivroBean() {
        livro.setTipoLivro(30);
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public List<Livro> getLivrosAtuais() {
        return livroRN.obterLivrosAtuais(UsuarioUtil.obterUsuarioLogado().getCurriculo());
    }

    public List<Livro> getLivrosPassados() {
        return livroRN.obterLivrosPassados(UsuarioUtil.obterUsuarioLogado().getCurriculo());
    }

    public int getTotal() {
        int total = 0;

        for (Livro l : getLivrosAtuais()) {
            total += l.getEstrato();
        }

        return total;
    }

    public String salvarLivro() {
        Login login = UsuarioUtil.obterUsuarioLogado();
        Curriculo curriculo = login.getCurriculo();

        if (curriculo == null) {
            BeanUtil.criarMensagemDeErro("Você ainda não possui Currículo",
                    "Por favor preencha seu currículo em 'Meu Currículo' -> 'Meu Perfil'");
        } else if (livro.getTitulo() == null
                || livro.getTitulo().trim().equals("")) {
            BeanUtil.criarMensagemDeErro("Erro ao salvar Livro.",
                    "Preencha o campo Título.");
        } else if (livro.getTipoLivro() != 30 && livro.getTipoLivro() != 10) {
            BeanUtil.criarMensagemDeErro("Erro ao salvar Livro.", "Selecione o campo Autor ou Editor.");
        } else if (livro.getAutor() == null || livro.getAutor().trim().equals("")) {
            BeanUtil.criarMensagemDeErro("Erro ao salvar Livro.", "Preencha o campo Autor.");
        } else if (livro.getAno() == null || livro.getAno().trim().equals("")) {
            BeanUtil.criarMensagemDeErro("Erro ao salvar Livro.", "Preencha o campo Ano de Publicação.");
        } else {

            if (livro.getTipoLivro() == 30 && livro.getCapitulo().equals("")) {
                livro.setEstrato(30);
            } else if (livro.getTipoLivro() == 30 && livro.getCapitulo() != null) {
                livro.setEstrato(20);
            } else if (livro.getTipoLivro() == 10) {
                livro.setEstrato(10);
            }
            livro.setCurriculo(curriculo);
            if (livroRN.salvar(livro)) {
                BeanUtil.criarMensagemDeInformacao(
                        "Operação realizada com sucesso",
                        "O Livro " + livro.getTitulo() + " foi salvo com sucesso.");
            } else {
                BeanUtil.criarMensagemDeErro("Erro ao salvar o livro", "Livro: " + livro.getTitulo());
            }
        }
        livro = new Livro();
        livro.setTipoLivro(30);
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
    
    public String upload() {
        BeanUtil.colocarNaSessao("livroUpload", livro);
        return "/usuario/cadastro/curriculo/livro/uploadLivro.xhtml";
    }
    
    public String voltar() {
        return "/usuario/cadastro/curriculo/livro/livros.xhtml";
    }
    
    public void uploadArquivoLivro(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        if (file != null) {
            String path = Upload.contextPath(file.getFileName());
            livro = (Livro) BeanUtil.lerDaSessao("livroUpload");
            livro.setArquivo(path);
            boolean salvou = livroRN.salvar(livro);
            boolean upload = Upload.copiarParaArquivos(file);

            if (upload && salvou) {
                BeanUtil.removerDaSessao("livroUpload");
                BeanUtil.criarMensagemDeInformacao("Sucesso!",
                        "O arquivo foi enviado.");
            } else {
                BeanUtil.criarMensagemDeErro("Erro!",
                        "O arquivo não foi enviado.");
            }
        }
    }
}
