package MVC.app;

<<<<<<< HEAD
=======
import MVC.controller.GestorJuego;
import MVC.model.*;
import MVC.view.ConsolaView;

/**
 * CLASE APP (MAIN) - Creada e implementada por Fabian (Director de Juego)
 */
>>>>>>> origin/Director-Juego-Fabian
public class App {
<<<<<<< HEAD
    public static void main(String[] args) {
<<<<<<< HEAD
        ConsolaView vista = new ConsolaView();
        GestorJuego gestor = new GestorJuego();
        
        // Personaje de prueba inicial
        Personaje legolas = new Arquero("Legolas", 5, 100, 10, 5);
        gestor.agregarPersonaje(legolas);

        boolean salir = false;
        while (!salir) {
            vista.mostrarMenu();
            int opcion = vista.pedirOpcion();

            switch (opcion) {
                case 1:
                    crearPersonajeInteractivo(vista, gestor);
                    break;
                case 2:
                    gestor.mostrarPersonajes();
                    break;
                case 3:
                    if (gestor.getListaPersonajes().isEmpty()) {
                        vista.mostrarMensaje("X Necesitas crear al menos un personaje en la Opción 1 primero.");
                        break;
                    }
                    
                    // Seleccionar con quién queremos pelear
                    vista.mostrarMensaje("\n--- SELECCIONA TU LUCHADOR ---");
                    for (int i = 0; i < gestor.getListaPersonajes().size(); i++) {
                        System.out.println((i+1) + ". " + gestor.getListaPersonajes().get(i).getNombre());
                    }
                    System.out.print("Elige un número: ");
                    int indice = vista.pedirOpcion() - 1;
                    
                    if (indice < 0 || indice >= gestor.getListaPersonajes().size()) {
                        vista.mostrarMensaje("Opción inválida.");
                        break;
                    }
                    
                    Personaje jugador = gestor.getListaPersonajes().get(indice);
                    
                    // Generar el enemigo
                    Personaje orco = new Orco("Orco Berserker", 2, 120, 18, 5);

                    vista.mostrarMensaje("\n=================================");
                    vista.mostrarMensaje(" =====|- ¡COMIENZA EL COMBATE A MUERTE! -|=====");
                    vista.mostrarMensaje(" " + jugador.getNombre() + " VS " + orco.getNombre());
                    vista.mostrarMensaje("=================================");

                    // Bucle de combate por turnos
                    while (jugador.estaVivo() && orco.estaVivo()) {
                        vista.mostrarMensaje("\n<3 Salud " + jugador.getNombre() + ": " + jugador.getSalud() + " | <3 Salud " + orco.getNombre() + ": " + orco.getSalud());
                        vista.mostrarMenuCombate();
                        int accion = vista.pedirOpcion();

                        // Turno del Jugador
                        if (accion == 1) {
                            jugador.atacar(orco);
                        } else if (accion == 2) {
                            // Validar quién puede recargar usando instanceof
                            if (jugador instanceof CombatienteDistancia) {
                                ((CombatienteDistancia) jugador).recargarArma();
                            } else if (jugador instanceof ArqueroMagico) {
                                ((ArqueroMagico) jugador).recargarArma();
                            } else {
                                vista.mostrarMensaje("¡OJO! " + jugador.getNombre() + " usa ataques cuerpo a cuerpo, no necesita recargar.");
                            }
                        } else {
                            vista.mostrarMensaje("Pierdes el turno por dudar en el campo de batalla.");
                        }

                        // Turno del Enemigo (Si sobrevivió al ataque)
                        if (orco.estaVivo()) {
                            vista.mostrarMensaje("\n-- Turno del Enemigo --");
                            orco.atacar(jugador);
                        }
                    }

                    // 4. Resultado del combate
                    vista.mostrarMensaje("\n=================================");
                    if (jugador.estaVivo()) {
                        vista.mostrarMensaje(":D ¡VICTORIA! " + jugador.getNombre() + " ha derrotado al terrible " + orco.getNombre() + ".");
                    } else {
                        vista.mostrarMensaje("xP DERROTA... " + jugador.getNombre() + " ha caído en combate.");
                        // Opcional: gestor.eliminarPersonaje(jugador); si quieres "muerte permanente"
                    }
                    break;
                case 4:
                    vista.mostrarMensaje("--- Uso de Habilidades Especiales ---");
                    for (Personaje p : gestor.getListaPersonajes()) {
                        if (p instanceof Curacion) {
                            ((Curacion) p).autocurar();
                        } else {
                            vista.mostrarMensaje(p.getNombre() + " no tiene habilidades de curación.");
                        }
                    };
                    break;
                case 5:
                    salir = true;
                    vista.mostrarMensaje("Saliendo del sistema...");
                    break;
                default:
                    vista.mostrarMensaje("Opción no válida.");
            }
        }
=======

>>>>>>> origin/PersonajesFisicos-Jeremi
=======
    public static void main(String[] args) throws Exception {
        
>>>>>>> origin/Core-Juego-Daniel
    }

    // ====================================================================
    // MÉTODO CREADO PARA LA CREACIÓN DINÁMICA DE CLASES
    // ====================================================================
    private static void crearPersonajeInteractivo(ConsolaView vista, GestorJuego gestor) {
        vista.mostrarMenuCreacion();
        int claseElegida = vista.pedirOpcion();
        
        if (claseElegida < 1 || claseElegida > 5) {
            vista.mostrarMensaje("ERROR: Clase no válida.");
            return;
        }

        String nombre = vista.pedirTexto("Introduce el nombre del personaje: ");
        
        Personaje nuevoPersonaje = null;
        
        // Valores base (podrías pedirselos al usuario también, pero los fijamos para agilizar)
        int nivel = 1;
        int salud = 100;
        int fuerza = 15;
        int defensa = 10;

        switch (claseElegida) {
            case 1:
                nuevoPersonaje = new Arquero(nombre, nivel, salud, fuerza, defensa);
                break;
            case 2:
                nuevoPersonaje = new Ballestero(nombre, nivel, salud, fuerza, defensa);
                break;
            case 3:
                nuevoPersonaje = new Mosquetero(nombre, nivel, salud, fuerza, defensa);
                break;
            case 4: // ESCARAMUZADOR (Doble Composición interactiva)
                vista.mostrarMenuPrincipalEscaramuzador();
                int opcPrin = vista.pedirOpcion();
                Arma principal = (opcPrin == 2) ? new Arma.Jabalina() : new Arma.Honda();

                vista.mostrarMenuSecundariaEscaramuzador();
                int opcSec = vista.pedirOpcion();
                Arma secundaria = (opcSec == 2) ? new Arma.EspadaCorta() : new Arma.Daga();
                
                nuevoPersonaje = new Escaramuzador(nombre, nivel, salud, fuerza, defensa, principal, secundaria);
                break;
            case 5: // CASO ARQUERO MÁGICO (Mana y Poder mágico en lugar de Fuerza/Defensa)
                int manaMax = 50;
                int poderMagico = 20;
                nuevoPersonaje = new ArqueroMagico(nombre, nivel, salud, manaMax, poderMagico);
                break;
        }

        if (nuevoPersonaje != null) {
            gestor.agregarPersonaje(nuevoPersonaje);
            vista.mostrarMensaje("Exito: ¡" + nombre + " ha sido añadido al equipo con éxito!");
        }
    }
}