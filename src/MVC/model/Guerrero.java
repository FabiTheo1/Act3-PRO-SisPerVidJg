package MVC.model;

import MVC.exceptions.AccionInvalidaException;
import MVC.exceptions.AtributoInvalidoException;
import MVC.exceptions.SinMunicionException;

public class Guerrero extends PersonajeFisico implements Defendible, Lanzar {
    private int ira;
    private boolean posturaDefensiva;
    
    // NUEVO: Composición con sistema Arma
    private Arma.HachaBatalla hachaBatalla;
    private Arma.HachaArrojadiza hachasArrojadizas;

    public Guerrero(String nombre, int nivel, int saludMax, int fuerza, int defensa, int ira) {
        super(nombre, nivel, saludMax, fuerza, defensa);
        this.ira = ira;
        this.posturaDefensiva = false;
        // Instanciamos sus armas por defecto
        this.hachaBatalla = new Arma.HachaBatalla();
        this.hachasArrojadizas = new Arma.HachaArrojadiza();
    }

    private void validarAtaque(Personaje objetivo) {
        if (objetivo == null) throw new AtributoInvalidoException("ERROR: El objetivo no puede ser nulo.");
        if (this.equals(objetivo)) throw new AccionInvalidaException("ERROR: " + getNombre() + " no puede atacarse a sí mismo.");
        if (!this.estaVivo() || !objetivo.estaVivo()) throw new AccionInvalidaException("ERROR: Acción inválida con entidades muertas.");
    }

    public void gritoDeGuerra() {
        this.ira += 20;
        System.out.println(getNombre() + " lanza un grito de guerrero. ¡Su ira aumenta a " + this.ira + "!");
    }

    @Override
    public void atacar(Personaje objetivo) {
        validarAtaque(objetivo);
        // Sumamos el daño del Hacha de Batalla
        int danioFinal = calcularDanio() + hachaBatalla.getDanioBase() + (this.ira / 5);
        System.out.println(getNombre() + " ataca brutalmente con su " + hachaBatalla.getNombre() + " a " + objetivo.getNombre() + " causando " + danioFinal + " de daño.");
        objetivo.recibirDanio(danioFinal);
        this.posturaDefensiva = false;
    }

    @Override
    public void defender() {
        this.posturaDefensiva = true;
        System.out.println(getNombre() + " levanta su escudo y entra en postura defensiva.");
    }

    @Override
    public void lanzarObjeto(Personaje objetivo) {
        validarAtaque(objetivo);
        // Validamos usando Arma y la excepción de Jeremi
        if (!hachasArrojadizas.usar()) {
            throw new SinMunicionException(getNombre() + " no tiene más hachas arrojadizas en su inventario.");
        }
        int danioLanzamiento = getFuerza() + hachasArrojadizas.getDanioBase();
        System.out.println(getNombre() + " lanza una " + hachasArrojadizas.getNombre() + " a " + objetivo.getNombre() + " causando " + danioLanzamiento + " de daño. (Hachas restantes: " + hachasArrojadizas.getMunicionActual() + ")");
        objetivo.recibirDanio(danioLanzamiento);
    }

    public boolean isPosturaDefensiva() { return posturaDefensiva; }
    public int getIra() { return ira; }
    public void setIra(int ira) { this.ira = ira; }
}