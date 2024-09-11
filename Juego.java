import java.util.Objects;
import java.util.Scanner;

public class Juego {
    private static String[][] mapa;
    static int filaHeroe = 5;
    static int columnaHeroe = 5;

    public Juego() {
        mapa = new String[10][10];
        // Inicializar el mapa con casillas vacías
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                mapa[i][j] = ".";
            }
        }
    }
    public static void verificarColision() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (Objects.equals(mapa[i][j], "E") && i == filaHeroe && j == columnaHeroe) {
                    System.out.println("¡Has encontrado un enemigo!");
                    // Aquí puedes agregar la lógica para manejar la colisión con el enemigo
                }
            }
        }
    }
    static void moverHeroe(String direccion) {
        int filaAnterior = filaHeroe;
        int columnaAnterior = columnaHeroe;

        switch (direccion) {
            case "N": // Norte
                filaHeroe--;
                break;
            case "S": // Sur
                filaHeroe++;
                break;
            case "E": // Este
                columnaHeroe++;
                break;
            case "O": // Oeste
                columnaHeroe--;
                break;
            default:
                System.out.println("Dirección no válida");
                return;
        }
        // Verificar si el personaje se sale del mapa
        if (filaHeroe < 1 || filaHeroe >= 9 || columnaHeroe < 1 || columnaHeroe >= 9) {
            filaHeroe = filaAnterior;
            columnaHeroe = columnaAnterior;
            return;
        }
        // Eliminar el personaje de la posición anterior
        mapa[filaAnterior][columnaAnterior] = ".";
        // Actualizar la posición del personaje en el mapa
        mapa[filaHeroe][columnaHeroe] = "H";
    }
    public void agregarObjetos(int filao, int columnao){
        mapa[filao][columnao] = "#";
    }

    public void agregarCofre(int fila, int columna) {
        mapa[fila][columna] = "C";
    }
    public void agregarEnemigo(int filaE, int columnaE) {
        mapa[filaE][columnaE] = "E";
        if(filaE==filaHeroe && columnaE==columnaHeroe){
            mapa[filaHeroe][columnaHeroe]= "X";
        }
    }

    public void imprimirMapa() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(mapa[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Juego mapa = new Juego();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                mapa.agregarObjetos(0, j);
                mapa.agregarObjetos(i, 9);
                mapa.agregarObjetos(i, 0);
                mapa.agregarObjetos(9, j);
                mapa.agregarCofre(1,4);
                mapa.agregarCofre(3,6);
                mapa.agregarCofre(5,8);
                mapa.agregarCofre(7,2);
                mapa.agregarEnemigo(3,3);
                mapa.agregarEnemigo(3,3);
                mapa.agregarEnemigo(5,5);
                mapa.agregarEnemigo(8,8);
                mapa.agregarEnemigo(3,6);
                Juego.mapa[filaHeroe][columnaHeroe] = "H";
            }
            System.out.println();
        }
        System.out.println("Mapa:");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Ingrese una dirección (N: norte, S: sur, E: este, O: oeste): ");
            String direccion = scanner.next();
            moverHeroe(direccion);
            verificarColision();
            mapa.imprimirMapa();
        }

    }
}