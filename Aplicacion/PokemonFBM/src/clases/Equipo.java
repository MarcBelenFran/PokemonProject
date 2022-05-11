package clases;

public class Equipo {
	private String nombre;
	private Pokemon[] listaPokemon;
	
	//Constructor
	public Equipo(String nombre, Pokemon[] listaPokemon) {
		this.nombre = nombre;
		listaPokemon = new Pokemon[6];
	}

	//Getters y Setters	
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	
	public Pokemon[] getListaPokemon() {return listaPokemon;}
	public void setListaPokemon(Pokemon[] listaPokemon) {this.listaPokemon = listaPokemon;}

}
