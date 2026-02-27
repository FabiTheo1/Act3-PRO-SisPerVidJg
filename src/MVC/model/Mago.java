package MVC.model;

import MVC.exceptions.ManaInsuficienteException;


/**
 * Clase Mago: Representa un personaje de tipo mágico especializado en elementos y curación.
 * Hereda de personajeMagico e implementa la interfaz Curacion.
 */

public class Mago extends personajeMagico implements Curacion {
    private String elemento;
    private String escuela;
    private boolean tieneGrimorio;
    private Personaje objetivo;

    public Mago(String nombre, int nivel, int saludMax, int mana, int poderMagico, String elemento, String escuela,
            boolean tieneGrimorio, Personaje objetivo) {
        super(nombre, nivel, saludMax, mana, poderMagico);
        this.elemento = elemento;
        this.escuela = escuela;
        this.tieneGrimorio = tieneGrimorio;
        this.objetivo = objetivo;
    }

    // --- GETTERS Y SETTERS ---

    public Personaje getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Personaje objetivo) {
        this.objetivo = objetivo;
    }

    public String getElemento() {
        return elemento;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
    }

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }

    public boolean isTieneGrimorio() {
        return tieneGrimorio;
    }

    public void setTieneGrimorio(boolean tieneGrimorio) {
        this.tieneGrimorio = tieneGrimorio;
    }

    // --- LÓGICA DE COMBATE (Sobrescribe Personaje) ---

    @Override
    public void atacar(Personaje objetivo) {
        int costeMana = 10;
        // Intentamos gastar maná para un ataque mágico
        if (gastarMana(costeMana)) {
            int danioBase = 10 + getPoderMagico();
            
            // Bonus pasivo si el mago posee su libro de hechizos
            if (tieneGrimorio) {
                danioBase += 5; 
            }

            System.out.println(getNombre() + " lanza un hechizo de " + elemento + " a " + objetivo.getNombre());
            objetivo.recibirDanio(danioBase);
        } else {
            // Si no hay maná, realiza un ataque físico muy débil
            System.out.println(getNombre() + " no tiene maná suficiente. Realiza un ataque físico débil.");
            objetivo.recibirDanio(2);
        }
    }

    @Override
    public void usarHabilidadEspecial() {
        try {
            // Habilidad de alto coste que requiere gestión de excepciones
            consumirMana(40);
            System.out.println(getNombre() + " canaliza la energía de la escuela " + escuela + " para un ataque devastador.");
        } catch (ManaInsuficienteException e) {
            System.out.println("Error de lanzamiento: " + e.getMessage());
        }
    }

    // --- LÓGICA DE CURACIÓN (Interfaz Curacion) ---

    @Override
    public int curar(Personaje objetivo) {
        int costeMana = 15;
        if (gastarMana(costeMana)) {
            int cantidadCurada = 15 + (getPoderMagico() / 2);
            // Se asume que la clase Personaje tiene el método recibirCuracion
            objetivo.recibirCuracion(cantidadCurada);
            System.out.println(getNombre() + " ha curado a " + objetivo.getNombre() + " +" + cantidadCurada + " HP.");
            return cantidadCurada;
        }
        System.out.println(getNombre() + " no tiene maná para realizar la curación.");
        return 0;
    }

    @Override
    public int autocurar() {
        int costeMana = 10;
        if (gastarMana(costeMana)) {
            int saludAnterior = getSalud();
            // El método setSalud de la clase padre ya valida no exceder el máximo
            setSalud(getSalud() + 20);
            int curacionReal = getSalud() - saludAnterior;
            System.out.println(getNombre() + " utiliza magia vital sobre sí mismo. +" + curacionReal + " HP.");
            return curacionReal;
        }
        return 0;
    }

    // --- MÉTODOS DE ESTADO ---

    @Override
    public void recibirDanio(int cantidad) {
        // Ejecutamos la lógica base de Personaje (restar vida)
        super.recibirDanio(cantidad);
        // Añadimos comportamiento extra (feedback por consola)
        System.out.println("[SISTEMA] " + getNombre() + " recibió daño. Salud restante: " + getSalud());
    }

    @Override
    public String toString() {
        return String.format("Mago [ID: %s | Nombre: %s | Salud: %d/%d | Mana: %d/%d | Elemento: %s]",
                getId(), getNombre(), getSalud(), getSaludMax(), getMana(), getManaMax(), elemento);
    }
}
