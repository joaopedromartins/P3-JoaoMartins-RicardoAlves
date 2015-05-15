package pt.uc.dei.aor.paj;

import java.io.Serializable;
import java.util.ArrayList;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class Users implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<Userbean> users;

	public Users() {
		users = new ArrayList<Userbean>();
		users.add(new Userbean ("ricardo", "123"));
		users.add(new Userbean ("joao", "123"));
	}

	//metodo para alterar password de um utilizador
	public synchronized boolean setPass(Userbean u, String password, String newpassword){
		return u.setPassword(password, newpassword);
	}

	//metodo para adicionar um utilizador
	public synchronized boolean addUser(String username, String password) {
		for(Userbean u:users){
			if(username.equals(u.getUsername())){
				return false;
			}
		}
		users.add( new Userbean(username, password) );
		return true;
	}

	//metodo para verificar utilizador e password
	public synchronized boolean checkUser(String username, String password){
		for(Userbean u:users){
			if(username.equals(u.getUsername())){
				return (u.checkPassword(password));
			}
		}
		return false;
	}

	public Userbean getUser(String username) {
		for(Userbean u:users){
			if(username.equals(u.getUsername())){
				return u;
			}
		}
		return null;
	}

}
