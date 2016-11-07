package controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import consommation.Appel;
import metier.*;
import meserreurs.*;

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
	private static final String EDIT_FILM = "editFilm";
	private static final String DELETE_FILM = "deleteFilm";
	private static final String LISTER_REALISATEURS = "listerRealisateurs";
	private static final String EDIT_REALISATEUR = "editRealisateur";
	private static final String DELETE_REALISATEUR = "deleteRealisateur";
	private static final String LISTER_ACTEURS = "listerActeurs";
	private static final String LISTER_CATEGORIES = "listerCategories";

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
			String ressource = "/listeFilms";
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
		if (EDIT_FILM.equals(actionName)) {
			destinationPage = "/editfilm.jsp";
		}
		if (DELETE_FILM.equals(actionName)) {
			destinationPage = "/listerfilms.jsp";
		}
		if (LISTER_REALISATEURS.equals(actionName)) {
			String ressource = "/listeRealisateurs";
			try {
				Appel unAppel = new Appel();
				reponse = unAppel.appelJson(ressource);
				Gson gson = new Gson();
				List<Film> json = gson.fromJson(reponse, List.class);
				request.setAttribute("mesRealisateurs", json);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				destinationPage = "/index.jsp";
				request.setAttribute("MesErreurs", e.getMessage());
			}

			destinationPage = "/listerrealisateurs.jsp";
		}
		
		if (EDIT_REALISATEUR.equals(actionName)) {
			destinationPage = "/editrealisateur.jsp";
		}
		if (DELETE_REALISATEUR.equals(actionName)) {
			destinationPage = "/listerrealisateurs.jsp";
		}
		
		if (LISTER_ACTEURS.equals(actionName)) {
			String ressource = "/listerActeurs";
			try {
				Appel unAppel = new Appel();
				reponse = unAppel.appelJson(ressource);
				Gson gson = new Gson();
				List<Film> json = gson.fromJson(reponse, List.class);
				request.setAttribute("mesActeurs", json);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				destinationPage = "/index.jsp";
				request.setAttribute("MesErreurs", e.getMessage());
			}

			destinationPage = "/listeracteurs.jsp";
		}
		if (LISTER_CATEGORIES.equals(actionName)) {
			String ressource = "/listerCategories";
			try {
				Appel unAppel = new Appel();
				reponse = unAppel.appelJson(ressource);
				Gson gson = new Gson();
				List<Film> json = gson.fromJson(reponse, List.class);
				request.setAttribute("mesCategories", json);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				destinationPage = "/index.jsp";
				request.setAttribute("MesErreurs", e.getMessage());
			}

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

