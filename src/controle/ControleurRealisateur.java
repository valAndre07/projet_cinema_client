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
@WebServlet("/ControleurRealisateur")
public class ControleurRealisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ACTION_TYPE = "action";
	private static final String ERROR_KEY = "messageErreur";
	private static final String ERROR_PAGE = "/erreur.jsp";
	private static final String INDEX = "index";
	private static final String LISTER_REALISATEURS = "listerRealisateurs";
	private static final String ADD_REALISATEUR_FORM = "addRealisateurForm";
	private static final String ADD_REALISATEUR = "addRealisateur";
	private static final String INFOS_REALISATEUR = "infosRealisateur";
	private static final String EDIT_REALISATEUR_FORM = "editRealisateurForm";
	private static final String EDIT_REALISATEUR = "editRealisateur";
	private static final String DELETE_REALISATEUR = "deleteRealisateur";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControleurRealisateur() {
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
					Appel unAppel = new Appel();
					
					String ressource = "/realisateurs/AjoutRealisateur/";
					unAppel = new Appel();
					reponse = unAppel.postJson(ressource, realisateur);
					
					destinationPage = "/ControleurRealisateur?action=listerRealisateurs";
				}
				if (INFOS_REALISATEUR.equals(actionName)) {
					String idRealisateur = (request.getParameter("idRealisateur").toString());
					String ressource1 = "realisateurs/"+idRealisateur;
					String ressource2 = "realisateurs/"+idRealisateur+"/films";
					try {
						Gson gson = new Gson();
						Appel unAppel = new Appel();
						reponse = unAppel.appelJson(ressource1);
						Realisateur realisateur = gson.fromJson(reponse,Realisateur.class);
						request.setAttribute("realisateur", realisateur);
						
						reponse = unAppel.appelJson(ressource2);
						String recup = reponse.substring(8, reponse.length()-1);
						
						try
						{
							TypeToken<ArrayList<Film>> token = new TypeToken<ArrayList<Film>>(){};
							ArrayList<Film> films = gson.fromJson(recup, token.getType());
							
							request.setAttribute("filmsRealisateur", films);
						}
						catch(Exception e){
							System.out.println(e);
						}
						
						} catch (Exception e) {
							// TODO Auto-generated catch block
							destinationPage = "/index.jsp";
							request.setAttribute("MesErreurs", e.getMessage());
						}
					
					destinationPage = "/infosrealisateur.jsp";
				}
				if (EDIT_REALISATEUR_FORM.equals(actionName)) {
					try {
						String idRealisateur = (request.getParameter("idRealisateur").toString());
						String ressource1 = "realisateurs/"+idRealisateur;
						
						Gson gson = new Gson();
						Appel unAppel = new Appel();
						reponse = unAppel.appelJson(ressource1);
						Realisateur realisateur = gson.fromJson(reponse,Realisateur.class);
						request.setAttribute("realisateur", realisateur);
						
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						destinationPage = "/index.jsp";
						request.setAttribute("MesErreurs", e.getMessage());
					}
					destinationPage = "/editrealisateur.jsp";
				}
				if (EDIT_REALISATEUR.equals(actionName)) {
					Realisateur realisateur = new Realisateur();
					realisateur.setNoRealisateur(Integer.parseInt(request.getParameter("edit_id").toString()));
					realisateur.setPrenomRealisateur(request.getParameter("edit_prenom").toString());
					realisateur.setNomRealisateur(request.getParameter("edit_nom").toString());
					int idRealisateur = Integer.parseInt((request.getParameter("edit_id").toString()));
					realisateur.setNoRealisateur(idRealisateur);
					Appel unAppel = new Appel();
					
					String ressource = "realisateurs/EditRealisateur/";
					unAppel = new Appel();
					reponse = unAppel.putJson(ressource, realisateur);
					
					destinationPage = "/ControleurRealisateur?action=listerRealisateurs";
				}
				if (DELETE_REALISATEUR.equals(actionName)) {
					int idRealisateur = Integer.parseInt((request.getParameter("idRealisateur").toString()));
					String ressource = "realisateurs/"+idRealisateur;
					try {
						Gson gson = new Gson();
						Appel unAppel = new Appel();
						unAppel.deleteJson(ressource);
						
						destinationPage = "/ControleurRealisateur?action=listerRealisateurs";
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

