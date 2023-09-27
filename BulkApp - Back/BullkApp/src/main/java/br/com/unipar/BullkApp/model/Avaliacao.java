package br.com.unipar.BullkApp.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Type;
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
	@Lob
	private byte[] arqAvaliacao;
	private String arqType;
	private String arqName;
	@OneToOne
	@NonNull
	private Usuario usuario;
	@JsonIgnore
	private LocalDateTime dataCriacao;
	@JsonIgnore
	private LocalDateTime dataModificacao;
	@JsonIgnore
	private LocalDateTime dataExclusao;

	public Avaliacao() {

	}
}
