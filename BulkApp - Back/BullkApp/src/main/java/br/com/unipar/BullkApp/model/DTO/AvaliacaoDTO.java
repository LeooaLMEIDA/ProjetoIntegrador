package br.com.unipar.BullkApp.model.DTO;

import br.com.unipar.BullkApp.model.Avaliacao;
import br.com.unipar.BullkApp.model.Treino;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Classe de Transferência de Informações referente a Avaliação")
public class AvaliacaoDTO {
    private Long id;
    private String descricao;
    private String observacao;
    private UsuarioWebDTO usuarioWebDTO;

    public static AvaliacaoDTO consultaDTO(Avaliacao avaliacao){
        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();
        avaliacaoDTO.setId(avaliacao.getId());
        avaliacaoDTO.setDescricao(avaliacao.getDescricao());
        avaliacaoDTO.setObservacao(avaliacao.getObservacao());
        avaliacaoDTO.setUsuarioWebDTO(UsuarioWebDTO.consultaWebDTO(avaliacao.getUsuario()));

        return avaliacaoDTO;
    }
}
