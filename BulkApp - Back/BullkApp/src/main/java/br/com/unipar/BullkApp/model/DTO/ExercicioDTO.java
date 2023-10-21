package br.com.unipar.BullkApp.model.DTO;

import br.com.unipar.BullkApp.enums.GrupoMuscularENUM;
import br.com.unipar.BullkApp.model.Aparelho;
import br.com.unipar.BullkApp.model.Exercicio;
import br.com.unipar.BullkApp.model.Treino;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Classe de Transferência de Informações referente a Exercicio")
public class ExercicioDTO extends ExercicioWebDTO {
    private String orientacao;
    private boolean status;
    private Aparelho aparelho;

    public static ExercicioDTO consultaDTO(Exercicio exercicio){
        ExercicioDTO exercicioDTO = new ExercicioDTO();
        exercicioDTO.setId(exercicio.getId());
        exercicioDTO.setDescricao(exercicio.getDescricao());
        exercicioDTO.setOrientacao(exercicio.getOrientacao());
        exercicioDTO.setStatus(exercicio.isStatus());
        exercicioDTO.setAparelho(exercicio.getAparelho());
        exercicioDTO.setGrpMusculos(exercicio.getGrpMusculos());

        return exercicioDTO;
    }
}
