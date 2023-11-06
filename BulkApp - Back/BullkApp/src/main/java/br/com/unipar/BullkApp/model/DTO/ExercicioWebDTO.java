package br.com.unipar.BullkApp.model.DTO;

import br.com.unipar.BullkApp.enums.GrupoMuscularENUM;
import br.com.unipar.BullkApp.model.Exercicio;
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
@ApiModel(description = "Classe de Transferência de Informações referente a Exercicio")
public class ExercicioWebDTO {
    private Long id;
    private String descricao;
    private GrupoMuscularENUM grpMusculos;
    private String orientacao;
    private boolean status;
    private String imgIlistracao;
    private Long idAparelho;

    public static ExercicioWebDTO consultaDTO(Exercicio exercicio) throws DataFormatException, IOException {
        ExercicioWebDTO exercicioDTO = new ExercicioWebDTO();
        exercicioDTO.setId(exercicio.getId());
        exercicioDTO.setDescricao(exercicio.getDescricao());
        exercicioDTO.setOrientacao(exercicio.getOrientacao());
        exercicioDTO.setStatus(exercicio.isStatus());
        exercicioDTO.setGrpMusculos(exercicio.getGrpMusculos());
        exercicioDTO.setImgIlistracao(Util.decompress(exercicio.getImgIlustracao()));
        exercicioDTO.setIdAparelho(exercicio.getAparelho().getId());

        return exercicioDTO;
    }
}
