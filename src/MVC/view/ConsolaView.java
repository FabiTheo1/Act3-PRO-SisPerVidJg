package MVC.view;
import java.util.Scanner;

/**
 * CLASE CONSOLA VIEW - Creada por Fabian (Director de Juego)
 * Se encarga de mostrar los menús y recoger los datos del usuario.
 */
public class ConsolaView {
    private Scanner scanner;

    public ConsolaView() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    // ====================================================================
    // MÉTODOS NUEVOS PARA LA INTERFAZ GRÁFICA
    // ====================================================================
    public void mostrarMenu() {
        System.out.println("\n--- ⚔️ SISTEMA DE PERSONAJES RPG ⚔️ ---");
        System.out.println("1. Crear nuevo personaje");
        System.out.println("2. Listar todos los personajes");
        System.out.println("3. Simular turno de ataques");
        System.out.println("4. Demostrar capacidades especiales");
        System.out.println("5. Salir");
        System.out.print("Elige una opción: ");
    }

    public void mostrarMenuCreacion() {
        System.out.println("\n--- 🛠️ CREACIÓN DE PERSONAJE ---");
        System.out.println("1. Arquero (Arma: Arco Largo)");
        System.out.println("2. Ballestero (Arma: Ballesta Pesada)");
        System.out.println("3. Mosquetero (Arma: Mosquete Imperial)");
        System.out.println("4. Escaramuzador (Arma: Honda + Escudo + Daga/Espada)");
        System.out.println("5. Arquero Mágico (Magia + Arco Largo)");
        System.out.print("Elige la clase: ");
    }

    public void mostrarMenuSecundariaEscaramuzador() {
        System.out.println("El Escaramuzador necesita un arma para rematar cuerpo a cuerpo:");
        System.out.println("1. Daga de Combate");
        System.out.println("2. Espada Corta");
        System.out.print("Elige arma secundaria: ");
    }

    public void mostrarMenuPrincipalEscaramuzador() {
        System.out.println("El Escaramuzador necesita un arma arrojadiza principal:");
        System.out.println("1. Honda de Cuero (Infinita, menos daño)");
        System.out.println("2. Jabalina de Acero (1 uso, mucho daño)");
        System.out.print("Elige arma principal: ");
    }

    public void mostrarMenuCombate() {
        System.out.println("\n--- ⚔️ TU TURNO ⚔️ ---");
        System.out.println("1. Atacar");
        System.out.println("2. Recargar Arma");
        System.out.print("¿Qué deseas hacer?: ");
    }

    public int pedirOpcion() {
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner (muy importante tras leer un int)
        return opcion;
    }

    public String pedirTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }
}