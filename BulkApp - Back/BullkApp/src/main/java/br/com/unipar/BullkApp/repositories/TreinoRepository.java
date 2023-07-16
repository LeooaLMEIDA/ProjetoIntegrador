package br.com.unipar.BullkApp.repositories;

import java.util.List;

import io.swagger.annotations.ApiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.unipar.BullkApp.model.Treino;

@Repository
@ApiModel(description = "Classe responsável pela comunicação com o Banco de Dados, referente aos Treinos")
public interface TreinoRepository extends JpaRepository<Treino, Long> {
	@Query
	public List<Treino> findByCdTreinoContainingAllIgnoringCase(String cdTreino);
}
