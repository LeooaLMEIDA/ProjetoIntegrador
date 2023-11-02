package br.com.unipar.BullkApp.services;

import br.com.unipar.BullkApp.enums.SexoENUM;
import br.com.unipar.BullkApp.enums.TipoUsuarioENUM;
import br.com.unipar.BullkApp.model.DTO.PageableDTO;
import br.com.unipar.BullkApp.model.DTO.UsuarioDTO;
import br.com.unipar.BullkApp.model.Usuario;
import br.com.unipar.BullkApp.repositories.mobile.UsuarioRepository;
import br.com.unipar.BullkApp.util.Util;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@ApiModel(description = "Classe responsável pela regras de Negócio referente ao Usuário")
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario insert(Usuario usuario) throws Exception{
        validaInsert(usuario);
        usuario.setUrlAvatar(usuario.getUrlAvatar());
        usuario.setDataCriacao(LocalDateTime.now());
        usuario.setDataModificacao(LocalDateTime.now());
        usuarioRepository.saveAndFlush(usuario);
        return usuario;
    }

//    public ResponseEntity<String> insertWithFile(MultipartFile file, String data) {
//        try {
//            if (!MediaType.IMAGE_PNG_VALUE.equals(file.getContentType()))
//                if (!MediaType.IMAGE_JPEG_VALUE.equals(file.getContentType()))
//                    throw new GenericErrorMessage("é necessário que a imagem seja um PNG ou JPEG!");
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            MapperUsuario usuario = objectMapper.readValue(data, MapperUsuario.class);
//
//            Usuario usuario1 = new Usuario();
//            usuario1.setNome(usuario.getNome());
//            usuario1.setDtNascimento(Date.from(usuario.getDtNascimento()));
//            usuario1.setSexo(usuario.getSexo());
//            usuario1.setCelular(usuario.getCelular());
//            usuario1.setEmail(usuario.getEmail());
//            usuario1.setTpUsuario(usuario.getTpUsuario());
//            usuario1.setStatus(usuario.isStatus());
//            usuario1.setSenha(usuario.getSenha());
//
//            usuario1.setUrlAvatar(file.getBytes());
//            usuario1.setMediaType(file.getContentType());
//
//            usuario1.setDataCriacao(LocalDateTime.now());
//            usuario1.setDataModificacao(LocalDateTime.now());
//
//            validaInsert(usuario1);
//
//            usuarioRepository.saveAndFlush(usuario1);
//
//            return ResponseEntity.ok().body("Upload com sucesso - Usuário id = " + usuario1.getId());
//        } catch (GenericErrorMessage e) {
//            e.printStackTrace();
//            return ResponseEntity.badRequest().body("Problema ao realizar Upload " + e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
//        }
//    }

//    public ResponseEntity<String> updateWithFile(MultipartFile file, String data) {
//        try {
//            if (!MediaType.IMAGE_PNG_VALUE.equals(file.getContentType()))
//                if (!MediaType.IMAGE_JPEG_VALUE.equals(file.getContentType()))
//                    throw new GenericErrorMessage("é necessário que a imagem seja um PNG ou JPEG!");
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            MapperUsuarioWithId usuario = objectMapper.readValue(data, MapperUsuarioWithId.class);
//
//            Usuario usuario1 = findById(usuario.getId());
//
//            usuario1.setNome(usuario.getNome());
//            usuario1.setDtNascimento(usuario.getDtNascimento());
//            usuario1.setSexo(usuario.getSexo());
//            usuario1.setCelular(usuario.getCelular());
//            usuario1.setEmail(usuario.getEmail());
//            usuario1.setTpUsuario(usuario.getTpUsuario());
//            usuario1.setStatus(usuario.isStatus());
//            usuario1.setSenha(usuario.getSenha());
//
//            usuario1.setUrlAvatar(file.getBytes());
//            usuario1.setMediaType(file.getContentType());
//
//            usuario1.setDataModificacao(LocalDateTime.now());
//
//            validaUpdate(usuario1);
//
//            if (usuario1.isStatus())
//                usuario1.setDataExclusao(null);
//
//            usuarioRepository.saveAndFlush(usuario1);
//
//            return ResponseEntity.ok().body("Usuário id = " + usuario1.getId() + " atualizado com sucesso!");
//        } catch (GenericErrorMessage e) {
//            e.printStackTrace();
//            return ResponseEntity.badRequest().body("Problema ao realizar Upload " + e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
//        }
//    }

    public Usuario update(Usuario usuario) throws Exception {
        validaUpdate(usuario);

        usuario.setUrlAvatar(usuario.getUrlAvatar());
        usuario.setDataModificacao(LocalDateTime.now());

        usuarioRepository.saveAndFlush(usuario);
        return usuario;
    }

    public Usuario delete(Long id) throws  Exception {
        Usuario usuario = Usuario.consultaDTO(findById(id));
        validaUpdate(usuario);
        usuario.setStatus(false);
        usuario.setUrlAvatar(null);
        usuario.setDataExclusao(LocalDateTime.now());
        usuario.setDataModificacao(LocalDateTime.now());
        usuarioRepository.saveAndFlush(usuario);
        return usuario;
    }

    public UsuarioDTO findById(Long id) throws Exception{
        Optional<Usuario> retorno = usuarioRepository.findById(id);
        if (retorno.isPresent()){
            return UsuarioDTO.consultaDTO(retorno.get());
        }
        else {
            throw new Exception("Usuário " + id + " não encontrado");
        }
    }

    public List<UsuarioDTO> findByFilters(String nome) throws Exception{
        List<Usuario> usuarios = usuarioRepository.findByNomeContainsIgnoreCase(nome);
        List<UsuarioDTO> usuarioDTOS = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            usuarioDTOS.add(UsuarioDTO.consultaDTO(usuario));
        }
        return usuarioDTOS;
    }

    public List<UsuarioDTO> findAll() throws Exception{
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioDTO> usuarioDTOS = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            usuarioDTOS.add(UsuarioDTO.consultaDTO(usuario));
        }
        return usuarioDTOS;
    }

    private void validaInsert(Usuario usuario) throws Exception{
        if (usuario.getId() != null){
            throw new Exception("Não é necessário informar o ID para inserir um novo Usuário");
        }
        List<Usuario> usuario1 = findByEmailLogin(usuario.getEmail());
        if (usuario1.size() > 0) {
            throw new Exception("Já existe um usuário cadastrado com o email " + usuario.getEmail());
        }
    }

    private void validaUpdate(Usuario usuario) throws Exception{
        if (usuario.getId() == null){
            throw new Exception("É necessário informar o ID para atualizar o cadastro do Usuário");
        }

        List<Usuario> usuarios = findByEmailLogin(usuario.getEmail());
        if (usuarios.size() > 1) {
            throw new Exception("Já existe outro usuário cadastrado com o email " + usuario.getEmail());
        }
    }

    public String validaInsertTeste(Usuario usuario) {
        if (usuario.getId() != null){
            return "Não é necessário informar o ID para inserir um novo Usuário";
        }
        return null;
    }

    public String validaUpdateTeste(Usuario usuario) {
        if (usuario.getId() == null){
            return "É necessário informar o ID para atualizar o cadastro do Usuário";
        }
        return null;
    }

    private List<Usuario> findByEmailLogin(String email) throws Exception {
        return usuarioRepository.findByEmailIgnoreCase(email);
    }

    private List<Usuario> findByEmail(String email) throws Exception {
        return usuarioRepository.findByEmailIsContainingIgnoreCase(email);
    }

    private List<Usuario> findBySexo(String sexo) throws Exception {
        return usuarioRepository.findBySexo(SexoENUM.valueOf(sexo));
    }

    private List<Usuario> findByTipoUsuario(String tipoUsuario) throws Exception {
        return usuarioRepository.findByTpUsuario(TipoUsuarioENUM.valueOf(tipoUsuario));
    }

    private List<Usuario> findByCelular(String celular) throws Exception {
        return usuarioRepository.findByCelularContaining(celular);
    }

    public List<SexoENUM> findSexo() {
        return List.of(SexoENUM.values());
    }

    public PageableDTO findAllPageable(int page, int registrosSolic) throws Exception {
        List<UsuarioDTO> usuarioDTOS = findAll();

        List<UsuarioDTO> usuarioDTOSRetorno = new ArrayList<>();

        int inicio = 0;
        int fim = registrosSolic;

        if (page > 1) {
            inicio = inicio + registrosSolic * (page - 1);
            fim = page * registrosSolic;
        }

        if (usuarioDTOS.size() < inicio) {
            throw new Exception("Não há elementos na página informada!");
        } else if (usuarioDTOS.size() < fim) {
            fim = usuarioDTOS.size();
        }

        for (int i = inicio; i < fim; i++) {
            usuarioDTOSRetorno.add(usuarioDTOS.get(i));
        }

        PageableDTO pageableDTO = new PageableDTO(new ArrayList<Object>(usuarioDTOSRetorno), page, usuarioDTOS.size());
        return pageableDTO;
    }

    public PageableDTO findByNomePageable(String nome, int page, int registrosSolic) throws Exception {
        List<UsuarioDTO> usuarioDTOS = new ArrayList<>();

        for (UsuarioDTO usuarioDTO:findAll()) {
            if (usuarioDTO.getNome().toUpperCase().contains(nome.toUpperCase()))
                usuarioDTOS.add(usuarioDTO);
        }

        List<UsuarioDTO> usuarioDTOSRetorno = new ArrayList<>();

        int inicio = 0;
        int fim = registrosSolic;

        if (page > 1) {
            inicio = inicio + registrosSolic * (page - 1);
            fim = page * registrosSolic;
        }

        if (usuarioDTOS.size() < inicio) {
            throw new Exception("Não há elementos na página informada!");
        } else if (usuarioDTOS.size() < fim) {
            fim = usuarioDTOS.size();
        }

        for (int i = inicio; i < fim; i++) {
            usuarioDTOSRetorno.add(usuarioDTOS.get(i));
        }

        PageableDTO pageableDTO = new PageableDTO(new ArrayList<Object>(usuarioDTOSRetorno), page, usuarioDTOS.size());
        return pageableDTO;
    }

    public PageableDTO findBySexoPageable(String sexo, int page, int registrosSolic) throws Exception {
        List<UsuarioDTO> usuarioDTOS = new ArrayList<>();

        for (UsuarioDTO usuario:findAll()) {
            if (usuario.getSexo().equals(SexoENUM.valueOf(sexo)))
                usuarioDTOS.add(usuario);
        }

        List<UsuarioDTO> usuarioDTOSRetorno = new ArrayList<>();

        int inicio = 0;
        int fim = registrosSolic;

        if (page > 1) {
            inicio = inicio + registrosSolic * (page - 1);
            fim = page * registrosSolic;
        }

        if (usuarioDTOS.size() < inicio) {
            throw new Exception("Não há elementos na página informada!");
        } else if (usuarioDTOS.size() < fim) {
            fim = usuarioDTOS.size();
        }

        for (int i = inicio; i < fim; i++) {
            usuarioDTOSRetorno.add(usuarioDTOS.get(i));
        }

        PageableDTO pageableDTO = new PageableDTO(new ArrayList<Object>(usuarioDTOSRetorno), page, usuarioDTOS.size());
        return pageableDTO;
    }

    public PageableDTO findByTipoUsuarioPageable(String tpUsuario, int page, int registrosSolic) throws Exception {
        List<UsuarioDTO> usuarioDTOS = new ArrayList<>();

        for (UsuarioDTO usuario:findAll()) {
            if (usuario.getTpUsuario().equals(TipoUsuarioENUM.valueOf(tpUsuario)))
                usuarioDTOS.add(usuario);
        }

        List<UsuarioDTO> usuarioDTOSRetorno = new ArrayList<>();

        int inicio = 0;
        int fim = registrosSolic;

        if (page > 1) {
            inicio = inicio + registrosSolic * (page - 1);
            fim = page * registrosSolic;
        }

        if (usuarioDTOS.size() < inicio) {
            throw new Exception("Não há elementos na página informada!");
        } else if (usuarioDTOS.size() < fim) {
            fim = usuarioDTOS.size();
        }

        for (int i = inicio; i < fim; i++) {
            usuarioDTOSRetorno.add(usuarioDTOS.get(i));
        }

        PageableDTO pageableDTO = new PageableDTO(new ArrayList<Object>(usuarioDTOSRetorno), page, usuarioDTOS.size());
        return pageableDTO;
    }

    public PageableDTO findByEmailPageable(String email, int page, int registrosSolic) throws Exception {
        List<UsuarioDTO> usuarioDTOS = new ArrayList<>();

        for (UsuarioDTO usuario: findAll()) {
            if (usuario.getEmail().toUpperCase().contains(email.toUpperCase()))
                usuarioDTOS.add(usuario);
        }

        List<UsuarioDTO> usuarioDTOSRetorno = new ArrayList<>();

        int inicio = 0;
        int fim = registrosSolic;

        if (page > 1) {
            inicio = inicio + registrosSolic * (page - 1);
            fim = page * registrosSolic;
        }

        if (usuarioDTOS.size() < inicio) {
            throw new Exception("Não há elementos na página informada!");
        } else if (usuarioDTOS.size() < fim) {
            fim = usuarioDTOS.size();
        }

        for (int i = inicio; i < fim; i++) {
            usuarioDTOSRetorno.add(usuarioDTOS.get(i));
        }

        PageableDTO pageableDTO = new PageableDTO(new ArrayList<Object>(usuarioDTOSRetorno), page, usuarioDTOS.size());
        return pageableDTO;
    }

    public PageableDTO findByCelularPageable(String celular, int page, int registrosSolic) throws Exception {
        List<UsuarioDTO> usuarioDTOS = new ArrayList<>();

        for (UsuarioDTO usuario:findAll()) {
            if (usuario.getCelular().contains(celular))
                usuarioDTOS.add(usuario);
        }

        List<UsuarioDTO> usuarioDTOSRetorno = new ArrayList<>();

        int inicio = 0;
        int fim = registrosSolic;

        if (page > 1) {
            inicio = inicio + registrosSolic * (page - 1);
            fim = page * registrosSolic;
        }

        if (usuarioDTOS.size() < inicio) {
            throw new Exception("Não há elementos na página informada!");
        } else if (usuarioDTOS.size() < fim) {
            fim = usuarioDTOS.size();
        }

        for (int i = inicio; i < fim; i++) {
            usuarioDTOSRetorno.add(usuarioDTOS.get(i));
        }

        PageableDTO pageableDTO = new PageableDTO(new ArrayList<Object>(usuarioDTOSRetorno), page, usuarioDTOS.size());
        return pageableDTO;
    }

    public PageableDTO findByStatusPageable(boolean status, int page, int registrosSolic) throws Exception {
        List<UsuarioDTO> usuarioDTOS = new ArrayList<>();

        for (UsuarioDTO usuarioDTO:findAll()) {
            if (usuarioDTO.isStatus() == status)
                usuarioDTOS.add(usuarioDTO);
        }

        List<UsuarioDTO> usuarioDTOSRetorno = new ArrayList<>();

        int inicio = 0;
        int fim = registrosSolic;

        if (page > 1) {
            inicio = inicio + registrosSolic * (page - 1);
            fim = page * registrosSolic;
        }

        if (usuarioDTOS.size() < inicio) {
            throw new Exception("Não há elementos na página informada!");
        } else if (usuarioDTOS.size() < fim) {
            fim = usuarioDTOS.size();
        }

        for (int i = inicio; i < fim; i++) {
            usuarioDTOSRetorno.add(usuarioDTOS.get(i));
        }

        PageableDTO pageableDTO = new PageableDTO(new ArrayList<Object>(usuarioDTOSRetorno), page, usuarioDTOS.size());
        return pageableDTO;
    }
}
