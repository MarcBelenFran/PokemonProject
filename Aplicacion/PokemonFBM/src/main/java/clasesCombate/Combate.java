package clasesCombate;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Combate {
	private static int id;
	private static Usuario usr1;
	private static Usuario usr2;
	private static Pokemon pk1, pk2;
	private static Movimiento mv1, mv2;
	public boolean running = true;
	public static Usuario ganador;
	
	File chat = new File(id+"log.txt");
	
	
	private void OrdenAtaque(Pokemon primero, Movimiento primeroM, Pokemon segundo, Movimiento segundoM) {
		
		//INICIO DE LA ESCRITURA
		try (BufferedWriter escritor = new BufferedWriter(new FileWriter(chat))){
			
		
			escritor.write(primero.getNombre() + " ha usado " + primeroM + "!");
			
			//CALCULO DE DMG
			int primerDmg = calculoDmg(primero, primeroM, segundo);
			int segundoDmg =  calculoDmg(segundo, segundoM, primero);
			
			//ATACA EL PRIMER POKEMON
			if((int)(Math.random() * 100 + 1) > primeroM.getProbCritico() ) {
				primerDmg = (int) (primerDmg*1.33);
				escritor.write("\n" + primero.getNombre() + " ha realizado un critico de " + primerDmg + " a " + segundo.getNombre() + "!"); 
				segundo.takeDmg(primerDmg);
			}else {
				escritor.write("\n" + primero.getNombre() + " ha realizado un ataque de " + primerDmg + " a " + segundo.getNombre() + "!"); ; 
				segundo.takeDmg(primerDmg);
			}
			
			if(segundo.isVivo() == false) {
				escritor.write("\n" + segundo.getNombre() + " ha caido en combate!");
				for(Pokemon pokemon : usr2.getEquipo()) {
					for (int i = 0; i < usr2.getEquipo().size(); i++) {
						if(pokemon.getNombre().equals(usr2.getEquipo().get(i).getNombre())) {
							usr2.getEquipo().remove(i);
						}else if(usr2.getEquipo().size()==0) {
							running = false;
							ganador = usr1;
							escritor.write("\n" + ganador.getNombre() + " ha sido el vencedor del combate!");
						}
					}
				}
				for(Pokemon pokemon : usr1.getEquipo()) {
					for (int i = 0; i < usr1.getEquipo().size(); i++) {
						if(pokemon.getNombre().equals(usr1.getEquipo().get(i).getNombre())) {
							usr1.getEquipo().remove(i);
						}else if(usr1.getEquipo().size()==0) {
							running = false;
							ganador = usr2;
							escritor.write("\n" + ganador.getNombre() + " ha sido el vencedor del combate!");
						}
					}
				}escritor.newLine();
			}
			
			
			//ATACA EL SEGUNDO POKEMON
			if((int)(Math.random() * 100 + 1) > segundoM.getProbCritico() ) {
				segundoDmg = (int) (segundoDmg*1.33);
				escritor.write("\n" + segundo.getNombre() + " ha realizado un critico de " + segundoDmg + " a " + primero.getNombre() + "!");
				primero.takeDmg(segundoDmg);
			}else {
				escritor.write("\n" + segundo.getNombre() + " ha realizado un ataque de " + segundoDmg + " a " + primero.getNombre() + "!"); 
				primero.takeDmg(segundoDmg);
			}
			
			if(primero.isVivo() == false) {
				escritor.write("\n" + primero.getNombre() + " ha caido en combate!");
				for(Pokemon pokemon : usr2.getEquipo()) {
					for (int i = 0; i < usr2.getEquipo().size(); i++) {
						if(pokemon.getNombre().equals(usr2.getEquipo().get(i).getNombre())) {
							usr2.getEquipo().remove(i);
						}else if(usr2.getEquipo().size()==0) {
							running = false;
							ganador = usr1;
							escritor.write("\n" + ganador.getNombre() + " ha sido el vencedor del combate!");
						}
					}
				}
				for(Pokemon pokemon : usr1.getEquipo()) {
					for (int i = 0; i < usr1.getEquipo().size(); i++) {
						if(pokemon.getNombre().equals(usr1.getEquipo().get(i).getNombre())) {
							usr1.getEquipo().remove(i);
						}else if(usr1.getEquipo().size()==0) {
							running = false;
							ganador = usr2;
							escritor.write("\n" + ganador.getNombre() + " ha sido el vencedor del combate!");
						}
					}
				}escritor.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void ataqueIndividual (Pokemon defiende, Pokemon ataca, Movimiento ataque) {
		try (BufferedWriter escritor = new BufferedWriter(new FileWriter(chat))){
			
			
			escritor.write(ataca.getNombre() + " ha usado " + ataque + "!");
			
			//CALCULO DE DMG
			int primerDmg = calculoDmg(ataca, ataque, defiende);
			
			//ATACA EL POKEMON
			if((int)(Math.random() * 100 + 1) > ataque.getProbCritico() ) {
				primerDmg = (int) (primerDmg*1.33);
				escritor.write("\n" + ataca.getNombre() + " ha realizado un critico de " + primerDmg + " a " + defiende.getNombre() + "!"); 
				defiende.takeDmg(primerDmg);
			}else {
				escritor.write("\n" + ataca.getNombre() + " ha realizado un ataque de " + primerDmg + " a " + defiende.getNombre() + "!"); ; 
				defiende.takeDmg(primerDmg);
			}
			
			if(defiende.isVivo() == false) {
				escritor.write("\n" + defiende.getNombre() + " ha caido en combate!");
				for(Pokemon pokemon : usr2.getEquipo()) {
					for (int i = 0; i < usr2.getEquipo().size(); i++) {
						if(pokemon.getNombre().equals(usr2.getEquipo().get(i).getNombre())) {
							usr2.getEquipo().remove(i);
						}else if(usr2.getEquipo().size()==0) {
							running = false;
							ganador = usr1;
							escritor.write("\n" + ganador.getNombre() + " ha sido el vencedor del combate!");
						}
					}
				}
				for(Pokemon pokemon : usr1.getEquipo()) {
					for (int i = 0; i < usr1.getEquipo().size(); i++) {
						if(pokemon.getNombre().equals(usr1.getEquipo().get(i).getNombre())) {
							usr1.getEquipo().remove(i);
						}else if(usr1.getEquipo().size()==0) {
							running = false;
							ganador = usr2;
							escritor.write("\n" + ganador.getNombre() + " ha sido el vencedor del combate!");
						}
					}
				}escritor.newLine();
			}
		}catch (IOException e) {
				e.printStackTrace();
		}
			
	}
	
	public File usaMovimiento() {
		if(usr1.isCambioPokemon() == true && usr2.isCambioPokemon() == true) {
			cambioPk(usr1, pk1);
			cambioPk(usr2, pk2);
			usr1.setCambioPokemon(false);
			usr2.setCambioPokemon(false);
			return chat;
		}else if(usr1.isCambioPokemon()==true && usr2.isCambioPokemon()==false) {
			cambioPk(usr1, pk1);
			ataqueIndividual(pk1, pk2, mv2);
			usr1.setCambioPokemon(false);
			return chat;
		}else if(usr1.isCambioPokemon()==false && usr2.isCambioPokemon()==true) {
			cambioPk(usr2, pk2);
			ataqueIndividual(pk2, pk1, mv1);
			usr2.setCambioPokemon(false);
			return chat;
		}else {
			if(pk1.getStats()[4]>= pk2.getStats()[4]) {
				OrdenAtaque(pk1, mv1, pk2, mv2);
				return chat;
			}else {
				OrdenAtaque(pk2, mv2, pk1, mv1);
				return chat;
			}
		}
	}
	
	private int calculoDmg(Pokemon atacando, Movimiento usado, Pokemon defendiendo) {
		int ataque, defensa;
		ataque = atacando.getStats()[2];
		defensa = defendiendo.getStats()[1];
		
		return (int)(ataque+usado.getAtaque()-defensa);
	}
	
	private void cambioPk(Usuario usr, Pokemon pk) {
		try (BufferedWriter escritor = new BufferedWriter(new FileWriter(chat))){
			escritor.write("\n" + usr.getNombre() + " ha cambiado el pokemon a" + pk.getNombre() + "!");
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	
	//GETTERS Y SETTERS
	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		Combate.id = id;
	}

	public static Usuario getUsr1() {
		return usr1;
	}

	public static void setUsr1(Usuario usr1) {
		Combate.usr1 = usr1;
	}

	public static Usuario getUsr2() {
		return usr2;
	}

	public static void setUsr2(Usuario usr2) {
		Combate.usr2 = usr2;
	}

	public static Pokemon getPk1() {
		return pk1;
	}

	public static void setPk1(Pokemon pk1) {
		Combate.pk1 = pk1;
	}

	public static Pokemon getPk2() {
		return pk2;
	}

	public static void setPk2(Pokemon pk2) {
		Combate.pk2 = pk2;
	}

	public static Movimiento getMv1() {
		return mv1;
	}

	public static void setMv1(Movimiento mv1) {
		Combate.mv1 = mv1;
	}

	public static Movimiento getMv2() {
		return mv2;
	}

	public static void setMv2(Movimiento mv2) {
		Combate.mv2 = mv2;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public static Usuario getGanador() {
		return ganador;
	}

	public static void setGanador(Usuario ganador) {
		Combate.ganador = ganador;
	}
	
}
