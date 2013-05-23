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
import invio.util.UploadArquivo;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author fabio
 */
@ManagedBean
@RequestScoped
public class LivroBean {

    private UploadArquivo fileUpload = new UploadArquivo();
    Livro livro = new Livro();
    LivroRN livroRN = new LivroRN();

    public LivroBean() {
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public List<Livro> getLivros() {
        return livroRN.obterLivros(UsuarioUtil.obterUsuarioLogado().getCurriculo());
    }

    public String salvarLivro() {
        Login login = UsuarioUtil.obterUsuarioLogado();
        Curriculo curriculo = login.getCurriculo();

        if (livro.getTitulo() == null
                || livro.getTitulo().trim().equals("")) {
            BeanUtil.criarMensagemDeErro("Erro ao salvar Livro.",
                    "Preencha o campo Título.");
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
//    public void uploadActionLivro(FileUploadEvent event) {
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
//            String nomeDoArquivo = this.fileUpload.uploadLivro(getCurriculo(), livro, tipo, stream);
//            this.livro.setArquivo(nomeDoArquivo);
//            livroRN.salvar(livro);
//            //Inicializa
//            this.livro = new Livro();
//            this.fileUpload = new UploadArquivo();
//            BeanUtil.criarMensagemDeInformacao("O Arquivo foi salvo com sucesso. ", "Arquivo: " + nomeDoArquivo);
//        } catch (IOException ex) {
//        }
//
//    }
}
