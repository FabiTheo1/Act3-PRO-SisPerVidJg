package MVC.model;

import MVC.exceptions.ManaInsuficienteException;

/**
 * Clase Druida: Personaje mágico que utiliza el poder de la naturaleza.
 * Puede cambiar de forma y realizar curaciones constantes.
 */
public class Druida extends personajeMagico implements Curacion, Sigiloso {

    private String formaActual; // Ejemplo: "Humano", "Oso", "Lince"
    private boolean enArmonia; // Bonus pasivo de regeneración
    private int nivelNaturaleza;

    /**
     * Constructor de la clase Druida
     * 
     * @param nombre          Nombre del druida
     * @param nivel           Nivel del druida
     * @param saludMax        Salud máxima del druida
     * @param mana            Mana del druida
     * @param poderMagico     Poder mágico del druida
     * @param formaActual     Forma actual del druida
     * @param nivelNaturaleza Nivel de naturaleza del druida
     */
    public Druida(String nombre, int nivel, int saludMax, int mana, int poderMagico,
            String formaActual, int nivelNaturaleza) {
        super(nombre, nivel, saludMax, mana, poderMagico);
        this.formaActual = formaActual;
        this.nivelNaturaleza = nivelNaturaleza;
        this.enArmonia = true;
    }

    /**
     * Obtiene la forma actual del druida
     * 
     * @return Forma actual del druida
     */
    public String getFormaActual() {
        return formaActual;
    }

    /**
     * Establece la forma actual del druida
     * 
     * @param formaActual Nueva forma del druida
     */
    public void setFormaActual(String formaActual) {
        this.formaActual = formaActual;
    }

    /**
     * Obtiene el nivel de naturaleza del druida
     * 
     * @return Nivel de naturaleza del druida
     */
    public int getNivelNaturaleza() {
        return nivelNaturaleza;
    }

    /**
     * Establece el nivel de naturaleza del druida
     * 
     * @param nivelNaturaleza Nuevo nivel de naturaleza del druida
     */
    public void setNivelNaturaleza(int nivelNaturaleza) {
        this.nivelNaturaleza = nivelNaturaleza;
    }

    // --- LÓGICA DE COMBATE (Sobrescribe Personaje/personajeMagico) ---

    /**
     * Ataca a un objetivo
     * 
     * @param objetivo Objetivo al que atacar
     */
    @Override
    public void atacar(Personaje objetivo) {
        // El daño depende de la forma actual
        if (formaActual.equalsIgnoreCase("Oso")) {
            int danioFisico = 15 + (nivelNaturaleza * 2);
            System.out.println(getNombre() + " en forma de Oso desgarra a " + objetivo.getNombre());
            objetivo.recibirDanio(danioFisico);
        } else {
            int costeMana = 8;
            // Intenta gastar maná para un ataque mágico
            if (gastarMana(costeMana)) {
                int danioMagico = 12 + getPoderMagico();
                System.out.println(getNombre() + " lanza Raíces Enredantes a " + objetivo.getNombre());
                // Aplica el daño al objetivo
                objetivo.recibirDanio(danioMagico);
            } else {
                // Si no tiene maná, realiza un ataque físico débil
                System.out.println(getNombre() + " golpea débilmente con su bastón.");
                objetivo.recibirDanio(4);
            }
        }
    }

    /**
     * Usa la habilidad especial del druida
     * 
     * @throws ManaInsuficienteException Si no tiene maná suficiente
     */
    @Override
    public void usarHabilidadEspecial() {
        try {
            // Consume maná para usar la habilidad especial
            consumirMana(45);
            this.formaActual = "Espíritu del Bosque";
            System.out.println(getNombre() + " trasciende a su forma de Espíritu. ¡Poder mágico aumentado!");
            // Aumenta el poder mágico del druida
            setPoderMagico(getPoderMagico() + 10);
        } catch (ManaInsuficienteException e) {
            // Si no tiene maná suficiente, muestra un mensaje de error
            System.out.println("La naturaleza no responde: " + e.getMessage());
        }
    }

    // --- IMPLEMENTACIÓN DE CURACION ---

    /**
     * Cura a un objetivo
     * 
     * @param objetivo Objetivo al que curar
     * @return Cantidad de salud curada
     */
    @Override
    public int curar(Personaje objetivo) {
        // Coste de maná para curar
        int costeMana = 12;
        if (gastarMana(costeMana)) {
            // El druida cura en base a su nivel de naturaleza
            int cura = 10 + (nivelNaturaleza * 3);
            // Aplica la curación al objetivo
            objetivo.recibirCuracion(cura);
            System.out.println(getNombre() + " usa Recrecimiento sobre " + objetivo.getNombre());
            return cura;
        }
        return 0;
    }

    /**
     * Se cura automáticamente un poco si está en armonía
     * 
     * @return Cantidad de salud curada
     */
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

    /**
     * Se transforma en pantera de las sombras para poder esconderse
     */
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

    /**
     * Cambia la forma del druida
     * 
     * @param nuevaForma Nueva forma del druida
     */
    public void cambiarForma(String nuevaForma) {
        System.out.println(getNombre() + " cambia su forma de " + formaActual + " a " + nuevaForma);
        this.formaActual = nuevaForma;
    }

    /**
     * Devuelve una representación en formato String del druida
     * 
     * @return Representación en formato String del druida
     */
    @Override
    public String toString() {
        return String.format("Druida [Nombre: %s | Forma: %s | Naturaleza: %d | Salud: %d/%d]",
                getNombre(), formaActual, nivelNaturaleza, getSalud(), getSaludMax());
    }

}
