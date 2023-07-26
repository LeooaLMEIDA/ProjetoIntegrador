package br.com.unipar.BullkApp.services;

import br.com.unipar.BullkApp.model.Aparelho;
import br.com.unipar.BullkApp.repositories.AparelhoRepository;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ApiModel(description = "Classe responsável pela regras de Negócio referente ao Aparelho")
public class AparelhoService {

    @Autowired
    private AparelhoRepository aparelhoRepository;

    public Aparelho insert(Aparelho aparelho) throws Exception{
        aparelho.setStatus(true);
        aparelhoRepository.saveAndFlush(aparelho);
        return aparelho;
    }

    public Aparelho update(Aparelho aparelho) throws Exception {
        validaUpdate(aparelho);
        aparelhoRepository.saveAndFlush(aparelho);
        return aparelho;
    }

    public Aparelho findById(Long id) throws Exception{
        Optional<Aparelho> retorno = aparelhoRepository.findById(id);
        if (retorno.isPresent()){
            return retorno.get();
        }
        else {
            throw new Exception("Aparelho " + id + " não encontrado");
        }
    }

    public List<Aparelho> findByFilters(String descricao) throws Exception{
        return aparelhoRepository.findByDescricaoContainingAllIgnoringCase(descricao);
    }

    public List<Aparelho> findAll() throws Exception{
        return aparelhoRepository.findAll();
    }

    private void validaInsert(Aparelho aparelho) throws Exception{
        if (aparelho.getId() != null){
            throw new Exception("Não é necessário informar o ID para inserir um novo Aparelho");
        }
    }

    private void validaUpdate(Aparelho aparelho) throws Exception{
        if (aparelho.getId() == null){
            throw new Exception("É necessário informar o ID para atualizar o cadastro do Aparelho");
        }
    }
}
