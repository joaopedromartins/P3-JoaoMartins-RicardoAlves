package pt.uc.dei.aor.paj;

import java.io.Serializable;
import java.util.ArrayList;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class Users implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<User>users;
	@Inject private User utilizador;
	
	public Users() {

			users = new ArrayList<User>();
			users.add(new User ("ricardo", "123"));
			users.add(new User ("joao", "123"));
	}

	public synchronized ArrayList<User> getUsers() {
		synchronized(users){
			return users;
		}
	} 

	public synchronized void addUser(User u) {
		
			//falta testar se ja existe no array
			users.add(u);
		
	}
	
	public synchronized String checkUser(){
		String message="Username inválido";
			for(User u:users){
				if(utilizador.getUsername().equals(u.getUsername())){
					if(utilizador.getPassword().equals(u.getPassword())){
						message="Login efectuado com sucesso";
					}else{
						message="Password inválida";
					}
					break;
				}
			}
		return message;
	}
	 
	public void setUser(String username){
		utilizador.setUsername(username);
	}
	
	public void setPass(String password){
		utilizador.setPassword(password);
	}
	
}
