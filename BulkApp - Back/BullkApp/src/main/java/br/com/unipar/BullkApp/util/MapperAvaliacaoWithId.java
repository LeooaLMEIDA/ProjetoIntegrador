package br.com.unipar.BullkApp.util;

import br.com.unipar.BullkApp.model.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MapperAvaliacaoWithId {
    private Long id;
    private String descricao;
    private String observacao;
    @JsonProperty
    private Usuario usuario;
}
