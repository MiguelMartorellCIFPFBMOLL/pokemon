package simuEx1;

public abstract class Persona {
    protected String nombre;
    protected String apellido;

    // Constructor vacío
    public Persona() {
    }

    // Constructor con parámetros
    public Persona(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    // Constructor copia
    public Persona(Persona p) {
        this.nombre = p.nombre;
        this.apellido = p.apellido;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    // Método toString
    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Apellido: " + apellido;
    }
}