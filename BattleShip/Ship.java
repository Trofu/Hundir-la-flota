/**
 * Posicionamiento de los barcos
 * @author JuanLuis
 * @version V.02
 *
 */
public class Ship {
    /**
     * Comprueba si las cordenadas sirven para el mapa
     * @param map mapa
     * @return cordenadas validas
     */
    public static String CheckMap(String[][]map){
        boolean yes = false;
        String cords;
        do {
            // Give cords
            do {
                cords = Tools.Give();
                if (cords.length()<2){
                    System.out.println("Give me another cord: ");
                }
            }while (cords.length()<2);

            // Subtract the cords in 2 variables
            String cord2="",cord1="";
            boolean a =false;
            cord1 = cordi(map,cords,cord1,cord2,a);
            a = true;
            cord2 = cordi(map,cords,cord1,cord2,a);

            for (int i = 1; i < map.length; i++) {
                for (int j = 1; j < map[0].length; j++) {
                    // Check if there are in map
                    String c = map[0][j];
                    String b = map[i][0];
                    if (map.length>23){
                        if (cord1.equalsIgnoreCase(c.replace("  ",""))&&cord2.equalsIgnoreCase(b.replace(" ",""))){
                            yes = true;
                            return cords;
                        }else if (cord2.equalsIgnoreCase(c.replace(" ",""))&&cord1.equalsIgnoreCase(b.replace("  ",""))){
                            yes = true;
                            return cords;
                        }
                    }else{
                        if (cord1.equalsIgnoreCase(c.replace(" ",""))&&cord2.equalsIgnoreCase(b.replace(" ",""))){
                            yes = true;
                            return cords;
                        }else if (cord2.equalsIgnoreCase(c.replace(" ",""))&&cord1.equalsIgnoreCase(b.replace(" ",""))){
                            yes = true;
                            return cords;
                        }
                    }
                }
            }
            if (!yes){
                // If yes==false cords fail
                System.out.println("\n\t\t\tCoords Fail\n");
                System.out.print("Give me other coords: ");
            }
        }while (!yes);
        return cords;
    }
    /**
     * Realiza cordenadas para el bot hasta que sirvan
     * @param aray mapa bot
     * @param length tamaño barco
     * @return true si se posiciona y false si no logra posicionarse
     */
    public static boolean CardinalBot (String[][]aray,int length){
        int nord1,nord2;
        //Position for bot
        do {
            nord1 = botposi(aray);
            nord2 = botposi(aray);
        }while (aray[nord2][nord1].equalsIgnoreCase("B "));
        //select cardinal point
        String north = String.valueOf((int)((Math.random()+1)*2));
        return Place(aray,length,nord1,nord2,north);
    }
    /**
     * Posiciona el barco en las cordenadas y pregunta en que direccion lo quiere posicionar
     * @param aray mapa player
     * @param cords cordenadas
     * @param length tamaño barco
     * @return true si se posiciona y false si no logra posicionarse
     */
    public static boolean Cardinal (String[][]aray,String cords,int length){
        int nord1,nord2;
        String pos1,pos2;
        //with the cords check if there's somthing
        do {
            String position = Position(aray,cords);
            pos1 = position.substring(0,2);
            pos2 = position.substring(2,4);
            nord1 = Integer.parseInt(pos1.replace(" ",""));
            nord2 = Integer.parseInt(pos2.replace(" ",""));
            if (aray[nord2][nord1].equalsIgnoreCase("B ")){
                System.out.println();
                System.out.println("\t\tThere's somthing there:\n");
                Tools.Show1(aray);
                System.out.print("\t\t Select other cords:");
                cords = CheckMap(aray);
            }
        }while (aray[nord2][nord1].equalsIgnoreCase("B "));
        //Accept cords
        System.out.println();
        System.out.println("\r\t\t\t\tCoords Accepted\r");
        aray[nord2][nord1] = "B  ";
        System.out.println("\t\t\t  Coords fixed in [" + cords + "]\n");
        Tools.Show1(aray);
        aray[nord2][nord1] = "~  ";
        //Ask in what cardinal point do you want to place it
        System.out.print("\nIn what carnidal point you want to place it?\nYou have North/South/West/East or N/S/W/E:");
        String north = Tools.Give();
        return Place(aray,length,nord1,nord2,north);
    }
    /**
     * Remplaza los espacios para que el programa pueda interpretarlos
     * @param aray mapa
     * @param cords cordenas
     * @return true si se posiciona y false si no logra posicionarse
     */
    public static String Position (String[][]aray,String cords){
        String cord2="",cord1="";
        //Give the position of the array
        boolean a =false;
        cord1 = cordi(aray,cords,cord1,cord2,a);
        cord1 = cord1.replace(" ","");
        a = true;
        cord2 = cordi(aray,cords,cord1,cord2,a);
        cord2 = cord2.replace(" ","");
        String end="";
        for (int i = 1; i < aray.length; i++) {
            for (int j = 1; j < aray[0].length; j++) {
                String c = aray[0][j];
                String b = aray[i][0];
                //Check position
                if (cord1.equalsIgnoreCase(c.replace(" ",""))&&cord2.equalsIgnoreCase(b.replace(" ",""))){
                    if (j>9){
                        end = j+""+i+" ";
                    }else {
                        end = j + " " + i + " ";
                    }
                }else if (cord2.equalsIgnoreCase(c.replace(" ",""))&&cord1.equalsIgnoreCase(b.replace(" ",""))){
                    if (j>9){
                        end = j+""+i+" ";
                    }else {
                        end = j +" "+i+" ";
                    }
                }
            }
        }
        return end;
    }
    /**
     * Posiciona los barcos en la direccion mandada
     * @param aray mapa
     * @param length tamaño
     * @param nord1 cordenada 1
     * @param nord2 cordenada 2
     * @param north direccion
     * @return true si se posiciona y false si no logra posicionarse
     */
    public static boolean Place(String[][]aray, int length, int nord1, int nord2, String north ){
        System.out.println();
        //Place ships
        if (north.equalsIgnoreCase("North")||north.equalsIgnoreCase("N")||north.equalsIgnoreCase("1")){
            if (length>nord2 && north.equals("1")){
                return false;
            }
            if (length>nord2){
                System.out.println("\t\t\tI cant place it\n");
            }else {
                for (int i = 1; i < length; i++) {
                    if(CheckWatter(aray, nord1, nord2 - i)) {
                        return false;
                    }
                }
                for (int i = 0; i < length; i++) {
                    aray[nord2-i][nord1] = "B  ";
                }
                return true;
            }
        }
        if (north.equalsIgnoreCase("South")||north.equalsIgnoreCase("S")||north.equalsIgnoreCase("2")){
            if (aray.length<nord2+length && north.equals("2")){
                return false;
            }
            if (aray.length<nord2+length){
                System.out.println("\t\t\tI cant place it\n");
            }else {
                for (int i = 1; i < length; i++) {
                    if(CheckWatter(aray, nord1, nord2 + i)) {
                        return false;
                    }
                }
                for (int i = 0; i < length; i++) {
                    aray[nord2+i][nord1] = "B  ";
                }
                return true;
            }
        }
        if (north.equalsIgnoreCase("West")||north.equalsIgnoreCase("W")||north.equalsIgnoreCase("3")){
            if (length>nord1 && north.equals("3")){
                return false;
            }
            if (length>nord1){
                System.out.println("\t\t\tI cant place it\n");
            }else {
                for (int i = 1; i < length; i++) {
                    if(CheckWatter(aray, nord1 - i, nord2)) {
                        return false;
                    }
                }
                for (int i = 0; i < length; i++) {
                    aray[nord2][nord1-i] = "B  ";
                }
                return true;
            }
        }
        if (north.equalsIgnoreCase("East")||north.equalsIgnoreCase("E")||north.equalsIgnoreCase("4")){
            if (aray.length<nord1+length && north.equals("4")){
                return false;
            }
            if (aray.length<nord1+length){
                System.out.println("\t\t\tI cant place it\n");
            }else {
                for (int i = 1; i < length; i++) {
                    if(CheckWatter(aray, nord1 + i, nord2)) {
                        return false;
                    }

                }
                for (int i = 0; i < length; i++) {
                    aray[nord2][nord1+i] = "B  ";
                }
                return true;
            }
        }
        return false;
    }
    /**
     * Comprueba si hay agua
     * @param aray mapa
     * @param cord1 cordenada 1
     * @param cord2 cordenada 2
     * @return true si se posiciona y false si no logra posicionarse
     */
    public static boolean CheckWatter (String[][] aray,int cord1,int cord2){
        //Check if there water
        return !aray[cord2][cord1].equalsIgnoreCase("~  ");
    }
    /**
     * Genera un numero random para que tenga una direccion para posicionar el barco
     * @param aray mapa
     * @return devuelve hacia donde direcciona el barco el bot
     */
    public static int botposi (String[][] aray){
        //Generate a random number
        int nord1 = (int)((Math.random())*(aray.length));
        if (nord1==0){
            nord1+=1;
        }
        return nord1;
    }
    /**
     * Recibe las 2 cordenadas
     * @param map mapa
     * @param cords cordenadas juntas
     * @param cord1 cordenada 1
     * @param cord2 cordenada 2
     * @param a si es bot o no
     * @return cordenadas
     */
    public static String cordi (String[][]map,String cords,String cord1,String cord2, boolean a){
        String word;
        if (cords.length()>2){
            cord1 = cords.charAt(0)+" ";
            cord2 = cords.charAt(2)+" ";
            for (int i = 1; i < map[0].length; i++) {
                word = ((char) ('A' + i - 1))+" " ;
                if (a){
                    if (cord1.equalsIgnoreCase(word)){
                        return cord2 = cords.substring(1,3);
                    }
                }else{
                    if (cord2.equalsIgnoreCase(word)){
                        return cord1 = cords.substring(0,2);
                    }
                }
            }
        }else {
            if (map.length>12){
                cord1 = cords.charAt(0)+" ";
                cord2 = cords.charAt(1)+" ";
                for (int i = 1; i < map[0].length; i++) {
                    word = ((char) ('A' + i - 1))+" " ;
                    if (a){
                        if (cord1.equalsIgnoreCase(word)){
                            return cord2 = cords.charAt(1)+"";
                        }
                    }else{
                        if (cord2.equalsIgnoreCase(word)){
                            return cord1 = cords.charAt(0)+"";
                        }
                    }
                }
            } else {
                if (a){
                    return cord2 = cords.charAt(1)+"";
                }else {
                    return cord1 = cords.charAt(0)+"";
                }
            }
        }
        if (a){
            return cord2;
        }else {
            return cord1;
        }
    }
}
