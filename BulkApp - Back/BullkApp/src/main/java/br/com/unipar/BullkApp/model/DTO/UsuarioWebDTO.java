package br.com.unipar.BullkApp.model.DTO;

import br.com.unipar.BullkApp.model.Usuario;
import br.com.unipar.BullkApp.services.UsuarioService;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Classe de Transferência de Informações referente a Usuário")
public class UsuarioWebDTO {
    private Long id;
    private String nome;

    public static UsuarioWebDTO consultaWebDTO(Usuario usuario){
        UsuarioWebDTO usuarioDTO = new UsuarioWebDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNome(usuario.getNome());

        return usuarioDTO;
    }

    public static UsuarioWebDTO consultaWebDTOById(Long id) throws Exception {
        UsuarioService usuarioService = new UsuarioService();

        Usuario usuario = usuarioService.findById(id);

        UsuarioWebDTO usuarioDTO = new UsuarioWebDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNome(usuario.getNome());

        return usuarioDTO;
    }
}
