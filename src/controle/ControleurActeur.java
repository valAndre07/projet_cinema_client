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
@WebServlet("/ControleurActeur")
public class ControleurActeur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ACTION_TYPE = "action";
	private static final String ERROR_KEY = "messageErreur";
	private static final String ERROR_PAGE = "/erreur.jsp";
	private static final String INDEX = "index";
	private static final String LISTER_ACTEURS = "listerActeurs";
	private static final String ADD_ACTEUR_FORM = "addActeurForm";
	private static final String ADD_ACTEUR = "addActeur";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControleurActeur() {
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
		
		
		else {
			String messageErreur = "[" + actionName + "] n'est pas une action valide.";
			request.setAttribute(ERROR_KEY, messageErreur);
		}
		// Redirection vers la page jsp appropriee
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destinationPage);
		dispatcher.forward(request, response);
		 
		}
}
