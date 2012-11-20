/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.conversor;

import invio.entidade.Programa;
import invio.rn.ProgramaRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Junior
 */
@FacesConverter(value="programaConversor")
public class ProgramaConversor implements Converter{

    private ProgramaRN programaRN;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
         Programa resposta = null;
        if (value == null || "".equals(value)) {
            return resposta;
        } else {
            programaRN = new ProgramaRN();
            resposta = programaRN.obter(new Integer(value));
            return resposta;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
       if (value == null) {
            return "";
        } else if (value instanceof Programa) {
            Integer id = ((Programa) value).getId();
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
