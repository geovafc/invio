/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.util;

import invio.entidade.Curriculo;
import invio.entidade.Frequencia;
import invio.entidade.Livro;
import invio.entidade.Periodico;
import invio.entidade.Plano;
import invio.entidade.Relatorio;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Junior
 */
public class UploadArquivo {

    private String diretorio;
    private String caminho;
    private byte[] arquivo;
    private File arquivo2;
    private String nome;

    public UploadArquivo() {
    }

    public String getDiretorio() {
        return this.diretorio;
    }

    public void setDiretorio(String diretorio) {
        this.diretorio = diretorio;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRealPath() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        FacesContext aFacesContext = FacesContext.getCurrentInstance();
        ServletContext context = (ServletContext) aFacesContext.getExternalContext().getContext();

        return context.getRealPath("/cadastro/curriculo/producao");
    }

    public String uploadLivro(Curriculo curriculo, 
            Livro livro, 
            String tipo, 
            InputStream stream) {
        String nomeDoArquivo = livro.getId() + livro.getTitulo()+"."+ tipo;
        upload(curriculo, ConfiguracaoUtil.TipoProducao.LIVROS, nomeDoArquivo, stream);
        return nomeDoArquivo;
    }
    
    public String uploadFrequencia(Curriculo curriculo, 
            Frequencia frequencia, 
            String tipo, 
            InputStream stream) {
        String nomeDoArquivo = frequencia.getId() + frequencia.getMes()+"."+ tipo;
        upload(curriculo, ConfiguracaoUtil.TipoProducao.FREQUENCIAS, nomeDoArquivo, stream);
        return nomeDoArquivo;
    }
    
    public String uploadRelatorio(Curriculo curriculo, 
            Relatorio relatorio, 
            String tipo, 
            InputStream stream) {
        String nomeDoArquivo = relatorio.getId() + relatorio.getMes()+"."+ tipo;
        upload(curriculo, ConfiguracaoUtil.TipoProducao.RELATORIOS, nomeDoArquivo, stream);
        return nomeDoArquivo;
    }

    public String uploadPeriodico(Curriculo curriculo, 
            Periodico periodico, 
            String tipo, 
            InputStream stream) {
        String nomeDoArquivo = periodico.getId() + periodico.getTitulo()+"."+ tipo;
        upload(curriculo, ConfiguracaoUtil.TipoProducao.PERIODICOS, nomeDoArquivo, stream);
        return nomeDoArquivo;
    }

    public String uploadPlano(Curriculo curriculo, 
            Plano plano, 
            String tipo, 
            InputStream stream) {
        String nomeDoArquivo = plano.getId() + plano.getTitulo()+"."+ tipo;
        upload(curriculo, ConfiguracaoUtil.TipoProducao.PLANOS, nomeDoArquivo, stream);
        return nomeDoArquivo;
    }
    
//    public String uploadOrientacao(Curriculo curriculo, 
//            Orientacao orientacao, 
//            String tipo, 
//            InputStream stream) {
//        String nomeDoArquivo = orientacao.getId() + orientacao.getAluno()+"."+ tipo;
//        upload(curriculo, ConfiguracaoUtil.TipoProducao.ORIENTACOES, nomeDoArquivo, stream);
//        return nomeDoArquivo;
//    }

    private void upload(Curriculo curriculo,
            ConfiguracaoUtil.TipoProducao tipo,
            String nomeDoArquivo,
            InputStream stream) {
        try {
            String path = ConfiguracaoUtil.getPath(tipo) + "/" + curriculo.getId();
            File pathPai = criarDiretorio(path);
            File arquivoTemp = new File(pathPai, nomeDoArquivo);
            FileOutputStream fos = new FileOutputStream(arquivoTemp);
            int c = 0;
            while ((c = stream.read()) != -1) {
                fos.write(c);
            }
            fos.close();
        } catch (Exception e) {
            System.out.println("Erro upload");
            System.out.println("Erro: " + e);
        }
    }

    private File criarDiretorio(String path) {
        File filePath = new File(path);
        if (!filePath.exists()) {
            System.out.println("criando diretorio ...");
            System.out.println(filePath.mkdirs());
            
        }
        return filePath;
    }
}