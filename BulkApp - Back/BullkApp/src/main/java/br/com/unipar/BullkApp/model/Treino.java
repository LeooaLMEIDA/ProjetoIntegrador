package br.com.unipar.BullkApp.model;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.persistence.*;

import br.com.unipar.BullkApp.model.DTO.TreinoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.lang.NonNull;

import br.com.unipar.BullkApp.enums.CdTreinoENUM;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "BULLK_TREINO")
@ApiModel(description = "Modelo para Representação de um Treino")
@Data
public class Treino {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Id Autogerado pelo sistema")
	private Long id;
	@Enumerated(EnumType.STRING)
	private CdTreinoENUM cdTreino;
	@NonNull
	private int series;
	@NonNull
	private int repeticoes;
	@NonNull
	private Time descanso;
	@NonNull
	private boolean status;
	@NonNull
	private Integer peso;
	private boolean alternativo;
	@ManyToOne
	private Exercicio exercicio;
	@ManyToOne
	private Usuario usuario;
	@JsonIgnore
	private LocalDateTime dataCriacao;
	@JsonIgnore
	private LocalDateTime dataModificacao;
	@JsonIgnore
	private LocalDateTime dataExclusao;

	public Treino() {

	}

	public static Treino consultaDTO(TreinoDTO treinoDTO) {
		Treino treino = new Treino();
		treino.setId(treinoDTO.getId());
		treino.setCdTreino(treinoDTO.getCdTreino());
		treino.setSeries(treinoDTO.getSeries());
		treino.setRepeticoes(treinoDTO.getRepeticoes());
		treino.setDescanso(treinoDTO.getDescanso());
		treino.setStatus(treinoDTO.isStatus());
		treino.setPeso(treinoDTO.getPeso());
		treino.setAlternativo(treinoDTO.isAlternativo());

		return treino;
	}
}
