package br.com.unipar.BullkApp.model;

import br.com.unipar.BullkApp.enums.SexoENUM;
import br.com.unipar.BullkApp.enums.TipoUsuarioENUM;
import br.com.unipar.BullkApp.model.DTO.UsuarioDTO;
import br.com.unipar.BullkApp.model.DTO.UsuarioWebDTO;
import br.com.unipar.BullkApp.util.Util;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.IOException;
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
	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] urlAvatar;
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

	public static Usuario consultaDTO(UsuarioDTO usuarioDTO) throws IOException {
		Usuario usuario = new Usuario();
		usuario.setId(usuarioDTO.getId());
		usuario.setNome(usuarioDTO.getNome());
		usuario.setDtNascimento(usuarioDTO.getDtNascimento());
		usuario.setSexo(usuarioDTO.getSexo());
		usuario.setCelular(usuarioDTO.getCelular());
		usuario.setEmail(usuarioDTO.getEmail());
		usuario.setTpUsuario(usuarioDTO.getTpUsuario());
		if (usuarioDTO.getUrlAvatar() != null)
			usuario.setUrlAvatar(Util.compressData(usuarioDTO.getUrlAvatar()));
		usuario.setStatus(usuarioDTO.isStatus());

		return usuario;
	}

	public static Usuario consultaDTO(UsuarioWebDTO usuarioDTO) throws IOException {
		Usuario usuario = new Usuario();
		usuario.setId(usuarioDTO.getId());
		usuario.setNome(usuarioDTO.getNome());
		usuario.setDtNascimento(usuarioDTO.getDtNascimento());
		usuario.setSexo(usuarioDTO.getSexo());
		usuario.setCelular(usuarioDTO.getCelular());
		usuario.setEmail(usuarioDTO.getEmail());
		usuario.setTpUsuario(usuarioDTO.getTpUsuario());
		if (usuarioDTO.getUrlAvatar() != null)
			usuario.setUrlAvatar(Util.compressData(usuarioDTO.getUrlAvatar()));
		usuario.setStatus(usuarioDTO.isStatus());
		usuario.setSenha(usuarioDTO.getSenha());

		return usuario;
	}
}