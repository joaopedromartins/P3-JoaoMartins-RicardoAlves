package pt.uc.dei.aor.paj;

import java.io.Serializable;
import java.util.ArrayList;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class Users implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<User> users;

	public Users() {
		users = new ArrayList<User>();
		users.add(new User ("ricardo", "123"));
		users.add(new User ("joao", "123"));
	}

	//metodo para alterar password de um utilizador
	public synchronized boolean setPass(User u, String password, String newpassword){
		return u.setPassword(password, newpassword);
	}
	
	//metodo para adicionar um utilizador
	public synchronized String addUser(String username, String password) {
		for(User u:users){
			if(username.equals(u.getUsername())){
				return "Erro: utilizador já existente!";
			}
		}
		users.add( new User(username, password) );
		return "Utilizador criado com sucesso.";
	}

	//metodo para verificar utilizador e password
	public synchronized boolean checkUser(String username, String password){
		for(User u:users){
			if(username.equals(u.getUsername())){
				return (u.checkPassword(password));
			}
		}
		return false;
	}

}
