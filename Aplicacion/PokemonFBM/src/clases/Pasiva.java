package clases;

public class Pasiva extends Efecto {
	private String nombre;
	private int id;
	
	// Constructor
	public Pasiva(float valor, String descripcion, boolean subirBajar, String estadisticas, String nombre, int id) {
		super(valor, descripcion, subirBajar, estadisticas);
		this.nombre = nombre;
		this.id = id;
	}

	// Getters y Setters
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}	
	
}
