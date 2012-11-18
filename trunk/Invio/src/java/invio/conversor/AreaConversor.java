/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.conversor;

import invio.entidade.Area;
import invio.rn.AreaRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Junior
 */
@FacesConverter(value = "areaConversor")
public class AreaConversor implements Converter {

    private AreaRN areaRN;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        Area resposta = null;
        if (value == null || "".equals(value)) {
            return resposta;
        } else {
            areaRN = new AreaRN();
            resposta = areaRN.obter(new Integer(value));
            return resposta;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        } else if (value instanceof Area) {
            Integer id = ((Area) value).getId();
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
