package br.com.unipar.BullkApp.controllers;

import br.com.unipar.BullkApp.model.Avaliacao;
import br.com.unipar.BullkApp.services.AvaliacaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/avaliacao")
@Api(description = "Controlador REST Responsável pela Operações que representam o objeto de negócios Avaliação")
public class AvaliaçãoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @PostMapping
    @ApiOperation(value = "Operação resposável pela Inserção de uma nova Avaliação")
    public Avaliacao insert(@RequestBody @Valid Avaliacao avaliacao) throws Exception{
        return avaliacaoService.insert(avaliacao);
    }

    @PutMapping
    @ApiOperation(value = "Operação responsável pela Atualização de uma Avaliação já existente")
    public Avaliacao update(@RequestBody @Valid Avaliacao avaliacao) throws Exception{
        return avaliacaoService.update(avaliacao);
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
}
