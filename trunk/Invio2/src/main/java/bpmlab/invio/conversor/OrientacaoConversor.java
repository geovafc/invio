/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bpmlab.invio.conversor;

import bpmlab.invio.entidade.Orientacao;
import bpmlab.invio.rn.OrientacaoRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Soranso
 */
@FacesConverter(value = "orientacaoConversor")
public class OrientacaoConversor implements Converter {

    private OrientacaoRN orientacaoRN;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Orientacao resposta = null;
        if (value == null || "".equals(value)) {
            return resposta;
        } else {
            orientacaoRN = new OrientacaoRN();
            resposta = orientacaoRN.obter(new Integer(value));
            return resposta;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        } else if (value instanceof Orientacao) {
            Integer id = ((Orientacao) value).getId();
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