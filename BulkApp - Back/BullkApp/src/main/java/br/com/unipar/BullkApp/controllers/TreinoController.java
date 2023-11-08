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
    public TreinoWebDTO insert(@RequestBody @Valid TreinoWebDTO treinoDTO) throws Exception{
        return treinoService.insert(treinoDTO);
    }

    @PutMapping
    @ApiOperation(value = "Operação responsável pela Atualização de um Treino já existente")
    public TreinoWebDTO update(@RequestBody TreinoWebDTO treino) throws Exception{
        return treinoService.update(treino);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Operação responsável por inativar um Treino existente")
    public TreinoWebDTO delete(@PathVariable Long id) throws Exception {
        return treinoService.delete(id);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Operação resposável pela busca de Treino via ID")
    public TreinoDTO findById(@PathVariable Long id) throws Exception{
        return treinoService.findById(id);
    }

    @GetMapping(path = "/filter")
    @ApiOperation(value = "Operação responsável pela busca do Treino via código do Treino")
    public List<TreinoDTO> findByFilters(@RequestParam("cdTreino") String cdTreino) throws Exception{
        return treinoService.findByFiltersCdTreino(cdTreino);
    }

    @GetMapping(path = "/filter/usuario")
    @ApiOperation(value = "Operação responsável pela busca do Treino via código do Treino e Usuário")
    public List<TreinoDTO> findByFilters(@RequestParam("cdTreino") String cdTreino, @RequestParam("usuario_id") Long usuario_id)throws Exception{
        return treinoService.findByFiltersUsuarioTreino(cdTreino, usuario_id);
    }

    @GetMapping
    @ApiOperation(value = "Operação resposável por listar todos os Treinos cadastrados no sistema")
    public List<TreinoDTO> findAll() throws Exception{
        return treinoService.findAll();
    }

    @GetMapping(path = "/filter/usuario/{id_usuario}")
    @ApiOperation(value = "Operação responsável pela busca dos Treinos atribuidos a um Usuário")
    public List<Treino> findByUsuario(@PathVariable Long id_usuario)throws Exception{
        return treinoService.findByUsuario(id_usuario);
    }

    @GetMapping(path = "/filter/alternativo/{id_treino}")
    @ApiOperation(value = "Operação responsável pela busca dos Treinos alternativos para um treino cadastrado")
    public TreinoDTO findAlternativo(@PathVariable Long id_treino)throws Exception{
        return treinoService.findAlternativo(id_treino);
    }

    @GetMapping(path = "/pages")
    @ApiOperation(value = "Operação resposável por listar todos os Treinos cadastrados no sistema de maneira paginada")
    public PageableDTO findAllPageable(@RequestParam("page") int page, @RequestParam("limit") int registros) throws Exception{
        return treinoService.findAllPageable(page, registros);
    }

    @GetMapping(path = "/pages/filter/str")
    @ApiOperation(value = "Operação resposável por listar todos os Treinos cadastrados no sistema de acordo com os filtros de texto disponíveis de maneira paginada")
    public PageableDTO findByStrPageable(@RequestParam("column") String chave, @RequestParam("value") String valor, @RequestParam("page") int page, @RequestParam("limit") int registros) throws Exception{
        if (chave.equalsIgnoreCase("codigo_treino"))
            return treinoService.findByCdTreinoPageable(valor, page, registros);
        else if (chave.equalsIgnoreCase("exercicio"))
            return treinoService.findByExercicioPageable(valor, page, registros);
        else if (chave.equalsIgnoreCase("usuario"))
            return treinoService.findByUsuarioPageable(valor, page, registros);
        return null;
    }

    @GetMapping(path = "/pages/filter/bool")
    @ApiOperation(value = "Operação resposável por listar todos os Treinos cadastrados no sistema de acordo com os filtros booleanos disponíveis de maneira paginada")
    public PageableDTO findByBoolPageable(@RequestParam("column") String chave, @RequestParam("value") boolean valor, @RequestParam("page") int page, @RequestParam("limit") int registros) throws Exception{
        if (chave.equalsIgnoreCase("status"))
            return treinoService.findByStatusPageable(valor, page, registros);
        else if (chave.equalsIgnoreCase("alternativo"))
            return treinoService.findByAlternativoPageable(valor, page, registros);
        return null;
    }
}
