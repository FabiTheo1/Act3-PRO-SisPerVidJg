package MVC.model;

import java.util.UUID;

public abstract class Personaje {
    private final String id; // Uso de final para que no se pueda cambiar el valor una vez se establezca
    private String nombre;
    private int nivel;
    private int salud;
    private int saludMax;

    protected Personaje(String nombre, int nivel, int saludMax) {
        this.id = UUID.randomUUID().toString(); // Uso de UUID para crear un identificador único del personaje
        this.nombre = nombre;
        this.nivel = nivel;
        this.salud = saludMax;
        this.saludMax = saludMax;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public int getSalud() {
        return salud;
    }

    public int getSaludMax() {
        return saludMax;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Rango de niveles
    public void setNivel(int nivel) {
        if (nivel >= 0 && nivel <= 100) {
            this.nivel = nivel;
        }
    }

    // Rango de salud
    public void setSalud(int salud) {
        if (salud >= 0 && salud <= this.saludMax) {
            this.salud = salud;
        } else if (salud < 0) {
            this.salud = 0; // Si la salud es menor que 0, se establece en 0
        } else {
            this.salud = this.saludMax; // Si la salud es mayor que la salud máxima, se establece en la salud máxima
        }
    }

    // Valor de la salud máxima, podría ser modificado por objetos curables o
    // mágicos
    // Los distintos personajes podrían tener diferentes valores de salud máxima,
    // dependiendo de su clase o nivel
    public void setSaludMax(int saludMax) {
        if (saludMax > 0) {
            this.saludMax = saludMax;
        }
    }

    // Métodos abstracto para atacar
    public abstract void atacar(Personaje objetivo);

    // Método para recibir daño
    public void recibirDanio(int cantidad) {
        if (cantidad > 0) {
            setSalud(getSalud() - cantidad); // Se utiliza el método setSalud para asegurarse de que la salud se
                                             // mantenga dentro de los límites establecidos
        }
    }

    // Método para recibir curación
    public void recibirCuracion(int cantidad) {
        if (cantidad > 0) {
            setSalud(getSalud() + cantidad);
            System.out.println(getNombre() + " se ha curado y ahora tiene " + getSalud() + " de salud.");
        }
    }

    // Método para verificar si el personaje está vivo
    public boolean estaVivo() {
        return this.salud > 0; // Un personaje está vivo si su salud es mayor que 0
    }

    @Override
    // Verifica si son el mismo objeto
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; // Verifica si son el mismo objeto
        }
        // Verifica si el objeto es nulo o de diferente clase
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Personaje personaje = (Personaje) obj; // Castea el objeto a Personaje
        return id.equals(personaje.id); // Compara los IDs para determinar igualdad
    }

    // El hashCode se basa en el ID único del personaje para garantizar que
    // personajes diferentes tengan hash codes diferentes
    @Override
    public int hashCode() {
        return id.hashCode();
    }

    // Método toString para representar el personaje como una cadena de texto
    @Override
    public String toString() {
        return "Personaje " + " | nombre ='" + getNombre() + " | nivel =" + getNivel() + "| salud =" + getSalud();
    }
}
