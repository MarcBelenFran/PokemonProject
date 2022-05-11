package clases;

public class Movimiento {
	private int id;
	private String nombre;
	private String tipo;
	private int potencia;
	private String descripcion;
	private boolean categoria;
	private int precision;
	private int probCritico;
	
	//Constructor
	public Movimiento(int id, String nombre, String tipo, int potencia, String descripcion, boolean categoria,
			int precision, int probCritico) {
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.potencia = potencia;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.precision = precision;
		this.probCritico = probCritico;
	}
	
	//Getters y Setters
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	
	public String getTipo() {return tipo;}
	public void setTipo(String tipo) {this.tipo = tipo;}
	
	public int getPotencia() {return potencia;}
	public void setPotencia(int potencia) {this.potencia = potencia;}
	
	public String getDescripcion() {return descripcion;}
	public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
	
	public boolean isCategoria() {return categoria;}
	public void setCategoria(boolean categoria) {this.categoria = categoria;}
	
	public int getPrecision() {return precision;}
	public void setPrecision(int precision) {this.precision = precision;}
	
	public int getProbCritico() {return probCritico;}
	public void setProbCritico(int probCritico) {this.probCritico = probCritico;}

}
