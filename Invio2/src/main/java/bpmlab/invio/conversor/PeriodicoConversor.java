/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bpmlab.invio.conversor;

import bpmlab.invio.entidade.Periodico;
import bpmlab.invio.rn.PeriodicoRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Junior
 */
@FacesConverter(value = "periodicoConversor")
public class PeriodicoConversor implements Converter {

    private PeriodicoRN periodicoRN;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Periodico resposta = null;
        if (value == null || "".equals(value)) {
            return resposta;
        } else {
            periodicoRN = new PeriodicoRN();
            resposta = periodicoRN.obter(new Integer(value));
            return resposta;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        } else if (value instanceof Periodico) {
            Integer id = ((Periodico) value).getId();
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