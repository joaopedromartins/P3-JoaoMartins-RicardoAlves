package pt.uc.dei.aor.paj;

import java.io.Serializable;
import java.util.EmptyStackException;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.operator.Operator;

@Named
@SessionScoped

public class Calculadora implements Serializable{
	private static final long serialVersionUID = 6030549976219071079L;

	private String exp;
	private float tempoTotal;


	public Calculadora() {
		this.exp = "";
	}

	//implementação da função factorial
	Operator factorial = new Operator("!", 1, true, Operator.PRECEDENCE_POWER + 1) {

		@Override
		public double apply(double... args) {
			final int arg = (int) args[0];
			if ((double) arg != args[0]) {
				throw new IllegalArgumentException("O operando do factorial tem de ser inteiro");
			}
			if (arg < 0) {
				throw new IllegalArgumentException("O operando do factorial não pode ser inferior a zero");
			}
			double result = 1;
			for (int i = 1; i <= arg; i++) {
				result *= i;
			}
			return result;
		}
	};


	//calcula o resultado da expressão pela lib Exp4j, apanhando as excepções apresentadas durante a execução da lib 
	public String getExp(){
		String result;

		long tempoInicial=System.nanoTime();
		long tempoFinal=tempoInicial;

		try {
			Expression e = new ExpressionBuilder(this.exp)
			.operator(factorial)
			.build();

			double result1 = e.evaluate();

			tempoFinal=System.nanoTime();

			result=result1+"";


		} catch (NumberFormatException nfe) {
			result="NumberFormatException";
		}catch(EmptyStackException ne){
			result="Parêntesis incorrectos";
		}catch(ArithmeticException ne){
			result="Divisao por zero";
		}catch(IllegalArgumentException ne){
			result="Argumentos inválidos";
		}
		tempoTotal =(tempoFinal - tempoInicial)/1000000000.0f;
		//System.out.println(tempoTotal);

		setExp(result);
		return result;
	}

	//actualização do valor da expressão a calcular
	public void setExp(String exp) {
		this.exp = exp;
	}

	public float getTempo(){
		return tempoTotal;
	}


}
