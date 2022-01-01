package Microservices.Microservice_volet.model;

import java.util.Random;

public class volet {
	
	private int id, etage, salle;
	private boolean etat;

	private double order;
	
	public double getOrder() {
		return order;
	}
	public void setOrder(double order) {
		this.order = order;
		if(this.order > 1)
			this.etat = true;
		else
			this.etat = false;
	}
	
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
	public volet(int etage, int salle) {
		super();
		Random rand = new Random();
		this.id = rand.nextInt(1000);
		this.etage = etage;
		this.salle = salle;
	}
	public volet() {
		super();
	}
	
}
