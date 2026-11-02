package ec.edu.uce.agenda_contactos;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    public void mostrarMenu(String[] nombres, String[] telefonos, int[] edades, int p) {

        Scanner sc = new Scanner(System.in);
        Acciones f = new Acciones();
        int opcion = 0;

        do {
            System.out.println("\n==========================================");
            System.out.println("             AGENDA DE CONTACTOS");
            System.out.println("==========================================");
            System.out.println(" 1. Nuevos contactos");
            System.out.println(" 2. Buscar por nombre - Reporte de datos");
            System.out.println(" 3. Buscar por nombre - Eliminar");
            System.out.println(" 4. Buscar por nombre - Modificar teléfono");
            System.out.println(" 5. Salir");
            System.out.println("==========================================");

            try {
                System.out.print("Seleccione una opción: ");
                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {

                    case 1:
                        System.out.println("====== Nuevos contactos ======");
                        p = f.nuevosContactos(nombres, telefonos, edades, p);
                        break;

                    case 2:
                        System.out.println("====== Buscar por nombre - Reporte de datos ======");
                        if (p == 0) {
                            System.out.println("No existen contactos registrados.");
                        } else {
                            f.buscarContacto(nombres, telefonos, edades, p);
                        }
                        break;

                    case 3:
                        System.out.println("====== Buscar por nombre - Eliminar ======");
                        p=f.eliminarContacto(nombres, telefonos, edades, p);
                        break;

                    case 4:
                        System.out.println("====== Buscar por nombre - Modificar teléfono =====");
                        p=f.modificarTelefono(nombres, telefonos, edades, p);
                        break;

                    case 5:
                        System.out.println("Gracias por utilizar la agenda.");
                        break;

                    default:
                        System.out.println("Seleccione una opción del 1 al 5.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Ingrese únicamente números.");
                sc.nextLine();
            }

        } while (opcion != 5);
    }



}
