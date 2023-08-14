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
    private String arqAvaliacao;

    public static AvaliacaoDTO consultaDTO(Avaliacao avaliacao){
        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();
        avaliacaoDTO.setId(avaliacao.getId());
        avaliacaoDTO.setArqAvaliacao(avaliacao.getArqAvaliacao());
        avaliacaoDTO.setDescricao(avaliacao.getDescricao());
        avaliacaoDTO.setObservacao(avaliacao.getObservacao());

        return avaliacaoDTO;
    }
}
