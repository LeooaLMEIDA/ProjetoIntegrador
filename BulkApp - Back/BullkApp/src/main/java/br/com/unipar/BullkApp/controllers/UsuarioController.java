package br.com.unipar.BullkApp.controllers;

import br.com.unipar.BullkApp.model.Usuario;
import br.com.unipar.BullkApp.services.UsuarioService;
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

    @PostMapping
    @ApiOperation(value = "Operação resposável pela Inserção de um novo Usuário")
    public Usuario insert(@RequestBody @Valid Usuario usuario) throws Exception{
        return usuarioService.insert(usuario);
    }

    @PutMapping
    @ApiOperation(value = "Operação responsável pela Atualização de um Usuário já existente")
    public Usuario update(@RequestBody @Valid Usuario usuario) throws Exception{
        return usuarioService.update(usuario);
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Operação responsável por inativar um Usuário existente")
    public Usuario delete(@PathVariable Long id) throws Exception {
        return usuarioService.delete(id);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Operação resposável pela busca de Usuário via ID")
    public Usuario findById(@PathVariable Long id) throws Exception{
        return usuarioService.findById(id);
    }

    @GetMapping(path = "/filter")
    @ApiOperation(value = "Operação responsável pela busca do Usuário via Nome")
    public List<Usuario> findByFilters(@RequestParam("nome") String nome)throws Exception{
        return usuarioService.findByFilters(nome);
    }

    @GetMapping
    @ApiOperation(value = "Operação resposável por listar todos os Usuário cadastrados no sistema")
    public List<Usuario> findAll() throws Exception{
        return usuarioService.findAll();
    }
}
