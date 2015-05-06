/**
 * Button numeric pressed goes to display
 * @param digito: digito numerico a acrescentar [0-9]
 */
function btnnumeric(digito){
	var displaytxt = document.getElementById("basicform:basicdisplay").value;
	var firstdigit = document.getElementById("basicform:firstdigit").value;
	
	if ( firstdigit == "true" ){
		//alert ('tecla '+digito+' - 0.0 Primeiro caracter!');
		displaytxt=""+digito;
		firstdigit = "false";
		document.getElementById("basicform:firstdigit").value=firstdigit;
	}
	else if ( displaytxt.length < 1){
		//alert ('tecla '+digito+' - Primeiro caracter!');
		displaytxt=""+digito;
	}
	else {
		//alert ('tecla '+digito+'!');
		displaytxt=""+displaytxt+digito;
	}
	document.getElementById("basicform:basicdisplay").value=displaytxt;
}

/**
 * Button coma or operator pressed goes to display
 * @param simbol: ponto ou operador a acrescentar [.+-/*]
 */
function btnoperator(simbol){
	var displaytxt = document.getElementById("basicform:basicdisplay").value;
	var firstdigit = document.getElementById("basicform:firstdigit").value;
	//alert ('TEST: tecla: '+simbol);
	//Se for primeiro digito, continua a expressao como ja tendo digitos
	if ( firstdigit == "true" ){
		firstdigit = "false";
		document.getElementById("basicform:firstdigit").value=firstdigit;
		//Se for 0.0 apaga texto do ecra. qq outro valor mantem e segue construcao da expressao
		if (displaytxt=="0.0") {
			displaytxt="";
		}
	}
	//Se o display estiver vazio
	if ( displaytxt.length < 1 ) {
		//se for o sinal "-" inicia a string
		//alert ('TEST: tecla: '+simbol);
		if (simbol=="-") {
			//alert ('TEST: simbol == -');
			displaytxt=""+simbol;
		}
		//se for o "." inicia a string com "0."
		else if (simbol==".") {
			//alert ('TEST: simbol == .');
			displaytxt="0"+simbol;
		}
		//caso contrario é um erro
		else {
			alert ('Erro: Tem que introduzir caracteres numéricos primeiro!');
		}
	}
	//se o display nao esta vazio, o caracter anterior tem de ser digito numerico
	else {
		//alert ('TEST:  displaytxt.length >= 1');
		//(ERRO) var lastChar=displaytxt.substring(displaytxt.length-1);
		var lastchar=displaytxt.substr(displaytxt.length-1, 1); //substr(number, lenght)
		//alert ('TEST: tecla: '+simbol+' last char: '+lastchar);
		//se digito anterior for numerico
		if ( isdigit(lastchar) ) {
			//alert ('TEST: é numerico!');
			//se o operador for ponto
			if (simbol==".") {
				//alert ('TEST: simbol=.');
//				//testar que nao existe outro ponto nos caracteres anteriores até aparecer um operador 
				//se o texto do display so tem um caracter
				if (displaytxt.length<2) {
					displaytxt=displaytxt+simbol;
				}
				//se o texto do display tem mais do que um caracter
				else {
					for (i=displaytxt.length-2;i>=0;i--) {
						var prevchar=displaytxt.substr(i,1); //substr(number, lenght)
						//alert ('TEST: simbol=. , inside for , prevchar='+prevchar);
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
							//alert ('TEST: IS nuneric digit e i=0!');
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
		//se for operador - testar que caracter anterior não e o operador - nem .
		else if (simbol=="-" && lastChar!="-" && lastChar!=".") {
			displaytxt=displaytxt+simbol;
		}
		//se for operador . testar que caracter anterior não é .
		else if (simbol=="." && lastChar!=".") {
			displaytxt=displaytxt+"0"+simbol;
		}
			
		else {
			alert ('não pode escrever dois operadores consecutivos: '+lastChar+simbol);
		}
	}
	document.getElementById("basicform:basicdisplay").value=displaytxt;
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
 * Button clear: clear last operator or number
 */
function btnclear() {
	var displaytxt = document.getElementById("basicform:basicdisplay").value;
	//Se display tem 0 ou 1 caracter
	if ( displaytxt.length < 2) {
		displaytxt="";
	}
	//Se display tem mais do que 1 caracter
	else {
		var lastchar=displaytxt.substr(displaytxt.length-1, 1); //substr(number, lenght)
		//Se for operador
		if (lastchar=="-" || lastchar=="+" || lastchar=="*" || lastchar=="/" || lastchar==".") {
			displaytxt=displaytxt.substr(0, displaytxt.length-1);
		}
		//Se for numero
		else {
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
	}
	
	document.getElementById("basicform:basicdisplay").value=displaytxt;
}

/**
 * Button clear expression: inicializa display with 0.0
 */
function btnclearexpression() {
	var displaytxt = "0.0";
	var firstdigit = "true";
	document.getElementById("basicform:firstdigit").value=firstdigit;
	document.getElementById("basicform:basicdisplay").value=displaytxt;
}

document.onkeydown = function (e) {
    return false;
}	

$(document).ready(function(){
	$('#hist > li').on("click",function(e){
		document.getElementById("basicform:basicdisplay").value=e.target.innerHTML;
	});
});
