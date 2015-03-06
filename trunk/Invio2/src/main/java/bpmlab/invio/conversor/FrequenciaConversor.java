/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bpmlab.invio.conversor;

import bpmlab.invio.entidade.Frequencia;
import bpmlab.invio.rn.FrequenciaRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Junior
 */
@FacesConverter(value = "frequenciaConversor")
public class FrequenciaConversor implements Converter {

    private FrequenciaRN frequenciaRN;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println("frequenciaConversor " + value);
        Frequencia resposta = null;
        if (value == null || "".equals(value)) {
            return resposta;
        } else {
            frequenciaRN = new FrequenciaRN();
            resposta = frequenciaRN.obter(new Integer(value));
            return resposta;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        System.out.println("frequenciaConversor " + value);
        if (value == null) {
            return "";
        } else if (value instanceof Frequencia) {
            Integer id = ((Frequencia) value).getId();
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