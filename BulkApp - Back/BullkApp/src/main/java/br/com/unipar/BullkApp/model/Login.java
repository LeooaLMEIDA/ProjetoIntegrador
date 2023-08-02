package br.com.unipar.BullkApp.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
public class Login {
    private String email;
    private String senha;
}
