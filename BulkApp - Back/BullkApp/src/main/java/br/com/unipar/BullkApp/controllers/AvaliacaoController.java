package br.com.unipar.BullkApp.controllers;

import br.com.unipar.BullkApp.model.DTO.AvaliacaoDTO;
import br.com.unipar.BullkApp.model.DTO.AvaliacaoWebDTO;
import br.com.unipar.BullkApp.model.DTO.ExercicioDTO;
import br.com.unipar.BullkApp.model.DTO.PageableDTO;
import br.com.unipar.BullkApp.services.AvaliacaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/avaliacao")
@Api(description = "Controlador REST Responsável pela Operações que representam o objeto de negócios Avaliação")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @PostMapping
    @ApiOperation(value = "Operação resposável pela Inserção de uma nova Avaliação")
    public AvaliacaoWebDTO insert(@RequestBody AvaliacaoWebDTO avaliacaoDTO) throws Exception{
        return avaliacaoService.insert(avaliacaoDTO);
    }

    @PutMapping
    @ApiOperation(value = "Operação resposável pela Atualização de uma Avaliação existente")
    public AvaliacaoWebDTO update(@RequestBody @Valid AvaliacaoWebDTO avaliacaoDTO) throws Exception{
        return avaliacaoService.update(avaliacaoDTO);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Operação responsável por excluir uma Avaliação existente")
    public AvaliacaoWebDTO delete(@PathVariable Long id) throws Exception {
        return avaliacaoService.delete(id);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Operação resposável pela busca de Avaliação via ID")
    public AvaliacaoDTO findById(@PathVariable Long id) throws Exception{
        return avaliacaoService.findById(id);
    }

    @GetMapping(path = "/filter")
    @ApiOperation(value = "Operação responsável pela busca da Avaliação via descrição")
    public List<AvaliacaoDTO> findByFilters(@RequestParam("descricao") String descricao)throws Exception{
        return avaliacaoService.findByFilters(descricao);
    }

    @GetMapping
    @ApiOperation(value = "Operação resposável por listar todas as Avaliações cadastradas no sistema")
    public List<AvaliacaoDTO> findAll() throws Exception{
        return avaliacaoService.findAll();
    }

    @GetMapping(path = "/filter/usuario/{id_usuario}")
    @ApiOperation(value = "Operação responsável pela busca das Avaliações atribuidas a um Usuário")
    public List<AvaliacaoDTO> findByUsuario(@PathVariable Long id_usuario)throws Exception{
        return avaliacaoService.findByUsuario(id_usuario);
    }

    @GetMapping(path = "/pages")
    @ApiOperation(value = "Operação resposável por listar todas as Avaliações cadastradas no sistema de maneira paginada")
    public PageableDTO findAllPageable(@RequestParam("page") int page, @RequestParam("limit") int registros) throws Exception{
        return avaliacaoService.findAllPageable(page, registros);
    }

    @GetMapping(path = "/pages/filter/str")
    @ApiOperation(value = "Operação resposável por listar as Avaliações cadastradas no sistema de acordo com os filtros de texto disponíveis de maneira paginada")
    public PageableDTO findFilterDescPageable(@RequestParam("column") String chave, @RequestParam("value") String valor, @RequestParam("page") int page, @RequestParam("limit") int registros) throws Exception{
        if (chave.equalsIgnoreCase("descricao"))
            return avaliacaoService.findByFilterDescPageable(valor, page, registros);
        else if (chave.equalsIgnoreCase("usuario"))
            return avaliacaoService.findByFilterUserPageable(valor, page, registros);
        return null;
    }

    @GetMapping(path = "/teste/{id}")
    public String findType(@PathVariable Long id) throws Exception {
        AvaliacaoDTO avaliacaoDTO = avaliacaoService.findById(id);

        String textoSerializado = avaliacaoDTO.getArqAvaliacao().substring(0,6);

        Base64 base64 = new Base64();

        String textoDeserializado = new String(base64.decode(textoSerializado));

        return textoDeserializado;
    }
}
