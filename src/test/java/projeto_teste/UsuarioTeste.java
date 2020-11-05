package projeto_teste;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.projeto.model.models.Usuario;
import com.projeto.model.service.UsuarioService;

public class UsuarioTeste {
	
	//@Test(expected = Exception.class)
	public void salvarUsuarioBancoDadosTeste() {
		
		Usuario usuario = new Usuario();
		
		//usuario.setId(1);
		usuario.setUsername("Lucas da Silva brito");
		usuario.setPassword("123456");
		usuario.setEmail("brito.s@aluno.ifsp.edu.br");
		usuario.setAtivo(false);
		usuario.setAdmin(false);
		
		UsuarioService usuarioService = new UsuarioService();
		
		usuarioService.save(usuario);
		
		System.out.println("Gravando usuario no banco de dados");
		
		//assertTrue(true);
	}
	
	@Test(expected = Exception.class)
	public void alterarUsuarioBancoDadosTeste() {
		
		Usuario usuario = new Usuario();
				
		usuario.setId(1);
		
		//usuario.setUsername("Lucas da Silva brito");
		//usuario.setPassword("123456");
		//usuario.setEmail("roberto@carlos.com.br");
		//usuario.setAtivo(false);
		//usuario.setAdmin(false);
		
		UsuarioService usuarioService = new UsuarioService();
		
		usuario = usuarioService.findById(usuario.getId());
		
		System.out.println(usuario.toString());
		
		usuario.setEmail("brito.s@alumno.ifsp.edu.br");
		
		usuarioService.save(usuario);
		
		System.out.println("Alteracao do usuario no banco de dados");
		
		//assertTrue(true);
	}
}
