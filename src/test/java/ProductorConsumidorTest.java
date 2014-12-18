import static org.junit.Assert.*;

import org.junit.Test;


public class ProductorConsumidorTest {
	
	private static final int TAMAÑO_BUFFER = 4;

	@Test
	public void bufferNuevo() {
		Buffer buffer = new Buffer(TAMAÑO_BUFFER);
		
		assertEquals(TAMAÑO_BUFFER, buffer.tamaño());
		assertEquals(TAMAÑO_BUFFER, buffer.disponible());
		assertEquals(0, buffer.ocupado());
	}
	
	@Test
	public void agregarElementoABuffer() throws BufferCompletoException {
		Buffer buffer = new Buffer(TAMAÑO_BUFFER);
		
		buffer.agregarElemento();
		
		assertEquals(TAMAÑO_BUFFER, buffer.tamaño());
		assertEquals(TAMAÑO_BUFFER-1, buffer.disponible());
		assertEquals(1, buffer.ocupado());
	}
	
	@Test(expected=BufferCompletoException.class)
	public void agregarElementoABufferCompleto() throws BufferCompletoException {
		Buffer buffer = new Buffer(TAMAÑO_BUFFER);
		
		buffer.agregarElemento();
		buffer.agregarElemento();
		buffer.agregarElemento();
		buffer.agregarElemento();
		buffer.agregarElemento();
		
	}
	
	@Test
	public void quitarElementoABuffer() throws BufferSinElementosException, BufferCompletoException {
		Buffer buffer = new Buffer(TAMAÑO_BUFFER);
		
		buffer.agregarElemento();
		buffer.quitarElemento();
		
		assertEquals(TAMAÑO_BUFFER, buffer.tamaño());
		assertEquals(TAMAÑO_BUFFER, buffer.disponible());
		assertEquals(0, buffer.ocupado());
	}
	
	@Test(expected=BufferSinElementosException.class)
	public void quitarElementoABufferNuevo() throws BufferSinElementosException {
		Buffer buffer = new Buffer(TAMAÑO_BUFFER);
		
		buffer.quitarElemento();

	}
	
	@Test
	public void producirElemento() {
		Buffer buffer = new Buffer(TAMAÑO_BUFFER);
		Productor productor = new Productor(buffer);
		
		productor.producir();
		
		assertEquals(TAMAÑO_BUFFER, buffer.tamaño());
		assertEquals(TAMAÑO_BUFFER-1, buffer.disponible());
		assertEquals(1, buffer.ocupado());
	}
	
	@Test
	public void consumirElemento() {
		Buffer buffer = new Buffer(TAMAÑO_BUFFER);
		Productor productor = new Productor(buffer);
		Consumidor consumidor = new Consumidor(buffer);
		
		productor.producir();
		consumidor.consumir();
		
		assertEquals(TAMAÑO_BUFFER, buffer.tamaño());
		assertEquals(TAMAÑO_BUFFER, buffer.disponible());
		assertEquals(0, buffer.ocupado());
	}
}
