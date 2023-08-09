package br.com.unipar.BullkApp.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import org.springframework.lang.NonNull;

import br.com.unipar.BullkApp.enums.SexoENUM;
import br.com.unipar.BullkApp.enums.TipoUsuarioENUM;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "BULLK_USUARIO")
@ApiModel(description = "Modelo para Representação de um Usuario")
@Data
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Id Autogerado pelo sistema")
	private Long id;
	@NotNull
	private String nome;
	@NotNull
	private Date dtNascimento;
	@NotNull
	@Enumerated(EnumType.STRING)
	private SexoENUM sexo;
	@NotNull
	private String celular;
	@NotNull
	private String email;
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoUsuarioENUM tpUsuario;
	@NotNull
	private String urlAvatar = "x";
	@NotNull
	private boolean status;
	@NotNull
	private String senha;

	public Usuario() {

	}
}