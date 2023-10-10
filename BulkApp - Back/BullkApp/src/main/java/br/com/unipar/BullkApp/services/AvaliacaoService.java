package br.com.unipar.BullkApp.services;

import br.com.unipar.BullkApp.exceptions.GenericErrorMessage;
import br.com.unipar.BullkApp.model.Avaliacao;
import br.com.unipar.BullkApp.model.DTO.AvaliacaoDTO;
import br.com.unipar.BullkApp.model.Usuario;
import br.com.unipar.BullkApp.repositories.AvaliacaoRepository;
import br.com.unipar.BullkApp.util.MapperAvaliacao;
import br.com.unipar.BullkApp.util.MapperAvaliacaoWithId;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@ApiModel(description = "Classe responsável pela regras de Negócio referente a Avaliação")
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private UsuarioService usuarioService;

    public Optional<Avaliacao> getFile(Long fileId) {
        return avaliacaoRepository.findById(fileId);
    }

    public ResponseEntity<String> insertWithFile(MultipartFile file, String data){
        try {
            System.out.println(file.getContentType());
            if (!file.getContentType().equals(MediaType.APPLICATION_PDF_VALUE)){
                throw new GenericErrorMessage("é necessário que o arquivo seja um PDF!");
            }
            ObjectMapper objectMapper = new ObjectMapper();
            MapperAvaliacao avaliacao = objectMapper.readValue(data, MapperAvaliacao.class);

            Avaliacao avaliacao1 = new Avaliacao();
            avaliacao1.setUsuario(avaliacao.getUsuario());
            avaliacao1.setArqName(file.getName());
            avaliacao1.setDescricao(avaliacao.getDescricao());
            avaliacao1.setObservacao(avaliacao.getObservacao());
            avaliacao1.setArqType(file.getContentType());
            avaliacao1.setArqAvaliacao(file.getBytes());

            avaliacao1.setDataCriacao(LocalDateTime.now());
            avaliacao1.setDataModificacao(LocalDateTime.now());

            avaliacaoRepository.saveAndFlush(avaliacao1);

            return ResponseEntity.ok().body("Upload com sucesso - Avaliação id = " + avaliacao1.getId());
        } catch (GenericErrorMessage e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Problema ao realizar Upload " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }

    public ResponseEntity<String> updateWithFile(MultipartFile file, String data) {
        try {
            System.out.println(file.getContentType());
            if (!file.getContentType().equals(MediaType.APPLICATION_PDF_VALUE)){
                throw new GenericErrorMessage("é necessário que o arquivo seja um PDF!");
            }
            ObjectMapper objectMapper = new ObjectMapper();
            MapperAvaliacaoWithId avaliacao = objectMapper.readValue(data, MapperAvaliacaoWithId.class);

            Avaliacao avaliacao1 = findById(avaliacao.getId());

            avaliacao1.setUsuario(avaliacao.getUsuario());
            avaliacao1.setArqName(file.getName());
            avaliacao1.setDescricao(avaliacao.getDescricao());
            avaliacao1.setObservacao(avaliacao.getObservacao());
            avaliacao1.setArqType(file.getContentType());
            avaliacao1.setArqAvaliacao(file.getBytes());

            avaliacao1.setDataModificacao(LocalDateTime.now());

            avaliacaoRepository.saveAndFlush(avaliacao1);

            return ResponseEntity.ok().body("Upload com sucesso - Avaliação id = " + avaliacao1.getId());
        } catch (GenericErrorMessage e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Problema ao realizar Upload " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }

    public AvaliacaoDTO insert(Avaliacao avaliacao) throws Exception{
        avaliacao.setUsuario(usuarioService.findById(avaliacao.getUsuario().getId()));

        avaliacao.setDataCriacao(LocalDateTime.now());
        avaliacao.setDataModificacao(LocalDateTime.now());

        avaliacaoRepository.saveAndFlush(avaliacao);

        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();
        avaliacaoDTO.consultaDTO(avaliacao);

        return avaliacaoDTO;
    }

    public AvaliacaoDTO update(Avaliacao avaliacao) throws Exception {
        validaUpdate(avaliacao);
        avaliacaoRepository.saveAndFlush(avaliacao);

        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();
        avaliacaoDTO.consultaDTO(avaliacao);

        return avaliacaoDTO;
    }

    public AvaliacaoDTO delete(Long id) throws  Exception {
        Avaliacao avaliacao = findById(id);
        avaliacaoRepository.delete(avaliacao);

        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();
        avaliacaoDTO.consultaDTO(avaliacao);

        return avaliacaoDTO;
    }

    public Avaliacao findById(Long id) throws Exception{
        Optional<Avaliacao> retorno = avaliacaoRepository.findById(id);
        if (retorno.isPresent()){
            return retorno.get();
        }
        else {
            throw new Exception("Avaliacao " + id + " não encontrada");
        }
    }

    public List<Avaliacao> findByFilters(String descricao) throws Exception{
        return avaliacaoRepository.findByDescricaoContainingAllIgnoringCase(descricao);
    }

    public List<AvaliacaoDTO> findAll() throws Exception{
        List<Avaliacao> avaliacoes = avaliacaoRepository.findAll();

        List<AvaliacaoDTO> avaliacaoDTOS = new ArrayList<>();

        for (Avaliacao avaliacao : avaliacoes) {
            avaliacaoDTOS.add(AvaliacaoDTO.consultaDTO(avaliacao));
        }
        return avaliacaoDTOS;
    }

    private void validaInsert(Avaliacao avaliacao) throws Exception{
        if (avaliacao.getId() != null){
            throw new Exception("Não é necessário informar o ID para inserir uma nova Avaliacao");
        }
    }

    private void validaUpdate(Avaliacao avaliacao) throws Exception{
        if (avaliacao.getId() == null){
            throw new Exception("É necessário informar o ID para atualizar o cadastro da Avaliacao");
        }
    }

    public List<AvaliacaoDTO> findByUsuario(Long id) throws Exception {
        Usuario usuario = usuarioService.findById(id);
        List<Avaliacao> avaliacoes = avaliacaoRepository.findByUsuario(usuario);

        List<AvaliacaoDTO> avaliacaoDTOS = new ArrayList<>();

        for (Avaliacao avaliacao : avaliacoes) {
            avaliacaoDTOS.add(AvaliacaoDTO.consultaDTO(avaliacao));
        }
        return avaliacaoDTOS;
    }

    public List<Avaliacao> findByUsuario(Usuario usuario) {
        return avaliacaoRepository.findByUsuario(usuario);
    }
}
