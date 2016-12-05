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
@WebServlet("/ControleurPersonnage")
public class ControleurPersonnage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ACTION_TYPE = "action";
	private static final String ERROR_KEY = "messageErreur";
	private static final String ERROR_PAGE = "/erreur.jsp";
	private static final String INDEX = "index";
	private static final String LINK_PERSONNAGE_FORM = "linkPersonnageForm";
	private static final String LINK_PERSONNAGE = "linkPersonnage";
	private static final String DELETE_PERSONNAGE = "deletePersonnage";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControleurPersonnage() {
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
			Gson gson = new Gson();
			/*Acteur acteur = new Acteur();
			Film film = new Film();
			
			String ressource1 = "films/"+idFilm;
			Gson gson = new Gson();
			Appel unAppel = new Appel();
			reponse = unAppel.appelJson(ressource1);
			film = gson.fromJson(reponse, Film.class);
			
			String ressource2 = "acteurs/"+idActeur;
			reponse = unAppel.appelJson(ressource2);
			acteur = gson.fromJson(reponse, Acteur.class);
			*/
			Personnage personnage = new Personnage();
			PersonnagePK pk = new PersonnagePK();
			pk.setNoAct(idActeur);
			pk.setNoFilm(idFilm);
			personnage.setId(pk);
			personnage.setNomPersonnage(request.getParameter("link_personnage").toString());
			
			String ressource = "/personnages/AjoutPersonnage/";
			Appel unAppel = new Appel();
			reponse = unAppel.postJson(ressource, personnage);			
			destinationPage = "/ControleurActeur?action=listerActeurs";
		}
		if (DELETE_PERSONNAGE.equals(actionName)) {
			String idFilm = request.getParameter("idFilm").toString();
			String idActeur = request.getParameter("idActeur").toString();
			String ressource = "personnages/{\"noFilm\":"+idFilm+",\"noAct\":"+idActeur+"}";
					
					
			try {
				Gson gson = new Gson();
				Appel unAppel = new Appel();
				unAppel.deleteJson(ressource);
				
				destinationPage = "/ControleurFilm?action=infosFilm&idFilm="+idFilm;
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

