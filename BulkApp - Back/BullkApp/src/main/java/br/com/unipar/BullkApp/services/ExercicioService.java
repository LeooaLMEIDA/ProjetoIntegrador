package br.com.unipar.BullkApp.services;

import br.com.unipar.BullkApp.enums.GrupoMuscularENUM;
import br.com.unipar.BullkApp.model.Aparelho;
import br.com.unipar.BullkApp.model.DTO.ExercicioDTO;
import br.com.unipar.BullkApp.model.DTO.ExercicioListDTO;
import br.com.unipar.BullkApp.model.DTO.ExercicioWebDTO;
import br.com.unipar.BullkApp.model.DTO.PageableDTO;
import br.com.unipar.BullkApp.model.Exercicio;
import br.com.unipar.BullkApp.repositories.mobile.ExercicioRepository;
import br.com.unipar.BullkApp.util.Util;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;

@Service
@ApiModel(description = "Classe responsável pela regras de Negócio referente ao Exercicio")
public class ExercicioService {

    @Autowired
    private ExercicioRepository exercicioRepository;

    @Autowired
    private AparelhoService aparelhoService;

    public ExercicioWebDTO insert(ExercicioWebDTO exercicioDTO) throws Exception{
        Aparelho aparelho = aparelhoService.findById(exercicioDTO.getIdAparelho());

        Exercicio exercicio = Exercicio.consultaDTO(exercicioDTO);
        exercicio.setAparelho(aparelho);

        validaInsert(exercicio);

        exercicio.setDataCriacao(LocalDateTime.now());
        exercicio.setDataModificacao(LocalDateTime.now());

        exercicioRepository.saveAndFlush(exercicio);

        return ExercicioWebDTO.consultaDTO(exercicio);
    }

    public ExercicioWebDTO update(ExercicioWebDTO exercicioDTO) throws Exception {
        Exercicio exercicio = Exercicio.consultaDTO(exercicioDTO);

        exercicio.setAparelho(aparelhoService.findById(exercicioDTO.getIdAparelho()));

        validaUpdate(exercicio);

        exercicio.setDataModificacao(LocalDateTime.now());
        exercicio.setDataCriacao(exercicioRepository.findById(exercicio.getId()).get().getDataCriacao());

        if (exercicio.isStatus())
            exercicio.setDataExclusao(null);

        exercicioRepository.saveAndFlush(exercicio);
        return ExercicioWebDTO.consultaDTO(exercicio);
    }

    public Exercicio delete(Long id) throws Exception {
        Exercicio exercicio = findById(id);
        validaUpdate(exercicio);
        exercicio.setStatus(false);
        exercicio.setDataCriacao(exercicioRepository.findById(exercicio.getId()).get().getDataCriacao());
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

    public List<ExercicioDTO> findByFilters(String descricao) throws Exception{
        List<Exercicio> exercicios = exercicioRepository.findByDescricaoContainingAllIgnoringCase(descricao);

        List<ExercicioDTO> exercicioDTOS = new ArrayList<>();

        for (Exercicio exercicio : exercicios) {
            exercicioDTOS.add(ExercicioDTO.consultaDTO(exercicio));
        }
        return exercicioDTOS;
    }

    public List<ExercicioDTO> findByFilterGrpMuscular(String grupoMuscular) throws Exception{
        List<Exercicio> exercicios = exercicioRepository.findByGrpMusculos(GrupoMuscularENUM.valueOf(grupoMuscular));

        List<ExercicioDTO> exercicioDTOS = new ArrayList<>();

        for (Exercicio exercicio : exercicios) {
            exercicioDTOS.add(ExercicioDTO.consultaDTO(exercicio));
        }
        return exercicioDTOS;
    }

    public List<ExercicioDTO> findAll() throws Exception{
        List<Exercicio> exercicios = exercicioRepository.findAll();

        List<ExercicioDTO> exercicioDTOS = new ArrayList<>();

        for (Exercicio exercicio : exercicios) {
            exercicioDTOS.add(ExercicioDTO.consultaDTO(exercicio));
        }
        return exercicioDTOS;
    }

    private void validaInsert(Exercicio exercicio) throws Exception{
        if (exercicio.getId() != null){
            throw new Exception("Não é necessário informar o ID para inserir um novo Exercicio");
        }

        if (!exercicio.getAparelho().isStatus()){
            throw new Exception("Não é possível inserir um exercício com um Aparelho inativado");
        }

        if (!Util.getFileType(ExercicioDTO.consultaDTO(exercicio).getImgIlistracao().substring(0,6)).contains("GIF")){
            throw new Exception("Não é possível inserir um exercício com uma ilustração diferente de um GIF!");
        }
    }

    private void validaUpdate(Exercicio exercicio) throws Exception{
        if (exercicio.getId() == null){
            throw new Exception("É necessário informar o ID para atualizar o cadastro do Exercicio");
        }

        if (!Util.getFileType(ExercicioDTO.consultaDTO(exercicio).getImgIlistracao().substring(0,6)).contains("GIF")){
            throw new Exception("Não é possível atualizar o exercício com uma ilustração diferente de um GIF!");
        }
    }

    public String validaInsertTeste(Exercicio exercicio) {
        if (exercicio.getId() != null){
            return "Não é necessário informar o ID para inserir um novo Exercicio";
        }

        if (!exercicio.getAparelho().isStatus()){
            return "Não é possível inserir um exercício com um Aparelho inativado";
        }
        return null;
    }

    public String validaUpdateTeste(Exercicio exercicio) {
        if (exercicio.getId() == null){
            return "É necessário informar o ID para atualizar o cadastro do Exercicio";
        }
        return null;
    }

    public List<ExercicioDTO> findByAparelho(Aparelho aparelho) throws DataFormatException, IOException {
        List<Exercicio> exercicios = exercicioRepository.findByAparelho(aparelho);

        List<ExercicioDTO> exercicioDTOS = new ArrayList<>();

        for (Exercicio exercicio:exercicios) {
            exercicioDTOS.add(ExercicioDTO.consultaDTO(exercicio));
        }

        return exercicioDTOS;
    }

    public PageableDTO findAllPageable(int page, int registrosSolic) throws Exception {
        List<Exercicio> exercicioDTOS = exercicioRepository.findAll();

        List<ExercicioListDTO> exercicioDTOSRetorno = new ArrayList<>();

        int inicio = 0;
        int fim = registrosSolic;

        if (page > 1) {
            inicio = inicio + registrosSolic * (page - 1);
            fim = page * registrosSolic;
        }

        if (exercicioDTOS.size() < inicio) {
            throw new Exception("Não há elementos na página informada!");
        } else if (exercicioDTOS.size() < fim) {
            fim = exercicioDTOS.size();
        }

        for (int i = inicio; i < fim; i++) {
            exercicioDTOSRetorno.add(ExercicioListDTO.consultaDTO(exercicioDTOS.get(i)));
        }

        PageableDTO pageableDTO = new PageableDTO(new ArrayList<Object>(exercicioDTOSRetorno), page, exercicioDTOS.size());
        return pageableDTO;
    }

    public PageableDTO findByDescPageable(String descricao, int page, int registrosSolic) throws Exception {
        List<Exercicio> exercicioDTOS = exercicioRepository.findByDescricaoContainingAllIgnoringCase(descricao);

        List<ExercicioListDTO> exercicioDTOSRetorno = new ArrayList<>();

        int inicio = 0;
        int fim = registrosSolic;

        if (page > 1) {
            inicio = inicio + registrosSolic * (page - 1);
            fim = page * registrosSolic;
        }

        if (exercicioDTOS.size() < inicio) {
            throw new Exception("Não há elementos na página informada!");
        } else if (exercicioDTOS.size() < fim) {
            fim = exercicioDTOS.size();
        }

        for (int i = inicio; i < fim; i++) {
            exercicioDTOSRetorno.add(ExercicioListDTO.consultaDTO(exercicioDTOS.get(i)));
        }

        PageableDTO pageableDTO = new PageableDTO(new ArrayList<Object>(exercicioDTOSRetorno), page, exercicioDTOS.size());
        return pageableDTO;
    }

    public PageableDTO findByGrpMuscularPageable(String descricao, int page, int registrosSolic) throws Exception {
        List<Exercicio> exercicioDTOS = exercicioRepository.findByGrpMusculos(GrupoMuscularENUM.valueOf(descricao));

        List<ExercicioListDTO> exercicioDTOSRetorno = new ArrayList<>();

        int inicio = 0;
        int fim = registrosSolic;

        if (page > 1) {
            inicio = inicio + registrosSolic * (page - 1);
            fim = page * registrosSolic;
        }

        if (exercicioDTOS.size() < inicio) {
            throw new Exception("Não há elementos na página informada!");
        } else if (exercicioDTOS.size() < fim) {
            fim = exercicioDTOS.size();
        }

        for (int i = inicio; i < fim; i++) {
            exercicioDTOSRetorno.add(ExercicioListDTO.consultaDTO(exercicioDTOS.get(i)));
        }

        PageableDTO pageableDTO = new PageableDTO(new ArrayList<Object>(exercicioDTOSRetorno), page, exercicioDTOS.size());
        return pageableDTO;
    }

    public PageableDTO findByAparelhoPageable(String descricao, int page, int registrosSolic) throws Exception {
        List<Exercicio> exercicioDTOS = new ArrayList<>();

        for (Aparelho aparelho:aparelhoService.findByFilters(descricao)) {
            exercicioDTOS.addAll(exercicioRepository.findByAparelho(aparelho));
        }

        List<ExercicioListDTO> exercicioDTOSRetorno = new ArrayList<>();

        int inicio = 0;
        int fim = registrosSolic;

        if (page > 1) {
            inicio = inicio + registrosSolic * (page - 1);
            fim = page * registrosSolic;
        }

        if (exercicioDTOS.size() < inicio) {
            throw new Exception("Não há elementos na página informada!");
        } else if (exercicioDTOS.size() < fim) {
            fim = exercicioDTOS.size();
        }

        for (int i = inicio; i < fim; i++) {
            exercicioDTOSRetorno.add(ExercicioListDTO.consultaDTO(exercicioDTOS.get(i)));
        }

        PageableDTO pageableDTO = new PageableDTO(new ArrayList<Object>(exercicioDTOSRetorno), page, exercicioDTOS.size());
        return pageableDTO;
    }

    public PageableDTO findByStatusPageable(boolean status, int page, int registrosSolic) throws Exception {

        List<Exercicio> exercicioDTOS = exercicioRepository.findByStatus(status);

        List<ExercicioListDTO> exercicioDTOSRetorno = new ArrayList<>();

        int inicio = 0;
        int fim = registrosSolic;

        if (page > 1) {
            inicio = inicio + registrosSolic * (page - 1);
            fim = page * registrosSolic;
        }

        if (exercicioDTOS.size() < inicio) {
            throw new Exception("Não há elementos na página informada!");
        } else if (exercicioDTOS.size() < fim) {
            fim = exercicioDTOS.size();
        }

        for (int i = inicio; i < fim; i++) {
            exercicioDTOSRetorno.add(ExercicioListDTO.consultaDTO(exercicioDTOS.get(i)));
        }

        PageableDTO pageableDTO = new PageableDTO(new ArrayList<Object>(exercicioDTOSRetorno), page, exercicioDTOS.size());
        return pageableDTO;
    }
}
