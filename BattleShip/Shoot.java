/**
 * Disparos
 * @author JuanLuis
 * @version V.02
 */
public class Shoot {
    /**
     * Se ejecuta todo el tema de los disparos
     * @param aray mapa jugador
     * @param bot mapa bot
     * @param dificl mapa bot (Es true si ponemos no)
     */
    public static void MapShot (String[][]aray,String[][]bot,boolean dificl){

        //Generate bot map
        String[][] map_bot_shot = new String[aray.length][aray[0].length];
        String[][] map_player_shot = new String[aray.length][aray[0].length];
        //Fill bot map
        Tools.Fill(map_bot_shot);
        //Copy bot map in shoot map
        Tools.Copy(map_bot_shot,map_player_shot);
        if (!dificl){
            if (aray.length>23){
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  BOT");
            }else {
                System.out.println("\t\t\t\t\t\t\t\t BOT");
            }
            System.out.println();
            Tools.Show2(bot,map_bot_shot);
        }
        if (aray.length>23){
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Player");
        }else {
            System.out.println("\t\t\t\t\t\t\t\tPlayer");
        }
        System.out.println();
        Tools.Show2(aray,map_player_shot);
        int aciertos=0,aciertosbot=0;
        //Here start the game
        do {
            //Here shot player
            if (Shot(bot,map_player_shot)){
                aciertos++;
            }
            //Here shot bot
            if (ShotBot(aray,map_bot_shot)){
                aciertosbot++;
            }
            //Show bot maps
            if (!dificl) {
                if (aray.length>23){
                    System.out.println("-------------------------------------------------------------------------------------------------");
                    System.out.println();
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  BOT");
                }else{
                    System.out.println("-----------------------------------------------------------------------");
                    System.out.println();
                    System.out.println("\t\t\t\t\t\t\t\t BOT");
                }
                Tools.Show2(bot,map_bot_shot);
            }
            //Show player maps
            if (aray.length>23){
                System.out.println("-----------------------------------------------------------------------");
                System.out.println();
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Player");
            }else{
                System.out.println("-----------------------------------------------------------------------");
                System.out.println();
                System.out.println("\t\t\t\t\t\t\t\tPlayer");
            }
            Tools.Show2(aray,map_player_shot);
        }while (aciertos<14&&aciertosbot<14);
        if (aciertos==14){
            System.out.println("Ganaste");
        }else {
            System.out.println("Perdiste");
        }
    }
    /**
     * Disparo del jugador al mapa del bot
     * @param ship mapa
     * @param shot_enemi mapa disparos enemigo
     * @return Si es true es un acierto si es un false es un fallo
     */
    public static boolean Shot(String[][]ship,String[][]shot_enemi) {
        boolean bot=false;
        boolean a;
        do {
            System.out.print("Select where you want to shoot: ");
            String cords = Ship.CheckMap(ship);
            //Check if the shoot was succesfily
            if (ACierto(ship, cords, shot_enemi, bot)) {
                System.out.println();
                System.out.println("Impact");
                System.out.println();
                return true;
            } else {
                //If not fail
                a = Fallo(ship, cords, shot_enemi, bot);
                System.out.println();
                System.out.println("Fail");
                System.out.println();
                if (!a){
                    System.out.println("Don't point to the same side, try again");
                }
            }
        }while (!a);
        return false;
    }
    /**
     * Repite el disparo si ya habias disparado ahi
     * @param aray Mapa
     * @param nord1 cordenada 1
     * @param nord2 cordenada 2
     * @return True si se repite y false si no se repite
     */
    public static boolean Repit(String[][]aray,int nord1,int nord2){
        if (aray[nord2][nord1].equalsIgnoreCase("*  ")){
            return true;
        }
        return aray[nord2][nord1].equalsIgnoreCase("X  ");

    }
    /**
     * Disparo del bot
     * @param ship mapa
     * @param shot_enemi mapa disparos
     * @return Si es true es un acierto si es un false es un fallo
     */
    public static boolean ShotBot(String[][]ship,String[][]shot_enemi) {
        boolean bot=true;
        String cords = "";
        boolean a;
        do {
            //Check if the shoot was succesfily
            if (ACierto(ship,cords,shot_enemi,bot)){
                return true;
            } else {
                //If not fail
                a = Fallo(ship,cords,shot_enemi,bot);
            }
        }while (!a);
        return false;
    }
    /**
     * Comprueba si has acertado a un barco
     * @param aray Mapa
     * @param cords cordenadas
     * @param shot mapa disparo
     * @param bot Comprobacion de si hacierta el bot
     * @return Si es true es un acierto si es un false es un fallo
     */
    public static boolean ACierto(String[][]aray,String cords,String[][]shot,boolean bot){
        int nord1=0,nord2=0;
        boolean a=false;
        if (bot){
            nord1 = Ship.botposi(aray);
            nord2 = Ship.botposi(aray);
        }else {
            String position = Ship.Position(aray,cords);
            String pos1 = position.substring(0,2);
            String pos2 = position.substring(2,4);
            nord1 = Integer.parseInt(pos1.replace(" ",""));
            nord2 = Integer.parseInt(pos2.replace(" ",""));
        }
        if (aray[nord2][nord1].equalsIgnoreCase("*  ")){
            return false;
        }else{
            if (Repit(shot,nord1,nord2)){
                return false;
            }else {
                if (aray[nord2][nord1].equalsIgnoreCase("B  ")){
                    shot[nord2][nord1]="X  ";
                    aray[nord2][nord1]="X  ";
                    return true;
                }
            }
            return false;
        }
    }
    /**
     * Comprueba si has acertado a un barco
     * @param aray Mapa
     * @param cords cordenadas
     * @param shot mapa disparo
     * @param bot Comprobacion de si hacierta el bot
     */
    public static boolean Fallo(String[][]aray,String cords,String[][]shot, boolean bot){
        int nord1=0,nord2=0;
        if (bot){
            nord1 = (int)((Math.random())*(aray.length));
            nord2 = (int)((Math.random())*(aray.length));
            if (nord1==0){
                nord1+=1;
            }
            if (nord2==0){
                nord2+=1;
            }
        }else {
            String position = Ship.Position(aray,cords);
            String pos1 = position.substring(0,2);
            String pos2 = position.substring(2,4);
            nord1 = Integer.parseInt(pos1.replace(" ",""));
            nord2 = Integer.parseInt(pos2.replace(" ",""));
        }
        if (Repit(shot,nord1,nord2)) {
            if (!bot) {
                return false;
            }
        }else {
            if (!aray[nord2][nord1].equals("B  ")) {
                shot[nord2][nord1]="*  ";
                aray[nord2][nord1]="*  ";
                return true;
            }
        }
        return true;
    }
}
