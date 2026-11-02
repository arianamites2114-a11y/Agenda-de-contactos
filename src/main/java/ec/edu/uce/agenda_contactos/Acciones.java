package ec.edu.uce.agenda_contactos;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Acciones {

    // Método para ingresar números enteros positivos
    public int Valor(int n, String texto) {
        Scanner sc = new Scanner(System.in);

        do {
            try {
                System.out.print(texto + ": ");
                n = sc.nextInt();

                if (n <= 0) {
                    System.out.println("No se permiten números negativos ni cero.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Ingrese únicamente números enteros positivos.");
                n = 0;
                sc.nextLine();
            }

        } while (n <= 0);

        return n;
    }

    // Inicializar arreglo de nombres
    public void inicializarNombres(String[] nombres) {
        for (int i = 0; i < nombres.length; i++) {
            nombres[i] = "";
        }
    }

    // Inicializar arreglo de teléfonos
    public void inicializarTelefonos(String[] telefonos) {

        for (int i = 0; i < telefonos.length; i++) {
            telefonos[i] = "";
        }
    }

    // Inicializar arreglo de edades
    public void inicializarEdades(int[] edades) {

        for (int i = 0; i < edades.length; i++) {
            edades[i] = 0;
        }
    }

    // Validar que el nombre contenga únicamente letras
    public static boolean validarLetras(String info) {
        Pattern pattern = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$");
        Matcher matcher = pattern.matcher(info);
        return matcher.matches();
    }

    // Ingresar nombres
    public String valorNombres(String nombre, String texto) {
        Scanner sc = new Scanner(System.in);

        do {
            System.out.print(texto + ": ");
            nombre = sc.nextLine().trim();

            if (!validarLetras(nombre)) {
                System.out.println("Ingrese únicamente letras.");
            }

        } while (!validarLetras(nombre));

        return nombre;
    }

    // Validar número de teléfono
    public static boolean validarTelefono(String telefono) {
        Pattern pattern = Pattern.compile("^09[0-9]{8}$");
        Matcher matcher = pattern.matcher(telefono);
        return matcher.matches();
    }

    // Ingresar teléfono
    public String valorTelefono(String telefono, String texto) {
        Scanner sc = new Scanner(System.in);

        do {
            System.out.print(texto + ": ");
            telefono = sc.nextLine();

            if (!validarTelefono(telefono)) {
                System.out.println("Ingrese un teléfono válido de 10 dígitos que comience con 09.");
            }

        } while (!validarTelefono(telefono));

        return telefono;
    }

    // Ingresar edades
    public int valorEdad(int edad, String texto) {
        Scanner sc = new Scanner(System.in);

        do {
            try {
                System.out.print(texto + ": ");
                edad = sc.nextInt();

                if (edad <= 0 || edad > 120) {
                    System.out.println("Ingrese una edad entre 1 y 120 años.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Ingrese únicamente números enteros.");
                edad = 0;
                sc.nextLine();
            }

        } while (edad <= 0 || edad > 120);

        return edad;
    }

    public void nomina(String[] nombres, String[] telefonos, int[] edades, int p) {

        System.out.println("\n==============================================================");
        System.out.println("                     AGENDA DE CONTACTOS");
        System.out.println("==============================================================");
        System.out.printf("%-5s | %-25s | %-15s | %-5s%n",
                "N°", "NOMBRE", "TELEFONO", "EDAD");
        System.out.println("--------------------------------------------------------------");

        for (int i = 0; i < p; i++) {

            String nombre = nombres[i].isEmpty() ? "________________" : nombres[i];
            String telefono = telefonos[i].isEmpty() ? "__________" : telefonos[i];
            String edad = edades[i] == 0 ? "____" : String.valueOf(edades[i]);

            System.out.printf("%-5d | %-25s | %-15s | %-5s%n",
                    (i + 1), nombre, telefono, edad);
        }

        System.out.println("==============================================================");
    }

    // Validar que el nombre no esté registrado
    public boolean existeNombre(String[] nombres, int p, String nombre) {

        for (int i = 0; i < p; i++) {

            if (nombres[i].equalsIgnoreCase(nombre)) {
                return true;
            }
        }

        return false;
    }

    // Registrar nuevos contactos
    public int nuevosContactos(String[] nombres, String[] telefonos, int[] edades, int p) {

        int cantidad = 0;
        String nombreNuevo;

        do {
            cantidad = Valor(cantidad, "¿Cuántos contactos desea ingresar?");

            if (p + cantidad > nombres.length) {
                System.out.println("No hay suficientes espacios disponibles.");
                cantidad = 0;
            }

        } while (cantidad == 0);

        for (int i = p; i < p + cantidad; i++) {

            System.out.println("\nCONTACTO " + (i + 1));

            do {
                nombreNuevo = valorNombres("", "Ingrese el nombre");

                if (existeNombre(nombres, i, nombreNuevo)) {
                    System.out.println("Este nombre ya está registrado. Ingrese otro nombre.");
                }

            } while (existeNombre(nombres, i, nombreNuevo));

            nombres[i] = nombreNuevo;
            telefonos[i] = valorTelefono("", "Ingrese el teléfono");
            edades[i] = valorEdad(0, "Ingrese la edad");
        }

        p = p + cantidad;

        System.out.println("\nContactos registrados correctamente.");

        nomina(nombres, telefonos, edades, p);

        return p;
    }

    // Buscar contacto por nombre
    public void buscarContacto(String[] nombres, String[] telefonos, int[] edades, int p) {

        Scanner sc = new Scanner(System.in);
        String nombreBuscar;
        boolean encontrado = false;

        System.out.print("Ingrese el nombre del contacto que desea buscar: ");
        nombreBuscar = sc.nextLine().trim();

        for (int i = 0; i < p; i++) {

            if (nombres[i].equalsIgnoreCase(nombreBuscar)) {

                System.out.println("\nCONTACTO ENCONTRADO");
                System.out.println("--------------------------------");
                System.out.println("Nombre: " + nombres[i]);
                System.out.println("Teléfono: " + telefonos[i]);
                System.out.println("Edad: " + edades[i]);
                System.out.println("--------------------------------");

                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontró ningún contacto con ese nombre.");
        }

        System.out.println("\nNÓMINA ACTUALIZADA");
        nomina(nombres, telefonos, edades, p);

    }

    public int eliminarContacto(String[] nombres, String[] telefonos, int[] edades, int p){
        String buscar = "";
        int posicion = -1;
        if (p==0){
            System.out.println("No existen elementos para eliminar");
            return p;
        }

        buscar =  valorNombres("","Ingrese el nombre y apellido a buscar");

        for (int i=0; i<p; i++){
            if (nombres [i].equals(buscar)){
                posicion = i;
            }
        }

        if (posicion == -1 ){
            System.out.println("El número no se encuentra en el arreglo");
            return p;
        }

        for (int i=posicion; i<p-1;i++){
            nombres [i] = nombres[i+1];
            telefonos[i] = telefonos[i+1];
            edades[i] = edades[i+1];
        }

        p--;
        nomina(nombres, telefonos, edades, p);
        return p;
    }

    public int modificarTelefono(String[] nombres, String[] telefonos, int[] edades, int p){
        String buscar = "", nuevoNum = "";
        int posicion = -1;

        buscar = valorNombres("", "Ingrese el nombre y apellido a buscar");

        for (int i=0; i<p;i++){
            if (nombres[i].equals(buscar)){
                posicion = i;
            }
        }

        if (posicion == -1){
            System.out.println("Contacto no registrado");
            return p;
        }

        do {
            nuevoNum = valorTelefono("", "Ingrese el nuevo número de teléfono");

            if (!validarTelefono(nuevoNum)) {
                System.out.println("Ingrese un teléfono válido de 10 dígitos que comience con 09.");
            }
        } while (!validarTelefono(nuevoNum));

        telefonos[posicion] = nuevoNum;
        nomina(nombres,telefonos,edades, p);

        return p;
    }

}
