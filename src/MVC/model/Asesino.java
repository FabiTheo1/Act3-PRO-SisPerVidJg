package MVC.model;

public class Asesino extends personajeFisico {
    private int puntosCombo;
    private double probCritico;

    public Asesino(String nombre, int nivel, int saludMax, int fuerza, int defensa, double probCritico) {
        super(nombre, nivel, saludMax, fuerza, defensa);
        this.probCritico = probCritico;
        this.puntosCombo = 0;
    }

    // Método propio: Habilidad específica
    public void emboscada() {
        this.puntosCombo += 2;
        System.out.println(getNombre() + " se oculta en las sombras y prepara una emboscada. Puntos de combo: "
                + this.puntosCombo);
    }

    // Implementación obligatoria
    @Override
    public void atacar(Personaje objetivo) {
        int danioFinal = calcularDanio();

        // Lógica de ataque crítico
        boolean esCritico = Math.random() <= this.probCritico;
        if (esCritico) {
            danioFinal *= 2;
            System.out.println("¡GOLPE CRÍTICO!");
        }

        // Daño extra si tiene puntos de combo
        if (this.puntosCombo > 0) {
            danioFinal += (this.puntosCombo * 5);
            this.puntosCombo = 0; // Gasta los puntos
        }

        System.out.println(getNombre() + " apuñala rápidamente a " + objetivo.getNombre() + " infligiendo "
                + danioFinal + " de daño.");
        objetivo.recibirDanio(danioFinal);
    }

    // --- GETTERS ---
    public int getPuntosCombo() {
        return puntosCombo;
    }

    public double getProbCritico() {
        return probCritico;
    }
}
