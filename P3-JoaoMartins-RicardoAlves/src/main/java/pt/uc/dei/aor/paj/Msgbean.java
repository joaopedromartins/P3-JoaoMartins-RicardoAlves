package pt.uc.dei.aor.paj;

public class Msgbean {
	private Userbean emissor;
	private String frase;
	
	//construtor
	Msgbean(Userbean emissor, String frase) {
		this.emissor=emissor;
		if (frase.length()<256) {
			this.frase=frase;
		} else {
			this.frase=frase.substring(0, 254);
		}
	}
	
	// Getter para o User emissor
	public Userbean getEmissor() {
		return emissor;
	}
	
	// Getter para a String frase da mensagem
	public String getFrase() {
		return frase;
	}
		
}
