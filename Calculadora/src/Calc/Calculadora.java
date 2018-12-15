package Calc;

public class Calculadora {

	public double Adicao(double a, double b){
		return a+b;
	}
	
	public double Subtracao(double a, double b){
		return a-b;
	}
	
	public double Multiplicacao(double a, double b){
		return a*b;
	}
	
	public double Divisao(double a, double b){
		if( b == 0) return 0;
		else return a/b;
	}
	
}
