package br.com.unipar.BullkApp.model;

import br.com.unipar.BullkApp.enums.SexoENUM;
import br.com.unipar.BullkApp.enums.TipoUsuarioENUM;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.time.LocalDateTime;

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
//	@Lob
	@Column(name = "nome")
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
//	@Email
	private String email;
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoUsuarioENUM tpUsuario;
	@Lob
	@Column(name = "urlAvatar")
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