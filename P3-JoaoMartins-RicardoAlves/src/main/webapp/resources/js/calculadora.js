
function btnparentices(fname){
	var displaytxt = document.getElementById("calculadoraform:scientificdisplay").value;
	var firstdigit = document.getElementById("calculadoraform:firstdigit").value;

	if ( firstdigit == "true" ){
		//alert ('tecla '+fname+' - 0.0 Primeiro caracter!');
		displaytxt=""+fname;
		firstdigit = "false";
		document.getElementById("calculadoraform:firstdigit").value=firstdigit;
		if (fname=="(") {
			displaytxt=""+fname;
		}
		else {
			alert ('Erro: tem que abrir parêntesis primeiro!');
		}
	}
	else if ( displaytxt.length < 1){
		//alert ('tecla '+fname+' - Primeiro caracter!');
		if (fname=="(") {
			displaytxt=""+fname;
		}
		else {
			alert ('Erro: tem que abrir parêntesis primeiro!');
		}
	}
	
	else {
		var lastchar=displaytxt.substr(displaytxt.length-1, 1); 	
		if (isoperator(lastchar) || isfn(lastchar) || lastchar=="(") {
			if (fname=="(") {
				displaytxt=""+displaytxt+fname;
			}
			else {
				alert ('Erro: tem que digitar um numero primeiro antes de fechar parêntesis!');
			}
		}
		else if (isdigit(lastchar) || lastchar==")") {
			if (fname==")") {
				var contador = 0;
				for (i=displaytxt.length-1;i>=0;i--) {
					var prevchar=displaytxt.substr(i,1); 
					if ( prevchar==")" ) {
						
						contador--;
					}
					else if ( prevchar=="(" ) {
						
						contador++;
					}
				}
				if (contador > 0) {
					displaytxt=""+displaytxt+fname;
				}
				else {
					alert ('Erro: tem que abrir primeiro parêntesis antes de os fechar!');
				}
			}
			else if (fname=="("){
				alert ('Erro: primeiro tem que digitar um operador ou função antes de abrir parêntesis!');
			}
		}
		else if(lastchar=="!"){
			displaytxt+=")";
		}
		
	}
	document.getElementById("calculadoraform:scientificdisplay").value=displaytxt;
}

function btnfunction(fname){
	var displaytxt = document.getElementById("calculadoraform:scientificdisplay").value;
	var firstdigit = document.getElementById("calculadoraform:firstdigit").value;

	if ( firstdigit == "true" ){
		
		displaytxt=""+fname;
		firstdigit = "false";
		document.getElementById("calculadoraform:firstdigit").value=firstdigit;
	}
	else if ( displaytxt.length < 1){
		
		displaytxt=""+fname;
	}
	else {
		var lastchar=displaytxt.substr(displaytxt.length-1, 1); 
		
		if (isoperator(lastchar)) {
			displaytxt=""+displaytxt+fname;
		}
		else if (lastchar=="("){
			
			displaytxt=""+displaytxt+fname;
		}
		else {
			alert ('Erro: Tem que utilizar um operador antes da funcao '+fname+'!');
		}
	}
	document.getElementById("calculadoraform:scientificdisplay").value=displaytxt;
}

/**
 * Button numeric pressed goes to display
 * @param digito: digito numerico a acrescentar [0-9]
 */
function btnnumeric(digito){
	var displaytxt = document.getElementById("calculadoraform:scientificdisplay").value;
	var firstdigit = document.getElementById("calculadoraform:firstdigit").value;

	if ( firstdigit == "true" ){
	
		displaytxt=""+digito;
		firstdigit = "false";
		document.getElementById("calculadoraform:firstdigit").value=firstdigit;
	}
	else if ( displaytxt.length < 1){
		
		displaytxt=""+digito;
	}
	else {
		var lastchar=displaytxt.substr(displaytxt.length-1, 1); 
	
		if (isoperator(lastchar) || isfn(lastchar) || isdigit(lastchar) || lastchar=="(" || lastchar==".") {
			displaytxt=""+displaytxt+digito;
		}
		else if (lastchar==")") {
			alert ('Erro: tecla '+digito+'\nPrimeiro tem que introduzir um operador ou função!');
		}
	}
	document.getElementById("calculadoraform:scientificdisplay").value=displaytxt;
}

/**
 * Button coma or operator pressed goes to display
 * @param simbol: ponto ou operador a acrescentar [.+-/*]
 */
function btnoperator(simbol){
	var displaytxt = document.getElementById("calculadoraform:scientificdisplay").value;
	var firstdigit = document.getElementById("calculadoraform:firstdigit").value;
	
	if ( firstdigit == "true" ){
		firstdigit = "false";
		document.getElementById("calculadoraform:firstdigit").value=firstdigit;
		
		if (displaytxt=="0.0") {
			displaytxt="";
		}
	}
	
	if ( displaytxt.length < 1 ) {
		//se for o sinal "-" inicia a string
		
		if (simbol=="-") {
			
			displaytxt=""+simbol;
		}
		//se for o "." inicia a string com "0."
		else if (simbol==".") {
			
			displaytxt="0"+simbol;
		}
		//caso contrario é um erro
		else {
			alert ('Erro: Tem que introduzir caracteres numéricos primeiro!');
		}
	}
	//se o display nao esta vazio, o caracter anterior tem de ser digito numerico
	else {
		var lastchar=displaytxt.substr(displaytxt.length-1, 1); 
		

		//se digito anterior for numerico
		if ( isdigit(lastchar) ) {
			
			//se o operador for ponto
			if (simbol==".") {
				
//				//testar que nao existe outro ponto nos caracteres anteriores até aparecer um operador 
				//se o texto do display so tem um caracter
				if (displaytxt.length<2) {
					displaytxt=displaytxt+simbol;
				}
				//se o texto do display tem mais do que um caracter
				else {
					for (i=displaytxt.length-2;i>=0;i--) {
						var prevchar=displaytxt.substr(i,1); //substr(number, lenght)
						
						// se caracter nao for digito numerico
						if ( ! isdigit(prevchar) ) {
							//se encontrar um ponto estamos perante erro
							if (prevchar==".") {
								alert ('Erro: o numero digitado já tem um ponto!');
								break;
							}
							//se encontrar um operador acrescenta o ponto ao display
							else {
								displaytxt=displaytxt+simbol;
								break;
							}
						}
						// se caracter for digito numerico e chegarmos ao inicio da string
						else if (i<=0) {
						
							displaytxt=displaytxt+simbol;
							break;
						}
					}
				}
			}
			//se o operador nao e ponto, acrescenta operador ao display
			else {
				displaytxt=displaytxt+simbol;
			}
		}
		//se digito anterior for factorial
		else if ( lastchar=="!") {
			displaytxt=""+displaytxt+simbol;
		}
		//se digito anterior for de uma funcao
		else if ( isfn(lastchar) ) {
			if (simbol=="-") {
				displaytxt=""+displaytxt+simbol;
			}
			else if (simbol==".") {
				displaytxt=""+displaytxt+"0"+simbol;
			}
			else {
				alert ('Erro: com o operador'+simbol+' a expressão fica inválida');
			}
		}

		//se digito anterior for um fechar parentices

		else if (lastchar==")") {
			
			displaytxt=displaytxt+simbol;
		}

		//se digito anterior for um operador
		else if ( isoperator(lastchar) ) {
			if (simbol==".") {
				displaytxt=displaytxt+"0"+simbol;
			}
			else if (simbol=="-") {
				if (lastchar!="-") {
					displaytxt=""+displaytxt+simbol;
				}
				else if (lastchar=="-") {
					alert ('Erro: com o operador'+simbol+' a expressão fica inválida.\nUlilizar a operador "+" em vez de "--"');
				}
				else {
					alert ('Erro: com o operador'+simbol+' a expressão fica inválida.\nTem que utilizar um numero ou função antes de\npoder utilizar outro operador.');
				}
			}
		}

		//se digito anterior for o ponto
		else if (lastchar==".") {
			//se for operador . testar que caracter anterior não é .
			alert ('Erro: Após o ponto tem que escrever dígitos numéricos em vez do operador: '+simbol);
		}


		//se digito anterior for um abrir parentices
		else if (lastchar=="(") {
			if (simbol=="-") {
				displaytxt=displaytxt+simbol;
			}
			else {
				alert ('Erro: não pode escrever o operador '+simbol+' a seguir à abertura de parentices!');
			}
		}

		else {
			alert ('Erro: não pode escrever dois operadores consecutivos: '+lastchar+simbol);
		}
	}
	document.getElementById("calculadoraform:scientificdisplay").value=displaytxt;
}


/**
 * if char is digit number [0-9] returns true. 
 * Else returns false.
 */
function isdigit(char){
	//alert("TEST: Char lenght: " + char.length);
	if (char.length!=1) {
		//alert("TEST: Char lenght != 1 ");
		return false;
	}
	else if (char=="0" || char=="1" || char=="2" || char=="3" || char=="4" || char=="5" || char=="6" || char=="7" || char=="8" || char=="9") {
		//alert("TEST: Char is numeric and i will return true !");
		return true;
	} 
	else {
		return false;
	}
}

/**
 * if char is operator [-+*\/] returns true. 
 * Else returns false.
 */
function isoperator(char){
	//alert("TEST: Char lenght: " + char.length);
	if (char.length!=1) {
		//alert("TEST: Char lenght != 1 ");
		return false;
	}
	else if (char=="-" || char=="+" || char=="*" || char=="/") {
		//alert("TEST: Char is numeric and i will return true !");
		return true;
	} 
	else {
		return false;
	}
}

/**
 * if char is function returns true. 
 * Else returns false.
 */
function isfn(char){
	//alert("TEST: Char lenght: " + char.length);
	if (char.length!=1) {
		//alert("TEST: Char lenght != 1 ");
		return false;
	}
	else if (char=="a" || char=="b" || char=="c" || char=="d" || char=="e" || char=="f" || char=="g" || char=="h" ||
			char=="i" || char=="j" || char=="k" || char=="l" || char=="m" || char=="n" || char=="o" || char=="p" || 
			char=="q" || char=="r" || char=="s" || char=="t" || char=="u" || char=="v" || char=="w" || char=="x" || 
			char=="y" || char=="z" || char=="^") {
		//alert("TEST: Char is numeric and i will return true !");
		return true;
	} 
	else {
		return false;
	}
}

/**
 * Button clear: clear last operator or number
 */
function btnclear() {
	var displaytxt = document.getElementById("calculadoraform:scientificdisplay").value;
	//Se display tem 0 ou 1 caracter
	if ( displaytxt.length < 2) {
		displaytxt="";
	}
	//Se display tem mais do que 1 caracter
	else {
		var lastchar=displaytxt.substr(displaytxt.length-1, 1); //substr(number, lenght)
		//Se for operador
		if (lastchar=="-" || lastchar=="+" || lastchar=="*" || lastchar=="/" || lastchar=="." || lastchar=="^" || lastchar=="!" ) {
			displaytxt=displaytxt.substr(0, displaytxt.length-1);
		}
		//Se for numero
		else if ( isdigit(lastchar) ){
			for (i=displaytxt.length-1;i>=0;i--) {
				lastchar=displaytxt.substr(i,1); //substr(number, lenght)
				if ( isdigit(lastchar) ) {
					displaytxt=displaytxt.substr(0, displaytxt.length-1);
					//alert("TEST: displaytxt="+displaytxt);
				}
				else if ( lastchar=="." ) {
					displaytxt=displaytxt.substr(0, displaytxt.length-1);
					//alert("TEST: displaytxt="+displaytxt);
				}
				else {
					break;
				}
			}
		}
		//se for parentices
		else if (lastchar=="(" || lastchar==")") {
			displaytxt=displaytxt.substr(0, displaytxt.length-1);
		}
		//se for funcao
		else if ( isfn(lastchar) ) {
			for (i=displaytxt.length-1;i>=0;i--) {
				lastchar=displaytxt.substr(i,1); //substr(number, lenght)
				if ( isfn(lastchar) ) {
					displaytxt=displaytxt.substr(0, displaytxt.length-1);
					//alert("TEST: displaytxt="+displaytxt);
				}
				else {
					break;
				}
			}
		}
		//qq outra situacao apaga ultimo caracter
		else {
			displaytxt=displaytxt.substr(0, displaytxt.length-1);
		}
	}

	document.getElementById("calculadoraform:scientificdisplay").value=displaytxt;
}

/**
 * Button clear expression: Reset display with 0.0
 */
function btnclearexpression() {
	var displaytxt = "0.0";
	var firstdigit = "true";
	document.getElementById("calculadoraform:firstdigit").value=firstdigit;
	document.getElementById("calculadoraform:scientificdisplay").value=displaytxt;
}

function btnfactorial() {
	var displaytxt = document.getElementById("calculadoraform:scientificdisplay").value;
	var firstdigit = document.getElementById("calculadoraform:firstdigit").value;
	var pi = "3.141592653589793238";

	if ( firstdigit == "true" ){
		//alert ('tecla ! - 0.0 Primeiro caracter!');
		displaytxt="";
		alert ('Erro: tem que colocar um numero antes desta funcão.');
		firstdigit = "false";
		document.getElementById("calculadoraform:firstdigit").value=firstdigit;
	}
	else if ( displaytxt.length < 1){
		alert ('Erro: tem que colocar um numero antes desta funcão.');
	}
	else {
		var lastchar=displaytxt.substr(displaytxt.length-1, 1); 	
		if (isoperator(lastchar) || isfn(lastchar) || lastchar=="(") {
			alert ('Erro: tem que colocar um numero antes desta função.');
		}
		else if(lastchar==")" || lastchar=="!") {
			displaytxt=displaytxt+"!"
		}
		else if(isdigit(lastchar)) {
			displaytxt=displaytxt+"!"
		}
		else {
			alert ('Erro: tem que colocar um numero antes desta funcão.');
		}
	}
	document.getElementById("calculadoraform:scientificdisplay").value=displaytxt;
}

function btnxrooty() {
	var displaytxt = document.getElementById("calculadoraform:scientificdisplay").value;
	var firstdigit = document.getElementById("calculadoraform:firstdigit").value;
	var pi = "3.141592653589793238";

	if ( firstdigit == "true" ){
		//alert ('tecla pi - 0.0 Primeiro caracter!');
		displaytxt="";
		alert ('Erro: tem que colocar um numero antes desta funcão.');
		firstdigit = "false";
		document.getElementById("calculadoraform:firstdigit").value=firstdigit;
	}
	else if ( displaytxt.length < 1){
		alert ('Erro: tem que colocar um numero antes desta funcão.');
	}
	else {
		var lastchar=displaytxt.substr(displaytxt.length-1, 1); 	
		if (isoperator(lastchar) || isfn(lastchar) || lastchar=="(") {
			alert ('Erro: tem que colocar um numero antes desta função.');
		}
		else if(lastchar==")" || lastchar=="!") {
			displaytxt=displaytxt+"^(1/"
		}
		else if(isdigit(lastchar)) {
			displaytxt=displaytxt+"^(1/"
		}
		else {
			alert ('Erro: tem que colocar um numero antes desta funcão.');
		}
	}
	document.getElementById("calculadoraform:scientificdisplay").value=displaytxt;
}

function btnpi() {
	var displaytxt = document.getElementById("calculadoraform:scientificdisplay").value;
	var firstdigit = document.getElementById("calculadoraform:firstdigit").value;
	var pi = "3.141592653589793238";

	if ( firstdigit == "true" ){
		//alert ('tecla pi - 0.0 Primeiro caracter!');
		displaytxt=""+pi;
		firstdigit = "false";
		document.getElementById("calculadoraform:firstdigit").value=firstdigit;
	}
	else if ( displaytxt.length < 1){
		displaytxt=""+pi;
	}
	else {
		var lastchar=displaytxt.substr(displaytxt.length-1, 1); 	
		if (isoperator(lastchar) || isfn(lastchar) || lastchar=="(") {
			displaytxt=""+displaytxt+pi;
		}
		else if(isdigit(lastchar)) {
			alert ('Erro: tem que colocar um operador ou abrir parentices antes de PI.');
		}
	}

	document.getElementById("calculadoraform:scientificdisplay").value=displaytxt;
}

document.onkeydown = function (e) {
	return false;
}	

$(document).ready(function(){
	$('#hist1 > li').on("click",function(e){
		document.getElementById("calculadoraform:scientificdisplay").value=e.target.innerHTML;
	});
});
