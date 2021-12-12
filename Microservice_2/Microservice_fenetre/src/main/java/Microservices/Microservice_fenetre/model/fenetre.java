package Microservices.Microservice_fenetre.model;

public class fenetre {
	private int id;
	private boolean etat;
	private int etage, salle;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isEtat() {
		return etat;
	}
	public void setEtat(boolean etat) {
		this.etat = etat;
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
	public fenetre(int id, int etage, int salle) {
		super();
		this.id = id;
		this.etage = etage;
		this.salle = salle;
	}
	public fenetre() {
		super();
	}
		

}
