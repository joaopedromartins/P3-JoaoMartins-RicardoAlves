package pt.uc.dei.aor.paj;


import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

@Named 
@SessionScoped
public class Calcinterface implements Serializable {
	private static final long serialVersionUID = -5031594103596986625L;
	
	@Inject Calculadora calc;
	@Inject Estatistica est;
	@Inject PickListView pickHist;
	@Inject Conversor conv;

	private String display;   /*APAGAR DEPOIS DE CONVERTER*/
	
	private String expression;
	private boolean firstdigit;
	private String btnradio;
	private String resultado;
	private String tipocalc;


	public Calcinterface() {
		this.display = "0.0";
		this.resultado = "0.0";
		this.firstdigit = true;
		this.btnradio = "rad";
		this.tipocalc = "cientifica";
		//this.tipocalc = "basica";
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
	public boolean getFirstdigit() {
		return firstdigit;
	}
	public void setFirstdigit(boolean firstdigit) {
		this.firstdigit = firstdigit;
	}

	//Getter e Setter associados à variável graus/radianos
	public String getBtnradio() {
		return btnradio;
	}
	public void setBtnradio(String btnradio) {
		this.btnradio = btnradio;
	}

	//Getter e Setter associados à variável basica/cientifica
	public String getTipocalc() {
		//System.out.println("get tipoclac");
		return tipocalc;
	}
	public void setTipocalc(String tipocalc) {
		this.tipocalc = tipocalc;
		//System.out.println("set tipoclac");
	}
	
	//Getter e Setter associados à variável expression
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
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
		this.setFirstdigit(true);
	}
	
	//metodo que controla a visibilidade dos botoes calculadora cientifica
	public boolean typescientific() {
		if (tipocalc.equals("cientifica")) {
			return true;
		} else {
			return false;
		}
	}
	
	//metodo que liga teclado à expressao/display 
	public void ackey() {
		this.expression = "0.0";
		this.resultado = "0.0";
		this.firstdigit=true;
	}
	
	//metodo que liga teclado à expressao/display 
	public void key(ActionEvent event) {
		/*if (firstdigit) {
			this.expression = "0.0";
			firstdigit = false;
		}
		if (this.expression.equals("0")) {
			this.expression = "";
		}*/
		String add = "";
		switch(event.getComponent().getId()) {
			case "btndivby": {
				add = "/"; 
				break;
			} case "btntimes": {
				add = "*";
				break;
			} case "btnplus": {
				add = "+";
				break;
			} case "btnminus": {
				add = "-";	
				break;
			} case "btndot": {
				add = ".";
				break;
			} case "btn0": {
				add = "0";
				break;
			} case "btn1": {
				add = "1";
				break;
			} case "btn2": {
				add = "2";
				break;
			} case "btn3": {
				add = "3";
				break;
			} case "btn4": {
				add = "4";
				break;
			} case "btn5": {
				add = "5";
				break;
			} case "btn6": {
				add = "6";
				break;
			} case "btn7": {
				add = "7";
				break;
			} case "btn8": {
				add = "8";
				break;
			} case "btn9": {
				add = "9";
				break;
			} case "sen": {
				add = "sin(";
				break;
			} case "cos": {
				add = "cos(";
				break;
			} case "tan": {
				add = "tan(";
				break;
			} case "asen": {
				add = "asin(";
				break;
			} case "acos": {
				add = "acos(";
				break;
			} case "atan": {
				add = "atan(";
				break;
			} case "ln": {
				add = "log(";
				break;
			} case "log": {
				add = "log10(";
				break;
			} case "factor": {
				break;
			} case "pot2": {
				add = "^2";
				break;
			} case "raiz2": {
				add = "sqrt(";
				break;
			} case "euler": {
				add = "e";
				break;
			} case "openP": {
				add = "(";
				break;
			} case "closeP": {
				add = ")";
				break;
			} case "modulo": {
				add = "abs(";
				break;
			} case "pi": {
				add = "PI";
				break;
			}
		}
		if (add.length()>0) {
			addToExpression(add);
		}
		/*if (!op.equals("")) {
			operators.add(op);
		}*/
	}
	
	public void addToExpression(String e) {
		if (e.length()<1) {
			return;
		} else if (this.firstdigit) {
			if( e.matches("[0-9]")) {
				System.out.println("TEST: Match: 0-9");
				this.expression=""+e;
				
			} else if (e.equals("-") || e.equals("+") || e.equals("/") || e.equals("*") ) {
				System.out.println("TEST: Match: + - * / ");
				this.expression = resultado + e;
			} else {
				System.out.println("TEST: FALTA VALIDAR FUNCOES");
				this.expression=expression+e;
			}
		} else {
			this.expression += e;
		}
		this.firstdigit=false;
		this.resultado=expression;
	}
}
