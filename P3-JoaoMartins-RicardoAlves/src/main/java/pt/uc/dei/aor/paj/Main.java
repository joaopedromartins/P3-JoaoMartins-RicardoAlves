package pt.uc.dei.aor.paj;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named 
@RequestScoped
public class Main implements Serializable {

	private static final long serialVersionUID = 3730437403554200689L;

	@Inject Calculadora calc;
	@Inject Estatistica est;
	@Inject PickListView pickHist;
	@Inject Conversor conv;
	@Inject Users users;

	private String display;
	private String firstdigit;
	private String btnradio;
	private String resultado;
	private String username;
	private String password;
	private String cpassword;
	private String msgerro;
	private String userLogged;


	public Main() {
		this.display = "0.0";
		this.resultado = "0.0";
		this.firstdigit = "true";
		this.btnradio = "rad";
		this.setUserLogged("joao");
	}

	//Getter e Setter da variável display referente ao valor da expressão introduzida pelo utilizador 
	//e ao resultado final após calculo da expressão	
	public String getDisplay() {
		return this.resultado;
	}
	public void setDisplay(String display) {
		this.display = display;
	}

	//envio da expressão introduzida pelo utiliador para a classe estatística
	public void setValor(String display) {
		est.setResultado(display);
	} 

	//Getter e Setter do valor associado ao primeiro digito da expressão
	public String getFirstdigit() {
		return firstdigit;
	}
	public void setFirstdigit(String firstdigit) {
		this.firstdigit = firstdigit;
	}

	//Getter e Setter associados à variável graus/radianos
	public String getBtnradio() {
		return btnradio;
	}
	public void setBtnradio(String btnradio) {
		this.btnradio = btnradio;
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

	//função de interface entre o cliente e o servidor
	public void btnequal() {
		String aux, aux3;
		//envia o valor da expressão introduzida para o historico
		pickHist.init(this.display);

		//caso a exp esteja em graus tem de converter antes de enviar para a lib
		if (this.btnradio.equals("deg")){
			//converte em radianos
			aux=conv.decompoe(this.display);
			//envia para calculo
			calc.setExp(aux);
			//obtem o valor do calculo em radianos
			aux3=calc.getExp();
			this.resultado=aux3+"";
			
		}else{
			//envia o valor da expressão introduzida para efectuar o calculo
			calc.setExp(this.display);
			//o resultado toma o valor devolvido após calculo da expressao
			this.resultado=calc.getExp();
		}

		//envio para os dados estatisticos
		setValor(this.display);
		this.setFirstdigit("true");
	}
	
	//funcao para efectuar logout
	public void userlogout() {
		this.setUsername(null);
		this.setPassword(null);
		this.setUserLogged(null);
	}
	
	//funcao para efectuar login
	public boolean userlogin() {
		if (users.checkUser(username, password).equals("Login efectuado com sucesso")) {
			setUserLogged(username);
			setMsgerro(null);
			return true;
		}
		else {
			setUserLogged(null);
			setMsgerro("Utilizador ou password inválido(s).");
			return false;
		}
	}
	
	//funcao para efectuar registo de utilizador
	public boolean usersignup() {
		//Testar tamanho do utilizador
		if (username.length()<2 ||username.length()<25) {
			setMsgerro("Erro: Tamanho de utilizador inválido!");
			return false;
		}
		else if (password.length()<2 ||password.length()<25) {
			setMsgerro("Erro: Tamanho de password inválido!");
			return false;
		}
		else if (!username.matches("[A-Za-z0-9]")) {
			setMsgerro("Erro: Utilizador tem caracter(es) inválido(s)!");
			return false;
		}
		else if (!password.matches("[A-Za-z0-9]")) {
			setMsgerro("Erro: Password tem caracter(es) inválido(s)!");
			return false;
		}
		else if (password.equals(cpassword) ) {
			String msg=users.addUser(username, password);
			if (msg.equals("Utilizador criado com sucesso.")) {
				userlogin();
				return true;
			}
			else {
				setMsgerro(msg);
			}
		}
		else {
			setMsgerro("Erro: As passwords introduzidas não concidem!");
		}
		return false;
	}
	
	
}
