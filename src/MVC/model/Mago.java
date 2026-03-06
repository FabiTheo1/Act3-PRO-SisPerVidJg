package MVC.model;

import MVC.exceptions.ManaInsuficienteException;

//TODOS: Hay cambios implementados por Fabian (echar culpa)

/**
 * Clase Mago: Representa un personaje de tipo mágico especializado en elementos
 * y curación.
 * Hereda de personajeMagico e implementa la interfaz Curacion.
 */

public class Mago extends PersonajeMagico implements Curacion {
    private String elemento;
    private String escuela;
    private Arma armaEquipada; // Grimorio o Bastón
    private Personaje objetivo;

    public Mago(String nombre, int nivel, int saludMax, int mana, int poderMagico, String elemento, String escuela, boolean tieneGrimorio, Personaje objetivo) {
        super(nombre, nivel, saludMax, mana, poderMagico);
        this.elemento = elemento;
        this.escuela = escuela;
        this.objetivo = objetivo;
        // Asignación inteligente de arma
        this.armaEquipada = tieneGrimorio ? new Arma.Grimorio() : new Arma.BastonMagico();
    }

    // --- GETTERS Y SETTERS ---

    /**
     * Obtiene el objetivo del mago
     * 
     * @return Objetivo del mago
     */
    public Personaje getObjetivo() {
        return objetivo;
    }

    /**
     * Establece el objetivo del mago
     * 
     * @param objetivo Objetivo del mago
     */
    public void setObjetivo(Personaje objetivo) {
        this.objetivo = objetivo;
    }

    /**
     * Obtiene el elemento del mago
     * 
     * @return Elemento del mago
     */
    public String getElemento() {
        return elemento;
    }

    /**
     * Establece el elemento del mago
     * 
     * @param elemento Elemento del mago
     */
    public void setElemento(String elemento) {
        this.elemento = elemento;
    }

    /**
     * Obtiene la escuela del mago
     * 
     * @return Escuela del mago
     */
    public String getEscuela() {
        return escuela;
    }

    /**
     * Establece la escuela del mago
     * 
     * @param escuela Escuela del mago
     */
    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }

    // --- LÓGICA DE COMBATE (Sobrescribe Personaje) ---

    /**
     * Ataca a un objetivo
     * 
     * @param objetivo Objetivo al que atacar
     */
    @Override
    public void atacar(Personaje objetivo) {
        int costeMana = 10;
        if (gastarMana(costeMana)) {
            int danioBase = 10 + getPoderMagico();
            // Si tiene Grimorio equipado, suma su daño al hechizo
            if (armaEquipada instanceof Arma.Grimorio) {
                danioBase += armaEquipada.getDanioBase();
            }
            System.out.println(getNombre() + " lanza un hechizo de " + elemento + " a " + objetivo.getNombre());
            objetivo.recibirDanio(danioBase);
        } else {
            // Si no hay maná, golpea físicamente con su arma
            int danioFisico = 2 + armaEquipada.getDanioBase();
            System.out.println(getNombre() + " no tiene maná suficiente. Golpea con su " + armaEquipada.getNombre() + ".");
            objetivo.recibirDanio(danioFisico);
        }
    }

    /**
     * Usa la habilidad especial del mago
     * 
     * @throws ManaInsuficienteException Si no tiene maná suficiente
     */
    @Override
    public void usarHabilidadEspecial() {
        try {
            consumirMana(40);
            int danioMasivo = (getPoderMagico() * 2) + 20;
            System.out.println(" ¡" + getNombre() + " canaliza la energía de la escuela de " + getEscuela() + " y lanza un hechizo devastador!");
            if (getObjetivo() != null) {
                getObjetivo().recibirDanio(danioMasivo);
            } else {
                System.out.println("Pero no hay objetivo a la vista...");
            }
        } catch (ManaInsuficienteException e) {
            System.out.println("Error de lanzamiento: " + e.getMessage());
        }
    }

    // --- LÓGICA DE CURACIÓN (Interfaz Curacion) ---

    /**
     * Cura a un objetivo
     * 
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
            System.out
                    .println(getNombre() + " ha curado a " + objetivo.getNombre() + " +" + cantidadCurada + " HP.");
            return cantidadCurada;
        }
        System.out.println(getNombre() + " no tiene maná para realizar la curación.");
        return 0;
    }

    /**
     * Se cura automáticamente un poco
     * 
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
     * 
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
     * 
     * @return Representación en formato String del mago
     */
    @Override
    public String toString() {
        return String.format("Mago [ID: %s | Nombre: %s | Salud: %d/%d | Mana: %d/%d | Elemento: %s]",
                getId(), getNombre(), getSalud(), getSaludMax(), getMana(), getManaMax(), elemento);
    }
}
