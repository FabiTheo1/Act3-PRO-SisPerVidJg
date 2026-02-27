package MVC.model;

public class Mago extends personajeMagico implements Curacion {
   private String elemento;
   private String escuela;
   private boolean tieneGrimorio;
   private Personaje objetivo;

    public Mago(String nombre, int nivel, int saludMax, int mana, int poderMagico, String elemento, String escuela, boolean tieneGrimorio, Personaje objetivo) {
        super(nombre, nivel, saludMax, mana, poderMagico);
        this.elemento = elemento;
        this.escuela = escuela;
        this.tieneGrimorio = tieneGrimorio;
        this.objetivo = objetivo;
    }

    public Personaje getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Personaje objetivo) {
        this.objetivo = objetivo;
    }

    public String getElemento() {
        return elemento;   
    }
    
    public void setElemento(String elemento) {
        this.elemento = elemento;
    }
    
    public String getEscuela() {
        return escuela;
    }
    
    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }
    
    public boolean isTieneGrimorio() {
        return tieneGrimorio;
    }
    
    public void setTieneGrimorio(boolean tieneGrimorio) {
        this.tieneGrimorio = tieneGrimorio;
    }
    
    @Override
    public void atacar(Personaje objetivo) { 
        System.out.println(getNombre() + " lanza una bola de fuego de elemento " + elemento + " a " + objetivo.getNombre());
    }

    @Override
    public void usarHabilidadEspecial() {
        System.out.println("[SISTEMA] El mago ha usado su habilidad especial.");
    }

    @Override
    public void recibirDanio(int cantidad) {
        throw new UnsupportedOperationException("recibirDanio");
    }

    @Override
    public int curar(Personaje objetivo) {
        throw new UnsupportedOperationException("curar");
    }

    @Override
    public int autocurar() {
        throw new UnsupportedOperationException("autocurar");
    }

    @Override
    public boolean estaVivo() {
        throw new UnsupportedOperationException("estaVivo");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("toString");
    }
}
