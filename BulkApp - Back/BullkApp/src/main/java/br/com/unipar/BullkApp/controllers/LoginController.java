package br.com.unipar.BullkApp.controllers;

import br.com.unipar.BullkApp.model.DTO.UsuarioDTO;
import br.com.unipar.BullkApp.model.Login;
import br.com.unipar.BullkApp.model.Usuario;
import br.com.unipar.BullkApp.services.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(path = "/login")
@Api(description = "Controlador REST Responsável pela Operação que representa o objeto de Login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping(value = "/mobile")
    @ApiOperation(value = "Operação resposável pela avaliação do usuário que está logando na aplicação mobile e retorno Usuário")
    public UsuarioDTO loginMobile(@RequestBody Login login) throws Exception {
        return loginService.loginMobile(login);
    }

    @PostMapping(value = "/web")
    @ApiOperation(value = "Operação resposável pela avaliação do usuário que está logando na aplicação web e retorno Usuário")
    public UsuarioDTO loginWeb(@RequestBody Login login) throws Exception {
        return loginService.loginWeb(login);
    }
}
