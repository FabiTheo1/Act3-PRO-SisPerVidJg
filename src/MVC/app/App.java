package MVC.app;

import MVC.controller.GestorJuego;
import MVC.model.*;
import MVC.view.ConsolaView;

/**
 * CLASE APP (MAIN) - Creada e implementada por Fabian (Director de Juego)
 * Esta clase une el patrón MVC. Instancia la Vista y el Controlador (Gestor),
 * y contiene el bucle principal de ejecución del programa.
 */
public class App {
    public static void main(String[] args) {
        // Inicialización de los componentes del patrón MVC
        ConsolaView vista = new ConsolaView();
        GestorJuego gestor = new GestorJuego();
        
        // 1. Demostración del Patrón de Composición (Persona 4)
        // En lugar de que el personaje tenga el daño fijo, se le "inyecta" un objeto Arma.
        Arma arcoPro = new Arma.Arco(); 
        CombatienteDistancia legolas = new CombatienteDistancia("Legolas", 5, 100, 10, 5, arcoPro);

        // Añadimos el personaje al gestor (desarrollado por Persona 1)
        gestor.agregarPersonaje(legolas);

        boolean salir = false;
        while (!salir) {
            vista.mostrarMenu();
            int opcion = vista.pedirOpcion();

            switch (opcion) {
                case 1:
                    // Muestra la lista usando el toString() sobrescrito
                    gestor.mostrarPersonajes();
                    break;
                case 2:
                    vista.mostrarMensaje("--- Simulacro de Combate (Polimorfismo) ---");
                    // REQUISITO DE RÚBRICA: Uso de Polimorfismo.
                    // El bucle llama al método atacar() sin importar si el personaje es Físico, Mágico o a Distancia.
                    // Java decide en tiempo de ejecución qué método atacar() ejecutar (Dynamic Binding).
                    for (Personaje p : gestor.getListaPersonajes()) {
                        p.atacar(legolas); 
                    }
                    break;
                case 3:
                    vista.mostrarMensaje("--- Uso de Habilidades Especiales (instanceof) ---");
                    // REQUISITO DE RÚBRICA: Uso de instanceof e interfaces.
                    // Recorremos la lista y verificamos qué personajes tienen la "capacidad" de Curacion.
                    for (Personaje p : gestor.getListaPersonajes()) {
                        if (p instanceof Curacion) {
                            ((Curacion) p).autocurar(); // Casteo seguro gracias al instanceof
                        } else {
                            vista.mostrarMensaje(p.getNombre() + " no tiene habilidades de curación.");
                        }
                    }
                    break;
                case 4:
                    salir = true;
                    vista.mostrarMensaje("Saliendo del sistema...");
                    break;
                default:
                    vista.mostrarMensaje("Opción no válida.");
            }
        }
    }
}