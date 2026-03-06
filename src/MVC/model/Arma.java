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

    // Arma balanceada, estándar y versatil.
    public static class Arco extends Arma {
        public Arco() { super("Arco Largo", 15, 20, true); }
    }

    // Daño alto, poca capacidad de munición y recarga lento.
    public static class Ballesta extends Arma {
        public Ballesta() { super("Ballesta Pesada", 25, 5, true); }
    }

    // Mucho daño, pero solo tiene una jabalina por combate.
    public static class Jabalina extends Arma {
        public Jabalina() { super("Jabalina de Acero", 35, 1, true); }
    }

    // Munición infinita pero poco daño.
    public static class Honda extends Arma {
        public Honda() { super("Honda de Cuero", 8, 0, false); }
    }

    // --- NUEVAS ARMAS AÑADIDAS ---

    public static class Mosquete extends Arma {
        // Mucho daño, pero solo 1 bala antes de recargar.
        public Mosquete() { super("Mosquete Imperial", 50, 1, true); } 
    }

    // Arma secundaria por defecto de los combatientes a distancia.
    public static class Daga extends Arma {
        public Daga() { super("Daga de Combate", 10, 0, false); }
    }

    // Arma secundaria, rápida con poco daño, puede usarse con escudo.
    public static class EspadaCorta extends Arma {
        public EspadaCorta() { super("Espada Corta", 15, 0, false); }
    }

    public static class EscudoPequeno extends Arma {
        // Escudo que pueden utilizar los combatientes a distancia.
        public EscudoPequeno() { super("Escudo de Madera", 0, 0, false); }
    }

    // --- ARMAS PARA LAS NUEVAS CLASES (MERGE FINAL) ---

    public static class Lanza extends Arma {
        public Lanza() { super("Lanza de Caballería", 20, 0, false); }
    }

    public static class HachaBatalla extends Arma {
        public HachaBatalla() { super("Hacha de Batalla", 25, 0, false); }
    }

    public static class HachaArrojadiza extends Arma {
        public HachaArrojadiza() { super("Hacha Arrojadiza", 18, 5, true); } // 5 usos
    }

    public static class CuchilloArrojadizo extends Arma {
        public CuchilloArrojadizo() { super("Cuchillo Venenoso", 12, 10, true); } // 10 usos
    }

    public static class BastonMagico extends Arma {
        public BastonMagico() { super("Bastón de Roble", 8, 0, false); }
    }

    public static class Grimorio extends Arma {
        public Grimorio() { super("Libro de Hechizos Antiguo", 5, 0, false); }
    }

    public static class Mandoble extends Arma {
        // Daño devastador, pero no permite llevar escudo
        public Mandoble() { super("Mandoble Pesado a Dos Manos", 40, 0, false); }
    }

    public static class EspadaLarga extends Arma {
        public EspadaLarga() { super("Espada Larga de Acero", 18, 0, false); }
    }
}