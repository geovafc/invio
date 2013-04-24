/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.rn.pdf;

import invio.dao.GenericDAO;
import invio.entidade.Qualis;
import java.util.List;
import javax.persistence.EntityExistsException;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Junior
 */
public class QualisRN {

    GenericDAO<Qualis> dao = new GenericDAO<Qualis>();

    public boolean salvar(Qualis qualis) {
        if (qualis.getQualisPK() == null) {
            return dao.criar(qualis);
        } else {
            return dao.alterar(qualis);
        }
    }

    public int salvar(List<Qualis> osQualis) {
        boolean confirmar = true;
        int registros = 0;
        int i = 0;
        final int PARAR = 100;
        int tamanhaLista = osQualis.size();
        
        if (dao.iniciarTransacao()) {

            for (int j = 0; j < tamanhaLista; j++) {
                Qualis qualis = osQualis.get(j);
                

                if (qualis.getQualisPK() != null) {

                    try {
                        confirmar = dao.alterar(qualis);
                    }
                    catch(EntityExistsException e){
                        System.out.println("Já existe um está chave primária"); 
                         e.printStackTrace();
                        System.out.println("- Registro: " + qualis.getQualisPK().getIssn() + " " + qualis.getQualisPK().getTitulo()
                            + " " + qualis.getEstrato() + " " + qualis.getQualisPK().getAreaAvaliacao()
                            + " " + qualis.getStatus()+"\n I:"+i);
                    }catch(ConstraintViolationException e){
                        System.out.println("Já existe um está chave primária, erro: ConstraintViolationException"); 
                         e.printStackTrace();
                        System.out.println("- Registro: " + qualis.getQualisPK().getIssn() + " " + qualis.getQualisPK().getTitulo()
                            + " " + qualis.getEstrato() + " " + qualis.getQualisPK().getAreaAvaliacao()
                            + " " + qualis.getStatus()+"\n I:"+i);
                    }
                    catch (Exception e) {
                        System.out.println("EXCEPTION PEGA");
                        e.printStackTrace();
                        continue;
                    } catch (Throwable t) {
                        System.out.println("Throwable/EXCEPTION PEGA");
                        t.printStackTrace();
                        continue;
                    }

                }
                if (!confirmar) {
                    continue;
                } else {
                    System.out.println("Registro Salvo: " + qualis.getQualisPK().getIssn() + " " + qualis.getQualisPK().getTitulo()
                            + " " + qualis.getEstrato() + " " + qualis.getQualisPK().getAreaAvaliacao()
                            + " " + qualis.getStatus());
                    i++;
                }

                if ( j==(tamanhaLista-1) || i == PARAR) {
                    
                    
                    if (!dao.concluirTransacao()) {
                        System.out.println("Não foi possível concluir Transação");
                        
                        System.out.println("- Registro: " + qualis.getQualisPK().getIssn() + " " + qualis.getQualisPK().getTitulo()
                            + " " + qualis.getEstrato() + " " + qualis.getQualisPK().getAreaAvaliacao()
                            + " " + qualis.getStatus());
                        
                        return registros;
                    } else {
                        registros += i;
                        i = 0;
                        System.out.println(registros + "SALVOS!!");
                        
                    }
                    if (!dao.iniciarTransacao()) {
                        System.out.println("Não foi possível iniciar Transação");
                        return registros;
                    }
                    
                }

            }
            return registros;
        } else {
            return registros;
        }
    }

    public boolean remover(Qualis qualis) {
        return dao.excluir(qualis);
    }

    public Qualis obter(Integer id) {
        return dao.obter(Qualis.class, id);
    }

    public List<Qualis> obterTodos() {
        return dao.obterTodos(Qualis.class);
    }
}