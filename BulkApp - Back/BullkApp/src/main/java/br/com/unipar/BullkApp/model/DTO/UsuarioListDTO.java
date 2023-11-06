package br.com.unipar.BullkApp.model.DTO;

import br.com.unipar.BullkApp.model.Usuario;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.zip.DataFormatException;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Classe de Transferência de Informações referente a Usuário")
public class UsuarioListDTO {
    private Long id;
    private String nome;

    public static UsuarioListDTO consultaDTO(Usuario usuario) throws DataFormatException, IOException {
        UsuarioListDTO usuarioDTO = new UsuarioListDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNome(usuario.getNome());

        return usuarioDTO;
    }
}
