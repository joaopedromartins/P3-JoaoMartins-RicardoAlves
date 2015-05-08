package pt.uc.dei.aor.paj;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named 
@SessionScoped
public class UsersInterface implements Serializable {
	private static final long serialVersionUID = -8310185641498834904L;

	@Inject Users users;

	private String username;
	private String password;
	private String cpassword;
	private String msgerro;
	private String userLogged;


	public UsersInterface() {
		this.setUserLogged(null);
	}

	

	//Getter e Setter associados à variável username
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	//Getter e Setter associados à variável password
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	//Getter e Setter associados à variável cpassword
	public String getCpassword() {
		return cpassword;
	}
	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}

	//Getter associados à variável userlogged
	public String getUserLogged() {
		return userLogged;
	}
	public void setUserLogged(String userLogged) {
		this.userLogged = userLogged;
	}

	//Getter associados à variável msgerro
	public String getMsgerro() {
		return msgerro;
	}
	public void setMsgerro(String msgerro) {
		this.msgerro = msgerro;
	}

	
	//funcao para efectuar logout
	public String userlogout() {
		this.setUsername(null);
		this.setPassword(null);
		this.setUserLogged(null);
		return "login";
	}
	
	//funcao para efectuar login
	public String userlogin() {
		if (users.checkUser(username, password).equals("Login efectuado com sucesso")) {
			setUserLogged(username);
			setMsgerro(null);
			return "calculadora";
		}
		else {
			setUserLogged(null);
			setMsgerro("Erro: Utilizador ou password inválido(s)!");
			return "./index";
		}
	}
	
	//funcao para efectuar registo de utilizador
	public String usersignup() {
		//Testar tamanho do utilizador
		if (username.length()<2 ||username.length()>25) {
			setMsgerro("Erro: Tamanho de utilizador inválido!");
			return "signup";
		}
		else if (password.length()<2 ||password.length()>25) {
			setMsgerro("Erro: Tamanho de password inválido!");
			return "signup";
		}
		else if (!username.matches("[A-Za-z0-9]")) {
			setMsgerro("Erro: Utilizador tem caracter(es) inválido(s)!");
			return "signup";
		}
		else if (!password.matches("[A-Za-z0-9]")) {
			setMsgerro("Erro: Password tem caracter(es) inválido(s)!");
			return "signup";
		}
		else if (password.equals(cpassword) ) {
			String msg=users.addUser(username, password);
			if (msg.equals("Utilizador criado com sucesso.")) {
				if (userlogin().equals("calculadora")) {
					return "calculadora";
				}
				else {
					return "signin";
				}
			}
			else {
				setMsgerro(msg);
			}
		}
		else {
			setMsgerro("Erro: As passwords introduzidas não concidem!");
		}
		return "signup";
	}
	
	
}

