package br.com.unipar.BullkApp.controllers;

import br.com.unipar.BullkApp.model.Login;
import br.com.unipar.BullkApp.model.Usuario;
import br.com.unipar.BullkApp.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(path = "/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping
    public Usuario login(@RequestBody Login login) throws Exception {
        return loginService.login(login);
    }
}
