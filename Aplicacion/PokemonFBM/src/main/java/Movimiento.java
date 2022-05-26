
public class Movimiento {
	private static int id = 0;
	private int ataque;
	private int probCritico;
	
	public Movimiento(String tipo, int ataque, int probCritico) {
		Movimiento.id++;
		this.setAtaque(ataque);
		this.setProbCritico(probCritico);
	}

	public int getAtaque() {return ataque;}
	public void setAtaque(int daño) {this.ataque = daño;}
	
	public int getProbCritico() {return probCritico;}
	public void setProbCritico(int probCritico) {this.probCritico = probCritico;}

	public int getId() {return id;}
	public void setId(int id) {Movimiento.id = id;}
}
