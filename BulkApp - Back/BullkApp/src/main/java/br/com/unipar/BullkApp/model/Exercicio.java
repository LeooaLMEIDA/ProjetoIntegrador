package br.com.unipar.BullkApp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	@ApiModelProperty(required = true)
	private String descricao;
	@NonNull
	private String imgIlustracao;
	@NonNull
	private String vdInstrucao;
	private boolean status;
	@ManyToOne
	@NonNull
	private Aparelho aparelho;
	@Enumerated(EnumType.STRING)
	@NonNull
	private GrupoMuscularENUM grpMusculos;

	public Exercicio() {

	}
}
