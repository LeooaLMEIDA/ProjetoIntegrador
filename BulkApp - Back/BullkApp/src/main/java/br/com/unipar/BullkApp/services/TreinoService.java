package br.com.unipar.BullkApp.services;

import br.com.unipar.BullkApp.model.Exercicio;
import br.com.unipar.BullkApp.model.Treino;
import br.com.unipar.BullkApp.model.Usuario;
import br.com.unipar.BullkApp.repositories.TreinoRepository;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        validaInsert(treino);

        treinoRepository.saveAndFlush(treino);

        return treino;
    }

    public Treino update(Treino treino) throws Exception {
        validaUpdate(treino);

        Treino treino1 = findById(treino.getId());

        treino1.setCdTreino(treino.getCdTreino() != null ? treino.getCdTreino() : treino1.getCdTreino());
        treino1.setStatus(treino.isStatus() == true ? treino.isStatus() : treino1.isStatus());
        treino1.setExercicio(treino.getExercicio() != null ? exercicioService.findById(treino.getExercicio().getId()) : treino1.getExercicio());
        treino1.setPeso(treino.getPeso() != null ? treino.getPeso() : treino1.getPeso());
        treino1.setUsuario(treino.getUsuario() != null ? usuarioService.findById(treino.getUsuario().getId()) : treino1.getUsuario());
        treino1.setDescanso(treino.getDescanso() != null ? treino.getDescanso() : treino1.getDescanso());
        treino1.setRepeticoes(treino.getRepeticoes() != 0 ? treino.getRepeticoes() : treino1.getRepeticoes());
        treino1.setSeries(treino.getSeries() != 0 ? treino.getSeries() : treino1.getSeries());

        treinoRepository.saveAndFlush(treino1);
        return treino1;
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

    public List<Treino> findByUsuario(Long id) throws Exception{
        List<Treino> treinos = treinoRepository.findByUsuario(usuarioService.findById(id));

        List<Treino> treinosAtivos = new ArrayList<>();

        for (Treino treino : treinos) {
            if (treino.isStatus())
                treinosAtivos.add(treino);
        }

        return treinosAtivos;
    }

    public List<Treino> findAll() throws Exception{
        return treinoRepository.findAll();
    }

    public List<Treino> findByExercicio(Exercicio exercicio) throws Exception{
        return treinoRepository.findByExercicio(exercicio);
    }

    private void validaInsert(Treino treino) throws Exception{
        if (treino.getId() != null){
            throw new Exception("Não é necessário informar o ID para inserir um novo Treino");
        }

        if (!treino.getExercicio().isStatus()) {
            throw new Exception("Não é possível inserir um Treino com um Exercício inativado");
        }

        if (!treino.getUsuario().isStatus()) {
            throw new Exception("Não é possível inserir um Treino com um Usuário inativado");
        }
    }

    private void validaUpdate(Treino treino) throws Exception{
        if (treino.getId() == null){
            throw new Exception("É necessário informar o ID para atualizar o cadastro do Treino");
        }
    }

    public List<Treino> findByUsuario(Usuario usuario) {
        return treinoRepository.findByUsuario(usuario);
    }
}
