package br.com.unipar.BullkApp.controllers;

import br.com.unipar.BullkApp.model.Avaliacao;
import br.com.unipar.BullkApp.model.DTO.AvaliacaoDTO;
import br.com.unipar.BullkApp.model.DTO.PageableDTO;
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
    public AvaliacaoDTO insert(@RequestBody @Valid Avaliacao avaliacao) throws Exception{
        return avaliacaoService.insert(avaliacao);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Operação responsável por inativar uma Avaliação existente")
    public AvaliacaoDTO delete(@PathVariable Long id) throws Exception {
        return avaliacaoService.delete(id);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Operação resposável pela busca de Avaliação via ID")
    public AvaliacaoDTO findById(@PathVariable Long id) throws Exception{
        return AvaliacaoDTO.consultaDTO(avaliacaoService.findById(id));
    }

    @GetMapping(path = "/filter")
    @ApiOperation(value = "Operação responsável pela busca do Avaliação via descrição")
    public List<AvaliacaoDTO> findByFilters(@RequestParam("descricao") String descricao)throws Exception{
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

    @GetMapping(path = "/pages")
    public PageableDTO findAllPageable(@RequestParam("page") int page, @RequestParam("limit") int registros) throws Exception{
        return avaliacaoService.findAllPageable(page, registros);
    }

    @GetMapping(path = "/pages/filter")
    public PageableDTO findFilterDescPageable(@RequestParam("column") String chave, @RequestParam("value") String valor, @RequestParam("page") int page, @RequestParam("limit") int registros) throws Exception{
        if (chave.equalsIgnoreCase("descricao"))
            return avaliacaoService.findByFilterDescPageable(valor, page, registros);
        else if (chave.equalsIgnoreCase("usuario"))
            return avaliacaoService.findByFilterUserPageable(valor, page, registros);
        return null;
    }
}
