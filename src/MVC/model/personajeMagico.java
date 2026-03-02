package MVC.model;

import MVC.exceptions.ManaInsuficienteException;

/**
 * Clase personajeMagico: Clase abstracta que representa a un personaje mágico.
 * 
 * @author Jonay Rivero
 */
public abstract class personajeMagico extends Personaje {
    protected int mana;
    protected int manaMax;
    protected int poderMagico;

    /**
     * Constructor de la clase personajeMagico
     * 
     * @param nombre      Nombre del personaje mágico
     * @param nivel       Nivel del personaje mágico
     * @param saludMax    Salud máxima del personaje mágico
     * @param mana        Mana del personaje mágico
     * @param poderMagico Poder mágico del personaje mágico
     */
    protected personajeMagico(String nombre, int nivel, int saludMax, int mana, int poderMagico) {
        super(nombre, nivel, saludMax);
        this.manaMax = mana;
        this.mana = mana;
        this.poderMagico = poderMagico;
    }

    /**
     * Obtiene el maná del personaje mágico
     * 
     * @return Maná del personaje mágico
     */
    public int getMana() {
        return mana;
    }

    /**
     * Establece el maná del personaje mágico
     * 
     * @param mana Maná del personaje mágico
     */
    public void setMana(int mana) {
        if (mana >= 0 && mana <= this.manaMax) {
            this.mana = mana;
        } else if (mana < 0) {
            this.mana = 0; // Si el maná es menor que 0, se establece en 0
        } else {
            this.mana = this.manaMax; // Si el maná es mayor que la salud máxima, se establece en la salud máxima
        }
    }

    /**
     * Obtiene el maná máximo del personaje mágico
     * 
     * @return Maná máximo del personaje mágico
     */
    public int getManaMax() {
        return manaMax;
    }

    /**
     * Establece el maná máximo del personaje mágico
     * 
     * @param manaMax Maná máximo del personaje mágico
     */
    public void setManaMax(int manaMax) {
        if (manaMax > 0) {
            this.manaMax = manaMax;
        }
    }

    /**
     * Obtiene el poder mágico del personaje mágico
     * 
     * @return Poder mágico del personaje mágico
     */
    public int getPoderMagico() {
        return poderMagico;
    }

    /**
     * Establece el poder mágico del personaje mágico
     * 
     * @param poderMagico Poder mágico del personaje mágico
     */
    public void setPoderMagico(int poderMagico) {
        if (poderMagico >= 0) {
            this.poderMagico = poderMagico;
        }
    }

    /**
     * Gasta maná
     * 
     * @param cantidad Cantidad de maná a gastar
     * @return true si se gastó maná, false en caso contrario
     */
    public boolean gastarMana(int cantidad) {
        if (cantidad > 0 && this.mana >= cantidad) {
            setMana(getMana() - cantidad);
            return true;
        }
        return false;
    }

    /**
     * Regenera maná
     * 
     * @param cantidad Cantidad de maná a regenerar
     */
    public void regenerarMana(int cantidad) {
        if (cantidad > 0) {
            setMana(this.mana + cantidad); // Utiliza el método setMana para asegurar que el maná no exceda el máximo
        }
    }

    /**
     * Consume maná
     * 
     * @param cantidad Cantidad de maná a consumir
     * @throws ManaInsuficienteException Si no hay maná suficiente
     */
    protected void consumirMana(int cantidad) throws ManaInsuficienteException {
        if (this.mana < cantidad) {
            throw new ManaInsuficienteException(
                    getNombre() + " no tiene maná suficiente (" + mana + "/" + cantidad + ")"); // Lanza la excepción si
                                                                                                // no hay maná
                                                                                                // suficiente
        }
        this.mana -= cantidad;
    }

    /**
     * Usa la habilidad especial del personaje mágico
     */
    public abstract void usarHabilidadEspecial();

    /**
     * Ataca a un objetivo
     * 
     * @param objetivo Objetivo al que atacar
     */
    public abstract void atacar(Personaje objetivo);

    /* CREACION DE CLASES HIJAS */
    /**
     * Clase Mago: Representa un personaje de tipo mágico especializado en elementos
     * y curación.
     * Hereda de personajeMagico e implementa la interfaz Curacion.
     */

    public class Mago extends personajeMagico implements Curacion {
        private String elemento;
        private String escuela;
        private boolean tieneGrimorio;
        private Personaje objetivo;

        /**
         * Constructor de la clase Mago
         * 
         * @param nombre        Nombre del mago
         * @param nivel         Nivel del mago
         * @param saludMax      Salud máxima del mago
         * @param mana          Mana del mago
         * @param poderMagico   Poder mágico del mago
         * @param elemento      Elemento del mago
         * @param escuela       Escuela del mago
         * @param tieneGrimorio Si el mago tiene grimorio
         * @param objetivo      Objetivo del mago
         */
        public Mago(String nombre, int nivel, int saludMax, int mana, int poderMagico, String elemento, String escuela,
                boolean tieneGrimorio, Personaje objetivo) {
            super(nombre, nivel, saludMax, mana, poderMagico);
            this.elemento = elemento;
            this.escuela = escuela;
            this.tieneGrimorio = tieneGrimorio;
            this.objetivo = objetivo;
        }

        // --- GETTERS Y SETTERS ---

        /**
         * Obtiene el objetivo del mago
         * 
         * @return Objetivo del mago
         */
        public Personaje getObjetivo() {
            return objetivo;
        }

        /**
         * Establece el objetivo del mago
         * 
         * @param objetivo Objetivo del mago
         */
        public void setObjetivo(Personaje objetivo) {
            this.objetivo = objetivo;
        }

        /**
         * Obtiene el elemento del mago
         * 
         * @return Elemento del mago
         */
        public String getElemento() {
            return elemento;
        }

        /**
         * Establece el elemento del mago
         * 
         * @param elemento Elemento del mago
         */
        public void setElemento(String elemento) {
            this.elemento = elemento;
        }

        /**
         * Obtiene la escuela del mago
         * 
         * @return Escuela del mago
         */
        public String getEscuela() {
            return escuela;
        }

        /**
         * Establece la escuela del mago
         * 
         * @param escuela Escuela del mago
         */
        public void setEscuela(String escuela) {
            this.escuela = escuela;
        }

        /**
         * Verifica si el mago tiene grimorio
         * 
         * @return true si el mago tiene grimorio, false en caso contrario
         */
        public boolean isTieneGrimorio() {
            return tieneGrimorio;
        }

        /**
         * Establece si el mago tiene grimorio
         * 
         * @param tieneGrimorio true si el mago tiene grimorio, false en caso contrario
         */
        public void setTieneGrimorio(boolean tieneGrimorio) {
            this.tieneGrimorio = tieneGrimorio;
        }

        // --- LÓGICA DE COMBATE (Sobrescribe Personaje) ---

        /**
         * Ataca a un objetivo
         * 
         * @param objetivo Objetivo al que atacar
         */
        @Override
        public void atacar(Personaje objetivo) {
            int costeMana = 10;
            // Intentamos gastar maná para un ataque mágico
            if (gastarMana(costeMana)) {
                // Calcula el daño base
                int danioBase = 10 + getPoderMagico();

                // Bonus pasivo si el mago posee su libro de hechizos
                if (tieneGrimorio) {
                    danioBase += 5;
                }

                System.out.println(getNombre() + " lanza un hechizo de " + elemento + " a " + objetivo.getNombre());
                objetivo.recibirDanio(danioBase);
            } else {
                // Si no hay maná, realiza un ataque físico muy débil
                System.out.println(getNombre() + " no tiene maná suficiente. Realiza un ataque físico débil.");
                objetivo.recibirDanio(2);
            }
        }

        /**
         * Usa la habilidad especial del mago
         * 
         * @throws ManaInsuficienteException Si no tiene maná suficiente
         */
        @Override
        public void usarHabilidadEspecial() {
            try {
                // Habilidad de alto coste que requiere gestión de excepciones
                consumirMana(40);
                System.out.println(
                        getNombre() + " canaliza la energía de la escuela " + escuela + " para un ataque devastador.");
            } catch (ManaInsuficienteException e) {
                System.out.println("Error de lanzamiento: " + e.getMessage());
            }
        }

        // --- LÓGICA DE CURACIÓN (Interfaz Curacion) ---

        /**
         * Cura a un objetivo
         * 
         * @param objetivo Objetivo al que curar
         * @return Cantidad de salud curada
         */
        @Override
        public int curar(Personaje objetivo) {
            int costeMana = 15;
            if (gastarMana(costeMana)) {
                int cantidadCurada = 15 + (getPoderMagico() / 2);
                // Se asume que la clase Personaje tiene el método recibirCuracion
                objetivo.recibirCuracion(cantidadCurada);
                System.out
                        .println(getNombre() + " ha curado a " + objetivo.getNombre() + " +" + cantidadCurada + " HP.");
                return cantidadCurada;
            }
            System.out.println(getNombre() + " no tiene maná para realizar la curación.");
            return 0;
        }

        /**
         * Se cura automáticamente un poco
         * 
         * @return Cantidad de salud curada
         */
        @Override
        public int autocurar() {
            int costeMana = 10;
            if (gastarMana(costeMana)) {
                int saludAnterior = getSalud();
                // El método setSalud de la clase padre ya valida no exceder el máximo
                setSalud(getSalud() + 20);
                // Calcula la cantidad de salud curada
                int curacionReal = getSalud() - saludAnterior;
                System.out.println(getNombre() + " utiliza magia vital sobre sí mismo. +" + curacionReal + " HP.");
                return curacionReal;
            }
            return 0;
        }

        // --- MÉTODOS DE ESTADO ---

        /**
         * Recibe daño
         * 
         * @param cantidad Cantidad de daño a recibir
         */
        @Override
        public void recibirDanio(int cantidad) {
            // Ejecutamos la lógica base de Personaje (restar vida)
            super.recibirDanio(cantidad);
            // Añadimos comportamiento extra (feedback por consola)
            System.out.println("[SISTEMA] " + getNombre() + " recibió daño. Salud restante: " + getSalud());
        }

        /**
         * Devuelve una representación en formato String del mago
         * 
         * @return Representación en formato String del mago
         */
        @Override
        public String toString() {
            return String.format("Mago [ID: %s | Nombre: %s | Salud: %d/%d | Mana: %d/%d | Elemento: %s]",
                    getId(), getNombre(), getSalud(), getSaludMax(), getMana(), getManaMax(), elemento);
        }
    }

    /**
     * Clase Nigromante: Un tipo de personaje mágico que utiliza almas y sigilo.
     * Implementa la interfaz Sigiloso para habilidades de ocultamiento.
     */
    public class Nigromante extends personajeMagico implements Sigiloso {
        private boolean tieneInvocacion;
        private int almasCosechadas;
        private String tipoInvocacion;

        /**
         * Constructor de la clase Nigromante
         * 
         * @param nombre          Nombre del nigromante
         * @param nivel           Nivel del nigromante
         * @param saludMax        Salud máxima del nigromante
         * @param mana            Mana del nigromante
         * @param poderMagico     Poder mágico del nigromante
         * @param inteligencia    Inteligencia del nigromante
         * @param tieneInvocacion Si el nigromante tiene invocación
         * @param almasCosechadas Almas cosechadas por el nigromante
         * @param tipoInvocacion  Tipo de invocación del nigromante
         */
        public Nigromante(String nombre, int nivel, int saludMax, int mana, int poderMagico, int inteligencia,
                boolean tieneInvocacion, int almasCosechadas, String tipoInvocacion) {
            super(nombre, nivel, saludMax, mana, poderMagico);
            this.tieneInvocacion = tieneInvocacion;
            this.almasCosechadas = almasCosechadas;
            this.tipoInvocacion = tipoInvocacion;

            // El poder mágico del nigromante se potencia con su inteligencia inicial
            setPoderMagico(getPoderMagico() + inteligencia);
        }

        /**
         * Establece si el nigromante tiene invocación
         * 
         * @param tieneInvocacion true si el nigromante tiene invocación, false en caso
         *                        contrario
         */
        public void setTieneInvocacion(boolean tieneInvocacion) {
            this.tieneInvocacion = tieneInvocacion;
        }

        /**
         * Obtiene las almas cosechadas por el nigromante
         * 
         * @return Almas cosechadas por el nigromante
         */
        public int getAlmasCosechadas() {
            return almasCosechadas;
        }

        /**
         * Establece las almas cosechadas por el nigromante
         * 
         * @param almasCosechadas Almas cosechadas por el nigromante
         */
        public void setAlmasCosechadas(int almasCosechadas) {
            this.almasCosechadas = almasCosechadas;
        }

        /**
         * Obtiene el tipo de invocación del nigromante
         * 
         * @return Tipo de invocación del nigromante
         */
        public String getTipoInvocacion() {
            return tipoInvocacion;
        }

        /**
         * Establece el tipo de invocación del nigromante
         * 
         * @param tipoInvocacion Tipo de invocación del nigromante
         */
        public void setTipoInvocacion(String tipoInvocacion) {
            this.tipoInvocacion = tipoInvocacion;
        }

        // --- LÓGICA DE COMBATE (Sobrescribe Personaje/personajeMagico) ---

        /**
         * Ataca a un objetivo
         * 
         * @param objetivo Objetivo al que atacar
         */
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

        /**
         * Usa la habilidad especial del nigromante
         * 
         * @throws ManaInsuficienteException Si no tiene maná suficiente
         */
        @Override
        public void usarHabilidadEspecial() {
            try {
                // Habilidad: Explosión de Almas (consume maná y depende de las almas
                // cosechadas)
                consumirMana(30);
                int danioAlmas = almasCosechadas * 5;
                System.out.println(
                        getNombre() + " libera las almas cosechadas causando " + danioAlmas + " de daño arcano.");
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
            System.out.println("¡Oscuridad! Un guerrero " + tipoInvocacion + " surge de la tierra bajo el mando de "
                    + getNombre());
        }

        // --- IMPLEMENTACIÓN DE INTERFAZ (Sigiloso) ---

        /**
         * El nigromante se esconde usando su habilidad de sigilo
         */
        @Override
        public void esconderse() {
            System.out.println(getNombre() + " se funde con las sombras y desaparece en una nube de ceniza negra.");
        }

        // --- MÉTODOS DE ESTADO Y REPRESENTACIÓN ---

        /**
         * Devuelve una representación en formato String del nigromante
         * 
         * @return Representación en formato String del nigromante
         */
        @Override
        public String toString() {
            return String.format("Nigromante [Nombre: %s | Almas: %d | Invocación: %s | Salud: %d/%d]",
                    getNombre(), almasCosechadas, (tieneInvocacion ? tipoInvocacion : "Ninguna"), getSalud(),
                    getSaludMax());
        }

    }

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
}
