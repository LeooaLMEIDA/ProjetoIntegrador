package br.com.unipar.BullkApp.controllers;

import br.com.unipar.BullkApp.model.Exercicio;
import br.com.unipar.BullkApp.model.Treino;
import br.com.unipar.BullkApp.model.Usuario;
import br.com.unipar.BullkApp.services.ExercicioService;
import br.com.unipar.BullkApp.services.TreinoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/exercicio")
@Api(description = "Controlador REST Responsável pela Operações que representam o objeto de negócios Exercicio")
public class ExercicioController {

    @Autowired
    private ExercicioService exercicioService;

    @Autowired
    private TreinoService treinoService;

    @PostMapping("/uploadArquivo")
    @ApiOperation(value = "Operação resposável pela Inserção de um novo Exercicio com imagem do exercicio")
    public ResponseEntity<String> insertWithGif(@RequestParam("file") MultipartFile file, @RequestParam("data") String data){
        return exercicioService.insertWithFile(file, data);
    }

    @PutMapping()
    @ApiOperation(value = "Operação resposável pela Atualização de um Exercicio já existente com imagem do exercicio")
    public ResponseEntity<String> updateWithGif(@RequestParam("file") MultipartFile file, @RequestParam("data") String data){
        return exercicioService.updateWithFile(file, data);
    }

    @GetMapping("/getGif/{fileId}")
    public ResponseEntity<ByteArrayResource> downloadGif(@PathVariable Long fileId) throws Exception {
        Exercicio exercicio = exercicioService.findById(fileId);
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.IMAGE_GIF);

        ByteArrayResource resource = new ByteArrayResource(exercicio.getImgIlustracao());
        return ResponseEntity.ok().headers(headers).body(resource);
    }

    @PostMapping
    @ApiOperation(value = "Operação resposável pela Inserção de um novo Exercicio")
    public Exercicio insert(@RequestBody @Valid Exercicio exercicio) throws Exception{
        return exercicioService.insert(exercicio);
    }

//    @PutMapping
//    @ApiOperation(value = "Operação responsável pela Atualização de um Exercicio já existente")
//    public Exercicio update(@RequestBody Exercicio exercicio) throws Exception{
//        return exercicioService.update(exercicio);
//    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Operação responsável por inativar um Exercicio existente")
    public Exercicio delete(@PathVariable Long id) throws Exception {
        Exercicio exercicio = exercicioService.delete(id);

        List<Treino> treinos = treinoService.findByExercicio(exercicio);

        for (Treino treino : treinos) {
            treinoService.delete(treino.getId());
        }

        return exercicio;
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Operação resposável pela busca de Exercicio via ID")
    public Exercicio findById(@PathVariable Long id) throws Exception{
        return exercicioService.findById(id);
    }

    @GetMapping(path = "/filter")
    @ApiOperation(value = "Operação responsável pela busca do Exercicio via descrição")
    public List<Exercicio> findByFilters(@RequestParam("descricao") String descricao)throws Exception{
        return exercicioService.findByFilters(descricao);
    }

    @GetMapping
    @ApiOperation(value = "Operação resposável por listar todos os Exercicios cadastrados no sistema")
    public List<Exercicio> findAll() throws Exception{
        return exercicioService.findAll();
    }
}
