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
	@ApiModelProperty(value = "Método utilizado para Consultar um Usuário pelo nome")
	public List<Usuario> findByNomeContainsIgnoreCaseOrderById(String nome);

	@Query
	@ApiModelProperty(value = "Método utilizado para Consultar um Usuário pelo e-mail")
	public List<Usuario> findByEmailIgnoreCase(String email);

	@Query
	@ApiModelProperty(value = "Método utilizado para Consultar um Usuário que contém o e-mail")
	public List<Usuario> findByEmailIsContainingIgnoreCaseOrderById(String email);

	@Query
	@ApiModelProperty(value = "Método utilizado para Consultar um Usuário pelo Sexo")
	public List<Usuario> findBySexoOrderById(SexoENUM sexo);

	@Query
	@ApiModelProperty(value = "Método utilizado para Consultar um Usuário pelo Tipo De Usuário")
	public List<Usuario> findByTpUsuarioOrderById(TipoUsuarioENUM tpUsuario);

	@Query
	@ApiModelProperty(value = "Método utilizado para Consultar um Usuário pelo Celular")
	public List<Usuario> findByCelularContainingOrderById(String celular);

	@Query
	@ApiModelProperty(value = "Método utilizado para Consultar um Usuário pelo Status")
	public List<Usuario> findByStatusOrderById(boolean status);

	@Query
	public List<Usuario> findByOrderById();
}
