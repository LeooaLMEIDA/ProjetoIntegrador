package br.com.unipar.BullkApp.controllers;

import br.com.unipar.BullkApp.model.Avaliacao;
import br.com.unipar.BullkApp.model.DTO.AvaliacaoDTO;
import br.com.unipar.BullkApp.services.AvaliacaoService;
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
import java.util.Optional;

@RestController
@RequestMapping(path = "/avaliacao")
@Api(description = "Controlador REST Responsável pela Operações que representam o objeto de negócios Avaliação")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @PostMapping("/uploadArquivo")
    @ApiOperation(value = "Operação responsável pela Inserção de uma nova Avaliação")
    public ResponseEntity<String> insertWithFile(@RequestParam("file")MultipartFile file, @RequestParam("data") String data){
        return avaliacaoService.insertWithFile(file, data);
    }

    @PutMapping()
    @ApiOperation(value = "Operação responsável pela Atualização de uma Avaliação já existente")
    public ResponseEntity<String> updateWithFile(@RequestParam("file")MultipartFile file, @RequestParam("data") String data){
        return avaliacaoService.updateWithFile(file, data);
    }

    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long fileId) throws Exception {
        Avaliacao avaliacao = avaliacaoService.findById(fileId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "Avaliação - " + fileId + ".pdf");
        ByteArrayResource resource = new ByteArrayResource(avaliacao.getArqAvaliacao());
        return ResponseEntity.ok().headers(headers).body(resource);
    }

    @PostMapping
    @ApiOperation(value = "Operação resposável pela Inserção de uma nova Avaliação")
    public Avaliacao insert(@RequestBody @Valid Avaliacao avaliacao) throws Exception{
        return avaliacaoService.insert(avaliacao);
    }

//    @PutMapping
//    @ApiOperation(value = "Operação responsável pela Atualização de uma Avaliação já existente")
//    public Avaliacao update(@RequestBody Avaliacao avaliacao) throws Exception{
//        return avaliacaoService.update(avaliacao);
//    }

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
    public List<Avaliacao> findByFilters(@RequestParam("usuario_id") String descricao)throws Exception{
        return avaliacaoService.findByFilters(descricao);
    }

    @GetMapping
    @ApiOperation(value = "Operação resposável por listar todos os Avaliação cadastrados no sistema")
    public List<AvaliacaoDTO> findAll() throws Exception{
        return avaliacaoService.findAll();
    }

    @GetMapping(path = "/filter/usuario/{id_usuario}")
    @ApiOperation(value = "Operação responsável pela busca dos Avaliações atribuidas a um Usuário")
    public List<AvaliacaoDTO> findByUsuario(@PathVariable Long id_usuario)throws Exception{
        return avaliacaoService.findByUsuario(id_usuario);
    }
}
