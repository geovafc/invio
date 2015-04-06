package bpmlab.invio.conversor;

import bpmlab.invio.entidade.Area;
import bpmlab.invio.rn.AreaRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

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
            return "";
        } else if (value instanceof Area) {
            Integer id = ((Area) value).getId();
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