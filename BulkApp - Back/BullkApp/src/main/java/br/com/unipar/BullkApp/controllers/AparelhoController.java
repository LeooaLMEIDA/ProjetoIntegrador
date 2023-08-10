package br.com.unipar.BullkApp.controllers;

import br.com.unipar.BullkApp.model.Aparelho;
import br.com.unipar.BullkApp.model.Exercicio;
import br.com.unipar.BullkApp.model.Treino;
import br.com.unipar.BullkApp.services.AparelhoService;
import br.com.unipar.BullkApp.services.ExercicioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/aparelho")
@Api(description = "Controlador REST Responsável pela Operações que representam o objeto de negócios Aparelho")
public class AparelhoController {

    @Autowired
    private AparelhoService aparelhoService;

    @Autowired
    private ExercicioService exercicioService;

    @PostMapping
    @ApiOperation(value = "Operação resposável pela Inserção de um novo Aparelho")
    public Aparelho insert(@RequestBody @Valid Aparelho aparelho) throws Exception{
        return aparelhoService.insert(aparelho);
    }

    @PutMapping
    @ApiOperation(value = "Operação responsável pela Atualização de um Aparelho já existente")
    public Aparelho update(@RequestBody Aparelho aparelho) throws Exception{
        return aparelhoService.update(aparelho);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Operação responsável por inativar um Aparelho existente")
    public Aparelho delete(@PathVariable Long id) throws Exception {
        Aparelho aparelho = aparelhoService.delete(id);

        List<Exercicio> exercicios = exercicioService.findByAparelho(aparelho);

        for (Exercicio exercicio : exercicios) {
            exercicioService.delete(exercicio.getId());
        }
        return aparelho;
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Operação resposável pela busca de Aparelho via ID")
    public Aparelho findById(@PathVariable Long id) throws Exception{
        return aparelhoService.findById(id);
    }

    @GetMapping(path = "/filter")
    @ApiOperation(value = "Operação responsável pela busca do Aparelho via descrição")
    public List<Aparelho> findByFilters(@RequestParam("descricao") String descricao)throws Exception{
        return aparelhoService.findByFilters(descricao);
    }

    @GetMapping
    @ApiOperation(value = "Operação resposável por listar todos os Aparelho cadastrados no sistema")
    public List<Aparelho> findAll() throws Exception{
        return aparelhoService.findAll();
    }


}
