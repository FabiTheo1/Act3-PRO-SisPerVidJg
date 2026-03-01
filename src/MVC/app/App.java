package MVC.app;

import MVC.controller.GestorJuego;
import MVC.model.Asesino;
import MVC.model.Defendible;
import MVC.model.Guerrero;
import MVC.model.Personaje;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("--- INICIANDO JUEGO ---");
        GestorJuego gestor = new GestorJuego();

        // 1. Instanciar (crear) los personajes físicos
        Guerrero g1 = new Guerrero("Jeremi", 10, 150, 20, 15, 50);
        Asesino a1 = new Asesino("Bartolo", 12, 100, 18, 5, 0.40);

        // 2. Agregarlos al gestor
        gestor.agregarPersonaje(g1);
        gestor.agregarPersonaje(a1);

        System.out.println("\n--- LISTADO DE PERSONAJES ---");
        gestor.mostrarPersonajes();

        System.out.println("\n--- DEMOSTRACIÓN DE ACCIONES Y POLIMORFISMO ---");
        // Habilidades únicas
        g1.gritoDeGuerra();
        a1.emboscada();

        System.out.println("\n--- COMBATE POLIMÓRFICO ---");
        // Demostramos polimorfismo: Llamamos a atacar() sin saber qué clase
        // exacta es
        // Simulamos que el objetivo es un "Orco" o muñeco de pruebas temporal para ver
        // el ataque
        Guerrero enemigoOrco = new Guerrero("Orco", 5, 500, 5, 5, 0);

        // Para probar el bucle y el instanceof
        Personaje[] equipo = { g1, a1 };

        for (Personaje p : equipo) {
            p.atacar(enemigoOrco);

            if (p instanceof Defendible) {
                ((Defendible) p).defender();
            }
        }

        System.out.println("\nSalud restante del enemigo: " + enemigoOrco.getSalud());
    }
}