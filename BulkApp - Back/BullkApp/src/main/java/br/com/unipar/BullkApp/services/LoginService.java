package br.com.unipar.BullkApp.services;

import br.com.unipar.BullkApp.model.DTO.UsuarioDTO;
import br.com.unipar.BullkApp.model.Login;
import br.com.unipar.BullkApp.model.Treino;
import br.com.unipar.BullkApp.model.Usuario;
import br.com.unipar.BullkApp.repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    private LoginRepository loginRepository;

    public UsuarioDTO login(Login login) throws Exception {
        Optional<Usuario> user = Optional.ofNullable(loginRepository.findByEmailIsContainingIgnoreCase(login.getEmail()));
        if (!user.isPresent()){
            throw new Exception("Usuario " + login.getEmail() + " não encontrado");
        }

        if (!user.get().isStatus()){
            throw new Exception("O usuário informado se encontra inativo!");
        }

        if (!user.get().getSenha().equals(login.getSenha())){
            throw new Exception("Senha Incorreta");
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.consultaDTO(user.get());

        return usuarioDTO;
    }
}
