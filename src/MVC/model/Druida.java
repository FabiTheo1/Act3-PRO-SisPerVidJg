package MVC.model;

import MVC.exceptions.ManaInsuficienteException;

/**
 * Clase Druida: Personaje mágico que utiliza el poder de la naturaleza.
 * Puede cambiar de forma y realizar curaciones constantes.
 */
public class Druida extends personajeMagico implements Curacion, Sigiloso {

    // --- ATRIBUTOS ---
    private String formaActual; // Ejemplo: "Humano", "Oso", "Lince"
    private boolean enArmonia; // Bonus pasivo de regeneración
    private int nivelNaturaleza;

    /**
     * Constructor para el Druida.
     */
    public Druida(String nombre, int nivel, int saludMax, int mana, int poderMagico,
            String formaActual, int nivelNaturaleza) {
        super(nombre, nivel, saludMax, mana, poderMagico);
        this.formaActual = formaActual;
        this.nivelNaturaleza = nivelNaturaleza;
        this.enArmonia = true;
    }

    public String getFormaActual() {
        return formaActual;
    }

    public void setFormaActual(String formaActual) {
        this.formaActual = formaActual;
    }

    public int getNivelNaturaleza() {
        return nivelNaturaleza;
    }

    public void setNivelNaturaleza(int nivelNaturaleza) {
        this.nivelNaturaleza = nivelNaturaleza;
    }

    // --- LÓGICA DE COMBATE (Sobrescribe Personaje/personajeMagico) ---

    @Override
    public void atacar(Personaje objetivo) {
        // El daño depende de la forma actual
        if (formaActual.equalsIgnoreCase("Oso")) {
            int danioFisico = 15 + (nivelNaturaleza * 2);
            System.out.println(getNombre() + " en forma de Oso desgarra a " + objetivo.getNombre());
            objetivo.recibirDanio(danioFisico);
        } else {
            int costeMana = 8;
            if (gastarMana(costeMana)) {
                int danioMagico = 12 + getPoderMagico();
                System.out.println(getNombre() + " lanza Raíces Enredantes a " + objetivo.getNombre());
                objetivo.recibirDanio(danioMagico);
            } else {
                System.out.println(getNombre() + " golpea débilmente con su bastón.");
                objetivo.recibirDanio(4);
            }
        }
    }

    @Override
    public void usarHabilidadEspecial() {
        try {
            consumirMana(45);
            this.formaActual = "Espíritu del Bosque";
            System.out.println(getNombre() + " trasciende a su forma de Espíritu. ¡Poder mágico aumentado!");
            setPoderMagico(getPoderMagico() + 10);
        } catch (ManaInsuficienteException e) {
            System.out.println("La naturaleza no responde: " + e.getMessage());
        }
    }

    // --- IMPLEMENTACIÓN DE CURACION ---

    @Override
    public int curar(Personaje objetivo) {
        int costeMana = 12;
        if (gastarMana(costeMana)) {
            // El druida cura en base a su nivel de naturaleza
            int cura = 10 + (nivelNaturaleza * 3);
            objetivo.recibirCuracion(cura);
            System.out.println(getNombre() + " usa Recrecimiento sobre " + objetivo.getNombre());
            return cura;
        }
        return 0;
    }

    @Override
    public int autocurar() {
        // El druida se cura automáticamente un poco si está en armonía
        if (enArmonia) {
            int saludAntes = getSalud();
            setSalud(getSalud() + 10 + nivelNaturaleza);
            System.out.println(getNombre() + " se regenera gracias a la Armonía Natural.");
            return getSalud() - saludAntes;
        }
        return 0;
    }

    // --- IMPLEMENTACIÓN DE INTERFAZ (Sigiloso) ---

    @Override
    public void esconderse() {
        // Lógica de transformación automática al entrar en sigilo
        String formaPrevia = this.formaActual;
        this.formaActual = "Pantera de las Sombras";

        System.out.println(getNombre() + " se agazapa y cambia de " + formaPrevia + " a " + formaActual + ".");
        System.out.println("Las hojas del entorno lo cubren por completo.");

        // Llamada al método por defecto de la interfaz Sigiloso
        avisarSigilo();
    }

    // --- MÉTODOS DE ESTADO ---

    public void cambiarForma(String nuevaForma) {
        System.out.println(getNombre() + " cambia su forma de " + formaActual + " a " + nuevaForma);
        this.formaActual = nuevaForma;
    }

    @Override
    public String toString() {
        return String.format("Druida [Nombre: %s | Forma: %s | Naturaleza: %d | Salud: %d/%d]",
                getNombre(), formaActual, nivelNaturaleza, getSalud(), getSaludMax());
    }

}