package pt.uc.dei.aor.paj;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named 
@RequestScoped
public class Chatinterface  implements Serializable {
	private static final long serialVersionUID = 1471294883116438933L;
	
	@Inject Messages messages;
	@Inject Users users;
	private String username;
	private String frase;
	private String[][] pagina;

	//apagar
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getFrase() {
		return frase;
	}
	public void setFrase(String frase) {
		this.frase = frase;
	}
	
	
	public String getpagina() {
		Msgbean[] paginamsg=messages.getMessages();
		pagina=new String[paginamsg.length][2];
		for (int i=0;i<paginamsg.length;i++) {
			pagina[i][0]=paginamsg[i].getEmissor().getUsername();
			pagina[i][1]=paginamsg[i].getFrase();
		}
		return "calculadora";
	}
	
	
	//passar esta funcao para o Usersinterface
	public String btnsend(String username) {
		Userbean u=users.getUser(username);
		if (u!=null) {
			messages.addMessages( new Msgbean(u, frase) );
		}
		return "calculadora";
	}
	
	
}
