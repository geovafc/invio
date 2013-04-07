/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.rn.pdf;

import invio.dao.GenericDAO;
import invio.entidade.Qualis;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.exception.NestableRuntimeException;
import org.hibernate.util.JDBCExceptionReporter;

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
        if (dao.iniciarTransacao()) {
            for (Qualis qualis : osQualis) {
//                if (qualis.getQualisPK() != null) {
//                    try {
//                    confirmar = dao.criar(qualis);     
//                    } catch (Exception e) {
//                        System.out.println("EXCEPTION PEGA");
//                        e.printStackTrace();
//                        continue;
//                    }
//                } else {
                if (qualis.getQualisPK() != null) {


                    try {
                        confirmar = dao.alterar(qualis);
                    } catch (Exception e) {
                        System.out.println("EXCEPTION PEGA");
                        e.printStackTrace();
                        continue;
                    } catch (Throwable t) {
                        System.out.println("EXCEPTION PEGA");
                        t.printStackTrace();
                        continue;
                    }

                }
//                }
                if (!confirmar) {
                    continue;
                }
                i++;
                if (i > PARAR) {
                    if (!dao.concluirTransacao()) {
                        return registros;
                    } else {
                        System.out.println(registros + "Salvos!");
                        registros += i;
                    }
                    if (!dao.iniciarTransacao()) {
                        return registros;
                    }
                    i = 0;
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
