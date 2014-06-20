/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Mikael Lima
 */
public class ArquivoUtil {

    public static String contextPath(String fileName) {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext external = context.getExternalContext();
        String path = external.getRealPath(
                "/resources/arquivos"
                + File.separator
                + fileName);
        return path;
    }
    
    public static boolean copiarParaArquivos(UploadedFile file) {
        try {
            String path = contextPath(file.getFileName());
            FileOutputStream saida = new FileOutputStream(path);
            InputStream entrada = file.getInputstream();
            IOUtils.copy(entrada, saida);
            IOUtils.closeQuietly(saida);
            IOUtils.closeQuietly(entrada);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    
    public static void exportaPDF(String path) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletResponse response
                = (HttpServletResponse) externalContext.getResponse();
        response.setContentType("application/pdf");
        try {
            FileInputStream input = new FileInputStream(path);
            byte[] bytes = IOUtils.toByteArray(input);
            OutputStream output = response.getOutputStream();
            output.write(bytes);
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(output);
        } catch (IOException e) {
        }
    }

}
