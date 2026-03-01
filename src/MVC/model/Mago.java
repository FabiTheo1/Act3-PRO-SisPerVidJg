package MVC.model;

import MVC.exceptions.ManaInsuficienteException;


/**
 * Clase Mago: Representa un personaje de tipo mágico especializado en elementos y curación.
 * Hereda de personajeMagico e implementa la interfaz Curacion.
 * @author Jonay Rivero
 */

public class Mago extends personajeMagico implements Curacion {
    private String elemento;
    private String escuela;
    private boolean tieneGrimorio;
    private Personaje objetivo;

    /**
     * Constructor de la clase Mago
     * @param nombre Nombre del mago
     * @param nivel Nivel del mago
     * @param saludMax Salud máxima del mago
     * @param mana Mana del mago
     * @param poderMagico Poder mágico del mago
     * @param elemento Elemento del mago
     * @param escuela Escuela del mago
     * @param tieneGrimorio Si el mago tiene grimorio
     * @param objetivo Objetivo del mago
     */
    public Mago(String nombre, int nivel, int saludMax, int mana, int poderMagico, String elemento, String escuela,
            boolean tieneGrimorio, Personaje objetivo) {
        super(nombre, nivel, saludMax, mana, poderMagico);
        this.elemento = elemento;
        this.escuela = escuela;
        this.tieneGrimorio = tieneGrimorio;
        this.objetivo = objetivo;
    }

    // --- GETTERS Y SETTERS ---

    /**
     * Obtiene el objetivo del mago
     * @return Objetivo del mago
     */
    public Personaje getObjetivo() {
        return objetivo;
    }

    /**
     * Establece el objetivo del mago
     * @param objetivo Objetivo del mago
     */
    public void setObjetivo(Personaje objetivo) {
        this.objetivo = objetivo;
    }

    /**
     * Obtiene el elemento del mago
     * @return Elemento del mago
     */
    public String getElemento() {
        return elemento;
    }

    /**
     * Establece el elemento del mago
     * @param elemento Elemento del mago
     */
    public void setElemento(String elemento) {
        this.elemento = elemento;
    }

    /**
     * Obtiene la escuela del mago
     * @return Escuela del mago
     */
    public String getEscuela() {
        return escuela;
    }

    /**
     * Establece la escuela del mago
     * @param escuela Escuela del mago
     */
    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }

    /**
     * Verifica si el mago tiene grimorio
     * @return true si el mago tiene grimorio, false en caso contrario
     */
    public boolean isTieneGrimorio() {
        return tieneGrimorio;
    }

    /**
     * Establece si el mago tiene grimorio
     * @param tieneGrimorio true si el mago tiene grimorio, false en caso contrario
     */
    public void setTieneGrimorio(boolean tieneGrimorio) {
        this.tieneGrimorio = tieneGrimorio;
    }

    // --- LÓGICA DE COMBATE (Sobrescribe Personaje) ---

    /**
     * Ataca a un objetivo
     * @param objetivo Objetivo al que atacar
     */
    @Override
    public void atacar(Personaje objetivo) {
        int costeMana = 10;
        // Intentamos gastar maná para un ataque mágico
        if (gastarMana(costeMana)) {
            // Calcula el daño base
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

    /**
     * Usa la habilidad especial del mago
     * @throws ManaInsuficienteException Si no tiene maná suficiente
     */
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

    /**
     * Cura a un objetivo
     * @param objetivo Objetivo al que curar
     * @return Cantidad de salud curada
     */
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

    /**
     * Se cura automáticamente un poco
     * @return Cantidad de salud curada
     */
    @Override
    public int autocurar() {
        int costeMana = 10;
        if (gastarMana(costeMana)) {
            int saludAnterior = getSalud();
            // El método setSalud de la clase padre ya valida no exceder el máximo
            setSalud(getSalud() + 20);
            // Calcula la cantidad de salud curada
            int curacionReal = getSalud() - saludAnterior;
            System.out.println(getNombre() + " utiliza magia vital sobre sí mismo. +" + curacionReal + " HP.");
            return curacionReal;
        }
        return 0;
    }

    // --- MÉTODOS DE ESTADO ---

    /**
     * Recibe daño
     * @param cantidad Cantidad de daño a recibir
     */
    @Override
    public void recibirDanio(int cantidad) {
        // Ejecutamos la lógica base de Personaje (restar vida)
        super.recibirDanio(cantidad);
        // Añadimos comportamiento extra (feedback por consola)
        System.out.println("[SISTEMA] " + getNombre() + " recibió daño. Salud restante: " + getSalud());
    }

    /**
     * Devuelve una representación en formato String del mago
     * @return Representación en formato String del mago
     */
    @Override
    public String toString() {
        return String.format("Mago [ID: %s | Nombre: %s | Salud: %d/%d | Mana: %d/%d | Elemento: %s]",
                getId(), getNombre(), getSalud(), getSaludMax(), getMana(), getManaMax(), elemento);
    }
}
