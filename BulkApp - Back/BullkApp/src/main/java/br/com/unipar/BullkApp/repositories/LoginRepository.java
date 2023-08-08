package br.com.unipar.BullkApp.repositories;

import br.com.unipar.BullkApp.model.Usuario;
import io.swagger.annotations.ApiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginRepository extends JpaRepository<Usuario, Long> {
        @Query
        Usuario findByEmailIsContainingIgnoreCase(String email);
}