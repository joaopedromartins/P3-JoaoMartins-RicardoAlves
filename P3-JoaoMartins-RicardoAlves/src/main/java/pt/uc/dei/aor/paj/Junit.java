package pt.uc.dei.aor.paj;

public class Junit {

	public String factorial(String exp){
		Calculadora calc = new Calculadora();
		calc.setExp(exp+"!");
		return calc.getExp();
	}
	
	public int multiply(int number1, int number2){
		
		return number1 * number2;
	}
}
