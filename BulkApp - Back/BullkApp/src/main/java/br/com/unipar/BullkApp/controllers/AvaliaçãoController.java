package br.com.unipar.BullkApp.controllers;

import br.com.unipar.BullkApp.model.Avaliacao;
import br.com.unipar.BullkApp.model.DTO.AvaliacaoDTO;
import br.com.unipar.BullkApp.services.AvaliacaoService;
import br.com.unipar.BullkApp.services.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/avaliacao")
@Api(description = "Controlador REST Responsável pela Operações que representam o objeto de negócios Avaliação")
public class AvaliaçãoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String get(Model model){
        List<Avaliacao> avaliacaos = avaliacaoService.getFiles();
        model.addAttribute("docs", avaliacaos);
        return "avalicao";
    }

    @PostMapping("/uploadArquivo")
    public String uploadMultipleFiles(@RequestParam("files")MultipartFile[] files){
        for (MultipartFile file : files) {
            avaliacaoService.saveFile(file);
        }
        return "redirect:/";
    }

    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long fileId) {
        Avaliacao avaliacao = avaliacaoService.getFile(fileId).get();
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(avaliacao.getArqType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\""+avaliacao.getArqName()+"\"")
                .body(new ByteArrayResource(avaliacao.getArqAvaliacao()));
    }

    @PostMapping
    @ApiOperation(value = "Operação resposável pela Inserção de uma nova Avaliação")
    public Avaliacao insert(@RequestBody @Valid Avaliacao avaliacao) throws Exception{
        return avaliacaoService.insert(avaliacao);
    }

    @PutMapping
    @ApiOperation(value = "Operação responsável pela Atualização de uma Avaliação já existente")
    public Avaliacao update(@RequestBody Avaliacao avaliacao) throws Exception{
        return avaliacaoService.update(avaliacao);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Operação responsável por inativar uma Avaliação existente")
    public Avaliacao delete(@PathVariable Long id) throws Exception {
        return avaliacaoService.delete(id);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Operação resposável pela busca de Avaliação via ID")
    public Avaliacao findById(@PathVariable Long id) throws Exception{
        return avaliacaoService.findById(id);
    }

    @GetMapping(path = "/filter")
    @ApiOperation(value = "Operação responsável pela busca do Avaliação via descrição")
    public List<Avaliacao> findByFilters(@RequestParam("descricao") String descricao)throws Exception{
        return avaliacaoService.findByFilters(descricao);
    }

    @GetMapping
    @ApiOperation(value = "Operação resposável por listar todos os Avaliação cadastrados no sistema")
    public List<Avaliacao> findAll() throws Exception{
        return avaliacaoService.findAll();
    }

    @GetMapping(path = "/filter/usuario")
    @ApiOperation(value = "Operação responsável pela busca dos Treinos atribuidos a um Usuário")
    public List<AvaliacaoDTO> findByUsuario(@RequestParam("id") Long id_usuario)throws Exception{
        return avaliacaoService.findByUsuario(id_usuario);
    }
}
