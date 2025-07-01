package ar.edu.utn.frc.tup.lciii.handlers;

public class ImpresoLetraPorLetra {
    public static void println(String text) {
        try {
            for (int i = 0; i < text.length(); i++) {
                System.out.print(text.charAt(i));
                Thread.sleep(10); // Ajusta el tiempo de espera según lo deseado
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
