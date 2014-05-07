package invio.util.relatorio;

import invio.dao.ConexaoDB;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;

/**
 *
 * @author fabio
 */
public class Relatorio {

    public static void geraRelatorio(String path, 
            String name, 
            Object params, 
            Object Area) {

        HashMap parameters = new HashMap();
        parameters.put("Meu_Parametro", params);
        parameters.put("Area", Area);

        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.responseComplete();
            ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    scontext.getRealPath(path), 
                    parameters, 
                    ConexaoDB.conectar());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
            exporter.exportReport();

            byte[] bytes = baos.toByteArray();

            if (bytes != null && bytes.length > 0) {
                HttpServletResponse response =
                        (HttpServletResponse) facesContext.getExternalContext().getResponse();
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "inline; filename=\"" + name + ".pdf\"");
                response.setContentLength(bytes.length);
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();
        }
    }

    public static void ImprimirRelatorio(String path, List dataSource, String name, Object params, Object Area) {
        try {

            FacesContext facesContext = FacesContext.getCurrentInstance();
            ServletContext scontext =
                    (ServletContext) facesContext.getExternalContext().getContext();

            JRBeanCollectionDataSource collection = new JRBeanCollectionDataSource(dataSource);
            HashMap<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("Meu_Parametro", params);
            parameters.put("Area", Area);
            JasperPrint print = JasperFillManager.fillReport(scontext.getRealPath(path),
                    parameters, collection);

            byte[] b = JasperExportManager.exportReportToPdf(print);
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, b);
            exporter.exportReport();

            HttpServletResponse res = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            res.setContentType("application/pdf");
            res.setHeader("Content-disposition", "inline; filename=\"" + name + ".pdf\"");
            res.getOutputStream().write(b);
            facesContext.renderResponse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
