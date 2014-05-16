/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package invio.spring;

import invio.entidade.Login;
import invio.rn.LoginRN;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author bpmlab
 */
public class LoginSpring implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        if (string.isEmpty()) {
            throw new UsernameNotFoundException(string);
        }
        final LoginRN loginRN = new LoginRN();
        Login temp = loginRN.obter(string);
        List<GrantedAuthority> papeis = new ArrayList<GrantedAuthority>();
        if (temp != null) {
            papeis.add(new GrantedAuthorityImpl("ROLE_" + temp.getPerfil()));
            User user = new User(
                    temp.getEmail(), 
                    temp.getSenha(), 
                    temp.getAtivo(), 
                    true, 
                    true, 
                    true, 
                    papeis);
            return user;
        } else {
            throw new UsernameNotFoundException(string);
        }
    }
}
