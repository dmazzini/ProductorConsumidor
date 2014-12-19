
public abstract class Buffer<T> {

	protected int tamaño;
	protected int ocupado;
	
	public Buffer(int tamaño) {
		this.tamaño = tamaño;
		this.ocupado = 0;
	}
	
	public abstract void agregarElemento(T elemento) throws InterruptedException;

	public abstract void quitarElemento() throws InterruptedException;

	public int tamaño() {
		return tamaño;
	}

	public int disponible() {
		return tamaño-ocupado;
	}


	public int ocupado() {
		return ocupado;
	}

	public boolean completo() {
		return tamaño == ocupado;
	}


}