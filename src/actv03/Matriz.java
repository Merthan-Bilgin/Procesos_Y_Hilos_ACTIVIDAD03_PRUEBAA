package actv03;

import java.util.Scanner;

public class Matriz {

	private int filas;
	private int columnas;
	private int[][] datos;

	public Matriz(int filas, int columnas) {
		this.filas = filas;
		this.columnas = columnas;
		datos = new int[filas][columnas];
	}

	public int getFilas() {
		return filas;
	}

	public int getColumnas() {
		return columnas;
	}

	public int getValor(int fila, int columna) {
		return datos[fila][columna];
	}

	public void setValor(int fila, int columna, int valor) {
		datos[fila][columna] = valor;
	}

	// Lectura por consola
	public void leerPorConsola(Scanner sc) {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				System.out.print("[" + i + "][" + j + "]: ");
				datos[i][j] = sc.nextInt();
			}
		}
	}

	// Mostrar matriz
	public void mostrar() {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				System.out.print(datos[i][j] + "\t");
			}
			System.out.println();
		}
	}
}