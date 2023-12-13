/**
 * Clase que inicializa el juego
 * @author JuanLuis
 * @version V.02
 */
public class BattleShip {

    /**
     * El Juego
     */
    public static void main(String[] args) {
        /**
         * Introduccion del juego
         */
        System.out.println("\nWelcome to Battleship");
        System.out.println("This game is a first time project");
        System.out.println("Game made by Juan Luis Espinosa Cuenca");
        /**
         * Aqui creamos los 2 mapas, el del bot y el del jugador
         */
        String[][] map_player = new String[11][11]; //o 26 or 11
        String[][] map_bot = new String[map_player.length][map_player[0].length];
        System.out.println();
        /**
         * Aqui te pide si luego cuando toque disparar quieres ver el mapa con los barcos del bot
         */
        System.out.print("Can you play with the bot map do you want?:");
        System.out.println(" Yes or no");
        boolean dificil = Tools.Give().equalsIgnoreCase("No");
        /**
         * Aqui comenzamos ya el programa empezando con donde posicionar los barcos
         */
        Start(map_player,map_bot);
        /**
         * Aqui ya comenzaria la segunda parte con jugar con los disparos
         */
        Shoot.MapShot(map_player,map_bot,dificil);
    }
    /**
     * Comienzo de rellenar los mapas de aray y bot
     * @param aray mapa jugador
     * @param bot mapa bot
     *
     */
    public static void Start(String[][]aray,String[][]bot){
        //Fill the array
        Tools.Fill(aray);
        //The array was copy for the shoot map
        Tools.Copy(aray,bot);
        //Place ship
        Ship(aray,bot);
    }
    /**
     * Posicionamiento de los barcos en un bucle
     * @param aray mapa jugador
     * @param bot mapa bot
     */
    public static void Ship (String[][]aray,String[][]bot){
        //Ship lenght
        int[] ship = {4,3,3,2,2};
        Tools.Show1(aray);
        //Place ships
        for (int i = 0; i < ship.length; i++) {
            int leng = ship[i];
            PlaceShip(leng,aray,bot);
        }
    }
    /**
     * Posicionamiento del barco en el mapa correspondiente segun el tamaño del barco
     * @param length barco
     * @param map mapa jugador
     * @param map_bot mapa bot
     */
    public static void PlaceShip (int length, String[][]map,String[][]map_bot){
        boolean yes=false;
        //Player place it
        do {
            System.out.println();
            System.out.print("Where do you want to place the ship of length " + length + "? ");
            String cord = Ship.CheckMap(map);
            yes = Ship.Cardinal(map,cord,length);
            if (!yes){
                System.out.println("Put it differently or change the coordinates\n");
                Tools.Show1(map);
            }
        }while (!yes);
        Tools.Show1(map);
        yes=false;
        //Bot place it
        do {
            yes = Ship.CardinalBot(map_bot,length);
        }while (!yes);
    }
}