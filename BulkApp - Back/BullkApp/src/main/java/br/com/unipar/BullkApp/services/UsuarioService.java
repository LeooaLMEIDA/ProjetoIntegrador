package br.com.unipar.BullkApp.services;

import br.com.unipar.BullkApp.enums.SexoENUM;
import br.com.unipar.BullkApp.model.Avaliacao;
import br.com.unipar.BullkApp.model.DTO.UsuarioDTO;
import br.com.unipar.BullkApp.model.Usuario;
import br.com.unipar.BullkApp.repositories.UsuarioRepository;
import br.com.unipar.BullkApp.util.MapperAvaliacao;
import br.com.unipar.BullkApp.util.MapperUsuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@ApiModel(description = "Classe responsável pela regras de Negócio referente ao Usuário")
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario insert(Usuario usuario) throws Exception{
        try {
            validaInsert(usuario);
            usuario.setStatus(true);
            usuario.setDataCriacao(LocalDateTime.now());
            usuario.setDataModificacao(LocalDateTime.now());
            usuarioRepository.saveAndFlush(usuario);
            return usuario;
        } catch (Exception e) {
            throw new Exception("Erro: " + e.getMessage());
        }

    }

    public Optional<Usuario> getFile(Long fileId) {
        return usuarioRepository.findById(fileId);
    }

    public ResponseEntity<String> saveFile(MultipartFile file, String data) {
        try {
            System.out.println(file.getContentType());
            if (!MediaType.IMAGE_PNG_VALUE.equals(file.getContentType()))
                if (!MediaType.IMAGE_JPEG_VALUE.equals(file.getContentType()))
                    throw new Exception("é necessário que a imagem seja um PNG ou JPEG!");


            ObjectMapper objectMapper = new ObjectMapper();
            MapperUsuario usuario = objectMapper.readValue(data, MapperUsuario.class);

            Usuario usuario1 = new Usuario();
            usuario1.setNome(usuario.getNome());
            usuario1.setDtNascimento(usuario.getDtNascimento());
            usuario1.setSexo(usuario.getSexo());
            usuario1.setCelular(usuario.getCelular());
            usuario1.setEmail(usuario.getEmail());
            usuario1.setTpUsuario(usuario.getTpUsuario());
            usuario1.setStatus(usuario.isStatus());
            usuario1.setSenha(usuario.getSenha());

            usuario1.setUrlAvatar(file.getBytes());
            usuario1.setMediaType(file.getContentType());

            usuario1.setDataCriacao(LocalDateTime.now());
            usuario1.setDataModificacao(LocalDateTime.now());

            usuarioRepository.saveAndFlush(usuario1);

            return ResponseEntity.ok().body("Upload com sucesso - Usuário id = " + usuario1.getId());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Problema ao realizar Upload " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Problema ao realizar Upload " + e.getMessage());
        }
    }

    public Usuario update(Usuario usuario) throws Exception {
        validaUpdate(usuario);

        Usuario usuario1 = findById(usuario.getId());

        usuario1.setTpUsuario(usuario.getTpUsuario() != null ? usuario.getTpUsuario() : usuario1.getTpUsuario());
        usuario1.setEmail(usuario.getEmail() != null ? usuario.getEmail() : usuario1.getEmail());
        usuario1.setNome(usuario.getNome() != null ? usuario.getNome() : usuario1.getNome());
        usuario1.setCelular(usuario.getCelular() != null ? usuario.getCelular() : usuario1.getCelular());
        usuario1.setSenha(usuario.getSenha() != null ? usuario.getSenha() : usuario1.getSenha());
        usuario1.setSexo(usuario.getSexo() != null ? usuario.getSexo() : usuario1.getSexo());
        usuario1.setUrlAvatar(usuario.getUrlAvatar() != null ? usuario.getUrlAvatar() : usuario1.getUrlAvatar());
        usuario1.setDtNascimento(usuario.getDtNascimento() != null ? usuario.getDtNascimento() : usuario1.getDtNascimento());
        usuario1.setStatus(usuario.isStatus() == true ? usuario.isStatus() : usuario1.isStatus());

        usuario1.setDataModificacao(LocalDateTime.now());

        usuarioRepository.saveAndFlush(usuario1);
        return usuario1;
    }

    public Usuario delete(Long id) throws  Exception {
        Usuario usuario = findById(id);
        validaUpdate(usuario);
        usuario.setStatus(false);
        usuario.setDataExclusao(LocalDateTime.now());
        usuario.setDataModificacao(LocalDateTime.now());
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
        UsuarioDTO usuario1 = findByEmail(usuario.getEmail());
        if (usuario1 != null) {
            throw new Exception("Já existe um usuário cadastrado com o email " + usuario1.getEmail());
        }
    }

    private void validaUpdate(Usuario usuario) throws Exception{
        if (usuario.getId() == null){
            throw new Exception("É necessário informar o ID para atualizar o cadastro do Usuário");
        }
    }

    public UsuarioDTO findByEmail(String email) throws Exception {
        Optional<Usuario> usuario = Optional.ofNullable(usuarioRepository.findByEmailIgnoreCase(email));
        if (usuario.isPresent()){
            return UsuarioDTO.consultaDTO(usuario.get());
        } else {
            return null;
        }
    }

    public List<SexoENUM> findSexo() {
        List<SexoENUM> sexoENUMS = new ArrayList<>();

        sexoENUMS.add(SexoENUM.FEMININO);
        sexoENUMS.add(SexoENUM.MASCULINO);

        return sexoENUMS;
    }
}
