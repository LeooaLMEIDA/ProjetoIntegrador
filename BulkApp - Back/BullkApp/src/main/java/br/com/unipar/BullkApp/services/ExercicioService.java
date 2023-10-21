package br.com.unipar.BullkApp.services;

import br.com.unipar.BullkApp.exceptions.GenericErrorMessage;
import br.com.unipar.BullkApp.model.Aparelho;
import br.com.unipar.BullkApp.model.DTO.AvaliacaoDTO;
import br.com.unipar.BullkApp.model.DTO.ExercicioDTO;
import br.com.unipar.BullkApp.model.DTO.PageableDTO;
import br.com.unipar.BullkApp.model.Exercicio;
import br.com.unipar.BullkApp.repositories.mobile.ExercicioRepository;
import br.com.unipar.BullkApp.util.MapperExercicio;
import br.com.unipar.BullkApp.util.MapperExercicioWithId;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@ApiModel(description = "Classe responsável pela regras de Negócio referente ao Exercicio")
public class ExercicioService {

    @Autowired
    private ExercicioRepository exercicioRepository;

    @Autowired
    private AparelhoService aparelhoService;

    public ResponseEntity<String> insertWithFile(MultipartFile file, String data) {
        try {
            if (!MediaType.IMAGE_GIF_VALUE.equals(file.getContentType()))
                throw new GenericErrorMessage("é necessário que a imagem seja um GIF!");

            ObjectMapper objectMapper = new ObjectMapper();
            MapperExercicio exercicio = objectMapper.readValue(data, MapperExercicio.class);

            Exercicio exercicio1 = new Exercicio();
            exercicio1.setDescricao(exercicio.getDescricao());
            exercicio1.setOrientacao(exercicio.getOrientacao());
            exercicio1.setStatus(exercicio.isStatus());
            exercicio1.setAparelho(aparelhoService.findById(exercicio.getAparelho().getId()));
            exercicio1.setGrpMusculos(exercicio.getGrpMusculos());

            exercicio1.setImgIlustracao(file.getBytes());
            exercicio1.setMediaType(file.getContentType());

            exercicio1.setDataCriacao(LocalDateTime.now());
            exercicio1.setDataModificacao(LocalDateTime.now());

            validaInsert(exercicio1);
            exercicioRepository.saveAndFlush(exercicio1);

            return ResponseEntity.ok().body("Upload com sucesso - Exercício id = " + exercicio1.getId());
        } catch (GenericErrorMessage e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Problema ao realizar Upload " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }

    public ExercicioDTO insert(Exercicio exercicio) throws Exception{
        Aparelho aparelho = aparelhoService.findById(exercicio.getAparelho().getId());

        exercicio.setAparelho(aparelho);

        validaInsert(exercicio);

        exercicio.setDataCriacao(LocalDateTime.now());
        exercicio.setDataModificacao(LocalDateTime.now());

        exercicioRepository.saveAndFlush(exercicio);

        return ExercicioDTO.consultaDTO(exercicio);
    }

    public ResponseEntity<String> updateWithFile(MultipartFile file, String data) {
        try {
            if (!MediaType.IMAGE_GIF_VALUE.equals(file.getContentType()))
                throw new GenericErrorMessage("é necessário que a imagem seja um GIF!");

            ObjectMapper objectMapper = new ObjectMapper();
            MapperExercicioWithId exercicio = objectMapper.readValue(data, MapperExercicioWithId.class);

            Exercicio exercicio1 = findById(exercicio.getId());

            exercicio1.setDescricao(exercicio.getDescricao());
            exercicio1.setOrientacao(exercicio.getOrientacao());
            exercicio1.setStatus(exercicio.isStatus());
            exercicio1.setAparelho(aparelhoService.findById(exercicio.getAparelho().getId()));
            exercicio1.setGrpMusculos(exercicio.getGrpMusculos());

            exercicio1.setImgIlustracao(file.getBytes());
            exercicio1.setMediaType(file.getContentType());

            exercicio1.setDataModificacao(LocalDateTime.now());

            if (exercicio1.isStatus())
                exercicio1.setDataExclusao(null);

            exercicioRepository.saveAndFlush(exercicio1);

            return ResponseEntity.ok().body("Usuário id = " + exercicio1.getId() + " atualizado com sucesso!");
        } catch (GenericErrorMessage e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Problema ao realizar Upload " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }

    public Exercicio update(Exercicio exercicio) throws Exception {
        validaUpdate(exercicio);

        Exercicio exercicio1 = findById(exercicio.getId());

        exercicio1.setDescricao(exercicio.getDescricao());
        exercicio1.setOrientacao(exercicio.getOrientacao());
        exercicio1.setStatus(exercicio.isStatus());
        exercicio1.setAparelho(aparelhoService.findById(exercicio.getAparelho().getId()));
        exercicio1.setGrpMusculos(exercicio.getGrpMusculos());
        exercicio1.setImgIlustracao(exercicio.getImgIlustracao());

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

    public List<ExercicioDTO> findByFilters(String descricao) throws Exception{
        List<Exercicio> exercicios = exercicioRepository.findByDescricaoContainingAllIgnoringCase(descricao);

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
    }

    private void validaUpdate(Exercicio exercicio) throws Exception{
        if (exercicio.getId() == null){
            throw new Exception("É necessário informar o ID para atualizar o cadastro do Exercicio");
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

    public List<Exercicio> findByAparelho(Aparelho aparelho) {
        return exercicioRepository.findByAparelho(aparelho);
    }

    public PageableDTO findAllPageable(int page, int registrosSolic) throws Exception {
        List<ExercicioDTO> exercicioDTOS = findAll();

        List<ExercicioDTO> exercicioDTOSRetorno = new ArrayList<>();

        int registros = registrosSolic;

        int inicio = 0;
        int fim = registros;

        if (page > 1) {
            inicio = inicio + registros * (page - 1);
            fim = page * registros;
        }

        if (exercicioDTOS.size() < inicio) {
            throw new Exception("Não há elementos na página informada!");
        } else if (exercicioDTOS.size() < fim) {
            fim = exercicioDTOS.size();
        }

        for (int i = inicio; i < fim; i++) {
            exercicioDTOSRetorno.add(exercicioDTOS.get(i));
        }

        PageableDTO pageableDTO = new PageableDTO(new ArrayList<Object>(exercicioDTOSRetorno), page, exercicioDTOSRetorno.size());
        return pageableDTO;
    }
}
