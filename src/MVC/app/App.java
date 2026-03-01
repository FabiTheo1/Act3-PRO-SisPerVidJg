package MVC.app;

import MVC.controller.GestorJuego;
import MVC.model.Asesino;
import MVC.model.Defendible;
import MVC.model.Guerrero;
import MVC.model.Personaje;

// Importacion de excepciones
import MVC.exceptions.AccionInvalidaException;
import MVC.exceptions.AtributoInvalidoException;
import MVC.exceptions.GestorException;

public class App {
    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("    INICIANDO SISTEMA DE PERSONAJES    ");
        System.out.println("=========================================\n");

        GestorJuego gestor = new GestorJuego();

        // 1. Instanciación (creacion) de personajes
        Guerrero g1 = new Guerrero("Jeremi", 10, 150, 20, 15, 50);
        Asesino a1 = new Asesino("Bartolo", 12, 100, 18, 5, 0.40);
        Guerrero dummy = new Guerrero("Muñeco de Pruebas", 1, 500, 0, 0, 0);

        gestor.agregarPersonaje(g1);
        gestor.agregarPersonaje(a1);
        gestor.agregarPersonaje(dummy);

        System.out.println("\n--- LISTADO DE PERSONAJES (toString) ---");
        gestor.mostrarPersonajes();

        System.out.println("\n=========================================");
        System.out.println("     DEMOSTRACIÓN DE POLIMORFISMO     ");
        System.out.println("=========================================");

        // Habilidades propias
        g1.gritoDeGuerra();
        a1.emboscada();

        // Array para demostrar polimorfismo
        Personaje[] equipoFisico = { g1, a1 };

        System.out.println("\n--- BUCLE POLIMÓRFICO ---");
        for (Personaje p : equipoFisico) {
            // Llamada polimórfica: No sabemos si es Guerrero o Asesino, pero ambos saben
            // atacar
            p.atacar(dummy);

            // Uso OBLIGATORIO de instanceof evaluando la Interfaz (Buena práctica)
            if (p instanceof Defendible) {
                ((Defendible) p).defender();
            }
            System.out.println("-".repeat(30));
        }

        System.out.println("\n=========================================");
        System.out.println("    PRUEBAS DE EXCEPCIONES Y ERRORES   ");
        System.out.println("=========================================");

        // --- PRUEBAS DE EXCEPCIONES ---

        // PRUEBA 1: Atributo Inválido (Fuerza negativa)
        System.out.print("[TEST 1 - Stats Negativos] -> ");
        try {
            Guerrero gError = new Guerrero("Fail", 1, 100, -5, 10, 0);
            gestor.agregarPersonaje(gError);
        } catch (AtributoInvalidoException e) {
            System.err.println("ERROR: " + e.getMessage());
        }

        // PRUEBA 2: Atacarse a sí mismo
        System.out.print("[TEST 2 - Fuego Amigo] -> ");
        try {
            g1.atacar(g1);
        } catch (AccionInvalidaException e) {
            System.err.println("ERROR: " + e.getMessage());
        }

        // PRUEBA 3: Atacar a un muerto
        System.out.print("[TEST 3 - Atacar a un cadáver] -> ");
        try {
            dummy.recibirDanio(dummy.getSaludMax()); // Matamos al Muñeco de pruebas de un golpe
            a1.atacar(dummy); // Intentamos atacarlo estando muerto
        } catch (AccionInvalidaException e) {
            System.err.println("ERROR: " + e.getMessage());
        }

        // PRUEBA 4: GestorException (Añadir un nulo)
        System.out.print("[TEST 4 - Añadir Nulo al Gestor] -> ");
        try {
            gestor.agregarPersonaje(null);
        } catch (GestorException e) {
            System.err.println("ERROR: " + e.getMessage());
        }

        System.out.println("\nPruebas finalizadas. El sistema es robusto y estable.");
    }
}