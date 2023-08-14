package br.com.unipar.BullkApp.repositories;

import br.com.unipar.BullkApp.model.DTO.UsuarioDTO;
import br.com.unipar.BullkApp.model.Usuario;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginRepository extends JpaRepository<Usuario, Long> {
        @Query
        @ApiModelProperty(value = "Método utilizado para Consultar um Usuário pelo e-mail")
        Usuario findByEmailIsContainingIgnoreCase(String email);
}