package br.com.unipar.BullkApp.services;

import br.com.unipar.BullkApp.model.Avaliacao;
import br.com.unipar.BullkApp.repositories.AvaliacaoRepository;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ApiModel(description = "Classe responsável pela regras de Negócio referente a Avaliação")
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private UsuarioService usuarioService;

    public Avaliacao insert(Avaliacao avaliacao) throws Exception{
        avaliacao.setUsuario(usuarioService.findById(avaliacao.getUsuario().getId()));

        avaliacaoRepository.saveAndFlush(avaliacao);

        return avaliacao;
    }

    public Avaliacao update(Avaliacao avaliacao) throws Exception {
        validaUpdate(avaliacao);
        avaliacaoRepository.saveAndFlush(avaliacao);
        return avaliacao;
    }

    public Avaliacao delete(Long id) throws  Exception {
        Avaliacao avaliacao = findById(id);
        validaUpdate(avaliacao);
        avaliacaoRepository.delete(avaliacao);
        return avaliacao;
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

    public List<Avaliacao> findAll() throws Exception{
        return avaliacaoRepository.findAll();
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
}
