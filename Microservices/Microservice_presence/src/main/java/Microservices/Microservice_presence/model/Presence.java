package Microservices.Microservice_presence.model;

import java.util.Random;

public class Presence {
	private int id, etage, salle;
	private boolean etat;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEtage() {
		return etage;
	}
	public void setEtage(int etage) {
		this.etage = etage;
	}
	public int getSalle() {
		return salle;
	}
	public void setSalle(int salle) {
		this.salle = salle;
	}
	public boolean isEtat() {
		return etat;
	}
	public void setEtat(boolean etat) {
		this.etat = etat;
	}
	public Presence(int etage, int salle) {
		super();
		Random rand = new Random();
		this.id = etage*10 + salle;
		this.etage = etage;
		this.salle = salle;
		rand = new Random();
		this.etat = rand.nextBoolean();
	}
	public Presence() {
		super();
	}
	
	public void update() {
		Random rand = new Random();
		this.etat = rand.nextBoolean();
	}
	
	
}
