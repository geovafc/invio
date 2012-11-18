/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.conversor;

import invio.entidade.Unidade;
import invio.rn.UnidadeRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Junior
 */
@FacesConverter(value="unidadeConversor")
public class UnidadeConversor implements Converter{

    private UnidadeRN unidadeRN;  
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Unidade resposta = null;
        if (value == null || "".equals(value)) {
            return resposta;
        } else {
            unidadeRN = new UnidadeRN();
            resposta = unidadeRN.obter(new Integer(value));
            return resposta;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
         if (value == null) {
            return null;
        } else if (value instanceof Unidade) {
            Integer id = ((Unidade) value).getId();
            if (id != null) {
                return id.toString();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
    
}
