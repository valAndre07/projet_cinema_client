package controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import consommation.Appel;
import metier.*;
import meserreurs.*;
import java.lang.reflect.Type;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;


/**
 * Servlet implementation class Controleur
 */
@WebServlet("/Controleur")
public class Controleur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ACTION_TYPE = "action";
	private static final String ERROR_KEY = "messageErreur";
	private static final String ERROR_PAGE = "/erreur.jsp";
	private static final String INDEX = "index";
	private static final String LISTER_FILMS = "listerFilms";
	private static final String ADD_FILM_FORM = "addFilmForm";
	private static final String ADD_FILM = "addFilm";
	private static final String EDIT_FILM = "editFilm";
	private static final String DELETE_FILM = "deleteFilm";
	private static final String LISTER_REALISATEURS = "listerRealisateurs";
	private static final String ADD_REALISATEUR_FORM = "addRealisateurForm";
	private static final String ADD_REALISATEUR = "addRealisateur";
	private static final String EDIT_REALISATEUR = "editRealisateur";
	private static final String DELETE_REALISATEUR = "deleteRealisateur";
	private static final String LISTER_ACTEURS = "listerActeurs";
	private static final String ADD_ACTEUR_FORM = "addActeurForm";
	private static final String ADD_ACTEUR = "addActeur";
	private static final String LISTER_CATEGORIES = "listerCategories";
	private static final String ADD_CATEGORIE_FORM = "addCategorieForm";
	private static final String ADD_CATEGORIE = "addCategorie";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controleur() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processusTraiteRequete(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processusTraiteRequete(request, response);
	}

	protected void processusTraiteRequete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String actionName = request.getParameter(ACTION_TYPE);
		String destinationPage = ERROR_PAGE;
		String reponse;
		// execute l'action
				if (INDEX.equals(actionName)) {
					destinationPage = "/index.jsp";
				}
		// execute l'action
		if (LISTER_FILMS.equals(actionName)) {
			String ressource = "films/listeFilms";
			try {
				Appel unAppel = new Appel();
				reponse = unAppel.appelJson(ressource);
				String recup = reponse.substring(8, reponse.length()-1);
				System.out.println(recup);
				Gson gson = new Gson();
				try
				{
					TypeToken<ArrayList<Film>> token = new TypeToken<ArrayList<Film>>(){};
					ArrayList<Film> films = gson.fromJson(recup, token.getType());
					
					request.setAttribute("mesFilms", films);
					System.out.println(films.get(0).getTitre());
				}
				catch(Exception e){
					System.out.println(e);
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				destinationPage = "/index.jsp";
				request.setAttribute("MesErreurs", e.getMessage());
			}

			destinationPage = "/listerfilms.jsp";
		}
		if (ADD_FILM_FORM.equals(actionName)) {
			String ressource1 = "realisateurs/listeRealisateurs";
			String ressource2 = "categories/listeCategories";
			try {
				Appel unAppel = new Appel();
				reponse = unAppel.appelJson(ressource1);
				//String recup = reponse.substring(8, reponse.length()-1);
				Gson gson = new Gson();
				try
				{
					TypeToken<ArrayList<Realisateur>> token = new TypeToken<ArrayList<Realisateur>>(){};
					ArrayList<Realisateur> realisateurs = gson.fromJson(reponse, token.getType());
					
					request.setAttribute("mesRealisateurs", realisateurs);
				}
				catch(Exception e){
					System.out.println(e);
				}
				reponse = unAppel.appelJson(ressource2);
				//String recup = reponse.substring(8, reponse.length()-1);
				try
				{
					TypeToken<ArrayList<Categorie>> token = new TypeToken<ArrayList<Categorie>>(){};
					ArrayList<Categorie> categories = gson.fromJson(reponse, token.getType());
					
					request.setAttribute("mesCategories", categories);
				}
				catch(Exception e){
					System.out.println(e);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				destinationPage = "/index.jsp";
				request.setAttribute("MesErreurs", e.getMessage());
			}
			destinationPage = "/addfilm.jsp";
		}
		if (ADD_FILM.equals(actionName)) {
			Film film = new Film();
			film.setTitre(request.getParameter("add_titre").toString());
			//film.setDuree(request.getParameter("add_duree"));
			//film.setDate(request.getParameter("add_titre"));
			//film.setBudget(request.getParameter("add_titre"));
			//film.setRecette(request.getParameter("add_titre"));
			//film.setRealisateur(request.getParameter("add_titre"));
			//film.setCategorie(request.getParameter("add_titre"));*/
			destinationPage = "/listerfilms.jsp";
		}
		if (EDIT_FILM.equals(actionName)) {
			destinationPage = "/editfilm.jsp";
		}
		if (DELETE_FILM.equals(actionName)) {
			String ressource = "films/deleteFilm";
			try {
				Appel unAppel = new Appel();
				reponse = unAppel.appelJson(ressource);
				Gson gson = new Gson();
				List<Film> json = gson.fromJson(reponse, List.class);
				request.setAttribute("mesFilms", json);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				destinationPage = "/index.jsp";
				request.setAttribute("MesErreurs", e.getMessage());
			}
			destinationPage = "/listerfilms.jsp";
		}
		if (LISTER_REALISATEURS.equals(actionName)) {
			String ressource = "realisateurs/listeRealisateurs";
			try {
				Appel unAppel = new Appel();
				reponse = unAppel.appelJson(ressource);
				Gson gson = new Gson();
				String recup = reponse.substring(15, reponse.length()-1);
				TypeToken<ArrayList<Realisateur>> token = new TypeToken<ArrayList<Realisateur>>(){};
				ArrayList<Realisateur> realisateurs = gson.fromJson(recup, token.getType());
				try
				{
					request.setAttribute("mesRealisateurs", realisateurs);
				}
				catch(Exception e){
					System.out.println(e);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				destinationPage = "/index.jsp";
				request.setAttribute("MesErreurs", e.getMessage());
			}

			destinationPage = "/listerrealisateurs.jsp";
		}
		if (ADD_REALISATEUR_FORM.equals(actionName)) {
			destinationPage = "/addrealisateur.jsp";
		}
		if (ADD_REALISATEUR.equals(actionName)) {
			Realisateur realisateur = new Realisateur();
			realisateur.setPrenomRealisateur(request.getParameter("add_prenom").toString());
			realisateur.setNomRealisateur(request.getParameter("add_nom").toString());
			destinationPage = "/listerrealisateurs.jsp";
		}
		if (EDIT_REALISATEUR.equals(actionName)) {
			destinationPage = "/editrealisateur.jsp";
		}
		if (DELETE_REALISATEUR.equals(actionName)) {
			destinationPage = "/listerrealisateurs.jsp";
		}
		
		if (LISTER_ACTEURS.equals(actionName)) {
			String ressource = "acteurs/listeActeurs";
			try {
				Appel unAppel = new Appel();
				reponse = unAppel.appelJson(ressource);
				Gson gson = new Gson();
				String recup = reponse.substring(10, reponse.length()-1);
				TypeToken<ArrayList<Acteur>> token = new TypeToken<ArrayList<Acteur>>(){};
				ArrayList<Acteur> acteurs = gson.fromJson(recup, token.getType());
				System.out.println(recup);
				try
				{
					request.setAttribute("mesActeurs", acteurs);
				}
				catch(Exception e){
					System.out.println(e);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				destinationPage = "/index.jsp";
				request.setAttribute("MesErreurs", e.getMessage());
			}

			destinationPage = "/listeracteurs.jsp";
		}
		if (ADD_ACTEUR_FORM.equals(actionName)) {
			destinationPage = "/addacteur.jsp";
		}
		if (ADD_ACTEUR.equals(actionName)) {
			Acteur acteur = new Acteur();
			acteur.setNomActeur(request.getParameter("add_nom").toString());
			acteur.setPrenomActeur(request.getParameter("add_prenom").toString());
			//acteur.setDateNaissance(dateNaissance);
			//acteur.setDateDeces(dateDeces);
			destinationPage = "/listeracteurs.jsp";
		}
		if (LISTER_CATEGORIES.equals(actionName)) {
			String ressource = "categories/listeCategories";
			try {
				Appel unAppel = new Appel();
				reponse = unAppel.appelJson(ressource);
				Gson gson = new Gson();
				String recup = reponse.substring(13, reponse.length()-1);
				System.out.println(recup);
				TypeToken<ArrayList<Categorie>> token = new TypeToken<ArrayList<Categorie>>(){};
				ArrayList<Categorie> categories = gson.fromJson(recup, token.getType());
				try
				{
					request.setAttribute("mesCategories", categories);
				}
				catch(Exception e){
					System.out.println(e);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				destinationPage = "/index.jsp";
				request.setAttribute("MesErreurs", e.getMessage());
			}

			destinationPage = "/listercategories.jsp";
		}
		if (ADD_CATEGORIE_FORM.equals(actionName)) {
			destinationPage = "/addcategorie.jsp";
		}
		if (ADD_CATEGORIE.equals(actionName)) {
			Categorie categorie = new Categorie();
			categorie.setLibelleCat(request.getParameter("add_libelle").toString());
			destinationPage = "/listercategories.jsp";
		}
		else {
			String messageErreur = "[" + actionName + "] n'est pas une action valide.";
			request.setAttribute(ERROR_KEY, messageErreur);
		}
		// Redirection vers la page jsp appropriee
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destinationPage);
		dispatcher.forward(request, response);
		 
		}
}

