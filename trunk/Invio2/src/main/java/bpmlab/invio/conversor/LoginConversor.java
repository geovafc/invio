package bpmlab.invio.conversor;

import bpmlab.invio.entidade.Login;
import bpmlab.invio.rn.LoginRN;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "loginConversor")
public class LoginConversor implements Converter {

    private LoginRN loginRN;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println("loginConversor " + value);
        Login resposta = null;
        if (value == null || "".equals(value)) {
            return resposta;
        } else {
            loginRN = new LoginRN();
            resposta = loginRN.obter(new Integer(value));
            return resposta;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        System.out.println("loginConversor " + value);
        if (value == null) {
            return "";
        } else if (value instanceof Login) {
            Integer id = ((Login) value).getId();
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