package metier;

import java.util.List;

public class Realisateur {

	private int noRea;
	private String nomRea;
	private String prenRea;
	private List<Film> films;
	
	public int getNoRealisateur() {
		return noRea;
	}
	public void setNoRealisateur(int noRealisateur) {
		this.noRea = noRealisateur;
	}
	public String getNomRealisateur() {
		return nomRea;
	}
	public void setNomRealisateur(String nomRealisateur) {
		this.nomRea = nomRealisateur;
	}
	public String getPrenomRealisateur() {
		return prenRea;
	}
	public void setPrenomRealisateur(String prenomRealisateur) {
		this.prenRea = prenomRealisateur;
	}
	
	
}
