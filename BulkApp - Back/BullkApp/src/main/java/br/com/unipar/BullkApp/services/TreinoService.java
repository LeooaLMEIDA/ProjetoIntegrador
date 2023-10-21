package br.com.unipar.BullkApp.services;

import br.com.unipar.BullkApp.model.DTO.ExercicioDTO;
import br.com.unipar.BullkApp.model.DTO.TreinoDTO;
import br.com.unipar.BullkApp.model.Exercicio;
import br.com.unipar.BullkApp.model.Treino;
import br.com.unipar.BullkApp.model.Usuario;
import br.com.unipar.BullkApp.repositories.mobile.TreinoRepository;
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

    public TreinoDTO insert(Treino treino) throws Exception{

        treino.setExercicio(exercicioService.findById(treino.getExercicio().getId()));

        treino.setUsuario(usuarioService.findById(treino.getUsuario().getId()));

        validaInsert(treino);

        treino.setDataCriacao(LocalDateTime.now());
        treino.setDataModificacao(LocalDateTime.now());

        treinoRepository.saveAndFlush(treino);

        return TreinoDTO.consultaDTO(treino);
    }

    public Treino update(Treino treino) throws Exception {
        validaUpdate(treino);

        Treino treino1 = findByIdentificador(treino.getId());

        treino1.setCdTreino(treino.getCdTreino());
        treino1.setStatus(treino.isStatus());
        treino1.setExercicio(exercicioService.findById(treino.getExercicio().getId()));
        treino1.setPeso(treino.getPeso());
        treino1.setUsuario(usuarioService.findById(treino.getUsuario().getId()));
        treino1.setDescanso(treino.getDescanso());
        treino1.setRepeticoes(treino.getRepeticoes());
        treino1.setSeries(treino.getSeries());
        treino1.setAlternativo(treino.isAlternativo());

        treino1.setDataModificacao(LocalDateTime.now());

        if (treino1.isStatus())
            treino1.setDataExclusao(null);

        treinoRepository.saveAndFlush(treino1);
        return treino1;
    }

    public Treino delete(Long id) throws  Exception {
        Treino treino = findByIdentificador(id);
        validaUpdate(treino);
        treino.setStatus(false);
        treino.setDataExclusao(LocalDateTime.now());
        treino.setDataModificacao(LocalDateTime.now());
        treinoRepository.saveAndFlush(treino);
        return treino;
    }

    public TreinoDTO findById(Long id) throws Exception{
        Optional<Treino> retorno = treinoRepository.findById(id);
        if (retorno.isPresent()){
            return new TreinoDTO().consultaDTO(retorno.get());
        }
        else {
            throw new Exception("Treino " + id + " não encontrado");
        }
    }

    private Treino findByIdentificador(Long id) throws Exception{
        Optional<Treino> retorno = treinoRepository.findById(id);
        if (retorno.isPresent()){
            return retorno.get();
        }
        else {
            throw new Exception("Treino " + id + " não encontrado");
        }
    }

    public List<TreinoDTO> findByFiltersCdTreino(String cdTreino) throws Exception{
        List<Treino> treinos = treinoRepository.findByCdTreinoContainingAllIgnoreCase(cdTreino);

        List<TreinoDTO> treinoDTOS = new ArrayList<>();

        for (Treino treino : treinos) {
            treinoDTOS.add(new TreinoDTO().consultaDTO(treino));
        }

        return treinoDTOS;
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

    public List<TreinoDTO> findAll() throws Exception{
        List<Treino> treinos = treinoRepository.findAll();

        List<TreinoDTO> treinoDTOS = new ArrayList<>();

        for (Treino treino : treinos) {
            treinoDTOS.add(new TreinoDTO().consultaDTO(treino));
        }

        return treinoDTOS;
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
        Treino treino1 = findByIdentificador(idTreino);

        List<Treino> treinos = findByUsuario(treino1.getUsuario());

        List<TreinoDTO> treinoDTOS = new ArrayList<>();

        for (Treino treino : treinos) {
            if (treino.isAlternativo() && treino.isStatus() && treino1.getExercicio().getGrpMusculos() == treino.getExercicio().getGrpMusculos()) {
                treinoDTOS.add(TreinoDTO.consultaDTO(treino));
            }
        }
        ExercicioDTO exercicioDTO = new ExercicioDTO();
        exercicioDTO.setDescricao("Nenhum treino alternativo para o treino atual");

        TreinoDTO treinoDTO = new TreinoDTO();
        treinoDTO.setId(-1L);
        treinoDTO.setCdTreino(treino1.getCdTreino());
        treinoDTO.setExercicio(exercicioDTO);

        if (treinoDTOS.size() == 0)
            return treinoDTO;
        else if (treinoDTOS.size() == 1)
            return treinoDTOS.get(0);
        else
            return treinoDTOS.get(new Random().nextInt(treinoDTOS.size()));
    }
}
