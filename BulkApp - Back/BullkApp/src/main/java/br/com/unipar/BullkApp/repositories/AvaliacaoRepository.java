package br.com.unipar.BullkApp.repositories;

import java.util.List;

import br.com.unipar.BullkApp.model.Usuario;
import io.swagger.annotations.ApiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.unipar.BullkApp.model.Avaliacao;

@Repository
@ApiModel(description = "Classe responsável pela comunicação com o Banco de Dados, referente às Avaliações")
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
	@Query
	public List<Avaliacao> findByDescricaoContainingAllIgnoringCase(String descricao);

	@Query
    public List<Avaliacao> findByUsuario(Usuario usuario);
}
