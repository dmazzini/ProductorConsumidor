import java.util.concurrent.LinkedBlockingQueue;


public class BufferBlockingQueue<T> extends Buffer<T> {

	private LinkedBlockingQueue<T> buffer;

	public BufferBlockingQueue(int tamaño) {
		super(tamaño);
		buffer = new LinkedBlockingQueue<T>();		
	}

	public void agregarElemento(T elemento) throws InterruptedException {
		buffer.put(elemento);
		ocupado++;
	}

	public void quitarElemento() throws InterruptedException {
		buffer.take();
		ocupado--;
	}

}
