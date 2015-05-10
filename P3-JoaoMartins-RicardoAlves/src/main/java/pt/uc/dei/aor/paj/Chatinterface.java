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
	private String username;
	private String frase;

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
}
