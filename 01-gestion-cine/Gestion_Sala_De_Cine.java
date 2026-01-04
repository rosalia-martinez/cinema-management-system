/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gestion_sala_de_cine;

import java.util.Scanner;

/**
 * @author Rosalía Martínez Hernández
 */
public class Gestion_Sala_De_Cine {

    static Scanner sc = new Scanner(System.in);
    
    //CONSTANTES
    private static final int FILAS = 10;
    private static final int COLUMNAS = 10;
    private static final int NUM_SALAS = 3;
    
    //Matrices como variables estáticas
    private static String[][] asientos_sala1 = new String[FILAS][COLUMNAS];
    private static String[][] asientos_sala2 = new String[FILAS][COLUMNAS];
    private static String[][] asientos_sala3 = new String[FILAS][COLUMNAS];
    
    //Cartelera
    private static final String[] CARTELERA = {
        "Psycho (1960) - Horror/Thriller",
        "Django Unchained (2012) - Western/Accion",
        "Pulp Fiction (1994) - Crimen/Drama"
    };
    
    public static void main(String[] args) {
        inicializar_salas(); //Inicializar todas las salas al inicio
        
        boolean salida = false;
        int opcion = 0;
        
        do {
            System.out.println("\n********************************************************");
            System.out.println("*** BIENVENID@ AL SISTEMA DE GESTION DE NUESTRO CINE ***");
            System.out.println("********************************************************");
            System.out.println("\nIndiquenos la opcion que desea:");
            System.out.println("1 - Ver cartelera");
            System.out.println("2 - Realizar reserva");
            System.out.println("3 - Ver disponibilidad de salas");
            System.out.println("0 - Salir del menu");
            System.out.print("Seleccione una opcion: ");
            opcion = sc.nextInt();
            
            switch (opcion) {
                case 1:
                    ver_cartelera();
                    break;
                case 2:
                    realizar_reserva();
                    break;
                case 3:
                    ver_disponibilidad();
                    break;
                case 0:
                    System.out.println("Esperamos volver a verle pronto!");
                    salida = true;
                    break;
                default:
                    System.err.println("Opcion invalida. Pruebe de nuevo.");
                    break;
            }
        } while (!salida);
    }
    
    //Método para inicializar todas las salas
    private static void inicializar_salas() {
        inicializar_una_sala(asientos_sala1);
        inicializar_una_sala(asientos_sala2);
        inicializar_una_sala(asientos_sala3);
    }
    
    private static void inicializar_una_sala(String[][] sala) {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                sala[i][j] = "L"; // L significa "libre"
            }
        }
    }
    
    //Opción 1: Ver cartelera
    private static void ver_cartelera() {
        System.out.println("\n================================================");
        System.out.println("===            CARTELERA DEL CINE            ===");
        System.out.println("================================================");
        for (int i = 0; i < CARTELERA.length; i++) {
            System.out.println("SALA " + (i + 1) + ": " + CARTELERA[i]);
        }
        System.out.println("================================================\n");
        
        System.out.println("Presione Enter para continuar...");
        sc.nextLine(); //Limpiar buffer
        sc.nextLine(); //Esperar el enter
    }
    
    //Opción 2: Realizar reserva
    private static void realizar_reserva() {
        boolean volver_menu = false;
        
        do {
            System.out.println("\n************************");
            System.out.println("*** REALIZAR RESERVA ***");
            System.out.println("************************");
            System.out.println("Seleccione la sala:");
            System.out.println("1 - Sala 1 (" + CARTELERA[0] + ")");
            System.out.println("2 - Sala 2 (" + CARTELERA[1] + ")");
            System.out.println("3 - Sala 3 (" + CARTELERA[2] + ")");
            System.out.println("4 - Volver al menu principal");
            System.out.print("Opcion: ");
            int sala = sc.nextInt();
            
            if (sala == 4) {
                return; //para volver al menu ppal
            }
            
            if (sala < 1 || sala > 3) {
                System.out.println("Sala no valida. Intente de nuevo.");
                continue;
            }
            
            String[][] sala_seleccionada = obtener_sala(sala);
            mostrar_asientos_sala(sala_seleccionada, sala);
            
            //Pedir fila y asiento
            System.out.print("\nIngrese el numero de fila (1-" + FILAS + "): ");
            int fila = sc.nextInt() - 1;
            
            System.out.print("Ingrese el numero de asiento (1-" + COLUMNAS + "): ");
            int asiento = sc.nextInt() - 1;
            
            //Validar que la posición sea válida
            if (fila < 0 || fila >= FILAS || asiento < 0 || asiento >= COLUMNAS) {
                System.out.println("Posicion invalida. Debe estar entre 1 y " + FILAS + " para filas y 1 y " + COLUMNAS + " para asientos.");
                continue;
            }
            
            //Verificar si el asiento está libre
            if (!sala_seleccionada[fila][asiento].equals("L")) {
                System.out.println("Este asiento ya esta ocupado. Por favor seleccione otro.");
                continue;
            }
            
            //Reservar el asiento
            sala_seleccionada[fila][asiento] = "O";
            System.out.println("\nRESERVA CONFIRMADA!");
            System.out.println("Sala " + sala + " - " + CARTELERA[sala-1]);
            System.out.println("Fila: " + (fila + 1) + " | Asiento: " + (asiento + 1));
            
            //Preguntar si desea hacer otra reserva
            System.out.println("\nDesea realizar otra reserva?");
            System.out.println("1 - Si");
            System.out.println("2 - No (Volver al menu)");
            System.out.print("Opcion: ");
            int continuar = sc.nextInt();
            
            if (continuar == 2) {
                volver_menu = true;
            }
            
        } while (!volver_menu);
    }
    
    //Opción 3: Ver disponibilidad de salas
    private static void ver_disponibilidad() {
        boolean volver_menu = false;
        
        do {
            System.out.println("\n**************************");
            System.out.println("*** VER DISPONIBILIDAD ***");
            System.out.println("**************************");
            System.out.println("Seleccione la sala a visualizar:");
            System.out.println("1 - Sala 1");
            System.out.println("2 - Sala 2");
            System.out.println("3 - Sala 3");
            System.out.println("4 - Ver todas las salas");
            System.out.println("5 - Volver al menu principal");
            System.out.print("Opcion: ");
            int opcion = sc.nextInt();
            
            switch (opcion) {
                case 1:
                    System.out.println("\n*** SALA 1: " + CARTELERA[0] + " ***");
                    mostrar_asientos_sala(asientos_sala1, 1);
                    mostrar_estadisticas(asientos_sala1, 1);
                    break;
                case 2:
                    System.out.println("\n*** SALA 2: " + CARTELERA[1] + " ***");
                    mostrar_asientos_sala(asientos_sala2, 2);
                    mostrar_estadisticas(asientos_sala2, 2);
                    break;
                case 3:
                    System.out.println("\n*** SALA 3: " + CARTELERA[2] + " ***");
                    mostrar_asientos_sala(asientos_sala3, 3);
                    mostrar_estadisticas(asientos_sala3, 3);
                    break;
                case 4:
                    System.out.println("\n***DISPONIBILIDAD DE TODAS LAS SALAS ***");
                    for (int i = 1; i <= 3; i++) {
                        System.out.println("\n*** SALA " + i + ": " + CARTELERA[i-1] + " ***");
                        mostrar_asientos_sala(obtener_sala(i), i);
                        mostrar_estadisticas(obtener_sala(i), i);
                    }
                    break;
                case 5:
                    volver_menu = true;
                    break;
                default:
                    System.out.println("Opcion no valida.");
                    break;
            }
            
            if (opcion >= 1 && opcion <= 4 && !volver_menu) {
                System.out.println("\nPresione Enter para continuar...");
                sc.nextLine(); //Limpiar buffer
                sc.nextLine(); //Esperar el enter
            }
            
        } while (!volver_menu);
    }
    
    //Método para obtener la matriz de una sala
    private static String[][] obtener_sala(int numero_sala) {
        switch (numero_sala) {
            case 1:
                return asientos_sala1;
            case 2:
                return asientos_sala2;
            case 3:
                return asientos_sala3;
            default:
                return null;
        }
    }
    
    //Método para mostrar los asientos de una sala
    private static void mostrar_asientos_sala(String[][] sala, int numero_sala) {
        System.out.println("\nMapa de asientos - L = Libre | O = Ocupado");
        System.out.println("******************************************");
        
        //Mostrar números de columnas alineados
        System.out.print("    ");
        for (int col = 1; col <= COLUMNAS; col++) {
            System.out.printf("%2d ", col);
        }
        System.out.println();
        
        //Mostrar filas con números alineados
        for (int fila = 0; fila < FILAS; fila++) {
            System.out.printf("%2d: ", fila + 1);
            for (int col = 0; col < COLUMNAS; col++) {
                System.out.print(" " + sala[fila][col] + " ");
            }
            System.out.println();
        }
    }
    
    // Método para mostrar estadísticas de ocupación
    private static void mostrar_estadisticas(String[][] sala, int numero_sala) {
        int libres = 0;
        int ocupados = 0;
        
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (sala[i][j].equals("L")) {
                    libres++;
                } else {
                    ocupados++;
                }
            }
        }
        
        int total = FILAS * COLUMNAS;
        double porcentaje_ocupacion = (ocupados * 100.0) / total;
        
        System.out.println("\n*********************************************");
        System.out.println("***          ESTADISTICAS SALA " + numero_sala + "          ***");
        System.out.println("*********************************************");
        System.out.println("Asientos totales: " + total);
        System.out.println("Asientos libres: " + libres);
        System.out.println("Asientos ocupados: " + ocupados);
        System.out.printf("Porcentaje de ocupacion: %.1f%%\n", porcentaje_ocupacion);
    }
}
