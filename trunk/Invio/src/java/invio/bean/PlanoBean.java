package invio.bean;

import invio.entidade.Plano;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Renan
 */
public class PlanoBean {
    
    private Plano plano = new Plano();
    private List<Plano> planos;

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    public List<Plano> getPlanos() {
        return planos;
    }

    public void setPlanos(List<Plano> planos) {
        this.planos = planos;
    }
    
    
    
    
    public void salvar(){
        
    }
    
    public void excluir(){
        
    }
    
    public List<Plano> listar(){
        return planos;
    }
    
    public void salvarCurriculo(){
        
    }
    
    
}
