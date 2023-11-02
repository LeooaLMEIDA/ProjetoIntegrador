package br.com.unipar.BullkApp.services;

import br.com.unipar.BullkApp.model.Avaliacao;
import br.com.unipar.BullkApp.model.DTO.AvaliacaoDTO;
import br.com.unipar.BullkApp.model.DTO.PageableDTO;
import br.com.unipar.BullkApp.model.DTO.UsuarioDTO;
import br.com.unipar.BullkApp.model.Usuario;
import br.com.unipar.BullkApp.repositories.mobile.AvaliacaoRepository;
import br.com.unipar.BullkApp.util.Util;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

//    public ResponseEntity<String> insertWithFile(MultipartFile file, String data){
//        try {
//            System.out.println(file.getContentType());
//            if (!file.getContentType().equals(MediaType.APPLICATION_PDF_VALUE)){
//                throw new GenericErrorMessage("é necessário que o arquivo seja um PDF!");
//            }
//            ObjectMapper objectMapper = new ObjectMapper();
//            MapperAvaliacao avaliacao = objectMapper.readValue(data, MapperAvaliacao.class);
//
//            Avaliacao avaliacao1 = new Avaliacao();
//            avaliacao1.setUsuario(avaliacao.getUsuario());
//            avaliacao1.setDescricao(avaliacao.getDescricao());
//            avaliacao1.setObservacao(avaliacao.getObservacao());
//            avaliacao1.setArqAvaliacao(file.getBytes());
//
//            avaliacao1.setDataCriacao(LocalDateTime.now());
//            avaliacao1.setDataModificacao(LocalDateTime.now());
//
//            avaliacaoRepository.saveAndFlush(avaliacao1);
//
//            return ResponseEntity.ok().body("Upload com sucesso - Avaliação id = " + avaliacao1.getId());
//        } catch (GenericErrorMessage e) {
//            e.printStackTrace();
//            return ResponseEntity.badRequest().body("Problema ao realizar Upload " + e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
//        }
//    }

//    public ResponseEntity<String> updateWithFile(MultipartFile file, String data) {
//        try {
//            System.out.println(file.getContentType());
//            if (!file.getContentType().equals(MediaType.APPLICATION_PDF_VALUE)){
//                throw new GenericErrorMessage("é necessário que o arquivo seja um PDF!");
//            }
//            ObjectMapper objectMapper = new ObjectMapper();
//            MapperAvaliacaoWithId avaliacao = objectMapper.readValue(data, MapperAvaliacaoWithId.class);
//
//            Avaliacao avaliacao1 = findById(avaliacao.getId());
//
//            avaliacao1.setUsuario(avaliacao.getUsuario());
//            avaliacao1.setDescricao(avaliacao.getDescricao());
//            avaliacao1.setObservacao(avaliacao.getObservacao());
//            avaliacao1.setArqAvaliacao(file.getBytes());
//
//            avaliacao1.setDataModificacao(LocalDateTime.now());
//
//            avaliacaoRepository.saveAndFlush(avaliacao1);
//
//            return ResponseEntity.ok().body("Upload com sucesso - Avaliação id = " + avaliacao1.getId());
//        } catch (GenericErrorMessage e) {
//            e.printStackTrace();
//            return ResponseEntity.badRequest().body("Problema ao realizar Upload " + e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
//        }
//    }

    public AvaliacaoDTO insert(AvaliacaoDTO avaliacaoDTO) throws Exception{
        validaInsert(avaliacaoDTO);
        Avaliacao avaliacao = Avaliacao.consultaDTO(avaliacaoDTO);

        avaliacao.setUsuario(Usuario.consultaDTO(usuarioService.findById(avaliacaoDTO.getIdUsuario())));
        avaliacao.setArqAvaliacao(avaliacao.getArqAvaliacao());

        avaliacao.setDataCriacao(LocalDateTime.now());
        avaliacao.setDataModificacao(LocalDateTime.now());

        avaliacaoRepository.saveAndFlush(avaliacao);

        avaliacaoDTO.setId(avaliacao.getId());

        return avaliacaoDTO;
    }

    public AvaliacaoDTO update(AvaliacaoDTO avaliacaoDTO) throws Exception {
        validaUpdate(avaliacaoDTO);

        Avaliacao avaliacao = Avaliacao.consultaDTO(avaliacaoDTO);

        avaliacao.setUsuario(Usuario.consultaDTO(usuarioService.findById(avaliacaoDTO.getIdUsuario())));
        avaliacao.setArqAvaliacao(avaliacao.getArqAvaliacao());

        avaliacao.setDataModificacao(LocalDateTime.now());

        avaliacaoRepository.saveAndFlush(avaliacao);

        return avaliacaoDTO;
    }

    public AvaliacaoDTO delete(Long id) throws  Exception {
        Avaliacao avaliacao = Avaliacao.consultaDTO(findById(id));
        avaliacaoRepository.delete(avaliacao);

        return AvaliacaoDTO.consultaDTO(avaliacao);
    }

    public AvaliacaoDTO findById(Long id) throws Exception{
        Optional<Avaliacao> retorno = avaliacaoRepository.findById(id);
        if (retorno.isPresent()){
            return AvaliacaoDTO.consultaDTO(retorno.get());
        }
        else {
            throw new Exception("Avaliacao " + id + " não encontrada");
        }
    }

    public List<AvaliacaoDTO> findByFilters(String descricao) throws Exception{
        List<Avaliacao> avaliacoes = avaliacaoRepository.findAll();

        List<AvaliacaoDTO> avaliacaoDTOS = new ArrayList<>();

        for (Avaliacao avaliacao : avaliacoes) {
            if (avaliacao.getDescricao().toUpperCase().contains(descricao.toUpperCase()))
                avaliacaoDTOS.add(AvaliacaoDTO.consultaDTO(avaliacao));
        }
        return avaliacaoDTOS;
    }

    public List<AvaliacaoDTO> findAll() throws Exception{
        List<Avaliacao> avaliacoes = avaliacaoRepository.findAll();

        List<AvaliacaoDTO> avaliacaoDTOS = new ArrayList<>();

        for (Avaliacao avaliacao : avaliacoes) {
            avaliacaoDTOS.add(AvaliacaoDTO.consultaDTO(avaliacao));
        }
        return avaliacaoDTOS;
    }

    private void validaInsert(AvaliacaoDTO avaliacaoDTO) throws Exception{
        if (avaliacaoDTO.getId() != null){
            throw new Exception("Não é necessário informar o ID para inserir uma nova Avaliacao");
        }
    }

    private void validaUpdate(AvaliacaoDTO avaliacaoDTO) throws Exception{
        if (avaliacaoDTO.getId() == null){
            throw new Exception("É necessário informar o ID para atualizar o cadastro da Avaliacao");
        }
    }

    public List<AvaliacaoDTO> findByUsuario(Long id) throws Exception {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findAll();

        List<AvaliacaoDTO> avaliacaoDTOS = new ArrayList<>();

        for (Avaliacao avaliacao : avaliacoes) {
            if (avaliacao.getUsuario().getId().equals(id))
                avaliacaoDTOS.add(AvaliacaoDTO.consultaDTO(avaliacao));
        }
        return avaliacaoDTOS;
    }

    public PageableDTO findAllPageable(int page, int registrosSolic) throws Exception {
        List<AvaliacaoDTO> avaliacaos = findAll();

        List<AvaliacaoDTO> avaliacaosRetorno = new ArrayList<>();

        int inicio = 0;
        int fim = registrosSolic;

        if (page > 1) {
            inicio = inicio + registrosSolic * (page - 1);
            fim = page * registrosSolic;
        }

        if (avaliacaos.size() < inicio) {
            throw new Exception("Não há elementos na página informada!");
        } else if (avaliacaos.size() < fim) {
            fim = avaliacaos.size();
        }

        for (int i = inicio; i < fim; i++) {
            avaliacaosRetorno.add(avaliacaos.get(i));
        }

        return new PageableDTO(new ArrayList<Object>(avaliacaosRetorno), page, avaliacaos.size());
    }

    public PageableDTO findByFilterDescPageable(String descricao, int page, int registrosSolic) throws Exception {
        List<AvaliacaoDTO> avaliacaos = findByFilters(descricao);

        List<AvaliacaoDTO> avaliacaosRetorno = new ArrayList<>();

        int inicio = 0;
        int fim = registrosSolic;

        if (page > 1) {
            inicio = inicio + registrosSolic * (page - 1);
            fim = page * registrosSolic;
        }

        if (avaliacaos.size() < inicio) {
            throw new Exception("Não há elementos na página informada!");
        } else if (avaliacaos.size() < fim) {
            fim = avaliacaos.size();
        }

        for (int i = inicio; i < fim; i++) {
            avaliacaosRetorno.add(avaliacaos.get(i));
        }

        return new PageableDTO(new ArrayList<Object>(avaliacaosRetorno), page, avaliacaos.size());
    }

    public PageableDTO findByFilterUserPageable(String usuario, int page, int registrosSolic) throws Exception {
        List<AvaliacaoDTO> avaliacaos = new ArrayList<>();

        for (UsuarioDTO usuarioDTO:usuarioService.findAll()) {
            if (usuarioDTO.getNome().toUpperCase().contains(usuario.toUpperCase()))
                avaliacaos.addAll(findByUsuario(usuarioDTO.getId()));
        }

        List<AvaliacaoDTO> avaliacaosRetorno = new ArrayList<>();

        int inicio = 0;
        int fim = registrosSolic;

        if (page > 1) {
            inicio = inicio + registrosSolic * (page - 1);
            fim = page * registrosSolic;
        }

        if (avaliacaos.size() < inicio) {
            throw new Exception("Não há elementos na página informada!");
        } else if (avaliacaos.size() < fim) {
            fim = avaliacaos.size();
        }

        for (int i = inicio; i < fim; i++) {
            avaliacaosRetorno.add(avaliacaos.get(i));
        }

        return new PageableDTO(new ArrayList<Object>(avaliacaosRetorno), page, avaliacaos.size());
    }
}
