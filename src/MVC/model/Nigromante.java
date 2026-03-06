package MVC.model;

import MVC.exceptions.ManaInsuficienteException;

//TODOS: Hay cambios implementados por Fabian (echar culpa)

/**
 * Clase Nigromante: Un tipo de personaje mágico que utiliza almas y sigilo.
 * Implementa la interfaz Sigiloso para habilidades de ocultamiento.
 */
public class Nigromante extends PersonajeMagico implements Sigiloso {
    private boolean tieneInvocacion;
    private int almasCosechadas;
    private String tipoInvocacion;
    private Arma.Grimorio grimorioOscuro; // Composición

    public Nigromante(String nombre, int nivel, int saludMax, int mana, int poderMagico, int inteligencia, boolean tieneInvocacion, int almasCosechadas, String tipoInvocacion) {
        super(nombre, nivel, saludMax, mana, poderMagico);
        this.tieneInvocacion = tieneInvocacion;
        this.almasCosechadas = almasCosechadas;
        this.tipoInvocacion = tipoInvocacion;
        this.grimorioOscuro = new Arma.Grimorio(); // Equipado
        setPoderMagico(getPoderMagico() + inteligencia);
    }

    /**
     * Establece si el nigromante tiene invocación
     * 
     * @param tieneInvocacion true si el nigromante tiene invocación, false en caso
     *                        contrario
     */
    public void setTieneInvocacion(boolean tieneInvocacion) {
        this.tieneInvocacion = tieneInvocacion;
    }

    /**
     * Obtiene las almas cosechadas por el nigromante
     * 
     * @return Almas cosechadas por el nigromante
     */
    public int getAlmasCosechadas() {
        return almasCosechadas;
    }

    /**
     * Establece las almas cosechadas por el nigromante
     * 
     * @param almasCosechadas Almas cosechadas por el nigromante
     */
    public void setAlmasCosechadas(int almasCosechadas) {
        this.almasCosechadas = almasCosechadas;
    }

    /**
     * Obtiene el tipo de invocación del nigromante
     * 
     * @return Tipo de invocación del nigromante
     */
    public String getTipoInvocacion() {
        return tipoInvocacion;
    }

    /**
     * Establece el tipo de invocación del nigromante
     * 
     * @param tipoInvocacion Tipo de invocación del nigromante
     */
    public void setTipoInvocacion(String tipoInvocacion) {
        this.tipoInvocacion = tipoInvocacion;
    }

    // --- LÓGICA DE COMBATE (Sobrescribe Personaje/personajeMagico) ---

    /**
     * Ataca a un objetivo
     * 
     * @param objetivo Objetivo al que atacar
     */
    @Override
    public void atacar(Personaje objetivo) {
        int danioDrenaje = 8 + (getPoderMagico() / 3) + grimorioOscuro.getDanioBase();
        System.out.println(getNombre() + " lee su " + grimorioOscuro.getNombre() + " y drena la vida de " + objetivo.getNombre() + ".");
        objetivo.recibirDanio(danioDrenaje);
        this.almasCosechadas++;
    }

    /**
     * Usa la habilidad especial del nigromante
     * 
     * @throws ManaInsuficienteException Si no tiene maná suficiente
     */
    @Override
    public void usarHabilidadEspecial() {
        try {
            // Habilidad: Explosión de Almas (consume maná y depende de las almas
            // cosechadas)
            consumirMana(30);
            int danioAlmas = almasCosechadas * 5;
            System.out.println(
                    getNombre() + " libera las almas cosechadas causando " + danioAlmas + " de daño arcano.");
            // Reseteamos las almas tras el uso de la habilidad
            this.almasCosechadas = 0;
        } catch (ManaInsuficienteException e) {
            System.out.println("Error de magia: " + e.getMessage());
        }
        
    }

    // --- LÓGICA DE INVOCACIÓN (Propios del Nigromante) ---

    /**
     * Intenta invocar una criatura consumiendo maná.
     * 
     * @throws ManaInsuficienteException si no tiene maná suficiente.
     */
    public void invocar() throws ManaInsuficienteException {
        consumirMana(40);
        this.tieneInvocacion = true;
        System.out.println("¡Oscuridad! Un guerrero " + tipoInvocacion + " surge de la tierra bajo el mando de "
                + getNombre());
    }

    // --- IMPLEMENTACIÓN DE INTERFAZ (Sigiloso) ---

    /**
     * El nigromante se esconde usando su habilidad de sigilo
     */
    @Override
    public void esconderse() {
        System.out.println(getNombre() + " se funde con las sombras y desaparece en una nube de ceniza negra.");
    }

    // --- MÉTODOS DE ESTADO Y REPRESENTACIÓN ---

    /**
     * Devuelve una representación en formato String del nigromante
     * 
     * @return Representación en formato String del nigromante
     */
    @Override
    public String toString() {
        return String.format("Nigromante [Nombre: %s | Almas: %d | Invocación: %s | Salud: %d/%d]",
                getNombre(), almasCosechadas, (tieneInvocacion ? tipoInvocacion : "Ninguna"), getSalud(),
                getSaludMax());
    }

}
