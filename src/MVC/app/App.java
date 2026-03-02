package MVC.app;

import MVC.controller.GestorJuego;
import MVC.model.*;
import MVC.view.ConsolaView;

public class App {
    public static void main(String[] args) {
        ConsolaView vista = new ConsolaView();
        GestorJuego gestor = new GestorJuego();
        
        // 1. Creación de las armas
        Arma arcoPro = new Arma.Arco();
        
        // 2. Creación del personaje pasándole el arma por composición
        CombatienteDistancia legolas = new CombatienteDistancia("Legolas", 5, 100, 10, 5, arcoPro);

        // Añadimos el personaje al gestor
        gestor.agregarPersonaje(legolas);

        boolean salir = false;
        while (!salir) {
            vista.mostrarMenu();
            int opcion = vista.pedirOpcion();

            switch (opcion) {
                case 1:
                    gestor.mostrarPersonajes();
                    break;
                case 2:
                    vista.mostrarMensaje("--- Simulacro de Combate (Polimorfismo) ---");
                    // CORRECCIÓN: Ahora todos atacan a 'legolas' en lugar de al inexistente 'arq1'
                    for (Personaje p : gestor.getListaPersonajes()) {
                        p.atacar(legolas); 
                    }
                    break;
                case 3:
                    vista.mostrarMensaje("--- Uso de Habilidades Especiales (instanceof) ---");
                    for (Personaje p : gestor.getListaPersonajes()) {
                        if (p instanceof Curacion) {
                            ((Curacion) p).autocurar();
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