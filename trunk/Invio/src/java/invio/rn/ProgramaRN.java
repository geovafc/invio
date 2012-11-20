/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.rn;

import invio.dao.GenericDAO;
import invio.entidade.Instituicao;
import invio.entidade.Programa;
import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author Dir de Armas Marinha
 */
public class ProgramaRN {

    GenericDAO<Programa> dao = new GenericDAO<Programa>();
    GenericDAO<Instituicao> daoInstituicao = new GenericDAO<Instituicao>();

    public boolean salvar(Programa p) {
        if (p.getId() == null) {
            return dao.criar(p);
        } else {
            return dao.alterar(p);
        }
    }

    public boolean remover(Programa p) {
        return dao.excluir(p);
    }

    public Programa obter(Integer id) {
        return dao.obter(Programa.class, id);
    }

    public List<Programa> obterTodos() {
        return dao.obterTodos(Programa.class);
    }

    public SelectItem[] getInstituicoes() {
        List<Instituicao> instituicaos = daoInstituicao.obterTodos(Instituicao.class);
        SelectItem[] retorno = new SelectItem[instituicaos.size()];
        for (int i = 0; i < retorno.length; i++) {
            retorno[i] = new SelectItem(instituicaos.get(i), instituicaos.get(i).getNome());
        }
        return retorno;
    }

    public static void main(String[] args) {
        ProgramaRN programaRN = new ProgramaRN();
        for (Programa p : programaRN.obterTodos()) {
            System.out.println(p);
        }
    }
}
