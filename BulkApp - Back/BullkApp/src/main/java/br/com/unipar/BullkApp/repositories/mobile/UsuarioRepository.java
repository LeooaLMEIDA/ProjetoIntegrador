package br.com.unipar.BullkApp.repositories.mobile;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.unipar.BullkApp.model.Usuario;

@Repository
@ApiModel(description = "Classe responsável pela comunicação com o Banco de Dados, referente aos Usuários")
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	@Query
	@ApiModelProperty(value = "Método utilizado para Consultar um Usuário pelo Nome")
	public List<Usuario> findByNomeContainingAllIgnoringCase(String nome);

	@Query
	@ApiModelProperty(value = "Método utilizado para Consultar um Usuário pelo e-mail")
	public List<Usuario> findByEmailIgnoreCase(String email);

	@Query
	public List<Usuario> findByNomeIsContainingIgnoreCase(String nome);

	@Query
	public List<Usuario> findBySexoContainingIgnoreCase(String sexo);

	@Query
	public List<Usuario> findByTpUsuarioContainingIgnoreCase(String tpUsuario);

	@Query
	public List<Usuario> findByCelularContainingIgnoreCase(String celular);
}
