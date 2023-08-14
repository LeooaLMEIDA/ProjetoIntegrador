package br.com.unipar.BullkApp.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "BULLK_APARELHO")
@ApiModel(description = "Modelo para Representação de um Aparelho")
@Data
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
	private LocalDateTime dataCriacao;
	private LocalDateTime dataModificacao;
	private LocalDateTime dataExclusao;

	public Aparelho() {

	}
}
