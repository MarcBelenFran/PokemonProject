package clases;

public class Pokemon {
	private String nombre;
	private int id;
	private String tipo;
	private int vida;
	private int ataque;
	private int ataqueEspecial;
	private int defensa;
	private int defensaEspecial;
	private int velocidad;
	private Objeto objeto;
	private Pasiva pasiva;
	private Movimiento[] listaMovimientos;
	
	// Constructor
	public Pokemon(String nombre, int id, String tipo, int vida, int ataque, int ataqueEspecial, int defensa,
			int defensaEspecial, int velocidad, Objeto objeto, Pasiva pasiva, Movimiento[] listaMovimientos) {
		this.nombre = nombre;
		this.id = id;
		this.tipo = tipo;
		this.vida = vida;
		this.ataque = ataque;
		this.ataqueEspecial = ataqueEspecial;
		this.defensa = defensa;
		this.defensaEspecial = defensaEspecial;
		this.velocidad = velocidad;
		this.objeto = objeto;
		this.pasiva = pasiva;
		listaMovimientos = new Movimiento[4];
	}

	// Getters y Setters
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	
	public String getTipo() {return tipo;}
	public void setTipo(String tipo) {this.tipo = tipo;}
	
	public int getVida() {return vida;}
	public void setVida(int vida) {this.vida = vida;}
	
	public int getAtaque() {return ataque;}
	public void setAtaque(int ataque) {this.ataque = ataque;}
	
	public int getAtaqueEspecial() {return ataqueEspecial;}
	public void setAtaqueEspecial(int ataqueEspecial) {this.ataqueEspecial = ataqueEspecial;}
	
	public int getDefensa() {return defensa;}
	public void setDefensa(int defensa) {this.defensa = defensa;}
	
	public int getDefensaEspecial() {return defensaEspecial;}
	public void setDefensaEspecial(int defensaEspecial) {this.defensaEspecial = defensaEspecial;}
	
	public int getVelocidad() {return velocidad;}
	public void setVelocidad(int velocidad) {this.velocidad = velocidad;}
	
	public Objeto getObjeto() {return objeto;}
	public void setObjeto(Objeto objeto) {this.objeto = objeto;}
	
	public Pasiva getPasiva() {return pasiva;}
	public void setPasiva(Pasiva pasiva) {this.pasiva = pasiva;}
	
	public Movimiento[] getListaMovimientos() {return listaMovimientos;}
	public void setListaMovimientos(Movimiento[] listaMovimientos) {this.listaMovimientos = listaMovimientos;}
}