package br.com.unipar.BullkApp.controllers;

import br.com.unipar.BullkApp.model.DTO.PageableDTO;
import br.com.unipar.BullkApp.model.DTO.TreinoDTO;
import br.com.unipar.BullkApp.model.DTO.TreinoWebDTO;
import br.com.unipar.BullkApp.model.Treino;
import br.com.unipar.BullkApp.services.TreinoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/treino")
@Api(description = "Controlador REST Responsável pela Operações que representam o objeto de negócios Treino")
public class TreinoController {

    @Autowired
    private TreinoService treinoService;

    @PostMapping
    @ApiOperation(value = "Operação resposável pela Inserção de um novo Treino")
    public TreinoWebDTO insert(@RequestBody @Valid Treino treino) throws Exception{
        return treinoService.insert(treino);
    }

    @PutMapping
    @ApiOperation(value = "Operação responsável pela Atualização de um Treino já existente")
    public TreinoWebDTO update(@RequestBody Treino treino) throws Exception{
        return TreinoWebDTO.consultaDTO(treinoService.update(treino));
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Operação responsável por inativar um Treino existente")
    public TreinoWebDTO delete(@PathVariable Long id) throws Exception {
        return TreinoWebDTO.consultaDTO(treinoService.delete(id));
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Operação resposável pela busca de Treino via ID")
    public TreinoWebDTO findById(@PathVariable Long id) throws Exception{
        return treinoService.findById(id);
    }

    @GetMapping(path = "/filter")
    @ApiOperation(value = "Operação responsável pela busca do Treino via código do Treino")
    public List<TreinoWebDTO> findByFilters(@RequestParam("cdTreino") String cdTreino) throws Exception{
        return treinoService.findByFiltersCdTreino(cdTreino);
    }

    @GetMapping(path = "/filter/usuario")
    @ApiOperation(value = "Operação responsável pela busca do Treino via código do Treino")
    public List<TreinoWebDTO> findByFilters(@RequestParam("cdTreino") String cdTreino, @RequestParam("usuario_id") Long usuario_id)throws Exception{
        return treinoService.findByFiltersUsuarioTreino(cdTreino, usuario_id);
    }

    @GetMapping
    @ApiOperation(value = "Operação resposável por listar todos os Treinos cadastrados no sistema")
    public List<TreinoDTO> findAll() throws Exception{
        return treinoService.findAll();
    }

    @GetMapping(path = "/filter/usuario/{id_usuario}")
    @ApiOperation(value = "Operação responsável pela busca dos Treinos atribuidos a um Usuário")
    public List<TreinoDTO> findByUsuario(@PathVariable Long id_usuario)throws Exception{
        return treinoService.findByUsuario(id_usuario);
    }

    @GetMapping(path = "/filter/alternativo/{id_treino}")
    @ApiOperation(value = "Operação responsável pela busca dos Treinos atribuidos a um Usuário")
    public TreinoDTO findAlternativo(@PathVariable Long id_treino)throws Exception{
        return treinoService.findAlternativo(id_treino);
    }

    @GetMapping(path = "/pages")
    public PageableDTO findAllPageable(@RequestParam("pagina") int page, @RequestParam("registros") int registros) throws Exception{
        return treinoService.findAllPageable(page, registros);
    }
}
