package MVC.controller;

import MVC.model.Personaje;

import java.util.ArrayList;

public class GestorJuego {
    private ArrayList<Personaje> listaPersonajes;

    public GestorJuego() {
        this.listaPersonajes = new ArrayList<>();
    }

    // Método para agregar un personaje a la lista
    public void agregarPersonaje(Personaje personaje) {
        if (personaje == null || personaje.getNombre() == null || personaje.getNombre().isEmpty()) { // Validación para evitar agregar personajes nulos o con nombres vacíos
            System.out.println("El personaje no puede ser nulo y debe tener un nombre válido.");
            return;
        } else if (this.listaPersonajes.contains(personaje)) { // Validación para evitar agregar personajes duplicados
            System.out.println("El personaje ya existe en la lista.");
            return;
        } else {
        listaPersonajes.add(personaje);
        System.out.println("Personaje agregado: " + personaje.getNombre());
        }
    }

    // Método para eliminar un personaje de la lista
    public void eliminarPersonaje(Personaje personaje) {
        if (this.listaPersonajes.contains(personaje)) { // Validación para verificar si el personaje existe en la lista antes de eliminarlo
            listaPersonajes.remove(personaje);
            System.out.println("Personaje eliminado: " + personaje.getNombre());
        } else {
            System.out.println("El personaje no existe en la lista.");
        }
    }

    // Método para mostrar la lista de personajes
    public void mostrarPersonajes() {
        if (listaPersonajes.isEmpty()) { // Validación para verificar si la lista de personajes está vacía antes de mostrarla
            System.out.println("No hay personajes en la lista.");
        } else {
            // Mostrar la lista de personajes utilizando el método toString() de cada personaje para una representación más detallada
            System.out.println("Lista de personajes:");
            for (Personaje personaje : listaPersonajes) {
                System.out.println("- " + personaje.toString());
            }
        }
    }
}
