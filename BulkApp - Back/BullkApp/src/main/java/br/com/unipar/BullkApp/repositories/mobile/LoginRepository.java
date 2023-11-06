package br.com.unipar.BullkApp.repositories.mobile;

import br.com.unipar.BullkApp.model.Usuario;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface LoginRepository extends JpaRepository<Usuario, Long> {
        @Query
        @Transactional
        @ApiModelProperty(value = "Método utilizado para Consultar um Usuário pelo e-mail")
        Usuario findByEmailIsIgnoreCase(String email);
}