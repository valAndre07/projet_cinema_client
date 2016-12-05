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
@WebServlet("/ControleurCategorie")
public class ControleurCategorie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ACTION_TYPE = "action";
	private static final String ERROR_KEY = "messageErreur";
	private static final String ERROR_PAGE = "/erreur.jsp";
	private static final String INDEX = "index";
	private static final String LISTER_CATEGORIES = "listerCategories";
	private static final String ADD_CATEGORIE_FORM = "addCategorieForm";
	private static final String ADD_CATEGORIE = "addCategorie";
	private static final String EDIT_CATEGORIE_FORM = "editCategorieForm";
	private static final String EDIT_CATEGORIE = "editCategorie";
	private static final String INFOS_CATEGORIE = "infosCategorie";
	private static final String DELETE_CATEGORIE = "deleteCategorie";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControleurCategorie() {
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
		
		if (LISTER_CATEGORIES.equals(actionName)) {
			String ressource = "categories/listeCategories";
			try {
				Appel unAppel = new Appel();
				reponse = unAppel.appelJson(ressource);
				Gson gson = new Gson();
				String recup = reponse.substring(13, reponse.length()-1);
				try
				{
					if (recup.substring(0, 1).equals("[")) {
						TypeToken<ArrayList<Categorie>> token = new TypeToken<ArrayList<Categorie>>(){};
						ArrayList<Categorie> categories = gson.fromJson(recup, token.getType());
						request.setAttribute("mesCategories", categories);
					} else {
						Categorie categorie = gson.fromJson(recup, Categorie.class);
						ArrayList<Categorie> categories = new ArrayList<Categorie>();
						categories.add(categorie);
						request.setAttribute("mesCategories", categories);
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

			destinationPage = "/listercategories.jsp";
		}
		if (ADD_CATEGORIE_FORM.equals(actionName)) {
			destinationPage = "/addcategorie.jsp";
		}
		if (ADD_CATEGORIE.equals(actionName)) {
			
			Categorie categorie = new Categorie();
			categorie.setCodeCat(request.getParameter("add_code").toString());
			categorie.setLibelleCat(request.getParameter("add_libelle").toString());
			Appel unAppel = new Appel();
			String ressource = "/categories/AjoutCategorie/";
			unAppel = new Appel();
			reponse = unAppel.postJson(ressource, categorie);
						
			destinationPage = "/ControleurCategorie?action=listerCategories";
		}
		if (EDIT_CATEGORIE_FORM.equals(actionName)) {
			String idCat = (request.getParameter("codeCat").toString());
			String ressource1 = "categories/"+idCat;
			
			try {
				
				Gson gson = new Gson();
				Appel unAppel = new Appel();
				reponse = unAppel.appelJson(ressource1);
				Categorie categorie = gson.fromJson(reponse, Categorie.class);
			
				request.setAttribute("categorie", categorie);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				destinationPage = "/index.jsp";
				request.setAttribute("MesErreurs", e.getMessage());
			}
			destinationPage = "/editcategorie.jsp";
		}
		if (EDIT_CATEGORIE.equals(actionName)) {
			
			Categorie categorie = new Categorie();
			categorie.setCodeCat(request.getParameter("edit_code").toString());
			categorie.setLibelleCat(request.getParameter("edit_libelle").toString());
			Appel unAppel = new Appel();
			String ressource = "/categories/EditCategorie/";
			unAppel = new Appel();
			reponse = unAppel.putJson(ressource, categorie);
						
			destinationPage = "/ControleurCategorie?action=listerCategories";
		}
		if (INFOS_CATEGORIE.equals(actionName)) {
			String codeCat = request.getParameter("codeCat").toString();
			String ressource1 = "categories/"+codeCat;
			String ressource2 = "categories/"+codeCat+"/films";
			try {
				Gson gson = new Gson();
				Appel unAppel = new Appel();
				reponse = unAppel.appelJson(ressource1);
				Categorie categorie = gson.fromJson(reponse, Categorie.class);
				
				request.setAttribute("categorie", categorie);
				
				reponse = unAppel.appelJson(ressource2);
				System.out.println(reponse);
				String recup = reponse.substring(8, reponse.length()-1);
				TypeToken<ArrayList<Film>> token = new TypeToken<ArrayList<Film>>(){};
				ArrayList<Film> films = gson.fromJson(recup, token.getType());
				try
				{
					request.setAttribute("films", films);
				}
				catch(Exception e){
					System.out.println(e);
				}
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					destinationPage = "/index.jsp";
					request.setAttribute("MesErreurs", e.getMessage());
				}
			
			destinationPage = "/infoscategorie.jsp";
		}
		if (DELETE_CATEGORIE.equals(actionName)) {
			String codeCat = (request.getParameter("codeCat").toString());
			String ressource = "categories/"+codeCat;
			try {
				Gson gson = new Gson();
				Appel unAppel = new Appel();
				unAppel.deleteJson(ressource);
				
				destinationPage = "/ControleurCategorie?action=listerCategories";
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

