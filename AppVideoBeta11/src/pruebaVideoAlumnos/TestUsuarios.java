package pruebaVideoAlumnos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dominio.ListaVideos;
import dominio.Usuario;
import dominio.Video;

class TestUsuarios {
	Usuario usuarioAux1;
	Usuario usuarioAux2;
	Usuario usuarioAux3;
	Video aux1;
	Video aux2;
	Video aux3;
	Video aux4;
	Video aux5;
	Video aux6;
	Video aux7;
	Video aux8;
	Video aux9;
	Video aux10;
	Video aux11;
	ListaVideos listaAux;
	ListaVideos	listaAux2;
	List<Video> listaVideos;
	List<Video> listaVideos2;
	List<Video> recientes;
	
	@BeforeEach
	void setUp() throws Exception {
		usuarioAux1 = new Usuario("Usuario 1", new Date(2021, 12, 26), "usuarioTest1", "12345678");
		usuarioAux2 = new Usuario("Usuario 2", "test", new Date(2021, 12, 26), "usuarioTest2@gmail.com", "usuarioTest2", "12345678");
		usuarioAux3 = new Usuario("Usuario 3", "test", new Date(2021, 12, 26), "usuarioTest3@gmail.com", "usuarioTest3", "12345678");
		
		aux1 = new Video("videoTest1", "https://www.youtube.com/watch?v=DmxAzRwgDkc");
		aux2 = new Video("videoTest2", "https://www.youtube.com/watch?v=xIisDEMILao");
		aux3 = new Video("videoTest3", "https://www.youtube.com/watch?v=IGCIX3kLzLw");
		aux4 = new Video("videoTest4", "https://www.youtube.com/watch?v=7zJleRoHt08");
		aux5 = new Video("videoTest5", "https://www.youtube.com/watch?v=7zJleRoHt08");
		aux6 = new Video("videoTest6", "https://www.youtube.com/watch?v=DmxAzRwgDkc");
		aux7 = new Video("videoTest7", "https://www.youtube.com/watch?v=xIisDEMILao");
		aux8 = new Video("videoTest8", "https://www.youtube.com/watch?v=IGCIX3kLzLw");
		aux9 = new Video("videoTest9", "https://www.youtube.com/watch?v=7zJleRoHt08");
		aux10 = new Video("videoTest10", "https://www.youtube.com/watch?v=7zJleRoHt08");
		aux11 = new Video("videoTest11", "https://www.youtube.com/watch?v=7zJleRoHt08");
		listaAux = new ListaVideos("Lista Test");
		listaVideos = new LinkedList<Video>();
		listaVideos2 = new LinkedList<Video>();
		recientes = new LinkedList<Video>();
		Collections.addAll(listaVideos, aux1, aux2, aux3, aux4);
		Collections.addAll(listaVideos2, aux1, aux2, aux3, aux4, aux5);
		Collections.addAll(recientes, aux10, aux9, aux8, aux7, aux6, aux5, aux4, aux3, aux2, aux1);
		listaVideos.stream().forEach(u->listaAux.addVideo(u));
		
		usuarioAux1.addListaVideos(listaAux);
		usuarioAux1.addListaVideos(listaAux2);
	}

	@Test
	void testSetPremium() {
		usuarioAux3.setPremium(true);
		usuarioAux2.setPremium(false);
		assertTrue(usuarioAux3.isPremium());
		assertFalse(usuarioAux2.isPremium());
	}

	@Test
	void testGetListasVideos() {
		List<ListaVideos> lista = usuarioAux1.getListasVideos();
		assertEquals(listaAux, lista.get(0));
		assertEquals(listaAux2, lista.get(1));
	}
	
	@Test
	void testGetRecientes() {
		for(Video elemento : recientes) {
			usuarioAux1.addVideoRecientes(elemento);
		}
		usuarioAux1.addVideoRecientes(aux11);
		assertTrue(usuarioAux1.getRecientes().contains(aux11));
		assertFalse(usuarioAux1.getRecientes().contains(aux10));
	}

	@Test
	void testIsVideoListas() {
		usuarioAux2.addListaVideos(listaAux);
		assertTrue(usuarioAux2.isVideoListas(aux2));
	}
	
	@Test
	void testGetListaVideosPorNombre() {
		usuarioAux3.addListaVideos(listaAux);
		usuarioAux3.addListaVideos(listaAux2);
		assertEquals(listaAux, usuarioAux3.getListaVideosPorNombre("Lista Test"));
	}

}
