package MVC.model;

import MVC.exceptions.ManaInsuficienteException;

//TODOS: Hay cambios implementados por Fabian (echar culpa)

/**
 * Clase Druida: Personaje mágico que utiliza el poder de la naturaleza.
 * Puede cambiar de forma y realizar curaciones constantes.
 */
public class Druida extends PersonajeMagico implements Curacion, Sigiloso {
    private String formaActual;
    private boolean enArmonia;
    private int nivelNaturaleza;
    private Arma.BastonMagico baston; // Composición
    private String formaAnimalElegida; // NUEVO

    public Druida(String nombre, int nivel, int saludMax, int mana, int poderMagico, String formaAnimalElegida, int nivelNaturaleza) {
        super(nombre, nivel, saludMax, mana, poderMagico);
        this.formaActual = "Humano"; // Siempre nace humano
        this.formaAnimalElegida = formaAnimalElegida;
        this.nivelNaturaleza = nivelNaturaleza;
        this.enArmonia = true;
        this.baston = new Arma.BastonMagico();
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
        // Si está transformado, pega muchísimo más duro
        if (formaActual.equals(formaAnimalElegida)) {
            int danioFisico = 25 + (getNivelNaturaleza() * 4);
            System.out.println(getNombre() + " en forma de " + formaActual + " embiste salvajemente a " + objetivo.getNombre());
            objetivo.recibirDanio(danioFisico);
        } else {
            int costeMana = 8;
            if (gastarMana(costeMana)) {
                int danioMagico = 12 + getPoderMagico();
                System.out.println(getNombre() + " lanza Raíces Enredantes a " + objetivo.getNombre());
                objetivo.recibirDanio(danioMagico);
            } else {
                // Golpe físico con el bastón en lugar de golpe débil genérico
                int danioFisico = 4 + baston.getDanioBase();
                System.out.println(getNombre() + " se queda sin maná y golpea con su " + baston.getNombre() + ".");
                objetivo.recibirDanio(danioFisico);
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
            consumirMana(30);
            this.cambiarForma(formaAnimalElegida);
            // Bufos de vida
            setSaludMax(getSaludMax() + 60);
            setSalud(getSalud() + 60);
            System.out.println("🐻 ¡" + getNombre() + " se ha transformado en un(a) " + formaAnimalElegida + " gigante! Su salud y fuerza se disparan.");
        } catch (ManaInsuficienteException e) {
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
