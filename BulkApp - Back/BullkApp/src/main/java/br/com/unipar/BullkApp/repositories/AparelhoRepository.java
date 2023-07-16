package br.com.unipar.BullkApp.repositories;

import java.util.List;

import io.swagger.annotations.ApiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.unipar.BullkApp.model.Aparelho;

@Repository
@ApiModel(description = "Classe responsável pela comunicação com o Banco de Dados, referente aos Aparelhos")
public interface AparelhoRepository extends JpaRepository<Aparelho, Long> {
	@Query
	public List<Aparelho> findByDescricaoContainingAllIgnoringCase(String descricao);
}
