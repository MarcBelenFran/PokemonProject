package clasesCombate;

public class Movimiento {
	private int id = 0;
	private int ataque;
	private int probCritico;
	
	public Movimiento(int id, int ataque, int probCritico) {
		this.setId(id);
		this.setAtaque(ataque);
		this.setProbCritico(probCritico);
	}

	public int getAtaque() {return ataque;}
	public void setAtaque(int dmg) {this.ataque = dmg;}
	
	public int getProbCritico() {return probCritico;}
	public void setProbCritico(int probCritico) {this.probCritico = probCritico;}

	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
}
