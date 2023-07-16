package br.com.unipar.BullkApp.services;

import br.com.unipar.BullkApp.model.Usuario;
import br.com.unipar.BullkApp.repositories.UsuarioRepository;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ApiModel(description = "Classe responsável pela regras de Negócio referente ao Usuário")
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario insert(Usuario usuario) throws Exception{
        usuarioRepository.saveAndFlush(usuario);
        return usuario;
    }

    public Usuario update(Usuario usuario) throws Exception {
        validaUpdate(usuario);
        usuarioRepository.saveAndFlush(usuario);
        return usuario;
    }

    public Usuario findById(Long id) throws Exception{
        Optional<Usuario> retorno = usuarioRepository.findById(id);
        if (retorno.isPresent()){
            return retorno.get();
        }
        else {
            throw new Exception("Usuário " + id + " não encontrado");
        }
    }

    public List<Usuario> findByFilters(String nome) throws Exception{
        return usuarioRepository.findByNomeContainingAllIgnoringCase(nome);
    }

    public List<Usuario> findAll() throws Exception{
        return usuarioRepository.findAll();
    }

    private void validaInsert(Usuario usuario) throws Exception{
        if (usuario.getId() != null){
            throw new Exception("Não é necessário informar o ID para inserir um novo Usuário");
        }
    }

    private void validaUpdate(Usuario usuario) throws Exception{
        if (usuario.getId() == null){
            throw new Exception("É necessário informar o ID para atualizar o cadastro do Usuário");
        }
    }
}
