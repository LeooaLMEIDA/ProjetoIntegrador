package br.com.unipar.BullkApp.util;

import br.com.unipar.BullkApp.enums.GrupoMuscularENUM;
import br.com.unipar.BullkApp.enums.SexoENUM;
import br.com.unipar.BullkApp.enums.TipoUsuarioENUM;
import br.com.unipar.BullkApp.model.Aparelho;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.lang.NonNull;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
public class MapperExercicio {
    private String descricao;
    private String orientacao;
    private boolean status;
    @JsonProperty
    private Aparelho aparelho;
    @Enumerated(EnumType.STRING)
    private GrupoMuscularENUM grpMusculos;
}
