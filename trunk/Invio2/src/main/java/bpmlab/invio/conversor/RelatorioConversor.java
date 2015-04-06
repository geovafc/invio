/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bpmlab.invio.conversor;

import bpmlab.invio.entidade.Relatorio;
import bpmlab.invio.rn.RelatorioRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Junior
 */
@FacesConverter(value = "relatorioConversor")
public class RelatorioConversor implements Converter {

    private RelatorioRN relatorioRN;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Relatorio resposta = null;
        if (value == null || "".equals(value)) {
            return resposta;
        } else {
            relatorioRN = new RelatorioRN();
            resposta = relatorioRN.obter(new Integer(value));
            return resposta;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        } else if (value instanceof Relatorio) {
            Integer id = ((Relatorio) value).getId();
            if (id != null) {
                return id.toString();
            } else {
                return "";
            }
        } else {
            return "";
        }
    }
}