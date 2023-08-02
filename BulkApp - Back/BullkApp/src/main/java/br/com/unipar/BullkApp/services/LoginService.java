package br.com.unipar.BullkApp.services;

import br.com.unipar.BullkApp.model.Login;
import br.com.unipar.BullkApp.model.Usuario;
import br.com.unipar.BullkApp.repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private LoginRepository loginRepository;

    public boolean login(Login login){
        Usuario user = loginRepository.findByEmail(login.getEmail());
        if (user != null && user.getSenha().equals(login.getSenha())){
            return true;
        }
        else {
            return false;
        }
    }
}
