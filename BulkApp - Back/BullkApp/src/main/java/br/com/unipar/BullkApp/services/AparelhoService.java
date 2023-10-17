package br.com.unipar.BullkApp.services;

import br.com.unipar.BullkApp.model.Aparelho;
import br.com.unipar.BullkApp.repositories.AparelhoRepository;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@ApiModel(description = "Classe responsável pela regras de Negócio referente ao Aparelho")
public class AparelhoService {

    @Autowired
    private AparelhoRepository aparelhoRepository;

    public Aparelho insert(Aparelho aparelho) throws Exception{
//        aparelho.setStatus(true);
        aparelho.setDataCriacao(LocalDateTime.now());
        aparelho.setDataModificacao(LocalDateTime.now());
        aparelhoRepository.saveAndFlush(aparelho);
        return aparelho;
    }

    public Aparelho update(Aparelho aparelho) throws Exception {
        validaUpdate(aparelho);

        Aparelho aparelho1 = findById(aparelho.getId());

        aparelho1.setDescricao(aparelho.getDescricao());
        aparelho1.setStatus(aparelho.isStatus());
        aparelho1.setDataModificacao(LocalDateTime.now());

        if (aparelho1.isStatus())
            aparelho1.setDataExclusao(null);

        aparelhoRepository.saveAndFlush(aparelho1);
        return aparelho1;
    }

    public Aparelho delete(Long id) throws  Exception {
        Aparelho aparelho = findById(id);
        validaUpdate(aparelho);
        aparelho.setStatus(false);
        aparelho.setDataExclusao(LocalDateTime.now());
        aparelho.setDataModificacao(LocalDateTime.now());
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

    public String validaInsertTeste(Aparelho aparelho) {
        if (aparelho.getId() != null){
            return "Não é necessário informar o ID para inserir um novo Aparelho";
        }
        return null;
    }

    public String validaUpdateTeste(Aparelho aparelho) {
        if (aparelho.getId() == null){
            return "É necessário informar o ID para atualizar o cadastro do Aparelho";
        }
        return null;
    }
}
