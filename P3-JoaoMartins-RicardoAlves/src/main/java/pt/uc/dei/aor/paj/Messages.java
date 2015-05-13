package pt.uc.dei.aor.paj;

import java.io.Serializable;
import java.util.ArrayList;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Messages implements Serializable {
	private static final long serialVersionUID = -5277521728072909525L;
	
	private ArrayList<Msgbean> conversa;
	private final int MAXMSG=10;
	private ArrayList<String> todasConversas;
	
	public Messages() {
		conversa = new ArrayList<>();
		todasConversas = new ArrayList<>();
	}
	
	//Getter tamanho da conversa
	public int getSize() {
		return conversa.size();
	}
	
	//Getter tamanho da pagina
	public int getMAXMSG() {
		return MAXMSG;
	}

	//metodo que devolve ultima pagina de mensagens
	public synchronized Msgbean[] getMessages() {
			if (conversa.size()<1) {
				return null;
			} else if (conversa.size()<MAXMSG) {
				Msgbean[] paginamensagens = new Msgbean[conversa.size()];
				for (int i=0;i<conversa.size()-1;i++) {
					paginamensagens[i]=conversa.get(i);
				}
				return paginamensagens;
			} else {
				Msgbean[] paginamensagens = new Msgbean[MAXMSG];
				for (int i=conversa.size()-MAXMSG;i<conversa.size()-1;i++) {
					paginamensagens[i]=conversa.get(i);
				}
				return paginamensagens;
			}
	}
	
	//metodo que devolve ultima pagina de mensagens ate à posicao pos
	public synchronized Msgbean[] getMessages(int pos) {
			if (conversa.size()<1) {
				return null;
			} else if (conversa.size()<MAXMSG) {
				Msgbean[] paginamensagens = new Msgbean[conversa.size()];
				for (int i=0;i<conversa.size()-1;i++) {
					paginamensagens[i]=conversa.get(i);
				}
				return paginamensagens;
			} else {
				if (pos>conversa.size()) {
					pos=conversa.size();
				} else if (pos<MAXMSG){
					pos=MAXMSG;
				}
				Msgbean[] paginamensagens = new Msgbean[MAXMSG];
				for (int i=0;i<MAXMSG;i++) {
					paginamensagens[i]=conversa.get(i+pos-MAXMSG);
				}
				return paginamensagens;
			}
	}

	public synchronized void addMessages(Msgbean m) {
			conversa.add(m);
		
	}
	
	
	
	
	public synchronized void addConversas(String username, String frase){
		todasConversas.add("<"+username+">: "+frase);
	}
	
	public synchronized ArrayList<String> recebeConversas(){
		return todasConversas;
	}
	
}
