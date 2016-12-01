package metier;

public class Personnage {
	
	private Acteur acteur;
	private Film film;
	private String nomPers;
	
	
	public Acteur getActeur() {
		return acteur;
	}
	public void setActeur(Acteur acteur) {
		this.acteur = acteur;
	}
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
	public String getNomPersonnage() {
		return nomPers;
	}
	public void setNomPersonnage(String nomPersonnage) {
		this.nomPers = nomPersonnage;
	}
	
	

}
