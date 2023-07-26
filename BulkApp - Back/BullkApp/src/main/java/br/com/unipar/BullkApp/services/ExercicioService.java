package br.com.unipar.BullkApp.services;

import br.com.unipar.BullkApp.model.Exercicio;
import br.com.unipar.BullkApp.repositories.ExercicioRepository;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ApiModel(description = "Classe responsável pela regras de Negócio referente ao Exercicio")
public class ExercicioService {

    @Autowired
    private ExercicioRepository exercicioRepository;

    public Exercicio insert(Exercicio exercicio) throws Exception{
        exercicio.setStatus(true);
        exercicioRepository.saveAndFlush(exercicio);
        return exercicio;
    }

    public Exercicio update(Exercicio exercicio) throws Exception {
        validaUpdate(exercicio);
        exercicioRepository.saveAndFlush(exercicio);
        return exercicio;
    }

    public Exercicio findById(Long id) throws Exception{
        Optional<Exercicio> retorno = exercicioRepository.findById(id);
        if (retorno.isPresent()){
            return retorno.get();
        }
        else {
            throw new Exception("Exercicio " + id + " não encontrado");
        }
    }

    public List<Exercicio> findByFilters(String nome) throws Exception{
        return exercicioRepository.findByDescricaoContainingAllIgnoringCase(nome);
    }

    public List<Exercicio> findAll() throws Exception{
        return exercicioRepository.findAll();
    }

    private void validaInsert(Exercicio exercicio) throws Exception{
        if (exercicio.getId() != null){
            throw new Exception("Não é necessário informar o ID para inserir um novo Exercicio");
        }
    }

    private void validaUpdate(Exercicio exercicio) throws Exception{
        if (exercicio.getId() == null){
            throw new Exception("É necessário informar o ID para atualizar o cadastro do Exercicio");
        }
    }
}
