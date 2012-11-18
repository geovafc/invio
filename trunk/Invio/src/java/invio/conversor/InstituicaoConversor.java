/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.conversor;

import invio.entidade.Instituicao;
import invio.rn.InstituicaoRN;
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
            return null;
        } else if (value instanceof Instituicao) {
            Integer id = ((Instituicao) value).getId();
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
