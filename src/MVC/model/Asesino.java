package MVC.model;

import MVC.exceptions.AccionInvalidaException;
import MVC.exceptions.AtributoInvalidoException;
import MVC.exceptions.SinMunicionException;

public class Asesino extends PersonajeFisico implements Sigiloso, Lanzar {
    private int puntosCombo;
    private double probCritico;
    private Arma.CuchilloArrojadizo armaArrojadiza;
    private boolean evasionPerfecta; // NUEVO: Para evitar el daño

    public Asesino(String nombre, int nivel, int saludMax, int fuerza, int defensa, double probCritico) {
        super(nombre, nivel, saludMax, fuerza, defensa);
        this.probCritico = probCritico;
        this.puntosCombo = 0;
        this.evasionPerfecta = false;
        this.armaArrojadiza = new Arma.CuchilloArrojadizo();
    }

    private void validarAtaque(Personaje objetivo) {
        if (objetivo == null) throw new AtributoInvalidoException("El objetivo no puede ser nulo.");
        if (this.equals(objetivo)) throw new AccionInvalidaException(getNombre() + " no puede atacarse a sí mismo.");
        if (!this.estaVivo()) throw new AccionInvalidaException(getNombre() + " está muerto.");
        if (!objetivo.estaVivo()) throw new AccionInvalidaException(objetivo.getNombre() + " ya está muerto.");
    }

    public void emboscada() {
        this.puntosCombo += 2;
        this.evasionPerfecta = true; // Activa la inmunidad para el próximo turno
        System.out.println("=3 " + getNombre() + " se oculta preparando una emboscada. ¡Es inmune al próximo ataque! Puntos combo: " + this.puntosCombo);
    }

    // NUEVO: Sobrescribimos recibirDanio para aplicar la evasión
    @Override
    public void recibirDanio(int cantidad) {
        if (evasionPerfecta) {
            System.out.println("🛡️ ¡" + getNombre() + " esquiva el golpe ágilmente desde las sombras y no recibe daño!");
            evasionPerfecta = false; // Se gasta la evasión
        } else {
            super.recibirDanio(cantidad);
        }
    }

    @Override
    public void esconderse() {
        System.out.println(getNombre() + " se desvanece en las sombras.");
    }

    @Override
    public void atacar(Personaje objetivo) {
        validarAtaque(objetivo);
        int danioFinal = calcularDanio();

        if (Math.random() <= this.probCritico) {
            danioFinal *= 2;
            System.out.println("¡GOLPE CRÍTICO!");
        }

        if (this.puntosCombo > 0) {
            danioFinal += (this.puntosCombo * 5);
            this.puntosCombo = 0; // Gasta puntos
        }

        System.out.println(getNombre() + " apuñala rápidamente a " + objetivo.getNombre() + " infligiendo " + danioFinal + " de daño.");
        objetivo.recibirDanio(danioFinal);
    }

    // NUEVO: Método para recargar cuchillos
    public void recargarArma() {
        armaArrojadiza.recargar(5);
        System.out.println(getNombre() + " saca más cuchillos de su cinto. (Cuchillos: " + armaArrojadiza.getMunicionActual() + ")");
    }

    @Override
    public void lanzarObjeto(Personaje objetivo) {
        validarAtaque(objetivo);

        // NUEVO: Usamos la lógica de nuestro sistema Arma + la Excepción de Jeremi
        if (!armaArrojadiza.usar()) {
            throw new SinMunicionException(getNombre() + " se ha quedado sin cuchillos para lanzar.");
        }

        int danioLanzamiento = (getFuerza() / 2) + armaArrojadiza.getDanioBase() + (this.puntosCombo * 2);
        this.puntosCombo = 0; 

        System.out.println(getNombre() + " lanza su " + armaArrojadiza.getNombre() + " a " + objetivo.getNombre() + 
                           " causando " + danioLanzamiento + " de daño. (Munición: " + armaArrojadiza.getMunicionActual() + ")");
        objetivo.recibirDanio(danioLanzamiento);
    }
}