import static org.junit.Assert.*;

import org.junit.Test;


public class ProductorConsumidorTest {
	
	@Test
	public void bufferNuevo() {
		Buffer buffer = new Buffer();
		assertEquals(0, buffer.size());
	}
	
	@Test
	public void agregarElementoABuffer() {
		Buffer buffer = new Buffer();
		buffer.agregarElemento();
		assertEquals(1, buffer.size());
	}
	
	@Test
	public void quitarElementoABuffer() {
		Buffer buffer = new Buffer();
		buffer.agregarElemento();
		buffer.quitarElemento();
		assertEquals(0, buffer.size());		
	}
	
	@Test
	public void quitarElementoABufferNuevo() {
		Buffer buffer = new Buffer();
		buffer.quitarElemento();
		assertEquals(0, buffer.size());		
	}
	
	@Test
	public void producirElemento() {
		Buffer buffer = new Buffer();
		Productor productor = new Productor(buffer);
		productor.producir();
		assertEquals(1, buffer.size());
	}
	
	@Test
	public void consumirElemento() {
		Buffer buffer = new Buffer();
		Productor productor = new Productor(buffer);
		productor.producir();
		Consumidor consumidor = new Consumidor(buffer);
		consumidor.consumir();
		assertEquals(0, buffer.size());
	}
}
