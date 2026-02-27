package MVC.model;

import MVC.exceptions.ManaInsuficienteException;

public class Nigromante extends personajeMagico implements Sigiloso {
    private boolean tieneInvocacion;
    private int almasCosechadas;
    private String tipoInvocacion;

    public Nigromante(String nombre, int nivel, int saludMax, int mana, int poderMagico, int inteligencia,
            boolean tieneInvocacion, int almasCosechadas, String tipoInvocacion) {
        super(nombre, nivel, saludMax, mana, poderMagico);
        this.tieneInvocacion = tieneInvocacion;
        this.almasCosechadas = almasCosechadas;
        this.tipoInvocacion = tipoInvocacion;
        setPoderMagico(getPoderMagico() + inteligencia);
    }

    public void setTieneInvocacion(boolean tieneInvocacion) {
        this.tieneInvocacion = tieneInvocacion;
    }

    public int getAlmasCosechadas() {
        return almasCosechadas;
    }

    public void setAlmasCosechadas(int almasCosechadas) {
        this.almasCosechadas = almasCosechadas;
    }

    public String getTipoInvocacion() {
        return tipoInvocacion;
    }

    public void setTipoInvocacion(String tipoInvocacion) {
        this.tipoInvocacion = tipoInvocacion;
    }

    @Override
    public void atacar(Personaje objetivo) {
        System.out.println(getNombre() + " drena la vida del enemigo. Almas totales: " + (++almasCosechadas));
    }

    @Override
    public void usarHabilidadEspecial() {
        System.out.println("[SISTEMA] El nigromante ha usado su habilidad especial.");
    }

    @Override
    public void esconderse() {
        System.out.println(getNombre() + " desaparece en una nube de ceniza negra.");
    }

    public void invocar() throws ManaInsuficienteException {
        consumirMana(40);
        tieneInvocacion = true;
        System.out.println("Un guerrero " + tipoInvocacion + " surge de la tierra.");
    }

    public boolean isTieneInvocacion() {
        return tieneInvocacion;
    }

}