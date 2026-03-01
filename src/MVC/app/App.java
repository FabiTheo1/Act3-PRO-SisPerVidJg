package MVC.app;

import MVC.controller.GestorJuego;
import MVC.model.Lanzar;
import MVC.model.Asesino;
import MVC.model.Defendible;
import MVC.model.Guerrero;
import MVC.model.Personaje;
import MVC.model.Sigiloso;

import MVC.exceptions.AccionInvalidaException;
import MVC.exceptions.AtributoInvalidoException;
import MVC.exceptions.GestorException;
import MVC.exceptions.SinMunicionException;

public class App {
    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("    INICIANDO SISTEMA DE PERSONAJES    ");
        System.out.println("=========================================\n");

        GestorJuego gestor = new GestorJuego();

        // 1. Instanciación (Añadido el parámetro de munición al final)
        Guerrero g1 = new Guerrero("Jeremi", 10, 150, 20, 15, 50, 2); // 2 hachas
        Asesino a1 = new Asesino("Bartolo", 12, 100, 18, 5, 0.40, 1); // 1 cuchillo
        Guerrero enemigo = new Guerrero("Muñeco de Pruebas", 1, 500, 0, 0, 0, 0);

        gestor.agregarPersonaje(g1);
        gestor.agregarPersonaje(a1);
        gestor.agregarPersonaje(enemigo);

        System.out.println("\n--- LISTADO DE PERSONAJES ---");
        gestor.mostrarPersonajes();

        System.out.println("\n=========================================");
        System.out.println("   DEMOSTRACIÓN DE POLIMORFISMO E INTERFACES ");
        System.out.println("=========================================");

        // Habilidades propias
        g1.gritoDeGuerra();
        a1.emboscada();

        // Array de personajes para demostrar polimorfismo
        Personaje[] equipoFisico = { g1, a1 };

        System.out.println("\n--- BUCLE POLIMÓRFICO ---");
        for (Personaje p : equipoFisico) {
            // Llamada polimórfica estándar
            p.atacar(enemigo);

            // Uso de instanceof evaluando las nuevas interfaces
            if (p instanceof Defendible) {
                ((Defendible) p).defender();
            }
            if (p instanceof Sigiloso) {
                ((Sigiloso) p).ocultarse();
            }
            if (p instanceof Lanzar) {
                ((Lanzar) p).lanzarObjeto(enemigo);
            }
            System.out.println("-".repeat(40));
        }

        System.out.println("\n=========================================");
        System.out.println("    PRUEBAS DE EXCEPCIONES Y ERRORES   ");
        System.out.println("=========================================");

        // PRUEBA NUEVA: Quedarse sin munición
        System.out.print("[TEST 1 - Sin Munición] -> ");
        try {
            // El asesino tenía 1 cuchillo y lo gastó en el bucle anterior, esto debe
            // fallar:
            a1.lanzarObjeto(enemigo);
        } catch (SinMunicionException e) {
            System.err.println("CAPTURADO: " + e.getMessage());
        }

        // PRUEBA 2: Atributo Inválido
        System.out.print("[TEST 2 - Stats Negativos] -> ");
        try {
            Guerrero gError = new Guerrero("Fail", 1, 100, -5, 10, 0, 0);
        } catch (AtributoInvalidoException e) {
            System.err.println("CAPTURADO: " + e.getMessage());
        }

        // PRUEBA 3: Atacarse a sí mismo
        System.out.print("[TEST 3 - Fuego Amigo] -> ");
        try {
            g1.atacar(g1);
        } catch (AccionInvalidaException e) {
            System.err.println("CAPTURADO: " + e.getMessage());
        }

        // PRUEBA 4: Atacar a un muerto
        System.out.print("[TEST 4 - Atacar a un cadáver] -> ");
        try {
            enemigo.recibirDanio(enemigo.getSaludMax()); // Matamos al Muñeco
            g1.lanzarObjeto(enemigo); // Intentamos lanzarle un objeto estando muerto
        } catch (AccionInvalidaException e) {
            System.err.println("CAPTURADO: " + e.getMessage());
        }

        // PRUEBA 5: GestorException (Aviso para tu compañero)
        System.out.print("[TEST 5 - Añadir Nulo al Gestor] -> ");
        try {
            gestor.agregarPersonaje(null);
        } catch (GestorException e) {
            System.err.println("CAPTURADO: " + e.getMessage());
        }

        System.out.println("\nPruebas finalizadas. El sistema Físico es 100% robusto.");
    }
}