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

import org.springframework.lang.NonNull;

import br.com.unipar.BullkApp.enums.GrupoMuscularENUM;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "BULLK_EXERCICIO")
@ApiModel(description = "Modelo para Representação de um Exercicio")
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
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getImgIlustracao() {
		return imgIlustracao;
	}
	public void setImgIlustracao(String imgIlustracao) {
		this.imgIlustracao = imgIlustracao;
	}
	public String getVdInstrucao() {
		return vdInstrucao;
	}
	public void setVdInstrucao(String vdInstrucao) {
		this.vdInstrucao = vdInstrucao;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Aparelho getAparelho() {
		return aparelho;
	}
	public void setAparelho(Aparelho aparelho) {
		this.aparelho = aparelho;
	}
	public GrupoMuscularENUM getGrpMusculos() {
		return grpMusculos;
	}
	public void setGrpMusculos(GrupoMuscularENUM grpMuscular) {
		this.grpMusculos = grpMuscular;
	} 
}
