
public class Consumidor {

	private final Buffer buffer;

	public Consumidor(Buffer buffer) {
		this.buffer = buffer;
	}

	public void consumir() {
		this.buffer.quitarElemento();
	}

}
