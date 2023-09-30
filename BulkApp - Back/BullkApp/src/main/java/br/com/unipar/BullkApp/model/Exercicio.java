package br.com.unipar.BullkApp.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.lang.NonNull;

import br.com.unipar.BullkApp.enums.GrupoMuscularENUM;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "BULLK_EXERCICIO")
@ApiModel(description = "Modelo para Representação de um Exercicio")
@Data
public class Exercicio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Id Autogerado pelo sistema")
	@NonNull
	private Long id;
	@NonNull
	@Size(max = 40)
	@ApiModelProperty(required = true)
	private String descricao;
	@NonNull
	private String orientacao;
	@NonNull
	private byte[] imgIlustracao;
	private String mediaType;
//	@NonNull
//	private String vdInstrucao;
	private boolean status;
	@ManyToOne
	@NonNull
	private Aparelho aparelho;
	@Enumerated(EnumType.STRING)
	@NonNull
	private GrupoMuscularENUM grpMusculos;
	@JsonIgnore
	private LocalDateTime dataCriacao;
	@JsonIgnore
	private LocalDateTime dataModificacao;
	@JsonIgnore
	private LocalDateTime dataExclusao;

	public Exercicio() {

	}
}
