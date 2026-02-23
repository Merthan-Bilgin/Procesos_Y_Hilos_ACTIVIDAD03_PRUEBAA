package actv03;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		// Si NO has puesto exactamente 4 números al arrancar el programa, 
		// el programa te dice que falta algo y se cierra.
		if (args.length != 4) {
			System.out.println("Debes indicar 4 parámetros: filasA columnasA filasB columnasB");
			return;
		}

		// Aquí convertimos esos 4 textos en números. 
		// Ejemplo: si escribiste "2", aquí se convierte en número 2.
		int filasA = Integer.parseInt(args[0]);
		int columnasA = Integer.parseInt(args[1]);
		int filasB = Integer.parseInt(args[2]);
		int columnasB = Integer.parseInt(args[3]);

		// Comprobamos que todos los números están entre 1 y 20. 
		// Si alguno no cumple, el programa se cierra.
		if (filasA < 1 || filasA > 20 || columnasA < 1 || columnasA > 20 || filasB < 1 || filasB > 20 || columnasB < 1
				|| columnasB > 20) {
			System.out.println("Los tamaños deben estar entre 1 y 20");
			return;
		}

		// Para multiplicar matrices, el número de columnas de A 
		// debe ser igual al número de filas de B. 
		// Si no coincide, no se puede multiplicar.
		if (columnasA != filasB) {
			System.out.println("No se pueden multiplicar: columnasA debe ser igual a filasB");
			return;
		}

		Scanner sc = new Scanner(System.in);
		boolean continuar = true;

		// Este bucle sirve para repetir el programa si el usuario quiere.
		while (continuar) {

			// Creamos las matrices vacías con el tamaño indicado.
			Matriz A = new Matriz(filasA, columnasA);
			Matriz B = new Matriz(filasB, columnasB);
			Matriz R = new Matriz(filasA, columnasB);

			// Pedimos al usuario que escriba los números de la matriz A.
			System.out.println("Introduce los datos de la matriz A:");
			A.leerPorConsola(sc);
			
			// Pedimos los números de la matriz B.
			System.out.println("Introduce los datos de la matriz B:");
			B.leerPorConsola(sc);

			// Creamos una tabla de hilos. 
			// Cada hilo calculará UNA casilla del resultado.
			hilo_multiplicacion[][] hilos = new hilo_multiplicacion[filasA][columnasB];

			// Aquí arrancamos todos los hilos. 
			// Cada hilo calcula una posición (i, j) de la matriz final.
			for (int i = 0; i < filasA; i++) {
				for (int j = 0; j < columnasB; j++) {
					hilos[i][j] = new hilo_multiplicacion(i, j, A, B);
					hilos[i][j].start();
				}
			}

			// Aquí esperamos a que TODOS los hilos terminen. 
			// Cuando un hilo termina, guardamos su resultado en la matriz R.
			for (int i = 0; i < filasA; i++) {
				for (int j = 0; j < columnasB; j++) {
					try {
						hilos[i][j].join();// Espera a que el hilo termine
						R.setValor(i, j, hilos[i][j].getResultado());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			// Mostramos la matriz final ya calculada.
			System.out.println("Matriz resultado:");
			R.mostrar();

			// Preguntamos si el usuario quiere repetir.
			System.out.println("¿Deseas realizar otra multiplicación?");
			System.out.println("1. Sí");
			System.out.println("2. No");
			continuar = sc.nextInt() == 1;
		}

		sc.close();
		System.out.println("Programa finalizado.");
	}
}