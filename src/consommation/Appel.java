package consommation;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class Appel {
	
	public  String  appelTextPlain(String action)
	{
		String uneChaine;
		
		WebTarget target = Consommateur.get().target;
		target = target.path(action);
		System.out.println(" uri :"  + target.getUri());
		uneChaine= target.request().accept(MediaType.TEXT_PLAIN).get(String.class);
		return uneChaine;
	}
	
	public  String  appelXml()
	{
		String uneChaine;
		
		WebTarget target = Consommateur.get().target;
		target = target.path("get");
		//System.out.println(" uri :"  + target.getUri());
		uneChaine= target.request().accept(MediaType.APPLICATION_XML).get(String.class);
		return uneChaine;
	}
	
	
	public  String  appelJson(String action)
	{
		String uneChaine;
		
		WebTarget target = Consommateur.get().target;
		target = target.path(action);
		System.out.println(" uri :"  + target.getUri());
		uneChaine= target.request().accept(MediaType.APPLICATION_JSON).get(String.class);
		return uneChaine;
	}
	
	public String putJson(String action, Object unObj)
	{
		Response uneChaine;
		
		WebTarget target = Consommateur.get().target;
		target = target.path(action);
		System.out.println(" uri :"  + target.getUri());
		Gson gson = new Gson();
		String json=gson.toJson(unObj);
		Response response = target.request(MediaType.APPLICATION_JSON)
	               .put(Entity.entity(json, MediaType.APPLICATION_JSON),Response.class);

		if(response.getStatus() >= 200 && response.getStatus() <= 299) {
			 return "Mise à jour effectuée";
		 }
		 else {
			 return "Echec mise à jour";
		 }
	}
	
	public String postJson(String action, Object unObj)
	{
		Response uneChaine;
		
		WebTarget target = Consommateur.get().target;
		target = target.path(action);
		System.out.println(" uri :" + target.getUri());
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.setDateFormat("yyyy-MM-dd").create();
		String json=gson.toJson(unObj);
		System.out.println(" json :"  + json);
		target.request(MediaType.APPLICATION_JSON).header("Content-Type", "application/json");
		Response response = target.request(MediaType.APPLICATION_JSON)
	               .post(Entity.entity(json, MediaType.APPLICATION_JSON),Response.class);
	     System.out.println(response.getStatus());       
		 if(response.getStatus() >= 200 && response.getStatus() <= 299) {
			 return "Mise à jour effectuée";
		 }
		 else {
			 return "Echec mise à jour";
		 }
	}
	public String deleteJson(String action)
	{
		Response uneChaine;
		
		WebTarget target = Consommateur.get().target;
		target = target.path(action);
		System.out.println(" uri :"  + target.getUri());
		Response response = target.request(MediaType.APPLICATION_JSON)
	               .delete();
	             
		 if(response.getStatus() >= 200 && response.getStatus() <= 299) {
			 return "Mise à jour effectuée";
		 }
		 else {
			 return "Echec mise à jour";
		 }
	}
	
}
