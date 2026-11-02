package ec.edu.uce.agenda_contactos;


public class Launcher {
    public static void main(String[] args) {

        int n = 0;
        int p = 0;

        Acciones f = new Acciones();

        n = f.Valor(n, "Ingrese la dimensión de la agenda");

        String[] Anombres = new String[n];
        String[] Atelefonos = new String[n];
        int[] Aedades = new int[n];

        f.inicializarNombres(Anombres);
        f.inicializarTelefonos(Atelefonos);
        f.inicializarEdades(Aedades);

        f.nomina(Anombres, Atelefonos, Aedades, n);

        Menu menu = new Menu();
        menu.mostrarMenu(Anombres, Atelefonos, Aedades, p);
    }
}
