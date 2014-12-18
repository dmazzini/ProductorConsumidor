
public class Productor implements Runnable{

	private final Buffer buffer;

	public Productor(Buffer buffer) {
		this.buffer = buffer;
	}

	public void producir() {
		try {
			this.buffer.agregarElemento();
		} catch (BufferCompletoException e) {
			// debe ponerse el thread a esperar a que se consuma un elemento.
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		this.producir();
	}

}
