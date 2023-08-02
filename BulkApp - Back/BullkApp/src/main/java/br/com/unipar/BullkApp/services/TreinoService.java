package br.com.unipar.BullkApp.services;

import br.com.unipar.BullkApp.model.Treino;
import br.com.unipar.BullkApp.repositories.TreinoRepository;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ApiModel(description = "Classe responsável pela regras de Negócio referente ao Treino")
public class TreinoService {

    @Autowired
    private TreinoRepository treinoRepository;

    @Autowired
    private ExercicioService exercicioService;

    @Autowired
    private UsuarioService usuarioService;

    public Treino insert(Treino treino) throws Exception{
        treino.setStatus(true);

        treino.setExercicio(exercicioService.findById(treino.getExercicio().getId()));

        treino.setUsuario(usuarioService.findById(treino.getUsuario().getId()));

        treinoRepository.saveAndFlush(treino);

        return treino;
    }

    public Treino update(Treino treino) throws Exception {
        validaUpdate(treino);
        treinoRepository.saveAndFlush(treino);
        return treino;
    }

    public Treino delete(Long id) throws  Exception {
        Treino treino = findById(id);
        validaUpdate(treino);
        treino.setStatus(false);
        treinoRepository.saveAndFlush(treino);
        return treino;
    }

    public Treino findById(Long id) throws Exception{
        Optional<Treino> retorno = treinoRepository.findById(id);
        if (retorno.isPresent()){
            return retorno.get();
        }
        else {
            throw new Exception("Treino " + id + " não encontrado");
        }
    }

    public List<Treino> findByFilters(String cdTreino) throws Exception{
        return treinoRepository.findByCdTreinoContainingAllIgnoringCase(cdTreino);
    }

    public List<Treino> findAll() throws Exception{
        return treinoRepository.findAll();
    }

    private void validaInsert(Treino treino) throws Exception{
        if (treino.getId() != null){
            throw new Exception("Não é necessário informar o ID para inserir um novo Treino");
        }
    }

    private void validaUpdate(Treino treino) throws Exception{
        if (treino.getId() == null){
            throw new Exception("É necessário informar o ID para atualizar o cadastro do Treino");
        }
    }
}
