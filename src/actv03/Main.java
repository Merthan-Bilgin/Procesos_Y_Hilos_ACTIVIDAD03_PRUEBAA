package actv03;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		// Comprobar que se han pasado exactamente 4 parámetros por consola
		if (args.length != 4) {
			System.out.println("Debes indicar 4 parámetros: filasA columnasA filasB columnasB");
			return;
		}
		//Convertimos los parámetros a números enteros
		int filasA = Integer.parseInt(args[0]);
		int columnasA = Integer.parseInt(args[1]);
		int filasB = Integer.parseInt(args[2]);
		int columnasB = Integer.parseInt(args[3]);

		// Comprobar que los tamaños están entre 1 y 20
		if (filasA < 1 || filasA > 20 || columnasA < 1 || columnasA > 20 || filasB < 1 || filasB > 20 || columnasB < 1
				|| columnasB > 20) {
			System.out.println("Los tamaños deben estar entre 1 y 20");
			return;
		}
		
		//Comprobar que las matrices se pueden multiplicar 
		// Para multiplicar: columnasA debe ser igual a filasB
		if (columnasA != filasB) {
			System.out.println("No se pueden multiplicar: columnasA debe ser igual a filasB");
			return;
		}

		Scanner sc = new Scanner(System.in);
		boolean continuar = true;

		while (continuar) {

			// 2️ Crear matrices
			Matriz matrizA = new Matriz(filasA, columnasA);
			Matriz matrizB = new Matriz(filasB, columnasB);
			Matriz matrizResultado = new Matriz(filasA, columnasB);

			// 3️ Elegir entrada de datos
			System.out.println("¿Cómo quieres introducir los datos?");
			System.out.println("1. Por consola");
			System.out.println("2. Desde archivo");
			int opcion = sc.nextInt();

			if (opcion == 1) {
				matrizA.leerPorConsola();
				matrizB.leerPorConsola();
			} else {
				System.out.println("Lectura por archivo (pendiente de implementar)");
				return;
			}

			// 4️ Crear y lanzar hilos
			hilo_multiplicacion[][] hilos = new hilo_multiplicacion[filasA][columnasB];

			for (int i = 0; i < filasA; i++) {
				for (int j = 0; j < columnasB; j++) {
					hilos[i][j] = new hilo_multiplicacion(i, j, matrizA, matrizB);
					hilos[i][j].start();
				}
			}

			// 5️ Esperar y recoger resultados
			for (int i = 0; i < filasA; i++) {
				for (int j = 0; j < columnasB; j++) {
					try {
						hilos[i][j].join();
						matrizResultado.setValor(i, j, hilos[i][j].getResultado());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			// 6️ Mostrar resultado
			System.out.println("Matriz resultado:");
			matrizResultado.mostrar();

			// 7️ Repetir o salir
			System.out.println("¿Quieres realizar otra multiplicación?");
			System.out.println("1. Sí");
			System.out.println("2. No");
			int resp = sc.nextInt();

			if (resp != 1) {
				continuar = false;
			}
		}

		System.out.println("Programa finalizado.");
	}
}