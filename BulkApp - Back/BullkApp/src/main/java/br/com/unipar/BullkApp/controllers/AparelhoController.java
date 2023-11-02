package br.com.unipar.BullkApp.controllers;

import br.com.unipar.BullkApp.model.Aparelho;
import br.com.unipar.BullkApp.model.DTO.ExercicioDTO;
import br.com.unipar.BullkApp.model.DTO.PageableDTO;
import br.com.unipar.BullkApp.model.DTO.TreinoDTO;
import br.com.unipar.BullkApp.model.Treino;
import br.com.unipar.BullkApp.services.AparelhoService;
import br.com.unipar.BullkApp.services.ExercicioService;
import br.com.unipar.BullkApp.services.TreinoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @Autowired
    private TreinoService treinoService;

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
    @ApiOperation(value = "Operação responsável por inativar um Aparelho existente, e todos os Exercícios e Treinos atrelados a ele")
    public Aparelho delete(@PathVariable Long id) throws Exception {
        Aparelho aparelho = aparelhoService.delete(id);

        List<ExercicioDTO> exercicios = exercicioService.findByAparelho(aparelho);

        for (ExercicioDTO exercicio : exercicios) {
            List<TreinoDTO> treinos = treinoService.findByExercicio(exercicioService.delete(exercicio.getId()).getId());

            for (TreinoDTO treino : treinos) {
                treinoService.delete(treino.getId());
            }

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

    @GetMapping(path = "/pages")
    @ApiOperation(value = "Operação resposável por listar todos os Aparelho cadastrados no sistema")
    public PageableDTO findAllPageable(@RequestParam("page") int page, @RequestParam("limit") int limit) throws Exception{
        return aparelhoService.findAllPageable(page, limit);
    }

    @GetMapping(path = "/pages/filter/str")
    @ApiOperation(value = "Operação resposável por listar todos os Aparelho cadastrados no sistema")
    public PageableDTO findByFilterPageable(@RequestParam("column") String chave, @RequestParam("value") String valor,@RequestParam("page") int page, @RequestParam("limit") int limit) throws Exception{
        if (chave.equals("descricao"))
            return aparelhoService.findByFilterPageable(valor, page, limit);
        return null;
    }

    @GetMapping(path = "/pages/filter/bool")
    @ApiOperation(value = "Operação resposável por listar todos os Aparelho cadastrados no sistema")
    public PageableDTO findByStatusPageable(@RequestParam("column") String chave, @RequestParam("value") boolean valor, @RequestParam("page") int page, @RequestParam("limit") int limit) throws Exception{
        if (chave.equals("status"))
            return aparelhoService.findByStatusPageable(valor, page, limit);
        return null;
    }
}
