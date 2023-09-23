package br.com.unipar.BullkApp.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "BULLK_ARQUIVO_AVALIACAO")
@ApiModel(description = "Modelo para Representação de um Arquivo de Avaliação Corporal")
@Data
@AllArgsConstructor

public class ArquivoAvaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Id Autogerado pelo sistema")
    @NonNull
    private Long id;

}
