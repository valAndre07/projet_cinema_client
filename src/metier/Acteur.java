package metier;

import java.util.Date;

public class Acteur {

	private int noAct;
	private String nomAct;
	private String prenomAct;
	private Date dateNaiss;
	private Date dateDeces;
	
	public int getNoActeur() {
		return noAct;
	}
	public void setNoActeur(int noActeur) {
		this.noAct = noActeur;
	}
	public String getNomActeur() {
		return nomAct;
	}
	public void setNomActeur(String nomActeur) {
		this.nomAct = nomActeur;
	}
	public String getPrenomActeur() {
		return prenomAct;
	}
	public void setPrenomActeur(String prenomActeur) {
		this.prenomAct = prenomActeur;
	}
	public Date getDateNaissance() {
		return dateNaiss;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaiss = dateNaissance;
	}
	public Date getDateDeces() {
		return dateDeces;
	}
	public void setDateDeces(Date dateDeces) {
		this.dateDeces = dateDeces;
	}
	
	
}
