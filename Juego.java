public class Juego {
    private String[][] mapa;

    public Juego() {
        mapa = new String[10][10];
        // Inicializar el mapa con casillas vacías
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                mapa[i][j] = ".";
            }
        }
    }

    public void agregarObstaculo(int fila, int columna) {
        mapa[fila][columna] = "#";
    }
    public void agregarCofre(int fila, int columna) {
        mapa[fila][columna] = "C";
    }
    public void agregarpersonaje(int fila, int columna) {
        mapa[fila][columna] = "C";
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

                mapa.agregarObstaculo(0, j);
                mapa.agregarObstaculo(i, 9);
                mapa.agregarObstaculo(i, 0);
                mapa.agregarObstaculo(9, j);
                mapa.agregarCofre(1,4);
                mapa.agregarCofre(3,6);
                mapa.agregarCofre(5,8);
                mapa.agregarCofre(7,2);

            }
            System.out.println();
        }
        System.out.println("Mapa:");
        mapa.imprimirMapa();
    }
}