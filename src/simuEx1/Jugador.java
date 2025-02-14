package simuEx1;

public class Jugador extends Persona{
	
	private int dorsal;
	private String demarcacion;
	
	public Jugador() {
		super();
	}
	
	public Jugador(String nombre, String apellido, int dorsal, String demarcacion) {
	    super(nombre, apellido); // Llamada correcta al constructor de Persona
	    this.dorsal = dorsal;
	    this.demarcacion = demarcacion;
	}

	
	public Jugador(Jugador j) {
		super(j);
		this.dorsal = j.dorsal;
		this.demarcacion = j.demarcacion;
	}
	
	public int getDorsal() {
		return dorsal;
	}
	
	public void setDorsal (int dorsal) {
		this.dorsal = dorsal;
	}
	
	public String getDemarcacion() {
		return demarcacion;
	}
	
	public void setDemarcacion(String demarcacion) {
		this.demarcacion = demarcacion;
	}
	
	public String toString() {
        return super.toString() + ", Dorsal: " + dorsal + ", Demarcación: " + demarcacion;
    }
}
