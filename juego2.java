import java.util.Scanner;
import java.util.Random;
public class juego2 {
    private static String[][] mapa;
    static Random rand = new Random();
    public juego2() {
        mapa = new String[10][10];
        // Inicializar el mapa con casillas vacías
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                mapa[i][j] = ".";
                mapa[i][0] ="#";
                mapa[i][9]="#";
                mapa[0][j]="#";
                mapa[9][j]="#";
                mapa[4][4]="C";
                mapa[8][8]="C";
                mapa[8][4]="C";
                mapa[4][8]="C";
                mapa[8][1]="X";
            }
        }
    }
    public void imprimirMapa() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == Integer.parseInt(Personaje.heroe[0]) && j == Integer.parseInt(Personaje.heroe[1])) {
                    System.out.print("P "); // print the player character
                } else if (i == Integer.parseInt(Enemigo.enemigo[0]) && j == Integer.parseInt(Enemigo.enemigo[1]) && Integer.parseInt(Enemigo.enemigo[2]) > 0) {
                    System.out.print("E "); // print the enemy character only if it's not defeated
                } else {
                    System.out.print(mapa[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
    public class Personaje {
        public static String[] heroe  = new String[4];
        public static void initPlayer() {
            heroe[0] = "1";
            heroe[1] = "1";
            heroe[2] = "100";
            heroe[3] = "15";
        }
        public static void moverPersonaje(String direccion) {
            int x = Integer.parseInt(heroe[0]);
            int y = Integer.parseInt(heroe[1]);
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
                heroe[0] = String.valueOf(x);
                heroe[1] = String.valueOf(y);
            }
            encuentroconuncofre();
            int z = Integer.parseInt(Enemigo.enemigo[0]);
            int w = Integer.parseInt(Enemigo.enemigo[1]);
            if (x == z && y == w) {
                pelea(); // Call the pelea method if the hero has encountered an enemy
            }
        }
    }
    public class Enemigo {
        public static String[] enemigo  = new String[4];
        public static void initEnemigo() {
            enemigo[0] = "5"; // initial X coordinate
            enemigo[1] = "5"; // initial Y coordinate
            enemigo[2] = "45"; // initial life
            enemigo[3] = "10"; // initial attack
        }
    }
    public static void Abrir_o_no_cofre(){
        System.out.print("Deseas abrir el cofre  a) Si / b) No");
        Scanner scanner = new Scanner(System.in);
        String opcion = scanner.next();
        switch (opcion){
            case "a":
                Suerte_con_los_cofres();
                break;
            case "b":
                Suerte_con_los_cofres();
                break;
        }
    }
    public static void encuentroconuncofre(){
        int x = Integer.parseInt(Personaje.heroe[0]);
        int y = Integer.parseInt(Personaje.heroe[1]);
        if (x==4 && y==4){
            Abrir_o_no_cofre();
        }else if(x==8 && y==8){
            Abrir_o_no_cofre();
        }else if(x==8 && y==4){
            Abrir_o_no_cofre();
        }else if(x==4 && y==8){
            Abrir_o_no_cofre();
        }else{
            System.out.println("pepe");
        }

    }
    public static void Suerte_con_los_cofres(){
        int vida = Integer.parseInt(Personaje.heroe[2]);
        int randomNum = rand.nextInt(2); // generates a random number between 0 and 1
        if (randomNum == 0) {
            System.out.println("Encontraste un cofre se han restado 20 de vida");
            vida -= 20;
        } else {
            vida += 20;
            System.out.println("Encontraste un cofre se han sumado 20 de vida");
        }Personaje.heroe[2] = String.valueOf(vida);

    }
    public static void pelea(){
        int x = Integer.parseInt(Personaje.heroe[0]);
        int y = Integer.parseInt(Personaje.heroe[1]);
        int z = Integer.parseInt(Enemigo.enemigo[0]);
        int w = Integer.parseInt(Enemigo.enemigo[1]);
        if(x==z && y==w){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Elige la opcion mas sabia en base a tu vida: a) huir como un cobarde; b) pelear como un macho pecho peludo lomo plateado:");
            String opcion = scanner.next();
            cowalski_opciones(opcion);
        }else{
            System.out.println("No hay moros en la costa sjjsj");
        }
    }
    public static void cowalski_opciones(String opcion){
        System.out.print("Elige la opcion mas sabia en base a tu vida: a) huir como un cobarde; b) pelear como un macho pecho peludo lomo plateado:");
        switch (opcion){
            case "a":
                int vida_restanteheroea=Integer.parseInt(Personaje.heroe[2])-Integer.parseInt(Enemigo.enemigo[3]);
                int vida_restanteenemigoa= Integer.parseInt(Enemigo.enemigo[2])-Integer.parseInt(Personaje.heroe[3]);
                Personaje.heroe[2] = String.valueOf(vida_restanteheroea);
                System.out.println("Vida del héroe: " + Personaje.heroe[2]);
                System.out.println("Vida del enemigo" + Enemigo.enemigo[2]);
                CasoA();
            case "b":
                int vida_restanteheroeb=Integer.parseInt(Personaje.heroe[2])-3*Integer.parseInt(Enemigo.enemigo[3]);
                int vida_restanteenemigob= Integer.parseInt(Enemigo.enemigo[2])-3*Integer.parseInt(Personaje.heroe[3]);
                Personaje.heroe[2] = String.valueOf(vida_restanteheroeb);
                Enemigo.enemigo[2] = String.valueOf(vida_restanteenemigob);
                CasoB();
                System.out.println("Vida del héroe: " + Personaje.heroe[2]);
        }
    }
    public static void CasoA(){
        int x = Integer.parseInt(Personaje.heroe[0]);
        int y = Integer.parseInt(Personaje.heroe[1]);
        x+=1;
        y+=1;
        Personaje.heroe[0] = String.valueOf(x);
        Personaje.heroe[1] = String.valueOf(y);
    }
    public static void CasoB(){
        if(Integer.parseInt(Enemigo.enemigo[2]) == 0){
            int x = Integer.parseInt(Enemigo.enemigo[0]);
            int y = Integer.parseInt(Enemigo.enemigo[1]);
            mapa[x][y] = "."; // remove the 'E' character from the map
            System.out.print("Enemigo derrotado");
        }
    }
    public static void Muerte(){
        int vidafinal = Integer.parseInt(Personaje.heroe[2]);
        if (vidafinal <= 0){
        System.out.print("Se acabaron tus puntos de vida, lo siento pero perdiste");
            System.exit(0);
        }else{
            System.out.println("Tu vida es " + vidafinal);
        }
    }
    public static void Ganaste(){
        int x = Integer.parseInt(Personaje.heroe[0]);
        int y = Integer.parseInt(Personaje.heroe[1]);
        if(x==8 && y==1){
            System.out.print("Ganaste llegaste al final del juego");
            System.exit(0);
        }else{
            System.out.print("El heroe sigue su camino");
        }
    }


    public static void main(String[] args) {
        juego2 mapa = new juego2();
        Personaje.initPlayer(); // initialize the player
        Enemigo.initEnemigo(); // initialize the enemy
        System.out.println("Mapa:");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Ingrese una dirección (w: norte, s: sur, d: este, a: oeste): ");
            String direccion = scanner.next();

            Personaje.moverPersonaje(direccion); // move the player
            mapa.imprimirMapa();
            Muerte();
            Ganaste();
        }
    }
}