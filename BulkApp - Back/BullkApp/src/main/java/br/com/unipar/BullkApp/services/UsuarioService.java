package br.com.unipar.BullkApp.services;

import br.com.unipar.BullkApp.enums.SexoENUM;
import br.com.unipar.BullkApp.enums.TipoUsuarioENUM;
import br.com.unipar.BullkApp.model.DTO.*;
import br.com.unipar.BullkApp.model.Usuario;
import br.com.unipar.BullkApp.repositories.mobile.UsuarioRepository;
import br.com.unipar.BullkApp.util.Util;
import io.swagger.annotations.ApiModel;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@ApiModel(description = "Classe responsável pela regras de Negócio referente ao Usuário")
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioListDTO insert(UsuarioWebDTO usuarioDTO) throws Exception{
        Usuario usuario = Usuario.consultaDTO(usuarioDTO);
        validaInsert(usuario);
        usuario.setDataCriacao(LocalDateTime.now());
        usuario.setDataModificacao(LocalDateTime.now());
        usuarioRepository.saveAndFlush(usuario);

        return UsuarioListDTO.consultaDTO(usuario);
    }

    public UsuarioListDTO update(UsuarioWebDTO usuarioDTO) throws Exception {
        if (usuarioDTO.getSenha() == null)
            usuarioDTO.setSenha(usuarioRepository.findById(usuarioDTO.getId()).get().getSenha());

        Usuario usuario = Usuario.consultaDTO(usuarioDTO);

        if (usuario.getUrlAvatar() == null)
            usuario.setUrlAvatar(usuarioRepository.findById(usuario.getId()).get().getUrlAvatar());

        validaUpdate(usuario);

        usuario.setDataModificacao(LocalDateTime.now());
        usuario.setDataCriacao(usuarioRepository.findById(usuario.getId()).get().getDataCriacao());

        if (usuario.isStatus())
            usuario.setDataExclusao(null);

        usuarioRepository.saveAndFlush(usuario);
        return UsuarioListDTO.consultaDTO(usuario);
    }

    public Usuario delete(Long id) throws  Exception {
        Usuario usuario = Usuario.consultaDTO(findById(id));
        validaUpdate(usuario);
        usuario.setStatus(false);
        usuario.setUrlAvatar(null);
        usuario.setDataCriacao(usuarioRepository.findById(usuario.getId()).get().getDataCriacao());
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

        String tipoArquivo = Util.getFileType(UsuarioDTO.consultaDTO(usuario).getUrlAvatar().substring(0,27));

        if (!tipoArquivo.contains("PNG") && !tipoArquivo.contains("JFIF")){
            throw new Exception("Não é possível inserir um Usuário com um avatar diferente de PNG ou JPEG/JPG!");
        }

        validaSenha(usuario.getSenha());
    }

    private void validaUpdate(Usuario usuario) throws Exception{
        if (usuario.getId() == null){
            throw new Exception("É necessário informar o ID para atualizar o cadastro do Usuário");
        }

        List<Usuario> usuarios = findByEmailLogin(usuario.getEmail());
        if (usuarios.size() > 1) {
            throw new Exception("Já existe outro usuário cadastrado com o email " + usuario.getEmail());
        }

        String tipoArquivo = Util.getFileType(UsuarioDTO.consultaDTO(usuario).getUrlAvatar().substring(0,27));

        if (!tipoArquivo.contains("PNG") && !tipoArquivo.contains("JFIF")){
            throw new Exception("Não é possível inserir um Usuário com um avatar diferente de PNG ou JPEG/JPG!");
        }

        validaSenha(usuario.getSenha());
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
        List<Usuario> usuarioDTOS = usuarioRepository.findAll();

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
            usuarioDTOSRetorno.add(UsuarioDTO.consultaDTO(usuarioDTOS.get(i)));
        }

        PageableDTO pageableDTO = new PageableDTO(new ArrayList<Object>(usuarioDTOSRetorno), page, usuarioDTOS.size());
        return pageableDTO;
    }

    public PageableDTO findByNomePageable(String nome, int page, int registrosSolic) throws Exception {
        List<Usuario> usuarioDTOS = usuarioRepository.findByNomeContainsIgnoreCase(nome);

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
            usuarioDTOSRetorno.add(UsuarioDTO.consultaDTO(usuarioDTOS.get(i)));
        }

        PageableDTO pageableDTO = new PageableDTO(new ArrayList<Object>(usuarioDTOSRetorno), page, usuarioDTOS.size());
        return pageableDTO;
    }

    public PageableDTO findBySexoPageable(String sexo, int page, int registrosSolic) throws Exception {
        List<Usuario> usuarioDTOS = usuarioRepository.findBySexo(SexoENUM.valueOf(sexo));

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
            usuarioDTOSRetorno.add(UsuarioDTO.consultaDTO(usuarioDTOS.get(i)));
        }

        PageableDTO pageableDTO = new PageableDTO(new ArrayList<Object>(usuarioDTOSRetorno), page, usuarioDTOS.size());
        return pageableDTO;
    }

    public PageableDTO findByTipoUsuarioPageable(String tpUsuario, int page, int registrosSolic) throws Exception {
        List<Usuario> usuarioDTOS = usuarioRepository.findByTpUsuario(TipoUsuarioENUM.valueOf(tpUsuario));

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
            usuarioDTOSRetorno.add(UsuarioDTO.consultaDTO(usuarioDTOS.get(i)));
        }

        PageableDTO pageableDTO = new PageableDTO(new ArrayList<Object>(usuarioDTOSRetorno), page, usuarioDTOS.size());
        return pageableDTO;
    }

    public PageableDTO findByEmailPageable(String email, int page, int registrosSolic) throws Exception {
        List<Usuario> usuarioDTOS = usuarioRepository.findByEmailIsContainingIgnoreCase(email);

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
            usuarioDTOSRetorno.add(UsuarioDTO.consultaDTO(usuarioDTOS.get(i)));
        }

        PageableDTO pageableDTO = new PageableDTO(new ArrayList<Object>(usuarioDTOSRetorno), page, usuarioDTOS.size());
        return pageableDTO;
    }

    public PageableDTO findByCelularPageable(String celular, int page, int registrosSolic) throws Exception {
        List<Usuario> usuarioDTOS = usuarioRepository.findByCelularContaining(celular);

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
            usuarioDTOSRetorno.add(UsuarioDTO.consultaDTO(usuarioDTOS.get(i)));
        }

        PageableDTO pageableDTO = new PageableDTO(new ArrayList<Object>(usuarioDTOSRetorno), page, usuarioDTOS.size());
        return pageableDTO;
    }

    public PageableDTO findByStatusPageable(boolean status, int page, int registrosSolic) throws Exception {
        List<Usuario> usuarioDTOS = usuarioRepository.findByStatus(status);

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
            usuarioDTOSRetorno.add(UsuarioDTO.consultaDTO(usuarioDTOS.get(i)));
        }

        PageableDTO pageableDTO = new PageableDTO(new ArrayList<Object>(usuarioDTOSRetorno), page, usuarioDTOS.size());
        return pageableDTO;
    }

    public boolean validaSenha(String senha) throws Exception {
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%?&])[A-Za-z\\d@$!%?&]{8,}$");
        Matcher matcher = pattern.matcher(senha);

        if (matcher.matches()) {
            return true;
        } else if (senha.length() < 8) {
            throw new Exception("Senha deve conter ao menos 8 caracteres");
        } else {
            throw new Exception("Senha inválida, verifique se há espaços ou caracteres impróprios");
        }
    }

    public boolean validatePasswordDb(UsuarioSenhaDTO usuario) {
        String senhaAtual = usuarioRepository.findById(usuario.getId()).get().getSenha();

        return senhaAtual.equals(usuario.getSenha());
    }
}
