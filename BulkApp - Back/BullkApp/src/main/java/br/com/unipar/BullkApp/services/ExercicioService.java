package br.com.unipar.BullkApp.services;

import br.com.unipar.BullkApp.model.Aparelho;
import br.com.unipar.BullkApp.model.Exercicio;
import br.com.unipar.BullkApp.repositories.ExercicioRepository;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@ApiModel(description = "Classe responsável pela regras de Negócio referente ao Exercicio")
public class ExercicioService {

    @Autowired
    private ExercicioRepository exercicioRepository;

    @Autowired
    private AparelhoService aparelhoService;

    public Exercicio insert(Exercicio exercicio) throws Exception{
        exercicio.setStatus(true);

        Aparelho aparelho = aparelhoService.findById(exercicio.getAparelho().getId());

        exercicio.setAparelho(aparelho);

        validaInsert(exercicio);

        exercicio.setDataCriacao(LocalDateTime.now());
        exercicio.setDataModificacao(LocalDateTime.now());

        exercicioRepository.saveAndFlush(exercicio);

        return exercicio;
    }

    public Exercicio update(Exercicio exercicio) throws Exception {
        validaUpdate(exercicio);

        Exercicio exercicio1 = findById(exercicio.getId());

        exercicio1.setDescricao(exercicio.getDescricao());
        exercicio1.setStatus(exercicio.isStatus());
        exercicio1.setAparelho(aparelhoService.findById(exercicio.getAparelho().getId()));
        exercicio1.setGrpMusculos(exercicio.getGrpMusculos());
        exercicio1.setImgIlustracao(exercicio.getImgIlustracao());
        exercicio1.setVdInstrucao(exercicio.getVdInstrucao());

        exercicio1.setDataModificacao(LocalDateTime.now());

        if (exercicio1.isStatus())
            exercicio1.setDataExclusao(null);

        exercicioRepository.saveAndFlush(exercicio1);
        return exercicio1;
    }

    public Exercicio delete(Long id) throws Exception {
        Exercicio exercicio = findById(id);
        validaUpdate(exercicio);
        exercicio.setStatus(false);
        exercicio.setDataExclusao(LocalDateTime.now());
        exercicio.setDataModificacao(LocalDateTime.now());
        exercicioRepository.saveAndFlush(exercicio);
        return exercicio;
    }

    public Exercicio findById(Long id) throws Exception{
        Optional<Exercicio> retorno = exercicioRepository.findById(id);
        if (retorno.isPresent()){
            Aparelho aparelho = aparelhoService.findById(retorno.get().getAparelho().getId());

            retorno.get().setAparelho(aparelho);

            return retorno.get();
        }
        else {
            throw new Exception("Exercicio " + id + " não encontrado");
        }
    }

    public List<Exercicio> findByFilters(String descricao) throws Exception{
        return exercicioRepository.findByDescricaoContainingAllIgnoringCase(descricao);
    }

    public List<Exercicio> findAll() throws Exception{
        return exercicioRepository.findAll();
    }

    private void validaInsert(Exercicio exercicio) throws Exception{
        if (exercicio.getId() != null){
            throw new Exception("Não é necessário informar o ID para inserir um novo Exercicio");
        }

        if (!exercicio.getAparelho().isStatus()){
            throw new Exception("Não é possível inserir um exercício com um Aparelho inativado");
        }


    }

    private void validaUpdate(Exercicio exercicio) throws Exception{
        if (exercicio.getId() == null){
            throw new Exception("É necessário informar o ID para atualizar o cadastro do Exercicio");
        }
    }

    public List<Exercicio> findByAparelho(Aparelho aparelho) {
        return exercicioRepository.findByAparelho(aparelho);
    }
}
