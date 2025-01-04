package tresenrayajuego;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Daniel MP
 */
public class TresEnRayaJuego {

    public static void main(String[] args) {
        //declaración de variables
        boolean play = true; //booleano que comprueba si se quiere volver a jugar tras una ronda o al iniciar el programa
        int fila = 0; //numero que determina en que fila de la matriz imprimes el carácter
        int filaComprobar = 0, columnaComprobar = 0; //cuando se sabe que hay que hace una jugada en la fila o columna, se guarda dicha fila/columna en una variable para más adelante
        int columna = 0; //numero que determina en que columna de la matriz imprimes el carácter
        char tablero[][] = { //matriz 3x3 que representa el tablero de juego
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };
        int numJugador = 0; //numero que define si es el turno de X o de O
        int opcionIni = 0; //la opcion de inicio del user
        int contadorFilas = 0, contadorColumnas = 0;//contadores para determinar si se hace una jugada específica o no
        boolean jugadaFilas = false, jugadaColumnas = false;//flags para ayudarme
        Scanner reader = new Scanner(System.in);

        //CÓDIGO DEL JUEGO:
        System.out.println("Bienvenido al juego del 3 en raya");
        System.out.println("1 Para jugar\n2 Para ver las reglas y empezar la partida\n3 Para salir");
        do { //en este do while se controlan que las opciones del menú no den excepciones
            try {
                opcionIni = reader.nextInt();
                if (opcionIni >= 1 && opcionIni <= 3) {
                    break;
                } else {
                    System.out.println("Solo valores entre 1 y 3");
                }
            } catch (InputMismatchException ime) {
                System.out.println("Valor incorrecto");
                reader.next();
            }
        } while (true);
        if (opcionIni == 2) { //reglas del juego
            System.out.println("Las reglas son sencillas:\nGana quien consiga alinear 3 X o O consecutivamente,\nya sea en diagonal, en una fila o en una columna.");
            System.out.println("");
            System.out.println("No se puede colocar un carácter en donde ya está puesto uno\n,si el tablero se llena pero no hay ninguna  alineación, entonces se dará empate,\nsolo está permitido colocar el carácter dentro del siguiente espacio:\n");
            System.out.println("1,1 | 1,2 | 1,3");
            System.out.println("----------------");
            System.out.println("2,1 | 2,2 | 2,3");
            System.out.println("----------------");
            System.out.println("3,1 | 3,2 | 3,3");
            System.out.println("");
        } else if (opcionIni == 3) {
            play = false;
        }
        //inicio del juego de 3 en raya trás el menú inicial:
        while (play == true) { //dentro de este while se representa el bucle jugable
            if (numJugador % 2 == 0) { //este if determina el turno de X o de O
                System.out.println("Jugador (X)");
            } else {
                System.out.println("CPU (O)");
            }

            do { //este do-while comprueba que los datos sean correctos y/o válidos
                if (numJugador % 2 == 0) {
                    try {
                        System.out.print("Fila:");
                        fila = reader.nextInt();
                        System.out.print("Columna:");
                        columna = reader.nextInt();
                    } catch (InputMismatchException ime) {
                        System.out.println("Valor inválido");
                        reader.next();
                    } catch (Exception e) {
                        System.out.println("Error inesperado\n" + e);
                        reader.next();
                    }
                } else {
                    //turno de la CPU
                    //comprobacion de las filas
                    for (int i = 0; i < 3 && jugadaFilas == false; i++) {
                        for (int j = 0; j < 3 && jugadaFilas == false; j++) {
                            if (tablero[i][j] != ' ') {
                                contadorFilas += 5;
                                if (contadorFilas == 10) {
                                    jugadaFilas = true;
                                    filaComprobar = i;
                                    fila = i + 1;
                                }
                            } else if (tablero[i][j] == ' ') {
                                contadorFilas = 0;
                            }
                        }
                    }
                    //comprobacion de las columnas
                    for (int i = 0; i < 3 && jugadaColumnas == false; i++) {//sigue siendo lo mismo que para las filas pero intercambiando j e i para recorrer columnas en vez de filas
                        for (int j = 0; j < 3 && jugadaColumnas == false; j++) {
                            if (tablero[j][i] != ' ') {
                                contadorColumnas += 5;
                                if (contadorColumnas == 10) {
                                    jugadaColumnas = true;
                                    columnaComprobar = i;
                                    columna = i + 1;
                                }
                            } else if (tablero[j][i] == ' ') {
                                contadorColumnas = 0;
                            }
                        }
                    }
                    if (jugadaFilas == true) {//se determina la columna
                        for (int k = 0; k < 3 && jugadaFilas == true; k++) {
                            if (tablero[filaComprobar][k] == ' ') {
                                columna = k + 1;
                            } else if (tablero[filaComprobar][0] != ' ' && tablero[filaComprobar][1] != ' ' && tablero[filaComprobar][2] != ' ') {
                                jugadaFilas = false;
                            }
                        }
                    }
                    if (jugadaColumnas == true) {//se determina la fila
                        for (int l = 0; l < 3 && jugadaColumnas == true; l++) {
                            if (tablero[l][columnaComprobar] == ' ') {
                                fila = l + 1;
                            } else if (tablero[0][columnaComprobar] != ' ' && tablero[1][columnaComprobar] != ' ' && tablero[2][columnaComprobar] != ' ') {
                                jugadaColumnas = false;
                            }
                        }
                    }
                    if (jugadaColumnas == false && jugadaFilas == false) {
                        fila = (int) (Math.random() * 3) + 1;
                        columna = (int) (Math.random() * 3) + 1;
                    }

                }
                if (fila >= 1 && fila <= 3 && columna >= 1 && columna <= 3) { //este if no permite un valor que provoce excepciones junto al try-catch
                    if (tablero[fila - 1][columna - 1] == ' ') { //este if comprueba que la celda seleccionada esté ya ocupada
                        break;
                    } else {
                        if (numJugador % 2 != 0) {
                        } else {
                            System.out.println("La celda ya está ocupada, elige otra.");
                        }
                    }
                } else {
                    if (numJugador % 2 != 0) {
                    } else {
                        System.out.println("Solo valores entre 1 y 3");
                    }
                }
            } while (true);
            if (numJugador % 2 == 0) { //imprime una X o un O dependiendo del turno (pares X, impares O)
                tablero[fila - 1][columna - 1] = 'X';
            } else {
                tablero[fila - 1][columna - 1] = 'O';
            }
            tablero(tablero); //se imprime el tablero y su estado actual tras cada turno
            if (hayGanador(tablero)) { //dentro de este if se comprueban las funciones hayGanador y tableroLleno.
                if (numJugador % 2 == 0) {
                    System.out.println("Ganan las X!");
                } else {
                    System.out.println("Ganan los O!");
                }
                play = false;
            } else if (tableroLleno(tablero)) {
                System.out.println("Empate... Ningún jugador logró hacer 3 en raya");
                play = false;
            }
            System.out.println("");
            numJugador++; //el número de jugador suma, dando turno al siguiente.
            contadorFilas = 0; //el contador de filas se reinicia
            contadorColumnas = 0; //el contador de columnas se reinicia
        }

        System.out.println("Hasta la próxima!");

    }//fin main

    //función que sirve para mostrar el tablero de juego y su estado
    public static void tablero(char tablero[][]) {
        //recorrer e imprimir el tablero
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tablero[i][j]);
                if (j < 2) {
                    System.out.print(" | ");  //separador entre columnas
                }
            }
            System.out.println(); //salto de línea después de cada fila
            if (i < 2) {
                System.out.println("---------"); //separador entre filas
            }
        }
    }//fin tablero

    public static boolean tableroLleno(char tablero[][]) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == ' ') {//si detecta un espacio vacío, termina la comprobación
                    return false;
                }
            }
        }
        return true; //si llega aquí, no hay espacios en blanco, empate
    }

    //esta función comprueba si hay una consecución de 3 carácteres iguales en filas, columnas, o las diagonales posibles.
    public static boolean hayGanador(char tablero[][]) {
        //comprobar filas
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] != ' ' && tablero[i][0] == tablero[i][1] && tablero[i][1] == tablero[i][2]) {
                return true;//se da victoria
            }
        }
        //comprobar columnas
        for (int j = 0; j < 3; j++) {
            if (tablero[0][j] != ' ' && tablero[0][j] == tablero[1][j] && tablero[1][j] == tablero[2][j]) {
                return true;//se da victoria
            }
        }
        //comprobar diagonal 1
        if (tablero[0][0] != ' ' && tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2]) {
            return true;//se da victoria
        }
        //comprobar diagonal 2
        if (tablero[0][2] != ' ' && tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0]) {
            return true;//se da victoria
        }
        return false; //no hay ningún ganador
    }

}//fin class
