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
                for (Login login : todos) {
                    if (login.getEmail().contains(chave)) {
                        resposta.add(login);
                    }
                }
            }
            return resposta;
        }
    }
}