package pt.uc.dei.aor.paj;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class Conversor implements Serializable {

	private static final long serialVersionUID = -289276448456559729L;

	private String expressao;

	public String getExpressao() {
		return expressao;
	}

	public void setExpressao(String expressao) {
		this.expressao = expressao;
	}

	//converte em radianos 
	private String toRadian(String exp){
		double aux=0;
		double aux1=0, aux2=0,aux3=0;
		
		int i=exp.length();
		int j=0;
		String valor="";

		boolean contem=false;
		for(j=0; j<i ; j++){

			if (exp.charAt(j)==('.')){
				contem=true;
			}
		}
		if (contem){
			j=0;
			while(exp.charAt(j)!=('.')){
				valor+=exp.charAt(j);
				j++;
			}
		}
		
		if(contem){
			aux=Double.parseDouble(valor);
			aux1=Math.toRadians(aux);
			aux2=aux+1;
			aux3=Math.toRadians(aux2);
			aux1=(aux1+aux3)/2;

		}else{
		aux = Double.parseDouble(exp);
		//converter para radianos
		aux1=Math.toRadians(aux);
		}
		exp=aux1+"";
		return exp;

	}

	
	//decomposição das operações
	//decomposição da expressão caracter a caracter para contagem dos operadores
	public String decompoe(String exp){
		int j=exp.length();
		String convertida="", aux="";
		
		if (exp.startsWith("-")){
			convertida+=exp.charAt(0);
			exp=exp.substring(1,j);
			j=j-1;}
		while(j>=1){
			if (exp.startsWith("acos")){

				convertida+="acos(";
				exp=exp.substring(5,j);
				aux=pesquisa(exp);
				convertida+=aux;
				exp=eliminaP(exp);
				j=exp.length();

			}else if (exp.startsWith("asin")){

				convertida+="asin(";
				exp=exp.substring(5,j);
				aux=pesquisa(exp);
				convertida+=aux;
				exp=eliminaP(exp);
				j=exp.length();

			}else if (exp.startsWith("atan")){

				convertida+="atan(";
				exp=exp.substring(5,j);
				aux=pesquisa(exp);
				convertida+=aux;
				exp=eliminaP(exp);
				j=exp.length();

			}else if (exp.startsWith("cosh")){

				convertida+="cosh(";
				 exp=exp.substring(5,j);
				 aux=pesquisa(exp);
				 convertida+=aux;
				 exp=eliminaP(exp);
				 j=exp.length();

				
			}else if (exp.startsWith("cos")){

				convertida+="cos(";
				 exp=exp.substring(4,j);
				 aux=pesquisa(exp);
				 convertida+=aux;
				 exp=eliminaP(exp);
				 j=exp.length();

				
			}else if (exp.startsWith("sinh")){

				convertida+="sinh(";
				 exp=exp.substring(5,j);
				 aux=pesquisa(exp);
				 convertida+=aux;
				 exp=eliminaP(exp);
				 j=exp.length();
				
				
			}else if (exp.startsWith("sin")){

				convertida+="sin(";
				 exp=exp.substring(4,j);
				 aux=pesquisa(exp);
				 convertida+=aux;
				 exp=eliminaP(exp);
				 j=exp.length();

				
			}else if (exp.startsWith("tanh")){

				convertida+="tanh(";
				 exp=exp.substring(5,j);
				 aux=pesquisa(exp);
				 convertida+=aux;
				 exp=eliminaP(exp);
				 j=exp.length();

				
			}else if (exp.startsWith("tan")){

				convertida+="tan(";
				exp=exp.substring(4,j);
				aux=pesquisa(exp);
				convertida+=aux;
				exp=eliminaP(exp);
				j=exp.length();

			}else {
				convertida+=exp.charAt(0);
				exp=exp.substring(1,j);
				j=j-1;
			}
		}
		return convertida;
	}

	private String pesquisa(String exp){
		String aux="";
		int j=exp.length();
		while (!exp.startsWith(")")){
			aux+=exp.charAt(0);
			exp=exp.substring(1,j);
			j=j-1;
		}
		aux=toRadian(aux);

		return aux;
	}
	
private static String eliminaP(String exp){
		
		int j=exp.length();
		while (!exp.startsWith(")")){
			exp=exp.substring(1,j);
			j=j-1;
		}
	
		return exp;
	}
}