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

	//Getter e Setter associados Ã  variÃ¡vel graus/radianos
	public String getBtnradio() {
		return btnradio;
	}
	public void setBtnradio(String btnradio) {
		this.btnradio = btnradio;
	}

	//Getter e Setter associados Ã  variÃ¡vel basica/cientifica
	public String getTipocalc() {
		//System.out.println("get tipoclac");
		return tipocalc;
	}
	public void setTipocalc(String tipocalc) {
		this.tipocalc = tipocalc;
		//System.out.println("set tipoclac");
	}
	
	//Getter e Setter associados Ã  variÃ¡vel expression
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
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

		//envio para os dados estatisticos
		setValor(this.display);
		this.setFirstdigit(true);
		
		// FALTA TESTAR SE O RESULTADO E NUMERICO ANTES DE ENVIAR PARA HISTORICO
		
		//envia o valor da expressÃ£o introduzida para o historico
		pickHist.init(this.display);
				
		//acrescentar tempo do calculo ao historico
		pickHist.addTime(String.valueOf(tempo));
		
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
		this.expression = "0.0";
		this.resultado = "0.0";
		this.firstdigit=true;
		return "calculadora";
	}
	
		
	//metodo que liga tecla C Ã  expressao/display 
	public String ckey() {
		//Se display tem 0 ou 1 caracter
		if ( this.expression.length() < 2) {
			this.expression="";
		}
		//Se display tem mais do que 1 caracter
		else {
			String lastchar=this.expression.substring(this.expression.length()-1, this.expression.length()); 
			//Se for operador
			if (lastchar=="-" || lastchar=="+" || lastchar=="*" || lastchar=="/" || lastchar=="." || lastchar=="^" || lastchar=="!" ) {
				this.expression=this.expression.substring(0, this.expression.length()-1);
			}
			//Se for numero
			else if ( lastchar.matches("[0-9]") ){
				for (int i=this.expression.length()-1;i>=0;i--) {
					lastchar=this.expression.substring(i,i+1); 
					if (  lastchar.matches("[0-9]") ) {
						this.expression=this.expression.substring(0, this.expression.length()-1);
					}
					else if ( lastchar=="." ) {
						this.expression=this.expression.substring(0, this.expression.length()-1);
					}
					else {
						break;
					}
				}
			}
			//se for parentices
			else if (lastchar=="(" || lastchar==")") {
				this.expression=this.expression.substring(0, this.expression.length()-1);
			}
			//se for funcao
			else if ( lastchar.matches("[a-z]") ) {
				for (int i=this.expression.length()-1;i>=0;i--) {
					lastchar=this.expression.substring(i,i+1); //substr(number, lenght)
					if ( lastchar.matches("[a-z]") ) {
						this.expression=this.expression.substring(0, this.expression.length()-1);
						//alert("TEST: this.expression="+this.expression);
					}
					else {
						break;
					}
				}
			}
			//qq outra situacao apaga ultimo caracter
			else {
				this.expression=this.expression.substring(0, this.expression.length()-1);
			}
		}
		this.resultado=expression;
		return "calculadora";
	}
	
	//metodo que liga tecla ! Ã  expressao/display 
	public void btnfactorial(ActionEvent event) {
		if ( firstdigit == true){
			this.expression = "("+ this.expression +"!)";
			firstdigit = false;
		} else if ( this.expression.length() < 1){
			this.expression = "(0!)";
		}
		else {
			this.expression += "!";
		}
		
		this.resultado=expression;
	}
	
	//metodo que liga teclado Ã  expressao/display 
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
				this.expression=""+e;
			} else if(e.equals("PI")) {
				//System.out.println("TEST: Match: PI");
				this.expression="3.141592653589793";
			} else if (e.equals("!") ) {
				this.expression = "(" + resultado + e + ")";
			} else if (e.equals(")") ) {
				this.expression = "(" + resultado + e;	
			} else if (e.equals("-") || e.equals("+") || e.equals("/") || e.equals("*") || e.equals("^") || e.equals("^(1/") ) {
				//System.out.println("TEST: Match: + - * / ");
				this.expression = resultado + e;
			} else if (this.expression.equals("0.0") ){
				//System.out.println("TEST: VALIDAR FUNCOES 0.0");
				this.expression = e + "";
			} else {
				//System.out.println("TEST: FALTA VALIDAR FUNCOES");
				this.expression = e + resultado;
			}
		} else if(e.equals("PI")) {
			//System.out.println("TEST: Match: PI");
			this.expression += "3.141592653589793238";
		} else {
			this.expression += e;
		}
		this.firstdigit=false;
		this.resultado=expression;
	}

	public void reuse(ActionEvent ae) {
		expression += (String)ae.getComponent().getAttributes().get("reut");
		resultado = expression;
	}
}
