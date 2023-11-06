package br.com.unipar.BullkApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "BULLK_APARELHO")
@ApiModel(description = "Modelo para Representação de um Aparelho")
@Data
@AllArgsConstructor
public class Aparelho {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Id Autogerado pelo sistema")
	@NonNull
	private Long id;
	@NonNull
	private String descricao;
	@NonNull
	private boolean status;
	@JsonIgnore
	private LocalDateTime dataCriacao;
	@JsonIgnore
	private LocalDateTime dataModificacao;
	@JsonIgnore
	private LocalDateTime dataExclusao;

	public Aparelho() {

	}
}
