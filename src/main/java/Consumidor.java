
public class Consumidor implements Runnable {

	private final Buffer buffer;

	public Consumidor(Buffer buffer) {
		this.buffer = buffer;
	}

	public void consumir() throws InterruptedException {
		synchronized (buffer) {
			while (buffer.ocupado() == 0) {
				this.buffer.wait();
				System.out.println("Thread " + Thread.currentThread().getName()
						+ " esperando consumir.");
			}
			try {
				this.buffer.quitarElemento();
				this.buffer.notifyAll();
				System.out.println("Thread " + Thread.currentThread().getName()
						+ " consumiendo.");
			} catch (BufferSinElementosException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void run() {
		try {
			this.consumir();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
