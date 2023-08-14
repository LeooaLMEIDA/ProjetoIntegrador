package br.com.unipar.BullkApp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import org.springframework.lang.NonNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

@Entity
@Table(name = "BULLK_AVALIACAO")
@ApiModel(description = "Modelo para Representação de uma Avaliação Corporal")
@Data
public class Avaliacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Id Autogerado pelo sistema")
	@NonNull
	private Long id;
	@NonNull
	private String descricao;
	private String observacao;
	@NonNull
	private String arqAvaliacao;
	@OneToOne
	@NonNull
	private Usuario usuario;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataModificacao;
	private LocalDateTime dataExclusao;

	public Avaliacao() {

	}
}
