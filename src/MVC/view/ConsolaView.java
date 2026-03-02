package MVC.view;
import java.util.Scanner;

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
        System.out.println("1. Listar todos los personajes");
        System.out.println("2. Simular turno de ataques (Polimorfismo)");
        System.out.println("3. Demostrar capacidades especiales (Interfaces)");
        System.out.println("4. Salir");
        System.out.print("Elige una opción: ");
    }

    public int pedirOpcion() {
        return scanner.nextInt();
    }
}