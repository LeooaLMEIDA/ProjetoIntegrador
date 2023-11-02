package br.com.unipar.BullkApp.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

import br.com.unipar.BullkApp.model.DTO.AvaliacaoDTO;
import br.com.unipar.BullkApp.util.Util;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.lang.NonNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.IOException;
import java.time.LocalDateTime;

@Entity
@Table(name = "BULLK_AVALIACAO")
@ApiModel(description = "Modelo para Representação de uma Avaliação Corporal")
@Data
public class Avaliacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Id Autogerado pelo sistema")
	private Long id;
	@NonNull
	@Size(max = 10)
	private String descricao;
	private String observacao;
	@NonNull
	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] arqAvaliacao;
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

	public static Avaliacao consultaDTO(AvaliacaoDTO avaliacaoDTO) throws IOException {
		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setId(avaliacaoDTO.getId());
		avaliacao.setDescricao(avaliacaoDTO.getDescricao());
		avaliacao.setObservacao(avaliacaoDTO.getObservacao());
		avaliacao.setArqAvaliacao(Util.compressData(avaliacaoDTO.getArqAvaliacao()));

		return avaliacao;
	}
}
