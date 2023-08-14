package br.com.unipar.BullkApp.controllers;

import br.com.unipar.BullkApp.model.DTO.TreinoDTO;
import br.com.unipar.BullkApp.model.Treino;
import br.com.unipar.BullkApp.model.Usuario;
import br.com.unipar.BullkApp.services.TreinoService;
import br.com.unipar.BullkApp.services.UsuarioService;
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
    public Treino insert(@RequestBody @Valid Treino treino) throws Exception{
        return treinoService.insert(treino);
    }

    @PutMapping
    @ApiOperation(value = "Operação responsável pela Atualização de um Treino já existente")
    public Treino update(@RequestBody Treino treino) throws Exception{
        return treinoService.update(treino);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Operação responsável por inativar um Treino existente")
    public Treino delete(@PathVariable Long id) throws Exception {
        return treinoService.delete(id);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Operação resposável pela busca de Treino via ID")
    public Treino findById(@PathVariable Long id) throws Exception{
        return treinoService.findById(id);
    }

    @GetMapping(path = "/filter")
    @ApiOperation(value = "Operação responsável pela busca do Treino via código do Treino")
    public List<Treino> findByFilters(@RequestParam("cdTreino") String cdTreino)throws Exception{
        return treinoService.findByFilters(cdTreino);
    }

    @GetMapping
    @ApiOperation(value = "Operação resposável por listar todos os Treinos cadastrados no sistema")
    public List<Treino> findAll() throws Exception{
        return treinoService.findAll();
    }

    @GetMapping(path = "/filter/usuario")
    @ApiOperation(value = "Operação responsável pela busca dos Treinos atribuidos a um Usuário")
    public List<TreinoDTO> findByUsuario(@RequestParam("id") Long id_usuario)throws Exception{
        return treinoService.findByUsuario(id_usuario);
    }
}
