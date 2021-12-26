package pruebaVideoAlumnos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dominio.ListaVideos;
import dominio.Video;

class TestListaVideos {
	Video aux1;
	Video aux2;
	Video aux3;
	Video aux4;
	Video aux5;
	ListaVideos listaAux;
	List<Video> listaVideos;
	List<Video> listaVideos2;
	
	@BeforeEach
	void setUp() throws Exception {
		aux1 = new Video("videoTest1", "https://www.youtube.com/watch?v=DmxAzRwgDkc");
		aux2 = new Video("videoTest2", "https://www.youtube.com/watch?v=xIisDEMILao");
		aux3 = new Video("videoTest3", "https://www.youtube.com/watch?v=IGCIX3kLzLw");
		aux4 = new Video("videoTest4", "https://www.youtube.com/watch?v=7zJleRoHt08");
		aux5 = new Video("videoTest5", "https://www.youtube.com/watch?v=7zJleRoHt08");
		listaAux = new ListaVideos("Lista Test");
		listaVideos = new LinkedList<Video>();
		listaVideos2 = new LinkedList<Video>();
		Collections.addAll(listaVideos, aux1, aux2, aux3, aux4);
		Collections.addAll(listaVideos2, aux1, aux2, aux3, aux4, aux5);
		listaVideos.stream().forEach(u->listaAux.addVideo(u));
	}

	@Test
	void testGetNombre() {
		assertEquals("Lista Test", listaAux.getNombre());
	}

	@Test
	void testGetListaVideos() {
		assertEquals(listaVideos, listaAux.getListaVideos());
	}

	@Test
	void testAddVideo() {
		listaAux.addVideo(aux5);
		assertEquals(listaVideos2, listaAux.getListaVideos());
	}

}
