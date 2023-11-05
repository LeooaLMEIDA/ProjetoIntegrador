package br.com.unipar.BullkApp.services;

import br.com.unipar.BullkApp.enums.CdTreinoENUM;
import br.com.unipar.BullkApp.model.DTO.*;
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

    public TreinoDTO insert(TreinoDTO treinoDTO) throws Exception{
        Treino treino = Treino.consultaDTO(treinoDTO);

        treino.setExercicio(exercicioService.findById(treinoDTO.getIdExercicio()));

        treino.setUsuario(Usuario.consultaDTO(usuarioService.findById(treinoDTO.getIdUsuario())));

        validaInsert(treino);

        treino.setDataCriacao(LocalDateTime.now());
        treino.setDataModificacao(LocalDateTime.now());

        treinoRepository.saveAndFlush(treino);

        return TreinoDTO.consultaDTO(treino);
    }

    public TreinoDTO update(TreinoDTO treinoDTO) throws Exception {
        Treino treino = Treino.consultaDTO(treinoDTO);

        treino.setUsuario(Usuario.consultaDTO(usuarioService.findById(treinoDTO.getIdUsuario())));
        treino.setExercicio(exercicioService.findById(treinoDTO.getIdExercicio()));

        validaUpdate(treino);

        treino.setDataModificacao(LocalDateTime.now());

        if (treino.isStatus())
            treino.setDataExclusao(null);

        treinoRepository.saveAndFlush(treino);
        return TreinoDTO.consultaDTO(treino);
    }

    public TreinoDTO delete(Long id) throws  Exception {
        Treino treino = findByIdentificador(id);
        validaUpdate(treino);
        treino.setStatus(false);
        treino.setDataExclusao(LocalDateTime.now());
        treino.setDataModificacao(LocalDateTime.now());
        treinoRepository.saveAndFlush(treino);
        return TreinoDTO.consultaDTO(treino);
    }

    public TreinoDTO findById(Long id) throws Exception{
        Optional<Treino> retorno = treinoRepository.findById(id);
        if (retorno.isPresent()){
            return TreinoDTO.consultaDTO(retorno.get());
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
        CdTreinoENUM cdTreinoENUM = CdTreinoENUM.valueOf(cdTreino);
        List<Treino> treinos = treinoRepository.findByCdTreino(cdTreinoENUM);

        List<TreinoDTO> treinoDTOS = new ArrayList<>();

        for (Treino treino : treinos) {
            treinoDTOS.add(TreinoDTO.consultaDTO(treino));
        }

        return treinoDTOS;
    }

    public List<TreinoDTO> findByFiltersUsuarioTreino(String cdTreino, Long usuario_id) throws Exception{
        Usuario usuario = Usuario.consultaDTO(usuarioService.findById(usuario_id));

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
        List<Treino> treinos = treinoRepository.findByUsuario(Usuario.consultaDTO(usuarioService.findById(id)));

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

        List<TreinoDTO> treinoWebDTOS = new ArrayList<>();

        for (Treino treino : treinos) {
            treinoWebDTOS.add(TreinoDTO.consultaDTO(treino));
        }

        return treinoWebDTOS;
    }

    public List<TreinoDTO> findByExercicio(Long id) throws Exception{
        List<TreinoDTO> treinoDTOS = new ArrayList<>();

        for (Treino treino:treinoRepository.findByExercicio(exercicioService.findById(id))) {
            treinoDTOS.add(TreinoDTO.consultaDTO(treino));
        }
        return treinoDTOS;
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
        treinoDTO.setIdExercicio(exercicioDTO.getId());

        if (treinoDTOS.size() == 0)
            return treinoDTO;
        else if (treinoDTOS.size() == 1)
            return treinoDTOS.get(0);
        else
            return treinoDTOS.get(new Random().nextInt(treinoDTOS.size()));
    }

    public PageableDTO findAllPageable(int page, int registrosSolic) throws Exception {
        List<TreinoDTO> treinoDTOS = findAll();

        List<TreinoDTO> treinoDTOSRetorno = new ArrayList<>();

        int inicio = 0;
        int fim = registrosSolic;

        if (page > 1) {
            inicio = inicio + registrosSolic * (page - 1);
            fim = page * registrosSolic;
        }

        if (treinoDTOS.size() < inicio) {
            throw new Exception("Não há elementos na página informada!");
        } else if (treinoDTOS.size() < fim) {
            fim = treinoDTOS.size();
        }

        for (int i = inicio; i < fim; i++) {
            treinoDTOSRetorno.add(treinoDTOS.get(i));
        }

        PageableDTO pageableDTO = new PageableDTO(new ArrayList<Object>(treinoDTOSRetorno), page, treinoDTOS.size());
        return pageableDTO;
    }

    public PageableDTO findByCdTreinoPageable(String cdTreino, int page, int registrosSolic) throws Exception {
        List<TreinoDTO> treinoDTOS = new ArrayList<>();

        for (TreinoDTO treinoWebDTO:findAll()) {
            if (treinoWebDTO.getCdTreino().equals(CdTreinoENUM.valueOf(cdTreino)))
                treinoDTOS.add(treinoWebDTO);
        }

        List<TreinoDTO> treinoDTOSRetorno = new ArrayList<>();

        int inicio = 0;
        int fim = registrosSolic;

        if (page > 1) {
            inicio = inicio + registrosSolic * (page - 1);
            fim = page * registrosSolic;
        }

        if (treinoDTOS.size() < inicio) {
            throw new Exception("Não há elementos na página informada!");
        } else if (treinoDTOS.size() < fim) {
            fim = treinoDTOS.size();
        }

        for (int i = inicio; i < fim; i++) {
            treinoDTOSRetorno.add(treinoDTOS.get(i));
        }

        PageableDTO pageableDTO = new PageableDTO(new ArrayList<Object>(treinoDTOSRetorno), page, treinoDTOS.size());
        return pageableDTO;
    }

    public PageableDTO findByExercicioPageable(String exercicio, int page, int registrosSolic) throws Exception {
        List<TreinoDTO> treinoDTOS = new ArrayList<>();

        for (ExercicioDTO exercicioDTO:exercicioService.findAll()) {
            if (exercicioDTO.getDescricao().toUpperCase().contains(exercicio.toUpperCase()))
                treinoDTOS.addAll(findByExercicio(exercicioDTO.getId()));
        }

        List<TreinoDTO> treinoDTOSRetorno = new ArrayList<>();

        int inicio = 0;
        int fim = registrosSolic;

        if (page > 1) {
            inicio = inicio + registrosSolic * (page - 1);
            fim = page * registrosSolic;
        }

        if (treinoDTOS.size() < inicio) {
            throw new Exception("Não há elementos na página informada!");
        } else if (treinoDTOS.size() < fim) {
            fim = treinoDTOS.size();
        }

        for (int i = inicio; i < fim; i++) {
            treinoDTOSRetorno.add(treinoDTOS.get(i));
        }

        PageableDTO pageableDTO = new PageableDTO(new ArrayList<Object>(treinoDTOSRetorno), page, treinoDTOS.size());
        return pageableDTO;
    }

    public PageableDTO findByUsuarioPageable(String usuario, int page, int registrosSolic) throws Exception {
        List<TreinoDTO> treinoDTOS = new ArrayList<>();

        for (UsuarioDTO usuarioDTO:usuarioService.findAll()) {
            if (usuarioDTO.getNome().toUpperCase().contains(usuario.toUpperCase()))
                for (Treino treino:treinoRepository.findByUsuario(Usuario.consultaDTO(usuarioService.findById(usuarioDTO.getId())))) {
                    treinoDTOS.add(TreinoDTO.consultaDTO(treino));
                }
        }
        List<TreinoDTO> treinoDTOSRetorno = new ArrayList<>();

        int inicio = 0;
        int fim = registrosSolic;

        if (page > 1) {
            inicio = inicio + registrosSolic * (page - 1);
            fim = page * registrosSolic;
        }

        if (treinoDTOS.size() < inicio) {
            throw new Exception("Não há elementos na página informada!");
        } else if (treinoDTOS.size() < fim) {
            fim = treinoDTOS.size();
        }

        for (int i = inicio; i < fim; i++) {
            treinoDTOSRetorno.add(treinoDTOS.get(i));
        }

        PageableDTO pageableDTO = new PageableDTO(new ArrayList<Object>(treinoDTOSRetorno), page, treinoDTOS.size());
        return pageableDTO;
    }

    public PageableDTO findByStatusPageable(boolean status, int page, int registrosSolic) throws Exception {
        List<TreinoDTO> treinoDTOS = new ArrayList<>();

        for (TreinoDTO treinoWebDTO:findAll()) {
            if (treinoWebDTO.isStatus() == status)
                treinoDTOS.add(treinoWebDTO);
        }
        List<TreinoDTO> treinoDTOSRetorno = new ArrayList<>();

        int inicio = 0;
        int fim = registrosSolic;

        if (page > 1) {
            inicio = inicio + registrosSolic * (page - 1);
            fim = page * registrosSolic;
        }

        if (treinoDTOS.size() < inicio) {
            throw new Exception("Não há elementos na página informada!");
        } else if (treinoDTOS.size() < fim) {
            fim = treinoDTOS.size();
        }

        for (int i = inicio; i < fim; i++) {
            treinoDTOSRetorno.add(treinoDTOS.get(i));
        }

        PageableDTO pageableDTO = new PageableDTO(new ArrayList<Object>(treinoDTOSRetorno), page, treinoDTOS.size());
        return pageableDTO;
    }

    public PageableDTO findByAlternativoPageable(boolean alternativo, int page, int registrosSolic) throws Exception {
        List<TreinoDTO> treinoDTOS = new ArrayList<>();

        for (TreinoDTO treinoWebDTO:findAll()) {
            if (treinoWebDTO.isAlternativo() == alternativo)
                treinoDTOS.add(treinoWebDTO);
        }
        List<TreinoDTO> treinoDTOSRetorno = new ArrayList<>();

        int inicio = 0;
        int fim = registrosSolic;

        if (page > 1) {
            inicio = inicio + registrosSolic * (page - 1);
            fim = page * registrosSolic;
        }

        if (treinoDTOS.size() < inicio) {
            throw new Exception("Não há elementos na página informada!");
        } else if (treinoDTOS.size() < fim) {
            fim = treinoDTOS.size();
        }

        for (int i = inicio; i < fim; i++) {
            treinoDTOSRetorno.add(treinoDTOS.get(i));
        }

        PageableDTO pageableDTO = new PageableDTO(new ArrayList<Object>(treinoDTOSRetorno), page, treinoDTOS.size());
        return pageableDTO;
    }
}
