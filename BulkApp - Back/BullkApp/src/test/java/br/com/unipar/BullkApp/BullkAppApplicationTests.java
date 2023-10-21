package br.com.unipar.BullkApp;

import br.com.unipar.BullkApp.enums.GrupoMuscularENUM;
import br.com.unipar.BullkApp.enums.SexoENUM;
import br.com.unipar.BullkApp.enums.TipoUsuarioENUM;
import br.com.unipar.BullkApp.model.Aparelho;
import br.com.unipar.BullkApp.model.Exercicio;
import br.com.unipar.BullkApp.model.Usuario;
import br.com.unipar.BullkApp.services.AparelhoService;
import br.com.unipar.BullkApp.services.ExercicioService;
import br.com.unipar.BullkApp.services.UsuarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

@SpringBootTest
class BullkAppApplicationTests {
	@Test
	public void testeInsercaoAparelho() {
		AparelhoService aparelhoService = new AparelhoService();
		Aparelho aparelhoTeste = new Aparelho();
		aparelhoTeste.setStatus(false);
		aparelhoTeste.setDescricao("teste");

		String insert = aparelhoService.validaInsertTeste(aparelhoTeste);

		Assertions.assertNull(insert);
	}

	@Test
	public void testeAtualizacaoAparelho() {
		AparelhoService aparelhoService = new AparelhoService();
		Aparelho aparelhoTeste = new Aparelho();
		aparelhoTeste.setId(1L);
		aparelhoTeste.setStatus(false);
		aparelhoTeste.setDescricao("teste");

		String update = aparelhoService.validaUpdateTeste(aparelhoTeste);

		Assertions.assertNull(update);
	}

	@Test
	public void testeInsercaoExercicio() {
		ExercicioService exercicioService = new ExercicioService();

		Aparelho aparelhoTeste = new Aparelho();
		aparelhoTeste.setId(1L);
		aparelhoTeste.setStatus(true);
		aparelhoTeste.setDescricao("teste");

		Exercicio exercicioTeste = new Exercicio();


		exercicioTeste.setDescricao("exemplo");
		exercicioTeste.setOrientacao("abc");
		exercicioTeste.setStatus(true);
		exercicioTeste.setAparelho(aparelhoTeste);
		exercicioTeste.setGrpMusculos(GrupoMuscularENUM.BICEPS);
		exercicioTeste.setImgIlustracao("asdas".getBytes());

		String insert = exercicioService.validaInsertTeste(exercicioTeste);

		Assertions.assertNull(insert);
	}

	@Test
		public void testeAtualizacaoExercicio() {
		ExercicioService exercicioService = new ExercicioService();

		Aparelho aparelhoTeste = new Aparelho();
		aparelhoTeste.setId(1L);
		aparelhoTeste.setStatus(true);
		aparelhoTeste.setDescricao("teste");

		Exercicio exercicioTeste = new Exercicio();
		exercicioTeste.setId(1L);
		exercicioTeste.setDescricao("exemplo");
		exercicioTeste.setOrientacao("abc");
		exercicioTeste.setStatus(true);
		exercicioTeste.setAparelho(aparelhoTeste);
		exercicioTeste.setGrpMusculos(GrupoMuscularENUM.BICEPS);
		exercicioTeste.setImgIlustracao("asdas".getBytes());

		String update = exercicioService.validaUpdateTeste(exercicioTeste);

		Assertions.assertNull(update);
	}

	@Test
	public void testeInsercaoUsuario() {
		UsuarioService usuarioService = new UsuarioService();

		Usuario usuarioTeste = new Usuario();
		usuarioTeste.setNome("Teste");
		usuarioTeste.setDtNascimento(Date.valueOf("2000-01-26"));
		usuarioTeste.setSexo(SexoENUM.MASCULINO);
		usuarioTeste.setCelular("99999999");
		usuarioTeste.setEmail("m@gmail.com");
		usuarioTeste.setTpUsuario(TipoUsuarioENUM.ADMIN);
		usuarioTeste.setStatus(true);
		usuarioTeste.setSenha("123");

		usuarioTeste.setUrlAvatar("file".getBytes());

		String insert = usuarioService.validaInsertTeste(usuarioTeste);

		Assertions.assertNull(insert);
	}

	@Test
	public void testeAtualizacaoUsuario() {
		UsuarioService usuarioService = new UsuarioService();

		Usuario usuarioTeste = new Usuario();
		usuarioTeste.setId(1L);
		usuarioTeste.setNome("Teste");
		usuarioTeste.setDtNascimento(Date.valueOf("2000-01-26"));
		usuarioTeste.setSexo(SexoENUM.MASCULINO);
		usuarioTeste.setCelular("99999999");
		usuarioTeste.setEmail("m@gmail.com");
		usuarioTeste.setTpUsuario(TipoUsuarioENUM.ADMIN);
		usuarioTeste.setStatus(true);
		usuarioTeste.setSenha("123");

		usuarioTeste.setUrlAvatar("file".getBytes());

		String update = usuarioService.validaUpdateTeste(usuarioTeste);

		Assertions.assertNull(update);
	}
}
