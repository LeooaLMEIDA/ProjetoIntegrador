package br.com.unipar.BullkApp.model.DTO;

import br.com.unipar.BullkApp.model.Avaliacao;
import br.com.unipar.BullkApp.model.Treino;
import br.com.unipar.BullkApp.util.Util;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.zip.DataFormatException;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Classe de Transferência de Informações referente a Avaliação")
public class AvaliacaoDTO {
    private Long id;
    private String descricao;
    private String observacao;
    private String arqAvaliacao;
    private Long idUsuario;

    public static AvaliacaoDTO consultaDTO(Avaliacao avaliacao) throws DataFormatException, IOException {
        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();
        avaliacaoDTO.setId(avaliacao.getId());
        avaliacaoDTO.setDescricao(avaliacao.getDescricao());
        avaliacaoDTO.setObservacao(avaliacao.getObservacao());
        avaliacaoDTO.setArqAvaliacao(Util.decompress(avaliacao.getArqAvaliacao()));
        avaliacaoDTO.setIdUsuario(avaliacao.getUsuario().getId());

        return avaliacaoDTO;
    }
}
