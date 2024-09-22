import java.util.Scanner;
import java.util.Random;

class Juego {
    static String[][] mapa;
    static int[] heroe = new int[4];
    static int[] enemigo = new int[4];
    static Random rand = new Random();

    public static void main(String[] args) {
        initMapa();
        initHeroe();
        initEnemigo();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Ingrese una dirección (w: norte, s: sur, d: este, a: oeste): ");
            String direccion = scanner.next();
            moverHeroe(direccion);
            imprimirMapa();
            verificarMuerte();
            verificarVictoria();
        }
    }

    static void initMapa() {
        mapa = new String[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                mapa[i][j] = ".";
                mapa[i][0] = "#";
                mapa[i][9] = "#";
                mapa[0][j] = "#";
                mapa[9][j] = "#";
                mapa[4][4] = "C";
                mapa[8][8] = "C";
                mapa[8][4] = "C";
                mapa[4][8] = "C";
                mapa[8][1] = "X";
            }
        }
    }

    static void initHeroe() {
        heroe[0] = 1;
        heroe[1] = 1;
        heroe[2] = 100;
        heroe[3] = 15;
    }

    static void initEnemigo() {
        enemigo[0] = 5;
        enemigo[1] = 5;
        enemigo[2] = 45;
        enemigo[3] = 10;
    }

    static void moverHeroe(String direccion) {
        int x = heroe[0];
        int y = heroe[1];
        switch (direccion) {
            case "w":
                x -= 1;
                break;
            case "s":
                x += 1;
                break;
            case "d":
                y += 1;
                break;
            case "a":
                y -= 1;
                break;
        }
        if (x >= 1 && x < 9 && y >= 1 && y < 9) {
            heroe[0] = x;
            heroe[1] = y;
        }
        verificarEncuentro();
    }

    static void imprimirMapa() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == heroe[0] && j == heroe[1]) {
                    System.out.print("P ");
                } else if (i == enemigo[0] && j == enemigo[1] && enemigo[2] > 0) {
                    System.out.print("E ");
                } else {
                    System.out.print(mapa[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    static void verificarEncuentro() {
        int x = heroe[0];
        int y = heroe[1];
        if (x == 4 && y == 4 || x == 8 && y == 8 || x == 8 && y == 4 || x == 4 && y == 8) {
            abrirCofre();
        } else if (x == enemigo[0] && y == enemigo[1] && enemigo[2] > 0) {
            pelea();
        }
    }

    static void abrirCofre() {
        System.out.print("Deseas abrir el cofre a) Si / b) No");
        Scanner scanner = new Scanner(System.in);
        String opcion = scanner.next();
        switch (opcion) {
            case "a":
                suerteConCofres();
                break;
            case "b":
                System.out.print("Continúa tu camino");
                break;
        }
    }

    static void suerteConCofres() {
        int vida = heroe[2];
        int randomNum = rand.nextInt(2);
        if (randomNum == 0) {
            System.out.println("Encontraste un cofre se han restado 20 de vida");
            vida -= 20;
        } else {
            vida += 20;
            System.out.println("Encontraste un cofre se han sumado 20 de vida");
        }
        heroe[2] = vida;
    }

    static void pelea() {
        int x = heroe[0];
        int y = heroe[1];
        if (x == enemigo[0] && y == enemigo[1] && enemigo[2] > 0) {
            System.out.print("Elige la opción más sabia en base a tu vida: a) huir como un cobarde; b) pelear como un macho pecho peludo lomo plateado:");
            Scanner scanner = new Scanner(System.in);
            String opcion = scanner.next();
            opcionDePelea(opcion);
        } else {
            System.out.println("No hay moros en la costa sjjsj");
        }
    }

    static void opcionDePelea(String opcion) {
        switch (opcion) {
            case "a":
                int vidaRestanteHeroe = heroe[2] - enemigo[3];
                heroe[2] = vidaRestanteHeroe;
                System.out.println("Vida del héroe: " + heroe[2]);
                System.out.println("Vida del enemigo: " + enemigo[2]);
                casoA();
                break;
            case "b":
                int vidaRestanteHeroeB = heroe[2] - 3 * enemigo[3];
                int vidaRestanteEnemigoB = enemigo[2] - 3 * heroe[3];
                heroe[2] = vidaRestanteHeroeB;
                enemigo[2] = vidaRestanteEnemigoB;
                casoB();
                System.out.println("Vida del héroe: " + heroe[2]);
                break;
        }
    }

    static void casoA() {
        int x = heroe[0];
        int y = heroe[1];
        x += 2;
        y += 1;
        heroe[0] = x;
        heroe[1] = y;
    }

    static void casoB() {
        if (enemigo[2] == 0) {
            int x = enemigo[0];
            int y = enemigo[1];
            mapa[x][y] = "."; // remove the 'E' character from the map
            System.out.print("Enemigo derrotado");
        }
    }

    static void verificarMuerte() {
        int vidaFinal = heroe[2];
        if (vidaFinal <= 0) {
            System.out.print("Se acabaron tus puntos de vida, lo siento pero perdiste");
            System.exit(0);
        } else {
            System.out.println("Tu vida es " + vidaFinal);
        }
    }

    static void verificarVictoria() {
        int x = heroe[0];
        int y = heroe[1];
        if (x == 8 && y == 1) {
            System.out.print("Ganaste llegaste al final del juego");
            System.exit(0);
        } else {
            System.out.print("El héroe sigue su camino");
        }
    }
}