package metier;

public class Personnage {
	
	private Acteur acteur;
	private Film film;
	private String nomPersonnage;
	
	
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
		return nomPersonnage;
	}
	public void setNomPersonnage(String nomPersonnage) {
		this.nomPersonnage = nomPersonnage;
	}
	
	

}
