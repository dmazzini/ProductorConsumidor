
public class Buffer {

	private int tamaño;
	private int ocupado;

	public Buffer(int tamaño) {
		this.tamaño = tamaño;
		this.ocupado = 0;
	}

	public void agregarElemento() throws BufferCompletoException {
		if(ocupado < tamaño) {
			ocupado++;
		} else {
			throw new BufferCompletoException();
		}
	}

	public void quitarElemento() throws BufferSinElementosException {
		if (ocupado > 0) {
			ocupado--;
		} else {
			throw new BufferSinElementosException();
		}
	}

	public int tamaño() {
		return tamaño;
	}

	public int disponible() {
		return tamaño-ocupado;
	}

	public int ocupado() {
		return ocupado;
	}

}
