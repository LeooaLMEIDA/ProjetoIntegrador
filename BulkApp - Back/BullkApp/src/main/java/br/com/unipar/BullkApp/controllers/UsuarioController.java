package br.com.unipar.BullkApp.controllers;

import br.com.unipar.BullkApp.enums.SexoENUM;
import br.com.unipar.BullkApp.model.Avaliacao;
import br.com.unipar.BullkApp.model.DTO.AvaliacaoDTO;
import br.com.unipar.BullkApp.model.DTO.ImagemDTO;
import br.com.unipar.BullkApp.model.DTO.UsuarioDTO;
import br.com.unipar.BullkApp.model.Treino;
import br.com.unipar.BullkApp.model.Usuario;
import br.com.unipar.BullkApp.services.AvaliacaoService;
import br.com.unipar.BullkApp.services.TreinoService;
import br.com.unipar.BullkApp.services.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/usuario")
@Api(description = "Controlador REST Responsável pela Operações que representam o objeto de negócios Usuário")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TreinoService treinoService;

    @Autowired
    private AvaliacaoService avaliacaoService;

    @PostMapping("/uploadArquivo")
    @ApiOperation(value = "Operação resposável pela Inserção de um novo Usuário com imagem do avatar")
    public ResponseEntity<String> insertWithPhoto(@RequestParam("file") MultipartFile file, @RequestParam("data") String data){
        return usuarioService.insertWithFile(file, data);
    }

    @PutMapping()
    @ApiOperation(value = "Operação resposável pela Atualização de um Usuário já existente com imagem do avatar")
    public ResponseEntity<String> updateWithPhoto(@RequestParam("file") MultipartFile file, @RequestParam("data") String data){
        return usuarioService.updateWithFile(file, data);
    }

    @GetMapping("/getPhoto/{fileId}")
    public ImagemDTO downloadPhoto(@PathVariable Long fileId) throws Exception {
        Usuario usuario = usuarioService.findById(fileId);
//        HttpHeaders headers = new HttpHeaders();
//
//        if (usuario.getMediaType().equals(MediaType.IMAGE_JPEG_VALUE)){
//            headers.setContentType(MediaType.IMAGE_JPEG);
//        } else if (usuario.getMediaType().equals(MediaType.IMAGE_PNG_VALUE)) {
//            headers.setContentType(MediaType.IMAGE_PNG);
//        }
//
//        ByteArrayResource resource = new ByteArrayResource(usuario.getUrlAvatar());
//        return ResponseEntity.ok().headers(headers).body(resource);
        ImagemDTO imagemDTO = new ImagemDTO();
        imagemDTO.setImagem(Base64.getEncoder().encodeToString(usuario.getUrlAvatar()));
        return imagemDTO;
    }

    @PostMapping("/postPhoto/{fileId}")
    public ImagemDTO viewPhoto(@PathVariable Long fileId) throws Exception {
        Usuario usuario = usuarioService.findById(fileId);
//        HttpHeaders headers = new HttpHeaders();
//
//        if (usuario.getMediaType().equals(MediaType.IMAGE_JPEG_VALUE)){
//            headers.setContentType(MediaType.IMAGE_JPEG);
//        } else if (usuario.getMediaType().equals(MediaType.IMAGE_PNG_VALUE)) {
//            headers.setContentType(MediaType.IMAGE_PNG);
//        }
//
//        ByteArrayResource resource = new ByteArrayResource(usuario.getUrlAvatar());
//        return ResponseEntity.ok().headers(headers).body(resource);
        ImagemDTO imagemDTO = new ImagemDTO();
        imagemDTO.setImagem(Base64.getEncoder().encodeToString(usuario.getUrlAvatar()));
        return imagemDTO;
    }

    @PostMapping
    @ApiOperation(value = "Operação resposável pela Inserção de um novo Usuário")
    public Usuario insert(@RequestBody @Valid Usuario usuario) throws Exception{
        return usuarioService.insert(usuario);
    }

//    @PutMapping
//    @ApiOperation(value = "Operação responsável pela Atualização de um Usuário já existente")
//    public Usuario update(@RequestBody Usuario usuario) throws Exception{
//        return usuarioService.update(usuario);
//    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Operação responsável por inativar um Usuário existente")
    public Usuario delete(@PathVariable Long id) throws Exception {
        Usuario usuario = usuarioService.delete(id);

        List<Treino> treinos = treinoService.findByUsuario(usuario);

        for (Treino treino : treinos) {
            treinoService.delete(treino.getId());
        }

        List<Avaliacao> avaliacoes = avaliacaoService.findByUsuario(usuario);

        for (Avaliacao avaliacao : avaliacoes) {
            avaliacaoService.delete(avaliacao.getId());
        }

        return usuario;
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Operação resposável pela busca de Usuário via ID")
    public Usuario findById(@PathVariable Long id) throws Exception{
        return usuarioService.findById(id);
    }

    @GetMapping(path = "/filter")
    @ApiOperation(value = "Operação responsável pela busca do Usuário via Nome")
    public List<Usuario> findByFilters(@RequestParam("nome") String nome)throws Exception{
        return usuarioService.findByFilters(nome);
    }

    @GetMapping
    @ApiOperation(value = "Operação resposável por listar todos os Usuário cadastrados no sistema")
    public List<Usuario> findAll() throws Exception{
        return usuarioService.findAll();
    }

    @GetMapping(path = "/email")
    public UsuarioDTO findByEmail(@RequestParam("email") String email) throws Exception {
        return usuarioService.findByEmail(email);
    }

    @GetMapping(path = "/sexo")
    public List<SexoENUM> findSexo() throws Exception {
        return usuarioService.findSexo();
    }
}
