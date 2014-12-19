
public class Consumidor implements Runnable {

	private final Buffer<Integer> buffer;

	public Consumidor(Buffer<Integer> buffer) {
		this.buffer = buffer;
	}

	public void run() {
		try {
			this.buffer.quitarElemento();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
