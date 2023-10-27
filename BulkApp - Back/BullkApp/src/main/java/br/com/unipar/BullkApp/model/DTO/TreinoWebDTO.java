package br.com.unipar.BullkApp.model.DTO;

import br.com.unipar.BullkApp.enums.CdTreinoENUM;
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
public class TreinoWebDTO {
    private Long id;
    private CdTreinoENUM cdTreino;
    private int series;
    private int repeticoes;
    private Time descanso;
    private boolean status;
    private boolean alternativo;
    private Integer peso;
    private ExercicioWebDTO exercicio;
    private UsuarioWebDTO usuario;

    public static TreinoWebDTO consultaDTO(Treino treino){
        TreinoWebDTO treinoDTO = new TreinoWebDTO();
        treinoDTO.setId(treino.getId());
        treinoDTO.setCdTreino(treino.getCdTreino());
        treinoDTO.setDescanso(treino.getDescanso());
        treinoDTO.setExercicio(ExercicioWebDTO.consultaWebDTO(treino.getExercicio()));
        treinoDTO.setPeso(treino.getPeso());
        treinoDTO.setStatus(treino.isStatus());
        treinoDTO.setAlternativo(treino.isAlternativo());
        treinoDTO.setSeries(treino.getSeries());
        treinoDTO.setRepeticoes(treino.getRepeticoes());
        treinoDTO.setUsuario(UsuarioWebDTO.consultaWebDTO(treino.getUsuario()));

        return treinoDTO;
    }

    public static TreinoWebDTO consultaByDTO(TreinoDTO treino) throws Exception {
        TreinoWebDTO treinoDTO = new TreinoWebDTO();
        treinoDTO.setId(treino.getId());
        treinoDTO.setCdTreino(treino.getCdTreino());
        treinoDTO.setDescanso(treino.getDescanso());
        treinoDTO.setExercicio(ExercicioWebDTO.consultaWebDTOById(treino.getExercicio().getId()));
        treinoDTO.setPeso(treino.getPeso());
        treinoDTO.setStatus(treino.isStatus());
        treinoDTO.setAlternativo(treino.isAlternativo());
        treinoDTO.setSeries(treino.getSeries());
        treinoDTO.setRepeticoes(treino.getRepeticoes());
        treinoDTO.setUsuario(UsuarioWebDTO.consultaWebDTOById(treino.getUsuario().getId()));

        return treinoDTO;
    }
}
