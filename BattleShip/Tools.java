import java.util.Scanner;
/**
 * Clase Tools que es usada por la mayoria de clases
 * @author JuanLuis
 * @version V.02
 */
public class Tools {
    /**
     * Muestra solo el mapa que le pases
     * @param aray mapa
     */
    public static void Show1 (String[][]aray){
        // Show the array
        for (int i = 0; i < aray.length; i++) {
            for (int j = 0; j < aray[0].length; j++) {
                if (j==0){
                    System.out.print("\t\t"+aray[i][j]);
                }else {
                    System.out.print(aray[i][j]);
                }
            }
            System.out.println();
        }
    }
    /**
     * Muestro los 2 mapas (Suelen ser el de barcos y disparos de 1 de los jugadores)
     * @param aray mapa 1
     * @param aray2 mapa 2
     */
    public static void Show2 (String[][]aray, String[][]aray2){
        // Show two arrays
        for (int i = 0; i < aray.length; i++) {
            for (int j = 0; j < aray[0].length; j++) {
                System.out.print(aray[i][j]);
            }
            System.out.print("\t\t");
            for (int j = 0; j < aray[0].length; j++) {
                System.out.print(aray2[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("\t\t\tShips\t\t\t\t\t\t\t\t\tShots");
        System.out.println();
    }
    /**
     * Pido una cordenada
     * @return cordenada
     */
    public static String Give (){
        //Give information
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }
    /**
     * Copia un String[][] a otro disctinto
     * @param aray mapa 1
     * @param copy mapa para copiar
     */
    public static void Copy(String[][]aray, String[][] copy){
        // Copy array1 in array2
        for (int i = 0; i < aray.length; i++) {
            System.arraycopy(aray[i], 0, copy[i], 0, aray[0].length);
        }
    }
    /**
     * Rellena el mapa con los datos necesarios
     * @param aray mapa para rellenar
     */
    public static void Fill(String[][] aray){
        //Fill map with water
        for (int i = 1; i < aray.length; i++) {
            for (int j = 1; j < aray[0].length; j++) {
                if (j>10){
                    aray[i][j] = "~  ";
                }else {
                    aray[i][j] = "~  ";
                }

            }
        }
        //Fill map with numbers
        for (int i = 1; i < aray.length; i++) {
            if (i>10){
                aray[0][i] = (i-1)+ " ";
            }else {
                aray[0][i] = (i-1)+ "  ";
            }

        }
        //Fill map with letters
        for (int i = 1; i < aray[0].length; i++) {
            aray[i][0] = ((char) ('A' + i - 1))+" " ;
        }
        //Fill map[0][0] with 2 spaces
        aray[0][0] = "/ ";
    }
}