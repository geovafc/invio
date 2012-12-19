/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.util;

import invio.entidade.Livro;
import invio.entidade.Periodico;
import java.io.File;
import java.io.FileInputStream;
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

    public String uploadLivro(Livro livro, String tipo, InputStream stream) {
        String nomeDoArquivo = livro.getTitulo() + "_" + livro.getId() + "." + tipo;
        upload(ConfiguracaoUtil.TipoProducao.LIVROS, nomeDoArquivo, stream);
        return nomeDoArquivo;
    }

    public String uploadPeriodico(Periodico periodico, String tipo, InputStream stream) {
        String nomeDoArquivo = periodico.getTitulo() + "_" + periodico.getId() + "." + tipo;
        upload(ConfiguracaoUtil.TipoProducao.PERIODICOS, nomeDoArquivo, stream);
        return nomeDoArquivo;
    }
    
private void upload(ConfiguracaoUtil.TipoProducao tipo,
            String nomeDoArquivo,
            InputStream stream) {
        try {
            File arquivoTemp = new File(ConfiguracaoUtil.getPath(tipo), nomeDoArquivo);
            FileOutputStream fos = new FileOutputStream(arquivoTemp);
            int c = 0;
            while ((c = stream.read()) != -1) {
                fos.write(c);
            }
            fos.close();
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
    }
    
    /*
     * 
     * public void fileUpload(FileUploadEvent event, String type, String diretorio) {
        try {
            this.nome = new java.util.Date().getTime() + type;
            this.caminho = getRealPath() + diretorio + getNome();
            this.arquivo = event.getFile().getContents();
            
            File file = new File(getRealPath() + diretorio);
            file.mkdirs();

        } catch (Exception ex) {
            System.out.println("Erro no upload do arquivo" + ex);
        }
    }
    * 
    * 
    * public void gravar(){
        try {
            
            FileOutputStream fos;
            fos = new FileOutputStream(this.caminho);
            fos.write(this.arquivo);
            fos.close();
            
        } catch (Exception ex) {
            Logger.getLogger(UploadArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
}