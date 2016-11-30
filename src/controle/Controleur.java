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
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;


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
	private static final String INFOS_FILM = "infosFilm";
	private static final String DELETE_FILM = "deleteFilm";
	private static final String LISTER_REALISATEURS = "listerRealisateurs";
	private static final String ADD_REALISATEUR_FORM = "addRealisateurForm";
	private static final String ADD_REALISATEUR = "addRealisateur";
	private static final String INFOS_REALISATEUR = "infosRealisateur";
	private static final String EDIT_REALISATEUR = "editRealisateur";
	private static final String DELETE_REALISATEUR = "deleteRealisateur";
	private static final String LISTER_ACTEURS = "listerActeurs";
	private static final String ADD_ACTEUR_FORM = "addActeurForm";
	private static final String ADD_ACTEUR = "addActeur";
	private static final String LISTER_CATEGORIES = "listerCategories";
	private static final String ADD_CATEGORIE_FORM = "addCategorieForm";
	private static final String ADD_CATEGORIE = "addCategorie";
	private static final String LINK_PERSONNAGE_FORM = "linkPersonnageForm";
	private static final String LINK_PERSONNAGE = "linkPersonnage";

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
		try {
			processusTraiteRequete(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processusTraiteRequete(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void processusTraiteRequete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		String actionName = request.getParameter(ACTION_TYPE);
		String destinationPage = ERROR_PAGE;
		String reponse;
		// execute l'action
				if (INDEX.equals(actionName)) {
					destinationPage = "/index.jsp";
				}
		// execute l'action
		
		
		
		if (LISTER_ACTEURS.equals(actionName)) {
			String ressource = "acteurs/listeActeurs";
			try {
				Appel unAppel = new Appel();
				reponse = unAppel.appelJson(ressource);
				Gson gson = new Gson();
				String recup = reponse.substring(10, reponse.length()-1);
				System.out.println(recup);
				TypeToken<ArrayList<Acteur>> token = new TypeToken<ArrayList<Acteur>>(){};
				ArrayList<Acteur> acteurs = gson.fromJson(recup, token.getType());
				System.out.println(acteurs.get(0).getPrenomActeur());
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
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Acteur acteur = new Acteur();
			acteur.setNomActeur(request.getParameter("add_nom").toString());
			acteur.setPrenomActeur(request.getParameter("add_prenom").toString());
			acteur.setDateNaissance(formatter.parse(request.getParameter("add_date_naissance").toString()));
			acteur.setDateDeces(formatter.parse(request.getParameter("add_date_deces").toString()));

			
			Appel unAppel = new Appel();
			String ressource = "/acteurs/" + acteur;
			unAppel = new Appel();
			reponse = unAppel.postJson(ressource, acteur);
			
			destinationPage = "/Controleur?action=listerActeurs";
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
			Appel unAppel = new Appel();
			String ressource = "/categories/" + categorie;
			unAppel = new Appel();
			reponse = unAppel.postJson(ressource, categorie);
						
			destinationPage = "/Controleur?action=listerCategories";
		}
		if (LINK_PERSONNAGE_FORM.equals(actionName)) {
			String ressource1 = "films/listeFilms";
			String ressource2 = "acteurs/listeActeurs";
			try {
				Appel unAppel = new Appel();
				reponse = unAppel.appelJson(ressource1);
				String recup = reponse.substring(8, reponse.length()-1);
				Gson gson = new Gson();
				try
				{
					TypeToken<ArrayList<Film>> token = new TypeToken<ArrayList<Film>>(){};
					ArrayList<Film> films = gson.fromJson(recup, token.getType());
					
					request.setAttribute("mesFilms", films);
					
					reponse = unAppel.appelJson(ressource2);
					recup = reponse.substring(10, reponse.length()-1);
					TypeToken<ArrayList<Acteur>> token2 = new TypeToken<ArrayList<Acteur>>(){};
					ArrayList<Acteur> acteurs = gson.fromJson(recup, token2.getType());
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
			destinationPage = "/linkpersonnage.jsp";
		}
		if (LINK_PERSONNAGE.equals(actionName)) {
			
			int idActeur = Integer.parseInt(request.getParameter("add_acteur").toString());
			int idFilm = Integer.parseInt(request.getParameter("add_film").toString());
				
			Acteur acteur = new Acteur();
			Film film = new Film();
			
			String ressource1 = "films/"+idFilm;
			Gson gson = new Gson();
			Appel unAppel = new Appel();
			reponse = unAppel.appelJson(ressource1);
			film = gson.fromJson(reponse, Film.class);
			
			String ressource2 = "acteurs/"+idActeur;
			reponse = unAppel.appelJson(ressource2);
			acteur = gson.fromJson(reponse, Acteur.class);

			Personnage personnage = new Personnage();
			personnage.setActeur(acteur);
			personnage.setFilm(film);
			personnage.setNomPersonnage(request.getParameter("link_personnage").toString());
			
			System.out.println(personnage.getNomPersonnage());
			System.out.println(personnage.getActeur().getNomActeur());
			System.out.println(personnage.getFilm().getTitre());
			String ressource = "/personnages/" + personnage;
			unAppel = new Appel();
			reponse = unAppel.postJson(ressource, personnage);			
			destinationPage = "/Controleur?action=listerActeurss";
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

