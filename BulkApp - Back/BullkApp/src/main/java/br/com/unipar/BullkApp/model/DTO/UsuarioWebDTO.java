package br.com.unipar.BullkApp.model.DTO;

import br.com.unipar.BullkApp.enums.SexoENUM;
import br.com.unipar.BullkApp.enums.TipoUsuarioENUM;
import br.com.unipar.BullkApp.model.Usuario;
import br.com.unipar.BullkApp.util.Util;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.Date;
import java.util.zip.DataFormatException;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Classe de Transferência de Informações referente a Usuário")
public class UsuarioWebDTO {
    private Long id;
    private String nome;
    private Date dtNascimento;
    private SexoENUM sexo;
    private String celular;
    private String email;
    private TipoUsuarioENUM tpUsuario;
    private boolean status;
    private String senha;
    private String urlAvatar;

    public static UsuarioWebDTO consultaDTO(Usuario usuario) throws DataFormatException, IOException {
        UsuarioWebDTO usuarioWebDTO = new UsuarioWebDTO();
        usuarioWebDTO.setId(usuario.getId());
        usuarioWebDTO.setSexo(usuario.getSexo());
        usuarioWebDTO.setTpUsuario(usuario.getTpUsuario());
        usuarioWebDTO.setCelular(usuario.getCelular());
        usuarioWebDTO.setEmail(usuario.getEmail());
        usuarioWebDTO.setStatus(usuario.isStatus());
        usuarioWebDTO.setDtNascimento(usuario.getDtNascimento());
        usuarioWebDTO.setNome(usuario.getNome());
        usuarioWebDTO.setUrlAvatar(Util.decompress(usuario.getUrlAvatar()));
        usuarioWebDTO.setSenha(usuario.getSenha());

        return usuarioWebDTO;
    }
}
