package MVC.view;
import java.util.Scanner;

/**
 * CLASE CONSOLA VIEW - Creada por Fabian
 * Aísla toda la interacción con el usuario (System.out y Scanner).
 */
public class ConsolaView {
    private Scanner scanner;

    public ConsolaView() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarMenu() {
        System.out.println("\n--- ⚔️ SISTEMA DE PERSONAJES RPG ⚔️ ---");
        System.out.println("1. Crear nuevo personaje");
        System.out.println("2. Listar todos los personajes");
        System.out.println("3. Simular combate a muerte (Usuario vs Orco)");
        System.out.println("4. Demostrar capacidades especiales (Interfaces)");
        System.out.println("5. Salir");
        System.out.print("Elige una opción: ");
    }

    public void mostrarMenuCreacion() {
        System.out.println("\n--- 🛠️ CREACIÓN DE PERSONAJE ---");
        System.out.println("- Clases a Distancia (Fabian):");
        System.out.println("  1. Arquero (Arma: Arco Largo)");
        System.out.println("  2. Ballestero (Arma: Ballesta Pesada)");
        System.out.println("  3. Mosquetero (Arma: Mosquete Imperial)");
        System.out.println("  4. Escaramuzador (Híbrido Distancia/Cuerpo a cuerpo)");
        System.out.println("  5. Arquero Mágico (Híbrido Magia/Arco)");
        System.out.println("- Clases Físicas (Jeremi) y un Noble (Daniel):");
        System.out.println("  6. Guerrero (Usa Ira y Hachas)");
        System.out.println("  7. Caballero (Usa Lanza y Armadura Pesada)");
        System.out.println("  8. Asesino (Usa Sigilo, Combos y Cuchillos)");
        System.out.println("- Clases Mágicas (Jonay):");
        System.out.println("  9. Mago (Hechizos Elementales y Curación)");
        System.out.println("  10. Nigromante (Drenaje de Almas y Magia Oscura)");
        System.out.println("  11. Druida (Formas de la Naturaleza y Curación)");
        System.out.print("Elige la clase (1-11): ");
    }

    public void mostrarMenuPrincipalEscaramuzador() {
        System.out.println("Elige arma arrojadiza principal para el Escaramuzador:");
        System.out.println("1. Honda de Cuero (Infinita, menos daño)");
        System.out.println("2. Jabalina de Acero (1 uso, mucho daño)");
        System.out.print("Opción: ");
    }

    public void mostrarMenuSecundariaEscaramuzador() {
        System.out.println("Elige arma cuerpo a cuerpo secundaria:");
        System.out.println("1. Daga de Combate");
        System.out.println("2. Espada Corta");
        System.out.print("Opción: ");
    }

    public void mostrarMenuCaballero() {
        System.out.println("Elige el equipamiento de tu Caballero:");
        System.out.println("1. Mandoble a dos manos (Mucho daño, sin escudo)");
        System.out.println("2. Lanza y Escudo (Equilibrado)");
        System.out.println("3. Espada Larga y Escudo (Clásico)");
        System.out.print("Opción: ");
    }

    public void mostrarMenuCombate() {
        System.out.println("\n--- ⚔️ TU TURNO ⚔️ ---");
        System.out.println("1. Atacar Cuerpo a Cuerpo / Hechizo Básico");
        System.out.println("2. Lanzar Objeto a Distancia (Si aplica)");
        System.out.println("3. Recargar Munición");
        System.out.println("4. Habilidad Especial / Defender / Emboscada");
        System.out.print("¿Qué deseas hacer?: ");
    }

    public int pedirOpcion() {
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        return opcion;
    }

    public String pedirTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }
    
    // Nuevo método para facilitar preguntas de Sí o No
    public boolean pedirConfirmacion(String mensaje) {
        System.out.print(mensaje + " (1. Sí / 2. No): ");
        int resp = pedirOpcion();
        return resp == 1;
    }
}