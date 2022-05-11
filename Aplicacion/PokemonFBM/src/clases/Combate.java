package clases;

public class Combate {
	private Premium[] jugador1;
	private Premium[] jugador2;
	
	//Constructor
	public Combate(Premium[] jugador1, Premium[] jugador2) {
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;
	}

	//Getters y setters
	public Premium[] getJugador1() {return jugador1;}
	public void setJugador1(Premium[] jugador1) {this.jugador1 = jugador1;}
	
	public Premium[] getJugador2() {return jugador2;}
	public void setJugador2(Premium[] jugador2) {this.jugador2 = jugador2;}

}
