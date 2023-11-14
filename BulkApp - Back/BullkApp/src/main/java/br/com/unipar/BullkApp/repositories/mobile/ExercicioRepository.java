package br.com.unipar.BullkApp.repositories.mobile;

import java.util.List;

import br.com.unipar.BullkApp.enums.GrupoMuscularENUM;
import br.com.unipar.BullkApp.model.Aparelho;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.unipar.BullkApp.model.Exercicio;

@Repository
@ApiModel(description = "Classe responsável pela comunicação com o Banco de Dados, referente aos Exercícios")
public interface ExercicioRepository extends JpaRepository<Exercicio, Long> {
	@Query
	@ApiModelProperty(value = "Método utilizado para Consultar um Exercício pela descrição")
	public List<Exercicio> findByDescricaoContainingAllIgnoringCaseOrderById(String descricao);

	@Query
	@ApiModelProperty(value = "Método utilizado para Consultar um Exercício pelo Aparelho")
    public List<Exercicio> findByAparelhoOrderById(Aparelho aparelho);

	@Query
	@ApiModelProperty(value = "Método utilizado para Consultar um Exercício pelo Grupo Muscular")
	public List<Exercicio> findByGrpMusculosOrderById(GrupoMuscularENUM grupoMuscular);

	@Query
	@ApiModelProperty(value = "Método utilizado para Consultar um Exercício pelo Status")
	public List<Exercicio> findByStatusOrderById(boolean status);

	@Query
	public List<Exercicio> findAllByOrderById();
}
