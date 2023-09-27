package br.com.unipar.BullkApp.util;

import br.com.unipar.BullkApp.enums.SexoENUM;
import br.com.unipar.BullkApp.enums.TipoUsuarioENUM;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Date;

@Data
public class MapperUsuarioWithId {
    private Long id;
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
