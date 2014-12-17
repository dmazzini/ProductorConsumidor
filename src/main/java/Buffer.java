
public class Buffer {

	private int size;

	public int size() {
		return size;
	}

	public void agregarElemento() {
		size++;
	}

	public void quitarElemento() {
		if (size > 0) {
			size--;
		}
	}

}
