
public class Productor {

	private final Buffer buffer;

	public Productor(Buffer buffer) {
		this.buffer = buffer;
	}

	public void producir() {
		this.buffer.agregarElemento();
	}

}
