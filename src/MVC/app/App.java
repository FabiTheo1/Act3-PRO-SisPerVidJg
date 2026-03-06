package MVC.app;

import MVC.view.ConsolaView;

public class App {
    public static void main(String[] args) {
<<<<<<< HEAD
        ConsolaView vista = new ConsolaView();
        // TODO: Instanciar GestorJuego (Persona 1)
        
        vista.mostrarMensaje("Iniciando el gestor de personajes...");

        // TODO: Crear instancias de Guerrero, Mago, etc. (Personas 2 y 3)
        // TODO: Añadir los personajes al GestorJuego

        boolean salir = false;
        while (!salir) {
            vista.mostrarMenu();
            int opcion = vista.pedirOpcion();

            switch (opcion) {
                case 1:
                    vista.mostrarMensaje("Listando personajes...");
                    // TODO: Llamar al GestorJuego para listar con toString()
                    break;
                case 2:
                    vista.mostrarMensaje("Iniciando fase de combate...");
                    // TODO: Bucle polimórfico (for p : personajes -> p.atacar())
                    break;
                case 3:
                    vista.mostrarMensaje("Usando habilidades especiales...");
                    // TODO: Bucle con instanceof (if p instanceof Curable...)
                    break;
                case 4:
                    salir = true;
                    vista.mostrarMensaje("Saliendo del sistema...");
                    break;
                default:
                    vista.mostrarMensaje("Opción no válida.");
            }
        }
=======

>>>>>>> origin/PersonajesFisicos-Jeremi
    }
}