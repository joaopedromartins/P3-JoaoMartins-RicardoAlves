package pt.uc.dei.aor.paj;

public class User {
	private String username;
	private String password;
	
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

	//Setter de nova password, validando a anterior
		public boolean setPassword(String password, String newpassword) {
			if (this.password.equals(password)) {
				this.password=newpassword;
				return true;
			}
			else return false;
		}
	
	//metodo para validar password correcta
	public boolean checkPassword(String password) {
		if (this.password.equals(password)) {
			return true;
		}
		else return false;
	}
	
	
}
