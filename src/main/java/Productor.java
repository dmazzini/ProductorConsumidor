
public class Productor implements Runnable{

	private final Buffer buffer;

	public Productor(Buffer buffer) {
		this.buffer = buffer;
	}

	public void producir() throws InterruptedException {
		synchronized (buffer) {
			while (buffer.completo()) {
				buffer.wait();
				System.out.println("Thread " + Thread.currentThread().getName()
						+ " esperando producir.");
			}
			try {
				this.buffer.agregarElemento();
				this.buffer.notifyAll();
				System.out.println("Thread " + Thread.currentThread().getName()
						+ " produciendo.");
			} catch (BufferCompletoException e) {
				e.printStackTrace();
			}

		}
	}

	public void run() {
		try {
			this.producir();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
