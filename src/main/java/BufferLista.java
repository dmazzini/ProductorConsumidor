import java.util.ArrayList;
import java.util.List;


public class BufferLista<T> extends Buffer<T> {

	private List<T> buffer; 

	public BufferLista(int tamaño) {
		super(tamaño);
		this.buffer = new ArrayList<T>();
	}

	public void agregarElemento(T elemento) throws InterruptedException {
		
		synchronized (buffer) {
			while (completo()) {
				buffer.wait();
			}
			if(ocupado < tamaño) {
				ocupado++;
				buffer.add(elemento);
				buffer.notifyAll();
			}
		}
	}

	public void quitarElemento() throws InterruptedException{
		
		synchronized (buffer) {
			while (ocupado() == 0) {
				buffer.wait();
			}
			if (ocupado > 0) {
				ocupado--;
				this.buffer.remove(0);
				buffer.notifyAll();
			} 
		}
	}

}
