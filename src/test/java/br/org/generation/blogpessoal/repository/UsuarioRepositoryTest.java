package br.org.generation.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import br.org.generation.blogpessoal.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start() {
		
		usuarioRepository.save(new Usuario(0L, "Iago Vieira", "iago@email.com.br", "13465278", ""));
		
		usuarioRepository.save(new Usuario(0L, "Sirlene oliveira", "sirlene@email.com.br", "13465278", ""));
		
		usuarioRepository.save(new Usuario(0L, "Jose silveira", "josef@email.com.br", "13465278", ""));
		
        usuarioRepository.save(new Usuario(0L, "muhammed silva", "muhammed@email.com.br", "13465278", ""));
		
	}
	
	@Test
	@DisplayName("Lista um único usuário")
	public void RetornarUsuario() {
		
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("iago@email.com");
		assertTrue(usuario.get().getUsuario().equals("iago@email.com.br"));
	}
	
	@Test
	@DisplayName("Lista 3 usuários")
	public void RetornarTresUsuarios() {
		
		List<Usuario> listaUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("eira");
		assertEquals(3, listaUsuarios.size());
		assertTrue(listaUsuarios.get(0).getNome().equals("João da Silva"));
		assertTrue(listaUsuarios.get(1).getNome().equals("Manuela da Silva"));
		assertTrue(listaUsuarios.get(2).getNome().equals("Adriana da Silva"));

	}
	
	
	@AfterAll
	void end() {
		
		usuarioRepository.deleteAll();
		
	}
	
}