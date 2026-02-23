package actv03;

import java.util.Scanner;

public class Matriz {
	private int filas;
	private int columnas;
	private int[][] datos;

	// Constructor
	public Matriz(int filas, int columnas) {
		this.filas = filas;
		this.columnas = columnas;
		datos = new int[filas][columnas];
	}

	// Getters
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

	// Leer matriz por consola
	public void leerPorConsola() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce los valores de la matriz:");
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				System.out.print("[" + i + "][" + j + "]: ");
				datos[i][j] = sc.nextInt();
			}
		}
	}

	// Mostrar matriz por pantalla
	public void mostrar() {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				System.out.print(datos[i][j] + "\t");
			}
			System.out.println();
		}
	}
}