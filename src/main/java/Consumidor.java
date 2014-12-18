
public class Consumidor implements Runnable {

	private final Buffer buffer;

	public Consumidor(Buffer buffer) {
		this.buffer = buffer;
	}

	public void consumir() {
		try {
			this.buffer.quitarElemento();
		} catch (BufferSinElementosException e) {
			// debe ponerse el thread a esperar a que se produzca un elemento.
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		this.consumir();
	}

}
