package br.com.unipar.BullkApp.controllers;

import br.com.unipar.BullkApp.model.Exercicio;
import br.com.unipar.BullkApp.services.ExercicioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/exercicio")
@Api(description = "Controlador REST Responsável pela Operações que representam o objeto de negócios Exercicio")
public class ExercicioController {

    @Autowired
    private ExercicioService exercicioService;

    @PostMapping
    @ApiOperation(value = "Operação resposável pela Inserção de um novo Exercicio")
    public Exercicio insert(@RequestBody @Valid Exercicio exercicio) throws Exception{
        return exercicioService.insert(exercicio);
    }

    @PutMapping
    @ApiOperation(value = "Operação responsável pela Atualização de um Exercicio já existente")
    public Exercicio update(@RequestBody @Valid Exercicio exercicio) throws Exception{
        return exercicioService.update(exercicio);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Operação responsável por inativar um Exercicio existente")
    public Exercicio delete(@PathVariable Long id) throws Exception {
        return exercicioService.delete(id);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Operação resposável pela busca de Exercicio via ID")
    public Exercicio findById(@PathVariable Long id) throws Exception{
        return exercicioService.findById(id);
    }

    @GetMapping(path = "/filter")
    @ApiOperation(value = "Operação responsável pela busca do Exercicio via descrição")
    public List<Exercicio> findByFilters(@RequestParam("descricao") String descricao)throws Exception{
        return exercicioService.findByFilters(descricao);
    }

    @GetMapping
    @ApiOperation(value = "Operação resposável por listar todos os Exercicios cadastrados no sistema")
    public List<Exercicio> findAll() throws Exception{
        return exercicioService.findAll();
    }
}
