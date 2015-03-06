/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bpmlab.invio.conversor;

import bpmlab.invio.entidade.Instituicao;
import bpmlab.invio.rn.InstituicaoRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Junior
 */
@FacesConverter(value="instituicaoConversor")
public class InstituicaoConversor implements Converter{

    private InstituicaoRN instituicaoRN;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Instituicao resposta = null;
        if (value == null || "".equals(value)) {
            return resposta;
        } else {
            instituicaoRN = new InstituicaoRN();
            resposta = instituicaoRN.obter(new Integer(value));
            return resposta;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
       if (value == null) {
            return "";
        } else if (value instanceof Instituicao) {
            Integer id = ((Instituicao) value).getId();
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
