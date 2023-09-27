package br.com.unipar.BullkApp.model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.NonNull;

import br.com.unipar.BullkApp.enums.SexoENUM;
import br.com.unipar.BullkApp.enums.TipoUsuarioENUM;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.RequestMapping;

@Entity
@Table(name = "BULLK_USUARIO")
@ApiModel(description = "Modelo para Representação de um Usuario")
@Data
@AllArgsConstructor
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
	@Email
	private String email;
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoUsuarioENUM tpUsuario;
	@Lob
	private byte[] urlAvatar;
	private String mediaType;
	@NotNull
	private boolean status;
	@NotNull
	private String senha;
	@JsonIgnore
	private LocalDateTime dataCriacao;
	@JsonIgnore
	private LocalDateTime dataModificacao;
	@JsonIgnore
	private LocalDateTime dataExclusao;


	public Usuario() {

	}
}