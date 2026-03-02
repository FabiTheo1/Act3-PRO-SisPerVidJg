package MVC.model;

/**
 * CLASE ARMA - Creada por Fabian
 * Implementa el patrón de diseño "Composición". Un personaje "tiene un" arma.
 * Agrupa todas las armas específicas utilizando "Clases Internas Estáticas" (Static Inner Classes)
 * para mantener el proyecto limpio y no generar decenas de archivos pequeños.
 */
public abstract class Arma {
    private String nombre;
    private int danioBase;
    private int municionActual;
    private int capacidadMaxima;
    private boolean usaMunicion;

    // Constructor base de las armas
    public Arma(String nombre, int danioBase, int capacidadMaxima, boolean usaMunicion) {
        this.nombre = nombre;
        this.danioBase = danioBase;
        this.capacidadMaxima = capacidadMaxima;
        this.municionActual = capacidadMaxima; 
        this.usaMunicion = usaMunicion;
    }

    // Método que evalúa si el arma puede usarse basándose en su munición
    public boolean usar() {
        if (!usaMunicion) return true; // Armas con munición infinita
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

    // =========================================================
    // CLASES INTERNAS ESTÁTICAS (Tipos concretos de Armas)
    // =========================================================

    public static class Arco extends Arma {
        public Arco() { super("Arco Largo", 15, 20, true); }
    }

    public static class Ballesta extends Arma {
        public Ballesta() { super("Ballesta Pesada", 25, 5, true); }
    }

    public static class Jabalina extends Arma {
        public Jabalina() { super("Jabalina de Acero", 35, 1, true); }
    }

    public static class Honda extends Arma {
        public Honda() { super("Honda de Cuero", 8, 0, false); }
    }
}