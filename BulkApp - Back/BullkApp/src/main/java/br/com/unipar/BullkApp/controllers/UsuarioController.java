package br.com.unipar.BullkApp.controllers;

import br.com.unipar.BullkApp.enums.SexoENUM;
import br.com.unipar.BullkApp.model.DTO.*;
import br.com.unipar.BullkApp.model.Treino;
import br.com.unipar.BullkApp.model.Usuario;
import br.com.unipar.BullkApp.services.AvaliacaoService;
import br.com.unipar.BullkApp.services.TreinoService;
import br.com.unipar.BullkApp.services.UsuarioService;
import br.com.unipar.BullkApp.util.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/usuario")
@Api(description = "Controlador REST Responsável pela Operações que representam o objeto de negócios Usuário")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TreinoService treinoService;

    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping("/getPhoto/{fileId}")
    @ApiOperation(value = "Operação resposável pelo retorno da foto do Usuário")
    public ImagemDTO getPhoto(@PathVariable Long fileId) throws Exception {
        Usuario usuario = Usuario.consultaDTO(usuarioService.findById(fileId));

        ImagemDTO imagemDTO = new ImagemDTO();
        imagemDTO.setImagem(Util.decompress(usuario.getUrlAvatar()));
        return imagemDTO;
    }

    @PostMapping
    @ApiOperation(value = "Operação resposável pela Inserção de um novo Usuário")
    public UsuarioWebDTO insert(@RequestBody @Valid UsuarioWebDTO usuario) throws Exception{
        return usuarioService.insert(usuario);
    }

    @PutMapping
    @ApiOperation(value = "Operação responsável pela Atualização de um Usuário já existente")
    public UsuarioWebDTO update(@RequestBody @Valid UsuarioWebDTO usuario) throws Exception{
        return usuarioService.update(usuario);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Operação responsável por inativar um Usuário existente e dos treinos atribuídos a ele")
    public UsuarioWebDTO delete(@PathVariable Long id) throws Exception {
        Usuario usuario = usuarioService.delete(id);

        List<Treino> treinos = treinoService.findByUsuario(usuario);

        for (Treino treino : treinos) {
            treinoService.delete(treino.getId());
        }

        List<AvaliacaoDTO> avaliacoes = avaliacaoService.findByUsuario(usuario.getId());

        for (AvaliacaoDTO avaliacao : avaliacoes) {
            avaliacaoService.delete(avaliacao.getId());
        }

        return UsuarioWebDTO.consultaDTO(usuario);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Operação resposável pela busca de Usuário via ID")
    public UsuarioDTO findById(@PathVariable Long id) throws Exception{
        return usuarioService.findById(id);
    }

    @GetMapping(path = "/filter")
    @ApiOperation(value = "Operação responsável pela busca do Usuário via Nome")
    public List<UsuarioDTO> findByFilters(@RequestParam("nome") String nome)throws Exception{
        return usuarioService.findByFilters(nome);
    }

    @GetMapping
    @ApiOperation(value = "Operação resposável por listar todos os Usuários cadastrados no sistema")
    public List<UsuarioDTO> findAll() throws Exception{
        return usuarioService.findAll();
    }

    @GetMapping(path = "/sexo")
    @ApiOperation(value = "Operação resposável pelo retorno de todos os ENUMs de Sexo")
    public List<SexoENUM> findSexo() throws Exception {
        return usuarioService.findSexo();
    }

    @GetMapping(path = "/pages")
    @ApiOperation(value = "Operação resposável por listar todos os Usuários cadastrados no sistema  de maneira paginada")
    public PageableDTO findAllPageable(@RequestParam("page") int page, @RequestParam("limit") int registros) throws Exception{
        return usuarioService.findAllPageable(page, registros);
    }

    @GetMapping(path = "/pages/filter/str")
    @ApiOperation(value = "Operação resposável por listar todos os Usuários cadastrados no sistema de acordo com os filtros de texto disponíveis de maneira paginada")
    public PageableDTO findByStrPageable(@RequestParam("column") String chave, @RequestParam("value") String valor, @RequestParam("page") int page, @RequestParam("limit") int registros) throws Exception{
        if (chave.equalsIgnoreCase("nome"))
            return usuarioService.findByNomePageable(valor, page, registros);
        else if (chave.equalsIgnoreCase("sexo"))
            return usuarioService.findBySexoPageable(valor, page, registros);
        else if (chave.equalsIgnoreCase("tipo_usuario"))
            return usuarioService.findByTipoUsuarioPageable(valor, page, registros);
        else if (chave.equalsIgnoreCase("email"))
            return usuarioService.findByEmailPageable(valor, page, registros);
        else if (chave.equalsIgnoreCase("celular"))
            return usuarioService.findByCelularPageable(valor, page, registros);
        return usuarioService.findAllPageable(page, registros);
    }

    @GetMapping(path = "/pages/filter/bool")
    @ApiOperation(value = "Operação resposável por listar todos os Usuários cadastrados no sistema de acordo com os filtros booleanos disponíveis de maneira paginada")
    public PageableDTO findByBoolPageable(@RequestParam("column") String chave, @RequestParam("value") boolean valor, @RequestParam("page") int page, @RequestParam("limit") int registros) throws Exception{
        if (chave.equalsIgnoreCase("status"))
            return usuarioService.findByStatusPageable(valor, page, registros);
        return usuarioService.findAllPageable(page, registros);
    }
}
