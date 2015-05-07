package pt.uc.dei.aor.paj;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import java.io.Serializable;

@Named
@SessionScoped
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private boolean logged=false;
	
	public User() {	
	}
	
	public User (String username, String password){
		this.username=username;
		this.password=password;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}

	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}
	
	
}
