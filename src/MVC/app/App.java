package MVC.app;

import MVC.controller.GestorJuego;
import MVC.model.*;
import MVC.view.ConsolaView;

public class App {
    
    // ====================================================================
    // 1. MÉTODO PRINCIPAL (MAIN) - Aquí está el bucle y el Menú
    // ====================================================================
    public static void main(String[] args) {
        ConsolaView vista = new ConsolaView();
        GestorJuego gestor = new GestorJuego();
        
        vista.mostrarMensaje("=========================================");
        vista.mostrarMensaje("🎮 BIENVENIDO AL MOTOR RPG DEL GRUPO 🎮");
        vista.mostrarMensaje("=========================================");

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
                    // AQUÍ LLAMAMOS AL COMBATE
                    iniciarCombate(vista, gestor);
                    break;
                case 4:
                    vista.mostrarMensaje("--- Uso de Habilidades Especiales (instanceof) ---");
                    for (Personaje p : gestor.getListaPersonajes()) {
                        if (p instanceof Curacion) {
                            ((Curacion) p).autocurar();
                        } else if (p instanceof Sigiloso) {
                            ((Sigiloso) p).esconderse();
                        } else {
                            vista.mostrarMensaje(p.getNombre() + " es un guerrero puro y no usa magia ni sigilo.");
                        }
                    }
                    break;
                case 5:
                    salir = true;
                    vista.mostrarMensaje("Guardando partida... Saliendo del sistema...");
                    break;
                default:
                    vista.mostrarMensaje("Opción no válida.");
            }
        }
    }

    // ====================================================================
    // 2. MÉTODO DE CREACIÓN DE PERSONAJES
    // ====================================================================
    private static void crearPersonajeInteractivo(ConsolaView vista, GestorJuego gestor) {
        vista.mostrarMenuCreacion();
        int claseElegida = vista.pedirOpcion();
        
        if (claseElegida < 1 || claseElegida > 11) {
            vista.mostrarMensaje("❌ Clase no válida.");
            return;
        }

        String nombre = vista.pedirTexto("Introduce el nombre del personaje: ");
        Personaje nuevoPersonaje = null;
        
        int nivel = 1; int salud = 100; int fuerza = 15; int defensa = 10;
        int manaBase = 50; int poderMagico = 20;

        switch (claseElegida) {
            case 1: nuevoPersonaje = new Arquero(nombre, nivel, salud, fuerza, defensa); break;
            case 2: nuevoPersonaje = new Ballestero(nombre, nivel, salud, fuerza, defensa); break;
            case 3: nuevoPersonaje = new Mosquetero(nombre, nivel, salud, fuerza, defensa); break;
            case 4:
                vista.mostrarMenuPrincipalEscaramuzador();
                Arma principal = (vista.pedirOpcion() == 2) ? new Arma.Jabalina() : new Arma.Honda();
                vista.mostrarMenuSecundariaEscaramuzador();
                Arma secundaria = (vista.pedirOpcion() == 2) ? new Arma.EspadaCorta() : new Arma.Daga();
                nuevoPersonaje = new Escaramuzador(nombre, nivel, salud, fuerza, defensa, principal, secundaria);
                break;
            case 5: 
                nuevoPersonaje = new ArqueroMagico(nombre, nivel, salud, manaBase, poderMagico); break;
            
            case 6: // Guerrero
                nuevoPersonaje = new Guerrero(nombre, nivel, salud + 20, fuerza + 5, defensa, 0); break;
            case 7: // Caballero
                vista.mostrarMenuCaballero();
                int opcCaballero = vista.pedirOpcion();
                Arma armaP = new Arma.EspadaLarga(); 
                Arma escu = new Arma.EscudoPequeno();
                if (opcCaballero == 1) { armaP = new Arma.Mandoble(); escu = null; }
                else if (opcCaballero == 2) { armaP = new Arma.Lanza(); }
                nuevoPersonaje = new Caballero(nombre, nivel, salud + 50, fuerza, defensa + 15, 20, 10, armaP, escu);
                break;
            case 8: // Asesino
                nuevoPersonaje = new Asesino(nombre, nivel, salud - 10, fuerza, defensa - 5, 0.35); break;
            
            case 9: // Mago
                String elemento = vista.pedirTexto("¿Qué elemento domina? (Fuego, Hielo, Rayo...): ");
                boolean tieneGrimorio = vista.pedirConfirmacion("¿Lleva un Grimorio antiguo equipado?");
                nuevoPersonaje = new Mago(nombre, nivel, salud, manaBase + 50, poderMagico + 10, elemento, "Destrucción", tieneGrimorio, null);
                break;
            case 10: // Nigromante
                nuevoPersonaje = new Nigromante(nombre, nivel, salud, manaBase + 20, poderMagico, 15, false, 0, "Esqueleto de Hueso"); break;
            case 11: // Druida
                String animal = vista.pedirTexto("¿En qué animal se transforma? (Ej: Oso, Lobo, Tigre): ");
                nuevoPersonaje = new Druida(nombre, nivel, salud + 10, manaBase, poderMagico, animal, 5); 
                break;
        }

        if (nuevoPersonaje != null) {
            gestor.agregarPersonaje(nuevoPersonaje);
            vista.mostrarMensaje("✅ ¡" + nombre + " ha sido añadido al equipo con éxito!");
        }
    }

    // ====================================================================
    // 3. MÉTODO DE COMBATE SIMULADO (AQUÍ ES DONDE VA TU CÓDIGO)
    // ====================================================================
    private static void iniciarCombate(ConsolaView vista, GestorJuego gestor) {
        if (gestor.getListaPersonajes().isEmpty()) {
            vista.mostrarMensaje("❌ Necesitas crear al menos un personaje en la Opción 1 primero.");
            return;
        }
        
        vista.mostrarMensaje("\n--- SELECCIONA TU LUCHADOR ---");
        for (int i = 0; i < gestor.getListaPersonajes().size(); i++) {
            System.out.println((i+1) + ". " + gestor.getListaPersonajes().get(i).getNombre() + " (" + gestor.getListaPersonajes().get(i).getClass().getSimpleName() + ")");
        }
        int indice = vista.pedirOpcion() - 1;
        
        if (indice < 0 || indice >= gestor.getListaPersonajes().size()) {
            vista.mostrarMensaje("Opción inválida.");
            return;
        }
        
        Personaje jugador = gestor.getListaPersonajes().get(indice);
        
        Personaje orco = new PersonajeFisico("Orco Berserker", 2, 120, 18, 5) {
            Arma.HachaBatalla hacha = new Arma.HachaBatalla();
            @Override
            public void atacar(Personaje obj) {
                int danio = getFuerza() + hacha.getDanioBase();
                System.out.println("👹 " + getNombre() + " ruge y golpea con su " + hacha.getNombre() + " causando " + danio + " de daño.");
                obj.recibirDanio(danio);
            }
        };

        vista.mostrarMensaje("\n=================================");
        vista.mostrarMensaje(" ⚔️ ¡COMIENZA EL COMBATE A MUERTE! ⚔️");
        vista.mostrarMensaje(" " + jugador.getNombre() + " VS " + orco.getNombre());
        vista.mostrarMensaje("=================================");

        // Si es mago, aseguramos que tenga al orco como objetivo de la habilidad especial
        if (jugador instanceof Mago) { ((Mago) jugador).setObjetivo(orco); }

        while (jugador.estaVivo() && orco.estaVivo()) {
            vista.mostrarMensaje("\n❤️ Salud " + jugador.getNombre() + ": " + jugador.getSalud() + " | ❤️ Salud " + orco.getNombre() + ": " + orco.getSalud());
            vista.mostrarMenuCombate();
            int accion = vista.pedirOpcion();

            try {
                switch (accion) {
                    case 1: 
                        jugador.atacar(orco); 
                        break;
                    case 2: // Lanzar objeto
                        if (jugador instanceof Lanzar) { ((Lanzar) jugador).lanzarObjeto(orco); } 
                        else { vista.mostrarMensaje("Este personaje no sabe lanzar objetos."); }
                        break;
                    case 3: // Recargar
                        if (jugador instanceof CombatienteDistancia) ((CombatienteDistancia) jugador).recargarArma();
                        else if (jugador instanceof ArqueroMagico) ((ArqueroMagico) jugador).recargarArma();
                        else if (jugador instanceof Asesino) ((Asesino) jugador).recargarArma();
                        else vista.mostrarMensaje("No usas munición recargable.");
                        break;
                    case 4: // Habilidades Especiales
                        if (jugador instanceof PersonajeMagico) ((PersonajeMagico) jugador).usarHabilidadEspecial();
                        else if (jugador instanceof Guerrero) ((Guerrero) jugador).gritoDeGuerra();
                        else if (jugador instanceof Asesino) ((Asesino) jugador).emboscada();
                        else if (jugador instanceof Defendible) ((Defendible) jugador).defender();
                        else vista.mostrarMensaje("No tienes habilidades especiales.");
                        break;
                    default: vista.mostrarMensaje("Pierdes el turno por dudar.");
                }
            } catch (Exception e) {
                vista.mostrarMensaje("⚠️ Acción fallida: " + e.getMessage());
            }

            if (orco.estaVivo()) {
                System.out.println("\n-- Turno del Enemigo --");
                orco.atacar(jugador);
            }
        }

        vista.mostrarMensaje("\n=================================");
        if (jugador.estaVivo()) vista.mostrarMensaje("🏆 ¡VICTORIA! " + jugador.getNombre() + " sobrevive.");
        else vista.mostrarMensaje("☠️ DERROTA... " + jugador.getNombre() + " ha caído.");
    }
}