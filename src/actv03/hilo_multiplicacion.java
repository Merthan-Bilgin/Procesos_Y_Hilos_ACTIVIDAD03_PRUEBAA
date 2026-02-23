package actv03;

public class hilo_multiplicacion extends Thread {

	private int fila;
	private int columna;
	private Matriz A;
	private Matriz B;
	private int resultado;

	public hilo_multiplicacion(int fila, int columna, Matriz A, Matriz B) {
		this.fila = fila;
		this.columna = columna;
		this.A = A;
		this.B = B;
	}

	@Override
	public void run() {
		int suma = 0;
		for (int k = 0; k < A.getColumnas(); k++) {
			suma += A.getValor(fila, k) * B.getValor(k, columna);
		}
		resultado = suma;
	}

	public int getResultado() {
		return resultado;
	}
}
