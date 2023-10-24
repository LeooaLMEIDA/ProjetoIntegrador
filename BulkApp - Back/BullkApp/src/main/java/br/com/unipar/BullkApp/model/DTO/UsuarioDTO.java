package br.com.unipar.BullkApp.model.DTO;

import br.com.unipar.BullkApp.enums.SexoENUM;
import br.com.unipar.BullkApp.enums.TipoUsuarioENUM;
import br.com.unipar.BullkApp.model.Usuario;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Classe de Transferência de Informações referente a Usuário")
public class UsuarioDTO extends UsuarioWebDTO{
    private Date dtNascimento;
    private SexoENUM sexo;
    private String celular;
    private String email;
    private TipoUsuarioENUM tpUsuario;
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
        usuarioDTO.setNome(usuario.getNome());

        return usuarioDTO;
    }
}
