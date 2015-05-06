package pt.uc.dei.aor.paj;

import java.io.Serializable;
import java.util.ArrayList;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Users implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<User> users;
	
	public Users() {
		synchronized(users){
			users = new ArrayList<>();
			users.add( new User ("ricardo", "123") );
			users.add( new User ("joao", "123") );
		}
	}

	public ArrayList<User> getUsers() {
		synchronized(users){
			return users;
		}
	} 

	public void addUser(User u) {
		synchronized(users){
			//falta testar se ja existe no array
			users.add(u);
		}
	}
	
}
