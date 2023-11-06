package br.com.unipar.BullkApp.controllers;

import br.com.unipar.BullkApp.model.DTO.*;
import br.com.unipar.BullkApp.model.Exercicio;
import br.com.unipar.BullkApp.services.ExercicioService;
import br.com.unipar.BullkApp.services.TreinoService;
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

    @Autowired
    private TreinoService treinoService;

    @GetMapping("/getGif/{fileId}")
    @ApiOperation(value = "Operação resposável pela visualização do gif do Exercício cadastrado")
    public ImagemDTO downloadGif(@PathVariable Long fileId) throws Exception {
        ExercicioDTO exercicio = ExercicioDTO.consultaDTO(exercicioService.findById(fileId));

        ImagemDTO imagemDTO = new ImagemDTO();
        imagemDTO.setImagem(exercicio.getImgIlistracao());

        return imagemDTO;
    }

    @PostMapping
    @ApiOperation(value = "Operação resposável pela Inserção de um novo Exercicio")
    public ExercicioWebDTO insert(@RequestBody @Valid ExercicioWebDTO exercicioDTO) throws Exception{
        return exercicioService.insert(exercicioDTO);
    }

    @PutMapping
    @ApiOperation(value = "Operação responsável pela Atualização de um Exercicio já existente")
    public ExercicioWebDTO update(@RequestBody ExercicioWebDTO exercicioDTO) throws Exception{
        return exercicioService.update(exercicioDTO);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Operação responsável por inativar um Exercicio existente")
    public ExercicioWebDTO delete(@PathVariable Long id) throws Exception {
        Exercicio exercicio = exercicioService.delete(id);

        List<TreinoDTO> treinos = treinoService.findByExercicio(id);

        for (TreinoDTO treino : treinos) {
            treinoService.delete(treino.getId());
        }

        return ExercicioWebDTO.consultaDTO(exercicio);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Operação resposável pela busca de Exercicio via ID")
    public ExercicioDTO findById(@PathVariable Long id) throws Exception{
        return ExercicioDTO.consultaDTO(exercicioService.findById(id));
    }

    @GetMapping(path = "/filter")
    @ApiOperation(value = "Operação responsável pela busca do Exercicio via descrição")
    public List<ExercicioDTO> findByFilters(@RequestParam("descricao") String descricao)throws Exception{
        return exercicioService.findByFilters(descricao);
    }

    @GetMapping
    @ApiOperation(value = "Operação resposável por listar todos os Exercicios cadastrados no sistema")
    public List<ExercicioDTO> findAll() throws Exception{
        return exercicioService.findAll();
    }

    @GetMapping(path = "/pages")
    public PageableDTO findAllPageable(@RequestParam("page") int page, @RequestParam("limit") int registros) throws Exception{
        return exercicioService.findAllPageable(page, registros);
    }

    @GetMapping(path = "/pages/filter/str")
    public PageableDTO findByStrPageable(@RequestParam("column") String chave, @RequestParam("value") String valor, @RequestParam("page") int page, @RequestParam("limit") int registros) throws Exception{
        if (chave.equalsIgnoreCase("descricao"))
            return exercicioService.findByDescPageable(valor, page, registros);
        else if (chave.equalsIgnoreCase("grupo_muscular"))
            return exercicioService.findByGrpMuscularPageable(valor, page, registros);
        else if (chave.equalsIgnoreCase("aparelho"))
            return exercicioService.findByAparelhoPageable(valor,page,registros);

        return null;
    }

    @GetMapping(path = "/pages/filter/bool")
    public PageableDTO findByBoolPageable(@RequestParam("column") String chave, @RequestParam("value") boolean valor, @RequestParam("page") int page, @RequestParam("limit") int registros) throws Exception{
        if (chave.equalsIgnoreCase("status"))
            return exercicioService.findByStatusPageable(valor, page, registros);
        return null;
    }
}
