package br.com.unipar.BullkApp.services;

import br.com.unipar.BullkApp.model.DTO.TreinoDTO;
import br.com.unipar.BullkApp.model.Exercicio;
import br.com.unipar.BullkApp.model.Treino;
import br.com.unipar.BullkApp.model.Usuario;
import br.com.unipar.BullkApp.repositories.TreinoRepository;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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

        treino.setDataCriacao(LocalDateTime.now());
        treino.setDataModificacao(LocalDateTime.now());

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
        treino1.setAlternativo(treino.isAlternativo() == true ? treino.isAlternativo() : treino1.isAlternativo());

        treino1.setDataModificacao(LocalDateTime.now());

        treinoRepository.saveAndFlush(treino1);
        return treino1;
    }

    public Treino delete(Long id) throws  Exception {
        Treino treino = findById(id);
        validaUpdate(treino);
        treino.setStatus(false);
        treino.setDataExclusao(LocalDateTime.now());
        treino.setDataModificacao(LocalDateTime.now());
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

    public List<Treino> findByFiltersCdTreino(String cdTreino) throws Exception{
        return treinoRepository.findByCdTreinoContainingAllIgnoringCase(cdTreino);
    }

    public List<TreinoDTO> findByFiltersUsuarioTreino(String cdTreino, Long usuario_id) throws Exception{
        Usuario usuario = usuarioService.findById(usuario_id);

        List<Treino> treinos = treinoRepository.findByCdTreinoContainingAllIgnoringCaseAndUsuario(cdTreino, usuario);

        List<TreinoDTO> treinosAtivos = new ArrayList<TreinoDTO>();

        for (Treino treino : treinos) {
            if (treino.isStatus() && !treino.isAlternativo()){
                treinosAtivos.add(TreinoDTO.consultaDTO(treino));
            }
        }

        return treinosAtivos;
    }

    public List<TreinoDTO> findByUsuario(Long id) throws Exception{
        List<Treino> treinos = treinoRepository.findByUsuario(usuarioService.findById(id));

        List<TreinoDTO> treinosAtivos = new ArrayList<TreinoDTO>();

        for (Treino treino : treinos) {
            if (treino.isStatus() && !treino.isAlternativo()){
                treinosAtivos.add(TreinoDTO.consultaDTO(treino));
            }
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

    public TreinoDTO findAlternativo(Long idTreino) throws Exception {
        Treino treino1 = findById(idTreino);

        List<Treino> treinos = findByUsuario(treino1.getUsuario());

        List<TreinoDTO> treinoDTOS = new ArrayList<>();

        for (Treino treino : treinos) {
            if (treino.isAlternativo() && treino.isStatus() && treino1.getExercicio().getGrpMusculos() == treino.getExercicio().getGrpMusculos()) {
                treinoDTOS.add(TreinoDTO.consultaDTO(treino));
            }
        }

        if (treinoDTOS.size() == 0)
            return null;
        else if (treinoDTOS.size() == 1)
            return treinoDTOS.get(0);
        else
            return treinoDTOS.get(new Random().nextInt(treinoDTOS.size()));
    }
}
