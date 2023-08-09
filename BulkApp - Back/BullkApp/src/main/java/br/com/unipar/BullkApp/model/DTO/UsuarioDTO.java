package br.com.unipar.BullkApp.model.DTO;

import br.com.unipar.BullkApp.enums.SexoENUM;
import br.com.unipar.BullkApp.enums.TipoUsuarioENUM;
import br.com.unipar.BullkApp.model.Usuario;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Classe de Transferência de Informações referente a Usuário")
public class UsuarioDTO {
    private Long id;
    private String nome;
    private Date dtNascimento;
    private SexoENUM sexo;
    private String celular;
    private String email;
    private TipoUsuarioENUM tpUsuario;
    private String urlAvatar = "x";
    private boolean status;

    public static UsuarioDTO consultaDTO(Usuario usuario){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setSexo(usuario.getSexo());
        usuarioDTO.setTpUsuario(usuario.getTpUsuario());
        usuarioDTO.setCelular(usuario.getCelular());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setStatus(usuario.isStatus());
        usuarioDTO.setDtNascimento(usuario.getDtNascimento());
        usuarioDTO.setUrlAvatar(usuario.getUrlAvatar());

        return usuarioDTO;
    }
}
