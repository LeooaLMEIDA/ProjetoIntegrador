package br.com.unipar.BullkApp.services;

import br.com.unipar.BullkApp.enums.TipoUsuarioENUM;
import br.com.unipar.BullkApp.model.DTO.UsuarioDTO;
import br.com.unipar.BullkApp.model.Login;
import br.com.unipar.BullkApp.model.Usuario;
import br.com.unipar.BullkApp.repositories.mobile.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    private LoginRepository loginRepository;

    public UsuarioDTO loginMobile(Login login) throws Exception {
        Optional<Usuario> user = Optional.ofNullable(loginRepository.findByEmailIsIgnoreCase(login.getEmail()));
        if (user.isEmpty()){
            throw new Exception("Usuario " + login.getEmail() + " não encontrado");
        }

        if (user.get().getTpUsuario().equals(TipoUsuarioENUM.ADMIN)){
            throw new Exception("Ambiente destinado a usuários com perfil aluno!");
        }

        if (!user.get().isStatus()){
            throw new Exception("O usuário informado se encontra inativo!");
        }

        if (!user.get().getSenha().equals(login.getSenha())){
            throw new Exception("Senha Incorreta");
        }

        return UsuarioDTO.consultaDTO(user.get());
    }

    public UsuarioDTO loginWeb(Login login) throws Exception {
        Optional<Usuario> user = Optional.ofNullable(loginRepository.findByEmailIsIgnoreCase(login.getEmail()));
        if (user.isEmpty()){
            throw new Exception("Usuario " + login.getEmail() + " não encontrado");
        }

        if (user.get().getTpUsuario().equals(TipoUsuarioENUM.ALUNO)){
            throw new Exception("Ambiente destinado a usuários com perfil administrador!");
        }

        if (!user.get().isStatus()){
            throw new Exception("O usuário informado se encontra inativo!");
        }

        if (!user.get().getSenha().equals(login.getSenha())){
            throw new Exception("Senha Incorreta");
        }

        return UsuarioDTO.consultaDTO(user.get());
    }
}
