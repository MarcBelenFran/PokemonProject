package clases;

public class Efecto {
	private float valor;
	private String descripcion;
	private boolean subirBajar;
	private String estadisticas;
	
	// Constructor
	public Efecto(float valor, String descripcion, boolean subirBajar, String estadisticas) {
		super();
		this.valor = valor;
		this.descripcion = descripcion;
		this.subirBajar = subirBajar;
		this.estadisticas = estadisticas;
	}
	
	//Getters y Setters
	public float getValor() {return valor;}
	public void setValor(float valor) {this.valor = valor;}
	
	public String getDescripcion() {return descripcion;}
	public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
	
	public boolean isSubirBajar() {return subirBajar;}
	public void setSubirBajar(boolean subirBajar) {this.subirBajar = subirBajar;}
	
	public String getEstadisticas() {return estadisticas;}
	public void setEstadisticas(String estadisticas) {this.estadisticas = estadisticas;}
}
