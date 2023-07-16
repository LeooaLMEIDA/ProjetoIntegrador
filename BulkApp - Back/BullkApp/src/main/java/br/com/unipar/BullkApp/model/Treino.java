package br.com.unipar.BullkApp.model;

import java.sql.Time;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.springframework.lang.NonNull;

import br.com.unipar.BullkApp.enums.CdTreinoENUM;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "BULLK_TREINO")
@ApiModel(description = "Modelo para Representação de um Treino")
public class Treino {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Id Autogerado pelo sistema")
	@NonNull
	private Long id;
	@Enumerated(EnumType.STRING)
	@NonNull
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
	@ManyToOne(cascade = CascadeType.ALL)
	@NonNull
	private Exercicio exercicio;
	@OneToOne(cascade = CascadeType.ALL)
	@NonNull
	private Usuario usuario;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public CdTreinoENUM getCdTreino() {
		return cdTreino;
	}
	public void setCdTreino(CdTreinoENUM cdTreino) {
		this.cdTreino = cdTreino;
	}
	public int getSeries() {
		return series;
	}
	public void setSeries(int series) {
		this.series = series;
	}
	public int getRepeticoes() {
		return repeticoes;
	}
	public void setRepeticoes(int repeticoes) {
		this.repeticoes = repeticoes;
	}
	public Time getDescanso() {
		return descanso;
	}
	public void setDescanso(Time descanso) {
		this.descanso = descanso;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Exercicio getExercicio() {
		return exercicio;
	}
	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	@NonNull
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(@NonNull Usuario usuario) {
		this.usuario = usuario;
	}
}
