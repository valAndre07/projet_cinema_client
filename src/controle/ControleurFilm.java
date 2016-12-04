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
@WebServlet("/ControleurFilm")
public class ControleurFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ACTION_TYPE = "action";
	private static final String ERROR_KEY = "messageErreur";
	private static final String ERROR_PAGE = "/erreur.jsp";
	private static final String INDEX = "index";
	private static final String LISTER_FILMS = "listerFilms";
	private static final String ADD_FILM_FORM = "addFilmForm";
	private static final String ADD_FILM = "addFilm";
	private static final String EDIT_FILM_FORM = "editFilmForm";
	private static final String EDIT_FILM = "editFilm";
	private static final String INFOS_FILM = "infosFilm";
	private static final String DELETE_FILM = "deleteFilm";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControleurFilm() {
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
		if (LISTER_FILMS.equals(actionName)) {
			String ressource = "films/listeFilms";
			try {
				Appel unAppel = new Appel();
				reponse = unAppel.appelJson(ressource);
				String recup = reponse.substring(8, reponse.length()-1);
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
					Gson gson = new Gson();
					String recup = reponse.substring(15, reponse.length()-1);
					TypeToken<ArrayList<Realisateur>> token = new TypeToken<ArrayList<Realisateur>>(){};
					ArrayList<Realisateur> realisateurs = gson.fromJson(recup, token.getType());
					
					reponse = unAppel.appelJson(ressource2);
					recup = reponse.substring(13, reponse.length()-1);
					TypeToken<ArrayList<Categorie>> token2 = new TypeToken<ArrayList<Categorie>>(){};
					ArrayList<Categorie> categories = gson.fromJson(recup, token2.getType());
					
					request.setAttribute("mesRealisateurs", realisateurs);
					request.setAttribute("mesCategories", categories);
					
			} catch (Exception e) {
				// TODO Auto-generated catch block
				destinationPage = "/index.jsp";
				request.setAttribute("MesErreurs", e.getMessage());
			}
		
			destinationPage = "/addfilm.jsp";
		}
		if (ADD_FILM.equals(actionName)) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Film film = new Film();
			film.setTitre(request.getParameter("add_titre").toString());
			film.setDuree(Integer.parseInt(request.getParameter("add_duree").toString()));
			String date = request.getParameter("add_date");
			String values[]  = date.split("/");
			film.setDateSortie(formatter.parse(values[2]+"-"+values[0]+"-"+values[1]));
			film.setBudget(Integer.parseInt(request.getParameter("add_budget").toString()));
			film.setMontantRecette(Integer.parseInt(request.getParameter("add_recette").toString()));
			int idRealisateur = Integer.parseInt(request.getParameter("add_realisateur").toString());
			String idCategorie = (request.getParameter("add_categorie").toString());
			
			Realisateur realisateur = new Realisateur();
			Categorie categorie = new Categorie();
			
			String ressource1 = "categories/"+idCategorie;
			Gson gson = new Gson();
			Appel unAppel = new Appel();
			reponse = unAppel.appelJson(ressource1);
			categorie = gson.fromJson(reponse, Categorie.class);
			
			String ressource2 = "realisateurs/"+idRealisateur;
			reponse = unAppel.appelJson(ressource2);
			realisateur = gson.fromJson(reponse, Realisateur.class);
			film.setRealisateur(realisateur);
			film.setCategorie(categorie);
						
			String ressource = "/films/AjoutFilm/";
			unAppel = new Appel();
			reponse = unAppel.postJson(ressource, film);
			System.out.println(reponse);			
			
			destinationPage = "/ControleurFilm?action=listerFilms";
		}
		if (EDIT_FILM_FORM.equals(actionName)) {
			int idFilm = Integer.parseInt((request.getParameter("idFilm").toString()));
			String ressource0 = "films/"+idFilm;
			String ressource1 = "realisateurs/listeRealisateurs";
			String ressource2 = "categories/listeCategories";
			try {
				
				Gson gson = new Gson();
				Appel unAppel = new Appel();
				reponse = unAppel.appelJson(ressource0);
				Film film = gson.fromJson(reponse, Film.class);
				
				
					reponse = unAppel.appelJson(ressource1);
					String recup = reponse.substring(15, reponse.length()-1);
					TypeToken<ArrayList<Realisateur>> token = new TypeToken<ArrayList<Realisateur>>(){};
					ArrayList<Realisateur> realisateurs = gson.fromJson(recup, token.getType());
					
					reponse = unAppel.appelJson(ressource2);
					recup = reponse.substring(13, reponse.length()-1);
					TypeToken<ArrayList<Categorie>> token2 = new TypeToken<ArrayList<Categorie>>(){};
					ArrayList<Categorie> categories = gson.fromJson(recup, token2.getType());
					
					request.setAttribute("film", film);
					request.setAttribute("mesRealisateurs", realisateurs);
					request.setAttribute("mesCategories", categories);
					
			} catch (Exception e) {
				// TODO Auto-generated catch block
				destinationPage = "/index.jsp";
				request.setAttribute("MesErreurs", e.getMessage());
			}
		
			destinationPage = "/editfilm.jsp";
		}
		if (EDIT_FILM.equals(actionName)) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Film film = new Film();
			film.setTitre(request.getParameter("edit_titre").toString());
			film.setDuree(Integer.parseInt(request.getParameter("edit_duree").toString()));
			String date = request.getParameter("edit_date");
			String values[]  = date.split("/");
			film.setDateSortie(formatter.parse(values[2]+"-"+values[0]+"-"+values[1]));
			film.setBudget(Integer.parseInt(request.getParameter("edit_budget").toString()));
			film.setMontantRecette(Integer.parseInt(request.getParameter("edit_recette").toString()));
			int idRealisateur = Integer.parseInt(request.getParameter("edit_realisateur").toString());
			String idCategorie = (request.getParameter("edit_categorie").toString());
			int idFilm = Integer.parseInt((request.getParameter("edit_id").toString()));
			film.setNoFilm(idFilm);
			
			Realisateur realisateur = new Realisateur();
			Categorie categorie = new Categorie();
			
			String ressource1 = "categories/"+idCategorie;
			Gson gson = new Gson();
			Appel unAppel = new Appel();
			reponse = unAppel.appelJson(ressource1);
			categorie = gson.fromJson(reponse, Categorie.class);
			
			String ressource2 = "realisateurs/"+idRealisateur;
			reponse = unAppel.appelJson(ressource2);
			realisateur = gson.fromJson(reponse, Realisateur.class);
			film.setRealisateur(realisateur);
			film.setCategorie(categorie);
						
			String ressource = "/films/EditFilm/";
			unAppel = new Appel();
			reponse = unAppel.putJson(ressource, film);
			System.out.println(reponse);			
			
			destinationPage = "/ControleurFilm?action=listerFilms";
		}
		if (INFOS_FILM.equals(actionName)) {
			int idFilm = Integer.parseInt((request.getParameter("idFilm").toString()));
			String ressource1 = "films/"+idFilm;
			String ressource2 = "films/"+idFilm+"/acteurs";
			try {
				Gson gson = new Gson();
				Appel unAppel = new Appel();
				reponse = unAppel.appelJson(ressource1);
				Film film = gson.fromJson(reponse, Film.class);
				
				request.setAttribute("film", film);
				
				reponse = unAppel.appelJson(ressource2);
				String recup = reponse.substring(14, reponse.length()-1);
				System.out.println(recup);
				TypeToken<ArrayList<Personnage>> token = new TypeToken<ArrayList<Personnage>>(){};
				ArrayList<Personnage> personnages = gson.fromJson(recup, token.getType());
				try
				{
					request.setAttribute("mesPersonnages", personnages);
				}
				catch(Exception e){
					System.out.println(e);
				}
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					destinationPage = "/index.jsp";
					request.setAttribute("MesErreurs", e.getMessage());
				}
			
			destinationPage = "/infosfilm.jsp";
		}
		if (DELETE_FILM.equals(actionName)) {
			int idFilm = Integer.parseInt((request.getParameter("idFilm").toString()));
			String ressource = "films/deleteFilm/"+idFilm;
			try {
				Gson gson = new Gson();
				Appel unAppel = new Appel();
				unAppel.appelJson(ressource);
				
				destinationPage = "/ControleurFilm?action=listerFilms";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				destinationPage = "/index.jsp";
				request.setAttribute("MesErreurs", e.getMessage());
			}
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

