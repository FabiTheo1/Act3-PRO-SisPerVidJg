package MVC.model;

import MVC.exceptions.AtributoInvalidoException;
import MVC.exceptions.AccionInvalidaException;
import MVC.exceptions.SinMunicionException;

/**
 * Clase PersonajeFisico
 * 
 * @author Jeremi
 * @since 2026-03-01
 */
public abstract class personajeFisico extends Personaje {
    private int fuerza;
    private int defensa;

    /**
     * Constructor de la clase PersonajeFisico
     * 
     * @param nombre   Nombre del personaje
     * @param nivel    Nivel del personaje
     * @param saludMax Salud máxima del personaje
     * @param fuerza   Fuerza del personaje
     * @param defensa  Defensa del personaje
     */
    protected personajeFisico(String nombre, int nivel, int saludMax, int fuerza, int defensa) {
        super(nombre, nivel, saludMax);
        setFuerza(fuerza);
        setDefensa(defensa);
    }

    // --- GETTERS ---
    /**
     * Método para obtener la fuerza
     * 
     * @return Fuerza del personaje
     */
    public int getFuerza() {
        return fuerza;
    }

    /**
     * Método para obtener la defensa
     * 
     * @return Defensa del personaje
     */
    public int getDefensa() {
        return defensa;
    }

    // --- SETTERS ---
    /**
     * Método para establecer la fuerza
     * 
     * @param fuerza Fuerza del personaje
     */
    public void setFuerza(int fuerza) {
        if (fuerza < 0) {
            throw new AtributoInvalidoException("ERROR: La fuerza no puede ser negativa. Valor recibido: " + fuerza);
        }
        this.fuerza = fuerza;
    }

    /**
     * Método para establecer la defensa
     * 
     * @param defensa Defensa del personaje
     */
    public void setDefensa(int defensa) {
        if (defensa < 0) {
            throw new AtributoInvalidoException("ERROR: La defensa no puede ser negativa. Valor recibido: " + defensa);
        }
        this.defensa = defensa;
    }

    // --- METODOS ---
    /**
     * Método para calcular el daño
     * 
     * @return Daño del personaje
     */
    public int calcularDanio() {
        return getFuerza() * getNivel();
    }

    public class Asesino extends personajeFisico implements Sigiloso, Lanzar {
        // Atributos específicos de la clase Asesino
        private int puntosCombo;
        private double probCritico;
        private int cuchillosArrojadizos;

        /**
         * Constructor de la clase Asesino
         * 
         * @param nombre               Nombre del personaje
         * @param nivel                Nivel del personaje
         * @param saludMax             Salud máxima del personaje
         * @param fuerza               Fuerza del personaje
         * @param defensa              Defensa del personaje
         * @param probCritico          Probabilidad de crítico del personaje
         * @param cuchillosArrojadizos Cuchillos arrojadizos del personaje
         */
        public Asesino(String nombre, int nivel, int saludMax, int fuerza, int defensa, double probCritico,
                int cuchillosArrojadizos) {
            super(nombre, nivel, saludMax, fuerza, defensa);
            this.probCritico = probCritico;
            this.puntosCombo = 0;
            this.cuchillosArrojadizos = Math.max(0, cuchillosArrojadizos);
        }

        // --- METODOS ---

        /**
         * Método para validar el ataque
         * 
         * @param objetivo Personaje objetivo
         */
        private void validarAtaque(Personaje objetivo) {
            if (objetivo == null) {
                throw new AtributoInvalidoException("ERROR: El objetivo del ataque no puede ser nulo.");
            }
            if (this.equals(objetivo)) {
                throw new AccionInvalidaException("ERROR: " + getNombre() + " no puede atacarse a sí mismo.");
            }
            if (!this.estaVivo()) {
                throw new AccionInvalidaException(
                        "ERROR: " + getNombre() + " está muerto y no puede realizar acciones.");
            }
            if (!objetivo.estaVivo()) {
                throw new AccionInvalidaException(
                        "ERROR: No puedes apuntar a " + objetivo.getNombre() + " porque ya está muerto.");
            }
        }

        /**
         * Método propio: Habilidad específica del personaje
         */
        public void emboscada() {
            this.puntosCombo += 2;
            System.out.println(getNombre() + " se oculta en las sombras y prepara una emboscada. Puntos de combo: "
                    + this.puntosCombo);
        }

        @Override
        public void ocultarse() {
            System.out.println(getNombre() + " se desvanece en las sombras, volviéndose invisible temporalmente.");
        }

        /**
         * Método para atacar a un personaje
         * 
         * @param objetivo Personaje objetivo
         */
        @Override
        public void atacar(Personaje objetivo) {
            // 1. Validaciones estrictas de combate
            validarAtaque(objetivo);

            // 2. Lógica combate específica del Asesino
            int danioFinal = calcularDanio();

            // Lógica ataque crítico
            boolean esCritico = Math.random() <= this.probCritico;
            if (esCritico) {
                danioFinal *= 2;
                System.out.println("¡GOLPE CRÍTICO!");
            }

            // Daño extra si tiene puntos de combo
            if (this.puntosCombo > 0) {
                danioFinal += (this.puntosCombo * 5);
                // Gasta los puntos tras el ataque
                this.puntosCombo = 0;
            }

            System.out.println(
                    getNombre() + " apuñala rápidamente a " + objetivo.getNombre() + " infligiendo " + danioFinal
                            + " de daño.");
            objetivo.recibirDanio(danioFinal);
        }

        /**
         * Método para lanzar un objeto
         * 
         * @param objetivo Personaje objetivo
         */
        @Override
        public void lanzarObjeto(Personaje objetivo) {
            validarAtaque(objetivo);

            if (this.cuchillosArrojadizos <= 0) {
                throw new SinMunicionException(getNombre() + " se ha quedado sin cuchillos para lanzar.");
            }

            this.cuchillosArrojadizos--;
            // El asesino hace menos daño base lanzando, pero suma sus puntos de combo
            int danioLanzamiento = (getFuerza() / 2) + 5 + (this.puntosCombo * 2);
            this.puntosCombo = 0; // Gastamos el combo al lanzar

            System.out.println(getNombre() + " lanza un cuchillo envenenado a " + objetivo.getNombre() + " causando "
                    + danioLanzamiento + " de daño. (Cuchillos restantes: " + this.cuchillosArrojadizos + ")");
            objetivo.recibirDanio(danioLanzamiento);
        }

        // --- GETTERS ---
        /**
         * Método para obtener los puntos de combo
         * 
         * @return Puntos de combo
         */
        public int getPuntosCombo() {
            return puntosCombo;
        }

        /**
         * Método para obtener la probabilidad de crítico
         * 
         * @return Probabilidad de crítico
         */
        public double getProbCritico() {
            return probCritico;
        }

        /**
         * Método para obtener los cuchillos arrojadizos
         * 
         * @return Cuchillos arrojadizos
         */
        public int getCuchillosArrojadizos() {
            return cuchillosArrojadizos;
        }
    }

    public class Guerrero extends personajeFisico implements Defendible, Lanzar {
        // Atributos específicos de la clase Guerrero
        private int ira;
        private boolean posturaDefensiva;
        private int hachasArrojadizas;

        /**
         * Constructor de la clase Guerrero
         * 
         * @param nombre            Nombre del personaje
         * @param nivel             Nivel del personaje
         * @param saludMax          Salud máxima del personaje
         * @param fuerza            Fuerza del personaje
         * @param defensa           Defensa del personaje
         * @param ira               Ira del personaje
         * @param hachasArrojadizas Hachas arrojadizas del personaje
         */
        public Guerrero(String nombre, int nivel, int saludMax, int fuerza, int defensa, int ira,
                int hachasArrojadizas) {
            super(nombre, nivel, saludMax, fuerza, defensa);
            this.ira = ira;
            this.posturaDefensiva = false;
            this.hachasArrojadizas = Math.max(0, hachasArrojadizas);
        }

        // --- METODOS ---
        /**
         * Método para validar el ataque
         * 
         * @param objetivo Personaje objetivo
         */
        private void validarAtaque(Personaje objetivo) {
            if (objetivo == null) {
                throw new AtributoInvalidoException("ERROR: El objetivo del ataque no puede ser nulo.");
            }
            if (this.equals(objetivo)) {
                throw new AccionInvalidaException("ERROR: " + getNombre() + " no puede atacarse a sí mismo.");
            }
            if (!this.estaVivo()) {
                throw new AccionInvalidaException(
                        "ERROR: " + getNombre() + " está muerto y no puede realizar acciones.");
            }
            if (!objetivo.estaVivo()) {
                throw new AccionInvalidaException(
                        "ERROR: No puedes apuntar a " + objetivo.getNombre() + " porque ya está muerto.");
            }
        }

        /**
         * Método propio: Habilidad específica del personaje
         */
        public void gritoDeGuerra() {
            this.ira += 20;
            System.out.println(getNombre() + " lanza un grito de guerrero. ¡Su ira aumenta a " + this.ira + "!");
        }

        /**
         * Método para atacar a un personaje
         * 
         * @param objetivo Personaje objetivo
         */
        @Override
        public void atacar(Personaje objetivo) {
            // 1. Validaciones de combate
            validarAtaque(objetivo);

            // 2. Lógica ataque, si todo es válido
            int danioFinal = calcularDanio() + (this.ira / 5);
            System.out.println(getNombre() + " ataca brutalmente con su hacha a " + objetivo.getNombre()
                    + " causando " + danioFinal + " de daño.");
            objetivo.recibirDanio(danioFinal);
            this.posturaDefensiva = false;
        }

        /**
         * Método para defender
         */
        @Override
        public void defender() {
            this.posturaDefensiva = true;
            System.out.println(getNombre() + " levanta su escudo y entra en postura defensiva.");
        }

        /**
         * Método para lanzar un objeto
         * 
         * @param objetivo Personaje objetivo
         */
        @Override
        public void lanzarObjeto(Personaje objetivo) {
            validarAtaque(objetivo);

            if (this.hachasArrojadizas <= 0) {
                throw new SinMunicionException(getNombre() + " no tiene más hachas arrojadizas en su inventario.");
            }

            this.hachasArrojadizas--; // Gasta una hacha
            int danioLanzamiento = getFuerza() + 10; // Daño base del hacha
            System.out.println(getNombre() + " lanza un hacha voladora a " + objetivo.getNombre() + " causando "
                    + danioLanzamiento + " de daño. (Hachas restantes: " + this.hachasArrojadizas + ")");
            objetivo.recibirDanio(danioLanzamiento);
        }

        /**
         * Método para obtener la postura defensiva
         * 
         * @return true si está en postura defensiva, false en caso contrario
         */
        public boolean isPosturaDefensiva() {
            return posturaDefensiva;
        }

        // --- GETTERS ---
        /**
         * Método para obtener la ira
         * 
         * @return Ira del personaje
         */
        public int getIra() {
            return ira;
        }

        /**
         * Método para obtener las hachas arrojadizas
         * 
         * @return Hachas arrojadizas del personaje
         */
        public int getHachasArrojadizas() {
            return hachasArrojadizas;
        }

        // --- SETTERS ---
        /**
         * Método para establecer la ira
         * 
         * @param ira Ira del personaje
         */
        public void setIra(int ira) {
            this.ira = ira;
        }
    }

}
