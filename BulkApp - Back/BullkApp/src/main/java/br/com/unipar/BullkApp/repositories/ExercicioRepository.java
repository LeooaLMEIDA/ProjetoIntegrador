package br.com.unipar.BullkApp.repositories;

import java.util.List;

import br.com.unipar.BullkApp.model.Aparelho;
import io.swagger.annotations.ApiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.unipar.BullkApp.model.Exercicio;

@Repository
@ApiModel(description = "Classe responsável pela comunicação com o Banco de Dados, referente aos Exercícios")
public interface ExercicioRepository extends JpaRepository<Exercicio, Long> {
	@Query
	public List<Exercicio> findByDescricaoContainingAllIgnoringCase(String descricao);

	@Query
    public List<Exercicio> findByAparelho(Aparelho aparelho);
}
