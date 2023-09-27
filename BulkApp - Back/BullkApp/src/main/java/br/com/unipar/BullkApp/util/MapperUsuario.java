package br.com.unipar.BullkApp.util;

import br.com.unipar.BullkApp.enums.SexoENUM;
import br.com.unipar.BullkApp.enums.TipoUsuarioENUM;
import br.com.unipar.BullkApp.model.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
public class MapperUsuario {
    private String nome;
    private Date dtNascimento;
    @Enumerated(EnumType.STRING)
    private SexoENUM sexo;
    private String celular;
    private String email;
    @Enumerated(EnumType.STRING)
    private TipoUsuarioENUM tpUsuario;
    private boolean status;
    private String senha;


}
