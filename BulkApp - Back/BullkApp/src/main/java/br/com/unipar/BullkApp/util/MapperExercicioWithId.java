package br.com.unipar.BullkApp.util;

import br.com.unipar.BullkApp.enums.GrupoMuscularENUM;
import br.com.unipar.BullkApp.model.Aparelho;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class MapperExercicioWithId {
    private Long id;
    private String descricao;
    private String orientacao;
    private boolean status;
    @JsonProperty
    private Aparelho aparelho;
    @Enumerated(EnumType.STRING)
    private GrupoMuscularENUM grpMusculos;
}
