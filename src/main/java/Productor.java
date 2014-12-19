
public class Productor implements Runnable{

	private final Buffer<Integer> buffer;

	public Productor(Buffer<Integer> buffer) {
		this.buffer = buffer;
	}

	public void run() {
		try {
			this.buffer.agregarElemento(new Integer(1));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
