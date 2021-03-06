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
	private static final String EDIT_ACTEUR_FORM = "editActeurForm";
	private static final String EDIT_ACTEUR = "editActeur";
	private static final String INFOS_ACTEUR = "infosActeur";
	private static final String DELETE_ACTEUR = "deleteActeur";

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
		if (INDEX.equals(actionName)) {
			destinationPage = "/index.jsp";
		}
		
		if (LISTER_ACTEURS.equals(actionName)) {
			String ressource = "acteurs/listeActeurs";
			try {
				Appel unAppel = new Appel();
				reponse = unAppel.appelJson(ressource);
				Gson gson = new Gson();
				String recup = reponse.substring(10, reponse.length()-1);
				try
				{
					if (recup.substring(0, 1).equals("[")) {
						TypeToken<ArrayList<Acteur>> token = new TypeToken<ArrayList<Acteur>>(){};
						ArrayList<Acteur> acteurs = gson.fromJson(recup, token.getType());
						request.setAttribute("mesActeurs", acteurs);
					} else {
						Acteur acteur = gson.fromJson(recup, Acteur.class);
						ArrayList<Acteur> acteurs = new ArrayList<Acteur>();
						acteurs.add(acteur);
						request.setAttribute("mesActeurs", acteurs);
					}
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
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Acteur acteur = new Acteur();
			acteur.setNomActeur(request.getParameter("add_nom").toString());
			acteur.setPrenomActeur(request.getParameter("add_prenom").toString());
			String date_naissance = request.getParameter("add_date_naissance");
			String values[]  = date_naissance.split("/");
			acteur.setDateNaissance(formatter.parse(values[2]+"-"+values[0]+"-"+values[1]));
			String date_deces = request.getParameter("add_date_deces");
			if (date_deces!="")
			{
				String values2[]  = date_deces.split("/");
				acteur.setDateDeces(formatter.parse(values2[2]+"-"+values2[0]+"-"+values2[1]));
			}
			
			
			Appel unAppel = new Appel();
			String ressource = "/acteurs/AjoutActeur/";
			unAppel = new Appel();
			reponse = unAppel.postJson(ressource, acteur);
			
			destinationPage = "/ControleurActeur?action=listerActeurs";
		}
		if (EDIT_ACTEUR_FORM.equals(actionName)) {
			int idActeur = Integer.parseInt((request.getParameter("noActeur").toString()));
			String ressource1 = "acteurs/"+idActeur;
			try {
				
				Gson gson = new Gson();
				Appel unAppel = new Appel();
				reponse = unAppel.appelJson(ressource1);
				Acteur acteur = gson.fromJson(reponse, Acteur.class);
				
				request.setAttribute("acteur", acteur);
					
			} catch (Exception e) {
				// TODO Auto-generated catch block
				destinationPage = "/index.jsp";
				request.setAttribute("MesErreurs", e.getMessage());
			}
			destinationPage = "/editacteur.jsp";
		}
		if (EDIT_ACTEUR.equals(actionName)) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Acteur acteur = new Acteur();
			acteur.setNoActeur(Integer.parseInt(request.getParameter("edit_id").toString()));
			acteur.setNomActeur(request.getParameter("edit_nom").toString());
			acteur.setPrenomActeur(request.getParameter("edit_prenom").toString());
			String date_naissance = request.getParameter("edit_date_naissance");
			String values[]  = date_naissance.split("/");
			acteur.setDateNaissance(formatter.parse(values[2]+"-"+values[0]+"-"+values[1]));
			String date_deces = request.getParameter("edit_date_deces");
			if (date_deces!="")
			{
				String values2[]  = date_deces.split("/");
				acteur.setDateDeces(formatter.parse(values2[2]+"-"+values2[0]+"-"+values2[1]));
			}
			
			Appel unAppel = new Appel();
			String ressource = "/acteurs/EditActeur/";
			unAppel = new Appel();
			reponse = unAppel.putJson(ressource, acteur);
			
			destinationPage = "/ControleurActeur?action=listerActeurs";
		}
		if (INFOS_ACTEUR.equals(actionName)) {
			int noActeur = Integer.parseInt((request.getParameter("noActeur").toString()));
			String ressource1 = "acteurs/"+noActeur;
			String ressource2 = "acteurs/"+noActeur+"/personnages";
			try {
				Gson gson = new Gson();
				Appel unAppel = new Appel();
				reponse = unAppel.appelJson(ressource1);
				Acteur acteur = gson.fromJson(reponse, Acteur.class);
				
				request.setAttribute("categorie", acteur);
				
				reponse = unAppel.appelJson(ressource2);
				String recup = reponse.substring(14, reponse.length()-1);
				if (recup.substring(0, 1).equals("[")) {
					TypeToken<ArrayList<Personnage>> token = new TypeToken<ArrayList<Personnage>>(){};
					ArrayList<Personnage> personnages = gson.fromJson(recup, token.getType());
					request.setAttribute("personnages", personnages);
				} else {
					Personnage personnage = gson.fromJson(recup, Personnage.class);
					ArrayList<Personnage> personnages = new ArrayList<Personnage>();
					personnages.add(personnage);
					request.setAttribute("personnages", personnages);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				destinationPage = "/index.jsp";
				request.setAttribute("MesErreurs", e.getMessage());
			}
			
			destinationPage = "/infosacteurs.jsp";
		}
		if (DELETE_ACTEUR.equals(actionName)) {
			int noActeur = Integer.parseInt((request.getParameter("noActeur").toString()));
			String ressource = "acteurs/"+noActeur;
			try {
				Gson gson = new Gson();
				Appel unAppel = new Appel();
				unAppel.deleteJson(ressource);
				
				destinationPage = "/ControleurActeur?action=listerActeurs";
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

