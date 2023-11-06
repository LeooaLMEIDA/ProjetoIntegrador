package br.com.unipar.BullkApp.repositories.mobile;

import java.util.List;

import br.com.unipar.BullkApp.enums.SexoENUM;
import br.com.unipar.BullkApp.enums.TipoUsuarioENUM;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import br.com.unipar.BullkApp.model.Usuario;

@Repository
@EnableJpaRepositories
@ApiModel(description = "Classe responsável pela comunicação com o Banco de Dados, referente aos Usuários")
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query
	@ApiModelProperty(value = "Método utilizado para Consultar um Usuário pelo e-mail")
	public List<Usuario> findByNomeContainsIgnoreCase(String nome);

	@Query
	@ApiModelProperty(value = "Método utilizado para Consultar um Usuário pelo e-mail")
	public List<Usuario> findByEmailIgnoreCase(String email);

	@Query
	@ApiModelProperty(value = "Método utilizado para Consultar um Usuário pelo e-mail")
	public List<Usuario> findByEmailIsContainingIgnoreCase(String email);

	@Query
	public List<Usuario> findBySexo(SexoENUM sexo);

	@Query
	public List<Usuario> findByTpUsuario(TipoUsuarioENUM tpUsuario);

	@Query
	public List<Usuario> findByCelularContaining(String celular);

	@Query
	public List<Usuario> findByStatus(boolean status);
}
