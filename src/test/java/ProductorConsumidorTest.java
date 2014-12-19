import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;


public class ProductorConsumidorTest {
	
	private static final int TAMAÑO_BUFFER = 4;

	@Rule public TestName testName = new TestName();
	
	
	@Before
    public void before() {
        System.out.println("-Comienza test " + testName.getMethodName());
    }

	@After
    public void after() {
        System.out.println("-Fin test " + testName.getMethodName());
    }
	
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
	public void producirElemento() throws InterruptedException {
		Buffer buffer = new Buffer(TAMAÑO_BUFFER);
		Productor productor = new Productor(buffer);
		
		Thread thread = new Thread(productor);
		thread.start();
		
		thread.join();
		assertEquals(TAMAÑO_BUFFER, buffer.tamaño());
		assertEquals(TAMAÑO_BUFFER-1, buffer.disponible());
		assertEquals(1, buffer.ocupado());
	}
	
	@Test
	public void consumirElemento() throws InterruptedException {
		Buffer buffer = new Buffer(TAMAÑO_BUFFER);
		Productor productor = new Productor(buffer);
		Consumidor consumidor = new Consumidor(buffer);
		
		Thread threadProductor = new Thread(productor);
		Thread threadConsumidor = new Thread(consumidor);
		
		threadProductor.start();
		threadConsumidor.start();
		
		threadProductor.join();
		threadConsumidor.join();
		assertEquals(TAMAÑO_BUFFER, buffer.tamaño());
		assertEquals(TAMAÑO_BUFFER, buffer.disponible());
		assertEquals(0, buffer.ocupado());
	}
	
	@Test
	public void esperarConsumirElemento() throws InterruptedException {
		Buffer buffer = new Buffer(TAMAÑO_BUFFER);
		Productor productor = new Productor(buffer);
		Consumidor consumidor = new Consumidor(buffer);		
		Thread threadProductor;
		Thread threadConsumidor;
		List<Thread> threads = new ArrayList<Thread>();
		for (int i = 0; i<10; i++) {
			threadProductor = new Thread(productor);
			threads.add(threadProductor);
		}

		for (int i = 0; i<10; i++) {
			threadConsumidor = new Thread(consumidor);
			threads.add(threadConsumidor);
		}
		
		for (Thread thread : threads) {
			thread.start();
		}
		
		for (Thread thread : threads) {
			thread.join();
		}
		
		assertEquals(TAMAÑO_BUFFER, buffer.tamaño());
		assertEquals(TAMAÑO_BUFFER, buffer.disponible());
		assertEquals(0, buffer.ocupado());
	}
}
