/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.conversor;

import invio.entidade.Plano;
import invio.rn.PlanoRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Dir de Armas Marinha
 */
@FacesConverter(value="planoConversor")
public class PlanoConversor implements Converter{

    private PlanoRN planoRN;
            
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       Plano resposta = null;
        if (value == null || "".equals(value)) {
            return resposta;
        } else {
            planoRN = new PlanoRN();
            resposta = planoRN.obter(new Integer(value));
            return resposta;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        } else if (value instanceof Plano) {
            Integer id = ((Plano) value).getId();
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
