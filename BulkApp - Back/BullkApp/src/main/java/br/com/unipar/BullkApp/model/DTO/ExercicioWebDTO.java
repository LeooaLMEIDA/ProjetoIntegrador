package br.com.unipar.BullkApp.model.DTO;

import br.com.unipar.BullkApp.enums.GrupoMuscularENUM;
import br.com.unipar.BullkApp.model.Aparelho;
import br.com.unipar.BullkApp.model.Exercicio;
import br.com.unipar.BullkApp.services.ExercicioService;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Classe de Transferência de Informações referente a Exercicio")
public class ExercicioWebDTO {
    private Long id;
    private String descricao;
    private GrupoMuscularENUM grpMusculos;

    public static ExercicioWebDTO consultaWebDTO(Exercicio exercicio){
        ExercicioWebDTO exercicioDTO = new ExercicioWebDTO();
        exercicioDTO.setId(exercicio.getId());
        exercicioDTO.setDescricao(exercicio.getDescricao());
        exercicioDTO.setGrpMusculos(exercicio.getGrpMusculos());

        return exercicioDTO;
    }

    public static ExercicioWebDTO consultaWebDTOById (Long id) throws Exception {
        ExercicioService exercicioService = new ExercicioService();

        Exercicio exercicio = exercicioService.findById(id);

        ExercicioWebDTO exercicioDTO = new ExercicioWebDTO();
        exercicioDTO.setId(exercicio.getId());
        exercicioDTO.setDescricao(exercicio.getDescricao());
        exercicioDTO.setGrpMusculos(exercicio.getGrpMusculos());

        return exercicioDTO;
    }
}
