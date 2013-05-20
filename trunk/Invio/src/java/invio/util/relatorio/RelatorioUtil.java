/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.util.relatorio;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author fabio
 */
public class RelatorioUtil<T> {

    public boolean criarPDF(
            Connection conexao,
            File arquivoJasper,
            File pdf) {
        try {
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(arquivoJasper);
            JasperPrint fillReport = JasperFillManager.fillReport(jasperReport, null, conexao);
            JasperExportManager.exportReportToPdfFile(fillReport, pdf.getAbsolutePath());
            conexao.close();
            return true;
        } catch (JRException ex) {
            return false;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean criarPDF(
            List<T> objetos,
            File arquivoJasper,
            File pdf) {
        try {
            JRDataSource dataSource = new JRBeanCollectionDataSource(objetos);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(arquivoJasper);
            JasperPrint fillReport = JasperFillManager.fillReport(jasperReport, null, dataSource);
            return true;
        } catch (JRException ex) {
            return false;
        }
    }
}
