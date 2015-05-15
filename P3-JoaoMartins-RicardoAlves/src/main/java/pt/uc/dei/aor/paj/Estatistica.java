package pt.uc.dei.aor.paj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class Estatistica implements Serializable{
	private static final long serialVersionUID = -8001386905558621067L;
	
	private HashMap<String, Integer> cont;
	private TreeMap<String, Integer> mapa;
	private ArrayList<String> resultado;
	private ArrayList<String> operador;
	private ArrayList<String> vezes;
	private ArrayList<String> percentagem;
	
	
	public Estatistica() {
		
		this.cont = new HashMap<String, Integer>();
		this.cont.put("+",0);
		this.cont.put("-",0);
		this.cont.put("*",0);
		this.cont.put("/",0);
		this.cont.put("abs",0);
		this.cont.put("acos",0);
		this.cont.put("asin",0);
		this.cont.put("atan",0);
		this.cont.put("cbrt",0);
		this.cont.put("ceil",0);
		this.cont.put("cos",0);
		this.cont.put("cosh",0);
		this.cont.put("exp",0);
		this.cont.put("floor",0);
		this.cont.put("log",0);
		this.cont.put("log10",0);
		this.cont.put("log2",0);
		this.cont.put("sin",0);
		this.cont.put("sinh",0);
		this.cont.put("sqrt",0);
		this.cont.put("tan",0);
		this.cont.put("tanh",0);
		this.cont.put("!",0);
		this.mapa=new TreeMap<String, Integer>();
		this.resultado=new ArrayList<String>();
		this.operador=new ArrayList<String>();
		this.vezes=new ArrayList<String>();
		this.percentagem=new ArrayList<String>();
	}

	//getter e setter do resultado 
	public ArrayList<String> getResultado(){
			String aux="";
			//this.resultado.add(aux);
		if (this.mapa.size()>0){
			this.resultado=new ArrayList<String>();
			for(String op:this.mapa.keySet()){
				aux=op+" :  "+ getContOperador(op);
				this.resultado.add(aux);
			}
		}
		return this.resultado; 
	}
	
	public void setResultado(String exp){
		decompoe(exp);
		this.mapa=mapaOrdenado();		
	}
	
	//decomposição da expressão caracter a caracter para contagem dos operadores
	private void decompoe(String exp){
		int j=exp.length();
		if (exp.startsWith("-")){
			exp=exp.substring(1,j);
			j=j-1;}
		while(j>=1){
			if (exp.startsWith("+")){
				adiciona("+");
				exp=exp.substring(1,j);
				j=j-1;
			}else if (exp.startsWith("-")){
				exp=exp.substring(1,j);
				if (!exp.startsWith("(")){
					adiciona("-");}
				j=j-1;
			}else if (exp.startsWith("*")){
				adiciona("*");
				exp=exp.substring(1,j);
				j=j-1;
			}else if (exp.startsWith("/")){
				adiciona("/");
				exp=exp.substring(1,j);
				j=j-1;
			}else if (exp.startsWith("!")){
				adiciona("!");
				exp=exp.substring(1,j);
				j=j-1;
			}else
				if (exp.startsWith("abs")){
				adiciona("abs");
				exp=exp.substring(3,j);
				j=j-3;
			}else if (exp.startsWith("acos")){
				adiciona("acos");
				exp=exp.substring(4,j);
				j=j-4;
			}else if (exp.startsWith("asin")){
				adiciona("asin");
				exp=exp.substring(4,j);
				j=j-4;
			}else if (exp.startsWith("atan")){
				adiciona("atan");
				exp=exp.substring(4,j);
				j=j-4;
			}else if (exp.startsWith("cbrt")){
				adiciona("cbrt");
				exp=exp.substring(4,j);
				j=j-4;
			}else if (exp.startsWith("ceil")){
				adiciona("ceil");
				exp=exp.substring(4,j);
				j=j-4;
			}else if (exp.startsWith("cosh")){
				adiciona("cosh");
				exp=exp.substring(4,j);
				j=j-4;
			}else if (exp.startsWith("cos")){
				adiciona("cos");
				exp=exp.substring(3,j);
				j=j-3;
			}else if (exp.startsWith("exp")){
				adiciona("exp");
				exp=exp.substring(3,j);
				j=j-3;
			}else if (exp.startsWith("floor")){
				adiciona("floor");
				exp=exp.substring(5,j);
				j=j-5;
			}else if (exp.startsWith("log2")){
				adiciona("log2");
				exp=exp.substring(4,j);
				j=j-4;
			}else if (exp.startsWith("log10")){
				adiciona("log10");
				exp=exp.substring(5,j);
				j=j-5;
			}else if (exp.startsWith("log")){
				adiciona("log");
				exp=exp.substring(3,j);
				j=j-3;
			}else if (exp.startsWith("sinh")){
				adiciona("sinh");
				exp=exp.substring(4,j);
				j=j-4;
			}else if (exp.startsWith("sin")){
				adiciona("sin");
				exp=exp.substring(3,j);
				j=j-3;
			}else if (exp.startsWith("sqrt")){
				adiciona("sqrt");
				exp=exp.substring(4,j);
				j=j-4;
			}else if (exp.startsWith("tanh")){
				adiciona("tanh");
				exp=exp.substring(4,j);
				j=j-4;
			}else if (exp.startsWith("tan")){
				adiciona("tan");
				exp=exp.substring(3,j);
				j=j-3;
			}else {
				exp=exp.substring(1,j);
				j=j-1;
			}
		}
	}
	
	//actualiza o contador do operador
	private void adiciona(String operador){
		int contador=this.cont.get(operador);
		contador++;
		this.cont.put(operador, contador);
	}

	//Ordenação das quantidades dos operadores
	private TreeMap<String, Integer> mapaOrdenado(){
		TreeMap<String, Integer> sortedMap = SortByValue(cont);  
		return sortedMap;
	}
	private static TreeMap<String, Integer> SortByValue(HashMap<String, Integer> map) {
		ValueComparator vc =  new ValueComparator(map);
		TreeMap<String,Integer> sortedMap = new TreeMap<String,Integer>(vc);
		sortedMap.putAll(map);
		return sortedMap;
	}
	
	//devolve a quantidade por operador
	private int getContOperador(String operador){
		return this.cont.get(operador);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	private void estatistica(){
		ArrayList<String> result = new ArrayList<String>();
		result = getResultado();
		operador.clear();
		vezes.clear();
		percentagem.clear();
		if(result.size()>0){
			int total=0;
			for(String s:result){
				s.replaceAll("\\s+","");
				String[] ps = s.split(":");
				String p2 = ps[1];
				total += Integer.parseInt(p2);
			}
			if(total>0){
				for(String s:result){
					s.replaceAll("\\s+","");
					String[] parts = s.split(":");
					String part1 = parts[0];
					String part2 = parts[1];
					if(part2.equals("0")){
						break;
					}else{
						operador.add(part1);
						vezes.add(part2);
						int number = Integer.parseInt(part2);
						double per = ((number/total)*100);
						String part3 = String.format("%.1f", per);
						percentagem.add(part3);
					}
				}
			}
		}
	}
	
	public synchronized ArrayList<String> getOperador(){
		estatistica();
		return this.operador;
	}
	
	public synchronized ArrayList<String> getVezes(){
		estatistica();
		return this.vezes;
	}
	
	public synchronized ArrayList<String> getPercentagem(){
		estatistica();
		return this.percentagem;
	}
	
}
