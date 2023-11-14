package br.com.unipar.BullkApp.repositories.mobile;

import java.util.List;

import br.com.unipar.BullkApp.enums.CdTreinoENUM;
import br.com.unipar.BullkApp.model.Exercicio;
import br.com.unipar.BullkApp.model.Usuario;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.unipar.BullkApp.model.Treino;

@Repository
@ApiModel(description = "Classe responsável pela comunicação com o Banco de Dados, referente aos Treinos")
public interface TreinoRepository extends JpaRepository<Treino, Long> {
	@Query
	@ApiModelProperty(value = "Método utilizado para Consultar um Treino pelo Código do Treino")
	public List<Treino> findByCdTreinoOrderById(CdTreinoENUM cdTreino);

	@Query
	@ApiModelProperty(value = "Método utilizado para Consultar um Treino pelo Código e Usuário do Treino")
	public List<Treino> findByCdTreinoContainingAllIgnoringCaseAndUsuarioOrderById(String cdTreino, Usuario usuario);

	@Query
	@ApiModelProperty(value = "Método utilizado para Consultar um Treino pelo Exercício")
	public List<Treino> findByExercicioOrderById(Exercicio exercicio);

	@Query
	@ApiModelProperty(value = "Método utilizado para Consultar um Treino pelo Usuário")
	public List<Treino> findByUsuarioOrderById(Usuario usuario);

	@Query
	@ApiModelProperty(value = "Método utilizado para Consultar um Treino pelo Status")
	public List<Treino> findByStatusOrderById(boolean status);

	@Query
	@ApiModelProperty(value = "Método utilizado para Consultar um Treino pelo Tipo de Treino (alternativo)")
	public List<Treino> findByAlternativoOrderById(boolean status);

	@Query
	public List<Treino> findByOrderById();
}
