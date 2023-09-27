package br.com.unipar.BullkApp;

import br.com.unipar.BullkApp.model.Aparelho;
import br.com.unipar.BullkApp.repositories.AparelhoRepository;
import br.com.unipar.BullkApp.services.AparelhoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BullkAppApplicationTests {

	private static AparelhoRepository aparelhoRepository;

	@Test
	public void testeInsercaoAparelho() {
		Aparelho aparelhoTeste = new Aparelho();
		aparelhoTeste.setStatus(false);
		aparelhoTeste.setDescricao("teste");

		aparelhoRepository.save(aparelhoTeste);

		throw new RuntimeException();
	}

}
