package br.com.unipar.BullkApp.model.DTO;

import br.com.unipar.BullkApp.enums.SexoENUM;
import br.com.unipar.BullkApp.enums.TipoUsuarioENUM;
import br.com.unipar.BullkApp.model.Usuario;
import br.com.unipar.BullkApp.util.Util;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class UsuarioDTO{
    private Long id;
    private String nome;
    private Date dtNascimento;
    private SexoENUM sexo;
    private String celular;
    private String email;
    private TipoUsuarioENUM tpUsuario;
    private boolean status;
    private String urlAvatar;
    private String senha;

    public static UsuarioDTO consultaDTO(Usuario usuario) throws DataFormatException, IOException {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setSexo(usuario.getSexo());
        usuarioDTO.setTpUsuario(usuario.getTpUsuario());
        usuarioDTO.setCelular(usuario.getCelular());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setStatus(usuario.isStatus());
        usuarioDTO.setDtNascimento(usuario.getDtNascimento());
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setUrlAvatar(Util.decompress(usuario.getUrlAvatar()));
        usuarioDTO.setSenha(usuario.getSenha());

        return usuarioDTO;
    }
}
