package metier;

public class Acteur {

	private int noAct;
	private String nomAct;
	private String prenomAct;
	private String dateNaiss;
	private String dateDeces;
	
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
	public String getDateNaissance() {
		return dateNaiss;
	}
	public void setDateNaissance(String dateNaissance) {
		this.dateNaiss = dateNaissance;
	}
	public String getDateDeces() {
		return dateDeces;
	}
	public void setDateDeces(String dateDeces) {
		this.dateDeces = dateDeces;
	}
	
	
}
