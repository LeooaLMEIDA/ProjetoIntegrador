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
	@NonNull
	private Long id;
	@NonNull
	private String nome;
	@NonNull
	private Date dtNascimento;
	@NonNull
	@Enumerated(EnumType.STRING)
	private SexoENUM sexo;
	@NonNull
	private String celular;
	@NonNull
	private String email;
	@Enumerated(EnumType.STRING)
	private TipoUsuarioENUM tpUsuario;
	@NonNull
	private String urlAvatar = "x";
	@NonNull
	private boolean status;
	@NonNull
	private String senha;

	public Usuario() {

	}
}