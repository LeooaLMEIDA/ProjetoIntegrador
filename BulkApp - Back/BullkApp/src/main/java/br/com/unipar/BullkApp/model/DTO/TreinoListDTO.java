package br.com.unipar.BullkApp.model.DTO;

import br.com.unipar.BullkApp.enums.CdTreinoENUM;
import br.com.unipar.BullkApp.model.Treino;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.sql.Time;
import java.util.zip.DataFormatException;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Classe de Transferência de Informações referente a Treino")
public class TreinoListDTO {
    private Long id;
    private CdTreinoENUM cdTreino;
    private int series;
    private int repeticoes;
    private Time descanso;
    private boolean status;
    private boolean alternativo;
    private Integer peso;
    private ExercicioListDTO exercicio;
    private UsuarioListDTO usuario;

    public static TreinoListDTO consultaDTO(Treino treino) throws DataFormatException, IOException {
        TreinoListDTO treinoDTO = new TreinoListDTO();
        treinoDTO.setId(treino.getId());
        treinoDTO.setCdTreino(treino.getCdTreino());
        treinoDTO.setDescanso(treino.getDescanso());
        treinoDTO.setExercicio(ExercicioListDTO.consultaDTO(treino.getExercicio()));
        treinoDTO.setPeso(treino.getPeso());
        treinoDTO.setStatus(treino.isStatus());
        treinoDTO.setAlternativo(treino.isAlternativo());
        treinoDTO.setSeries(treino.getSeries());
        treinoDTO.setRepeticoes(treino.getRepeticoes());
        treinoDTO.setUsuario(UsuarioListDTO.consultaDTO(treino.getUsuario()));

        return treinoDTO;
    }
}
