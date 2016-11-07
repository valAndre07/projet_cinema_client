package metier;

public class Film {

	private int noFilm;
	private String titre;
	private int duree;
	private String dateSortie;
	private int budget;
	private int montantRecette;
	private Realisateur realisateur;
	private Categorie categorie;
	
	public int getNoFilm() {
		return noFilm;
	}
	public void setNoFilm(int noFilm) {
		this.noFilm = noFilm;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public int getDuree() {
		return duree;
	}
	public void setDuree(int duree) {
		this.duree = duree;
	}
	public String getDateSortie() {
		return dateSortie;
	}
	public void setDateSortie(String dateSortie) {
		this.dateSortie = dateSortie;
	}
	public int getBudget() {
		return budget;
	}
	public void setBudget(int budget) {
		this.budget = budget;
	}
	public int getMontantRecette() {
		return montantRecette;
	}
	public void setMontantRecette(int montantRecette) {
		this.montantRecette = montantRecette;
	}
	public Realisateur getRealisateur() {
		return realisateur;
	}
	public void setRealisateur(Realisateur realisateur) {
		this.realisateur = realisateur;
	}
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	
}
