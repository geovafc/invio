package invio.rn;

import invio.dao.LoginDAO;
import invio.entidade.Login;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dir de Armas Marinha
 */
public class LoginRN {

    LoginDAO dao = new LoginDAO();

    public boolean salvar(Login login) {
        boolean salvou = false;

        if (dao.iniciarTransacao()) {
            if (login.getId() == null) {
                login.setPerfil('U');//Isso faz com que todo novo usuario no sistema entre com o nivel de acesso:usuário;
                salvou = dao.criar(login);
            } else {
                salvou = dao.alterar(login);
            }
            dao.concluirTransacao();
        }
        return salvou;
    }

    public boolean remover(Login login) {
        //TODO Deveria remover o currículo associado
        return dao.excluir(login);
    }

    public Login obter(Integer id) {
        return dao.obter(Login.class, id);
    }

    public Login obter(String email) {
        return dao.obter(email);
    }

    public boolean existe(String email) {
        return dao.existe(email);
    }

    public List<Login> obterTodos() {
        return dao.obterTodos(Login.class);
    }

    public List<Login> filtrar(String chave) {
        List<Login> resposta = new ArrayList<Login>();
        if (chave == null) {
            return resposta;
        } else {
            List<Login> todos = dao.obterTodos(Login.class);
            if (todos != null) {
                boolean email = false;
                boolean nome = false;
                for (Login login : todos) {
                    email = login.getEmail().contains(chave);
                    if (login.getCurriculo() != null) {
                        nome = login.getCurriculo().getNome().contains(chave);
                    }
                    if (email || nome) {
                        resposta.add(login);
                    }
                }
            }
            return resposta;
        }
    }
}