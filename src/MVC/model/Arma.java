package MVC.model;

/**
 * (Comentario estilo JavaDoc prueba)
 * CLASE ARMA - Creada por Persona 4
 * Esta clase permite que el daño y la munición no dependan del personaje, 
 * sino del objeto que sostiene.
 */
public abstract class Arma {
    private String nombre;
    private int danioBase;
    private int municionActual;
    private int capacidadMaxima;
    private boolean usaMunicion;

    public Arma(String nombre, int danioBase, int capacidadMaxima, boolean usaMunicion) {
        this.nombre = nombre;
        this.danioBase = danioBase;
        this.capacidadMaxima = capacidadMaxima;
        this.municionActual = capacidadMaxima; // Inicia cargada
        this.usaMunicion = usaMunicion;
    }

    // Método para disparar/lanzar
    public boolean usar() {
        if (!usaMunicion) return true; // Si es algo como una honda infinita o magia
        if (municionActual > 0) {
            municionActual--;
            return true;
        }
        return false;
    }

    public void recargar(int cantidad) {
        this.municionActual = Math.min(capacidadMaxima, municionActual + cantidad);
    }

    // Getters
    public String getNombre() { return nombre; }
    public int getDanioBase() { return danioBase; }
    public int getMunicionActual() { return municionActual; }
    public boolean isUsaMunicion() { return usaMunicion; }

    @Override
    public String toString() {
        return nombre + " (Daño: " + danioBase + " | Mun: " + (usaMunicion ? municionActual : "∞") + ")";
    }

    public static class Arco extends Arma {
        public Arco() { 
            super("Arco Largo", 15, 20, true); 
        }
    }

    public static class Ballesta extends Arma {
        public Ballesta() { 
            super("Ballesta Pesada", 25, 5, true); 
        }
    }

    public static class Jabalina extends Arma {
        public Jabalina() { 
            super("Jabalina de Acero", 35, 1, true); 
        }
    }

    public static class Honda extends Arma {
        public Honda() { 
            // La honda no gasta munición (false), balas infinitas
            super("Honda de Cuero", 8, 0, false); 
        }
    }
}