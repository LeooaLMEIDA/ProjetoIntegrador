package br.com.unipar.BullkApp.services;

import br.com.unipar.BullkApp.model.Avaliacao;
import br.com.unipar.BullkApp.model.DTO.*;
import br.com.unipar.BullkApp.model.Usuario;
import br.com.unipar.BullkApp.repositories.mobile.AvaliacaoRepository;
import br.com.unipar.BullkApp.repositories.mobile.UsuarioRepository;
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

    @Autowired
    private UsuarioRepository usuarioRepository;

    public AvaliacaoWebDTO insert(AvaliacaoWebDTO avaliacaoDTO) throws Exception{
        Avaliacao avaliacao = Avaliacao.consultaDTO(avaliacaoDTO);

        validaInsert(avaliacao);

        avaliacao.setUsuario(Usuario.consultaDTO(usuarioService.findById(avaliacaoDTO.getIdUsuario())));
        avaliacao.setArqAvaliacao(avaliacao.getArqAvaliacao());

        avaliacao.setDataCriacao(LocalDateTime.now());
        avaliacao.setDataModificacao(LocalDateTime.now());

        avaliacaoRepository.saveAndFlush(avaliacao);

        avaliacaoDTO.setId(avaliacao.getId());

        return avaliacaoDTO;
    }

    public AvaliacaoListDTO update(AvaliacaoWebDTO avaliacaoDTO) throws Exception {
        Avaliacao avaliacao = Avaliacao.consultaDTO(avaliacaoDTO);

        if (avaliacao.getArqAvaliacao() == null)
            avaliacao.setArqAvaliacao(avaliacaoRepository.findById(avaliacao.getId()).get().getArqAvaliacao());

        validaUpdate(avaliacao);

        avaliacao.setUsuario(Usuario.consultaDTO(usuarioService.findById(avaliacaoDTO.getIdUsuario())));
        avaliacao.setArqAvaliacao(avaliacao.getArqAvaliacao());

        avaliacao.setDataModificacao(LocalDateTime.now());

        avaliacaoRepository.saveAndFlush(avaliacao);

        return AvaliacaoListDTO.consultaDTO(avaliacao);
    }

    public AvaliacaoWebDTO delete(Long id) throws  Exception {
        Avaliacao avaliacao = Avaliacao.consultaDTO(findById(id));
        avaliacaoRepository.delete(avaliacao);

        return AvaliacaoWebDTO.consultaDTO(avaliacao);
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
        List<Avaliacao> avaliacoes = avaliacaoRepository.findByOrderById();

        List<AvaliacaoDTO> avaliacaoDTOS = new ArrayList<>();

        for (Avaliacao avaliacao : avaliacoes) {
            if (avaliacao.getDescricao().toUpperCase().contains(descricao.toUpperCase()))
                avaliacaoDTOS.add(AvaliacaoDTO.consultaDTO(avaliacao));
        }
        return avaliacaoDTOS;
    }

    public List<AvaliacaoDTO> findAll() throws Exception{
        List<Avaliacao> avaliacoes = avaliacaoRepository.findByOrderById();

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

        if (!Util.getFileType(AvaliacaoDTO.consultaDTO(avaliacao).getArqAvaliacao().substring(0,6)).contains("PDF")){
            throw new Exception("Não é possível inserir uma Avaliação com um arquivo diferente de PDF!");
        }
    }

    private void validaUpdate(Avaliacao avaliacao) throws Exception{
        if (avaliacao.getId() == null){
            throw new Exception("É necessário informar o ID para atualizar o cadastro da Avaliacao");
        }

        if (!Util.getFileType(AvaliacaoDTO.consultaDTO(avaliacao).getArqAvaliacao().substring(0,6)).contains("PDF")){
            throw new Exception("Não é possível atualizar uma Avaliação com um arquivo diferente de PDF!");
        }
    }

    public List<AvaliacaoDTO> findByUsuario(Long id) throws Exception {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findByOrderById();

        List<AvaliacaoDTO> avaliacaoDTOS = new ArrayList<>();

        for (Avaliacao avaliacao : avaliacoes) {
            if (avaliacao.getUsuario().getId().equals(id))
                avaliacaoDTOS.add(AvaliacaoDTO.consultaDTO(avaliacao));
        }
        return avaliacaoDTOS;
    }

    public PageableDTO findAllPageable(int page, int registrosSolic) throws Exception {
        List<Avaliacao> avaliacaos = avaliacaoRepository.findByOrderById();

        List<AvaliacaoListDTO> avaliacaosRetorno = new ArrayList<>();

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
            avaliacaosRetorno.add(AvaliacaoListDTO.consultaDTO(avaliacaos.get(i)));
        }

        return new PageableDTO(new ArrayList<Object>(avaliacaosRetorno), page, avaliacaos.size());
    }

    public PageableDTO findByFilterDescPageable(String descricao, int page, int registrosSolic) throws Exception {
        List<Avaliacao> avaliacaos = avaliacaoRepository.findByDescricaoContainingAllIgnoringCase(descricao);

        List<AvaliacaoListDTO> avaliacaosRetorno = new ArrayList<>();

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
            avaliacaosRetorno.add(AvaliacaoListDTO.consultaDTO(avaliacaos.get(i)));
        }

        return new PageableDTO(new ArrayList<Object>(avaliacaosRetorno), page, avaliacaos.size());
    }

    public PageableDTO findByFilterUserPageable(String usuario, int page, int registrosSolic) throws Exception {
        List<Avaliacao> avaliacaos = new ArrayList<>();

        for (Usuario usuarioDTO: usuarioRepository.findByNomeContainsIgnoreCaseOrderById(usuario)) {
            avaliacaos.addAll(avaliacaoRepository.findByUsuario(usuarioDTO));
        }

        List<AvaliacaoListDTO> avaliacaosRetorno = new ArrayList<>();

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
            avaliacaosRetorno.add(AvaliacaoListDTO.consultaDTO(avaliacaos.get(i)));
        }

        return new PageableDTO(new ArrayList<Object>(avaliacaosRetorno), page, avaliacaos.size());
    }
}
