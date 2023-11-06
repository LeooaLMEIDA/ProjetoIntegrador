package br.com.unipar.BullkApp.model.DTO;

import br.com.unipar.BullkApp.model.Exercicio;
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
public class ExercicioListDTO {
    private Long id;
    private String descricao;

    public static ExercicioListDTO consultaDTO(Exercicio exercicio) throws DataFormatException, IOException {
        ExercicioListDTO exercicioDTO = new ExercicioListDTO();
        exercicioDTO.setId(exercicio.getId());
        exercicioDTO.setDescricao(exercicio.getDescricao());

        return exercicioDTO;
    }
}
