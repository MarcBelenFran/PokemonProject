package clases;

public class Usuario {
	private int id;
	private String nombre;
	private Equipo[] listaEquipos;
	private Tarjeta tarjeta;
	
	// Constructor
	public Usuario(int id, String nombre, Equipo[] listaEquipos, Tarjeta tarjeta) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.listaEquipos = listaEquipos;
		this.tarjeta = tarjeta;
	}

	// Getters y Setters
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	
	public Equipo[] getListaEquipos() {return listaEquipos;}
	public void setListaEquipos(Equipo[] listaEquipos) {this.listaEquipos = listaEquipos;}
	
	public Tarjeta getTargeta() {return tarjeta;}
	public void setTargeta(Tarjeta tarjeta) {this.tarjeta = tarjeta;}
}
