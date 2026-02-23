package actv03;

public class hilo_multiplicacion extends Thread {

	private int fila;
	private int columna;
	private Matriz matrizA;
	private Matriz matrizB;
	private int resultado;

	// Constructor
	public hilo_multiplicacion(int fila, int columna, Matriz matrizA, Matriz matrizB) {

		this.fila = fila;
		this.columna = columna;
		this.matrizA = matrizA;
		this.matrizB = matrizB;

	}

	@Override
	public void run() {

		int suma = 0;

		for (int k = 0; k < matrizA.getColumnas(); k++) {

			suma += matrizA.getValor(fila, k) * matrizB.getValor(k, columna);

		}

		resultado = suma;

	}

	public int getResultado() {

		return resultado;

	}

}
