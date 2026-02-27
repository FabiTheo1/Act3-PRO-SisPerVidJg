package MVC.model;

import MVC.exceptions.ManaInsuficienteException;

/**
 * Clase Nigromante: Un tipo de personaje mágico que utiliza almas y sigilo.
 * Implementa la interfaz Sigiloso para habilidades de ocultamiento.
 */
public class Nigromante extends personajeMagico implements Sigiloso {
    private boolean tieneInvocacion;
    private int almasCosechadas;
    private String tipoInvocacion;

    public Nigromante(String nombre, int nivel, int saludMax, int mana, int poderMagico, int inteligencia,
            boolean tieneInvocacion, int almasCosechadas, String tipoInvocacion) {
        super(nombre, nivel, saludMax, mana, poderMagico);
        this.tieneInvocacion = tieneInvocacion;
        this.almasCosechadas = almasCosechadas;
        this.tipoInvocacion = tipoInvocacion;

        // El poder mágico del nigromante se potencia con su inteligencia inicial
        setPoderMagico(getPoderMagico() + inteligencia);
    }

    public void setTieneInvocacion(boolean tieneInvocacion) {
        this.tieneInvocacion = tieneInvocacion;
    }

    public int getAlmasCosechadas() {
        return almasCosechadas;
    }

    public void setAlmasCosechadas(int almasCosechadas) {
        this.almasCosechadas = almasCosechadas;
    }

    public String getTipoInvocacion() {
        return tipoInvocacion;
    }

    public void setTipoInvocacion(String tipoInvocacion) {
        this.tipoInvocacion = tipoInvocacion;
    }

    // --- LÓGICA DE COMBATE (Sobrescribe Personaje/personajeMagico) ---

    @Override
    public void atacar(Personaje objetivo) {
        // Un ataque básico de drenaje de vida
        int danioDrenaje = 8 + (getPoderMagico() / 3);
        System.out.println(getNombre() + " drena la vida de " + objetivo.getNombre() + ".");

        objetivo.recibirDanio(danioDrenaje);

        // Lógica de cosecha: cada ataque exitoso aumenta su contador de almas
        this.almasCosechadas++;
        System.out.println("[COSECHA] Almas totales: " + almasCosechadas);
    }

    @Override
    public void usarHabilidadEspecial() {
        try {
            // Habilidad: Explosión de Almas (consume maná y depende de las almas
            // cosechadas)
            consumirMana(30);
            int danioAlmas = almasCosechadas * 5;
            System.out.println(getNombre() + " libera las almas cosechadas causando " + danioAlmas + " de daño arcano.");
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
        System.out.println("¡Oscuridad! Un guerrero " + tipoInvocacion + " surge de la tierra bajo el mando de " + getNombre());
    }

    // --- IMPLEMENTACIÓN DE INTERFAZ (Sigiloso) ---

    @Override
    public void esconderse() {
        System.out.println(getNombre() + " se funde con las sombras y desaparece en una nube de ceniza negra.");
    }

    // --- MÉTODOS DE ESTADO Y REPRESENTACIÓN ---

    @Override
    public String toString() {
        return String.format("Nigromante [Nombre: %s | Almas: %d | Invocación: %s | Salud: %d/%d]",
                getNombre(), almasCosechadas, (tieneInvocacion ? tipoInvocacion : "Ninguna"), getSalud(),
                getSaludMax());
    }

}