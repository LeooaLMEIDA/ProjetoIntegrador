package br.com.unipar.BullkApp.model.DTO;

import br.com.unipar.BullkApp.enums.CdTreinoENUM;
import br.com.unipar.BullkApp.model.Exercicio;
import br.com.unipar.BullkApp.model.Treino;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Classe de Transferência de Informações referente a Treino")
public class TreinoDTO {
    private Long id;
    private CdTreinoENUM cdTreino;
    private int series;
    private int repeticoes;
    private Time descanso;
    private boolean status;
    private boolean alternativo;
    private Integer peso;
    private ExercicioDTO exercicio;
    private UsuarioDTO usuario;

    public static TreinoDTO consultaDTO(Treino treino){
        TreinoDTO treinoDTO = new TreinoDTO();
        treinoDTO.setId(treino.getId());
        treinoDTO.setCdTreino(treino.getCdTreino());
        treinoDTO.setDescanso(treino.getDescanso());
        treinoDTO.setExercicio(ExercicioDTO.consultaDTO(treino.getExercicio()));
        treinoDTO.setPeso(treino.getPeso());
        treinoDTO.setStatus(treino.isStatus());
        treinoDTO.setAlternativo(treino.isAlternativo());
        treinoDTO.setSeries(treino.getSeries());
        treinoDTO.setRepeticoes(treino.getRepeticoes());
        treinoDTO.setUsuario(UsuarioDTO.consultaDTO(treino.getUsuario()));

        return treinoDTO;
    }
}
