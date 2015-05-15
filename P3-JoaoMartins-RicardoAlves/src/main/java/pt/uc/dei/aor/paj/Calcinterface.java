package pt.uc.dei.aor.paj;


import java.io.Serializable;
import java.util.ArrayList;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

@Named 
@SessionScoped
public class Calcinterface implements Serializable {
	private static final long serialVersionUID = -5031594103596986625L;

	@Inject private Calculadora calc;
	@Inject private Estatistica est;
	@Inject private PickListView pickHist;
	@Inject private Conversor conv;

	private String display;   
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

	//Getter e Setter da variÃ¡vel display referente ao valor da expressÃ£o introduzida pelo utilizador 
	//e ao resultado final apÃ³s calculo da expressÃ£o	
	public String getDisplay() {
		return this.resultado;
	}
	public void setDisplay(String display) {
		this.display = display;
	}

	//envio da expressÃ£o introduzida pelo utiliador para a classe estatÃ­stica
	public void setValor(String display) {
		est.setResultado(display);
	} 

	//Getter e Setter do valor associado ao primeiro digito da expressÃ£o
	public boolean getFirstdigit() {
		return firstdigit;
	}
	public void setFirstdigit(boolean firstdigit) {
		this.firstdigit = firstdigit;
	}

	//Getter e Setter associados a  variavel graus/radianos
	public String getBtnradio() {
		return btnradio;
	}
	public void setBtnradio(String btnradio) {
		this.btnradio = btnradio;
	}

	//Getter e Setter associados a variavel tipocalc
	public String getTipocalc() {
		//System.out.println("get tipoclac");
		return tipocalc;
	}
	public void setTipocalc(String tipocalc) {
		this.tipocalc = tipocalc;
		//System.out.println("set tipoclac");
	}

	//funcao de interface entre o cliente e o servidor
	public String btnequal() {
		String aux1, aux2;
		float tempo;

		//caso a exp esteja em graus tem de converter antes de enviar para a lib
		if (this.btnradio.equals("deg")){
			//converte em radianos
			aux1=conv.decompoe(this.display);
			//envia para calculo
			calc.setExp(aux1);
			//obtem o valor do calculo em radianos
			aux2=calc.getExp();
			this.resultado=aux2+"";
			tempo = calc.getTempo();

		}else{
			//envia o valor da expressÃ£o introduzida para efectuar o calculo
			calc.setExp(this.display);
			//o resultado toma o valor devolvido apÃ³s calculo da expressao
			this.resultado=calc.getExp();
			tempo = calc.getTempo();
		}

		// VERIFICAR SE O RESULTADO E NUMERICO ANTES DE ENVIAR PARA A ESTATISTICA
		if (resultado.matches("[\\x00-\\x20]*[+-]?(NaN|Infinity|((((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)" +
				"([eE][+-]?(\\p{Digit}+))?)|(\\.((\\p{Digit}+))([eE][+-]?(\\p{Digit}+))?)|" +
				"(((0[xX](\\p{XDigit}+)(\\.)?)|(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+)))" +
				"[pP][+-]?(\\p{Digit}+)))[fFdD]?))[\\x00-\\x20]*")) {
			//envio para os dados estatisticos
			setValor(this.display);
		}

		// NOTA: ENVIAR SEMPRE PARA A HISTORICO PARA QUE SEJA POSSIVEL CORRIGIR A EXPRESSAO

		//envia o valor da expressao introduzida para o historico
		pickHist.init(this.display);

		//acrescentar tempo do calculo ao historico
		pickHist.addTime(String.valueOf(tempo));

		//Sinaliza que proxima tecla e primeiro digito
		this.setFirstdigit(true);

		//devolve pagina destino que e a propria pagina
		return "calculadora";
	}

	//metodo que controla a visibilidade dos botoes calculadora cientifica
	public boolean typescientific() {
		if (tipocalc.equals("cientifica")) {
			return true;
		} else {
			return false;
		}
	}

	//metodo que liga tecla AC Ã  expressao/display 
	public String ackey() {
		this.display = "0.0";
		this.resultado = "0.0";
		this.firstdigit=true;
		return "calculadora";
	}


	//metodo que liga tecla C Ã  expressao/display 
	public String ckey() {
		//Se display tem 0 ou 1 caracter
		if ( this.display.length() < 2) {
			this.display="";
		}
		//Se display tem mais do que 1 caracter
		else {
			String lastchar=this.display.substring(this.display.length()-1, this.display.length()); 
			//Se for operador
			if (lastchar=="-" || lastchar=="+" || lastchar=="*" || lastchar=="/" || lastchar=="." || lastchar=="^" || lastchar=="!" ) {
				this.display=this.display.substring(0, this.display.length()-1);
			}
			//Se for numero
			else if ( lastchar.matches("[0-9]") ){
				for (int i=this.display.length()-1;i>=0;i--) {
					lastchar=this.display.substring(i,i+1); 
					if (  lastchar.matches("[0-9]") ) {
						this.display=this.display.substring(0, this.display.length()-1);
					}
					else if ( lastchar=="." ) {
						this.display=this.display.substring(0, this.display.length()-1);
					}
					else {
						break;
					}
				}
			}
			//se for parentices
			else if (lastchar=="(" || lastchar==")") {
				this.display=this.display.substring(0, this.display.length()-1);
			}
			//se for funcao
			else if ( lastchar.matches("[a-z]") ) {
				for (int i=this.display.length()-1;i>=0;i--) {
					lastchar=this.display.substring(i,i+1); //substr(number, lenght)
					if ( lastchar.matches("[a-z]") ) {
						this.display=this.display.substring(0, this.display.length()-1);
						//alert("TEST: this.expression="+this.expression);
					}
					else {
						break;
					}
				}
			}
			//qq outra situacao apaga ultimo caracter
			else {
				this.display=this.display.substring(0, this.display.length()-1);
			}
		}
		this.resultado=display;
		return "calculadora";
	}

	//metodo que liga tecla ! Ã  expressao/display 
	public void btnfactorial(ActionEvent event) {
		if ( firstdigit == true){
			this.display = "("+ this.display +"!)";
			firstdigit = false;
		} else if ( this.display.length() < 1){
			this.display = "(0!)";
		}
		else {
			this.display += "!";
		}

		this.resultado=display;
	}

	//metodo que liga teclado a  expressao/display 
	public void key(ActionEvent event) {
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
		} case "btnopen": {
			add = "(";
			break;
		} case "btnclose": {
			add = ")";
			break;
		} case "btnabs": {
			add = "abs(";
			break;
		} case "btnacos": {
			add = "acos(";
			break;
		} case "btnasin": {
			add = "asin(";
			break;
		} case "btnatan": {
			add = "atan(";
			break;
		} case "btncbrt": {
			add = "cbrt(";
			break;
		} case "btnceil": {
			add = "ceil(";
			break;
		} case "btncos": {
			add = "cos(";
			break;	
		} case "btncosh": {
			add = "cosh(";
			break;
		} case "btnexp": {
			add = "exp(";
			break;	
		} case "btnfloor": {
			add = "floor(";
			break;	
		} case "btnlog": {
			add = "log(";
			break;
		} case "btnlog10": {
			add = "log10(";
			break;
		} case "btnlog2": {
			add = "log2(";
			break;
		} case "btnsin": {
			add = "sin(";
			break;
		} case "btnsinh": {
			add = "sinh(";
			break;
		} case "btnsqrt": {
			add = "sqrt(";
			break;
		} case "btntan": {
			add = "tan(";
			break;
		} case "btntanh": {
			add = "tanh(";
			break;
		} case "btnfact": {
			add = "!";
			break;
		} case "btn-x-power-y": {
			add = "^";
			break;
		} case "btn-x-root-y": {
			add = "^(1/";
			break;
		} case "btn-pi": {
			add = "PI";
			break;
		}
		}
		if (add.length()>0) {
			addToExpression(add);
		}
	}

	public void addToExpression(String e) {
		if (e.length()<1) {
			return;
		} else if (this.firstdigit) {
			if( e.matches("[0-9]") || e.equals("(") ) {
				this.display=""+e;
			} else if(e.equals("PI")) {
				//System.out.println("TEST: Match: PI");
				this.display="3.141592653589793";
			} else if (e.equals("!") ) {
				this.display = "(" + resultado + e + ")";
			} else if (e.equals(")") ) {
				this.display = "(" + resultado + e;	
			} else if (e.equals("-") || e.equals("+") || e.equals("/") || e.equals("*") || e.equals("^") || e.equals("^(1/") ) {
				//System.out.println("TEST: Match: + - * / ");
				this.display = resultado + e;
			} else if (this.display.equals("0.0") ){
				//System.out.println("TEST: VALIDAR FUNCOES 0.0");
				this.display = e + "";
			} else {
				//System.out.println("TEST: FALTA VALIDAR FUNCOES");
				this.display = e + resultado;
			}
		} else if(e.equals("PI")) {
			//System.out.println("TEST: Match: PI");
			this.display += "3.141592653589793238";
		} else {
			this.display += e;
		}
		this.firstdigit=false;
		this.resultado=display;
	}

	public void reuse(ActionEvent ae) {
		if (this.firstdigit) {
			display = (String)ae.getComponent().getAttributes().get("exphist");
		} else {
			display += (String)ae.getComponent().getAttributes().get("exphist");
		}
		resultado = display;
	}

	//////////////////////////////////////////////////////////////////

	public synchronized ArrayList<String> getOperador(){
		return est.getOperador();
	}

	public synchronized ArrayList<String> getVezes(){
		return est.getVezes();
	}

	public synchronized ArrayList<String> getPercentagem(){
		return est.getPercentagem();
	}
}
